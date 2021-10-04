package com.gestiondesUtilisateur.Entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;




@Entity(name="Produits")
public class ProduitEntity implements Serializable {


	private static final long serialVersionUID = 5994743952339664127L;
	@Id
	@GeneratedValue
	private Long id;
	@Column(nullable=false,length =30)
	private String produitId;
	@Column(nullable=false,length =30)
	private String code;
	@Column(nullable=false,length =30)
	private String nom;
	@Column(nullable=false,length =100)
	private String description;
	@Column(nullable=false)
	private String prix;
	@ManyToOne
	@JoinColumn(name="users_id")
	private UserEntity user;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	public UserEntity getUser() {
		return user;
	}
	public void setUser(UserEntity user) {
		this.user = user;
	}
	
	

}
