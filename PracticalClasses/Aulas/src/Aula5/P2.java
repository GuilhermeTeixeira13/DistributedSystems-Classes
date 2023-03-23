package Aula5;

public class P2 extends Thread {
    private int[] sharedVariable;
    private Semaphore semaphore;

    public P2(int[] sharedVariable, Semaphore semaphore) {
        this.sharedVariable = sharedVariable;
        this.semaphore = semaphore;
    }

    public void run() {
    	while (true) {
    		semaphore.semWait();
    		sharedVariable[0]++;
    		semaphore.semSignal();
    	}
    }
}