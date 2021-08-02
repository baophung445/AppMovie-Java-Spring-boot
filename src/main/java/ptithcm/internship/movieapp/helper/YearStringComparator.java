package ptithcm.internship.movieapp.helper;

import java.util.Comparator;

public class YearStringComparator implements Comparator<String>{

	@Override
	public int compare(String o1, String o2) {
		Integer n1 = Integer.valueOf(o1);
		Integer n2 = Integer.valueOf(o2);
		return (n1.intValue() > n2.intValue())?1:-1;
	}

}
