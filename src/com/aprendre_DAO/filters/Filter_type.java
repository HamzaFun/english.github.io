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

import com.aprendre_DAO.c_table.Voyage;
import com.aprendre_DAO.dao.DAOFactory;
import com.aprendre_DAO.dao.VoyageDao;
import com.aprendre_DAO.forms.VoyageForm;

/**
 * Servlet Filter implementation class Filter_type
 */
@WebFilter( urlPatterns = {"/pub/type_f.jsp","/Add_demande_ses"})
public class Filter_type implements Filter {
	private static final String DAO_FACTORY 	= "daofactory";
	private VoyageDao VGDao ;
	private static final String ATT_TYP 		= "type";
	private static final String ATT_VG 			= "lvgt";
	private static final String ATT_FORM_VGT 	= "form_lvgt";
    public Filter_type() {
        // TODO Auto-generated constructor stub
    }

	
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		VoyageForm form_lvg = new VoyageForm( VGDao );
		HttpServletRequest req = (HttpServletRequest) request;
		String type = (String ) req.getParameter(ATT_TYP);
		
		if(type != null) {
			List<Voyage> lvgt = form_lvg.list_voyage_t(type.toUpperCase());
			req.setAttribute(ATT_FORM_VGT, form_lvg);
			if( form_lvg.getErreurs().isEmpty()) {
				req.removeAttribute(ATT_FORM_VGT);
				req.setAttribute(ATT_VG, lvgt);	
			}
		}
		chain.doFilter(req, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		this.VGDao = ((DAOFactory) fConfig.getServletContext().getAttribute(DAO_FACTORY) ).getVGDao();
	}

}
