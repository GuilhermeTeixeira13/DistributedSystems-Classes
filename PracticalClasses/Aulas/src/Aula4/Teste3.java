package Aula4;

public class Teste3 {
	public static void main (String[] str){	
		int dim = 1000000;
		int[] A = new int [dim];
        int[] B = new int[dim];
        int[] C = new int[dim];
        
        for (int i = 0; i < dim; i++) {
            A[i] = (int)(Math.random()*100);
            B[i] = (int)(Math.random()*100);
        }
        
        long startTime = System.currentTimeMillis();
        
		ThreadSoma T1 = new ThreadSoma(A, B, C, 0, dim/2);
		ThreadSoma T2 = new ThreadSoma(A, B, C, dim/2, dim);
		
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