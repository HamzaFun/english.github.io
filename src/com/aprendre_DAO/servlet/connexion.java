package com.aprendre_DAO.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aprendre_DAO.c_table.Utilisateur;
import com.aprendre_DAO.dao.DAOFactory;
import com.aprendre_DAO.dao.UtilisateurDao;
import com.aprendre_DAO.forms.ConnexionForm;


@WebServlet("/connexion")
public class connexion extends HttpServlet {
	private static final long serialVersionUID 		= 1L;
	public static final String DAO_FACTORY 			= "daofactory";   
	public static final String VUE 					= "/pub/login.jsp";
	public static final String VUE_SUCCES 			= "/pub/page_principale.jsp" ;
	public static final String ATT_FORM 			= "form";
	public static final String ATT_SES_USER 		= "sesuser";
	private UtilisateurDao userDao ;
    public connexion() {
        super();
    }
    public void init() throws ServletException{
    	this.userDao = ((DAOFactory) getServletContext().getAttribute(DAO_FACTORY) ).getUtilisateurDao();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.getServletContext().getRequestDispatcher( VUE ).forward(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ConnexionForm form_con = new ConnexionForm( userDao ); 
		Utilisateur user = form_con.Connecteruser( request );
		
		HttpSession ses = request.getSession();
		
		request.setAttribute(ATT_SES_USER, user);
		request.setAttribute(ATT_FORM, form_con);
		
		if ( form_con.getErreurs().isEmpty() ) {
			request.removeAttribute(ATT_SES_USER);
			request.removeAttribute(ATT_FORM);
            ses.setAttribute( ATT_SES_USER, user );
            this.getServletContext().getRequestDispatcher( VUE_SUCCES ).forward( request, response );
        } else {
            ses.setAttribute( ATT_SES_USER, null );
            this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
        }
		
	}
	
	
	
	

}
