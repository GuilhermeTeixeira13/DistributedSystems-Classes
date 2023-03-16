package Aula4;

public class MyThread2 implements Runnable{
	public MyThread2(){ 
		
	}
	
	public void run(){
		System.out.println("Hi there, from " + Thread.currentThread().getName() );
	} 
}
