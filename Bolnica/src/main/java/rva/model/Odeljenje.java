package rva.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Odeljenje implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ODELJENJE_ID_GENERATOR")
	@SequenceGenerator(name = "ODELJENJE_ID_GENERATOR", sequenceName = "ODELJENJE_SEQ", allocationSize = 1)
	private int id;
	
	private String naziv;
	
	private String lokacija;
	
	@ManyToOne
	@JoinColumn(name = "bolnica")
	private Bolnica bolnica;
	
	@JsonIgnore
	@OneToMany(mappedBy = "odeljenje")
	private List<Pacijent> pacijenti;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getLokacija() {
		return lokacija;
	}

	public void setLokacija(String lokacija) {
		this.lokacija = lokacija;
	}

	public Bolnica getBolnica() {
		return bolnica;
	}

	public void setBolnica(Bolnica bolnica) {
		this.bolnica = bolnica;
	}

	public List<Pacijent> getPacijenti() {
		return pacijenti;
	}

	public void setPacijenti(List<Pacijent> pacijenti) {
		this.pacijenti = pacijenti;
	}

	


}
