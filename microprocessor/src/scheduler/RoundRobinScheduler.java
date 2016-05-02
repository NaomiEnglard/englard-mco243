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
}
