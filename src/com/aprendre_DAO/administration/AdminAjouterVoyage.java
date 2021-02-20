package com.aprendre_DAO.administration;

import java.io.IOException;

import com.aprendre_DAO.c_table.Voyage;
import com.aprendre_DAO.dao.DAOFactory;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aprendre_DAO.dao.VoyageDao;
import com.aprendre_DAO.forms.VoyageForm;

@WebServlet( "/adminAjouterVg" )
public class AdminAjouterVoyage extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID 		= 1L;

	private static final String CONF_DAO_FACTORY  	= "daofactory";

    private static final String ATT_VG         		= "vg";
    private static final String ATT_FORM_VG         = "form_vg";

    private static final String ATT_SESSION_VG 		= "mapVg";

    private static final String VUE               	= "/WEB-INF/user/Profile.jsp";
    private static final String VUE_SUCCES        	= "/WEB-INF/user/Profile.jsp";

    private VoyageDao            VGDao;

    public void init() throws ServletException {
        this.VGDao = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getVGDao();
    }

    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }

    @SuppressWarnings("unchecked")
	protected void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        VoyageForm form = new VoyageForm( VGDao );
        Voyage vg = form.ajouterVoyage( request );

        request.setAttribute( ATT_VG, vg );
        request.setAttribute( ATT_FORM_VG, form );

        if ( form.getErreurs().isEmpty() ) {
            HttpSession session = request.getSession();
            Map<Long, Voyage> mapVg = (HashMap<Long, Voyage>) session.getAttribute( ATT_SESSION_VG );

            if ( mapVg == null ) {
            	mapVg = new HashMap<Long, Voyage>();
            }
            mapVg.put( (long) vg.getId_v(), vg );

            session.setAttribute( ATT_SESSION_VG, mapVg );
            request.removeAttribute( ATT_VG );

            this.getServletContext().getRequestDispatcher( VUE_SUCCES ).forward( request, response );
            return;
        }

        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }
}