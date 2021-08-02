package ptithcm.internship.movieapp.helper;

import java.util.Comparator;

public class YearComparator implements Comparator<Integer>{

	@Override
	public int compare(Integer o1, Integer o2) {
		return (o1.intValue() > o2.intValue())?1:-1;
	}

}
