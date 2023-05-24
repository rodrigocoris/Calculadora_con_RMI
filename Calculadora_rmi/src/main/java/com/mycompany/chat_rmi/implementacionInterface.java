
package com.mycompany.chat_rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class implementacionInterface extends UnicastRemoteObject implements interfaceRMI{
    
    public implementacionInterface () throws RemoteException{
        super();
    }
    
     @Override
    public String nombre(String nombre) throws RemoteException {
        return nombre;
    }
    
}
