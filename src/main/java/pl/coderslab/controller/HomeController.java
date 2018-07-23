package pl.coderslab.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.coderslab.dao.CinemaDao;
import pl.coderslab.dao.FilmDao;
import pl.coderslab.dao.ReservationDao;
import pl.coderslab.dao.ScreeningDao;
import pl.coderslab.dao.URSDao;
import pl.coderslab.dao.UserDao;
import pl.coderslab.entity.Cinema;
import pl.coderslab.entity.Film;
import pl.coderslab.entity.Screening;
import pl.coderslab.entity.SearchQuery;
import pl.coderslab.entity.URSlink;
import pl.coderslab.entity.User;
import pl.coderslab.util.Converter;

@Controller
@RequestMapping("/")
public class HomeController {

	@Autowired
	UserDao userDao;

	@Autowired
	ReservationDao resDao;

	@Autowired
	URSDao ursDao;

	@Autowired
	FilmDao filmDao;

	@Autowired
	CinemaDao cinemaDao;

	@Autowired
	ScreeningDao screeningDao;

	@GetMapping("")
	public String doGet(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(600);
		session.removeAttribute("screeningtobook");

		Object dateError = session.getAttribute("dateerror");
		if (dateError != null) {
			model.addAttribute("dateerror", dateError.toString());
			session.removeAttribute("dateerror");
		} else {
			Object dateValue = session.getAttribute("datevalue");
			if (dateValue != null) {
				model.addAttribute("datevalue", dateValue.toString());
				session.removeAttribute("datevalue");
			}
		}
		// Sprawdzenie, czy jest zalogowany użytkownik
		Object loggedUsername = session.getAttribute("loggedUsername");
		if (loggedUsername != null) {
			User loggedUser = userDao.findUserByUsername(loggedUsername.toString());
			if (loggedUser != null) {
				model.addAttribute("username", loggedUser.getUsername());
				List<URSlink> upcoming = ursDao.findUpcomingByUserId(loggedUser.getId());
				model.addAttribute("upcoming", upcoming);
				List<URSlink> past = ursDao.findPastByUserId(loggedUser.getId());
				model.addAttribute("past", past);
				List<URSlink> cancelled = ursDao.findCancelledByUserId(loggedUser.getId());
				model.addAttribute("cancelled", cancelled);
			}
		}
		// Sprawdzenie, czy są wyniki wyszukiwania
		String searchresultsString = (String) session.getAttribute("searchresults");
		if (searchresultsString != null && !searchresultsString.equals("")
				&& !searchresultsString.equals("noresults")) {
			String[] screeningIdsRaw = searchresultsString.split(":");
			List<Screening> searchresults = new ArrayList<>();

			for (int idArrayIndex = 0; idArrayIndex < screeningIdsRaw.length; idArrayIndex++) {
				long screeningId = Long.parseLong(screeningIdsRaw[idArrayIndex]);
				Screening thisScreening = screeningDao.findScreeningById(screeningId);
				if (thisScreening != null) {
					searchresults.add(thisScreening);
				}
			}
			model.addAttribute("searchresults", searchresults);
		}
		if (searchresultsString != null && !searchresultsString.equals("") && searchresultsString.equals("noresults")) {
			model.addAttribute("searchresults", "emptylist");
		}
		if (searchresultsString == null || searchresultsString.equals("")) {
			model.addAttribute("searchresults", "noquery");
		}
		SearchQuery sq = new SearchQuery();
		model.addAttribute("searchQuery", sq);
		return "home";
	}

	@PostMapping("")
	public String searchFormPost(@Valid SearchQuery searchQuery, BindingResult result, Model model,
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (result.hasErrors()) {
			FieldError dateError = result.getFieldError("date");
			if (dateError != null) {
				String dateMessage = dateError.getDefaultMessage();
				session.setAttribute("dateerror", dateMessage);
			} else {
				session.setAttribute("datevalue", searchQuery.getDate());
			}
			return "redirect:/";
		}
		String filmTitleTrimmed = searchQuery.getFilmTitle().trim();
		String cinemaNameTrimmed = searchQuery.getCinemaName().trim();
		String date = searchQuery.getDate();
		Timestamp timestampFrom = null;
		Timestamp timestampTo = null;
		if (date != null && !date.equals("")) {
			String revDate = Converter.reverseDate(date);
			timestampFrom = Timestamp.valueOf(revDate.concat(" 00:00:01"));
			timestampTo = Timestamp.valueOf(revDate.concat(" 23:59:59"));
		}
		List<Screening> searchresults = new ArrayList<Screening>();
		// tytuł i data
		if ((cinemaNameTrimmed.equals("") || cinemaNameTrimmed == null)
				&& !(filmTitleTrimmed.equals("") || filmTitleTrimmed == null) && !(date.equals("") || date == null)) {
			searchresults = screeningDao.findScreeningByFilmTitleAndTimestamps(filmTitleTrimmed, timestampFrom,
					timestampTo);
		}
		// kino i data
		if (!(cinemaNameTrimmed.equals("") || cinemaNameTrimmed == null)
				&& (filmTitleTrimmed.equals("") || filmTitleTrimmed == null) && !(date.equals("") || date == null)) {
			searchresults = screeningDao.findScreeningByCinemaNameAndTimestamps(cinemaNameTrimmed, timestampFrom,
					timestampTo);
		}
		// kino i tytuł
		if (!(cinemaNameTrimmed.equals("") || cinemaNameTrimmed == null)
				&& !(filmTitleTrimmed.equals("") || filmTitleTrimmed == null) && (date.equals("") || date == null)) {
			searchresults = screeningDao.findUpcomingScreeningsByFilmTitleAndCinemaName(filmTitleTrimmed,
					cinemaNameTrimmed);
		}
		// kino, tytuł, data
		if (!(cinemaNameTrimmed.equals("") || cinemaNameTrimmed == null)
				&& (!(filmTitleTrimmed.equals("")) || filmTitleTrimmed == null) && !(date.equals("") || date == null)) {
			searchresults = screeningDao.findScreeningByFilmTitleAndCinemaNameAndTimestamps(filmTitleTrimmed,
					cinemaNameTrimmed, timestampFrom, timestampTo);
		}
		// nic
		if ((cinemaNameTrimmed.equals("") || cinemaNameTrimmed == null)
				&& (filmTitleTrimmed.equals("") || filmTitleTrimmed == null) && (date.equals("") || date == null)) {
			searchresults = screeningDao.findUpcomingScreenings();
		}
		// tylko data
		if ((cinemaNameTrimmed.equals("") || cinemaNameTrimmed == null)
				&& (filmTitleTrimmed.equals("") || filmTitleTrimmed == null) && !(date.equals("") || date == null)) {
			searchresults = screeningDao.findScreeningsByTimestamps(timestampFrom, timestampTo);
		}
		// tylko tytuł
		if ((cinemaNameTrimmed.equals("") || cinemaNameTrimmed == null)
				&& !(filmTitleTrimmed.equals("") || filmTitleTrimmed == null) && (date.equals("") || date == null)) {
			searchresults = screeningDao.findUpcomingScreeningsByFilmTitle(filmTitleTrimmed);
		}
		// tylko kino
		if (!(cinemaNameTrimmed.equals("") || cinemaNameTrimmed == null)
				&& (filmTitleTrimmed.equals("") || filmTitleTrimmed == null) && (date.equals("") || date == null)) {
			searchresults = screeningDao.findUpcomingScreeningsByCinemaName(cinemaNameTrimmed);
		}
		// zapisanie id wyszukanych seansów w stringu i dodanie do sesji
		if (searchresults.size() > 0) {
			StringBuilder sb = new StringBuilder();
			for (Screening screening : searchresults) {
				sb.append(String.valueOf(screening.getId()));
				sb.append(":");
			}
			session.setAttribute("searchresults", sb.toString());
		} else {
			session.setAttribute("searchresults", "noresults");
		}
		return "redirect:/";
	}

	@ModelAttribute("cinemas")
	public List<Cinema> addCinemas() {
		return cinemaDao.findAllCinemas();
	}

	@ModelAttribute("films")
	public List<Film> addFilms() {
		return filmDao.findAllFilms();
	}
}