package rva.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rva.model.Bolnica;
import rva.repository.BolnicaRepository;

@Service
public class BolnicaService {

	@Autowired
	private BolnicaRepository bolnicaRepository;//ne moramo da kreiramo objekat klase koja implementira interfejs

	public List<Bolnica> getAllBolnica(){ 
		return bolnicaRepository.findAll();
	}
	
	public Optional<Bolnica> getBolnicaById(int bolnicaId) {
		return bolnicaRepository.findById(bolnicaId);
	}
	
	public boolean existsById(int id) {
		return getBolnicaById(id).isPresent();
	}
	
	public List<Bolnica> getAllBolnicaByNaziv(String naziv){ 
		return bolnicaRepository.findByNazivContainingIgnoreCase(naziv);
	}
	
	public List<Bolnica> getBolnicaByPocetakNaziva(String pocetakNaziva){ 
		return bolnicaRepository.getBolnicaByPocetakNaziva(pocetakNaziva.toLowerCase());
	}
	
}
