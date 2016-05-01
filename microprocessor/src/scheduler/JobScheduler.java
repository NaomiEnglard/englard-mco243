package scheduler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

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

	public static void main(String[] args) {
		ArrayList<Job> jobs = new ArrayList<Job>();
		jobs.add(new Job("1", Priority.High,JobType.Computed, 100));
		jobs.add(new Job("2", Priority.Meduim,JobType.Computed, 100));
		jobs.add(new Job("3", Priority.Low,	JobType.Computed, 50));
		jobs.add(new Job("4", Priority.High,JobType.Computed, 200));
		jobs.add(new Job("5", Priority.High, JobType.IO,100));
		Comparator<Job> c = new PriorityCamparator();
		JobScheduler s = new JobScheduler(jobs, c);
		s.run();
		System.out.println("Completed it took " + s.totalTime);

	}
}
