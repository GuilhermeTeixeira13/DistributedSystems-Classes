package Aula2;

import java.net.*;

public class exc1 {
	public static void main (String args[]) throws Exception{
		InetAddress host = null;
		host = InetAddress.getLocalHost();
		System.out.println(host.getHostName());
	} 
}
