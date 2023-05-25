package com.mycompany.chat_rmi;

import java.awt.HeadlessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javax.swing.JOptionPane;

public class ClienteRMI {

    public static void main(String[] args) {
        try {

            String nombre = JOptionPane.showInputDialog("Ingresa tu nombre");

            Registry registry = LocateRegistry.getRegistry(Env.HOST, Env.PORT);

            Servidor servidor = (Servidor) registry.lookup("chat");
            new Thread(new ClienteImpl(nombre, servidor)).start();
        } catch (HeadlessException | NotBoundException | RemoteException e) {
            System.out.println(e.getMessage());
        }

    }

}
