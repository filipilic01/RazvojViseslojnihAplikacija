package rva.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import rva.model.Bolnica;

public interface BolnicaRepository extends JpaRepository<Bolnica, Integer> {

	List<Bolnica> findByNazivContainingIgnoreCase(String naziv);
	
	@Query(value= "select * from bolnica where lower(naziv) like :pocetak%", nativeQuery= true)
	List<Bolnica> getBolnicaByPocetakNaziva(@Param("pocetak")String pocetakNaziva);
} 
