package PrepFreq2;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;

public class ServidorXi {
    public static void main(String[] argv) {
		try { 
			java.rmi.registry.LocateRegistry.createRegistry(1099);
		} catch (Exception e) {
		}try {
			ServidorXiInterface objRemoto = new ServidorXiImpl();
			Naming.rebind("ServidorXi", objRemoto);
		} catch (MalformedURLException | RemoteException e) {
			System.out.println(e.getMessage());
		}
	}
}