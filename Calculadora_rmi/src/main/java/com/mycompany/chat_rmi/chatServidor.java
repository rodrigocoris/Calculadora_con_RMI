
package com.mycompany.chat_rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;


public interface chatServidor extends Remote{
    void registro(chatCliente cliente) throws RemoteException;
    void mensaje(String mensaje) throws RemoteException;
    void Numeros(int numero) throws RemoteException;
    ArrayList<Integer> ObtNumeros() throws RemoteException;
    
    
    ///////////////////////////////////Esto se necesita para obtener los numeros de la calculadora
    
    //void setNumber(double number) throws RemoteException;
    
    //double getNumber1() throws RemoteException;
    
   //double getNumber2() throws RemoteException;
}
