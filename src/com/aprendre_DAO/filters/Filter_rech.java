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
import com.aprendre_DAO.forms.RecherchForm;

/**
 * Servlet Filter implementation class Filter_rech
 */
@WebFilter( urlPatterns = {"/pub/rech_f.jsp","/Add_demande_ses"})
public class Filter_rech implements Filter {

	private static final String DAO_FACTORY 		= "daofactory";
	private VoyageDao VGDao ;
	private static final String ATT_RECH 			= "rech";
	private static final String SES_ATT_RECH 		= "ses_rech";
	private static final String ATT_FORM_VGT 		= "form_rech";
	
    public Filter_rech() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		RecherchForm form_rech = new RecherchForm( VGDao );
		HttpServletRequest req = (HttpServletRequest) request;
		List<Voyage> rech = form_rech.recherch(req);
		System.out.println("hellow recherche ::: "+ rech.size());
		req.setAttribute(ATT_FORM_VGT, form_rech);
		if( form_rech.getErreurs().isEmpty()) {
			req.setAttribute(ATT_RECH, rech);
			if(rech.size() != 0) {
			HttpSession ses = req.getSession();
			ses.setAttribute(SES_ATT_RECH, rech);
			}
		}
		
		chain.doFilter(req, response);
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
		this.VGDao = ((DAOFactory) fConfig.getServletContext().getAttribute(DAO_FACTORY) ).getVGDao();
	}

}
