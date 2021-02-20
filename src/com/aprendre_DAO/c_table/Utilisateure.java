package com.aprendre_DAO.c_table;
import java.sql.Timestamp;


public class Utilisateure {
	private long id ;
	private	String email ;
	private String nom ;
	private String mdp ;
	private Timestamp ddi ;
	
	public long getId() {
		return id;
	}
	public void setId( long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail( String email) {
		this.email = email;
	}
	public String getMdp() {
		return mdp;
	}
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public Timestamp getddi() {
		return ddi;
	}
	public void setddi(Timestamp ddi) {
		this.ddi = ddi;
	}
	
	
	
	
	
}
