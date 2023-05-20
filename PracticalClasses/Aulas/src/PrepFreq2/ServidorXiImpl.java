package PrepFreq2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServidorXiImpl  extends UnicastRemoteObject implements ServidorXiInterface {
	public ServidorXiImpl() throws java.rmi.RemoteException {
		super();
	}
	
	public String lerConteudoArquivo() throws RemoteException {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("DADOS.dat"));
            StringBuilder conteudo = new StringBuilder();
            String linha;
            while ((linha = reader.readLine()) != null) {
                conteudo.append(linha);
            }
            reader.close();

            return conteudo.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
