package Aula3;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client5 {
	public static void main(String[] args) throws IOException {
		String serverAddress = "127.0.0.1";
		int portNumber = 2222;

		try {
			Socket socket = new Socket(serverAddress, portNumber);
			
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader keyboardIn = new BufferedReader(new InputStreamReader(System.in));
			String inputLine = in.readLine();

			System.out.println(inputLine);
			if (inputLine.equals("Server reached maximum capacity, try again later.")) {
				System.out.println("Closing connection...");
		        socket.close();
			} else {
				while (true) {
					System.out.println();
		            System.out.println("1. Registar aluno");
		            System.out.println("2. Consultar alunos");
		            System.out.println("3. Consultar acessos");
		            System.out.println("4. Ver dados de aluno");
		            System.out.println("5. Quit");
		            System.out.print("Enter your choice (1-4): ");
		            String choice = keyboardIn.readLine();	            
			
					// Envia escolha para o servidor
					out.println(choice);
					out.flush();
					
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

					while ((inputLine = in.readLine()) != null) {
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
