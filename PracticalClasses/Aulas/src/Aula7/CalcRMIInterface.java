package Aula7;

public interface CalcRMIInterface extends java.rmi.Remote {
	public int soma (int n1, int n2) throws java.rmi.RemoteException;
	public int sub (int n1, int n2) throws java.rmi.RemoteException;
	public int mult (int n1, int n2) throws java.rmi.RemoteException;
	public int div (int n1, int n2) throws java.rmi.RemoteException;
}
