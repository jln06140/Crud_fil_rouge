package co.simplon.springboot.simplecrud.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="document")
public class Document {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String type;
	private String numero;
	private Date date_emission;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_suspect")
	private Suspect suspect;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public Date getDate_emission() {
		return date_emission;
	}
	public void setDate_emission(Date date_emission) {
		this.date_emission = date_emission;
	}
	
	
}
