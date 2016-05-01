package scheduler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Queue;

/*
 * Fifo scheduler and each process runs for X seconds and then the next process goes
 */
public class RoundRobinScheduler extends Scheduler {

	public RoundRobinScheduler(List<Job> jobs) {
		super(jobs);

	}

	@Override
	public void run() {
		// dequeue job run it for up to X seconds
		// if not finished add it back to the queue
		while (jobs.size() != 0) {
			Job job = jobs.remove(0);// remove the head
			totalTime += ExecuteJob(job);
			if (!job.isFinished()) {
				jobs.add(job);
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
		RoundRobinScheduler s = new RoundRobinScheduler(jobs);
		s.run();
		System.out.println("Completed it took " + s.totalTime);

	}
}
