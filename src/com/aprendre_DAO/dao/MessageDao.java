package com.aprendre_DAO.dao;

import java.util.List;

import com.aprendre_DAO.c_table.Message;
import com.aprendre_DAO.exception.DAOException;


public interface MessageDao {
    void create( Message message ) throws DAOException;

    List<Message> list( UtilisateurDao utilisateurDao ) throws DAOException;

    void delete( Long id ) throws DAOException;

    void update( Message message, boolean vu ) throws DAOException;
}
