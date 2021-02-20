package com.aprendre_DAO.dao;

import java.util.List;

import com.aprendre_DAO.c_table.Voyage;
import com.aprendre_DAO.exception.DAOException;

public interface VoyageDao {
	

	void create( Voyage vg ) throws DAOException;
	void delete( int id ) throws DAOException;
	public List<Voyage> list_v() throws DAOException ;
	public List<Voyage> all_v() throws DAOException ;
	public List<Voyage> recherche(Voyage vg) throws DAOException ;
	public List<Voyage> list_v_p() throws DAOException ;
	public List<Voyage> list_v_t(String type) throws DAOException ;
	public List<Voyage> list_v(String plvg, String test) throws DAOException ;
//	public Map<String, List<Voyage>[]> list_All() throws DAOException ;
	
}
