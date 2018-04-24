package co.simplon.springboot.simplecrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.simplon.springboot.simplecrud.model.Profil;
import java.lang.String;
import java.util.List;

public interface ProfilRepository extends JpaRepository<Profil, Integer> {
	
	List<Profil> findByLibelle(String libelle);

	
}
