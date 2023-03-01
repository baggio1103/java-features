package remoterepository;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Repository <T> extends Remote {

    T persist(T t) throws RemoteException;

}
