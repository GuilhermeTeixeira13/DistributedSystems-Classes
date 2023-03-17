package MultiThreaded;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	private ServerSocket ss;
	private Socket s;
	private Connection c;
	
	public Server(){
		Premio p = new Premio();
		try {
			ss = new ServerSocket (5432);
		}
		catch ( IOException e){
			System.out.println(e.getMessage());
		}
		try {
			while (true) {
				s = ss.accept();
				c = new Connection (s, p); 
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	public static void main (String args[]){
		Server server = new Server();
	}
} 
