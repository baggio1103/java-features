package remotecalculator;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Calculator extends Remote  {

    Integer multiply(int x, int y) throws RemoteException;

}
