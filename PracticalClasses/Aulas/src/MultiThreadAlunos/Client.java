package MultiThreadAlunos;

import java.io.*;
import java.net.*;

public class Client {
	public static void main(String[] args) throws IOException {
		String serverAddress = "127.0.0.1";
		int portNumber = 2222;

		try {
			Socket socket = new Socket(serverAddress, portNumber);
			
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

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
		            String choice = Ler.umaString();	            
	
					out.println(choice);
					out.flush();

					String nome = "";
					switch (choice) {
			            case "1":
			            	System.out.print("\nNumero: ");
			            	String numero = Ler.umaString();
			            	out.println(numero);
			            	out.flush();
			            	
			            	System.out.print("Nome: ");
			            	nome = Ler.umaString();
			            	out.println(nome);
			            	out.flush();
			            	
			            	System.out.print("Curso: ");
			            	String curso = Ler.umaString();
			            	out.println(curso);
			            	out.flush();
			            	
			            	System.out.print("Telemovel: ");
			            	String telemovel = Ler.umaString();
			            	out.println(telemovel);
			            	out.flush();
			            	
			            	System.out.print("Email: ");
			            	String email = Ler.umaString();
			            	out.println(email);
			            	out.flush();
			                break;
			            case "4":
			            	System.out.print("\nNome: ");
			            	nome = Ler.umaString();
			            	out.println(nome);
			            	out.flush();
			            	break;
			            case "5":
			            	System.out.println("Closing connection...");
					        socket.close();
					        break;
					}

					while ((inputLine = in.readLine()) != null) {
						if (inputLine.equals("EOF")) { break; }
						System.out.println(inputLine);
					}
				}
			}
		} catch (IOException e) {
			System.err.println("Error: " + e.getMessage());
		}
	}
}
