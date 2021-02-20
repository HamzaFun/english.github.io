package com.aprendre_DAO.filters;

import java.io.IOException;
//import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import com.aprendre_DAO.c_table.Message;
import com.aprendre_DAO.c_table.Utilisateur;
import com.aprendre_DAO.dao.DAOFactory;
import com.aprendre_DAO.dao.DemandeDao;
import com.aprendre_DAO.dao.MessageDao;
//import com.aprendre_DAO.forms.MessageForm;
import com.aprendre_DAO.dao.UtilisateurDao;


@WebFilter("/*")
public class Filter_message implements Filter {
	 public static final String ATT_SES_USER 				= "sesuser";
//	private static final String ATT_MESL 					= "mesl"; 
//	private static final String ATT_FORM_MES 				= "form_mes";
//	private static final String VUE_SUC 					= "/pub/Profile.jsp";
	private MessageDao MESDao ;
	private UtilisateurDao userDao ;
	private DemandeDao DMDao ;
	private static final String DAO_FACTORY 				= "daofactory";
	private static final String ATT_SESSION_MESSAGE      	= "mesl";
	private static final String ATT_SESSION_NOTIFICATION 	= "notification";
	private static final String ATT_DML_DB 					= "MapDml_DB";
	
    public Filter_message() {
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
		HttpServletRequest req = (HttpServletRequest) request;
//		MessageForm form_mes = new MessageForm(MESDao);
//		List<Message> messages = new ArrayList<Message>();
		HttpSession ses = req.getSession() ;
		Utilisateur admin = (Utilisateur) ses.getAttribute( ATT_SES_USER );
		if( admin != null && admin.isAdmin() ) {
//			messages = form_mes.listerMessage();
//		if( form_mes.getErreurs().isEmpty()) {
//			request.setAttribute(ATT_MESL, messages );
//			request.setAttribute(ATT_FORM_MES, form_mes );
//		}
			if ( ses.getAttribute( ATT_DML_DB ) == null ) {
				Map<Integer, Demande> mapDM_DB = DMDao.list_demande();
				
				ses.setAttribute( ATT_DML_DB, mapDM_DB );
			}
		if ( ses.getAttribute( ATT_SESSION_MESSAGE ) == null ) {
            int notification = 0;
            List<Message> messages = MESDao.list( userDao );
            Map<Long, Message> mapMessages = new HashMap<Long, Message>();
            for ( Message message : messages ) {
                if ( !message.isVu() ) {
                    notification++;
                }
                mapMessages.put( message.getId(), message );
            }
            ses.setAttribute( ATT_SESSION_NOTIFICATION, notification );
            ses.setAttribute( ATT_SESSION_MESSAGE, mapMessages );
        }
		
	}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		this.MESDao = ((DAOFactory) fConfig.getServletContext().getAttribute(DAO_FACTORY) ).getMessageDao();
		this.userDao = ( (DAOFactory) fConfig.getServletContext().getAttribute( DAO_FACTORY ) )
                .getUtilisateurDao();
		this.DMDao = ((DAOFactory) fConfig.getServletContext().getAttribute(DAO_FACTORY) ).getDMDao();
	}

}
