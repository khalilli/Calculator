import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;

public class Server {
	
	public static void main(String[] args) {
		try {
			int port = 1099;
			//calc : object of the interface implementation class
			Calculator calc = (Calculator) UnicastRemoteObject.exportObject(new CalculatorImpl(), 0);
			System.out.println("Server is ready!");
			//registry : rmiregistry within the server JVM
			Registry registry = LocateRegistry.createRegistry(port);
			//overwrites (rebinds) the remote object if it doesn't exist
			//otherwise - binds
			if(!Arrays.asList(registry.list()).contains("Calculator"))
				registry.bind("Calculator", calc);
			else 
				registry.rebind("Calculator", calc);
		} catch (Exception e) {
			System.out.println(e);
		}		
	}
}
