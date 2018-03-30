package co.simplon.springboot.simplecrud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.simplon.springboot.simplecrud.model.Agent;


public interface AgentRepository {

	
	List<Agent> getAllAgents() throws Exception;
	Agent getAgent(Long id) throws Exception;
	void deleteAgent(Long id) throws Exception;
	Agent insertAgent(Agent agent) throws Exception;
	Agent updateAgent(Agent agent) throws Exception;
	Agent getAgentByName(String nom) throws Exception;
	
	
}