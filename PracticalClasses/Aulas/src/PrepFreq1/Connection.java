package PrepFreq1;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Connection extends Thread {
	private Socket s;
	private int numConnections;
	
	public Connection (Socket s, int numConnections) {
		super();
		this.s = s;
		this.numConnections = numConnections;
		start();
	}
	
	public void run() {
		try {
			ObjectInputStream is = new ObjectInputStream(this.s.getInputStream());
			ObjectOutputStream os = new ObjectOutputStream(this.s.getOutputStream());
			
			int choice;
			while ((choice = (int) is.readObject()) != 3) {
				if (choice == 1) {
					os.writeObject("Sou o servidor e tu és o cliente nº " + this.numConnections + "!");
				} else if (choice == 2) {
					os.writeObject("Vemo-nos às " + Math.round((Math.random() * 24)) + "h!");
				}
				os.flush();
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
