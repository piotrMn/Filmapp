package pl.coderslab.controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.coderslab.dao.CinemaDao;
import pl.coderslab.dao.FilmDao;
import pl.coderslab.dao.ScreeningDao;
import pl.coderslab.entity.Cinema;
import pl.coderslab.entity.Screening;

@Controller
@RequestMapping("/screening")
public class ScreeningController {

	@Autowired
	ScreeningDao scrDao;

	@Autowired
	FilmDao filmDao;

	@Autowired
	CinemaDao cinemaDao;

	@RequestMapping("/add")
	@ResponseBody
	public String addScreeningLegnica() {
		// Kino Lalka
		Cinema cin1 = cinemaDao.findByName("Lalka");
		LocalDateTime ldt1 = LocalDateTime.of(2018, 6, 20, 13, 30);
		for (int i = 0; i < 42; i++) {
			Screening scr = new Screening();
			scr.setFilm(filmDao.findById(1L));
			scr.setCinema(cin1);
			Timestamp ts = Timestamp.valueOf(ldt1.plusDays(i));
			scr.setTimestamp(ts);
			scr.setPrice(16);
			scrDao.save(scr);
		}
		LocalDateTime ldt2 = LocalDateTime.of(2018, 6, 20, 16, 0);
		for (int i = 0; i < 42; i++) {
			Screening scr = new Screening();
			scr.setFilm(filmDao.findById(2L));
			scr.setCinema(cin1);
			Timestamp ts = Timestamp.valueOf(ldt2.plusDays(i));
			scr.setTimestamp(ts);
			scr.setPrice(18);
			scrDao.save(scr);
		}
		LocalDateTime ldt3 = LocalDateTime.of(2018, 6, 20, 18, 0);
		for (int i = 0; i < 42; i++) {
			Screening scr = new Screening();
			scr.setFilm(filmDao.findById(3L));
			scr.setCinema(cin1);
			Timestamp ts = Timestamp.valueOf(ldt3.plusDays(i));
			scr.setTimestamp(ts);
			scr.setPrice(20);
			scrDao.save(scr);
		}
		LocalDateTime ldt4 = LocalDateTime.of(2018, 6, 20, 20, 0);
		for (int i = 0; i < 42; i++) {
			Screening scr = new Screening();
			scr.setFilm(filmDao.findById(5L));
			scr.setCinema(cin1);
			Timestamp ts = Timestamp.valueOf(ldt4.plusDays(i));
			scr.setTimestamp(ts);
			scr.setPrice(20);
			scrDao.save(scr);
		}
		// Kino Odeon
		Cinema cin2 = cinemaDao.findByName("Odeon");
		LocalDateTime ldt5 = LocalDateTime.of(2018, 6, 20, 17, 0);
		for (int i = 0; i < 42; i++) {
			Screening scr = new Screening();
			scr.setFilm(filmDao.findById(1L));
			scr.setCinema(cin2);
			Timestamp ts = Timestamp.valueOf(ldt5.plusDays(i));
			scr.setTimestamp(ts);
			scr.setPrice(17);
			scrDao.save(scr);
		}
		LocalDateTime ldt6 = LocalDateTime.of(2018, 6, 20, 18, 30);
		for (int i = 0; i < 42; i++) {
			Screening scr = new Screening();
			scr.setFilm(filmDao.findById(3L));
			scr.setCinema(cin2);
			Timestamp ts = Timestamp.valueOf(ldt6.plusDays(i));
			scr.setTimestamp(ts);
			scr.setPrice(19);
			scrDao.save(scr);
		}
		LocalDateTime ldt7 = LocalDateTime.of(2018, 6, 20, 20, 0);
		for (int i = 0; i < 42; i++) {
			Screening scr = new Screening();
			scr.setFilm(filmDao.findById(4L));
			scr.setCinema(cin2);
			Timestamp ts = Timestamp.valueOf(ldt7.plusDays(i));
			scr.setTimestamp(ts);
			scr.setPrice(21);
			scrDao.save(scr);
		}
		LocalDateTime ldt8 = LocalDateTime.of(2018, 6, 20, 21, 0);
		for (int i = 0; i < 42; i++) {
			Screening scr = new Screening();
			scr.setFilm(filmDao.findById(5L));
			scr.setCinema(cin2);
			Timestamp ts = Timestamp.valueOf(ldt8.plusDays(i));
			scr.setTimestamp(ts);
			scr.setPrice(21);
			scrDao.save(scr);
		}
		// Kino Gigant
		Cinema cin3 = cinemaDao.findByName("Gigant");
		LocalDateTime ldt9 = LocalDateTime.of(2018, 6, 20, 13, 30);
		for (int i = 0; i < 42; i++) {
			Screening scr = new Screening();
			scr.setFilm(filmDao.findById(2L));
			scr.setCinema(cin3);
			Timestamp ts = Timestamp.valueOf(ldt9.plusDays(i));
			scr.setTimestamp(ts);
			scr.setPrice(16);
			scrDao.save(scr);
		}
		LocalDateTime ldt10 = LocalDateTime.of(2018, 6, 20, 16, 0);
		for (int i = 0; i < 42; i++) {
			Screening scr = new Screening();
			scr.setFilm(filmDao.findById(3L));
			scr.setCinema(cin3);
			Timestamp ts = Timestamp.valueOf(ldt10.plusDays(i));
			scr.setTimestamp(ts);
			scr.setPrice(18);
			scrDao.save(scr);
		}
		LocalDateTime ldt11 = LocalDateTime.of(2018, 6, 20, 18, 0);
		for (int i = 0; i < 42; i++) {
			Screening scr = new Screening();
			scr.setFilm(filmDao.findById(4L));
			scr.setCinema(cin3);
			Timestamp ts = Timestamp.valueOf(ldt11.plusDays(i));
			scr.setTimestamp(ts);
			scr.setPrice(18);
			scrDao.save(scr);
		}
		LocalDateTime ldt12 = LocalDateTime.of(2018, 6, 20, 20, 30);
		for (int i = 0; i < 42; i++) {
			Screening scr = new Screening();
			scr.setFilm(filmDao.findById(5L));
			scr.setCinema(cin3);
			Timestamp ts = Timestamp.valueOf(ldt12.plusDays(i));
			scr.setTimestamp(ts);
			scr.setPrice(18);
			scrDao.save(scr);
		}
		return "screenings added";
	}

}
