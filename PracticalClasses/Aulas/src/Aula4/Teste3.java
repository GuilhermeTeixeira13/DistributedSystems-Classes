package Aula4;

import java.util.Arrays;

public class Teste3 {
	public static void main (String[] str){
        int[] A = {1, 2, 3, 4, 5};
        int[] B = {6, 7, 8, 9, 10};
        int[] C = new int[A.length];
		
		ThreadSoma T1 = new ThreadSoma(A, B, C, 0, 3);
		ThreadSoma T2 = new ThreadSoma(A, B, C, 3, 5);
		Thread Ta, Tb;
		
		Ta = new Thread( T1 );
		Tb = new Thread( T2 );
		
		Ta.start();
		Tb.start();
		
		try {
			Ta.join();
			Tb.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		System.out.println(Arrays.toString(C));
	} 
}