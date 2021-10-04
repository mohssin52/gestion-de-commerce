package com.gestiondesUtilisateur.Request;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserRequest {
	@NotNull(message="Ce champ ne doit etre null !")
@Size(max=20,message="Ce champ  doit avoir au max 20 caractere")
	private String firstname;
@NotNull(message="Ce champ ne doit etre null !")
@Size(max=20,message="Ce champ  doit avoir au max 20 caractere")
	private String lastname;
@NotNull(message="Ce champ ne doit etre null !")
@Size(max=50,message="Ce champ  doit avoir au max 20 caractere")
@Email(message ="ce champ doit respecter la format email")
	private String email;
@NotNull(message="Ce champ ne doit etre null !")
@Size(min=8, message="mot de passe doit avoir au moins 8 caracteres !")
@Size(max=12, message="mot de passe doit avoir au max 12 caracteres !")
@Pattern(regexp="(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$", message="ce mot de passe doit avoir des lettres en Maj et Minsc et numero")
	private String password;
private Boolean admin; 
    private List<CommandeRequest>commande;
    private List<ProduitRequest>produitRequest;
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
	
	public List<CommandeRequest> getCommande() {
		return commande;
	}
	public void setCommande(List<CommandeRequest> commande) {
		this.commande = commande;
	}
	public Boolean getAdmin() {
		return admin;
	}
	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}
	public List<ProduitRequest> getProduitRequest() {
		return produitRequest;
	}
	public void setProduitRequest(List<ProduitRequest> produitRequest) {
		this.produitRequest = produitRequest;
	}


	}



