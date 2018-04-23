package co.simplon.springboot.simplecrud.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "agent")
@JsonIgnoreProperties(ignoreUnknown = true)
@PrimaryKeyJoinColumn(name="id", referencedColumnName= "id")
public class Agent extends Personne implements Serializable{
	


	/**
	 * Model Agent qui herite du model personne
	 * et qui ajoute un email et un mot de passe pour se connecter
	 * ainsi qu'un profil afin de restreindre les droits
	 */
	private static final long serialVersionUID = -7492656493898251454L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Email
	private String email;
	
	private String motdepasse;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_profil")
	private Profil profil;
	
	
	
	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getMotdepasse() {
		return motdepasse;
	}
	
	public void setMotdepasse(String motdepasse) {
		this.motdepasse = motdepasse;
	}

	public Profil getProfil() {
		return profil;
	}

	public void setProfil(Profil profil) {
		this.profil = profil;
	}

	
	
	
	
}