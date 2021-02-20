package com.aprendre_DAO.administration;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aprendre_DAO.c_table.Message;
import com.aprendre_DAO.dao.MessageDao;
import com.aprendre_DAO.dao.DAOFactory;

@WebServlet( "/adminModificationMessage" )
public class AdminModificationMessage extends HttpServlet {
    
	private static final long serialVersionUID 			= 1L;
//	private static final String CONF_DAO_FACTORY    	= "daoFactory";

    private static final String ATT_SESSION_MESSAGE 	= "mesl";
//    private static final String ATT_MESSAGE         	= "message";
    private static final String ATT_NOTIFICATION    	= "notification";

    private static final String PARAM_ID            	= "id";
    private static final String PARAM_VU            	= "vu";

    public static final String  VUE                 	= "/WEB-INF/user/Profile.jsp";

    private MessageDao MESDao ;
   	private static final String DAO_FACTORY = "daofactory";
   	public void init() throws ServletException{
       	this.MESDao = ( (DAOFactory) getServletContext().getAttribute(DAO_FACTORY) ).getMessageDao();
       }

    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        String idValeur = getValeurParam( request, PARAM_ID );
        Boolean vu = Boolean.parseBoolean( getValeurParam( request, PARAM_VU ) );

        Long id = null;
        try {
            id = Long.parseLong( idValeur );

        } catch ( NumberFormatException e ) {
            e.printStackTrace();
        }

        HttpSession session = request.getSession();
        
        @SuppressWarnings("unchecked")
        Map<Long, Message> mapMessages = (HashMap<Long, Message>) session.getAttribute( ATT_SESSION_MESSAGE );

        Message message = mapMessages.get( id );

        /* Mise a jour des notifications */
        int notif = (int) session.getAttribute( ATT_NOTIFICATION );
        if ( !message.isVu() ) {
            session.setAttribute( ATT_NOTIFICATION, notif - 1 );
        } else {
            session.setAttribute( ATT_NOTIFICATION, notif + 1 );
        }
        /* Mise a jour dans la base */
        try {
        	MESDao.update( message, vu );
        } catch ( NullPointerException e ) {
            e.printStackTrace();
        }
        /* Puis Mise a jour dans la Map */
        mapMessages.replace( message.getId(), message );
        /*
         * Et remplacement de l'ancienne Map en session par la nouvelle
         */
        session.setAttribute( ATT_SESSION_MESSAGE, mapMessages );
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }

    @Override
    protected void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
    }

    private static String getValeurParam( HttpServletRequest request, String nomChamp ) {
        String valeur = request.getParameter( nomChamp );
        if ( valeur == null || valeur.trim().length() == 0 ) {
            return null;
        } else {
            return valeur.trim();
        }
    }
}
