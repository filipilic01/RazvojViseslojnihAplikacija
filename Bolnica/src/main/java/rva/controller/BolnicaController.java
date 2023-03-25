package rva.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import rva.model.Bolnica;
import rva.service.BolnicaService;

@RestController
public class BolnicaController {
	@Autowired
	private BolnicaService bolnicaService;
	
	
	@GetMapping("/bolnica")
	public ResponseEntity<?> getAllBolnica(){
		List<Bolnica> bolnice = bolnicaService.getAllBolnica();
		if(bolnice.isEmpty()) {
			return new ResponseEntity<>("Bolnice - empty list", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(bolnice, HttpStatus.OK);
		
	}
	
	@GetMapping("/bolnica/{id}")
	public ResponseEntity<?> getBolnicaById(@PathVariable("id")int bolnicaId){
		if(bolnicaService.existsById(bolnicaId)) {
			Optional<Bolnica> bolnica = bolnicaService.getBolnicaById(bolnicaId);
			return ResponseEntity.ok(bolnica);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Bolnica by id " + bolnicaId + "not found");
		
	}
	
	@GetMapping("/bolnica/naziv/{naziv}")
	public ResponseEntity<?> getAllBolnicaByNaziv(@PathVariable("naziv")String naziv){
		List<Bolnica> bolnice = bolnicaService.getAllBolnicaByNaziv(naziv);
		if(bolnice.isEmpty()) {
			return new ResponseEntity<>("Bolnice - empty list", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(bolnice, HttpStatus.OK);
		
	}
	
	@GetMapping("/bolnica/pocetakNaziva/{pocetakNaziva}")
	public ResponseEntity<?> getBolnicaByPocetakNaziva(@PathVariable("pocetakNaziva")String pocetakNaziva){
		List<Bolnica> bolnice = bolnicaService.getBolnicaByPocetakNaziva(pocetakNaziva);
		if(bolnice.isEmpty()) {
			return new ResponseEntity<>("Bolnice - empty list", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(bolnice, HttpStatus.OK);
		
	}
	
	
}
