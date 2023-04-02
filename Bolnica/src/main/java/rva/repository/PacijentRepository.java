package rva.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import rva.model.Bolnica;
import rva.model.Dijagnoza;
import rva.model.Odeljenje;
import rva.model.Pacijent;

public interface PacijentRepository extends JpaRepository<Pacijent, Integer> {

	List<Pacijent> findByPrezimeContainingIgnoreCase(String prz);
	
	//List<Pacijent> findByBirthDateAfter(Date datum);
	List<Pacijent> findByOdeljenje(Odeljenje odeljenje);
	
	List<Pacijent> findByDijagnoza(Dijagnoza dijagnoza);
	
	//List<Pacijent> findByOsiguranjeTrue();
	
}
