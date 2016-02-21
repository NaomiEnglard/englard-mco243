package englard.microprocessor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Memory {

	// private Word[] memory;
	private Word[] memory;

	public Memory(String memoryFileName) throws IOException {
		memory = new Word[256];
		readInInitialMemory(memoryFileName);
	}

	private void readInInitialMemory(String fileName) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader(fileName));
		char[] readInChar = new char[256];
		in.read(readInChar);
		in.close();
		convertCharArrayToWordArray(readInChar);

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
