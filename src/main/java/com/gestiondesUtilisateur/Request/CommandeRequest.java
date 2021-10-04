package com.gestiondesUtilisateur.Request;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;


public class CommandeRequest {
	@DateTimeFormat(pattern ="yyyy-mm-jj ")
private Date dateCmd;
private long numero;
private double totale;
private Boolean valide;
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
public Date getDateCmd() {
	return dateCmd;
}
public void setDateCmd(Date dateCmd) {
	this.dateCmd = dateCmd;
}

}
