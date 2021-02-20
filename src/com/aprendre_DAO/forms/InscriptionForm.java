package com.aprendre_DAO.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.jasypt.util.password.ConfigurablePasswordEncryptor;

import com.aprendre_DAO.c_table.Utilisateur;
import com.aprendre_DAO.dao.UtilisateurDao;
import com.aprendre_DAO.exception.DAOException;




public class InscriptionForm {
	private static final String ALGO_CHIFFREMENT 	= "SHA-256";
	public static final String  CHAMP_EMAIL 		= "email";
	public static final String  CHAMP_MDP 			= "mdp";
	public static final String  CHAMP_PAYS 			= "pays";
	public static final String  CHAMP_NOM 			= "nom";
	public static final String  CHAMP_PRENOM 		= "prenom";
	public static final String  CHAMP_AGE 			= "age";
	public static final String  CHAMP_TEL	 		= "tel";
	public static final String  CHAMP_CONF 			= "confirmation";
	private String              resultat 			= null;
	public Map<String, String> erreurs 				= new HashMap<String, String>();
	
	private UtilisateurDao userDao ;
	public InscriptionForm ( UtilisateurDao userDao) {
		this.userDao = userDao;
	}
	public String getResultat() {
		return resultat;
	}
	public Map<String, String> getErreurs() {
        return erreurs;
    }
	public Utilisateur inscrireUser( HttpServletRequest request) {
		String email = getVChamp(request, CHAMP_EMAIL );
		String mdp = getVChamp( request, CHAMP_MDP );
	    String confirmation = getVChamp( request, CHAMP_CONF );
	    String nom = getVChamp( request, CHAMP_NOM );
	    String pays = getVChamp( request, CHAMP_PAYS );
	    String prenom = getVChamp(request, CHAMP_PRENOM );
	    String age = request.getParameter( CHAMP_AGE );
	    String tel = request.getParameter( CHAMP_TEL );
	    
	    Utilisateur user = new Utilisateur();
	    try {
	    	traiterNom( nom, user );
	    	traiterPrenom(prenom, user );
	        traiterEmail( email, user );
	        traiterTel(tel,user);
	        traiterMDP( mdp, confirmation, user );
	        traiterPays(pays, user);
	        traiterAge(age, user);
	        
	        if ( erreurs.isEmpty() ) {
	            userDao.create( user );
	            resultat = "Succès de l'inscription.";
	        } else {
	            resultat = "échec de l'inscription.";
	        }
	    } catch ( DAOException e ) {
	        resultat = "échec de l'inscription : une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
	        System.out.println(resultat);
	        e.printStackTrace();
	    }

	    return user;
	}
	
	private void traiterNom( String nom, Utilisateur user ) {
		try {
			vldNom( nom );
		} catch ( FormValidationException e ) {
			setErreur( CHAMP_NOM, e.getMessage() );
		}
		user.setNom( nom );
	}
	
	private void traiterPrenom( String prenom, Utilisateur user ) {
		try {
			vldPrenom( prenom );
		} catch ( FormValidationException e ) {
			setErreur( CHAMP_PRENOM, e.getMessage() );
		}
		user.setPrenom( prenom );
	}
	
	private void traiterEmail( String email, Utilisateur user ) {
		try {
			vldEmail( email );
		} catch ( FormValidationException e ) {
			setErreur( CHAMP_EMAIL, e.getMessage() );
		}
		user.setEmail( email );
	}
	
	private void traiterAge( String age, Utilisateur user ) {
		int v = -1;
		try {
			v = vldAge( age );
		} catch ( FormValidationException e ) {
			setErreur( CHAMP_AGE, e.getMessage() );
		}
		user.setAge( v );
	}
	
	
	
	private void traiterMDP( String mdp, String confirmation, Utilisateur user ) {
	    try {
	    	validationMotsDePasse( mdp, confirmation );
	    } catch ( FormValidationException e ) {
	        setErreur( CHAMP_MDP, e.getMessage() );
	        setErreur( CHAMP_CONF, null );
	    }

	    ConfigurablePasswordEncryptor passwordEncryptor = new ConfigurablePasswordEncryptor();
	    passwordEncryptor.setAlgorithm( ALGO_CHIFFREMENT );
	    passwordEncryptor.setPlainDigest( false );
	    String mdpC = passwordEncryptor.encryptPassword( mdp );

	    user.setMdp( mdpC );
	}
	private void traiterTel( String tel, Utilisateur user ) {
        try {
            vldTel( tel );
        } catch ( FormValidationException e ) {
            setErreur( CHAMP_TEL, e.getMessage() );
        }
        user.setTel( tel );
    }
	
	private void traiterPays(String pays, Utilisateur user) {
		try {
	        vldPays( pays);
	    } catch ( FormValidationException e ) {
	        setErreur( CHAMP_PAYS, e.getMessage() );
	    }
	    user.setPays( pays );
	}
	
	private void vldEmail( String email ) throws FormValidationException {
		if ( email != null ) {
			if ( ! email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
				throw new FormValidationException( "Merci de saisir une adresse mail valide." );
			} else if ( userDao.find( email ) != null ) {
				throw new FormValidationException( "Cette adresse email est déjà utilisée, merci d'en choisir une autre." );
			}
		} else {
			throw new FormValidationException( "SVP saisir une adresse mail ." );
		}
	}
	
	 private int vldAge( String age ) throws FormValidationException {
	        int v;
	        if ( age != null ) {
	            try {
	                v = Integer.parseInt( age );
	                if ( v < 10 && v < 0 ) {
	                    throw new FormValidationException( "vous pouvez pas acceder notre site . " );
	                }
	            } catch ( NumberFormatException e ) {
	                v = -1;
	                throw new FormValidationException( "L'Age doit etre un nombre." );
	            }
	        } else {
	            v = -1;
	            throw new FormValidationException( "Merci d'entrer votre age" );
	        }

	        return v;
	    }

    private void vldTel( String tel ) throws FormValidationException {
        if ( tel != null ) {
            if ( !tel.matches( "^\\d+$" ) ) {
                throw new FormValidationException( "Le numero de telephone doit uniquement contenir des chiffres." );
            } else if ( tel.length() < 4 || tel.length() > 10 ) {
                throw new FormValidationException(
                        "Le numero de telephone doit contenir au moins 4 chiffres et 10 chiffres au plus." );
            }
        } else {
            throw new FormValidationException( "Merci d'entrer un numero de telephone." );
        }
    }


    private void validationMotsDePasse( String motDePasse, String confirmation ) throws FormValidationException {
        if ( motDePasse != null && confirmation != null ) {
            if ( !motDePasse.equals( confirmation ) ) {
                throw new FormValidationException(
                        "Les mots de passe entrÃ©s sont diffÃ©rents, merci de les saisir à nouveau." );
            } else if ( motDePasse.trim().length() < 3 ) {
                throw new FormValidationException( "Les mots de passe doivent contenir au moins 3 caractères." );
            }
        } else {
            throw new FormValidationException( "Merci de saisir et confirmer votre mot de passe." );
        }
    }

    private void vldNom( String nom ) throws FormValidationException {
        if ( nom != null && nom.length() < 3 ) {
            throw new FormValidationException( "Le nom d'utilisateur doit contenir au moins 3 caractères." );
        } else if ( nom == null ) {
            throw new FormValidationException( "Merci de saisir votre nom." );
        }
    }
    private void vldPays( String nom ) throws FormValidationException {
        if ( nom != null && nom.length() < 3 ) {
            throw new FormValidationException( "Le champ du pays doit contenir au moins 3 caractères." );
        } else if ( nom == null ) {
            throw new FormValidationException( "Merci de saisir votre pays." );
        }
    }

    private void vldPrenom( String prenom ) throws FormValidationException {
        if ( prenom != null && prenom.length() < 3 ) {
            throw new FormValidationException( "Le prenom d'utilisateur doit contenir au moins 3 caractères." );
        } else if ( prenom == null ) {
            throw new FormValidationException( "Merci de saisir votre prenom." );
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
