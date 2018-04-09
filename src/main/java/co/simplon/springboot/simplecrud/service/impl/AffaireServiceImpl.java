package co.simplon.springboot.simplecrud.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.simplon.springboot.simplecrud.model.Affaire;
import co.simplon.springboot.simplecrud.repository.AffaireRepository;
import co.simplon.springboot.simplecrud.service.AffaireService;

@Service
public class AffaireServiceImpl implements AffaireService{

	@Autowired
	private AffaireRepository affaireRepository;
	
	// sql : "SELECT * FROM affaire"
	public List<Affaire> getAllAffaires(){
		return this.affaireRepository.findAll();
	}
	
	// sql: "DELETE FROM affaire WHERE id = " + affaire.getId();
	public void deleteAffaire(Affaire affaire) {
		this.affaireRepository.delete(affaire);
	}
	
	// sql: "SELECT FROM affaire WHERE id = " + affaire.getId();
	public Affaire getAffaire(Long id) {
		return this.affaireRepository.findOne(id);
	}

	// sql: "ALTER TABLE 
	public Affaire addAffaire(Affaire affaire) {
		return this.affaireRepository.save(affaire);
	}
	
	public Affaire saveAffaire(Affaire affaire) {
		return this.affaireRepository.save(affaire);
	}
}
