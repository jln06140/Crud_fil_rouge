package co.simplon.springboot.simplecrud.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.simplon.springboot.simplecrud.model.Suspect;
import co.simplon.springboot.simplecrud.repository.SuspectRepository;
import co.simplon.springboot.simplecrud.service.SuspectService;

@Service
public class SuspectServicImp implements SuspectService {

	@Autowired
	private SuspectRepository suspectRepository;
	
	@Override
	public List<Suspect> getAllSuspects() {
		return this.suspectRepository.findAll();
	}

	@Override
	public Suspect getSuspect(long id) {
		return this.suspectRepository.findOne(id);
	}

	@Override
	public void deleteSuspect(Suspect suspect) {
		this.suspectRepository.delete(suspect);
		
	}

}
