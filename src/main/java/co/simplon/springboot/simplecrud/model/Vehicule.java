package co.simplon.springboot.simplecrud.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table (name="vehicule")
@JsonIgnoreProperties(ignoreUnknown = true)

public class Vehicule implements Serializable{
	

	
/** Classe qui est le miroir de la base de donnée afférente
	 * 
	 */
	private static final long serialVersionUID = 6459603377773703145L;
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
long id;

@Column(name="marque")
private String marque;
@Column(name="modele")
private String modele;
@Column(name="couleur")
private String couleur;
@Column(name="immatriculation", unique=true)
private String immatriculation;


public long getId() {
	return id;
}

public void setId(long id) {
	this.id = id;
}

public String getMarque() {
	return marque;
}

public void setMarque(String marque) {
	this.marque = marque;
}

public String getModele() {
	return modele;
}

public void setModele(String modele) {
	this.modele = modele;
}

public String getImmatriculation() {
	return immatriculation;
}

public void setImmatriculation(String immatriculation) {
	this.immatriculation = immatriculation;
}

public String getCouleur() {
	return couleur;
}

public void setCouleur(String couleur) {
	this.couleur = couleur;
}

public static long getSerialversionuid() {
	return serialVersionUID;
}


}

