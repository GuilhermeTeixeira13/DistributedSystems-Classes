package Aula2;

import java.net.*;

public class exc2 {
	public static void main (String args[]) throws Exception{
		InetAddress host = null;
		host = InetAddress.getLocalHost();
		byte ip [] = host.getAddress();
		for ( int i= 0 ; i < ip.length; i++){
			if (i>0) System.out.print(".");
			System.out.print(ip[i] & 0xff);
		}
		System.out.println();
	}
}

/*
 * 
 * O programa executa um loop for para imprimir cada byte do endereço IP 
 * em formato decimal, separado por um ponto. A linha "System.out.print(ip[i] & 0xff);" 
 * converte o byte para um valor inteiro sem sinal e o imprime na saída padrão. 
 * O resultado é o endereço IP do computador local em formato de ponto decimal.
 * 
 * */
 