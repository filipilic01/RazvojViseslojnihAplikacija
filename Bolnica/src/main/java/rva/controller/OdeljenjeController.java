package rva.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import rva.model.Bolnica;
import rva.model.Dijagnoza;
import rva.model.Odeljenje;
import rva.service.BolnicaService;
import rva.service.OdeljenjeService;

@CrossOrigin
@RestController
public class OdeljenjeController {

	@Autowired
	private OdeljenjeService odeljenjeService;
	
	@Autowired
	private BolnicaService bolnicaService;
	
	
	@GetMapping("/odeljenje")
	public ResponseEntity<?> getAllOdeljenje(){
		List<Odeljenje> odeljenja = odeljenjeService.getAllOdeljenje();
		if(odeljenja.isEmpty()) {
			return new ResponseEntity<>("Odeljenja - empty list", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(odeljenja, HttpStatus.OK);
		
	}
	
	@GetMapping("/odeljenje/{id}")
	public ResponseEntity<?> getOdeljenjeById(@PathVariable("id")int odeljenjeId){
		if(odeljenjeService.existsById(odeljenjeId)) {
			Optional<Odeljenje> odeljenje= odeljenjeService.getOdeljenjeById(odeljenjeId);
			return ResponseEntity.ok(odeljenje);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Odeljenje by id " + odeljenjeId + " not found");
		
	}
	
	@GetMapping("/odeljenje/naziv/{naziv}")
	public ResponseEntity<?> getAllOdeljenjeByNaziv(@PathVariable("naziv")String naziv){
		List<Odeljenje> odeljenja = odeljenjeService.getAllOdeljenjeByNaziv(naziv);
		if(odeljenja.isEmpty()) {
			return new ResponseEntity<>("Odeljenja - empty list", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(odeljenja, HttpStatus.OK);
		
	}
	
	@GetMapping("/odeljenaBolnice/{id}")
	public ResponseEntity<?> getOdeljenjaBolnice(@PathVariable("id") int id){
		Optional<Bolnica> bolnica = bolnicaService.getBolnicaById(id);
		if(bolnica.isPresent()) {
			List<Odeljenje> odeljenja = odeljenjeService.getOdeljenjaBolnice(bolnica.get());
			if(odeljenja.isEmpty()) {
				return new ResponseEntity<>("Odeljenja not found", HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(odeljenja, HttpStatus.OK);
		}
		return new ResponseEntity<>("Bolnica not found", HttpStatus.NOT_FOUND);
		
		
	}

	@GetMapping("/odeljenje/pocetakNaziva/{pocetakNaziva}")
	public ResponseEntity<?> getOdeljenjeByPocetakNaziva(@PathVariable("pocetakNaziva")String pocetakNaziva){
		List<Odeljenje> odeljenja = odeljenjeService.getOdeljenjeByPocetakNaziva(pocetakNaziva);
		if(odeljenja.isEmpty()) {
			return new ResponseEntity<>("Odeljenja - empty list", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(odeljenja, HttpStatus.OK);
		
	}
	
	@PostMapping("/odeljenje")
	public ResponseEntity<?> addOdeljenje(@RequestBody Odeljenje odeljenje){
		if(!bolnicaService.existsById(odeljenje.getBolnica().getId()))
		{
			return new ResponseEntity<>("Bolnica with id " + odeljenje.getBolnica().getId() + " not found",HttpStatus.NOT_FOUND);
		}
		else {
		if(odeljenjeService.existsById(odeljenje.getId())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Odeljenje with id " + odeljenje.getId() + " already exists");
		}
		else {
		Odeljenje savedOdeljenje = odeljenjeService.addOdeljenje(odeljenje);
		return new ResponseEntity<>(savedOdeljenje,HttpStatus.OK);
		}
		}
	}

	@PutMapping("/odeljenje/{id}")
	public ResponseEntity<?> putOdeljenje(@RequestBody Odeljenje odeljenje, @PathVariable("id") int id){
		if(!bolnicaService.existsById(odeljenje.getBolnica().getId()))
		{
			return new ResponseEntity<>("Bolnica with id " + odeljenje.getBolnica().getId() + " not found",HttpStatus.NOT_FOUND);
		}
		else {
		if(!odeljenjeService.existsById(odeljenje.getId())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Odeljenje with id " + odeljenje.getId() + " not found");
		}
		else {
			odeljenje.setId(id);
			Odeljenje updatedOdeljenje = odeljenjeService.addOdeljenje(odeljenje);
			return new ResponseEntity<>(updatedOdeljenje,HttpStatus.OK);
		}
		}
	}
	
	@DeleteMapping("/odeljenje/{id}")
	public ResponseEntity<?> deleteOdeljenje(@PathVariable("id")int id)
	{
		if(!odeljenjeService.existsById(id)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Odeljenje with id " + id + " not found");
		}
		odeljenjeService.deleteOdeljenjeById(id);
		return new ResponseEntity<>("Odeljenje with id " + id + " has been deleted.", HttpStatus.OK);
	}
}
