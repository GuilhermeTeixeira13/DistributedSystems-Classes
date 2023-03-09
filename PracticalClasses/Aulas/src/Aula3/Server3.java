package Aula3;

import java.net.*;
import java.io.*;
import java.lang.Math;

public class Server3 {
    public static void main(String[] args) throws IOException {
        int portNumber = 2222;

        try {
            ServerSocket serverSocket = new ServerSocket(portNumber);
            System.out.println("Server started on port " + portNumber);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client " + clientSocket.getInetAddress().getHostAddress() + " connected");
                new ClientHandler(clientSocket).start();
            }
            
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static class ClientHandler extends Thread {
        private Socket clientSocket;

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        public void run() {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                
                String inputLine;
                char clientType = ' ';
                int messageCounter = 0;
                
                while ((inputLine = in.readLine()) != null) {
                	System.out.println("Client: " + inputLine);
                	if (inputLine.equalsIgnoreCase("disconnect")) {
    			        break;
    			    }
    				
                	
                	if (messageCounter == 0) {
                		clientType = inputLine.charAt(0);
            			out.println("Client type has been set to " + clientType + ".");
                	}
                	else {
                		if (clientType == 'A') {
                			out.println(inputLine + "**2 = " + Math.pow(Integer.parseInt(inputLine), 2));
                		}
                		else if (clientType == 'B') {
                			out.println("sqrt(" + inputLine + ") = " + Math.sqrt(Double.parseDouble(inputLine)));
                		}
                	}
                	messageCounter++;
                }

                System.out.println("Client " + clientSocket.getInetAddress().getHostAddress() + " disconnected");
                clientSocket.close();
            } catch (IOException e) {
                System.err.println("Error handling client: " + e.getMessage());
            }
        }
    }
}

