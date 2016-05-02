package scheduler;

import java.util.Comparator;

public class DeadlineComparator  implements Comparator<Job> {

	@Override
	public int compare(Job one, Job two) {
		return one.getDeadLine().compareTo(two.getDeadLine());
	}

}
