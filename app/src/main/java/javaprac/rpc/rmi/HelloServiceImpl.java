package javaprac.rpc.rmi;

import java.rmi.RemoteException;

import java.rmi.server.UnicastRemoteObject;


public class HelloServiceImpl extends UnicastRemoteObject implements HelloService {

    private static final long serialVersionUID = -622343243243223L;

    public HelloServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public String sayHi(String name) throws RemoteException {
        return "Hello, " + name;
    }
}
