package Aula7;

import java.rmi.Naming;
import java.util.Scanner;

public class RMIClient {
	public static void main(String[] argv) {
		//Se está a trabalhar com uma versão do java inferior a 17,
		//não comente a instrução abaixo:
		//System.setSecurityManager(new SecurityManager());

		try {
			//bind server object to object in client
			RMIInterface myServerObject = (RMIInterface) Naming.lookup("RMIImpl");
			
			while (true) {
				System.out.println();
	            System.out.println("1. Adicionar a lista");
	            System.out.println("2. Consultar lista");
	            System.out.println("3. Numero de vezes que a lista foi consultada");
	            System.out.println("4. Quit");
	            System.out.print("Enter your choice (1-4): ");
	            
	            Scanner scannerChoice = new Scanner(System.in);
	            int choice = scannerChoice.nextInt();	
	            
	            if (choice == 4) break;
		
				switch (choice) {
		            case 1:
		            	Scanner scannerLista = new Scanner(System.in);     
		            	System.out.print("\nNome para adicionar a lista: ");
		            	String name = scannerLista.nextLine();
		            	myServerObject.adiciona(name);
		                break;
		            case 2:
		            	System.out.println("\nLista: " + myServerObject.consulta());
		            	break;
		            case 3:
		            	System.out.println( "\nO metodo consulta() foi invocado " + myServerObject.getCount() + " vezes.");
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
