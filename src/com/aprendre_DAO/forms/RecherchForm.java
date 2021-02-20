package com.aprendre_DAO.forms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.aprendre_DAO.c_table.Voyage;
import com.aprendre_DAO.dao.VoyageDao;
import com.aprendre_DAO.exception.DAOException;

public class RecherchForm {
	public static final String  CHAMP_ID 			= "id_v";
	public static final String  CHAMP_CONTINENT 	= "continent";
	public static final String  CHAMP_PAYS 			= "pays";
	public static final String  CHAMP_DIS			= "description";
	public static final String  CHAMP_VILLE 		= "ville";
	public static final String  CHAMP_DATE_V		= "date_v";
//	public static final String  CHAMP_TEMP_V 		= "temp_v";
	public static final String  CHAMP_DURE_V 		= "dure_v";
	public static final String  CHAMP_TYPE_V 		= "type_v";
	public static final String  CHAMP_PRIX 			= "prix";
	
	private String              resultat 			= null;
	public Map<String, String> erreurs 				= new HashMap<String, String>();
	
	private VoyageDao VGDao;
	
	public RecherchForm ( VoyageDao VGDao) {
		this.VGDao = VGDao;
	}
	public String getResultat() {
		return resultat;
	}
	public Map<String, String> getErreurs() {
        return erreurs;
    }
	
	public List<Voyage> recherch(HttpServletRequest request){
		String continent= getVChamp( request, CHAMP_CONTINENT );
	    String pays = getVChamp( request, CHAMP_PAYS );
	    String ville= getVChamp( request, CHAMP_VILLE );
	    String date_v= getVChamp( request, CHAMP_DATE_V );
	    String dure_v = getVChamp( request, CHAMP_DURE_V );
//	    String temp_v = getVChamp( request, CHAMP_TEMP_V );
	    String type_v = getVChamp( request, CHAMP_TYPE_V );
	    String prix = getVChamp( request, CHAMP_PRIX );
		List<Voyage> LV = new ArrayList<Voyage>();
		Voyage vg = new Voyage();
		try {
			traiterContinent( continent, vg );
	    	traiterPays( pays, vg );
	    	traiterVille( ville, vg );
	    	traiterDate_v( date_v, vg );
	    	traiterDure_v( dure_v, vg );
//	    	traiterTemp_v( temp_v, vg );
	    	traiterType_v( type_v, vg );
	    	traiterPrix( prix, vg ); 
	    	if(erreurs.isEmpty()) {
	    		LV = VGDao.recherche(vg);
	    		resultat= "Recherche Complete";
	    	}else {
	            resultat = "échec de l'operation.";
	        }
		}catch ( DAOException e ) {
	        resultat = "échec de la recherche : une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
	        System.out.println(resultat);
	        e.printStackTrace();
	    }
		
		
		return LV;
	}
	
	
	private void traiterContinent( String continent, Voyage vg ) {
	    try {
	    	if(continent != null) {
	    		continent = continent.toUpperCase();
	    	}
	        vldContinent( continent );
	    } catch ( FormValidationException e ) {
	        setErreur( CHAMP_CONTINENT, e.getMessage() );
	    }
	    vg.setContinent( continent );
	}
	private void traiterPays( String pays, Voyage vg ) {
	    try {
	    	if(pays != null) {
	    		pays = pays.toUpperCase();
	    	}
	        vldContinent( pays );
	    } catch ( FormValidationException e ) {
	        setErreur( CHAMP_PAYS, e.getMessage() );
	    }
	    vg.setPays( pays );
	}
	private void traiterVille( String ville, Voyage vg ) {
	    try {
	    	if(ville != null) {
	    		ville = ville.toUpperCase();
    	}
	        vldContinent( ville );
	    } catch ( FormValidationException e ) {
	        setErreur( CHAMP_VILLE, e.getMessage() );
	    }
	    vg.setVille( ville );
	}
	private void traiterDate_v( String date, Voyage vg ) {
	    try {
	    	if(date != null) {
	    		date = date.toUpperCase();
	    	}
	        vldContinent( date );
	    } catch ( FormValidationException e ) {
	        setErreur( CHAMP_DATE_V, e.getMessage() );
	    }
	    vg.setDate_v( date );
	}
//	private void traiterTemp_v( String continent, Voyage vg ) {
//	    try {
//	    	if(continent != null) {
//	    		continent = continent.toUpperCase();
//	    	}
//	        vldContinent( continent );
//	    } catch ( FormValidationException e ) {
//	        setErreur( CHAMP_TEMP_V, e.getMessage() );
//	    }
//	    vg.setTemp_v( continent );
//	}
	private void traiterDure_v( String dure, Voyage vg ) {
	    try {
//	    	if(dure != null) {
//	    		dure = dure.toUpperCase();
//	    	}
	        vldContinent( dure );
	    } catch ( FormValidationException e ) {
	        setErreur( CHAMP_DURE_V, e.getMessage() );
	    }
	    vg.setDure_v( dure );
	}
	private void traiterType_v( String type, Voyage vg ) {
	    try {
//	    	if(type != null) {
//	    		type = type.toUpperCase();
//	    	}
	        vldContinent( type );
	    } catch ( FormValidationException e ) {
	        setErreur( CHAMP_TYPE_V, e.getMessage() );
	    }
	    vg.setType_v( type );
	}

	private void traiterPrix( String prix, Voyage vg ) {
		int p = 1000;
	    try {
	        p = vldPrix( prix );
	    } catch ( FormValidationException e ) {
	        setErreur( CHAMP_PRIX, e.getMessage() );
	    }
	    vg.setPrix( p );
	}

	private void vldContinent( String contenu ) throws FormValidationException {
	    if ( contenu != null && contenu.length() < 3 ) {
	        throw new FormValidationException( "Le contenu doit contenir au moins 3 caractères." );
	    }else {
	    	contenu = "";
	    }
	}
	private int vldPrix( String prix ) throws FormValidationException {
		int p = 1000;
	    if ( prix != null ) {
	    	try {
	    		p = Integer.parseInt( prix );
	            if ( p < 0  ) {
	                throw new FormValidationException( "vous pouvez pas entrer un prix négatif " );
	            }
	            
	        } catch ( NumberFormatException e ) {
	            p = -1;
	            throw new FormValidationException( "Le prix doit etre un nombre." );
	        }
	    } 
	    return p;
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
