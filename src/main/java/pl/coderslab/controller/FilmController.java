package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.coderslab.dao.FilmDao;
import pl.coderslab.entity.Film;

@Controller
@RequestMapping("/film")
public class FilmController {

	@Autowired
	FilmDao filmDao;

	@RequestMapping("/add")
	@ResponseBody
	public String addFilms() {
		Film film1 = new Film();
		film1.setTitle("Tomb Raider");
		film1.setDirector("Roar Uthaug");
		film1.setGenre("Przygodowy");
		film1.setDescription("Lara Croft to niepokorna córka ekscentrycznego podróżnika, który zniknął, gdy dziewczyna miała kilkanaście lat. Teraz, jako 21-letnia kobieta, podąża własną ścieżką, odmawiając spełnienia woli ojca, który chciał dla niej spokojnego życia. Zostawia wszystko za sobą i udaje się w ostatnie znane miejsce jego pobytu. Poszukując śladów, musi odnaleźć osławiony grobowiec na mitycznej wyspie u wybrzeży Japonii. Jeśli nie przezwycięży własnych lęków, może nie przeżyć niezwykle niebezpiecznej wyprawy. Jak wiele poświęci, by poznać tajemnicę zniknięcia ojca i zyskać miano tomb raidera");
		film1.setYear(2018);
		film1.setPoster("http://localhost:8080/Filmapp/static/images/tombraider.jpg");
		filmDao.save(film1);
		
		Film film2 = new Film();
		film2.setTitle("Dead Pool 2");
		film2.setDirector("David Leitch");
		film2.setGenre("Akcja");
		film2.setDescription("Po przeżyciu groźnego wypadku podczas serfowania, Wade Wilson (Ryan Reynolds) stara się spełnić swoje marzenia o sławie jako hodowca buldoga francuskiego, jednocześnie żyjąc w otwartym związku z ukochaną kobietą. Próbuje odzyskać swoją pasję do życia, toczy walki z wojownikami ninja i człowiekiem z metalu. Podróżuje po całym świecie, by odkrywać znaczenie rodziny, przyjaźni i by ubogacić swoje życie seksualne.");
		film2.setYear(2018);
		film2.setPoster("http://localhost:8080/Filmapp/static/images/deadpool2.jpg");
		filmDao.save(film2);
		
		Film film3 = new Film();
		film3.setTitle("Tully");
		film3.setDirector("Jason Reitman");
		film3.setGenre("Komedia");
		film3.setDescription("Komedia o trzydziestoletniej matce trójki dzieci, która musi zmierzyć się z młodą, ponętną, szaloną i tajemniczą nianią... ");
		film3.setYear(2018);
		film3.setPoster("http://localhost:8080/Filmapp/static/images/tully.jpg");
		filmDao.save(film3);
		
		Film film4 = new Film();
		film4.setTitle("Ella i John");
		film4.setDirector("Paolo Virzi");
		film4.setGenre("Dramat");
		film4.setDescription("Hellen Mirren i Donald Sutherland wcielają się w uciekającą parę, która udaje się w niezapomnianą podróż starym RV „Leisure Seeker”. Podróż zaczyna się w Bostonie, jej zwieńczeniem ma być dom Ernesta Hemingway’a w Key West. Podróż jest próbą odnalezienia pasji życia i miłości, która ich łączy, a podróż to niezwykła – pełna przygód i niespodzianek – od samego początku do końca.");
		film4.setYear(2018);
		film4.setPoster("http://localhost:8080/Filmapp/static/images/ellaijohn.jpg");
		filmDao.save(film4);
		
		Film film5 = new Film();
		film5.setTitle("Han Solo: Gwiezdne wojny - historie");
		film5.setDirector("Ron Howard");
		film5.setGenre("ScienceFiction");
		film5.setDescription("W „Han Solo. Gwiezdne wojny - historie” poznasz nieznane wcześniej przygody najsłynniejszego przemytnika w galaktyce! W czeluściach mrocznego i groźnego przestępczego półświatka, Han Solo zaprzyjaźnia się ze swoim przyszłym drugim pilotem Chewbaccą i poznaje hazardzistę Calrissiana. Tak rodzi się legenda jednego z najbardziej kultowych bohaterów w historii kina, znanego z późniejszej sagi Gwiezdne wojny.");
		film5.setYear(2018);
		film5.setPoster("http://localhost:8080/Filmapp/static/images/hansolo.jpg");
		filmDao.save(film5);
		
		return "films added";

	}

}
