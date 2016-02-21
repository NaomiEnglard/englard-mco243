package englard.microprocessor;

import java.io.IOException;

public class Memory {

	// private Word[] memory;
	private Word[] memory;

	public Memory(String memoryLine) throws IOException {
		memory = new Word[256];
		convertCharArrayToWordArray(memoryLine.toCharArray());
	}

	private void convertCharArrayToWordArray(char[] readInChar) {
		for (int i = 0; i < readInChar.length; i++) {
			memory[i] = new Word(Integer.parseInt(
					String.valueOf(readInChar[i]), 16));
		}
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (Word w : memory) {
			builder.append(w);
		}
		return builder.toString();
	}

	public Word[] getMemoryContenet() {
		return this.memory;
	}

	public int size() {
		return 256;
	}

	public Word getMemoryAtPosition(int index) {
		return this.memory[index];
	}

	public void setMemoryAtPosition(int index, Word word) {
		this.memory[index] = word;
	}
}
