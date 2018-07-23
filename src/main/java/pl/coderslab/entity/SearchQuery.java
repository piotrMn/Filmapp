package pl.coderslab.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import pl.coderslab.validator.NotDate;
import pl.coderslab.validator.PastDate;

@Entity
@Table(name = "searchquery")
public class SearchQuery {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	String filmTitle;

	String cinemaName;

	@PastDate()
	@NotDate()
	String date;

	public String getFilmTitle() {
		return filmTitle;
	}

	public void setFilmTitle(String filmTitle) {
		this.filmTitle = filmTitle;
	}

	public String getCinemaName() {
		return cinemaName;
	}

	public void setCinemaName(String cinemaName) {
		this.cinemaName = cinemaName;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public SearchQuery() {
	}

}
