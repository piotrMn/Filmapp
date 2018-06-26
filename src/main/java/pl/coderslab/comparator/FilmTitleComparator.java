package pl.coderslab.comparator;


import java.util.Comparator;

import pl.coderslab.entity.Film;

public class FilmTitleComparator implements Comparator<Film> {

	@Override
	public int compare(Film f1, Film f2) {
		return f1.getTitle().compareToIgnoreCase(f2.getTitle());
	}
}
