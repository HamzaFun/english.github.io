package com.aprendre_DAO.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aprendre_DAO.c_table.Voyage;
import com.aprendre_DAO.dao.DAOFactory;
import com.aprendre_DAO.dao.VoyageDao;
import com.aprendre_DAO.forms.RecherchForm;

/**
 * Servlet implementation class recherche
 */
@WebServlet("/recherche")
public class Recherche extends HttpServlet {
	private static final long serialVersionUID 		= 1L;
       
	private static final String DAO_FACTORY 		= "daofactory";
	private VoyageDao VGDao ;
	private static final String ATT_RECH 			= "rech";
	private static final String SES_ATT_RECH 		= "sesrech";
	private static final String SES_ATT_RE 			= "re";
    private static final String VUE_SUC 			= "/pub/destination.jsp";
    private static final String ATT_FORM_VGT 		= "form_rech";
    public static final String VUE 					= "/pub/page_principale.jsp";
    public Recherche() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void init() throws ServletException{
    	this.VGDao = ((DAOFactory) getServletContext().getAttribute(DAO_FACTORY) ).getVGDao();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RecherchForm form_rech = new RecherchForm( VGDao );
		List<Voyage> rech = form_rech.recherch(request);
		HttpSession ses = request.getSession();
		if( form_rech.getErreurs().isEmpty()) {
			System.out.println("hellow recherche ::: "+ rech.size());
		request.setAttribute(ATT_FORM_VGT, form_rech);
		request.setAttribute(ATT_RECH, rech);
		ses.setAttribute(SES_ATT_RECH, rech);
		int re = 1;
		ses.setAttribute(SES_ATT_RE, re);
		}
		this.getServletContext().getRequestDispatcher( VUE_SUC ).forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
