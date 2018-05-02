package co.simplon.springboot.simplecrud.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.springboot.simplecrud.model.Vehicule;
import co.simplon.springboot.simplecrud.service.VehiculeService;

/**
 * 
 * @author Alexandre NESIC
 * Classe qui servira à communiquer avec le front
 *
 */

@RestController
@RequestMapping("/api")
public class VehiculeController {
	
	@Autowired
	VehiculeService vehiculeService;
	
	@CrossOrigin
	@GetMapping("/vehicule")
	List<Vehicule> getAllVehicules() throws Exception{
		return vehiculeService.getAllVehicules();
	}
	
	
	@CrossOrigin
	@GetMapping("/vehicule/{id}")
	ResponseEntity<Vehicule> getVehiculeById(@PathVariable(value="id") long id) throws Exception {
	    Vehicule vehicule = vehiculeService.getVehicule(id);
	    if(vehicule == null) {
	        return ResponseEntity.notFound().build();
	    }
	    return ResponseEntity.ok().body(vehicule);
	}
	
	@CrossOrigin
	@PostMapping("/vehicule")
	Vehicule addVehicule(@Valid @RequestBody Vehicule vehicule) throws Exception{
		return this.vehiculeService.addVehicule(vehicule);
	}
	
	@CrossOrigin
	@PutMapping("/vehicule/{id}")
	ResponseEntity<Vehicule> updateUtilisateur(@PathVariable(value="id") long id, @Valid @RequestBody Vehicule vehicule) throws Exception{
		Vehicule VehiculeToUpdate = this.vehiculeService.getVehicule(id);
		if(VehiculeToUpdate == null)
			return ResponseEntity.notFound().build();
		
		// Update the mandatory attributes
		VehiculeToUpdate.setMarque(vehicule.getMarque());
		VehiculeToUpdate.setModele(vehicule.getModele());
		VehiculeToUpdate.setCouleur(vehicule.getCouleur());
		VehiculeToUpdate.setImmatriculation(vehicule.getImmatriculation());
		
		Vehicule updatedVehicule = this.vehiculeService.updateVehicule(VehiculeToUpdate);
		return ResponseEntity.ok(updatedVehicule);
	}
	
	@CrossOrigin
	@DeleteMapping("/vehicule/{id}")
	ResponseEntity<Vehicule> deleteVehicule(@PathVariable(value="id") long id) throws Exception{
		Vehicule vehicule = vehiculeService.getVehicule(id);
		if(vehicule == null)
			return ResponseEntity.notFound().build();
		
		vehiculeService.deleteVehicule(id);
		return ResponseEntity.ok().build();
	}
}
