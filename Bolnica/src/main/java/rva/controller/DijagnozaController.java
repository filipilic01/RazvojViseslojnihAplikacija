package rva.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.BatchProperties.Jdbc;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import rva.model.Bolnica;
import rva.model.Dijagnoza;
import rva.service.DijagnozaService;

@RestController
public class DijagnozaController {
	@Autowired
	private DijagnozaService dijagnozaService;
	@Autowired
	private JdbcTemplate template;
	
	@GetMapping("/dijagnoza")
	public ResponseEntity<?> getAllDijagnoza(){
		List<Dijagnoza> dijagnoze = dijagnozaService.getAllDijagnoza();
		if(dijagnoze.isEmpty()) {
			return new ResponseEntity<>("Dijagnoze - empty list", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(dijagnoze, HttpStatus.OK);
		
	}
	
	@GetMapping("/dijagnoza/{id}")
	public ResponseEntity<?> getDijagnozaById(@PathVariable("id")int dijagnozaId){
		if(dijagnozaService.existsById(dijagnozaId)) {
			Optional<Dijagnoza> dijagnoza= dijagnozaService.getDijagnozaById(dijagnozaId);
			return ResponseEntity.ok(dijagnoza);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Dijagnoza by id " + dijagnozaId + "not found");
		
	}
	
	@GetMapping("/dijagnoza/naziv/{naziv}")
	public ResponseEntity<?> getAllDijagnozaByNaziv(@PathVariable("naziv")String naziv){
		List<Dijagnoza> dijagnoze = dijagnozaService.getAllDijagnozaByNaziv(naziv);
		if(dijagnoze.isEmpty()) {
			return new ResponseEntity<>("Dijagnoze - empty list", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(dijagnoze, HttpStatus.OK);
		
	}
	
	@GetMapping("/dijagnoza/oznaka/{oznaka}")
	public ResponseEntity<?> getAllDijagnozaByOznaka(@PathVariable("oznaka")String oznaka){
		List<Dijagnoza> dijagnoze = dijagnozaService.getAllDijagnozaByOznaka(oznaka);
		if(dijagnoze.isEmpty()) {
			return new ResponseEntity<>("Dijagnoze - empty list", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(dijagnoze, HttpStatus.OK);
		
	}
	
	@GetMapping("/dijagnoza/pocetakOznake/{pocetakOznake}")
	public ResponseEntity<?> getDijagnozaByPocetakOznake(@PathVariable("pocetakOznake")String pocetakOznake){
		List<Dijagnoza> dijagnoze = dijagnozaService.getDijagnozaByPocetakOznake(pocetakOznake);
		if(dijagnoze.isEmpty()) {
			return new ResponseEntity<>("Dijagnoze - empty list", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(dijagnoze, HttpStatus.OK);
		
	}
	
	@PostMapping("/dijagnoza")
	public ResponseEntity<?> postDijagnoza(@RequestBody Dijagnoza dijagnoza){
		if(dijagnozaService.existsById(dijagnoza.getId())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Dijagnoza with id " + dijagnoza.getId() + " already exists");
		}
		
		Dijagnoza savedDijagnoza= dijagnozaService.addDijagnoza(dijagnoza);
		return new ResponseEntity<>(savedDijagnoza, HttpStatus.OK);
		
	}
	
	@PutMapping("/dijagnoza/{id}")
	public ResponseEntity<?> putDijagnoza(@PathVariable("id")int dijagnozaId,@RequestBody Dijagnoza dijagnoza){
		if(!dijagnozaService.existsById(dijagnoza.getId())) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Dijagnoza with id " + dijagnoza.getId() + " not found");
		}
		
		dijagnoza.setId(dijagnozaId);
		Dijagnoza updatedDijagnoza =dijagnozaService.addDijagnoza(dijagnoza);
		return new ResponseEntity<>(updatedDijagnoza, HttpStatus.OK);
		
	}
	
	@DeleteMapping("/dijagnoza/{id}")
	public ResponseEntity<?> deleteDijagnoza(@PathVariable("id")int dijagnozaId)
	{
		if(!dijagnozaService.existsById(dijagnozaId)) {
			
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Dijagnoza with id " + dijagnozaId + " not found");
		}
		else {
			if(dijagnozaId==-100) {
				dijagnozaService.deleteDijagnozaById(dijagnozaId);
				template.execute("INSERT INTO \"dijagnoza\"(\"id\", \"naziv\", \"opis\", \"oznaka\") values (-100, 'Test', 'Test', 'Test')");
				return new ResponseEntity<>("Dijagnoza with id " + dijagnozaId + " has been deleted.", HttpStatus.OK);
			}
			else {
				dijagnozaService.deleteDijagnozaById(dijagnozaId);
				return new ResponseEntity<>("Dijagnoza with id " + dijagnozaId + " has been deleted.", HttpStatus.OK);
			}
		}
	}

	

}
