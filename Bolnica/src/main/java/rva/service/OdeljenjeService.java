package rva.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rva.model.Bolnica;
import rva.model.Dijagnoza;
import rva.model.Odeljenje;
import rva.repository.OdeljenjeRepository;

@Service
public class OdeljenjeService {

	@Autowired
	private OdeljenjeRepository odeljenjeRepository;
	
	public List<Odeljenje> getAllOdeljenje(){ 
		return odeljenjeRepository.findAll();
	}
	
	public Optional<Odeljenje> getOdeljenjeById(int odeljenjeId) {
		return odeljenjeRepository.findById(odeljenjeId);
	}
	
	public boolean existsById(int id) {
		return getOdeljenjeById(id).isPresent();
	}
	
	public List<Odeljenje> getAllOdeljenjeByNaziv(String naziv){ 
		return odeljenjeRepository.findByNazivContainingIgnoreCase(naziv);
	}
	
	public List<Odeljenje> getOdeljenjaBolnice(Bolnica bolnica){ 
		return odeljenjeRepository.findByBolnica(bolnica);
	}

	public List<Odeljenje> getOdeljenjeByPocetakNaziva(String pocetakNaziva){ 
		return odeljenjeRepository.getOdeljenjeByPocetakNaziva(pocetakNaziva.toLowerCase());
	}
	
	public Odeljenje addOdeljenje(Odeljenje odeljenje) {
		return odeljenjeRepository.save(odeljenje);
	}
	
	public void deleteOdeljenjeById(int id) {
		odeljenjeRepository.deleteById(id);
	}
}
