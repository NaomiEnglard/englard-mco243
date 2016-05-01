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

	public static void main(String[] args) {
		ArrayList<Job> jobs = new ArrayList<Job>();
		jobs.add(new Job("1", Priority.High, JobType.Computed, 100));
		jobs.add(new Job("2", Priority.Meduim, JobType.Computed, 100));
		jobs.add(new Job("3", Priority.Low, JobType.Computed, 50));
		jobs.add(new Job("4", Priority.Low, JobType.Computed, 200));
		jobs.add(new Job("5", Priority.Low, JobType.IO, 100));
		Comparator<Job> c = new PriorityCamparator();
		SelfishRoundRobin s = new SelfishRoundRobin(jobs, c);
		s.run();
		System.out.println("Completed it took " + s.totalTime);

	}

}
