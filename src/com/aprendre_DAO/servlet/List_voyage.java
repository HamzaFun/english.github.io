package com.aprendre_DAO.servlet;

import java.io.IOException;
import java.util.List;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.aprendre_DAO.c_table.Voyage;
import com.aprendre_DAO.dao.DAOFactory;

import com.aprendre_DAO.dao.VoyageDao;
import com.aprendre_DAO.forms.VoyageForm;

/**
 * Servlet implementation class List_voyage
 */
@WebServlet("/List_voyage")
public class List_voyage extends HttpServlet {
	private static final long serialVersionUID 		= 1L;
	private static final String DAO_FACTORY 		= "daofactory";
	private VoyageDao VGDao ;
	private static final String ATT_VG_TYPE 		= "type_v";
	private static final String ATT_VG 				= "lvgt";
	private static final String SES_ATT_VG 			= "ses_lvgt";
    private static final String VUE_SUC 			= "/pub/destiantion.jsp";
    private static final String ATT_FORM_VGT 		= "form_lvgt";
//    public static final String VUE 				= "/pub/page_principale.jsp";
	
    public List_voyage() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void init() throws ServletException{
    	this.VGDao = ((DAOFactory) getServletContext().getAttribute(DAO_FACTORY) ).getVGDao();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		VoyageForm form_lvg = new VoyageForm( VGDao );
		String type = request.getParameter(ATT_VG_TYPE).toUpperCase();
		List<Voyage> lvgt = form_lvg.list_voyage_t(type);
		request.setAttribute(ATT_FORM_VGT, form_lvg);
		if( form_lvg.getErreurs().isEmpty()) {
			request.removeAttribute(ATT_FORM_VGT);
			request.setAttribute(ATT_VG, lvgt);	
			request.getSession().setAttribute(SES_ATT_VG, lvgt);
		}
		if(type != null) {
			System.out.println("lkghiug");
			request.setAttribute(ATT_VG_TYPE, type);
			this.getServletContext().getRequestDispatcher( "/pub/type_f.jsp" ).forward(request, response);

		}
		this.getServletContext().getRequestDispatcher( VUE_SUC ).forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		VoyageForm form_lvg = new VoyageForm( VGDao );
//		HttpSession ses = request.getSession();
//		Map<String, List<Voyage>> lvg = form_lvg.list_voyage();
//		request.setAttribute(ATT_FORM_VG, form_lvg);
//		request.setAttribute(ATT_VG, lvg);
//		
//		if( form_lvg.getErreurs().isEmpty()) {
//			ses.setAttribute( ATT_SES_VG, lvg );
//		}
//		
		this.getServletContext().getRequestDispatcher( VUE_SUC ).forward(request, response);
	}

}
