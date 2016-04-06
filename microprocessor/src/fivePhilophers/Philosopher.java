package fivePhilophers;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Philosopher extends Thread {

	private Fork f1;
	private Fork f2;

	private String name;
	private AtomicInteger forks;
	//private LinkedBlockingQueue<Fork> forks;

	public Philosopher(String name, Fork f1, Fork f2,
			AtomicInteger forksOnTable) {
		this.forks = forksOnTable;
		this.name = name;
		this.f1 = f1;
		this.f2 = f2;
	}

	public void run() {
		while (true) {
			think();
			eat();
		}
	}

	public void eat() {
		System.out.println(this + " trying to pick up  " + f1);
		// if (f1.onTable() && f2.onTable()) {
		while (!(forks.get() > 1)) {
			System.out.println("waiting for a fork and thinking");
			think();
		}
		synchronized (f1) {
		//	System.out.println(forks.remove(f1));
			forks.decrementAndGet();
			waitForAFewSeconds(5);
			System.out.println(this + " trying to pick up " + f2);
			synchronized (f2) {
				forks.decrementAndGet();
				System.out.println(this + " eating...");
				waitForAFewSeconds(5);
			}
			System.out.println(this + " put down " + f1);
			forks.incrementAndGet();

		}
		System.out.println(this + " put down " + f2);
		forks.incrementAndGet();
		// }
	}

	public void think() {
	
		waitForAFewSeconds(5);
	}

	private void waitForAFewSeconds(int seconds) {
		try {
			Thread.sleep((long) (seconds * 500));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "Philosopher [name=" + name + "]";
	}

}
