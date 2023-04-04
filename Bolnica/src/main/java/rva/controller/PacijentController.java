package rva.controller;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
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
import rva.model.Pacijent;
import rva.service.DijagnozaService;
import rva.service.OdeljenjeService;
import rva.service.PacijentService;

@CrossOrigin
@RestController
public class PacijentController {
	
	@Autowired
	private PacijentService pacijentService;
	
	@Autowired 
	private OdeljenjeService odeljenjeService;
	
	@Autowired
	private DijagnozaService dijagnozaService;
	
	@Autowired
	private JdbcTemplate template;
	
	@GetMapping("/pacijent")
	public ResponseEntity<?> getAllPacijent(){
		List<Pacijent> pacijenti = pacijentService.getAllPacijent();
		if(pacijenti.isEmpty()) {
			return new ResponseEntity<>("Pacijenti - empty list", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(pacijenti, HttpStatus.OK);
		
	}
	
	@GetMapping("/pacijent/{id}")
	public ResponseEntity<?> getPacijentById(@PathVariable("id")int pacijentId){
		if(pacijentService.existsById(pacijentId)) {
			Optional<Pacijent> pacijent= pacijentService.getPacijentById(pacijentId);
			return ResponseEntity.ok(pacijent);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pacijent by id " + pacijentId + "not found");
		
	}
	
	@GetMapping("/pacijent/prezime/{prezime}")
	public ResponseEntity<?> getAllPacijentByPrezime(@PathVariable("prezime")String prezime){
		List<Pacijent> pacijenti = pacijentService.getAllPacijentByPrezime(prezime);
		if(pacijenti.isEmpty()) {
			return new ResponseEntity<>("Pacijenti - empty list", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(pacijenti, HttpStatus.OK);
		
	}
	/*
	@GetMapping("/imaOsiguranje")
	public ResponseEntity<List<Pacijent>> imaOsiguranje() {
		List<Pacijent> pacijenti = pacijentService.findByOsiguranjeTrue();
		return new ResponseEntity<>(pacijenti, HttpStatus.OK);
	}*/
	/*
	@GetMapping("/datumRodjenja/{datumRodjenja}")
	public ResponseEntity<?> getPacijentByBirthDateAfter(@PathVariable("datumRodjenja")Date datum){
		List<Pacijent> pacijenti = pacijentService.getPacijentByBirthDateAfter(datum);
		if(pacijenti.isEmpty()) {
			return new ResponseEntity<>("Pacijenti - empty list", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(pacijenti, HttpStatus.OK);
		
	}
	
*/
	@GetMapping("/odeljenjaPacijenata/{id}")
	public ResponseEntity<?> getOdeljenjaPacijenata(@PathVariable("id") int id){
		Optional<Odeljenje> odeljenje = odeljenjeService.getOdeljenjeById(id);
		if(odeljenje.isPresent()) {
			List<Pacijent> pacijenti = pacijentService.getOdeljenjaPacijenata(odeljenje.get());
			if(pacijenti.isEmpty()) {
				return new ResponseEntity<>("Pacijenti not found", HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(pacijenti, HttpStatus.OK);
		}
		return new ResponseEntity<>("Odeljenje not found", HttpStatus.NOT_FOUND);
		
		
	}
	
	@GetMapping("/dijagnozePacijenata/{id}")
	public ResponseEntity<?> getDijagnozePacijenata(@PathVariable("id") int id){
		Optional<Dijagnoza> dijagnoza = dijagnozaService.getDijagnozaById(id);
		if(dijagnoza.isPresent()) {
			List<Pacijent> pacijenti = pacijentService.getDijagnozePacijenata(dijagnoza.get());
			if(pacijenti.isEmpty()) {
				return new ResponseEntity<>("Pacijenti not found", HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(pacijenti, HttpStatus.OK);
		}
		return new ResponseEntity<>("Dijagnoza not found", HttpStatus.NOT_FOUND);
		
		
	}
	
	@PostMapping("/pacijent")
	public ResponseEntity<?> addPacijent(@RequestBody Pacijent pacijent){
		if(!odeljenjeService.existsById(pacijent.getOdeljenje().getId()))
		{
			return new ResponseEntity<>("Odeljenje with id " + pacijent.getOdeljenje().getId() + " not found",HttpStatus.NOT_FOUND);
		}
		else {
		if(pacijentService.existsById(pacijent.getId())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Pacijent with id " + pacijent.getId() + " already exists");
		}
		else {
			
		Pacijent savedPacijent = pacijentService.addPacijent(pacijent);
		return new ResponseEntity<>(savedPacijent,HttpStatus.OK);
		}
		}
	}
	
	@PutMapping("/pacijent/{id}")
	public ResponseEntity<?> putPacijent(@RequestBody Pacijent pacijent, @PathVariable("id") int id){
		if(!odeljenjeService.existsById(pacijent.getOdeljenje().getId()))
		{
			return new ResponseEntity<>("Odeljenje with id " + pacijent.getOdeljenje().getId() + " not found",HttpStatus.NOT_FOUND);
		}
		else {
		if(!pacijentService.existsById(pacijent.getId())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Pacijent with id " + pacijent.getId() + " not found");
		}
		else {
			pacijent.setId(id);
			Pacijent updatedPacijent = pacijentService.addPacijent(pacijent);
			return new ResponseEntity<>(updatedPacijent,HttpStatus.OK);
		}
		}
	}
	
	@DeleteMapping("/pacijent/{id}")
	public ResponseEntity<?> deletePacijent(@PathVariable("id")int pacijentId)
	{
		if(!pacijentService.existsById(pacijentId)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pacijent with id " + pacijentId + " not found");
		}
		else {
			if(pacijentId==-100) {
				pacijentService.deletePacijentById(pacijentId);
				template.execute("INSERT INTO \"pacijent\"(\"id\", \"ime\", \"prezime\", \"zdr_osiguranje\", \"datum_rodjenja\", \"odeljenje\", \"dijagnoza\") values (-100, 'Test', 'Test', 'TRUE', '1900-05-24', '1', '1')");
				return new ResponseEntity<>("Pacijent with id " + pacijentId + " has been deleted.", HttpStatus.OK);
			}
			else {
				pacijentService.deletePacijentById(pacijentId);
				return new ResponseEntity<>("Pacijent with id " + pacijentId + " has been deleted.", HttpStatus.OK);
			}
		}
	}
	
	
}
