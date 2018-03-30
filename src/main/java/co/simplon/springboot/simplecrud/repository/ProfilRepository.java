package co.simplon.springboot.simplecrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.simplon.springboot.simplecrud.model.Profil;

public interface ProfilRepository extends JpaRepository<Profil, Integer> {
	
}
