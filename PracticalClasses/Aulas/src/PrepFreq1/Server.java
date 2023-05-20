package PrepFreq1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	private ServerSocket ss;
	private Socket s;
	private Connection c;
	private int numConnections = 0;
	
	public Server() {
		try {
			ss = new ServerSocket (2222);
		}
		catch ( IOException e){
			System.out.println(e.getMessage());
		}
		try {
			while (true) {
				numConnections++;
				s = ss.accept();
				c = new Connection ( s, numConnections ); 
			} 
		
		} catch(IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void main (String args[]){
		Server server = new Server();
	}

}
