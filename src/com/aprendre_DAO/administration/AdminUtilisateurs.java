package com.aprendre_DAO.administration;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet( "/adminuser" )
public class AdminUtilisateurs extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID 		= 1L;
	public static final String VUE 					= "/WEB-INF/user/Profile.jsp";

    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }

}
