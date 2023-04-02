package rva.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rva.model.Bolnica;
import rva.model.Dijagnoza;
import rva.repository.DijagnozaRepository;

@Service
public class DijagnozaService {

	@Autowired
	private DijagnozaRepository dijagnozaRepository;
	
	public List<Dijagnoza> getAllDijagnoza(){ 
		return dijagnozaRepository.findAll();
	}
	
	public Optional<Dijagnoza> getDijagnozaById(int dijagnozaId) {
		return dijagnozaRepository.findById(dijagnozaId);
	}
	
	public boolean existsById(int id) {
		return getDijagnozaById(id).isPresent();
	}
	
	public List<Dijagnoza> getAllDijagnozaByNaziv(String naziv){ 
		return dijagnozaRepository.findByNazivContainingIgnoreCase(naziv);
	}
	
	public List<Dijagnoza> getAllDijagnozaByOznaka(String oznaka){ 
		return dijagnozaRepository.findByOznakaContainingIgnoreCase(oznaka);
	}
	
	public List<Dijagnoza> getDijagnozaByPocetakOznake(String pocetakOznake){ 
		return dijagnozaRepository.getDijagnozaByPocetakOznake(pocetakOznake.toLowerCase());
	}
	
	public Dijagnoza addDijagnoza(Dijagnoza dijagnoza) {
		return dijagnozaRepository.save(dijagnoza);
	}
	
	public void deleteDijagnozaById(int id) {
		dijagnozaRepository.deleteById(id);
	}
	
}
