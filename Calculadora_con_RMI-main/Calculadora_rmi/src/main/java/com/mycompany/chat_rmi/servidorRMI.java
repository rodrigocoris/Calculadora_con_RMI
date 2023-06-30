package com.mycompany.chat_rmi;

import java.net.SocketException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class servidorRMI {

    public static void main(String[] args) throws SocketException {
        try {
            System.setProperty("java.rmi.server.hostname", Env.HOST);

            Registry rmi = LocateRegistry.createRegistry(Env.PORT);

            rmi.rebind("chat", (Remote) new ServidorImpl());

            System.out.println("Servidor activo en " + Env.HOST + ":" + Env.PORT);
        } catch (RemoteException e) {
            System.err.println(e.getMessage());
        }
    }

}
