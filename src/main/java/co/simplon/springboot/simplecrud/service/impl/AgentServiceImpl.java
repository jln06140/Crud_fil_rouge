package co.simplon.springboot.simplecrud.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.simplon.springboot.simplecrud.model.Agent;
import co.simplon.springboot.simplecrud.repository.AgentRepository;
import co.simplon.springboot.simplecrud.service.AgentService;

@Service
public class AgentServiceImpl implements AgentService{

	@Autowired
	private AgentRepository agentRepository;
	
	
	public List<Agent> getAllAgents() throws Exception{
		return this.agentRepository.getAllAgents();
	}
	
	public void deleteAgent(Long id) throws Exception {
		this.agentRepository.deleteAgent(id);
	}
	
	public Agent getAgent(Long id) throws Exception {
		return this.agentRepository.getAgent(id);
	}

	@Override
	public Agent addAgent(Agent agent) throws Exception {
		
		return this.agentRepository.insertAgent(agent);
	}

	@Override
	public Agent updateAgent(Agent agent) throws Exception {
		return this.agentRepository.updateAgent(agent);
	}

	@Override
	public Agent getAgentByName(String nom) throws Exception {
		return this.agentRepository.getAgentByName(nom);
	}
}
