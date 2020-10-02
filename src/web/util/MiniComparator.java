package web.util;

import java.util.Comparator;

import web.dto.Anniversary;

public class MiniComparator implements Comparator<Anniversary> {

	@Override
	public int compare(Anniversary o1, Anniversary o2) {
		
		int restday = o1.getRest_day();
		int restday2 = o2.getRest_day();
		
		if(restday > restday2) {
			return 1;
		} else if(restday < restday2) {
			return -1;
		} else {
			return 0;
		}
	}
}