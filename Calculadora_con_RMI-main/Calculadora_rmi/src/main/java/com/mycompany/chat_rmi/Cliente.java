package com.mycompany.chat_rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Cliente extends Remote {

    void mensajeCliente(String mensaje) throws RemoteException;

}
