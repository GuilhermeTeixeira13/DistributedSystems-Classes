package Aula4;

import java.util.Random;

public class Teste3 {
	public static void main (String[] str){		
		int[] A = new int [100000000];
        int[] B = new int[100000000];
        int[] C = new int[100000000];
        
        Random random = new Random();
        for (int i = 0; i < 100000000; i++) {
            A[i] = random.nextInt();
            B[i] = random.nextInt();
        }
        
        long startTime = System.currentTimeMillis();
        
		ThreadSoma T1 = new ThreadSoma(A, B, C, 0, 50000000);
		ThreadSoma T2 = new ThreadSoma(A, B, C, 50000000, 100000000);
		
		try {
			T1.join();
			T2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	
		long endTime = System.currentTimeMillis();
		long executionTime = endTime - startTime;
        System.out.println("Execution time: " + executionTime + "ms");
	} 
}