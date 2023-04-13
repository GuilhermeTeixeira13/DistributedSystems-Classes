package Aula7;

import java.rmi.Naming;

import myinputs.Ler;

public class AlunosRMIClient {
	public static void main(String[] argv) {
		try {
			AlunosRMIInterface alunosRemoteObject = (AlunosRMIInterface) Naming.lookup("RMIImpl");
			
			while (true) {
				System.out.println();
	            System.out.println("1. Registar aluno");
	            System.out.println("2. Mostrar alunos registados");
	            System.out.println("3. Quit");
	            System.out.print("Enter your choice (1-3): ");
	            
	            int choice = Ler.umInt();	
	            
	            if (choice == 3) break;
		
				switch (choice) {
		            case 1: 
		            	System.out.print("\nNome: ");
		            	String nome = Ler.umaString();
		            	
		            	System.out.print("Número: ");
		            	String numero = Ler.umaString();
		            	
		            	System.out.print("Curso: ");
		            	String curso = Ler.umaString();
		            	
		            	System.out.print("Telemóvel: ");
		            	String telemovel = Ler.umaString();
		            	
		            	System.out.print("Email: ");
		            	String email = Ler.umaString();
		            	
		            	System.out.println(alunosRemoteObject.registaAluno(nome, numero, curso, telemovel, email));
		                break;
		            case 2:
		            	System.out.println(alunosRemoteObject.outputListaDeAlunos());
		            	break;
				}
			}
		}
		catch(Exception e) {
			System.out.println("Exception occured: " + e);
			System.exit(0);
		}
		System.out.println("RMI connection successful");
	}
}
