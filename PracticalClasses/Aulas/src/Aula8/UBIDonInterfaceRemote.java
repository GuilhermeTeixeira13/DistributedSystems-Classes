package Aula8;

public interface UBIDonInterfaceRemote extends java.rmi.Remote {
	public String doar(String nome, Float valor) throws java.rmi.RemoteException;
	public String consultarTotal() throws java.rmi.RemoteException;
	public String consultarDoadores() throws java.rmi.RemoteException;
}
