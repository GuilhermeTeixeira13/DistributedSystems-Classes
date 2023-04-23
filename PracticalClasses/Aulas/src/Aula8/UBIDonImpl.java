package Aula8;

import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collections;

public class UBIDonImpl extends UnicastRemoteObject implements UBIDonInterfaceRemote {
	ArrayList<String> doadores;
	Float totalDoado;
	
	public UBIDonImpl() throws java.rmi.RemoteException {
		super();
		doadores = new ArrayList<>();
		totalDoado = 0.0f;
	}
	
	public String doar(String nome, Float valor) {
		String feedback = "Doação feita com sucesso!";
		
		synchronized(totalDoado) {
			totalDoado += valor;
		}
		synchronized(doadores) {
			if (Collections.frequency(doadores, nome) == 0) {
				doadores.add(nome);
            }
		}
		
		if (doadores.size() == 3)
			feedback += " Você foi o 3º doador!";
		
		return feedback;
	}
	
	public String consultarTotal() {
		return String.valueOf(totalDoado);
	}
	
	public String consultarDoadores() {
		return doadores.toString();
	}
}
