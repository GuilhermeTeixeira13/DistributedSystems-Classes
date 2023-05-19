package PrepFreq;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
	private Socket s;
	
	public Client() {
		String serverAddress = "127.0.0.1";
		int portNumber = 2222;
		
		try {
			s = new Socket (serverAddress, portNumber);
			ObjectOutputStream os = new ObjectOutputStream(s.getOutputStream());
			ObjectInputStream is = new ObjectInputStream(s.getInputStream());
			
			int option = 0;
			while (option != 3) {
				System.out.println("1 - OPÇÃO 1");
				System.out.println("2 - OPÇÃO 2");
				System.out.println("3 - QUIT");
				System.out.print("Your choice: ");
				option = Ler.umInt();
				os.writeObject(option);
				os.flush();
				System.out.println(is.readObject());
			}
			s.close();
		} catch ( IOException e){ 
			System.out.println(e.getMessage());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}	
	}
	public static void main (String args []){
		Client c = new Client();
	}
}
