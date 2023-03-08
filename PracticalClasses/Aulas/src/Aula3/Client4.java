package Aula3;

import java.io.*;
import java.net.*;

public class Client4 {
    public static void main(String[] args) {
        try {
            Socket serverSocket = new Socket("localhost", 1234);

            ObjectOutputStream output = new ObjectOutputStream(serverSocket.getOutputStream());
            output.flush();
            ObjectInputStream input = new ObjectInputStream(serverSocket.getInputStream());

            String mensagem;
            do {
                System.out.print("Cliente diz: ");
                BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
                mensagem = teclado.readLine();
                output.writeObject(mensagem);
                output.flush();

                mensagem = (String) input.readObject();
                System.out.println("Servidor diz: " + mensagem);
            } while (!mensagem.equals("fim"));

            input.close();
            output.close();
            serverSocket.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
