package Aula5;

public class P1 extends Thread {
    private int[] sharedVariable;
    private int M;
    private Semaphore semaphore;

    public P1(int[] sharedVariable, int M, Semaphore semaphore) {
        this.sharedVariable = sharedVariable;
        this.M = M;
        this.semaphore = semaphore;
    }

    public void run() {
        int x = M;
        int y = -M;

        while (true) {
        	semaphore.semWait();
            x = x - sharedVariable[0];
            y = y + sharedVariable[0];
            semaphore.semSignal();
            
            System.out.println("P1 x = " + x);
            System.out.println("P1 y = " + y);

            if (x + y != 0) {
                System.out.println("Secção crítica violada");
                break;
            }
            sharedVariable[0]++;
        }
    }
}