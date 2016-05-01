package fivePhilophers;

public class Waitor {

	public Waitor() {

	}

	public synchronized boolean tryToEat(Fork a, Fork b){
		if (a.onTable() &&  b.onTable()){
			a.pickedUp();
			b.pickedUp();
			return true;
		}
		return false;
	}
}
