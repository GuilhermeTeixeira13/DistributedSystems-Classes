package MultiThreaded;

public class Premio {
	private int valor;
	
	public int getValor() {
		return valor;
	}
	
	public synchronized void setValor(int valor) {
		this.valor = valor;
	}
	
	public synchronized void incValor() {
		valor ++;
	} 
}
