package pl.coderslab.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "reservation")
public class Reservation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@OneToMany(mappedBy = "reservation", fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Set<URSlink> urs = new HashSet<URSlink>();

	private int nrOfSeats;
	
	private String seatCodes;
	
	private String seatDescribed;

	private String resNumber;
	
	private Boolean cancelled;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getNrOfSeats() {
		return nrOfSeats;
	}

	public void setNrOfSeats(int nrOfSeats) {
		this.nrOfSeats = nrOfSeats;
	}

	public String getSeatCodes() {
		return seatCodes;
	}

	public void setSeatCodes(String seatCodes) {
		this.seatCodes = seatCodes;
	}

	public Set<URSlink> getURS() {
		return urs;
	}

	public void setURS(Set<URSlink> urs) {
		this.urs = urs;
	}

	public String getResNumber() {
		return resNumber;
	}

	public void setResNumber(String resNUmber) {
		this.resNumber = resNUmber;
	}

	public Set<URSlink> getUrs() {
		return urs;
	}

	public void setUrs(Set<URSlink> urs) {
		this.urs = urs;
	}

	public Boolean isCancelled() {
		return cancelled;
	}

	public void setCancelled(Boolean cancelled) {
		this.cancelled = cancelled;
	}

	public String getSeatDescribed() {
		return seatDescribed;
	}

	public void setSeatDescribed(String seatDescribed) {
		this.seatDescribed = seatDescribed;
	}


	public Reservation() {

	}

}
