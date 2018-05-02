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

import co.simplon.springboot.simplecrud.model.Agent;
import co.simplon.springboot.simplecrud.model.Profil;
import co.simplon.springboot.simplecrud.repository.AgentRepository;
import co.simplon.springboot.simplecrud.repository.ProfilRepository;
import co.simplon.springboot.simplecrud.service.AgentService;

@RestController
@RequestMapping("/api")
public class AgentController {
	
	@Autowired
	AgentService AgentService;
	
	@Autowired
	ProfilRepository profilRepository;
	
	@CrossOrigin
	@GetMapping("/agent")
	List<Agent> getAllAgents() throws Exception{
		return AgentService.getAllAgents();
	}
	
	
	@CrossOrigin
	@GetMapping("/agent/{id}")
	ResponseEntity<Agent> getAgentById(@PathVariable(value="id") long id) throws Exception {
	    Agent agent = AgentService.getAgent(id);
	    if(agent == null) {
	        return ResponseEntity.notFound().build();
	    }
	    return ResponseEntity.ok().body(agent);
	}
	
	@CrossOrigin
	@PostMapping("/agent")
	Agent addAgent(@Valid @RequestBody Agent agent) throws Exception{
		return this.AgentService.addAgent(agent);
	}
	
	@CrossOrigin
	@PutMapping("/agent/{id}")
	ResponseEntity<Agent> updateAgent(@PathVariable(value="id") long id, @Valid @RequestBody Agent agent) throws Exception{
		Agent AgentToUpdate = this.AgentService.getAgent(id);
		if(AgentToUpdate == null)
			return ResponseEntity.notFound().build();
		
		// Update the mandatory attributes
		AgentToUpdate.setPrenom(agent.getPrenom());
		AgentToUpdate.setNom(agent.getNom());
		AgentToUpdate.setAdresse(agent.getAdresse());
		AgentToUpdate.setEmail(agent.getEmail());
		AgentToUpdate.setVille(agent.getVille());
		
		
		if(agent.getEmail() != null) {
			AgentToUpdate.setEmail(agent.getEmail());
			AgentToUpdate.setMotdepasse(agent.getMotdepasse());
			//Profil p = this.profilRepository.findByLibelle(agent.getProfil().getLibelle()).get(0);
			Profil p = this.profilRepository.findAll().stream().filter((res)-> res.getLibelle().toUpperCase().equals(agent.getProfil().getLibelle())).findAny().get();
			AgentToUpdate.setProfil(p);
		}
		Agent updatedAgent = this.AgentService.updateAgent(AgentToUpdate);
		return ResponseEntity.ok(updatedAgent);
	}
	
	@CrossOrigin
	@DeleteMapping("/agent/{id}")
	ResponseEntity<Agent> deleteAgent(@PathVariable(value="id") long id) throws Exception{
		Agent agent = AgentService.getAgent(id);
		if(agent == null)
			return ResponseEntity.notFound().build();
		
		AgentService.deleteAgent(id);
		return ResponseEntity.ok().build();
	}
}
