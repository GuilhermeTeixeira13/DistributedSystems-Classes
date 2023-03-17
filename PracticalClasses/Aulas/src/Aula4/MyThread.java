package Aula4;

public class MyThread extends Thread {
	public MyThread(String name){
		super(name);
		start();
	}
	
	public void run() {
		System.out.println("Hello there, from " + getName() );
	}
}
