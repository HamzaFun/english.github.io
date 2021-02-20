package com.aprendre_DAO.filters;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.aprendre_DAO.c_table.Demande;
import com.aprendre_DAO.dao.DAOFactory;
import com.aprendre_DAO.dao.DemandeDao;
import com.aprendre_DAO.forms.demandeForm;


@WebFilter( urlPatterns= "/*" )
public class Filter_demande implements Filter {
	private static final String DAO_FACTORY 		= "daofactory";
    private static final String ATT_MAPDML 			= "mapdml";
    private static final String ATT_SES_DM 			= "sesmapdml_db";
    private static final String ATT_FORM_DM 		= "form_dml";
    public static final String ATT_SES_USER 		= "sesuser";
    private DemandeDao DMDao ;
    public Filter_demande() {
        // TODO Auto-generated constructor stub
    }

	
	public void destroy() {
		// TODO Auto-generated method stub
	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        
        HttpServletRequest req = (HttpServletRequest) request;
		demandeForm form_dml = new demandeForm( DMDao );
		HttpSession ses =   req.getSession();
		if( ses.getAttribute(ATT_SES_USER) != null) {
		Map<Integer,Demande> dml = form_dml.find_demande( ses );
		req.setAttribute(ATT_FORM_DM, form_dml);
		if( form_dml.getErreurs().isEmpty()) {
			req.setAttribute(ATT_MAPDML, dml);
			System.out.println("map demande :::: "+ dml.size());
			ses.setAttribute( ATT_SES_DM, dml );
		}	
		}
		chain.doFilter(request, response);
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
		this.DMDao = ((DAOFactory) fConfig.getServletContext().getAttribute(DAO_FACTORY) ).getDMDao();
	}

}
