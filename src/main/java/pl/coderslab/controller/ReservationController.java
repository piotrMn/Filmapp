package pl.coderslab.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pl.coderslab.dao.ReservationDao;
import pl.coderslab.dao.ScreeningDao;
import pl.coderslab.dao.URSDao;
import pl.coderslab.dao.UserDao;
import pl.coderslab.entity.Reservation;
import pl.coderslab.entity.Screening;
import pl.coderslab.entity.SearchQuery;
import pl.coderslab.entity.URSlink;
import pl.coderslab.entity.User;
import pl.coderslab.util.Converter;
import pl.coderslab.util.ReservationUtil;

@Controller
@RequestMapping("/reserve")
public class ReservationController {

	@Autowired
	ReservationDao reservationDao;
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	URSDao ursDao;
	
	@Autowired
	ScreeningDao screeningDao;
	
	@RequestMapping("/form")
	public String addReservation(@RequestParam String screeningId, @RequestParam String username, Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("screeningId", screeningId);
		session.setAttribute("username", username);
		long id = 0;
		try {
			id = Long.parseLong(screeningId);
		} catch (NumberFormatException e) {
			return "redirect:/";
		}
		Screening screening = screeningDao.findScreeningById(id);
		if(screening != null) {
			List<Reservation> reservations = reservationDao.findReservationByScreeningId(id);
			String seatCodes = "";
			for(Reservation res : reservations) {
				seatCodes += res.getSeatCodes();
			}
			List<String> bookedSeatsInStringList = Converter.convertSeatCodes(seatCodes, screening.getCinema().getRows(), screening.getCinema().getSeatsInRow());
			model.addAttribute("bookedseats", bookedSeatsInStringList);
			model.addAttribute("screening", screening);
			return "reservationForm";
		}
		return "home";
	}
	@PostMapping("/form")
	public String addReservationPost(@RequestParam String numberofseats, @RequestParam String seatcodes, @RequestParam String screeningId, Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		long scrId = Long.parseLong(screeningId);
		String username = (String) session.getAttribute("loggedUsername");
		Integer numberOfSeatsParsed = null;
		try {
			numberOfSeatsParsed = Integer.parseInt(numberofseats);
		} catch (NumberFormatException e) {
			return "redirect:/reserve/form";
		}
		Screening screening = screeningDao.findScreeningById(scrId);
		if(screening.getBooked() + numberOfSeatsParsed > screening.getCinema().getCapacity()) {
			model.addAttribute("error", "noseats");
			model.addAttribute("screening", screening);
			return "reservationForm";
		}
		URSlink urs = new URSlink();
		Reservation res = new Reservation();
		res.setNrOfSeats(numberOfSeatsParsed);
		res.setSeatCodes(seatcodes);
		res.setSeatDescribed(ReservationUtil.createSeatDescribed(seatcodes));
		res.setCancelled(false);
		res.getURS().add(urs);
		res.setResNumber(ReservationUtil.generateNumber());
		User user = userDao.findUserByUsername(username);
		user.getURS().add(urs);
		userDao.update(user);
		screening.setBooked(screening.getBooked() + numberOfSeatsParsed);
		screening.getUrs().add(urs);
		urs.setReservation(res);
		urs.setUser(user);
		urs.setScreening(screening);
		ursDao.update(urs);
		session.removeAttribute("screeningId");
		SearchQuery sq = new SearchQuery();
		model.addAttribute("searchQuery", sq);
		return "redirect:/";
	}
	

	@GetMapping("/cancel")
	public String deleteReservation(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String rawResId = request.getParameter("res_id");
		Long resId = null;
		try {
			resId = Long.parseLong(rawResId);
		} catch(NumberFormatException e) {
			return "home";
		}
		String rawUserId = request.getParameter("user_id");
		Long userId = null;
		try {
			userId = Long.parseLong(rawUserId);
		} catch(NumberFormatException e) {
			return "home";
		}
		User user = userDao.findUserById(userId);
		session.setAttribute("loggedUsername", user.getUsername());
		URSlink thisUrsLink = ursDao.findByReservationId(resId);
		Reservation res = thisUrsLink.getReservation();
		res.setCancelled(true);
		Screening scr = thisUrsLink.getScreening();
		scr.setBooked(scr.getBooked() - res.getNrOfSeats());
		ursDao.update(thisUrsLink);
		return "redirect:/";
	}
}
