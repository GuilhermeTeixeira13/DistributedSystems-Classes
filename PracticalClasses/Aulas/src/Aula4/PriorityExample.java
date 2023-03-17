package Aula4;

public class PriorityExample {
    public static void main(String[] args) {
    	MyThread Ta, Tb, Tc, Td;
		Ta = new MyThread("A");
		Tb = new MyThread("B");
		Tc = new MyThread("C");
		Td = new MyThread("D");

        Ta.setPriority(Thread.MAX_PRIORITY); // 10
        Tb.setPriority(Thread.NORM_PRIORITY); // 5
        Tc.setPriority(Thread.MIN_PRIORITY); // 1
        
        System.out.println("A prioridade default é: " + Td.getPriority());
    }
}