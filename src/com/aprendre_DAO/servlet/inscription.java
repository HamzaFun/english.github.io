package com.aprendre_DAO.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aprendre_DAO.c_table.Utilisateur;
import com.aprendre_DAO.dao.DAOFactory;
import com.aprendre_DAO.dao.UtilisateurDao;
import com.aprendre_DAO.forms.InscriptionForm;

/**
 * Servlet implementation class inscription
 */
@WebServlet("/inscription")
public class inscription extends HttpServlet {
	private static final long serialVersionUID 		= 1L;
	public static final String DAO_FACTORY 			= "daofactory";
	public static final String ATT_USER 			= "user";
	public static final String ATT_FORM 			= "form";
	public static final String VUE 					= "/pub/inscription.jsp";
	public static final String VUE_SUC 				= "/pub/login.jsp";
	private UtilisateurDao userDao ;
	
    public inscription() {
        super();
        
    }
    public void init() throws ServletException{
    	this.userDao = ((DAOFactory) getServletContext().getAttribute(DAO_FACTORY) ).getUtilisateurDao();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher( VUE ).forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		InscriptionForm form = new InscriptionForm( userDao );
		Utilisateur user = form.inscrireUser( request );
		System.out.println("fom erreurs : " + form.erreurs.get("nom"));
		request.setAttribute( ATT_USER, user);
		request.setAttribute(ATT_FORM, form );
		
		if( form.getErreurs().isEmpty()) {
			request.removeAttribute(ATT_FORM);
			this.getServletContext().getRequestDispatcher( VUE_SUC ).forward( request, response);
		}else {
			this.getServletContext().getRequestDispatcher( VUE ).forward( request, response);		
	}
	}

}
