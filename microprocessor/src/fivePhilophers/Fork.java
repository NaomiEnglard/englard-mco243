package fivePhilophers;

import java.util.concurrent.atomic.AtomicBoolean;



public class Fork {
	
	private int number;
	private AtomicBoolean onTable;

	public Fork(int number) {
		this.number = number;
		onTable = new AtomicBoolean(true);
	}

	@Override
	public String toString() {
		return "Fork [number=" + number + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + number;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Fork other = (Fork) obj;
		if (number != other.number)
			return false;
		return true;
	}
	
	
	public boolean onTable(){
		return onTable.get();
	}
	public void pickedUp(){
		onTable.set(false);
	}
	
	
}
