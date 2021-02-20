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
import com.aprendre_DAO.dao.DAOFactory;
import com.aprendre_DAO.dao.MessageDao;
import com.aprendre_DAO.exception.DAOException;

@WebServlet( "/adminSuppressionMessage" )
public class AdminSuppressionMessage extends HttpServlet {
	
	private static final long serialVersionUID 			= 1L;
    private static final String PARAM_ID            	= "idMessage";
    private static final String ATT_SESSION_MESSAGE 	= "mesl";

    public static final String  VUE                 	= "/WEB-INF/user/Profile.jsp";

    private MessageDao MESDao ;
	private static final String DAO_FACTORY 			= "daofactory";
	public void init() throws ServletException{
    	this.MESDao = ((DAOFactory) getServletContext().getAttribute(DAO_FACTORY) ).getMessageDao();
    }

	@SuppressWarnings("unchecked")
    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        String idMessage = getValeurParametre( request, PARAM_ID );

        HttpSession session = request.getSession();
        Map<Long, Message> messages = (HashMap<Long, Message>) session.getAttribute( ATT_SESSION_MESSAGE );

        /* Si l'id du message et la Map des messages ne sont pas vides */
        if ( messages != null ) {
            try {
                Long id = Long.parseLong( idMessage );

                /* Alors suppression de la base */
                MESDao.delete( id );
                /* Puis suppression du type de la Map */
                messages.remove( id );

                /*
                 * Et remplacement de l'ancienne Map en session par la nouvelle
                 */
                session.setAttribute( ATT_SESSION_MESSAGE, messages );
            } catch ( DAOException e ) {
                e.printStackTrace();
            } catch ( NumberFormatException e ) {
                e.printStackTrace();
            }
        }
        /* Redirection vers la fiche récapitulative */
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }

    private static String getValeurParametre( HttpServletRequest request, String nomChamp ) {
        String valeur = request.getParameter( nomChamp );
        if ( valeur == null || valeur.trim().length() == 0 ) {
            return null;
        } else {
            return valeur;
        }
    }
}
