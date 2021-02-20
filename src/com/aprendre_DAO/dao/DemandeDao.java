package com.aprendre_DAO.dao;

import java.util.Map;

import com.aprendre_DAO.c_table.Demande;
import com.aprendre_DAO.exception.DAOException;

public interface DemandeDao {
	
	void Ajouter_demande(Map<Integer,Demande> dml ) throws DAOException ;
	
	void delete_demande(int id_dm) throws DAOException ;
	
	Map<Integer,Demande> list_demande(String email) throws DAOException ;
	
	Map<Integer,Demande> list_demande() throws DAOException ;
	
}
