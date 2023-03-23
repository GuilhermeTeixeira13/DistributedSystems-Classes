package Aula5;

public class teste2 {
	public static void main(String[] args) {
		SalaCinema SC = new SalaCinema(50, "Harry Potter");

        PostoVenda p1 = new PostoVenda("Posto 1", SC);
        PostoVenda p2 = new PostoVenda("Posto 2", SC);
        PostoVenda p3 = new PostoVenda("Posto 3", SC);

        try {
            Thread.sleep(1000); // Espera um segundo
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
	}
}
