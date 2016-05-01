package scheduler;

import java.util.List;
import java.util.Random;

public abstract class Scheduler implements Runnable {

	protected List<Job> jobs;
	public static final int TIME_SLICE = 10;
	public static final int OVERHAED = 1;
	protected int numJobsCompleted;
	protected int totalTime;
	protected Random rand = new Random();

	public Scheduler(List<Job> jobs) {
		this.jobs = jobs;
		totalTime = 0;
		numJobsCompleted = 0;
	}

	public abstract void run();

	public int ExecuteJob(Job job) {
		job.setLastRanAtTime(System.currentTimeMillis());
		job.setState(State.Running);
		int actualTimeSlice = calculateActualTime(job);
		job.decerementTimeLeftToRun(actualTimeSlice);
		if (job.isFinished()) {
			numJobsCompleted++;
		} else {
			job.setState(State.Ready);
		}
		return actualTimeSlice; // time it ran for
	}

	public int calculateActualTime(Job job) {
		int timeLeft = job.getTimeLeft();
		int actualTimeSlice;
		if (job.getType().equals(JobType.IO)) {
			actualTimeSlice = Math.min(rand.nextInt(TIME_SLICE), timeLeft);
		} else {
			actualTimeSlice = Math.min(timeLeft, TIME_SLICE);
		}
		return actualTimeSlice;
	}

}
