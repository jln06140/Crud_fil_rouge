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
@Table(name = "affaire")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Affaire implements Serializable{
	


	/**
	 * 
	 */
	private static final long serialVersionUID = -7492656493898251454L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name="id_agent")
	private int idAgent;
	@Column(name="titre")
	private String titre;
	@Column(name="date_ouverture")
	private String dateOuverture;
	@Column(name="status")
	private String status;
	@Column(name="date_cloture")
	private String dateCloture;
	@Column(name="description")
	private String description;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getIdAgent() {
		return idAgent;
	}
	public void setIdAgent(int idAgent) {
		this.idAgent = idAgent;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getDateOuverture() {
		return dateOuverture;
	}
	public void setDateOuverture(String dateOuverture) {
		this.dateOuverture = dateOuverture;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDateCloture() {
		return dateCloture;
	}
	public void setDateCloture(String dateCloture) {
		this.dateCloture = dateCloture;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
	
}