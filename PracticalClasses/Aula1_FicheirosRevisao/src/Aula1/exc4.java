package Aula1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class exc4 {

	public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\\\Users\\\\gui\\\\eclipse-workspace\\\\Aula1_FicheirosRevisao\\\\src\\\\Aula1\\\\teste2.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

}
