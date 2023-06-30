package com.mycompany.chat_rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class ServidorImpl extends UnicastRemoteObject implements Servidor {

    public ArrayList<Cliente> clientes;
    public double numeroUno, numeroDos;//variables para los numeros

    public ServidorImpl() throws RemoteException {
        clientes = new ArrayList<>();
    }

    @Override
    public void mensaje(String mensaje) throws RemoteException {
        int a = 0;
        while (a < clientes.size()) {
            clientes.get(a++).mensajeCliente(mensaje);
        }
    }

    @Override
    public void registro(Cliente cliente) throws RemoteException {
        System.out.println("Cliente conectado!");
        clientes.add(cliente);
    }

    @Override
    public double getNumeroUno() throws RemoteException {
        return numeroUno;
    }

    @Override
    public double getNumeroDos() throws RemoteException {
        return numeroDos;
    }

    @Override
    public void setNumeroUno(double numero) throws RemoteException {
        System.out.println("Seteo el numero 1");
        numeroUno = numero;
    }

    @Override
    public void setNumeroDos(double numero) throws RemoteException {
        System.out.println("Seteo el numero 2");
        numeroDos = numero;
    }
}
