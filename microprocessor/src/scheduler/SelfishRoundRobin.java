package scheduler;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/*
 * Gradually increases priority over time - older processes are favored
 * two queues - a  holding queue and a active queue
 */
public class SelfishRoundRobin extends Scheduler {

	private final int holdingRate = 1; // rate the process increases priority in
										// holding queue
	private final int activeRate = 2; // rate process increases in active queue
										// (greater than holding rate);
	private int count;
	private List<Job> holding;

	public SelfishRoundRobin(List<Job> jobs, Comparator<Job> priorityComparator) {
		super(jobs);
		holding = new ArrayList<Job>();
		splitUpJobs(jobs); // slit into 2 queue holding and active
		count = 1;
	}

	private void splitUpJobs(List<Job> allJobs) {
		ArrayList<Job> active = new ArrayList<Job>();
		for (Job j : allJobs) {
			if (j.getPriority() == Priority.High) {
				active.add(j);
			} else {
				holding.add(j);
			}
		}
		this.jobs = active;
	}

	@Override
	public void run() {
		while (jobs.size() != 0) {
			execute();
		}
		while (holding.size() != 0) {
			bumpUpPriority();
			execute();
		}

	}

	private void execute() {
		Job job = jobs.remove(0);// remove the head
		totalTime += ExecuteJob(job);
		if (!job.isFinished()) {
			jobs.add(job);
		}
		bumpUpPriority();
	}

	// check if any priority bumbed up would be equal to high, if it is move it
	// to active
	private void bumpUpPriority() {
		Iterator<Job> iter = holding.iterator();
		Job j;
		while (iter.hasNext()) {
			j = iter.next();
			if ((j.getPriority().getNumberValue() + holdingRate * count) == Priority.High
					.getNumberValue()) {
				jobs.add(j);
				iter.remove();
			}
		}

		count++;

	}

	

}
