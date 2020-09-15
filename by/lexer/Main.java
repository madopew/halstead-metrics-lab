package by.lexer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		StringBuilder sb = new StringBuilder("");
		int amount = 0;
		try(BufferedReader br = new BufferedReader(new FileReader(new File("C:\\Users\\Madi\\Desktop\\lexer\\" + scanner.nextLine() + ".kt")))) {
			String currentLine = "";
			while((currentLine = br.readLine()) != null) {
				sb.append(currentLine).append('\n');
				amount++;
			}
		} catch (Exception e) {
			System.err.println(e.toString());
		}
		System.out.println(new Parser(sb.toString()).toString());
		System.out.printf("Amount of lines - %d\n", amount);
		Metric m = new Metric(sb.toString());
		System.out.printf("nu1 - %d\nnu2 - %d\nn1 - %d\nn2 - %d\nnu - %d\nn - %d\nv - %d\n", m.getNu1(), m.getNu2(), m.getN1(), m.getN2(), m.getNu(), m.getN(), m.getV());
	}
}
