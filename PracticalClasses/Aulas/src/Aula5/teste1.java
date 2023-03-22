package Aula5;

public class teste1 {

	public static void main(String[] args) {
		int[] sharedVariable = new int[1];
        sharedVariable[0] = 0;

        P1 p1 = new P1(sharedVariable, 10);
        P2 p2 = new P2(sharedVariable);
        
        // Para o programa terminar assim que o P1 termina
        p2.setDaemon(true);

        p1.start();
        p2.start();

        try {
            Thread.sleep(1000); // Espera um segundo
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Simula a violação da secção crítica
        synchronized (sharedVariable) {
            sharedVariable[0]++;
        }

        try {
            p1.join(); // Aguarda o término do processo p1
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
	}
}
