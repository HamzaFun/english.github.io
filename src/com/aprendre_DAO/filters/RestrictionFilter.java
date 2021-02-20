package com.aprendre_DAO.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aprendre_DAO.c_table.Utilisateur;


@WebFilter( urlPatterns = "/*" )
public class RestrictionFilter implements Filter {
    private static final String VISITEUR_PUB       			= "/pub";
    private static final String VISITEUR_CSS     			= "/Css";
    private static final String VISITEUR_LIST        		= "/L";
    private static final String VISITEUR_IMAGE= 			"/images";
    private static final String VISITEUR_RECHERCH			= "/rech";
    private static final String VISITEUR_ADD 				= "/Add";
    private static final String VISITEUR_INC				= "/inscription";
    
    private static final String UTILISATEUR_PROFIL       	= "/WEB-INF/user";
    private static final String UTILISATEUR_DECCONNEXION 	= "/dec";
    private static final String UTILISATEUR_CONNEXION 		= "/con";
    private static final String UTILISATEUR_SUPRIMER 		= "/Sup";
    

    private static final String ADMIN_ADMINISTRATION     	= "WEB-INF/administration";
    private static final String ADMIN_MES    				= "/Mes";
    private static final String ADMIN_AD     				= "/ad";


    private static final String ACCES_404                	= "/pub/404.jsp";

    private static final String ATT_SESSION_USER         	= "sesuser";

    public void init( FilterConfig config ) throws ServletException {
    }

    public void doFilter( ServletRequest req, ServletResponse resp, FilterChain chain )
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        String chemin = request.getRequestURI().substring( request.getContextPath().length() );
        
        System.out.println("chemin :::: "+ chemin);
        if ( chemin.startsWith( VISITEUR_PUB )
//        		|| chemin.startsWith( "/type_f" )
                || chemin.startsWith( VISITEUR_LIST )
                || chemin.startsWith( VISITEUR_CSS )
                || chemin.startsWith( VISITEUR_IMAGE )
                || chemin.startsWith( VISITEUR_ADD )
                || chemin.startsWith( VISITEUR_INC )
                || chemin.startsWith( VISITEUR_RECHERCH )
                || chemin.startsWith( UTILISATEUR_SUPRIMER )
                || chemin.startsWith( UTILISATEUR_CONNEXION )
                || chemin.startsWith( ADMIN_AD )
                || chemin.startsWith( ACCES_404 )
                || chemin.length() <= 1 ) {
            chain.doFilter( request, response );
            return;
        }

        HttpSession session = request.getSession();

        Utilisateur user = (Utilisateur) session.getAttribute( ATT_SESSION_USER );
        if ( user == null ) {
            // response.sendRedirect(request.getContextPath() + ACCES_404);
            request.getRequestDispatcher( ACCES_404 ).forward( request, response );
        } else {
            if ( chemin.startsWith( UTILISATEUR_PROFIL )
                    || chemin.startsWith( UTILISATEUR_DECCONNEXION ) ) {
                chain.doFilter( request, response );
                return;
            } else {
                if ( user.isAdmin() ) {
                    if ( chemin.startsWith( ADMIN_ADMINISTRATION ) || chemin.startsWith( ADMIN_MES ) || chemin.startsWith( ADMIN_AD ) ) {
                        chain.doFilter( request, response );
                        return;
                    } else {
                        request.getRequestDispatcher( ACCES_404 ).forward( request, response );
                    }
                } else {
                    request.getRequestDispatcher( ACCES_404 ).forward( request, response );
                }
            }
        }
    }

    public void destroy() {
    }
}
