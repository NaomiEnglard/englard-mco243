package englard.compiler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import javax.xml.bind.annotation.adapters.HexBinaryAdapter;


public class Compiler {

	private String[] command;
	private StringBuilder output;

	public Compiler(BufferedReader read) throws IOException {
		output = new StringBuilder();
		// BufferedReader read = new BufferedReader(new InputStreamReader(
		// System.in));
		String readLine;
		while (output.length() != 256) {
			readLine = read.readLine();
			command = readLine.split(" ");
			switch (command[0]) {
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

	}

	
	public String getHexCommnad() {
		return output.toString();
	}

	private void data() {
		output.append(command[1]);
		// output.append("%");
	}

	private void stop() {
		output.append("8");
	}

	private void skip2() {
		output.append("7");
		appendNextTwoNumbers();
	}

	private void skip() {
		output.append("6");
		appendNextTwoNumbers();

	}

	private void decrement() {
		output.append("5");

	}

	private void increment() {
		output.append("4");

	}

	private void add() {
		output.append("3");

	}

	private void swap() {
		output.append("2");

	}

	private void store() {
		output.append("1");
		appendNextTwoNumbers();

	}

	private void load() {
		output.append("0");
		appendNextTwoNumbers();
	}

	private void appendNextTwoNumbers() {
		String num = Integer.toHexString(Integer.parseInt(this.command[1]));
		if (num.length() == 1) {
			output.append("0");
			output.append(num.charAt(0));
		} else {
			output.append(num.charAt(0));
			output.append(num.charAt(1));
		}
	}

	public static void main(String[] args) {
		try {
			BufferedReader read = new BufferedReader(new InputStreamReader(
					System.in));
			Compiler c = new Compiler(read);
			System.out.println(c.getHexCommnad());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
