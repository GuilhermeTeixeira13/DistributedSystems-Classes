package Aula7;

public interface AlunosRMIInterface extends java.rmi.Remote {
	public String registaAluno(String nome, String numero, String curso, String telemovel, String email) throws java.rmi.RemoteException;
	public String outputListaDeAlunos() throws java.rmi.RemoteException;
}
