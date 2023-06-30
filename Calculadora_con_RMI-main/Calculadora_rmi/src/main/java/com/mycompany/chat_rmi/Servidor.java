package com.mycompany.chat_rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Servidor extends Remote {

    void registro(Cliente cliente) throws RemoteException;

    void mensaje(String mensaje) throws RemoteException;

    double getNumeroUno() throws RemoteException;

    double getNumeroDos() throws RemoteException;
    
    void setNumeroUno(double numero) throws RemoteException;

    void setNumeroDos(double numero) throws RemoteException;
}
