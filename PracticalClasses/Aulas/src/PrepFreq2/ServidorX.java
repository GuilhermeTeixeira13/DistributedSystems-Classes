package PrepFreq2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorX {
    private static final int SERVER_PORT = 8888; 
    private static final int NUM_SERVIDORES_AUXILIARES = 10; 
    private static int servidorAtual = -1;
    private static String[] ips = {"sv1", "sv2", "sv3", "sv4", "sv5", "sv6", "sv7", "sv8", "sv9", "sv10"};

    
    public ServidorX () {
    	try {
            ServerSocket serverSocket = new ServerSocket(SERVER_PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                Connection c = new Connection(clientSocket, this);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ServidorX sx = new ServidorX();
    }
    
    public synchronized String obterServidorAuxiliar() {
        servidorAtual = (servidorAtual + 1) % NUM_SERVIDORES_AUXILIARES;
        return ips[servidorAtual];
    }
}

