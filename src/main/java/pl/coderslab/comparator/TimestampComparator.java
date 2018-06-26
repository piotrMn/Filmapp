package pl.coderslab.comparator;


import java.sql.Timestamp;
import java.util.Comparator;

public class TimestampComparator implements Comparator<Timestamp> {

	@Override
	public int compare(Timestamp ts1, Timestamp ts2) {
		if(ts1.before(ts2)) {
			return -1;
		}
		if(ts1.equals(ts2)) {
			return 0;
		}
		return 1;
	}
}
