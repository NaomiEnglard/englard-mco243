package englard.microprocessor;

public class Acumulator {

	private Word value;

	/*
	 * start with 0 by default
	 */
	public Acumulator() {
		value = new Word(0);
	}

	public int getDecimalValue() {
		return this.value.getDecimalValue();
	}
	
	public Word getWord(){
		return this.value;
	}

	public void setValue(Word newValue) {
		this.value = newValue;
	}

	public void setValue(int newValue) {
		this.value = new Word(newValue);
	}

}
