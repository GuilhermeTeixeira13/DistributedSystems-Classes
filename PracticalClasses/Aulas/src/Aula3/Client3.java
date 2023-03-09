package Aula3;

import java.io.*;
import java.net.*;

public class Client3 {
	public static void main(String[] args) throws IOException {
		String serverAddress = "127.0.0.1";
		int portNumber = 2222;

		try {
			Socket socket = new Socket(serverAddress, portNumber);
			
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader keyboardIn = new BufferedReader(new InputStreamReader(System.in));

			System.out.println("Connected to server " + socket.getInetAddress().getHostAddress());

			String inputLine;
			while (true) {
				System.out.print("Enter message to send to server: ");
				String outputLine = keyboardIn.readLine();
		
				out.println(outputLine);
				out.flush();

				inputLine = in.readLine();
				System.out.println("Server: " + inputLine);
			}
		} catch (IOException e) {
			System.err.println("Error: " + e.getMessage());
		}
	}
}
