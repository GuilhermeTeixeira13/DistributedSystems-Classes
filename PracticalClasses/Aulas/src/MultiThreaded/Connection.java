package MultiThreaded;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Connection extends Thread {
	private Socket S;
	private Premio P;
	public Connection ( Socket s, Premio p) {
		super();
		S = s;
		P=p;
		start();
	}

	public void run () {
		try {
			ObjectOutputStream os = new ObjectOutputStream (S.getOutputStream());
			ObjectInputStream is = new ObjectInputStream( S.getInputStream() );
			
			int v = (int)is.readObject(); //recebe a aposta
			P.incValor(); // incrementa o valor do prémio
			int guess =(int) (Math.random()*100); //gera o valor aleatório
			double ganhou = P.getValor()/2.0;
			
			if (v == guess){
				P.setValor(0);
				os.writeObject ("Parabéns Ganhou " + ganhou+ "€" );
			}else
				os.writeObject ("Continue a tentar, o prémio já é de " + (2*ganhou)+ "€");
			os.flush();
		}
		catch ( IOException e) {
			System.out.println(e.getMessage());
		}
		catch (ClassNotFoundException ee){
			System.out.println(ee.getMessage());
		}
	}
}
