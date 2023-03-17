package Aula4;

public class Teste4 {

	public static void main(String[] args) {
		Normal normalThread = new Normal();
		Daemon daemonThread = new Daemon();
		
		Thread Ta;
		Ta = new Thread( normalThread );
		
		daemonThread.start();
		Ta.start();
	}
}
