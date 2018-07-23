package pl.coderslab.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pl.coderslab.dao.ReservationDao;
import pl.coderslab.dao.ScreeningDao;
import pl.coderslab.dao.UserDao;
import pl.coderslab.entity.Reservation;
import pl.coderslab.entity.Screening;
import pl.coderslab.entity.SearchQuery;
import pl.coderslab.entity.User;
import pl.coderslab.util.Converter;
import pl.coderslab.validator.LoginGroupVal;
import pl.coderslab.validator.RegisterGroupVal;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserDao userDao;

	@Autowired
	ReservationDao reservationDao;

	@Autowired
	ScreeningDao screeningDao;

	@RequestMapping("/signup")
	public String addUserForm(Model model, HttpServletRequest request) {
		User user = new User();
		model.addAttribute("user", user);
		Object screeningToBook = request.getParameter("screeningtobook");
		if (screeningToBook != null) {
			HttpSession session = request.getSession();
			session.setAttribute("screeningtobook", screeningToBook.toString());
		}
		return "signupForm";
	}

	@PostMapping("/signup")
	public String addUserFormPost(@Validated({ RegisterGroupVal.class }) User user, BindingResult result,
			@RequestParam String password2, Model model, HttpServletRequest request) {
		if (result.hasErrors()) {
			return "signupForm";
		}
		if (user.getPassword().equals(password2)) {
			user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
			model.addAttribute("username", user.getUsername());
			HttpSession session = request.getSession();
			session.setAttribute("loggedUsername", user.getUsername());
			this.userDao.save(user);
			Object screeningToBook = session.getAttribute("screeningtobook");
			if (screeningToBook != null) {
				String thisIdRaw = screeningToBook.toString();
				long thisId = 0;
				try {
					thisId = Long.parseLong(thisIdRaw);
				} catch (NumberFormatException e) {
					return "redirect:/";
				}
				Screening screening = screeningDao.findScreeningById(thisId);
				List<Reservation> reservations = reservationDao.findReservationByScreeningId(thisId);
				String seatCodes = "";
				for(Reservation res : reservations) {
					seatCodes += res.getSeatCodes();
				}
				List<String> bookedSeatsInStringList = Converter.convertSeatCodes(seatCodes, screening.getCinema().getRows(), screening.getCinema().getSeatsInRow());
				model.addAttribute("bookedseats", bookedSeatsInStringList);
				model.addAttribute("screening", screening);
				return "reservationForm";
			}
			return "redirect:/";
		} else {
			model.addAttribute("error", "mismatch");
			return "signupForm";
		}
	}

	@GetMapping("/login")
	public String login(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "loginForm";
	}

	@PostMapping("/login")
	public String loginPost(@Validated({ LoginGroupVal.class }) User user, BindingResult result, Model model,
			HttpServletRequest request) {
		if (result.hasErrors()) {
			return "loginForm";
		}
		HttpSession session = request.getSession();
		User userToMatch = userDao.findUserByUsername(user.getUsername());
		if (BCrypt.checkpw(user.getPassword(), userToMatch.getPassword())) {
			session.setAttribute("loggedUsername", userToMatch.getUsername());
		}
		Object screeningToBook = session.getAttribute("screeningtobook");
		if (screeningToBook != null) {
			String thisIdRaw = screeningToBook.toString();
			long thisId = 0;
			try {
				thisId = Long.parseLong(thisIdRaw);
			} catch (NumberFormatException e) {
				return "redirect:/";
			}
			Screening screening = screeningDao.findScreeningById(thisId);
			List<Reservation> reservations = reservationDao.findReservationByScreeningId(thisId);
			String seatCodes = "";
			for(Reservation res : reservations) {
				seatCodes += res.getSeatCodes();
			}
			List<String> bookedSeatsInStringList = Converter.convertSeatCodes(seatCodes, screening.getCinema().getRows(), screening.getCinema().getSeatsInRow());
			model.addAttribute("bookedseats", bookedSeatsInStringList);
			model.addAttribute("screening", screening);
			return "reservationForm";
		}
		return "redirect:/";
	}

	@RequestMapping("/logout")
	public String logout(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		session.removeAttribute("loggedUsername");
		session.removeAttribute("searchresults");
		SearchQuery sq = new SearchQuery();
		model.addAttribute("searchQuery", sq);
		return "redirect:/";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam String username, Model model) {
		User userToDelete = userDao.findUserByUsername(username);
		model.addAttribute("username", userToDelete.getUsername());
		return "deleteForm";
	}

	@PostMapping("/delete")
	public String deletePost(@RequestParam String password, @RequestParam String userid, HttpServletRequest request) {
		HttpSession session = request.getSession();
		try {
			long userIdl = Long.parseLong(userid);
			User userToDelete = userDao.findUserById(userIdl);
			if (BCrypt.checkpw(password, userToDelete.getPassword())) {
				userDao.delete(userToDelete);
				session.removeAttribute("loggedUsername");
			}
		} catch (NumberFormatException e) {
			return "redirect:/";
		}
		return "redirect:/";
	}

}
