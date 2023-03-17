package Aula4;

public class ThreadSoma extends Thread {
	private int[] A;
    private int[] B;
    private int[] C;
    private int p;
    private int u;
	
	public ThreadSoma(int [] A, int [] B, int [] C, int p, int u){
		super();
		this.A = A;
        this.B = B;
        this.C = C;
        this.p = p;
        this.u = u;
        start();
	}
	
	public void run() {
        for (int i = p; i < u; i++) {
            C[i] = A[i] + B[i];
        }
    }
}
