package com.aprendre_DAO.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.aprendre_DAO.dao.DAOFactory;

public class InitDaoFactory implements ServletContextListener {
	private static final String DAO_FACTORY 	= "daofactory";
	private DAOFactory daofactory ;
	@Override
    public void contextInitialized( ServletContextEvent event ) {
        
        ServletContext servletContext = event.getServletContext();
        this.daofactory = DAOFactory.getInstance();
        servletContext.setAttribute( DAO_FACTORY, this.daofactory );
    }

    @Override
    public void contextDestroyed( ServletContextEvent event ) {
    }
}
