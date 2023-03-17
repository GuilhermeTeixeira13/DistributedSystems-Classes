package Aula4;

public class Teste5 {
	public static void main (String[] arg){
		MyThread5 Ta, Tb, Tc;
		
		ThreadGroup this_group1 = new ThreadGroup("Grupo 1");;
		ThreadGroup this_group2 = new ThreadGroup("Grupo 2");;
		
		System.out.println("O nome do grupo é: " + this_group1.getName());
		System.out.println("O nº de Threads activas no grupo é " + this_group1.activeCount());

		System.out.println("O nome do grupo é: " + this_group2.getName());
		System.out.println("O nº de Threads activas no grupo é " + this_group2.activeCount());
		
		Ta= new MyThread5 (this_group1, "Thread A");
		Tb= new MyThread5 (this_group2, "Thread B");
		Tc= new MyThread5 (this_group2, "Thread C");
		
		// inicie a execução das Threads
		
		Ta.start();
		Tb.start();
		Tc.start();

		// obtenha o nome do grupo e o número de threads activas nesse grupo
		System.out.println("O nome do grupo é: " + this_group1.getName());
		System.out.println("O nº de Threads activas no grupo é " + this_group1.activeCount());
		
		// obtenha o nome do grupo e o número de threads activas nesse grupo
		System.out.println("O nome do grupo é: " + this_group2.getName());
		System.out.println("O nº de Threads activas no grupo é " + this_group2.activeCount());
		
		try
		{
			Thread.sleep (100);
		}
		catch (InterruptedException e)
		{
			
		}
		
		// Pode invocar um método em todas as Threads do grupo:
		this_group1.interrupt();
		this_group2.interrupt();
	}
}
