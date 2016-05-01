package scheduler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class JobScheduler implements Runnable {

	private List<Job> jobs;
	private Comparator<Job> comparator;
	private static final int TIME_SLICE = 10;
	private static final int OVERHAED = 1;
	private int numJobsCompleted = 0;
	private static int totalTime = 0;
	private Random rand = new Random();
	private Job lastJob;

	public JobScheduler(List<Job> jobs, Comparator compare) {
		this.jobs = jobs;
		this.comparator = compare;
		lastJob = null;
	}

	public void run() {
		while (jobs.size() != 0) {
			Collections.sort(jobs, comparator);
			Job job = jobs.get(0);
			int timeLeft = ExecuteJob(job);
			this.totalTime += timeLeft;
			//if (job != lastJob) {
				this.totalTime += OVERHAED;
				lastJob = job;
			//}

		}

	}

	private int ExecuteJob(Job job) {
		job.setLastRanAtTime(System.currentTimeMillis());
		job.setState(State.Running);
		int actualTimeSlice = calculateActualTime(job);
		int timeLeft = actualTimeSlice;
		job.decerementTimeLeftToRun(actualTimeSlice);
		if (job.isFinished()) {
			jobs.remove(0);
			numJobsCompleted++;
		} else {
			job.setState(State.Ready);
		}
		return timeLeft;
	}

	private int calculateActualTime(Job job) {
		int timeLeft = job.getTimeLeft();

		int actualTimeSlice;
		if (job.getType().equals(JobType.IO)) {
			actualTimeSlice = Math.min(rand.nextInt(TIME_SLICE), timeLeft);
		} else {
			actualTimeSlice = Math.min(timeLeft, TIME_SLICE);
		}
		return actualTimeSlice;
	}

	public static void main(String[] args) {
		List<Job> jobs = Arrays.asList(new Job("1", Priority.High, JobType.Computed, 100), new Job("2",
				Priority.Meduim, JobType.Computed, 100), new Job("3", Priority.Low, JobType.Computed, 50), new Job("4",
				Priority.High, JobType.Computed, 200), new Job("5", Priority.High, JobType.IO, 100));
		Comparator<Job> c = new PriorityCamparator();
		JobScheduler s = new JobScheduler(new ArrayList(jobs), c);
		s.run();
		System.out.println("Completed it took " + totalTime);

	}
}
