package Aula8;

import java.io.*;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HelloServer extends java.rmi.server.UnicastRemoteObject implements Hello_S_I {
	private static Hello_C_I client;
	private int clientCount = 0;
	private List<Integer> last10Numbers = new ArrayList<>();
	private List<Hello_C_I> clients = new ArrayList<>();

	
	public HelloServer() throws java.rmi.RemoteException{
		super();
	}
	
	public void printOnServer(String s) throws java.rmi.RemoteException{
		System.out.println( " SERVER : " +s );
	}
	
	public void subscribe(String name, Hello_C_I c) throws java.rmi.RemoteException {
	    System.out.println("Subscribing " + name);
	    synchronized (this) {
	    	client = c;
	    	clients.add(c);
	        clientCount++;
	        last10Numbers.add(clientCount);
	        if (last10Numbers.size() == 3) {
	            int winningNumber = last10Numbers.get(new Random().nextInt(3));
	            System.out.println("Winning number is " + winningNumber);          
	            for (Hello_C_I client : clients.subList(clients.size() - 3, clients.size())) {
	            	client.printOnClient("Winning number is " + winningNumber);
	            }
	            last10Numbers.clear();
	        }
	    }
	}
	
	//Método local
	public static String lerString (){
		String s = "";
		try{
			BufferedReader in = new BufferedReader ( new InputStreamReader (System.in), 1);
			s= in.readLine();
		}
		catch (IOException e){
			System.out.println( e.getMessage());
		}
		return s;
	}
	
	public static void main (String [] args){
		String s;
		try {
			java.rmi.registry.LocateRegistry.createRegistry(1099);
			
			HelloServer h = new HelloServer();
			Naming.rebind ("Hello", h);
			while (true){
				System.out.println("Mensagem para o cliente:");
				s= lerString();
				client.printOnClient(s);
			}
		}
		catch (RemoteException r){
			System.out.println("Exception in server"+r.getMessage());
		}
		catch (java.net.MalformedURLException u){
			System.out.println("Exception in server - URL" );
		}
	}
}