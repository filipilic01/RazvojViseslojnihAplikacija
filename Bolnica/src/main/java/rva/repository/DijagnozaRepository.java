package rva.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import rva.model.Bolnica;
import rva.model.Dijagnoza;

public interface DijagnozaRepository extends JpaRepository<Dijagnoza, Integer> {

	List<Dijagnoza> findByNazivContainingIgnoreCase(String naziv);
	
	List<Dijagnoza> findByOznakaContainingIgnoreCase(String oznaka);
	
	@Query(value= "select * from dijagnoza where lower(oznaka) like :pocetak%", nativeQuery= true)
	List<Dijagnoza> getDijagnozaByPocetakOznake(@Param("pocetak")String pocetakOznake);
	
}
