package Aula1;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class exc8 {

	public static void main(String[] args) {
		try (Scanner scanner = new Scanner(System.in);
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("C:\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\Users\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\gui\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\eclipse-workspace\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\Aula1_FicheirosRevisao\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\src\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\Aula1\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\books.ser"))) {

			ArrayList<String> books = new ArrayList<String>();
			while (true) {
				System.out.print("Enter a book name (or 'done' to finish): ");
				String book = scanner.nextLine();
				if (book.equals("done")) {
					break;
				}
				books.add(book);
			}
			
			
			oos.writeObject(books);
			System.out.println("List of books written to file.");
		} catch (IOException e) {
			System.err.println("Error writing lines of text: " + e.getMessage());
		}
	}

}
