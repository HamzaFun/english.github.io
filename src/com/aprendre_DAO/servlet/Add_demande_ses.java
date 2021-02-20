package com.aprendre_DAO.servlet;

import java.io.IOException;
import java.util.HashMap;
//import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aprendre_DAO.c_table.Demande;
//import com.aprendre_DAO.c_table.Voyage;

/**
 * Servlet implementation class Add_demande_ses
 */
@WebServlet("/Add_demande_ses")
public class Add_demande_ses extends HttpServlet {
	
	private static final long serialVersionUID 		= 1L;
	public static final String ATT_SES_USER 		= "sesuser";
	public static final String ATT_SES_DML 			= "sesdml";
	public static final String ATT_SES_MAPDML 		= "sesmapdml";
	public static final String  CHAMP_EMAIL 		= "email";
	public static final String  CHAMP_CONTINENT 	= "continent";
	public static final String  CHAMP_PAYS 			= "pays";
	public static final String  CHAMP_VILLE 		= "ville";
	public static final String  CHAMP_DATE_V		= "date_v";
	public static final String  CHAMP_TEMP_V 		= "temp_v";
	public static final String  CHAMP_DURE_V 		= "dure_v";
	public static final String  CHAMP_TYPE_V 		= "type_v";
	public static final String  CHAMP_PRIX 			= "prix";
	public static final String  CHAMP_ID 			= "id_v";
//	private static final String ATT_VG 				= "lvgt";
	private static final String VUE_SUC 			= "/pub/destination.jsp";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Add_demande_ses() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		this.getServletContext().getRequestDispatcher( VUE_SUC ).forward(request, response);
	}

	
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession ses = request.getSession();
		if( ses.getAttribute(ATT_SES_USER) != null) {
			Map<Integer, Demande > Map_dml = new HashMap<Integer, Demande>();
			if( ses.getAttribute( ATT_SES_MAPDML ) != null) {
				Map_dml = (Map<Integer, Demande>) ses.getAttribute(ATT_SES_MAPDML);
			}
			if( request.getParameter( CHAMP_ID ) != null) {
				Map_dml.put( Integer.parseInt( request.getParameter( CHAMP_ID ) ), AjouterDm( request  ) );
				ses.setAttribute(ATT_SES_MAPDML, Map_dml);
			}
			
			if( request.getParameter("type") != null) {
				request.setAttribute("type", request.getParameter("type"));
				this.getServletContext().getRequestDispatcher( "/pub/type_f.jsp" ).forward(request, response);
			}else {
				
				if( request.getParameter("recherch") != null) {	
					
					this.getServletContext().getRequestDispatcher( "/pub/rech_f.jsp" ).forward(request, response);
				}else {
					this.getServletContext().getRequestDispatcher( VUE_SUC ).forward(request, response);
					}
			}
		}
	}
	
	private static Demande AjouterDm( HttpServletRequest request ) {
        Demande dm = new Demande();
        dm.setEmail( request.getParameter( CHAMP_EMAIL ));
        dm.setContinent( request.getParameter( CHAMP_CONTINENT ));
        dm.setPays( request.getParameter( CHAMP_PAYS ));
        dm.setVille( request.getParameter( CHAMP_VILLE ));
        dm.setDate_v( request.getParameter( CHAMP_DATE_V ));
        dm.setTemp_v( request.getParameter( CHAMP_TEMP_V ));
        dm.setDure_v( request.getParameter( CHAMP_DURE_V ));
        dm.setType_v( request.getParameter( CHAMP_TYPE_V ));
        dm.setPrix( request.getParameter( CHAMP_PRIX ));
        dm.setId_v( Integer.parseInt( request.getParameter( CHAMP_ID )));
           
        return dm;
    }
	
	


}
