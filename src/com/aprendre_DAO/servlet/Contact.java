package com.aprendre_DAO.servlet;

import java.io.IOException;

import com.aprendre_DAO.c_table.Message;
import com.aprendre_DAO.dao.DAOFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aprendre_DAO.dao.MessageDao;
import com.aprendre_DAO.forms.MessageForm;


@SuppressWarnings("serial")
@WebServlet( "/contact" )
public class Contact extends HttpServlet {
    private static final String CONF_DAO_FACTORY 		= "daofactory";
    private static final String VUE              		= "/WEB-INF/user/Profile.jsp";

    private static final String ATT_MESSAGE      		= "message";
    private static final String ATT_FORM         		= "form_mes";

    private MessageDao          messageDao;

    public void init() throws ServletException {
        this.messageDao = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getMessageDao();
    }

    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }

    protected void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        MessageForm form = new MessageForm( messageDao );
        Message message = form.envoyerMessage( request );

        request.setAttribute( ATT_MESSAGE, message );
        request.setAttribute( ATT_FORM, form );

        if ( form.getErreurs().isEmpty() ) {
            request.removeAttribute( ATT_MESSAGE );
        }

        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }
}
