package rva.service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rva.model.Bolnica;
import rva.model.Dijagnoza;
import rva.model.Odeljenje;
import rva.model.Pacijent;
import rva.repository.PacijentRepository;

@Service
public class PacijentService {

	@Autowired
	private PacijentRepository pacijentRepository;
	
	public List<Pacijent> getAllPacijent(){ 
		return pacijentRepository.findAll();
	}
	
	public Optional<Pacijent> getPacijentById(int pacijentId) {
		return pacijentRepository.findById(pacijentId);
	}
	
	public boolean existsById(int id) {
		return getPacijentById(id).isPresent();
	}
	
	public List<Pacijent> getAllPacijentByPrezime(String prz){ 
		return pacijentRepository.findByPrezimeContainingIgnoreCase(prz);
	}
	
	public List<Pacijent> getOdeljenjaPacijenata(Odeljenje odeljenje){ 
		return pacijentRepository.findByOdeljenje(odeljenje);
	}

	public List<Pacijent> getDijagnozePacijenata(Dijagnoza dijagnoza){ 
		return pacijentRepository.findByDijagnoza(dijagnoza);
	}
	/*
	public List<Pacijent> findByOsiguranjeTrue() {
        return pacijentRepository.findByOsiguranjeTrue();
    }
	*/
	/*public List<Pacijent> getPacijentByBirthDateAfter(Date datum){ 
		return pacijentRepository.findByBirthDateAfter(datum);
	}*/
	public Pacijent addPacijent(Pacijent pacijent) {
		return pacijentRepository.save(pacijent);
	}
	
	public void deletePacijentById(int id) {
		pacijentRepository.deleteById(id);
	}
}
