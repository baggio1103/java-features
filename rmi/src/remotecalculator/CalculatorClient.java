package remotecalculator;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class CalculatorClient {

    public static void main(String[] args) throws RemoteException, NotBoundException {
        var registry = LocateRegistry.getRegistry(2222);
        var remoteCalculator = (Calculator) registry.lookup(CalculatorServer.SERVER_NAME);
        var result = remoteCalculator.multiply(10, 1000);
        System.out.println(result);
    }

}
