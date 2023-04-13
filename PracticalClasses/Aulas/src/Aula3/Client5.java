package Aula3;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client5 {
	public static void main(String[] args) throws IOException {
		// Specifies the IP address and port number to connect to the server
		String serverAddress = "127.0.0.1";
		int portNumber = 5345;

		try {
			// Creates a new socket with the specified server address and port number
			Socket socket = new Socket(serverAddress, portNumber);
			
			// Creates a buffered reader to read input from the server
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			// Creates a print writer to send output to the server
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			// Creates a buffered reader to read input from the keyboard
			BufferedReader keyboardIn = new BufferedReader(new InputStreamReader(System.in));
			
			// Reads the first message sent by the server and prints it to the console
			String inputLine = in.readLine();
			System.out.println(inputLine);
			
			// If the server reached maximum capacity, closes the connection and exits the program
			if (inputLine.equals("Server reached maximum capacity, try again later.")) {
				System.out.println("Closing connection...");
		        socket.close();
			} else {
				// Otherwise, enters a loop to continuously prompt the user for input
				while (true) {
					System.out.println();
		            System.out.println("1. Registar aluno");
		            System.out.println("2. Consultar alunos");
		            System.out.println("3. Consultar acessos");
		            System.out.println("4. Ver dados de aluno");
		            System.out.println("5. Quit");
		            System.out.print("Enter your choice (1-4): ");
		            String choice = keyboardIn.readLine();	            
			
		            // Sends the user's input to the server
					out.println(choice);
					out.flush();
					
					// If the user chooses to register a new student
					String nome = "";
					switch (choice) {
			            case "1":
			            	System.out.print("\nNúmero: ");
			            	String numero = keyboardIn.readLine();
			            	out.println(numero);
			            	out.flush();
			            	
			            	System.out.print("Nome: ");
			            	nome = keyboardIn.readLine();
			            	out.println(nome);
			            	out.flush();
			            	
			            	System.out.print("Curso: ");
			            	String curso = keyboardIn.readLine();
			            	out.println(curso);
			            	out.flush();
			            	
			            	System.out.print("Telemóvel: ");
			            	String telemovel = keyboardIn.readLine();
			            	out.println(telemovel);
			            	out.flush();
			            	
			            	System.out.print("Email: ");
			            	String email = keyboardIn.readLine();
			            	out.println(email);
			            	out.flush();
			                break;
			            case "4":
			            	System.out.print("\nNome: ");
			            	nome = keyboardIn.readLine();
			            	out.println(nome);
			            	out.flush();
			            	break;
			            case "5":
			            	System.out.println("Closing connection...");
					        socket.close();
					        break;
					}

					// Reads and prints any output sent by the server
					while ((inputLine = in.readLine()) != null) {
						// The server sends "EOF" to indicate the end of a message
						if (inputLine.equals("EOF")) {
					        break;
					    }
						System.out.println(inputLine);
					}
				}
			}
		} catch (IOException e) {
			System.err.println("Error: " + e.getMessage());
		}
	}
}
