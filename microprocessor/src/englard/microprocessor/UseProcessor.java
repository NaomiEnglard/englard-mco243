package englard.microprocessor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class UseProcessor {
	public static void main(String[] args) throws IOException {

		//BufferedReader in = new BufferedReader(new FileReader("mach.in.txt"));
		BufferedReader in = new BufferedReader(new FileReader("mach.in.txt"));

		String line;
		while ((line = in.readLine()) != null) {
			Processor p = new Processor(line);
			

		}

		in.close();
	}
}
