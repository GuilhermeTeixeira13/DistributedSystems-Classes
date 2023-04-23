package Aula8;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;

public class UBIDonServer {
	public static void main(String[] argv) {
		try { 
			java.rmi.registry.LocateRegistry.createRegistry(1099);
			System.out.println("RMI registry ready.");
		} catch (Exception e) {
			System.out.println("Exception starting RMI registry:");
		}try {
			UBIDonInterfaceRemote objRemoto = new UBIDonImpl();
			Naming.rebind("RMIImpl", objRemoto);
			System.out.println("Remote object ready");
		} catch (MalformedURLException | RemoteException e) {
			System.out.println(e.getMessage());
		}
	}
}
