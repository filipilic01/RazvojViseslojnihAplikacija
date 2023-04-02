package rva.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import rva.model.Bolnica;
import rva.model.Dijagnoza;
import rva.model.Odeljenje;

public interface OdeljenjeRepository extends JpaRepository<Odeljenje, Integer>{

	List<Odeljenje> findByNazivContainingIgnoreCase(String naziv);
	
	List<Odeljenje> findByBolnica(Bolnica bolnica);
	
	@Query(value= "select * from odeljenje where lower(naziv) like :pocetak%", nativeQuery= true)
	List<Odeljenje> getOdeljenjeByPocetakNaziva(@Param("pocetak")String pocetakNaziva);
	
}
