package com.aprendre_DAO.filters;

import java.io.IOException;
import java.util.List;


import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.aprendre_DAO.c_table.Voyage;
import com.aprendre_DAO.dao.DAOFactory;
import com.aprendre_DAO.dao.VoyageDao;
import com.aprendre_DAO.forms.VoyageForm;

@WebFilter( urlPatterns= "/*")
public class Filter_voyage implements Filter {

	private static final String DAO_FACTORY 	= "daofactory";
	private VoyageDao VGDao ;
	private static final String ATT_LVG 		= "lvg";
	private static final String ATT_ALL_V 		= "all_v";
	private static final String ATT_LVGP 		= "lvgp";
	private static final String ATT_PLVG 		= "plvg";
	private static final String SES_ATT_LVG 	= "ses_lvg";
	private static final String SES_ATT_ALL_V 	= "ses_all_v";
	private static final String SES_ATT_LVGP 	= "ses_lvgp";
    private static final String ATT_FORM_VG 	= "form_lvg";
    private static final String ATT_PAYS		= "v_pays";

    public Filter_voyage() {
        // TODO Auto-generated constructor stub
    }

	public void destroy() {
		// TODO Auto-generated method stub
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		VoyageForm form_lvg = new VoyageForm( VGDao );
		HttpServletRequest req = (HttpServletRequest) request;
		List<Voyage> lvg = form_lvg.list_voyage();
		List<Voyage> lvgp = form_lvg.list_voyage_p();
		List<Voyage> all_v = form_lvg.All_voyage();
		HttpSession ses = req.getSession();
		ses.setAttribute(SES_ATT_LVG, lvg);
		ses.setAttribute(SES_ATT_LVGP, lvgp);
		ses.setAttribute(SES_ATT_ALL_V, all_v);
		req.setAttribute(ATT_LVG, lvg);
		req.setAttribute(ATT_LVGP, lvgp);
		req.setAttribute(ATT_ALL_V, all_v);
		
		if( req.getParameter(ATT_PAYS) != null ) {
			List<Voyage> pays_lvg = form_lvg.list_voyage( req.getParameter(ATT_PAYS).toUpperCase(),"pays" );
			req.setAttribute(ATT_PAYS, req.getAttribute(ATT_PAYS));
			req.setAttribute(ATT_PLVG, pays_lvg);				
		}
		req.setAttribute(ATT_FORM_VG, form_lvg);
		chain.doFilter(req, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		this.VGDao = ((DAOFactory) fConfig.getServletContext().getAttribute(DAO_FACTORY) ).getVGDao();
	}

}
