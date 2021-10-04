package com.gestiondesUtilisateur.Response;

import java.util.List;

public class UserResponse {

	private String userId;
	private  String firstname;
	private String lastname; 
	private String email;
	private Boolean admin;
	private List<CommandeResponse>commande;
	private List<ProduitResponse>produit;
	public String getUserId() {
		return userId; 
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<CommandeResponse> getCommande() {
		return commande;
	}
	public void setCommande(List<CommandeResponse> commande) {
		this.commande = commande;
	}
	public Boolean getAdmin() {
		return admin;
	}
	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}
	public List<ProduitResponse> getProduit() {
		return produit;
	}
	public void setProduit(List<ProduitResponse> produit) {
		this.produit = produit;
	}
	
}
