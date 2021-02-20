package com.aprendre_DAO.c_table;
public class Utilisateur {
	private String email;
	private String nom;
	private String prenom;
	private String mdp;
	private String pays;
	private int age;
	private String tel;
	private boolean admin;
	
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmail() {
		return email;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getNom() {
		return nom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	public String getMdp() {
		return mdp;
	}
	public void setPays(String pays) {
		this.pays= pays;
	}
	public String getPays() {
		return pays;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getAge() {
		return age;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getTel() {
		return tel;
	}
	public boolean isAdmin() {
		return this.admin ; 
	}
	public void setAdmin( boolean admin) {
		this.admin = admin;
	}
	
}
