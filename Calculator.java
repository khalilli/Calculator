import java.rmi.Remote;
import java.rmi.RemoteException;

//Creating a Calculator interface 
public interface Calculator extends Remote {
	//method prototypes
	// x, y : integer values of the operands	
	
	//return value : sum of x and y
	public int sum(int x, int y) throws RemoteException;
	//return value : subtraction of x and y
	public int subtraction(int x, int y) throws RemoteException;
	//return value : product of x and y
	public int product(int x, int y) throws RemoteException;
	//precondition : y should not be equal to zero
	//return value : division of x and y
	public int division(int x, int y) throws RemoteException;
}
