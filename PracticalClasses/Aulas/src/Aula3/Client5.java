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

			System.out.println("Connected to server " + socket.getInetAddress().getHostAddress());

			String inputLine;
			while (true) {
				System.out.println();
	            System.out.println("1. Registar aluno");
	            System.out.println("2. Consultar alunos");
	            System.out.println("3. Consultar acessos");
	            System.out.println("4. Ver dados de aluno");
	            System.out.println("5. Quit");
	            System.out.print("Enter your choice (1-4): ");
	            Scanner scanner = new Scanner(System.in);
	            String choice = scanner.nextLine();	            
		
				// Envia escolha para o servidor
				out.println(choice);
				out.flush();
				
				String nome = "";
				switch (choice) {
		            case "1":
		            	System.out.print("Número: ");
		            	String numero = scanner.nextLine();
		            	out.println(numero);
		            	out.flush();
		            	
		            	System.out.print("Nome: ");
		            	nome = scanner.nextLine();
		            	out.println(nome);
		            	out.flush();
		            	
		            	System.out.print("Curso: ");
		            	String curso = scanner.nextLine();
		            	out.println(curso);
		            	out.flush();
		            	
		            	System.out.print("Telemóvel: ");
		            	String telemovel = scanner.nextLine();
		            	out.println(telemovel);
		            	out.flush();
		            	
		            	System.out.print("Email: ");
		            	String email = scanner.nextLine();
		            	out.println(email);
		            	out.flush();
		                break;
		            case "4":
		            	System.out.print("Nome: ");
		            	nome = scanner.nextLine();
		            	out.println(nome);
		            	out.flush();
		            	break;
		            case "5":
		            	System.out.println("Closing connection...");
				        socket.close();
				        break;
				}

				// Recebe resposta do servidor
				inputLine = in.readLine();
				while ((inputLine = in.readLine()) != null) {
				    System.out.println(inputLine);
				}
			}
		} catch (IOException e) {
			System.err.println("Error: " + e.getMessage());
		}
	}
}
