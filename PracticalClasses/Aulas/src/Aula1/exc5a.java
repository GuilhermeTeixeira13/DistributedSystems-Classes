package Aula1;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class exc5a {

	public static void main(String[] args) {
		Person p1 = new Person ("Gui", 20, "guilhermeteixeira1313@gmail.com", "935694907");
		Person p2 = new Person ("João", 20, "joao@gmail.com", "978673456");
		
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("C:\\\\\\\\Users\\\\\\\\gui\\\\\\\\eclipse-workspace\\\\\\\\Aula1_FicheirosRevisao\\\\\\\\src\\\\\\\\Aula1\\\\\\\\person.ser"))) {
            oos.writeObject(p1);
            oos.writeObject(p2);
            System.out.println("Persons objects written to file.");
        } catch (IOException e) {
            System.err.println("Error writing Person object: " + e.getMessage());
        }
	}

}
