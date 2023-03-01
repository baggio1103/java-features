package remotecalculator;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class CalculatorServer {

    public static final String SERVER_NAME = "REMOTE_CALCULATOR";

    public static void main(String[] args) throws RemoteException, AlreadyBoundException, InterruptedException {
        var remoteCalculator = new RemoteCalculator();
        var registry = LocateRegistry.createRegistry(2222);
        var calculator = UnicastRemoteObject.exportObject(remoteCalculator, 0);
        registry.bind(SERVER_NAME, calculator);
        System.out.println("Server ready");
    }

}
