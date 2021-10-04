package com.gestiondesUtilisateur.Response;

public class ProduitResponse {
private String produitId;
private String code;
private String nom;
private String description;
private String prix; 
public String getProduitId() {
	return produitId;
}
public void setProduitId(String produitId) {
	this.produitId = produitId;
}
public String getCode() {
	return code;
}
public void setCode(String code) {
	this.code = code;
}
public String getNom() {
	return nom;
}
public void setNom(String nom) {
	this.nom = nom;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public String getPrix() {
	return prix;
}
public void setPrix(String prix) {
	this.prix = prix;
}

}
