package Aula5;

public class P1 extends Thread {
    private int[] sharedVariable;
    private int M;

    public P1(int[] sharedVariable, int M) {
        this.sharedVariable = sharedVariable;
        this.M = M;
    }

    public void run() {
        int x = M;
        int y = -M;

        while (true) {
        	synchronized (sharedVariable) {
                x = x - sharedVariable[0];
                y = y + sharedVariable[0];
                
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
}