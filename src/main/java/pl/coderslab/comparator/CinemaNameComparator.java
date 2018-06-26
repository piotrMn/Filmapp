package pl.coderslab.comparator;


import java.util.Comparator;

import pl.coderslab.entity.Cinema;

public class CinemaNameComparator implements Comparator<Cinema> {

	@Override
	public int compare(Cinema c1, Cinema c2) {
		return c1.getName().compareToIgnoreCase(c2.getName());
	}
}
