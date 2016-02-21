package englard.microprocessor;

import java.io.IOException;

public class Processor {

	private Acumulator a;
	private Acumulator b;
	private Memory memory;
	int test = 0;

	/*
	 * @param fileName pass in the file with the instuction set and memory
	 */
	public Processor(String fileName) throws IOException {

		a = new Acumulator();
		b = new Acumulator();
		memory = new Memory(fileName);

	}

	private void followInstuctionSet() {
		// go through each word
		int amount = 0; // keep track of how many you words are read in the case
						// statment
		int memPos;
		String hexRegistor;
		for (int i = 0; i < memory.size(); i += amount) {
			switch (memory.getMemoryAtPosition(i).getDecimalValue()) {
			case 0:
				// read in 2 more words - and load acumulator A with content of
				// that registar
				amount = 3;
				hexRegistor = (memory.getMemoryAtPosition(i + 1).getHexValue() + memory
						.getMemoryAtPosition(i + 2).getHexValue());
				memPos = Integer.valueOf(hexRegistor, 16);
				a.setValue(memory.getMemoryAtPosition(memPos));
				break;
			case 1:
				// write contect of acumulator a to memPos specified by next two
				amount = 3;
				hexRegistor = (memory.getMemoryAtPosition(i + 1).toString() + memory
						.getMemoryAtPosition(i + 2).toString());
				memPos = Integer.valueOf(hexRegistor, 16);
				memory.setMemoryAtPosition(memPos, a.getWord());
				break;
			case 2:
				// swap values in Acumulator a and b
				amount = 1;
				Word temp = a.getWord();
				a.setValue(b.getWord());
				b.setValue(temp);
				break;
			case 3:
				// add a and b - low word in a high word in b
				amount = 1;
				int decSum = (a.getDecimalValue() + b.getDecimalValue());
				Address sumAddress = new Address(decSum);

				// split into two units the first in A the second in B
				a.setValue(sumAddress.getWordOne());
				b.setValue(sumAddress.getWordTwo());
				break;
			case 4:
				amount = 1;
				int value = a.getDecimalValue();
				if (value == 15) {
					a.setValue(0);
				} else {
					a.setValue((value + 1));
				}
				break;
			case 5:
				amount = 1;
				value = a.getDecimalValue();
				if (value == 0) {
					a.setValue(15);

				} else {
					a.setValue((value - 1));
				}
				break;
			case 6:
				amount = 3;
				if (a.getDecimalValue() == 0) {
					// next command to be executed is location specified by
					// argument
					hexRegistor = (memory.getMemoryAtPosition(i + 1).toString() + memory
							.getMemoryAtPosition(i + 2).toString());
					memPos = Integer.valueOf(hexRegistor, 16);
					i = memPos; // jump to commad specified

				}
				break;
			case 7:

				amount = 0;
				// next command to be executed is location specified by
				// argument
				hexRegistor = (memory.getMemoryAtPosition(i + 1).toString() + memory
						.getMemoryAtPosition(i + 2).toString());
				memPos = Integer.valueOf(hexRegistor, 16);
				i = memPos; // jump to command specified

				break;
			case 8:
				amount = 256;
				break;

			}
		}
	}

	public static void main(String[] args) throws IOException {

		Processor p = new Processor("memory.txt");
		System.out.print(p.memory);
		p.followInstuctionSet();
		System.out.println();
		System.out.print(p.memory);

	}

}
