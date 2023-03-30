package Aula6;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor1 {
	private ServerSocket ss;
	private Socket s;
	private Connection1 c;
	public Servidor1(){
		try
		{
			ss = new ServerSocket (5432);
		}
		catch ( IOException e){
			
		}
		try {
			while (true) {
				s = ss.accept();
				c = new Connection1 (s);
			}
		}
		catch (IOException e) {}
	}
	public static void main (String args[]){
		Servidor1 dataHora = new Servidor1();
	}
}