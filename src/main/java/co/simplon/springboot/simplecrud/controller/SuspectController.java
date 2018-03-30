package co.simplon.springboot.simplecrud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.springboot.simplecrud.model.Suspect;
import co.simplon.springboot.simplecrud.service.SuspectService;

@RestController
@RequestMapping("/api")
public class SuspectController {

	@Autowired
	private SuspectService suspectService;
	
	@CrossOrigin
	@GetMapping("/suspect")
	List<Suspect> getAllSuspect(){
		return this.suspectService.getAllSuspects();
	}
	
	@CrossOrigin
	@GetMapping("/suspect/{id}")
	ResponseEntity<Suspect> getSuspectById(@PathVariable(value="id") long id){
		Suspect suspect = this.suspectService.getSuspect(id);
		if(suspect == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(suspect);
	}
}
