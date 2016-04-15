package scheduler;

import java.util.Comparator;

public class TimeRemainingComparator implements Comparator<Job> {

	@Override
	public int compare(Job o1, Job o2) {
		Integer time  = o1.getTimeLeft();
		Integer time2  =  o2.getTimeLeft();
		return time.compareTo(time2);
		
	}
}
