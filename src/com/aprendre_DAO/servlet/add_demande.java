package com.aprendre_DAO.servlet;

import java.io.IOException;
import java.util.HashMap;
//import java.util.List;
import java.util.Map;
//import java.util.Map.Entry;

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

@WebServlet("/add_demande")
public class add_demande extends HttpServlet {
	private static final long serialVersionUID 		= 1L;
    private static final String DAO_FACTORY 		= "daofactory";
    public static final String ATT_SES_USER 		= "sesuser";
//    private static final String ATT_DM 			= "dm";
    public static final String ATT_SES_MAPDML 		= "sesmapdml";
    private static final String ATT_DML_DB 			= "MapDml_DB";
//    private static final String ATT_SES_DM 		= "sesdm";
    private static final String ATT_FORM_DM 		= "form_add_dm";
    private static final String VUE_SUC 			= "/WEB-INF/user/Profile.jsp";
    public static final String VUE 					= "/WEB-INF/user/Profile.jsp";
    private DemandeDao DMDao ;
    
    public add_demande() {
        super();
        
    }
    
    public void init() throws ServletException{
    	this.DMDao = ((DAOFactory) getServletContext().getAttribute(DAO_FACTORY) ).getDMDao();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher( VUE ).forward(request, response);
	}

	
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession ses = request.getSession();
		if(ses.getAttribute(ATT_SES_USER) != null && ses.getAttribute(ATT_SES_MAPDML) != null ) {
		demandeForm form_dm = new demandeForm( DMDao );
		Map <Integer,Demande> mapdml = (Map<Integer,Demande>) ses.getAttribute(ATT_SES_MAPDML);
		form_dm.ADD_DM( mapdml );
		request.setAttribute(ATT_FORM_DM, form_dm);
		
		if( form_dm.getErreurs().isEmpty()) {
			Map<Integer, Demande> mapDM_DB = (Map<Integer, Demande>) ses.getAttribute(ATT_DML_DB);
//			for( Entry<Integer, Demande> dm : mapdml.entrySet() ) {
//				if(dm.getKey() != null && dm.getValue() != null ) {
//				mapDM_DB.put(dm.getKey(), dm.getValue());
////				mapDM_DB.
//				}
//			}
			Map<Integer, Demande> map = new HashMap<Integer, Demande>();
			map.putAll(mapdml);
			mapDM_DB = map;
			mapdml.clear();
			ses.setAttribute(ATT_SES_MAPDML,mapdml);
			ses.setAttribute(ATT_DML_DB,mapDM_DB);
//			request.removeAttribute(ATT_FORM_DM);
		}
		}
		this.getServletContext().getRequestDispatcher( VUE_SUC ).forward(request, response);
	}

}
