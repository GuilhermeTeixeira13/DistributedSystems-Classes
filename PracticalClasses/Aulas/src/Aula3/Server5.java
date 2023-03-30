package Aula3;

import java.net.*;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.io.*;
import java.lang.Math;

public class Server5 {
	static String svHeader = "\n_______________________\n|        SERVER       |\n\n";
	static String svFooter = "|                     |\n_______________________\nEOF";
	
	static ArrayList<Aluno> listaAlunos = readAlunosFromFile();
	static int numAcessos = 0;
	
	// Initialize a Semaphore with a maximum of 5 permits (client connections)
	static Semaphore sem = new Semaphore(5);
	
	// Initialize a thread pool with a fixed number of 5 threads
	static ExecutorService pool = Executors.newFixedThreadPool(5);

	
    public static void main(String[] args) throws IOException, InterruptedException {
        int portNumber = 2222;

        try {
        	// Create a new ServerSocket on the specified port number
            ServerSocket serverSocket = new ServerSocket(portNumber);
            
            System.out.println("Server started on port " + portNumber);

            while (true) {
            	// Wait for an incoming client connection
            	Socket clientSocket = serverSocket.accept();
            	
            	 // Try to acquire a permit from the semaphore
            	if (sem.tryAcquire()) {
            		// If successful, increment the number of accesses and print a message{ 
                    numAcessos++;
                    System.out.println("Client " + clientSocket.getInetAddress().getHostAddress() + " connected");
                    
                    // Create a new PrintWriter to send a message to the client
                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                    out.println("Connected to the server " + serverSocket.getInetAddress().getHostAddress());
                    
                    // Create a new thread to handle the client connection using the ClientHandler class
                    pool.execute(new ClientHandler(clientSocket));
                } else {
                	// If the maximum number of connections has been reached, reject the connection and send a message to the client
                    System.out.println("Maximum number of clients reached, rejecting connection...");
                    
                    // Close the connection with the client that tried to connect
                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                    out.println("Server reached maximum capacity, try again later.");
                    clientSocket.close();
                }
            }
            
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static class ClientHandler extends Thread {
        private Socket clientSocket;

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        public void run() {
            try {
            	// create BufferedReader and PrintWriter to read and write data to and from the client
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                
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
	    	            	out.println(svHeader+"Número de acessos ao servidor até ao momento: " + numAcessos + "\n\n"+svFooter);
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
                
                // release a semaphore used for synchronization and close client socket
                sem.release();
                clientSocket.close();
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