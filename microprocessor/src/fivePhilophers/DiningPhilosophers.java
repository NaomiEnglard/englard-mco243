package fivePhilophers;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class DiningPhilosophers {

	public static void main(String[] args) {
		Fork f1 = new Fork(0);
		Fork f2 = new Fork(1);
		Fork f3 = new Fork(2);
		Fork f4 = new Fork(3);
		Fork f5 = new Fork(4);

		LinkedBlockingQueue<Fork> allForks = new LinkedBlockingQueue<>();
		allForks.add(f1);
		allForks.add(f2);
		allForks.add(f3);
		allForks.add(f4);
		allForks.add(f5);
		 AtomicInteger numForksOnTable = new AtomicInteger(5);

		Philosopher a = new Philosopher("A", f1, f2, numForksOnTable);
		Philosopher b = new Philosopher("B", f2, f3, numForksOnTable);
		Philosopher c = new Philosopher("C", f3, f4, numForksOnTable);
		Philosopher d = new Philosopher("D", f4, f5, numForksOnTable);
		Philosopher e = new Philosopher("E", f5, f1, numForksOnTable);

		a.start();
		b.start();
		c.start();
		d.start();
		e.start();

	}
}