package MultiThreadAlunos;

import java.net.*;

import java.util.ArrayList;

import Aula3.Aluno;

import java.io.*;


public class Server {
	// A list of students read from a file
	private ArrayList<Aluno> listaAlunos = readAlunosFromFile();

	// Three connections to handle client requests
	private Connection c1;
	private Connection c2;
	private Connection c3;

	// An array to keep track of the number of accesses
	private int[] numeroAcessos;

	public Server() {
	    // Port number for the server to listen on
	    int portNumber = 2222;

	    // Create a server socket to listen for client connections
	    ServerSocket serverSocket = null;

	    try {
	        serverSocket = new ServerSocket(portNumber);
	        System.out.println("Server started on port " + portNumber);
	    } catch (IOException e) {
	        System.err.println("Error: " + e.getMessage());
	    }

	    // Initialize the number of accesses to zero
	    numeroAcessos = new int[1];
	    numeroAcessos[0] = 0;

	    // Create three connections to handle client requests - Maximum 3 connections simultaneously
	    c1 = new Connection(serverSocket, numeroAcessos, listaAlunos);
	    c2 = new Connection(serverSocket, numeroAcessos, listaAlunos);
	    c3 = new Connection(serverSocket, numeroAcessos, listaAlunos);
	}

	public static void main(String[] args) throws IOException, InterruptedException {
	    // Create an instance of the server
	    Server s = new Server();
	}

	// Read a list of students from a file
	private static ArrayList<Aluno> readAlunosFromFile() {
	    ArrayList<Aluno> alunos = new ArrayList<Aluno>();
	    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("C:\\\\\\\\Users\\\\\\\\gui\\\\\\\\Desktop\\\\\\\\Programação\\\\\\\\DistributedSystems-UBI\\\\\\\\PracticalClasses\\\\\\\\Aulas\\\\\\\\src\\\\\\\\MultiThreadAlunos\\\\\\\\alunos.ser"))) {
	        alunos = (ArrayList<Aluno>) ois.readObject();
	    } catch (IOException | ClassNotFoundException e) {
	        System.err.println("Error reading lines of text: " + e.getMessage());
	    }
	    return alunos;
	}
}