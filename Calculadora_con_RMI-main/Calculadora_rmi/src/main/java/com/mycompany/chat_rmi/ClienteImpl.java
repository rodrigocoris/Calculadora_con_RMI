package com.mycompany.chat_rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClienteImpl extends UnicastRemoteObject implements Cliente, Runnable {

    Servidor servidor;
    public String nombre = null;

    ClienteImpl(String nombre, Servidor servidor) throws RemoteException {
        this.nombre = nombre;
        this.servidor = servidor;
    }

    @Override
    public void mensajeCliente(String mensaje) throws RemoteException {
        System.err.println(mensaje);
    }

    @Override
    public void run() {
        Scanner escaner = new Scanner(System.in);
        String mensaje;

        while (true) {
            mensaje = escaner.nextLine();
            try {
                servidor.mensaje(nombre + " : " + mensaje);
            } catch (RemoteException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public void mensaje(String mensaje) {
        try {
            System.out.println(servidor.getNumeroUno());
            if (servidor.getNumeroUno() == 0.0) {
                servidor.setNumeroUno(Double.parseDouble(mensaje));
            } else {
                servidor.setNumeroDos(Double.parseDouble(mensaje));
            }
            servidor.mensaje(nombre + " : " + mensaje);
        } catch (RemoteException ex) {
            Logger.getLogger(ClienteImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
