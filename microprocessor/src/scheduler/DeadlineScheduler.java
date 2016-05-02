package scheduler;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
 * sort jobs by deadline, the ones that have a earlier deadline should
 * be completed first
 * same as job scheduler just pass in a deadline compartor
 */
public class DeadlineScheduler extends Scheduler {

	

	private Comparator<Job> comparator;
	private Job lastJob;

	public DeadlineScheduler(List<Job> jobs, Comparator<Job> compare) {
		super(jobs);
		this.comparator = compare;
		lastJob = null;

	}

	public void run() {
		while (jobs.size() != 0) {
			Collections.sort(jobs, comparator);
			Job job = jobs.get(0);
			totalTime += ExecuteJob(job);
			if (job.isFinished()) {
				jobs.remove(0);
			} 
			if (job != lastJob) {
				this.totalTime += OVERHAED;
				lastJob = job;
			}

		}

	}

}
