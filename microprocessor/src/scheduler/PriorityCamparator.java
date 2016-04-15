package scheduler;

import java.util.Comparator;

public class PriorityCamparator implements Comparator<Job>  {

	@Override
	public int compare(Job one, Job two) {
		Priority p1  = one.getPriority();
		Priority p2  =  two.getPriority();
		return p1.compareTo(p2);
		
	}

}
