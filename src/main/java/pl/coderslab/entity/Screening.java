package pl.coderslab.entity;

import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "screening")
public class Screening {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private Timestamp timestamp;

	private String timestampFormatted;

	@ManyToOne
	private Film film;

	@ManyToOne
	private Cinema cinema;

	@OneToMany(mappedBy = "screening", fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Set<URSlink> urs = new HashSet<URSlink>();

	private int booked;
	
	private double price;

	public int getBooked() {
		return booked;
	}

	public void setBooked(int booked) {
		this.booked = booked;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
		LocalDateTime ldt = timestamp.toLocalDateTime();
		int day = ldt.getDayOfMonth();
		DayOfWeek dayOfWeek = ldt.getDayOfWeek();
		int dayValue = dayOfWeek.getValue();
		String dayString = "";
		switch (dayValue) {
		case 1:
			dayString = "Poniedziałek";
			break;
		case 2:
			dayString = "Wtorek";
			break;
		case 3:
			dayString = "Środa";
			break;
		case 4:
			dayString = "Czwartek";
			break;
		case 5:
			dayString = "Piątek";
			break;
		case 6:
			dayString = "Sobota";
			break;
		case 7:
			dayString = "Niedziela";
			break;
		}
		int month = ldt.getMonthValue();
		String monthString = "";
		switch (month) {
		case 1:
			monthString = "stycznia";
			break;
		case 2:
			monthString = "lutego";
			break;
		case 3:
			monthString = "marca";
			break;
		case 4:
			monthString = "kwietnia";
			break;
		case 5:
			monthString = "maja";
			break;
		case 6:
			monthString = "czerwca";
			break;
		case 7:
			monthString = "lipca";
			break;
		case 8:
			monthString = "sierpnia";
			break;
		case 9:
			monthString = "września";
			break;
		case 10:
			monthString = "października";
			break;
		case 11:
			monthString = "listopada";
			break;
		case 12:
			monthString = "grudnia";
			break;
		}
		int year = ldt.getYear();
		int hour = ldt.getHour();
		int minutes = ldt.getMinute();
		if (minutes < 10) {
			this.timestampFormatted = dayString + ", " + day + ". " + monthString + " " + year + " godz. " + hour
					+ ":0" + minutes;
		}
		else {
			this.timestampFormatted = dayString + ", " + day + ". " + monthString + " " + year + " godz. " + hour + ":"
					+ minutes;
		}
	}

	public String getTimestampFormatted() {
		return timestampFormatted;
	}

	public Cinema getCinema() {
		return cinema;
	}

	public void setCinema(Cinema cinema) {
		this.cinema = cinema;
	}

	public Set<URSlink> getURS() {
		return urs;
	}

	public void setURS(Set<URSlink> urs) {
		this.urs = urs;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Film getFilm() {
		return film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

	public Set<URSlink> getUrs() {
		return urs;
	}

	public void setUrs(Set<URSlink> urs) {
		this.urs = urs;
	}

	public Screening() {

	}

}
