package com.gestiondesUtilisateur.shered.dto;

import java.io.Serializable;
import java.util.List;

public class UserDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -291927327510445059L;
	private long id;
	private String userId;
	private String firstname;
	private String lastname;
	private String email;
	private String password;
	private Boolean admin;
	private String encryptedPassword;
	private String emailVerificationToken;
	private Boolean emailVerificationStatus = false; 
	private List<CommandeDto> commande;
	private List<ProduitDto> produit;
     public long getId() {
		return id;
	}  
	public void setId(long id) {
		this.id = id;
	}
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEncryptedPassword() {
		return encryptedPassword;
	}
	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}
	public String getEmailVerificationToken() {
		return emailVerificationToken;
	}
	public void setEmailVerificationToken(String emailVerificationToken) {
		this.emailVerificationToken = emailVerificationToken;
	}
	public Boolean getEmailVerificationStatus() {
		return emailVerificationStatus;
	}
	public void setEmailVerificationStatus(Boolean emailVerificationStatus) {
		this.emailVerificationStatus = emailVerificationStatus;
	}
	public List<CommandeDto> getCommande() {
		return commande;
	}
	public void setCommande(List<CommandeDto> commande) {
		this.commande = commande;
	}
	public Boolean getAdmin() {
		return admin;
	}
	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}
	public List<ProduitDto> getProduit() {
		return produit;
	}
	public void setProduit(List<ProduitDto> produit) {
		this.produit = produit;
	}
	
	
	
	
}
