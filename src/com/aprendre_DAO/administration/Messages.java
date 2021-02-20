package com.aprendre_DAO.administration;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aprendre_DAO.dao.DAOFactory;
import com.aprendre_DAO.dao.MessageDao;
import com.aprendre_DAO.forms.MessageForm;

/**
 * Servlet implementation class Messages
 */
@WebServlet("/Messages")
public class Messages extends HttpServlet {
	
	private static final long serialVersionUID 	= 1L;
//	private static final String ATT_MESL 		= "mesl"; 
//	private static final String ATT_FORM_MES 	= "form_mes";
	private static final String VUE_SUC 		= "/WEB-INF/user/Profile.jsp";
	public static final String ATT_SES_USER 	= "sesuser";
	private static final String ATT_ID_MES 		= "id_mes";
	private MessageDao MESDao ;
	private static final String DAO_FACTORY 	= "daofactory";
	public void init() throws ServletException{
    	this.MESDao = ((DAOFactory) getServletContext().getAttribute(DAO_FACTORY) ).getMessageDao();
    }
	
    public Messages() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		  MessageForm form_mes = new MessageForm(MESDao); 
		  
		  if( request.getParameter(ATT_ID_MES) != null ) { 
			  form_mes.supprimerMessage( request.getParameter(ATT_ID_MES) ); 
		  }
		  
		  request.getServletContext().getRequestDispatcher(VUE_SUC).forward(request,
		  response);
		 
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
