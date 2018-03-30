package co.simplon.springboot.simplecrud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.springboot.simplecrud.model.Profil;
import co.simplon.springboot.simplecrud.repository.ProfilRepository;

@RestController
@RequestMapping("/api")
public class ProfilController {
	
	@Autowired
	ProfilRepository profilRepo;
	
	@CrossOrigin
	@GetMapping("/profil")
	List<Profil> getAllProfils(){
		return profilRepo.findAll();
	}
	
	@CrossOrigin
	@GetMapping("/profil/{libelle}")
	ResponseEntity<Profil> getProfilByLibelle(@PathVariable(value="libelle") String libelle){
		List<Profil> listeProfils = profilRepo.findAll();
		if(listeProfils.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		Profil profilRes = listeProfils.stream().filter((profil)-> libelle.toUpperCase().equals(profil.getLibelle())).findAny().get();
		if(profilRes != null) {
			return ResponseEntity.ok().body(profilRes);
		}
		return null;
	}
	
}
