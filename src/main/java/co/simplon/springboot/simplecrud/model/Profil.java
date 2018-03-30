package co.simplon.springboot.simplecrud.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "profil")
public class Profil implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5233533098364575443L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
	@NotNull
	private String libelle;
	
	@OneToMany(mappedBy="profil")
	@JsonIgnore
	private Set<Agent> agents;

	public Profil() {
		this.agents = new HashSet<>(); 
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public Set<Agent> getAgents() {
		return agents;
	}

	public void setAgents(Set<Agent> agents) {
		this.agents = agents;
	}
	
	public void addAgentToList(Agent agent) {
		this.getAgents().add(agent);
	}

	
	
	


	
//	@OneToMany(Cascade = CascadeType.ALL
//			fetch = FetchType.LAZY
//			mappedBy = "profil")
//	private List<Utilisateur> utilisateurs = new ArrayList<>();
	
	
	
}
