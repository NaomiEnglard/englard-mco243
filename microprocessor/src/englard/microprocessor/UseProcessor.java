package englard.microprocessor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UseProcessor {
	public static void main(String[] args) throws IOException {

		// BufferedReader in = new BufferedReader(new
		// FileReader("mach.in.txt"));
		// BufferedReader in = new BufferedReader(new
		// FileReader("mach.in.txt"));

		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

		String line;
		while ((line = input.readLine()) != null) {
			Processor p = new Processor(line);

		}

		// in.close();
		input.close();
	}
}
