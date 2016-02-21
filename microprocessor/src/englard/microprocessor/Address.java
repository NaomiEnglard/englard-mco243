package englard.microprocessor;

public class Address {

	private Word one;
	private Word two;

	public Address(int one, int two) {

		this.one = new Word(one);
		this.two = new Word(two);
	}

	/*
	 * given a large decimal
	 */
	public Address(int decSum) {
		String hexSum = Integer.toHexString(decSum);
		if (decSum > 10) {

			one = new Word(String.valueOf(hexSum.charAt(0)));
			two = new Word(String.valueOf(hexSum.charAt(1)));

		} else {
			one = new Word(0);
			two = new Word(String.valueOf(hexSum.charAt(0)));
		}
	}

	public Address(Word one, Word two) {

		this.one = one;
		this.two = two;
	}

	public Word getWordOne() {
		return one;
	}

	public void setWordOne(Word one) {
		this.one = one;
	}

	public Word getWordTwo() {
		return two;
	}

	public void setWordTwo(Word two) {
		this.two = two;
	}

}
