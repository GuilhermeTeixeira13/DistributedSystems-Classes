package MultiThreadAlunos;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import Aula3.Aluno;

public class Connection extends Thread {
	private ServerSocket serverSocket;
	Socket clientSocket = null;
	private int []numeroAcessos;
	private ArrayList<Aluno> listaAlunos;
	
	private static final String SERVER_HEADER = "\n_______________________\n|        SERVER       |\n\n";
	private static final String SERVER_FOOTER = "|                     |\n_______________________\nEOF";

	public Connection(ServerSocket serverSocket, int[] numeroAcessos, ArrayList<Aluno> listaAlunos) {
		super();
		this.serverSocket = serverSocket;
		this.numeroAcessos = numeroAcessos;
		this.listaAlunos = listaAlunos;
		start();
	}

	public void run() {
		try {
			while(true) {
				// Wait for a client to connect
				clientSocket = serverSocket.accept();

				// Increment the number of accesses to the server
				synchronized (numeroAcessos) {
					numeroAcessos[0]++;
				}

				// Print client information
				System.out.println("Client " + clientSocket.getInetAddress().getHostAddress() + " connected");

				// Setup input and output streams
				BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

				// Send welcome message to the client
				out.println("Connected to the server " + serverSocket.getInetAddress().getHostAddress());

				String inputLine;

				// Read input from the client
				while ((inputLine = in.readLine()) != null) {
					System.out.println("Client: Option=" + inputLine);

					if (inputLine.equalsIgnoreCase("5")) {
						break;
					}

					switch (inputLine) {
					case "1":
						// Register a new student
						synchronized (listaAlunos) {
							String numero = in.readLine();
							String nome = in.readLine();
							String curso = in.readLine();
							String telemovel = in.readLine();
							String email = in.readLine();

							// Check if the student is already registered
							boolean isDuplicate = verificaRepetidos(Integer.parseInt(numero), listaAlunos);

							if (isDuplicate) {
								out.println(SERVER_HEADER + "Aluno repetido. Não foi registado novamente. Número de alunos registados: " + listaAlunos.size() + ".\n\n" + SERVER_FOOTER);
							} else {
								Aluno novo = new Aluno(Integer.parseInt(numero), nome, curso, Integer.parseInt(telemovel), email);
								listaAlunos.add(novo);
								writeAlunosToFile(listaAlunos);
								out.println(SERVER_HEADER + "Registado com sucesso. Número de alunos registados: " + listaAlunos.size() + ".\n\n" + SERVER_FOOTER);
								out.flush();
							}
						}
						break;
					case "2":
						// Send the list of registered students to the client
						out.println(outputListaDeAlunos(listaAlunos));
						out.flush();
						break;
					case "3":
						// Send the number of accesses to the server to the client
						out.println(SERVER_HEADER + "Numero de acessos ao servidor ate ao momento: " + numeroAcessos[0] + "\n\n" + SERVER_FOOTER);
						out.flush();
						break;
					case "4":
						// Send the phone number of a specific student to the client
						String nome = in.readLine();
						out.println(devolveNumContacto(nome, listaAlunos));
						out.flush();
						break;	    	    	             
					}
				}          
			}   

		}
		catch (IOException e) {
		}
		finally {
			try {
				clientSocket.close();
				System.out.println("Client " + clientSocket.getInetAddress().getHostAddress() + " disconnected");
				numeroAcessos[0] = numeroAcessos[0] - 1;
			} catch (IOException e) {

			}
		}
	}
	
	private static Boolean verificaRepetidos(int numAluno, ArrayList<Aluno> alunos) {	
    	for (Aluno aluno : alunos) {
    	    if (aluno.getNumero() == numAluno)
    	    	return true;
    	}
    	return false;
	}
    
    private static String outputListaDeAlunos(ArrayList<Aluno> alunos) {
    	String lista = SERVER_HEADER + "-----------------------\n";
    	lista += "         ALUNOS        \n";
    	lista += "-----------------------\n";
    	for (Aluno aluno : alunos) {
    		lista += "Número: " + aluno.getNumero() + "\n";
    		lista += "Nome: " + aluno.getNome() + "\n";
    		lista += "Curso: " + aluno.getCurso() + "\n";
    		lista += "Telemóvel: " + aluno.getTelemovel() + "\n";
    		lista += "Email: " + aluno.getEmail() + "\n";
    	    lista += "-----------------------\n";
    	}
    	lista += "\n" + SERVER_FOOTER;
    	return lista;
	}
    
    private static String devolveNumContacto(String nome, ArrayList<Aluno> alunos) {
    	String lista = SERVER_HEADER;
    	lista += "-----------------------\n";
    	for (Aluno aluno : alunos) {
    		if(aluno.getNome().equals(nome)) {
    			lista += "Número: " + aluno.getNumero() + "\n";
    			lista += "Telemóvel: " + aluno.getTelemovel() + "\n";
    			lista += "-----------------------\n\n";
    		}
    	}
    	lista += SERVER_FOOTER;
    	return lista;
	}
    
    private static void writeAlunosToFile(ArrayList<Aluno> alunos) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("C:\\Users\\gui\\Desktop\\Programação\\DistributedSystems-UBI\\PracticalClasses\\Aulas\\src\\MultiThreadAlunos\\alunos.ser"))) {
            oos.writeObject(alunos);
        } catch (IOException e) {
            System.err.println("Error writing list of students: " + e.getMessage());
        }
    }
}
