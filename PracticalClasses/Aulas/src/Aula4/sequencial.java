package Aula4;

public class sequencial {
	public static void main(String[] args) {
		int dim = 1000000;
		int[] A = new int [dim];
        int[] B = new int[dim];
        int[] C = new int[dim];
        
        for (int i = 0; i < dim; i++) {
            A[i] = (int)(Math.random()*100);
            B[i] = (int)(Math.random()*100);
        }
        
        long startTime = System.currentTimeMillis();
        
        for (int i = 0; i < dim; i++) {
            C[i] = A[i] + B[i];
        }
        
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        System.out.println("Execution time: " + executionTime + "ms");

        // #3.c -> Para arrays grandes, o sequencial é mais lento. S:ms vs T:ms
	}
}
