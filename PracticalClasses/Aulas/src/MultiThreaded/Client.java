package MultiThreaded;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import myinputs.Ler;

public class Client {
	private Socket s;

	public Client(){
		int aposta = 0;
		try {
			s = new Socket ("127.0.0.1", 5432);
			ObjectOutputStream os = new ObjectOutputStream(s.getOutputStream());
			ObjectInputStream is = new ObjectInputStream( s.getInputStream() );
			System.out.println("Qual é a sua aposta?");
			aposta = Ler.umInt();
			os.writeObject(aposta);
			os.flush();
			System.out.println(is.readObject());
			s.close();}
		catch ( IOException e)
		{ System.out.println(e.getMessage());}
		catch (ClassNotFoundException ex)
		{ System.out.println(ex.getMessage()); }
	}

	public static void main (String args []){
		Client c = new Client();
	}
} 