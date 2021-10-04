package com.gestiondesUtilisateur.Entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.springframework.format.annotation.DateTimeFormat;


@Entity(name="commandes")
public class CommandeEntity implements Serializable {

	private static final long serialVersionUID = -2173672061603566485L;
	@Id 
	@GeneratedValue
	private long id; 
	@Column(nullable=false,length =30)
	private String commandeId;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateCmd;
	@Column(length =12,nullable=false)
	private long numero;
	@Column(nullable=false)
	private double totale;
	@Column(nullable=false) 
	private Boolean valide =false;
	@ManyToOne 
	
	@JoinColumn(name="users_id")
	private UserEntity user; 
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCommandeId() {
		return commandeId;
	}
	public void setCommandeId(String commandeId) {
		this.commandeId = commandeId;
	}
	public Date getDateCmd() {
		return dateCmd;
	}
	public void setDateCmd(Date dateCmd) {
		this.dateCmd = dateCmd;
	}
	public long getNumero() {
		return numero;
	}
	public void setNumero(long numero) {
		this.numero = numero;
	}
	public double getTotale() {
		return totale;
	}
	public void setTotale(double totale) {
		this.totale = totale;
	}
	public Boolean getValide() {
		return valide;
	}
	public void setValide(Boolean valide) {
		this.valide = valide;
	}
	public UserEntity getUser() {
		return user;
	}
	public void setUser(UserEntity user) {
		this.user = user;
	}
	
}
