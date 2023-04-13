package Aula7;

import java.rmi.Naming;
import java.util.Scanner;
import myinputs.Ler;

public class CalcRMIClient {
	public static void main(String[] argv) {
		try {
			CalcRMIInterface myServerObject = (CalcRMIInterface) Naming.lookup("RMIImpl");
			
			while (true) {
				System.out.println();
	            System.out.println("1. Somar");
	            System.out.println("2. Subtrair");
	            System.out.println("3. Dividir");
	            System.out.println("4. Multiplicar");
	            System.out.println("5. Quit");
	            System.out.print("Enter your choice (1-5): ");
	            
	            int choice = Ler.umInt();	
	            
	            if (choice == 5) break;
	            
	            System.out.print("\n1º número: ");
            	int n1 = Ler.umInt();
            	
            	System.out.print("\n2º número: ");
            	int n2 = Ler.umInt();
		
				switch (choice) {
		            case 1:  
		            	System.out.println("O resultado é -> " + myServerObject.soma(n1, n2));
		                break;
		            case 2:
		            	System.out.println("O resultado é -> " + myServerObject.sub(n1, n2));
		            	break;
		            case 3:
		            	System.out.println("O resultado é -> " + myServerObject.div(n1, n2));
		            	break;
		            case 4:
		            	System.out.println("O resultado é -> " + myServerObject.mult(n1, n2));
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
