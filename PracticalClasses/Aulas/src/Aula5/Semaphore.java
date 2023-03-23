package Aula5;

public class Semaphore {
	private int s;
	public Semaphore (int i){
		s = i;
	}
	public synchronized void semWait (){
		while (s <= 0 ) {
			try { wait(); }
			catch (InterruptedException e) {}
		}
		s = s –1;
	}
	public synchronized void semSignal (){
		s = s + 1 ;
		notify(); 
	}
}
