package Aula8;

import java.rmi.RemoteException;

public interface Hello_S_I extends java.rmi.Remote {	
	public void printOnServer(String s) throws java.rmi.RemoteException;
	public void subscribe(String name, Hello_C_I c) throws RemoteException;
}
