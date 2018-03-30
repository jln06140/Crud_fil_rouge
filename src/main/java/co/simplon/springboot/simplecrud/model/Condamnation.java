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
@Table(name="condamnation")
public class Condamnation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private Date date_condamnation;
	
	private String motif;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_suspect_cond")
	private Suspect suspect;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDateCondamnation() {
		return date_condamnation;
	}

	public void setDateCondamnation(Date dateCondamnation) {
		this.date_condamnation = dateCondamnation;
	}

	public String getMotif() {
		return motif;
	}

	public void setMotif(String motif) {
		this.motif = motif;
	}
	
	
	
}
