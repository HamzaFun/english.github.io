package com.aprendre_DAO.dao;

import java.sql.*;

import java.util.HashMap;

import java.util.Map;

import com.aprendre_DAO.c_table.Demande;
import com.aprendre_DAO.exception.DAOException;

import static com.aprendre_DAO.dao.DAO_tools.InitRP;
import static com.aprendre_DAO.dao.DAO_tools.fermer;

public class DemandeDaoImpl implements DemandeDao{
	private static final String SQL_Insert_DM 		= "INSERT INTO `t_demande` (`email`,`id_dm_v`) VALUES(?,?) ";
	private static final String SQL_Delete_DM 		= "DELETE FROM t_demande WHERE id_dm_v = ?";;
	private static final String SQL_Select_DMS 		= "SELECT td.email, v.continent, pv.pays, pv.ville, pv.date_v, pv.temp_v, pv.dure_v, pv.type_v, pv.prix_v,pv.id_v_p FROM t_demande as td ,voyage AS v,pays_v AS pv WHERE EXISTS ( SELECT * FROM pays_v WHERE td.id_dm_v LIKE pv.id_v_p ) AND pv.pays LIKE v.pays_v AND email LIKE ?  ORDER BY date_v";
	private static final String SQL_Select 			= "SELECT email, v.continent, pv.pays, pv.ville, pv.date_v, pv.temp_v, pv.dure_v, pv.type_v, pv.prix_v,pv.id_v_p FROM t_demande as td ,voyage AS v,pays_v AS pv WHERE EXISTS ( SELECT * FROM pays_v WHERE td.id_dm_v LIKE pv.id_v_p ) AND pays LIKE pays_v GROUP BY pv.ville ORDER BY date_v";
	private DAOFactory daofactory;
	
	 DemandeDaoImpl( DAOFactory daofactory ) {
        this.daofactory = daofactory;
    }
	 
	public void Ajouter_demande(Map<Integer,Demande> DML) throws DAOException {
		Connection con =null;
		PreparedStatement p_st = null;
		
		try {
			con = daofactory.getConnection();
			
			for (Map.Entry<Integer,Demande> me : DML.entrySet()){
			p_st = InitRP(con, SQL_Insert_DM, false,  me.getValue().getEmail(), me.getValue().getId_v());
			int statut = p_st.executeUpdate();
			if(statut == 0) {
				throw new DAOException("échec de l'ajoute de cette demande");
			}
			}

		}catch(SQLException e) {
				throw new DAOException(e);
			}finally {
				fermer(p_st, con);
			}
		}
	public void delete_demande(int id_dm) throws DAOException{
		Connection con =null;
		PreparedStatement p_st = null;
		
		try {
			con = daofactory.getConnection();
			p_st = InitRP(con, SQL_Delete_DM, false, id_dm);
			int statut = p_st.executeUpdate();
			if(statut == 0) {
				throw new DAOException("échec de la suppression de cette demande");
			}
		}catch(SQLException e) {
				throw new DAOException(e);
			}finally {
				fermer(p_st, con);
			}
		}
	
	public Map<Integer,Demande> list_demande(String email) throws DAOException{
		Connection con = null;
		PreparedStatement p_st = null;
		ResultSet rs = null;
		Map<Integer,Demande> DM = new HashMap<Integer,Demande>();
		
		try {
			con = daofactory.getConnection();
			p_st = InitRP(con, SQL_Select_DMS, false, email );
			rs = p_st.executeQuery();
			while( rs.next() ) {
				DM.put( rs.getInt("id_v_p") , setAll_DM(rs) );
			}
		}catch(SQLException e) {
			throw new DAOException(e);
		}finally {
			fermer(rs,p_st,con);
		}
		
		
		return DM;
	}
	public Map<Integer,Demande> list_demande() throws DAOException{
		Connection con = null;
		PreparedStatement p_st = null;
		ResultSet rs = null;
		Map<Integer,Demande> DM = new HashMap<Integer,Demande>();
		
		try {
			con = daofactory.getConnection();
			p_st = InitRP(con, SQL_Select, false );
			rs = p_st.executeQuery();
			while( rs.next() ) {
				DM.put( rs.getInt("id_v_p") , setAll_DM(rs) );
			}
		}catch(SQLException e) {
			throw new DAOException(e);
		}finally {
			fermer(rs,p_st,con);
		}
		
		
		return DM;
	}
	
	private static Demande setAll_DM( ResultSet resultat ) throws SQLException {
    	Demande DM = new Demande();
    	DM.setEmail( resultat.getString( "email" ) );
    	DM.setId_v( resultat.getInt( "id_v_p" ) );
    	DM.setContinent( resultat.getString( "continent" ) );
    	DM.setPays( resultat.getString( "pays" ) );
    	DM.setVille( resultat.getString( "ville" ) );
    	DM.setDate_v( resultat.getString( "date_v" ) );
    	DM.setTemp_v( resultat.getString( "temp_v") );
    	DM.setDure_v( resultat.getString( "dure_v") );
    	DM.setType_v( resultat.getString( "type_v") );
    	DM.setPrix( resultat.getString( "prix_v" ) );
    	return DM;
    }

	

}
