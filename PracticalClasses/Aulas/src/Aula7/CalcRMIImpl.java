package Aula7;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class CalcRMIImpl  extends UnicastRemoteObject implements CalcRMIInterface {
	public CalcRMIImpl() throws java.rmi.RemoteException {
		super();
	}
	
	public int soma (int n1, int n2) throws java.rmi.RemoteException {
		return n1 + n2;
	}
	
	public int sub (int n1, int n2) throws java.rmi.RemoteException {
		return n1 - n2;
	}

	public int div (int n1, int n2) throws java.rmi.RemoteException {
		return n1 / n2;
	}
	
	public int mult (int n1, int n2) throws java.rmi.RemoteException {
		return n1 * n2;
	}
}
