package com.aprendre_DAO.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;


import com.aprendre_DAO.exception.DAOException;

import static com.aprendre_DAO.dao.DAO_tools.InitRP;
import static com.aprendre_DAO.dao.DAO_tools.fermer;

import com.aprendre_DAO.c_table.Voyage;
import com.aprendre_DAO.dao.DAOFactory;

public class VoyageDaoImpl implements VoyageDao {
	
	private DAOFactory daofactory;
	private static final String SQL_Select 					= "SELECT * FROM `voyage` GROUP BY continent" ;
	private static final String SQL_Select_PAYS 			= "SELECT * FROM `voyage` ORDER BY continent" ;
	private static final String SQL_Select_PAYS_p	 		= "SELECT * FROM `pays_v` ORDER BY pays" ;
	private static final String SQL_Select_RECHERCHE 		= "SELECT * FROM `pays_v`,`voyage` AS vg WHERE vg.continent LIKE ? AND date_v LIKE ? AND pays LIKE ? AND ville LIKE ? AND dure_v LIKE ? AND type_v LIKE ? AND prix_v <= ? AND pays LIKE pays_v ORDER BY pays" ;
	private static final String SQL_Select_TYPE 			= "SELECT * FROM `pays_v` WHERE type_v = ?" ;
	private static final String SQL_Select_PAR_PAYS 		= "SELECT * FROM `pays_v` as pv, `voyage` WHERE pv.pays = ? AND pays_v = ?" ;
//	private static final String SQL_Select_PAR_CONTINENT 	= "SELECT `continent`,`pays_v`,`id_v_p`, `pays`, `ville`, `date_v`, `temp_v`, `dure_v`, `type_v`, `prix_v`,`description`,`lien` FROM `voyage`, `pays_v` WHERE continent = ? AND pays_v = pays GROUP BY pays" ;
	private static final String SQL_INSERT_P 				= "INSERT INTO pays_v (`pays`, `ville`, `date_v`, `temp_v`, `dure_v`, `type_v`, `prix_v`,`description`,`lien`) SELECT ?, ?, ?, ?,?,?,?,?,? FROM DUAL WHERE NOT EXISTS ( SELECT `ville` FROM pays_v WHERE ville like ? AND date_v LIKE ? ) ";
	private static final String SQL_DELETE_P 				= "DELETE FROM pays_v WHERE id_v_p = ?";
	private static final String SQL_INSERT_V 				= "INSERT INTO voyage (`continent` , `pays_v`) SELECT ?, ? FROM DUAL WHERE NOT EXISTS ( SELECT `pays_v` FROM voyage WHERE pays_v = ? ) ";
//	private static final String SQL_DELETE_V 				= "DELETE FROM voyage WHERE pays_v = ?";
	VoyageDaoImpl( DAOFactory daofactory ) {
        this.daofactory = daofactory;
    
	}
	
	@Override
	public List<Voyage> list_v() throws DAOException{
		Connection con = null;
		PreparedStatement p_st = null ;
		ResultSet rs = null ;
		List<Voyage> LV = new ArrayList<Voyage>();	
		try {
			con = daofactory.getConnection();
			p_st = InitRP( con, SQL_Select, false );
			rs = p_st.executeQuery();
			while( rs.next() ) {
				LV.add( setAllC(rs) );
			}
		}catch( SQLException e ) {
			throw new DAOException(e);
		}finally {
			fermer(rs,p_st,con);
		}
		
		return LV ;
	}
	public List<Voyage> all_v() throws DAOException{
		Connection con = null;
		PreparedStatement p_st = null ;
		ResultSet rs = null ;
		List<Voyage> LV = new ArrayList<Voyage>();	
		try {
			con = daofactory.getConnection();
			p_st = InitRP( con, SQL_Select_PAYS_p, false );
			rs = p_st.executeQuery();
			while( rs.next() ) {
				LV.add( setAllT(rs) );
			}
		}catch( SQLException e ) {
			throw new DAOException(e);
		}finally {
			fermer(rs,p_st,con);
		}
		
		return LV ;
	}
	public List<Voyage> list_v_p() throws DAOException{
		Connection con = null;
		PreparedStatement p_st = null ;
		ResultSet rs = null ;
		List<Voyage> LV = new ArrayList<Voyage>();	
		try {
			con = daofactory.getConnection();
			p_st = InitRP( con, SQL_Select_PAYS, false );
			rs = p_st.executeQuery();
			while( rs.next() ) {
				LV.add( setAllC(rs) );
			}
		}catch( SQLException e ) {
			throw new DAOException(e);
		}finally {
			fermer(rs,p_st,con);
		}
		
		return LV ;
	}
	public List<Voyage> list_v_t(String type) throws DAOException{
		Connection con = null;
		PreparedStatement p_st = null ;
		ResultSet rs = null ;
		List<Voyage> LV = new ArrayList<Voyage>();	
		try {
			con = daofactory.getConnection();
			p_st = InitRP( con, SQL_Select_TYPE, false,type);
			rs = p_st.executeQuery();
			while( rs.next() ) {
				LV.add( setAllT(rs) );
			}
		}catch( SQLException e ) {
			throw new DAOException(e);
		}finally {
			fermer(rs,p_st,con);
		}
		
		return LV ;
	}
	public List<Voyage> recherche(Voyage vg) throws DAOException{
		Connection con = null;
		PreparedStatement p_st = null ;
		ResultSet rs = null ;
		List<Voyage> LV = new ArrayList<Voyage>();	
		try {
			con = daofactory.getConnection();
			p_st = InitRP( con, SQL_Select_RECHERCHE, false, vg.getContinent(),vg.getDate_v(),vg.getPays(),vg.getVille()
					, vg.getDure_v(),vg.getType_v(),vg.getPrix());
			rs = p_st.executeQuery();
			while( rs.next() ) {
				LV.add( setAll(rs) );
			}
		}catch( SQLException e ) {
			throw new DAOException(e);
		}finally {
			fermer(rs,p_st,con);
		}
		
		return LV ;
	}
//	public Map<String, List<Voyage>[]> list_All() throws DAOException{
//		Connection con = null;
//		PreparedStatement p_st = null ;
//		ResultSet rs = null ;
//		Map<String, List<Voyage>[]> LV = new HashMap<String, List<Voyage>[]>();	
//		try {
//			con = daofactory.getConnection();
//			p_st = InitRP( con, SQL_Select, false );
//			rs = p_st.executeQuery();
//			while( rs.next() ) {
//				LV.put( rs.getString( "continent" )  , list_v( rs.getString( "continent" ), "continent" ));
//			}
//		}catch( SQLException e ) {
//			throw new DAOException(e);
//		}finally {
//			fermer(rs,p_st,con);
//		}
//		
//		return LV ;
//	}
	
	public List<Voyage> list_v( String plvg, String test ) throws DAOException{
		Connection con = null;
		PreparedStatement p_st = null ;
		ResultSet rs = null ;
		List<Voyage> LV = new  ArrayList<Voyage>();	
		try {
			con = daofactory.getConnection();
			if( test == "pays") {
			p_st = InitRP( con, SQL_Select_PAR_PAYS, false, plvg ,plvg );
			rs = p_st.executeQuery();
			
			while( rs.next() ) {
				LV.add( setAll(rs) );
			}
		}
		}catch( SQLException e ) {
			throw new DAOException(e);
		}finally {
			fermer(rs,p_st,con);
		}
		
		return LV ;
	}
	public void create( Voyage vg ) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        PreparedStatement p_st= null;
        
        

        try {
            connection = daofactory.getConnection();

            preparedStatement = InitRP( connection, SQL_INSERT_P, false, vg.getPays(),
            		vg.getVille(), vg.getDate_v(), vg.getTemp_v(),vg.getDure_v(),vg.getType_v(),vg.getPrix(),vg.getDescription(), vg.getLien(),vg.getVille(), vg.getDate_v() );
            p_st = InitRP(connection, SQL_INSERT_V, false, vg.getContinent().toUpperCase(),vg.getPays(),vg.getPays());
            int status = preparedStatement.executeUpdate();
            int st= p_st.executeUpdate();
            if ( status == 0 && st == 0 ) {
                throw new DAOException( "échec de l'ajoute de voyage, aucune ligne ajoutée dans la table." );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
        	fermer(p_st);
            fermer( preparedStatement, connection );
        }
    }
	public void delete( int id ) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = daofactory.getConnection();
            preparedStatement = InitRP( connection, SQL_DELETE_P, false, id );

            int status = preparedStatement.executeUpdate();
            if ( status == 0 ) {
                throw new DAOException( "échec de la suppression du voyage, aucune ligne supprimee de la table." );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
        	fermer( preparedStatement, connection );
        }
    }
	private static Voyage setAll( ResultSet resultat ) throws SQLException {
		Voyage voyage = new Voyage();
		voyage.setId_v( resultat.getInt( "id_v_p" ) );
		voyage.setContinent( resultat.getString( "continent" ) );
		voyage.setPays( resultat.getString( "pays" ) );
		voyage.setVille( resultat.getString( "ville" ) );
		voyage.setDate_v( resultat.getString( "date_v" ) );
		voyage.setTemp_v( resultat.getString( "temp_v" ) );
		voyage.setDure_v( resultat.getString( "dure_v" ) );
		voyage.setType_v( resultat.getString( "type_v" ) );
		voyage.setDescription( resultat.getString( "description" ) );
		voyage.setPrix( resultat.getInt( "prix_v" ) );
		voyage.setLien( resultat.getString( "lien" ) );
		
    	return voyage;
    }
	private static Voyage setAllT( ResultSet resultat ) throws SQLException {
		Voyage voyage = new Voyage();
		voyage.setId_v( resultat.getInt( "id_v_p" ) );
		voyage.setPays( resultat.getString( "pays" ) );
		voyage.setVille( resultat.getString( "ville" ) );
		voyage.setDate_v( resultat.getString( "date_v" ) );
		voyage.setTemp_v( resultat.getString( "temp_v" ) );
		voyage.setDure_v( resultat.getString( "dure_v" ) );
		voyage.setType_v( resultat.getString( "type_v" ) );
		voyage.setDescription( resultat.getString( "description" ) );
		voyage.setPrix( resultat.getInt( "prix_v" ) );
		voyage.setLien( resultat.getString( "lien" ) );
    	return voyage;
    }
	private static Voyage setAllC( ResultSet resultat ) throws SQLException {
		Voyage voyage = new Voyage();
		voyage.setContinent( resultat.getString( "continent" ) );
		voyage.setPays( resultat.getString( "pays_v" ) );
		
    	return voyage;
    }
}
