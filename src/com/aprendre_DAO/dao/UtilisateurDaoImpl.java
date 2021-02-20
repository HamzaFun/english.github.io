package com.aprendre_DAO.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aprendre_DAO.c_table.Utilisateur;
import com.aprendre_DAO.exception.DAOException;

import static com.aprendre_DAO.dao.DAO_tools.InitRP;
import static com.aprendre_DAO.dao.DAO_tools.fermer;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class UtilisateurDaoImpl implements UtilisateurDao {
	 	private static final String SQL_Select_Par_Email 	= "SELECT email, nom, prenom, mdp, pays, age, tel,`Admin` FROM user WHERE email = ? ";
		private DAOFactory daofactory;
		private static final String SQL_Insert 				= "INSERT INTO agence.`user`(`email`, `nom`, `prenom`, `mdp`, `pays`, `age`, `tel` , `ddc`) VALUES (?,?,?,?,?,?,?, NOW() )";
		private static final String SQL_Select_USERS 		= "SELECT email, nom, prenom, mdp, pays, age, tel, ddc FROM user ORDER BY ddc";
		private static final String SQL_Delete_Email 		= "DELETE FROM user WHERE email = ?";
	 	UtilisateurDaoImpl( DAOFactory daofactory ) {
	        this.daofactory = daofactory;
	    }
	 	
	    @Override
	    public Utilisateur find( String email ) throws DAOException {
	        Connection con = null;
	        PreparedStatement p_st = null;
	        ResultSet rs = null;
	        Utilisateur user = null;
	        try {
	        	con =  daofactory.getConnection();
	        	p_st = InitRP(con, SQL_Select_Par_Email, false, email);
	        	rs = p_st.executeQuery();
	        	if( rs.next() ) {
	        		user = setAll(rs);
	        	}
	        }catch(SQLException e) {
	        	throw new DAOException(e);
	        }finally {
	        	fermer(rs, p_st, con);
	        }
	        return user;
	    }
	    
	    @Override
	    public void create( Utilisateur user ) throws DAOException {
	    	Connection con = null;
	    	PreparedStatement p_st = null;

	    	try {
	    		con =  daofactory.getConnection();
	    		System.out.println("la connection : "+con);
				p_st = InitRP(con, SQL_Insert, false, user.getEmail(), user.getNom(), user.getPrenom(), 
	    				user.getMdp(), user.getPays(), user.getAge(),user.getTel());
	    		int statut = p_st.executeUpdate();
	    		if(statut == 0) {
	    			throw new DAOException("échec de la création d'utilisateure *^*");
	    		}
	    	}catch(SQLException e) {
	    		throw new DAOException( e ) ;
	    	}finally {
	    		fermer(p_st, con);
	    	}
	    }
	    
	    public void delete( String email ) throws DAOException {
	        Connection con = null;
	        PreparedStatement p_st = null;
	        

	        try {
	            con = daofactory.getConnection();
	            p_st = InitRP( con, SQL_Delete_Email, false, email );

	            int status = p_st.executeUpdate();
	            if ( status == 0 ) {
	                throw new DAOException( "échec de la suppression , aucune ligne a supprimer" );
	            }
	        } catch ( SQLException e ) {
	            throw new DAOException( e );
	        } finally {
	            fermer( p_st, con);
	        }
	    }
	    
	    public List<Utilisateur> list(){
	    	Connection con = null;
	    	PreparedStatement p_st = null;
	    	ResultSet rs = null;
	    	List<Utilisateur> users = new ArrayList<Utilisateur>();
	    	
	    	try {
	    		con = daofactory.getConnection();
	    		p_st = InitRP(con, SQL_Select_USERS, false );
	    		
	    		rs = p_st.executeQuery();
	    		while( rs.next() ) {
	    			users.add( setAll( rs ));
	    		}
	    	}catch(SQLException e) {
	    		throw new DAOException(e);
	    	}finally {
	    		fermer(rs,p_st,con);
	    	}
	    	return users;
	    }
	    
	    private static Utilisateur setAll( ResultSet resultat ) throws SQLException {
	    	Utilisateur utilisateur = new Utilisateur();
	    	utilisateur.setEmail( resultat.getString( "email" ) );
	    	utilisateur.setMdp( resultat.getString( "mdp" ) );
	    	utilisateur.setNom( resultat.getString( "nom" ) );
	    	utilisateur.setPrenom( resultat.getString( "prenom" ) );
	    	utilisateur.setAge( resultat.getInt( "age") );
	    	utilisateur.setAdmin( resultat.getBoolean( "Admin") );
	    	return utilisateur;
	    }
	    

	}





