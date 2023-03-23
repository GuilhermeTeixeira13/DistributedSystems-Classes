package Aula5;

public class PostoVenda extends Thread {
	SalaCinema SC;
	String posto;
	
	public PostoVenda(String posto, SalaCinema SC){
		super();
		this.SC = SC;
		this.posto = posto;
        start();
	}
	
	public void run() {
		int pausa;
		while (true){
			try {
				pausa = 2;
				sleep(pausa);			
				
				if (SC.getBilhetesDisponiveis() == 0) {
					System.out.println(posto + " fim");
					break;
				}else {
					System.out.println( posto + " vendeu o bilhete " + SC.venderBilhete() +
							" para o filme " + SC.getNomeFilme());
				}
			} catch (InterruptedException ex) {
				System.out.println(ex.getMessage());
			}
		}
	}
}