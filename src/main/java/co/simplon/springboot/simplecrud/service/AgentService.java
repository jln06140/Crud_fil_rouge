package co.simplon.springboot.simplecrud.service;

import java.util.List;

import co.simplon.springboot.simplecrud.model.Agent;

public interface AgentService {

	List<Agent> getAllAgents() throws Exception;
	void deleteAgent(Long id)throws Exception;
	Agent getAgent(Long id) throws Exception;
	Agent addAgent(Agent agent) throws Exception;
	Agent updateAgent(Agent angent) throws Exception;
	Agent getAgentByName(String nom) throws Exception;
}
