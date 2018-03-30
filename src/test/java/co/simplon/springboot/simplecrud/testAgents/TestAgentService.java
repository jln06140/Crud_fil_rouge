package co.simplon.springboot.simplecrud.testAgents;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import co.simplon.springboot.simplecrud.model.Agent;
import co.simplon.springboot.simplecrud.repository.AgentRepository;
import co.simplon.springboot.simplecrud.service.AgentService;
import co.simplon.springboot.simplecrud.service.impl.AgentServiceImpl;

/*
 * Classe de test sur les services Agent en utilisant un mock
 */

@RunWith(SpringRunner.class)
@DataJpaTest
public class TestAgentService {
	 @TestConfiguration
	    static class AgentServiceImplTestContextConfiguration {
	  
	        @Bean
	        public AgentService agentService() {
	            return new AgentServiceImpl();
	        }
	    }
	
	 @Autowired
	 private AgentService agentService;
	
	@MockBean
	private AgentRepository agentRepository;
	
	
	@Before
	public void setUp() throws Exception {
	    Agent alex = new Agent();
	    alex.setNom("alex");
	 
	    Mockito.when(agentRepository.getAgentByName(alex.getNom()))
	      .thenReturn(alex);
	}
	
	@Test
	public void whenValidName_thenAgentShouldBeFound() throws Exception {
	    String name = "alex";
	    Agent found = agentService.getAgentByName(name);
	  
	     assertThat(found.getNom())
	      .isEqualTo(name);
	 }

}
