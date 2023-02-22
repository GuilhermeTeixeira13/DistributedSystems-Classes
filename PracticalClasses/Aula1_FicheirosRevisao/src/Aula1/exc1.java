package Aula1;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class exc1 {
	public static void main (String args[]){
		 BufferedWriter bw;
		 try {
		 bw = new BufferedWriter ( new FileWriter ("C:\\\\\\\\Users\\\\\\\\gui\\\\\\\\eclipse-workspace\\\\\\\\Aula1_FicheirosRevisao\\\\\\\\src\\\\\\\\Aula1\\\\\\\\teste1.txt"));
		 bw.write("1");
		 bw.newLine();
		 bw.write("2");
		 bw.flush();
		 bw.close();
	 }
	 catch (IOException e){
		 System.out.println(e.getMessage());
	 }
	}
}
