package com.aprendre_DAO.forms;

//import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

//import org.joda.time.DateTime;

//import com.aprendre_DAO.c_table.Demande;
import com.aprendre_DAO.c_table.Voyage;
import com.aprendre_DAO.dao.VoyageDao;
import com.aprendre_DAO.exception.DAOException;

public class VoyageForm {
	public static final String  CHAMP_EMAIL 		= "email_m";
	public static final String  CHAMP_ID 			= "id_v";
	public static final String  CHAMP_CONTINENT 	= "continent";
	public static final String  CHAMP_PAYS 			= "pays";
	public static final String  CHAMP_DIS			= "description";
	public static final String  CHAMP_VILLE 		= "ville";
	public static final String  CHAMP_DATE_V		= "date_v";
	public static final String  CHAMP_TEMP_V 		= "temp_v";
	public static final String  CHAMP_DURE_V 		= "dure_v";
	public static final String  CHAMP_TYPE_V 		= "type_v";
	public static final String  CHAMP_PRIX 			= "prix";
	public static final String  CHAMP_LIEN 			= "lien";
	
	
	private String              resultat 			= null;
	public Map<String, String> erreurs 				= new HashMap<String, String>();
	
	private VoyageDao VGDao;
	
	public VoyageForm ( VoyageDao VGDao) {
		this.VGDao = VGDao;
	}
	public String getResultat() {
		return resultat;
	}
	public Map<String, String> getErreurs() {
        return erreurs;
    }
	
	
//	public Map<String, List<Voyage>[]> list_voyage(){
//		
//		Map<String, List<Voyage>[]> LVG = new HashMap<String, List<Voyage>[]>();	
//		try {
//			
//			if( erreurs.isEmpty()) {
//				LVG = VGDao.list_All();
//				resultat="Sucses de la recherche ";
//			}else {
//				resultat = "Échec de l'ajoute.";
//			}
//			
//		}catch(DAOException e) {
//			resultat = "Échec de l'recherche : une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
//			e.printStackTrace();
//		}
//		
//		
//		return LVG;
//	}
public List<Voyage> list_voyage( String plvg ,String test){
		
		List<Voyage> LVG = null;
		try {
			
			if( erreurs.isEmpty()) {
				LVG = VGDao.list_v(plvg,test);
				resultat="Succès de la recherche";
			}else {
				resultat = "échec de l'ajoute.";
			}
			
		}catch(DAOException e) {
			resultat = "échec de la recherche : une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
			e.printStackTrace();
		}
		
		
		return LVG;
	}
public List<Voyage> All_voyage(){
	
	List<Voyage> LVG = null;
	try {
		
		if( erreurs.isEmpty()) {
			LVG = VGDao.all_v();
			resultat="Succès de la recherche";
		}else {
			resultat = "échec de l'ajoute.";
		}
		
	}catch(DAOException e) {
		resultat = "échec de la recherche : une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
		e.printStackTrace();
	}
	
	
	return LVG;
}
public List<Voyage> list_voyage(){
		
		List<Voyage> LVG = null;
		try {
			
			if( erreurs.isEmpty()) {
				LVG = VGDao.list_v();
				resultat="Succès de la recherche";
			}else {
				resultat = "échec de l'ajoute.";
			}
			
		}catch(DAOException e) {
			resultat = "échec de la recherche : une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
			e.printStackTrace();
		}
		
		
		return LVG;
	}
public List<Voyage> list_voyage_p(){
	
	List<Voyage> LVG = null;
	try {
		
		if( erreurs.isEmpty()) {
			LVG = VGDao.list_v_p();
			resultat="Succès de la recherche";
		}else {
			resultat = "échec de l'ajoute.";
		}
		
	}catch(DAOException e) {
		resultat = "échec de la recherche : une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
		e.printStackTrace();
	}
	
	
	return LVG;
}
public List<Voyage> list_voyage_t( String type){
	
	List<Voyage> LVG = null;
	try {
		
		if( erreurs.isEmpty()) {
			LVG = VGDao.list_v_t(type);
			resultat="Succès de la recherche";
		}else {
			resultat = "échec de l'ajoute.";
		}
		
	}catch(DAOException e) {
		resultat = "échec de la recherche : une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
		e.printStackTrace();
	}
	
	
	return LVG;
}
public Voyage ajouterVoyage( HttpServletRequest request ) {
    String continent= getVChamp( request, CHAMP_CONTINENT );
    String pays = getVChamp( request, CHAMP_PAYS );
    String ville= getVChamp( request, CHAMP_VILLE );
    String discription = getVChamp( request, CHAMP_DIS );
    String date_v= getVChamp( request, CHAMP_DATE_V );
    String dure_v = getVChamp( request, CHAMP_DURE_V );
    String temp_v = getVChamp( request, CHAMP_TEMP_V );
    String type_v = getVChamp( request, CHAMP_TYPE_V );
    String prix = getVChamp( request, CHAMP_PRIX );
    String lien= getVChamp( request, CHAMP_LIEN);

    Voyage vg = new Voyage();
    try {
    	if(continent != null) {
    		continent = continent.toUpperCase();
    	}
    	if(pays != null) {
    		pays = pays.toUpperCase();
    	}
    	if(ville != null) {
    		ville = ville.toUpperCase();
    	}
    	traiterDiscription( discription, vg );
    	traiterContinent( continent, vg );
    	traiterPays( pays, vg );
    	traiterVille( ville, vg );
    	traiterDate_v( date_v, vg );
    	traiterDure_v( dure_v, vg );
    	traiterTemp_v( temp_v, vg );
    	traiterType_v( type_v, vg );
    	traiterPrix( prix, vg );        
    	traiterLien( lien, vg );

        if ( erreurs.isEmpty() ) {
            VGDao.create( vg );
            resultat = "voyage ajouter avec succès.";
        } else {
            resultat = "échec de l'ajoute du voyage.";
        }
    } catch ( DAOException e ) {
        setErreur( "imprévu ", "Erreur imprevue lors de l'addition." );
        resultat = "échec de la création du voyage : + une erreur imprévue est survenue, merci de réessayer dans quelques instants. \n + ou vous avez entre une existe voyage";
        e.printStackTrace();
    }

    return vg;
}

public void supprimerVoyage( int id_v ) {
	try {
		
		
		VGDao.delete(id_v);
		
		
	}catch( DAOException e) {
		setErreur( "imprévu ", "Erreur imprévue lors de l'suppression." );
        resultat = "échec de la suppresion du voyage : une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
        e.printStackTrace();
	}
	
}
private void traiterContinent( String continent, Voyage vg ) {
    try {
        vldContinent( continent );
    } catch ( FormValidationException e ) {
        setErreur( CHAMP_CONTINENT, e.getMessage() );
    }
    vg.setContinent( continent );
}
private void traiterPays( String continent, Voyage vg ) {
    try {
        vldContinent( continent );
    } catch ( FormValidationException e ) {
        setErreur( CHAMP_PAYS, e.getMessage() );
    }
    vg.setPays( continent );
}
private void traiterVille( String continent, Voyage vg ) {
    try {
        vldContinent( continent );
    } catch ( FormValidationException e ) {
        setErreur( CHAMP_VILLE, e.getMessage() );
    }
    vg.setVille( continent );
}
private void traiterDate_v( String continent, Voyage vg ) {
    try {
        vldContinent( continent );
    } catch ( FormValidationException e ) {
        setErreur( CHAMP_DATE_V, e.getMessage() );
    }
    vg.setDate_v( continent );
}
private void traiterTemp_v( String continent, Voyage vg ) {
    try {
        vldContinent( continent );
    } catch ( FormValidationException e ) {
        setErreur( CHAMP_TEMP_V, e.getMessage() );
    }
    vg.setTemp_v( continent );
}
private void traiterDure_v( String continent, Voyage vg ) {
    try {
        vldContinent( continent );
    } catch ( FormValidationException e ) {
        setErreur( CHAMP_DURE_V, e.getMessage() );
    }
    vg.setDure_v( continent );
}
private void traiterType_v( String continent, Voyage vg ) {
    try {
        vldContinent( continent );
    } catch ( FormValidationException e ) {
        setErreur( CHAMP_TYPE_V, e.getMessage() );
    }
    vg.setType_v( continent );
}
private void traiterDiscription( String contenu, Voyage vg ) {
    try {
        vldDiscription( contenu );
    } catch ( FormValidationException e ) {
        setErreur( CHAMP_DIS, e.getMessage() );
    }
    vg.setDescription( contenu );
}
private void traiterPrix( String prix, Voyage vg ) {
	int p = -1;
    try {
        p = vldPrix( prix );
    } catch ( FormValidationException e ) {
        setErreur( CHAMP_PRIX, e.getMessage() );
    }
    vg.setPrix( p );
}
private void traiterLien( String contenu, Voyage vg ) {
    try {
        vldLien( contenu );
    } catch ( FormValidationException e ) {
        setErreur( CHAMP_LIEN, e.getMessage() );
    }
    vg.setLien( contenu );
}
private void vldDiscription( String discription ) throws FormValidationException {
    if ( discription != null && discription.length() < 30 ) {
        throw new FormValidationException( "La description du voyage doit contenir au moins 30 caractères." );
    } else if ( discription == null ) {
        throw new FormValidationException( "Merci de saisir la description du voyage." );
    }
}
private void vldLien( String lien ) throws FormValidationException {
    if ( lien != null && lien.length() < 20 ) {
        throw new FormValidationException( "Le lien de l'image doit contenir au moins 20 caractères." );
    } else if ( lien == null ) {
        throw new FormValidationException( "Merci de saisir le lien de l'image." );
    }
}

private void vldContinent( String contenu ) throws FormValidationException {
    if ( contenu != null && contenu.length() < 3 ) {
        throw new FormValidationException( "Ce champ doit contenir au moins 3 caractères." );
    } else if ( contenu == null ) {
        throw new FormValidationException( " Saisir dans ce chmaps svp ." );
    }
}
private int vldPrix( String prix ) throws FormValidationException {
	int p = -1;
    if ( prix != null ) {
    	try {
    		p = Integer.parseInt( prix );
            if ( p < 100 || p > 5000 ) {
                throw new FormValidationException( "vous pouvez pas entrer un prix inferieure a 100 DH ou superieure a 5000 DH " );
            }
            
        } catch ( NumberFormatException e ) {
            p = -1;
            throw new FormValidationException( "Le prix doit etre un nombre." );
        }
    } else if ( prix == null ) {
        throw new FormValidationException( "Merci de saisir le prix." );
    }
    return p;
}













//
//	private void traiterEmail( String email, Demande dm) {
//		try {
//			vld( email );
//		} catch ( FormValidationException e ) {
//			setErreur( CHAMP_EMAIL, e.getMessage() );
//		}
//		dm.setEmail( email );
//	}
//	private void traiterEmail( String email) {
//		try {
//			vld( email );
//		} catch ( FormValidationException e ) {
//			setErreur( CHAMP_EMAIL, e.getMessage() );
//		}
//	}
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
//	
	
	
//	private void vld( String str ) throws FormValidationException {
//        if ( str == null ) {
//            throw new FormValidationException( "L'entrer doit etre non vide." );
//        }
//    }
	
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
