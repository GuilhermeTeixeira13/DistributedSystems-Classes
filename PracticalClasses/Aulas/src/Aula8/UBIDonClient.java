package Aula8;

import java.rmi.Naming;

import myinputs.Ler;

public class UBIDonClient {
	public static void main(String[] argv) {
		try {
			UBIDonInterfaceRemote ubiRemoteObject = (UBIDonInterfaceRemote) Naming.lookup("RMIImpl");
			
			while (true) {
				System.out.println();
	            System.out.println("1. Donativo");
	            System.out.println("2. Consultar total");
	            System.out.println("3. Consultar doadores");
	            System.out.println("4. Quit");
	            System.out.print("Enter your choice (1-4): ");
	            
	            int choice = Ler.umInt();	
	            
	            if (choice == 4) break;
		
				switch (choice) {
		            case 1: 
		            	System.out.print("\nNome: ");
		            	String nome = Ler.umaString();
		            	
		            	System.out.print("Valor doacao: ");
		            	Float valor = Ler.umFloat();
		            	
		            	System.out.println(ubiRemoteObject.doar(nome, valor));
		                break;
		            case 2:
		            	System.out.println("\nTotal doado: " + ubiRemoteObject.consultarTotal() + "$");
		            	break;
		            case 3:
		            	System.out.println("\n" + ubiRemoteObject.consultarDoadores());
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

