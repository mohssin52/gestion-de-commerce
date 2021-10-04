package com.gestiondesUtilisateur.Request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;




public class ProduitRequest {
	@NotNull(message="Ce champ ne doit etre null !")
	private String code;  
	@NotNull(message="Ce champ ne doit etre null !")
	@Size(max=20,message="Ce champ  doit avoir au max 20 caractere")
	private String nom;
	@NotNull(message="Ce champ ne doit etre null !")
	@Size(max=100,message="Ce champ  doit avoir au max 100 caractere")
	private String description;
	@NotNull(message="Ce champ ne doit etre null !")
	private String prix; 
	
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
