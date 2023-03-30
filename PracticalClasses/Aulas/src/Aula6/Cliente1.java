package Aula6;

import java.net.*;
import java.io.*;

public class Cliente1{
	private Socket s;
	public Cliente1() {
	    try {
	        s = new Socket("127.0.0.1", 5432);
	        ObjectInputStream is = new ObjectInputStream(s.getInputStream());
	        for (int i = 0; i < 5; i++) {
	            System.out.println(is.readObject());
	        }
	        s.close();
	    } catch (IOException | ClassNotFoundException e) {
	        System.out.println(e.getMessage());
	    }
	}

	public static void main (String args []){
		Cliente1 c = new Cliente1();
	}
}

