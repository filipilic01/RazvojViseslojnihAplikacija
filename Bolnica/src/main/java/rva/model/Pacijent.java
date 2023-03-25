package rva.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Pacijent implements Serializable{

private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PACIJENT_ID_GENERATOR")
	@SequenceGenerator(name = "PACIJENT_ID_GENERATOR", sequenceName = "PACIJENT_SEQ", allocationSize = 1)
	private int id;
	
	private String ime;
	
	private String prezime;
	
	private boolean zdrOsiguranje;
	
	private Date datumRodjenja;
	
	@ManyToOne
	@JoinColumn(name = "odeljenje")
	private Odeljenje odeljenje;
	
	@ManyToOne
	@JoinColumn(name = "dijagnoza")
	private Dijagnoza dijagnoza;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public boolean isZdrOsiguranje() {
		return zdrOsiguranje;
	}

	public void setZdrOsiguranje(boolean zdrOsiguranje) {
		this.zdrOsiguranje = zdrOsiguranje;
	}

	public Date getDatumRodjenja() {
		return datumRodjenja;
	}

	public void setDatumRodjenja(Date datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}

	public Odeljenje getOdeljenje() {
		return odeljenje;
	}

	public void setOdeljenje(Odeljenje odeljenje) {
		this.odeljenje = odeljenje;
	}

	public Dijagnoza getDijagnoza() {
		return dijagnoza;
	}

	public void setDijagnoza(Dijagnoza dijagnoza) {
		this.dijagnoza = dijagnoza;
	}
	
	
}
