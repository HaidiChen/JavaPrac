package javaprac.rpc.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface HelloService extends Remote {

    String sayHi(String name) throws RemoteException;
}
