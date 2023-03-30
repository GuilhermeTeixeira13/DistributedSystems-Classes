package Aula6;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Connection1 extends Thread {
	private Socket S;
	public Connection1 ( Socket s) {
		super();
		S = s;
		start();
	}
	public void run () {
	    try {
	        ObjectOutputStream os = new ObjectOutputStream(S.getOutputStream());
	        for(int i=0; i<5; i++) {
	            os.writeObject("A data e hora do sistema: " + new java.util.Date());
	            os.flush();
	            try {
	                sleep(4000);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
	        os.close();
	    }
	    catch (IOException e) {}
	}

}