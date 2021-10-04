package com.gestiondesUtilisateur.Entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="users")
public class UserEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7542260126433016150L;
	@Id
	@GeneratedValue
	private long id;
	@Column(nullable=false)
	private String userId;
	@Column(nullable=false)
	private String firstname;
	@Column(nullable=false)
	private String lastname;
	@Column(nullable=false,unique=true)
	private String email;
	private Boolean admin;
	@Column(nullable=false)
	private String encryptedPassword;
	@Column(nullable=true)
	private String emailVerificationToken;
	@Column(nullable=false)
	private Boolean emailVerificationStatus = false;
	@OneToMany(fetch=FetchType.LAZY,cascade =CascadeType.ALL,mappedBy ="user") 
	private List<CommandeEntity>commande;
	@OneToMany(fetch =FetchType.LAZY,cascade=CascadeType.ALL,mappedBy="user")
	private List<ProduitEntity> produit;
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
	public List<CommandeEntity> getCommande() {
		return commande;
	}
	public void setCommande(List<CommandeEntity> commande) {
		this.commande = commande;
	}
	public Boolean getAdmin() {
		return admin;
	}
	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}
	public List<ProduitEntity> getProduit() {
		return produit;
	}
	public void setProduit(List<ProduitEntity> produit) {
		this.produit = produit;
	}

	
	
	
}
