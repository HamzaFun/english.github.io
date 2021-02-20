package com.aprendre_DAO.forms;


import java.util.HashMap;

import java.util.Map;

//import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.aprendre_DAO.c_table.Demande;
import com.aprendre_DAO.c_table.Utilisateur;
import com.aprendre_DAO.dao.DemandeDao;
import com.aprendre_DAO.exception.DAOException;


public class demandeForm {
	public static final String  CHAMP_EMAIL 	= "email_m";
	public static final String  CHAMP_PAYS 		= "pays";
	public static final String  CHAMP_VILLE 	= "ville";
	public static final String  CHAMP_DATE_V	= "date_v";
	public static final String  CHAMP_TEMP_V 	= "temp_v";
	public static final String  CHAMP_DURE_V 	= "dure_v";
	public static final String  CHAMP_TYPE_V 	= "type_v";
	public static final String  CHAMP_ID 		= "id_v";
	
	private String              resultat = null;
	public Map<String, String> erreurs = new HashMap<String, String>();
	
	private DemandeDao DMDao;
	
	public demandeForm ( DemandeDao DMDao) {
		this.DMDao = DMDao;
	}
	public String getResultat() {
		return resultat;
	}
	public Map<String, String> getErreurs() {
        return erreurs;
    }
	
	public void supprimerDemande( int id_dm ) {
		try {
			DMDao.delete_demande(id_dm);
		}catch( DAOException e) {
			setErreur( "imprévu ", "Erreur imprévue lors de l'suppression." );
	        resultat = "échec de la suppresion du demande : une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
	        e.printStackTrace();
		}
	}
	public void ADD_DM( Map<Integer,Demande> dml) {
		
	    
	    try {
	        
	        if ( erreurs.isEmpty() ) {
	            DMDao.Ajouter_demande( dml );
	            resultat = "votre voyages sont acceptes";
	        } else {
	            resultat = "échec de l'ajoute.";
	        }
	    } catch ( DAOException e ) {
	        resultat = "échec de l'ajoute : une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
	        System.out.println(resultat);
	        e.printStackTrace();
	    }
	}
	public Map<Integer,Demande> find_demande(HttpSession session){
		
		String email = (( Utilisateur ) session.getAttribute("sesuser")).getEmail();
		Map<Integer, Demande> mapdml = new HashMap<Integer,Demande>();
		try {
			traiterEmail(email);
			
			if( erreurs.isEmpty()) {
				mapdml = DMDao.list_demande(email);
				resultat="Succès de la recherche ";
			}else {
				resultat = "échec de l'ajoute.";
			}
			
		}catch(DAOException e) {
			resultat = "échec de la recherche : une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
			e.printStackTrace();
		}
		
		
		return mapdml;
	}
//	private void traiterEmail( String email, Demande dm) {
//		try {
//			vld( email );
//		} catch ( FormValidationException e ) {
//			setErreur( CHAMP_EMAIL, e.getMessage() );
//		}
//		dm.setEmail( email );
//	}
	private void traiterEmail( String email) {
		try {
			vld( email );
		} catch ( FormValidationException e ) {
			setErreur( CHAMP_EMAIL, e.getMessage() );
		}
	}
//	private void traiterId( String id, Demande dm ) {
//		int v = -1;
//		try {
//			v = vldAge( id );
//		} catch ( FormValidationException e ) {
//			setErreur( CHAMP_ID, e.getMessage() );
//		}
//		dm.setId_v( v );
//	}
//	private int vldAge( String id ) throws FormValidationException {
//        int v;
//        if ( id != null ) {
//            try {
//                v = Integer.parseInt( id );
//                if ( v > 100 && v < 0 ) {
//                    throw new FormValidationException( "une erreure dans l'id de voyage . " );
//                }
//            } catch ( NumberFormatException e ) {
//                v = -1;
//                throw new FormValidationException( "L'id doit etre un nombre." );
//            }
//        } else {
//            v = -1;
//            throw new FormValidationException( "Merci d'entrer votre age" );
//        }
//
//        return v;
//    }
//	private void traiterPays( String pays, Demande dm) {
//		try {
//			vld( pays );
//		} catch ( FormValidationException e ) {
//			setErreur( CHAMP_PAYS, e.getMessage() );
//		}
//		dm.setPays( pays );
//	}
//	private void traiterVille( String pays, Demande dm) {
//		try {
//			vld( pays );
//		} catch ( FormValidationException e ) {
//			setErreur( CHAMP_PAYS, e.getMessage() );
//		}
//		dm.setVille( pays );
//	}
//	private void traiterDate_v( String date_v, Demande dm) {
//		try {
//			vld( date_v );
//		} catch ( FormValidationException e ) {
//			setErreur( CHAMP_DATE_V, e.getMessage() );
//		}
//		dm.setDate_v( date_v );
//	}
//	private void traiterTemp_v( String temp_v, Demande dm) {
//		try {
//			vld( temp_v );
//		} catch ( FormValidationException e ) {
//			setErreur( CHAMP_TEMP_V, e.getMessage() );
//		}
//		dm.setTemp_v( temp_v );
//	}
//	private void traiterDure_v( String dure_v, Demande dm) {
//		try {
//			vld( dure_v );
//		} catch ( FormValidationException e ) {
//			setErreur( CHAMP_DURE_V, e.getMessage() );
//		}
//		dm.setDure_v( dure_v );
//	}
//	private void traiterType_v( String type_v, Demande dm) {
//		try {
//			vld( type_v );
//		} catch ( FormValidationException e ) {
//			setErreur( CHAMP_DURE_V, e.getMessage() );
//		}
//		dm.setType_v( type_v );
//	}
	
	
	
	private void vld( String str ) throws FormValidationException {
        if ( str == null ) {
            throw new FormValidationException( "L'entrer doit etre non vide." );
        }
    }
	
	private void setErreur( String CHAMP,String mes) {
		erreurs.put(CHAMP, mes);
	}
//	private static String getVChamp( HttpServletRequest request, String champ ) {
//        String val = request.getParameter( champ );
//        if ( val == null || val.trim().length() == 0 ) {
//            return null;
//        } else {
//            return val.trim();
//        }      
//    }
}
