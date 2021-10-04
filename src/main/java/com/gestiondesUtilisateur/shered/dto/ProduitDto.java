package com.gestiondesUtilisateur.shered.dto;

import java.io.Serializable;

public class ProduitDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5931263060970716511L;
	private Long id;
	private String produitId;
	private String code;
	private String nom;
	private String description;
	private String prix;
	private UserDto userDto;
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
	public UserDto getUserDto() {
		return userDto;
	}
	public void setUserDto(UserDto userDto) {
		this.userDto = userDto;
	} 
}
