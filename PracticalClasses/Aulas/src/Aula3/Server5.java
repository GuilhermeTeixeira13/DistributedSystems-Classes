package Aula3;

import java.net.*;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import Aula6.Connection1;

import java.io.*;

// Modificar a forma como fazemos a pool de threads - Do lado do cliente

// numAcessos

public class Server5 {
	static String svHeader = "\n_______________________\n|        SERVER       |\n\n";
	static String svFooter = "|                     |\n_______________________\nEOF";
	
	static ArrayList<Aluno> listaAlunos = readAlunosFromFile();

	private ClientHandler c1;
	private ClientHandler c2;
	private ClientHandler c3;
	
	private int []numeroAcessos;
	
	public Server5 () {
		int portNumber = 5345;
        ServerSocket serverSocket = null;
        
        try {
        	// Create a new ServerSocket on the specified port number
            serverSocket = new ServerSocket(portNumber);
            
            System.out.println("Server started on port " + portNumber);  
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
        
        numeroAcessos = new int[1];
		numeroAcessos[0] = 0;
        
        c1 = new ClientHandler (serverSocket, numeroAcessos);
        c2 = new ClientHandler (serverSocket, numeroAcessos); 
        c3 = new ClientHandler (serverSocket, numeroAcessos); 
	}
	
    public static void main(String[] args) throws IOException, InterruptedException {
    	Server5 s = new Server5();
    }

    private static class ClientHandler extends Thread {
        private ServerSocket serverSocket;
        Socket clientSocket = null;
        private int []numeroAcessos;

        public ClientHandler(ServerSocket serverSocket, int[] numeroAcessos) {
        	super();
            this.serverSocket = serverSocket;
            this.numeroAcessos = numeroAcessos;
            start();
        }

        public void run() {
            try {
            	while(true) {
            		clientSocket = serverSocket.accept();
            		numeroAcessos[0] = numeroAcessos[0] + 1;
                	
                    System.out.println("Client " + clientSocket.getInetAddress().getHostAddress() + " connected");
                	
                	// create BufferedReader and PrintWriter to read and write data to and from the client
                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                    
                    out.println("Connected to the server " + serverSocket.getInetAddress().getHostAddress());
                    
                    String inputLine;
                    
                    // loop to read messages from the client until the client sends the message "5" or the connection is closed
                    while ((inputLine = in.readLine()) != null) {
                    	// print the received message to the console
                    	System.out.println("Client: Option=" + inputLine);
                    	
                    	// if the received message is "5", break out of the loop and close the connection
                    	if (inputLine.equalsIgnoreCase("5")) {
        			        break;
        			    }
                    	
                    	String nome = "";
                    	
                    	// use a switch statement to handle different types of messages
                    	switch (inputLine) {
    	    	            case "1":
    	    	            	synchronized(listaAlunos) {
    	    	            		// read additional data from the client
    	    	            		
    	    	            		String numero = in.readLine();
    	    	            		nome = in.readLine();
    	    	            		String curso = in.readLine();
    	    	            		String telemovel = in.readLine();
    	    	            		String email = in.readLine();

    	    	            		// check if the student is already registered
    	    	            		Boolean alunosRepetidos = verificaRepetidos(Integer.parseInt(numero), listaAlunos);

    	    	            		// if the student is already registered, send a message to the client indicating that the registration failed
    	    	            		if(alunosRepetidos) {
    	    	            			out.println(svHeader + "Aluno repetido. Não foi registado novamente. Número de alunos registados: " + listaAlunos.size() + ".\n\n"+svFooter);
    	    	            		}
    	    	            		// if the student is not already registered, add the student to the list and send a message to the client indicating that the registration succeeded
    	    	            		else {
    	    	            			Aluno novo = new Aluno(Integer.parseInt(numero), nome, curso, Integer.parseInt(telemovel), email);
    	    	            			listaAlunos.add(novo);
    	    	            			writeAlunosToFile(listaAlunos);
    	    	            			out.println(svHeader+"Registado com sucesso. Número de alunos registados: " + listaAlunos.size() + ".\n\n"+svFooter);
    	    	            			out.flush();
    	    	            		}
    	    	            	}
    	    	                break;
    	    	            case "2":
    	    	            	// send a list of students to the client
    	    	            	out.println(outputListaDeAlunos(listaAlunos));
    	    	            	out.flush();
    	    	                break;
    	    	            case "3":
    	    	            	// send the number of accesses to the server to the client
    	    	            	out.println(svHeader+"Número de acessos ao servidor até ao momento: " + numeroAcessos[0] + "\n\n"+svFooter);
    	    	            	out.flush();
    	    	                break;
    	    	            case "4":
    	    	            	// read additional data from the client
    	    	            	nome = in.readLine();
    	    	            	
    	    	            	// send the student's phone number to the client
    	    	            	out.println(devolveNumContacto(nome, listaAlunos));
    	    	            	out.flush();
    	    	                break;	    	    	             
                    	}
                    } 
                    
                    System.out.println("Client " + clientSocket.getInetAddress().getHostAddress() + " disconnected");
                    
                    clientSocket.close();
                }   
    
            }
            catch (IOException e) {
            }
        }
    }
    
    private static ArrayList<Aluno> readAlunosFromFile() {	
		ArrayList<Aluno> alunos = new ArrayList<Aluno>();
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("C:\\Users\\guite\\OneDrive\\Área de Trabalho\\DistributedSystems-UBI\\PracticalClasses\\Aulas\\src\\Aula3\\alunos.ser"))) {
			alunos = (ArrayList<Aluno>) ois.readObject();
        } catch (IOException e) {
            System.err.println("Error reading lines of text: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("Error reading lines of text: " + e.getMessage());
        }
        return alunos;
	}
    
    private static Boolean verificaRepetidos(int numAluno, ArrayList<Aluno> alunos) {	
    	for (Aluno aluno : alunos) {
    	    if (aluno.getNumero() == numAluno)
    	    	return true;
    	}
    	return false;
	}
    
    private static String outputListaDeAlunos(ArrayList<Aluno> alunos) {
    	String lista = svHeader + "-----------------------\n";
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
    	lista += "\n" + svFooter;
    	return lista;
	}
    
    private static String devolveNumContacto(String nome, ArrayList<Aluno> alunos) {
    	String lista = svHeader;
    	lista += "-----------------------\n";
    	for (Aluno aluno : alunos) {
    		if(aluno.getNome().equals(nome)) {
    			lista += "Número: " + aluno.getNumero() + "\n";
    			lista += "Telemóvel: " + aluno.getTelemovel() + "\n";
    			lista += "-----------------------\n\n";
    		}
    	}
    	lista += svFooter;
    	return lista;
	}
    
    private static void writeAlunosToFile(ArrayList<Aluno> alunos) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("C:\\\\Users\\\\guite\\\\OneDrive\\\\Área de Trabalho\\\\DistributedSystems-UBI\\\\PracticalClasses\\\\Aulas\\\\src\\\\Aula3\\\\alunos.ser"))) {
            oos.writeObject(alunos);
        } catch (IOException e) {
            System.err.println("Error writing list of students: " + e.getMessage());
        }
    }
}