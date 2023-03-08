package Aula3;

import java.io.*;
import java.net.*;

public class Server4 {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(1234);
            System.out.println("Servidor pronto para receber conexões.");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Conexão estabelecida com " + clientSocket.getInetAddress());

                ObjectOutputStream output = new ObjectOutputStream(clientSocket.getOutputStream());
                output.flush();
                ObjectInputStream input = new ObjectInputStream(clientSocket.getInputStream());

                String mensagem;
                do {
                    mensagem = (String) input.readObject();
                    System.out.println("Cliente diz: " + mensagem);

                    System.out.print("Servidor diz: ");
                    BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
                    mensagem = teclado.readLine();
                    output.writeObject(mensagem);
                    output.flush();
                } while (!mensagem.equals("fim"));

                input.close();
                output.close();
                clientSocket.close();
                System.out.println("Conexão encerrada com " + clientSocket.getInetAddress());
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

