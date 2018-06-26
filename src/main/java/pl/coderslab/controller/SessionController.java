package pl.coderslab.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/session")
public class SessionController {
	
	@RequestMapping("/end")
	@ResponseBody
	public String endSession(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		return "session invalidated";
	}
	
	@RequestMapping("/clean")
	public String clean(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("searchresults");
		return "redirect:/";
	}

}
