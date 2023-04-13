package Aula7;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class AlunosRMIImpl extends UnicastRemoteObject implements AlunosRMIInterface {
	ArrayList<Aluno> listaAlunos;
	String svHeader = "\n_______________________\n|        SERVER       |\n\n";
	String svFooter = "|                     |\n_______________________\n";
	
	public AlunosRMIImpl() throws java.rmi.RemoteException {
		super();
		listaAlunos = readAlunosFromFile();
	}
	
	public String registaAluno(String nome, String numero, String curso, String telemovel, String email) {
		synchronized(listaAlunos) {	
    		Boolean alunosRepetidos = verificaRepetidos(Integer.parseInt(numero), listaAlunos);

    		if(alunosRepetidos) {
    			return (svHeader + "Aluno repetido. Não foi registado novamente. Número de alunos registados: " + listaAlunos.size() + ".\n\n"+svFooter);
    		}
    		else {
    			Aluno novo = new Aluno(Integer.parseInt(numero), nome, curso, Integer.parseInt(telemovel), email);
    			listaAlunos.add(novo);
    			writeAlunosToFile(listaAlunos);
    			return (svHeader+"Registado com sucesso. Número de alunos registados: " + listaAlunos.size() + ".\n\n"+svFooter);
    		}
    	}
	}
	
	public ArrayList<Aluno> readAlunosFromFile() {	
		ArrayList<Aluno> alunos = new ArrayList<Aluno>();
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("C:\\Users\\guite\\OneDrive\\Área de Trabalho\\DistributedSystems-UBI\\PracticalClasses\\Aulas\\src\\Aula7\\alunos.ser"))) {
			alunos = (ArrayList<Aluno>) ois.readObject();
        } catch (IOException e) {
            System.err.println("Error reading lines of text: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("Error reading lines of text: " + e.getMessage());
        }
        return alunos;
	}
	
	public Boolean verificaRepetidos(int numAluno, ArrayList<Aluno> alunos) {	
    	for (Aluno aluno : alunos) {
    	    if (aluno.getNumero() == numAluno)
    	    	return true;
    	}
    	return false;
	}
    
    public String outputListaDeAlunos() {
    	String lista = svHeader + "-----------------------\n";
    	lista += "         ALUNOS        \n";
    	lista += "-----------------------\n";
    	for (Aluno aluno : listaAlunos) {
    		lista += "Número: " + aluno.getNumero() + "\n";
    		lista += "Nome: " + aluno.getNome() + "\n";
    		lista += "Curso: " + aluno.getCurso() + "\n";
    		lista += "Telemóvel: " + aluno.getTelemovel() + "\n";
    		lista += "Email: " + aluno.getEmail() + "\n";
    	    lista += "-----------------------\n";
    	}
    	lista += "\n" + svFooter;
    	return lista;
	}
    
    public String devolveNumContacto(String nome, ArrayList<Aluno> alunos) {
    	String lista = svHeader;
    	lista += "-----------------------\n";
    	for (Aluno aluno : alunos) {
    		if(aluno.getNome().equals(nome)) {
    			lista += "Número: " + aluno.getNumero() + "\n";
    			lista += "Telemóvel: " + aluno.getTelemovel() + "\n";
    			lista += "-----------------------\n\n";
    		}
    	}
    	lista += svFooter;
    	return lista;
	}
    
    public void writeAlunosToFile(ArrayList<Aluno> alunos) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("C:\\\\Users\\\\guite\\\\OneDrive\\\\Área de Trabalho\\\\DistributedSystems-UBI\\\\PracticalClasses\\\\Aulas\\\\src\\\\Aula7\\\\alunos.ser"))) {
            oos.writeObject(alunos);
        } catch (IOException e) {
            System.err.println("Error writing list of students: " + e.getMessage());
        }
    }
}
