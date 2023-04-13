package Aula7;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;

public class CalcRMIServer {
	public static void main(String[] argv) {
		try { 
			java.rmi.registry.LocateRegistry.createRegistry(1099);
			System.out.println("RMI registry ready.");
		} catch (Exception e) {
			System.out.println("Exception starting RMI registry:");
		}try {
			CalcRMIInterface objRemoto = new CalcRMIImpl();
			Naming.rebind("RMIImpl", objRemoto);
			System.out.println("Remote object ready");
		} catch (MalformedURLException | RemoteException e) {
			System.out.println(e.getMessage());
		}
	}
}
