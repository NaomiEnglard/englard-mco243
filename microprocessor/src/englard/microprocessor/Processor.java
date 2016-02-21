package englard.microprocessor;

import java.io.IOException;

public class Processor {

	private Acumulator a;
	private Acumulator b;
	private Memory memory;

	/*
	 * @param fileName pass in the file with the instuction set and memory
	 */
	public Processor(String memoryString) throws IOException {

		a = new Acumulator();
		b = new Acumulator();
		memory = new Memory(memoryString);
		followInstuctionSet();

	}

	private void followInstuctionSet() {
		int increment = 0;
		int counter = 0;
		for (int i = 0; i < memory.size(); i += increment) {
			switch (memory.getMemoryAtPosition(i).getDecimalValue()) {
			case 0:
				increment = 3;
				load(i);
				break;
			case 1:
				increment = 3;
				store(i);
				break;
			case 2:
				increment = 1;
				swap();
				break;
			case 3:
				increment = 1;
				add();
				break;
			case 4:
				increment = 1;
				addOne();
				break;
			case 5:
				increment = 1;
				minusOne();
				break;
			case 6:

				if (a.getDecimalValue() == 0) {
					i = jump(i);
					increment = 0;
				} else {
					increment = 3;
				}
				break;
			case 7:
				increment = 0;
				i = jump(i);
				break;
			case 8:
				increment = 256;
				break;
			}
		}
		System.out.println();
		System.out.print(memory);
	}

	private void load(int i) {
		int memPos = memoryPositionOfNextTwoWords(i);
		a.setValue(memory.getMemoryAtPosition(memPos));

	}

	private void store(int i) {
		int memPos = memoryPositionOfNextTwoWords(i);
		memory.setMemoryAtPosition(memPos, a.getWord());
	}

	private void swap() {
		Word temp = a.getWord();
		a.setValue(b.getWord());
		b.setValue(temp);
	}

	private void add() {
		int decSum = (a.getDecimalValue() + b.getDecimalValue());
		Address sumAddress = new Address(decSum);

		// split into two units the first in A the second in B
		a.setValue(sumAddress.getWordOne());
		b.setValue(sumAddress.getWordTwo());
	}

	private void addOne() {
		int value = a.getDecimalValue();
		if (value == 15) {
			a.setValue(0);
		} else {
			a.setValue((value + 1));
		}

	}

	private void minusOne() {
		int value = a.getDecimalValue();
		if (value == 0) {
			a.setValue(15);

		} else {
			a.setValue((value - 1));
		}
	}

	private int jump(int i) {
		// next command to be executed is location specified by
		// argument
		return memoryPositionOfNextTwoWords(i);

	}

	/*
	 * get the next two values in hex and return the memory position they
	 * represent
	 */
	private int memoryPositionOfNextTwoWords(int i) {
		String hexRegistor = (memory.getMemoryAtPosition(i + 1).getHexValue() + memory
				.getMemoryAtPosition(i + 2).getHexValue());
		return Integer.valueOf(hexRegistor, 16);
	}

}
