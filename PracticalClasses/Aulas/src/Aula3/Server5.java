package Aula3;

import java.net.*;
import java.util.ArrayList;
import java.io.*;
import java.lang.Math;

public class Server5 {
	static ArrayList<Aluno> listaAlunos = readAlunosFromFile();
	static int numAcessos = 0;
	static String svHeader = "\n_______________________\n|        SERVER       |\n\n";
	static String svFooter = "|                     |\n_______________________\nEOF";
	
    public static void main(String[] args) throws IOException {
        int portNumber = 2222;

        try {
            ServerSocket serverSocket = new ServerSocket(portNumber);
            System.out.println("Server started on port " + portNumber);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                numAcessos++;
                System.out.println("Client " + clientSocket.getInetAddress().getHostAddress() + " connected");
                new ClientHandler(clientSocket).start();
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
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                
                String inputLine;
                
                while ((inputLine = in.readLine()) != null) {
                	System.out.println("Client: Option=" + inputLine);
                	if (inputLine.equalsIgnoreCase("5")) {
    			        break;
    			    }
                	String nome = "";
                
                	switch (inputLine) {
	    	            case "1":
	    	            	String numero = in.readLine();
	    	            	nome = in.readLine();
	    	            	String curso = in.readLine();
	    	            	String telemovel = in.readLine();
	    	            	String email = in.readLine();
	    	            	
	    	            	Boolean alunosRepetidos = verificaRepetidos(Integer.parseInt(numero), listaAlunos);
	    	            	
	    	            	if(alunosRepetidos) {
	    	            		out.println(svHeader + "Aluno repetido. Não foi registado novamente. Número de alunos registados: " + listaAlunos.size() + ".\n"+svFooter);
	    	            	} else {
	    	            		Aluno novo = new Aluno(Integer.parseInt(numero), nome, curso, Integer.parseInt(telemovel), email);
	    	            		listaAlunos.add(novo);
	    	            		writeAlunosToFile(listaAlunos);
	    	            		out.println(svHeader+"Registado com sucesso. Número de alunos registados: " + listaAlunos.size() + ".\n"+svFooter);
	    	            		out.flush();
	    	            	}
	    	                break;
	    	            case "2":
	    	            	out.println(outputListaDeAlunos(listaAlunos));
	    	            	out.flush();
	    	                break;
	    	            case "3":
	    	            	out.println(svHeader+"Número de acessos ao servidor até ao momento: " + numAcessos + "\n"+svFooter);
	    	            	out.flush();
	    	                break;
	    	            case "4":
	    	            	nome = in.readLine();
	    	            	out.println(devolveNumContacto(nome, listaAlunos));
	    	            	out.flush();
	    	                break;	    	    	             
                	}
                } 
                
                System.out.println("Client " + clientSocket.getInetAddress().getHostAddress() + " disconnected");
                clientSocket.close();
            }   
            catch (IOException e) {
                System.err.println("Error handling client: " + e.getMessage());
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