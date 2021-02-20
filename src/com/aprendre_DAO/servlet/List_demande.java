package com.aprendre_DAO.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aprendre_DAO.c_table.Demande;
import com.aprendre_DAO.dao.DAOFactory;
import com.aprendre_DAO.dao.DemandeDao;
import com.aprendre_DAO.forms.demandeForm;


@WebServlet("/List_demande")
public class List_demande extends HttpServlet {
	private static final long serialVersionUID 		= 1L;
    private static final String DAO_FACTORY 		= "daofactory";
    private static final String ATT_DM 				= "dml";
    private static final String ATT_SES_DM 			= "sesdml_db";
    private static final String ATT_FORM_DM 		= "form_dml";
    private static final String VUE_SUC 			= "/WEB-INF/user/Profile.jsp";
    public static final String VUE 					= "/WEB-INF/user/Profile.jsp";
    private DemandeDao DMDao ;
    
    public List_demande() {
        super();
        
    }
    
    public void init() throws ServletException{
    	this.DMDao = ((DAOFactory) getServletContext().getAttribute(DAO_FACTORY) ).getDMDao();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		demandeForm form_dml = new demandeForm( DMDao );
		HttpSession ses = request.getSession();
		Map<Integer,Demande> dml = form_dml.find_demande( ses );
		request.setAttribute(ATT_FORM_DM, form_dml);
		request.setAttribute(ATT_DM, dml);
		
		if( form_dml.getErreurs().isEmpty()) {
			request.removeAttribute(ATT_FORM_DM);
			request.removeAttribute(ATT_DM);
			ses.setAttribute( ATT_SES_DM, dml );
		}
		
		this.getServletContext().getRequestDispatcher( VUE_SUC ).forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println("DMDao :"+ DMDao);
//		demandeForm form_dml = new demandeForm( DMDao );
//		HttpSession ses = request.getSession();
//		Map<Integer, Demande> dml = form_dml.find_demande( ses );
//		request.setAttribute(ATT_FORM_DM, form_dml);
//		request.setAttribute(ATT_DM, dml);
//		
//		if( form_dml.getErreurs().isEmpty()) {
//			ses.setAttribute( ATT_SES_DM, dml );
//		}
//		
//		this.getServletContext().getRequestDispatcher( VUE_SUC ).forward(request, response);
	}

}