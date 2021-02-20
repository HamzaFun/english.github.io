package com.aprendre_DAO.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class DAO_tools {
	
	
	
	public static PreparedStatement InitRP(Connection connexion, String sql, boolean returnKey, Object...objects)
	throws SQLException{
		PreparedStatement pst = connexion.prepareStatement(sql, returnKey ? Statement.RETURN_GENERATED_KEYS : Statement.NO_GENERATED_KEYS);
		
		for(int i=0;i< objects.length; i++) {
			if(objects[i] instanceof Integer) {
				if( ( Integer ) objects[i] == -1)
				objects[i] = "%";
			}
			if( objects[i] == null || objects[i] == "") {
				objects[i] = "%";
			}
			System.out.println("object ::"+i+"est : "+objects[i]);
			pst.setObject(i+1, objects[i]);
		}
		return pst;
	}
    public static void fermer( ResultSet resultat) {
    	if(resultat != null) {
    		try {
    			resultat.close();
    		}catch(SQLException e) {
    			System.out.println("échec de fermeture *_* "+ e.getMessage());
    		}
    	}
    }
    public static void fermer(java.sql.Connection con) {
    	if(con != null) {
    		try {
    			con.close();
    		}catch(SQLException e) {
    			System.out.println("échec de fermeture *_* "+ e.getMessage());
    		}
    	}
    }
    public static void fermer(Statement st) {
    	if(st != null) {
    		try {
    			st.close();
    		}catch(SQLException e) {
    			System.out.println("échec de fermeture *_* "+ e.getMessage());
    		}
    	}
    }
    public static void fermer(Statement st, java.sql.Connection con) {
    	fermer(st);
    	fermer(con);
    }
    public static void fermer(ResultSet resultat, Statement st, java.sql.Connection con) {
    	fermer(resultat);
    	fermer(st);
    	fermer(con);
    }
}
