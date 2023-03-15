package rva.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Bolnica implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BOLNICA_ID_GENERATOR")
	@SequenceGenerator(name = "BOLNICA_ID_GENERATOR", sequenceName = "BOLNICA_SEQ", allocationSize = 1)
	private int id;
	
	private String naziv;
	
	private String adresa;
	
	private double budzet;
	
	@JsonIgnore
	@OneToMany(mappedBy = "bolnica")
	private List<Odeljenje> odeljenja;

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

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public double getBudzet() {
		return budzet;
	}

	public void setBudzet(double budzet) {
		this.budzet = budzet;
	}

	public List<Odeljenje> getOdeljenja() {
		return odeljenja;
	}

	public void setOdeljenja(List<Odeljenje> odeljenja) {
		this.odeljenja = odeljenja;
	}
	
	

}
