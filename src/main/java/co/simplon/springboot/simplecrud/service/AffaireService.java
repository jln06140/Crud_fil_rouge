package co.simplon.springboot.simplecrud.service;

import java.util.List;

import co.simplon.springboot.simplecrud.model.Affaire;

public interface AffaireService {

	List<Affaire> getAllAffaires();
	void deleteAffaire(Affaire affaire);
	Affaire getAffaire(Long id);
	Affaire addAffaire(Affaire affaire);
	Affaire saveAffaire(Affaire affaire); 
}
