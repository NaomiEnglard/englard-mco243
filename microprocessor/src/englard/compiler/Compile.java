package englard.compiler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Compile {

	public static void main(String[] args) {
		BufferedReader read = new BufferedReader(new InputStreamReader(
				System.in));
		String command;
		//string builder and token should be private
		try {
			while ((command = read.readLine()) != null) {
				String[] token = command.split(" ");
				switch (token[0]) {
				case "LD":
					load();
					break;
				case "ST":
					store();
					break;
				case "SWP":
					swap();
					break;
				case "ADD":
					add();
					break;
				case "INC":
					increment();
					break;
				case "DEC":
					decrement();
					break;
				case "BZ":
					skip();
					break;
				case "BR":
					skip2();
					break;
				case "STP":
					stop();
					break;
				case "DATA":
					data();
					break;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void data() {
		// TODO Auto-generated method stub
		
	}

	private static void stop() {
		// TODO Auto-generated method stub
		
	}

	private static void skip2() {
		// TODO Auto-generated method stub

	}

	private static void skip() {
		// TODO Auto-generated method stub

	}

	private static void decrement() {
		// TODO Auto-generated method stub

	}

	private static void increment() {
		// TODO Auto-generated method stub

	}

	private static void add() {
		// TODO Auto-generated method stub

	}

	private static void swap() {
		// TODO Auto-generated method stub

	}

	private static void store() {
		// TODO Auto-generated method stub

	}

	private static void load() {
		// TODO Auto-generated method stub

	}
}
