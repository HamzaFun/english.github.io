package com.aprendre_DAO.c_table;

public class Voyage {
	private int id_v;
	private String continent;
	private String pays;
	private String ville;
	private String description;
	private String date_v;
	private String temp_v;
	private String dure_v;
	private String type_v;
	private int prix;
	private String lien;
	
	
	public void setId_v(int id_v) {
		this.id_v = id_v;
	}
	public int getId_v() {
		return id_v;
	}
	public void setContinent(String continent) {
		this.continent = continent;
	}
	public String getContinent() {
		return continent;
	}
	public void setPays(String pays) {
		this.pays = pays;
	}
	public String getPays() {
		return pays;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public String getVille() {
		return ville;
	}
	public void setDescription(String discription) {
		this.description = discription;
	}
	public String getDescription() {
		return description;
	}
	public void setTemp_v(String temp_v) {
		this.temp_v= temp_v;
	}
	public String getTemp_v() {
		return temp_v;
	}
	public void setDate_v(String date_v) {
		this.date_v= date_v;
	}
	public String getDate_v() {
		return date_v;
	}
	public void setDure_v(String dure_v) {
		this.dure_v= dure_v;
	}
	public String getDure_v() {
		return dure_v;
	}
	public void setType_v(String type_v) {
		this.type_v= type_v;
	}
	public String getType_v() {
		return type_v;
	}
	public void setPrix(int prix) {
		this.prix = prix;
	}
	public int getPrix() {
		return prix;
	}
	public void setLien(String lien) {
		this.lien = lien;
	}
	public String getLien() {
		return lien;
	}
}
