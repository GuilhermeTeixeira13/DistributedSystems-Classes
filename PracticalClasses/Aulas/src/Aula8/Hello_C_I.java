package Aula8;

public interface Hello_C_I extends java.rmi.Remote {
	 public void printOnClient (String s) throws java.rmi.RemoteException;
	 public void displayWinningNumber(String s) throws java.rmi.RemoteException;
}
