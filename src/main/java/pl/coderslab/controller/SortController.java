package pl.coderslab.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.coderslab.comparator.CinemaNameComparator;
import pl.coderslab.comparator.FilmTitleComparator;
import pl.coderslab.comparator.TimestampComparator;
import pl.coderslab.dao.ScreeningDao;
import pl.coderslab.entity.Screening;


@Controller
@RequestMapping("/sort")
public class SortController {
	
	@Autowired
	ScreeningDao screeningDao;
	
	@GetMapping("/time")
	public String sortByTime(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		String searchresultsString = (String) session.getAttribute("searchresults");
		if(searchresultsString != null) {
			String[] screeningIdsRaw = searchresultsString.split(":");
			List<Screening> searchresults = new ArrayList<>();
			for(int i = 0; i < screeningIdsRaw.length; i++) {
				long id = Long.parseLong(screeningIdsRaw[i]);
				Screening scr = screeningDao.findScreeningById(id);
				searchresults.add(scr);
			}
			TimestampComparator timestampComparator = new TimestampComparator();
			FilmTitleComparator filmTitleComparator = new FilmTitleComparator();
			CinemaNameComparator cinemaNameComparator = new CinemaNameComparator();
			Comparator<Screening> byTimestampThenByTitleThenByCinema = Comparator.comparing(Screening::getTimestamp, timestampComparator)
					.thenComparing(Screening::getFilm, filmTitleComparator)
					.thenComparing(Screening::getCinema, cinemaNameComparator);
			
			searchresults.sort(byTimestampThenByTitleThenByCinema);
			StringBuilder sb = new StringBuilder();
			for (Screening scr : searchresults) {
				sb.append(String.valueOf(scr.getId()));
				sb.append(":");
			}
			session.setAttribute("searchresults", sb.toString());
		}
		return "redirect:/";
	}
	
	@GetMapping("/title")
	public String sortByTitle(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		String searchresultsString = (String) session.getAttribute("searchresults");
		if(searchresultsString != null) {
			String[] screeningIdsRaw = searchresultsString.split(":");
			List<Screening> searchresults = new ArrayList<>();
			for(int i = 0; i < screeningIdsRaw.length; i++) {
				long id = Long.parseLong(screeningIdsRaw[i]);
				Screening scr = screeningDao.findScreeningById(id);
				searchresults.add(scr);
			}
			TimestampComparator timestampComparator = new TimestampComparator();
			FilmTitleComparator filmTitleComparator = new FilmTitleComparator();
			CinemaNameComparator cinemaNameComparator = new CinemaNameComparator();
			Comparator<Screening> byTitleThenByDateThenByCinema = Comparator.comparing(Screening::getFilm, filmTitleComparator)
					.thenComparing(Screening::getTimestamp, timestampComparator)
					.thenComparing(Screening::getCinema, cinemaNameComparator);
			
			searchresults.sort(byTitleThenByDateThenByCinema);
			StringBuilder sb = new StringBuilder();
			for (Screening scr : searchresults) {
				sb.append(String.valueOf(scr.getId()));
				sb.append(":");
			}
			session.setAttribute("searchresults", sb.toString());
		}
		return "redirect:/";
	}
	
	@GetMapping("/cinema")
	public String sortByCinema(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		String searchresultsString = (String) session.getAttribute("searchresults");
		if(searchresultsString != null) {
			String[] screeningIdsRaw = searchresultsString.split(":");
			List<Screening> searchresults = new ArrayList<>();
			for(int i = 0; i < screeningIdsRaw.length; i++) {
				long id = Long.parseLong(screeningIdsRaw[i]);
				Screening scr = screeningDao.findScreeningById(id);
				searchresults.add(scr);
			}
			TimestampComparator timestampComparator = new TimestampComparator();
			FilmTitleComparator filmTitleComparator = new FilmTitleComparator();
			CinemaNameComparator cinemaNameComparator = new CinemaNameComparator();
			Comparator<Screening> byCinemaThenByDateThenByTitle = Comparator.comparing(Screening::getCinema, cinemaNameComparator)
					.thenComparing(Screening::getTimestamp, timestampComparator)
					.thenComparing(Screening::getFilm, filmTitleComparator);
			
			searchresults.sort(byCinemaThenByDateThenByTitle);
			StringBuilder sb = new StringBuilder();
			for (Screening scr : searchresults) {
				sb.append(String.valueOf(scr.getId()));
				sb.append(":");
			}
			session.setAttribute("searchresults", sb.toString());
		}
		return "redirect:/";
	}

}
