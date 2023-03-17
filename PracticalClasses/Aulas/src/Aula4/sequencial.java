package Aula4;

import java.util.Random;

public class sequencial {
	public static void main(String[] args) {
		int[] A = new int [100000000];
        int[] B = new int[100000000];
        int[] C = new int[100000000];
        
        Random random = new Random();
        for (int i = 0; i < 100000000; i++) {
            A[i] = random.nextInt();
            B[i] = random.nextInt();
        }
        
        long startTime = System.currentTimeMillis();
        
        for (int i = 0; i < A.length; i++) {
            C[i] = A[i] + B[i];
        }
        
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        System.out.println("Execution time: " + executionTime + "ms");

        // #3.c -> Para arrays grandes, o sequencial é mais lento. S:ms vs T:ms
	}
}
