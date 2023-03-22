package Aula5;

public class P2 extends Thread {
    private int[] sharedVariable;

    public P2(int[] sharedVariable) {
        this.sharedVariable = sharedVariable;
    }

    public void run() {
        while (true) {
            synchronized (sharedVariable) {
            	sharedVariable[0]++;
            }
        }
    }
}