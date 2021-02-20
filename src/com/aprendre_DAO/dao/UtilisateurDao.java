package com.aprendre_DAO.dao;

import java.util.List;

import com.aprendre_DAO.c_table.Utilisateur;
import com.aprendre_DAO.exception.DAOException;

	public interface UtilisateurDao {

	    void create( Utilisateur utilisateur ) throws DAOException;

	    Utilisateur find( String email ) throws DAOException;
	    
	    void delete(String email) throws DAOException ;
	    
	    List<Utilisateur> list() throws DAOException ;
	    
	}

