package com.aprendre_DAO.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.jasypt.util.password.ConfigurablePasswordEncryptor;

import com.aprendre_DAO.c_table.Utilisateur;
import com.aprendre_DAO.dao.UtilisateurDao;
import com.aprendre_DAO.exception.DAOException;
import com.aprendre_DAO.forms.FormValidationException;

public class ConnexionForm {
	 private static final String CHAMP_EMAIL      = "email";
	 private static final String CHAMP_PASS       = "mdp";
	 private static final String ALGO_CHIFFREMENT = "SHA-256";
	 
	 private String resultat_con;
	 private Map<String, String> erreurs = new HashMap<String, String>();
	 private UtilisateurDao userDao;
	 
	 public ConnexionForm( UtilisateurDao userDao) {
		 this.userDao = userDao;
	 }
	 public String getResultat_con() {
	        return resultat_con;
	    }

	 public Map<String, String> getErreurs() {
	        return erreurs;
	 }
	 public Utilisateur Connecteruser( HttpServletRequest request) {
		 String email = getVChamp( request, CHAMP_EMAIL );
	     String mdp = getVChamp( request, CHAMP_PASS );
	     
	     Utilisateur user = new Utilisateur() ;
	     Utilisateur user_db = new Utilisateur();
	     try {
	    	 traiterEmail( email, user);
	    	 user_db = userDao.find( email );
	    	 boolean passeCorrect = false;
	    	 if ( user_db != null ) {
	                passeCorrect = traiterMdp( mdp, user_db.getMdp() );
	            }
	            
	    	 	resultat_con = "échec de la connexion.";
	    	 	if ( erreurs.isEmpty() ) {
	                if ( user_db == null ) {
	                    setErreur( CHAMP_EMAIL, "L'e-mail entre ne correspond a aucun compte." );
	                } else if ( !passeCorrect ) {
	                    setErreur( CHAMP_PASS, "Mot de passe incorrect." );
	                } else {
	                    user.setAge( user_db.getAge() );
//	                    user_db.setDateInscription( user.getDateInscription() );
	                    user.setNom( user_db.getNom() );
	                    user.setPrenom( user_db.getPrenom() );
	                    user.setTel( user_db.getTel() );
	                    user.setAdmin( user_db.isAdmin() );

	                    resultat_con = "Succès de la connexion.";
	                }
	            }
	     		}catch ( DAOException e ) {
	            setErreur( "imprevu ", "Erreur imprévue lors de la création." );
	            resultat_con = "échec de la connexion : une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
	            e.printStackTrace();
	        }
	     return user;
	 }
	     private boolean traiterMdp( String motDePasse, String motDePasseChiffre ) {

	         
	         try {
	             vldMdp( motDePasse );
	         } catch ( FormValidationException e ) {
	             setErreur( CHAMP_PASS, e.getMessage() );
	         }

	         
	         ConfigurablePasswordEncryptor passwordEncryptor = new ConfigurablePasswordEncryptor();
	         passwordEncryptor.setAlgorithm( ALGO_CHIFFREMENT );
	         passwordEncryptor.setPlainDigest( false );
	         return passwordEncryptor.checkPassword( motDePasse, motDePasseChiffre );
	     }

	     
	     private void vldMdp( String mdp ) throws FormValidationException {
	         if ( mdp == null ) {
	             throw new FormValidationException( "Merci de saisir votre mot de passe." );
	         }
	     }
	 private void traiterEmail( String email, Utilisateur utilisateur ) {
	        /* Validation du champ email. */
	        try {
	            vldEmail( email );
	        } catch ( FormValidationException e ) {
	            setErreur( CHAMP_EMAIL, e.getMessage() );
	        }
	        utilisateur.setEmail( email );
	    }
	 private void vldEmail( String email ) throws FormValidationException {
	        if ( email != null && !email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
	            throw new FormValidationException( "Merci de saisir une adresse mail valide." );
	        } else if ( email == null ) {
	            throw new FormValidationException( "Merci de saisir votre adresse mail." );
	        } else {

	        }
	    }
	 
	 private void setErreur( String CHAMP,String mes) {
			erreurs.put(CHAMP, mes);
		}
	 
	 private static String getVChamp( HttpServletRequest request, String champ ) {
	        String val = request.getParameter( champ );
	        if ( val == null || val.trim().length() == 0 ) {
	            return null;
	        } else {
	            return val.trim();
	        }      
	    }
}
