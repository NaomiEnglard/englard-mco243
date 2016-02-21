package englard.microprocessor;

public class Word {

	private int word;

	/*
	 * get a decimal value
	 */
	public Word(int word) {
		this.word = word;
	}
	
	/*
	 * get a hex value
	 */
	public Word(String hexWord){
		this.word = Integer.valueOf(hexWord, 16);
	}

	public int getDecimalValue() {
		return this.word;
	}
	
	public String getHexValue(){
		return Integer.toHexString(this.word);
	}

	public void setWord(int value) {
		this.word = value;
	}
	
	public void setWord(String hexValue){
		this.word = Integer.valueOf(hexValue, 16);
	}

	public String toString() {
		return this.getHexValue();
	}
}
