package scheduler;


import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class JobScheduler extends Scheduler {

	private Comparator<Job> comparator;
	private Job lastJob;

	public JobScheduler(List<Job> jobs, Comparator<Job> compare) {
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
