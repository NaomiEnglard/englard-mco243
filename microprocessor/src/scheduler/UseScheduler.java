package scheduler;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

public class UseScheduler {

	public static void main(String[] args) {
		Date day1 = new Date(34566l);
		Date day2 = new Date(23456786543l);
		Date day3 = new Date(34566l);
		Date day4 = new Date(345663456l);
		Date day5 = new Date(99999999999l);

		ArrayList<Job> jobs = new ArrayList<Job>();
		jobs.add(new Job("1", Priority.High, JobType.Computed, 100, day1));
		jobs.add(new Job("2", Priority.Meduim, JobType.Computed, 100, day2));
		jobs.add(new Job("3", Priority.Low, JobType.Computed, 50, day3));
		jobs.add(new Job("4", Priority.Low, JobType.Computed, 200, day4));
		jobs.add(new Job("5", Priority.Low, JobType.IO, 100, day5));
		Comparator<Job> c = new PriorityCamparator();

		RoundRobinScheduler rr = new RoundRobinScheduler(jobs);
		rr.run();
		System.out.println("Roud Robin completed it took " + rr.totalTime + " jobs completed "+rr.numJobsCompleted);
		jobs.add(new Job("1", Priority.High, JobType.Computed, 100, day1));
		jobs.add(new Job("2", Priority.Meduim, JobType.Computed, 100, day2));
		jobs.add(new Job("3", Priority.Low, JobType.Computed, 50, day3));
		jobs.add(new Job("4", Priority.Low, JobType.Computed, 200, day4));
		jobs.add(new Job("5", Priority.Low, JobType.IO, 100, day5));
		SelfishRoundRobin s = new SelfishRoundRobin(jobs, c);
		s.run();

		System.out.println("Selfish round robin completed it took "
				+ s.totalTime + " jobs completed "+s.numJobsCompleted);
		JobScheduler js = new JobScheduler(jobs, c);
		js.run();
		System.out.println("job scheduler completed it took " + js.totalTime + " jobs completed "+js.numJobsCompleted);

		// deadline
		jobs.add(new Job("1", Priority.High, JobType.Computed, 100, day1));
		jobs.add(new Job("2", Priority.Meduim, JobType.Computed, 100, day2));
		jobs.add(new Job("3", Priority.Low, JobType.Computed, 50, day3));
		jobs.add(new Job("4", Priority.Low, JobType.Computed, 200, day4));
		jobs.add(new Job("5", Priority.Low, JobType.IO, 100, day5));
		JobScheduler deadline = new JobScheduler(jobs, new DeadlineComparator());
		deadline.run();
		System.out.println("deadline scheduler completed it took " + deadline.totalTime + " jobs completed "+deadline.numJobsCompleted);

		//short remaining time first
		jobs.add(new Job("1", Priority.High, JobType.Computed, 100, day1));
		jobs.add(new Job("2", Priority.Meduim, JobType.Computed, 100, day2));
		jobs.add(new Job("3", Priority.Low, JobType.Computed, 50, day3));
		jobs.add(new Job("4", Priority.Low, JobType.Computed, 200, day4));
		jobs.add(new Job("5", Priority.Low, JobType.IO, 100, day5));
		JobScheduler spf = new JobScheduler(jobs, new TimeRemainingComparator());
		spf.run();
		System.out.println("shortest process first scheduler completed it took " + spf.totalTime + " jobs completed "+deadline.numJobsCompleted);
	}
}
