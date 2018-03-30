package co.simplon.springboot.simplecrud.service;

import java.util.List;

import co.simplon.springboot.simplecrud.model.Suspect;

public interface SuspectService {
	
	List<Suspect> getAllSuspects();
	Suspect getSuspect (long id);
	void deleteSuspect (Suspect suspect);

}
