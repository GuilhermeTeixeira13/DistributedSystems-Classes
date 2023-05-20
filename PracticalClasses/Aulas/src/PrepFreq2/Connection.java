package PrepFreq2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Connection extends Thread {
	private Socket s;
    private ServidorX servidorX;

    public Connection(Socket s, ServidorX servidorX) {
        super();
        this.s = s;
        this.servidorX = servidorX;
        start();
    }
	
	public void run() {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
	        PrintWriter out = new PrintWriter(s.getOutputStream(), true);

	        String pedido = in.readLine();
	        System.out.println("Pedido recebido do cliente: " + pedido);

	        String servidorAuxiliar = servidorX.obterServidorAuxiliar();
	        String respostaServidorAuxiliar = obterRespostaDoServidorAuxiliar(servidorAuxiliar);

	        out.println(respostaServidorAuxiliar);

	        s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	
	private String obterRespostaDoServidorAuxiliar(String ipServidorAuxiliar) {
		try {
	        Registry registry = LocateRegistry.getRegistry(ipServidorAuxiliar, 1099);
	        ServidorXi servidorXi = (ServidorXi) registry.lookup("ServidorXi");

	        String resposta = servidorXi.lerConteudoArquivo();

	        return "Resposta do servidor auxiliar " + ipServidorAuxiliar + ": " + resposta;
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return null;
    }
}