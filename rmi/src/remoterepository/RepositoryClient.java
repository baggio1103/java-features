package remoterepository;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class RepositoryClient {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws RemoteException, NotBoundException {
        var registry = LocateRegistry.getRegistry(2222);
        var repository =  (Repository<Student>) registry.lookup(RepositoryServer.REPOSITORY_SERVER);
        var student = new Student("baggio");
        var persistedStudent = repository.persist(student);
        System.out.println("Before persistence: " + student);
        System.out.println("After persistence: " + persistedStudent);
    }

}
