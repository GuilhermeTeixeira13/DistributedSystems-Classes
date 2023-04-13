package MultiThreadAlunos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
	public static void main(String[] args) {
		// Set server IP and port number
		String serverAddress = "127.0.0.1";
		int portNumber = 2222;

		try {
			// Connect to server
			Socket socket = new Socket(serverAddress, portNumber);

			// Initialize input and output streams
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

			// Read welcome message from server
			String inputLine = in.readLine();
			System.out.println(inputLine);

			// If server is full, close the connection
			if (inputLine.equals("Server reached maximum capacity, try again later.")) {
				System.out.println("Closing connection...");
				socket.close();
			} else {
				// Loop until user quits
				while (true) {
					System.out.println();
					System.out.println("1. Register student");
					System.out.println("2. Consult students");
					System.out.println("3. Consult accesses");
					System.out.println("4. View student data");
					System.out.println("5. Quit");
					System.out.print("Enter your choice (1-4): ");

					// Read user input
					String choice = new BufferedReader(new InputStreamReader(System.in)).readLine();

					// Send user choice to server
					out.println(choice);
					out.flush();

					// Process user choice
					switch (choice) {
					case "1":
						// Register student
						System.out.print("\nNumber: ");
						String number = new BufferedReader(new InputStreamReader(System.in)).readLine();
						out.println(number);

						System.out.print("Name: ");
						String name = new BufferedReader(new InputStreamReader(System.in)).readLine();
						out.println(name);

						System.out.print("Course: ");
						String course = new BufferedReader(new InputStreamReader(System.in)).readLine();
						out.println(course);

						System.out.print("Phone number: ");
						String phone = new BufferedReader(new InputStreamReader(System.in)).readLine();
						out.println(phone);

						System.out.print("Email: ");
						String email = new BufferedReader(new InputStreamReader(System.in)).readLine();
						out.println(email);
						break;

					case "4":
						// View student data
						System.out.print("\nName: ");
						String studentName = new BufferedReader(new InputStreamReader(System.in)).readLine();
						out.println(studentName);
						break;

					case "5":
						// Quit
						System.out.println("Closing connection...");
						socket.close();
						return;
					}

					// Read server response
					while ((inputLine = in.readLine()) != null) {
						if (inputLine.equals("EOF")) {
							break;
						}
						System.out.println(inputLine);
					}
				}
			}
		} catch (IOException e) {
			System.err.println("Error: " + e.getMessage());
		}
	}
}