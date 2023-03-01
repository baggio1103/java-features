package remoterepository;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class RepositoryServer {

    public static final String REPOSITORY_SERVER = "STUDENT_REPOSITORY";

    public static void main(String[] args) throws RemoteException, AlreadyBoundException {
        var studentRepository = new StudentRemoteRepository();
        var registry = LocateRegistry.createRegistry(2222);
        var  repository = UnicastRemoteObject.exportObject(studentRepository, 0);
        registry.bind(REPOSITORY_SERVER, repository);
        System.out.println("Student repository server started");
    }

}
