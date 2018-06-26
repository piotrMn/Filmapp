package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.coderslab.dao.CinemaDao;
import pl.coderslab.entity.Cinema;

@Controller
@RequestMapping("/cinema")
public class CinemaController {
	
	@Autowired
	CinemaDao cinemaDao;
	
	@RequestMapping("/add")
	@ResponseBody
	public String addCinemasLegnica() {
		Cinema cin1 = new Cinema();
		cin1.setName("Lalka");
		cin1.setAddress("ul. Zielona 20");
		cin1.setRows(15);
		cin1.setSeatsInRow(15);
		cin1.setCapacity(15 * 15);
		cinemaDao.save(cin1);
		
		Cinema cin2 = new Cinema();
		cin2.setName("Odeon");
		cin2.setAddress("ul. Szeroka 16");
		cin2.setRows(14);
		cin2.setSeatsInRow(15);
		cin2.setCapacity(14 * 15);
		cinemaDao.save(cin2);
		
		Cinema cin3 = new Cinema();
		cin3.setName("Gigant");
		cin3.setAddress("ul. Sienna 112");
		cin3.setRows(16);
		cin3.setRows(16);
		cin3.setCapacity(16 * 16);
		cinemaDao.save(cin3);
		
		return "cinemas added";
	}

}
