
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {
	public static void main(String[] args) {
		String machine = "localhost";
		int port = 1099;
		try {
			//Returns a reference to the remote object on the specified host and port
			Registry registry = LocateRegistry.getRegistry(machine,port);
			//GUI object
			GUI calculator = new GUI();
			calculator.calc = (Calculator)registry.lookup("Calculator");
				calculator.textField.setText(String.valueOf(calculator.res));
		} catch (Exception e) {
			System.out.println("Client exception : " + e);
		}
	}
}

/*
 * To launch the Calculator
 * 1. compile all .java files in case if there are any modifications
 * -> javac *.java
 * 2. lauch the Server side code in one terminal
 * -> java Server
 * 3. launch the Client side code in the other terminal
 * -> java Client
 */