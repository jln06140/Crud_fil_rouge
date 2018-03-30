package co.simplon.springboot.simplecrud.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="suspect")
@PrimaryKeyJoinColumn(name="id", referencedColumnName= "id")
public class Suspect extends Personne {
	
	@OneToMany(mappedBy="suspect")
	private List<Condamnation> condamnations;
	
	@OneToMany(mappedBy="suspect")
	private List<Document> Documents;
	//private List<Vehicule> Vehicules;
	
	
	
	public List<Document> getDocuments() {
		return Documents;
	}
	public List<Condamnation> getCondamnations() {
		return condamnations;
	}
	public void setCondamnations(List<Condamnation> condamnations) {
		this.condamnations = condamnations;
	}
	public void setDocuments(List<Document> documents) {
		Documents = documents;
	}
	
	
	

}
