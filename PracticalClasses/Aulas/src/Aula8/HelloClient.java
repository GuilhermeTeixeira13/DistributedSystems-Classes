package Aula8;

import java.rmi.*;
public class HelloClient extends java.rmi.server.UnicastRemoteObject implements Hello_C_I {
	static int clientNumber = 0;
	
	public HelloClient() throws RemoteException {
		super();
		clientNumber++;
	}
	
	//Método remoto
	public void printOnClient (String s) throws java.rmi.RemoteException {
		System.out.println ("Message from server: " + s);
	}
	
	public static int getCount() throws java.rmi.RemoteException {
		return clientNumber;
	}
	
	public static void main (String [] args){
		try {
			Hello_S_I h= (Hello_S_I)Naming.lookup ("Hello");
			HelloClient c = new HelloClient();
			h.subscribe( "Nome da máquina cliente ...", (Hello_C_I)c);
		}
		catch (Exception r){
			System.out.println ( "Exception in client" +r.getMessage());
		}
	}
}
