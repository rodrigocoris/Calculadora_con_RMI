
package com.mycompany.chat_rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;
import javax.swing.JTextField;


public class implementacionClienteChat extends UnicastRemoteObject implements chatCliente, Runnable {
    chatServidor servidor;
    public String nombre = null;
    JTextField display;
    
    implementacionClienteChat(String nombre, chatServidor servidor, JTextField display) throws RemoteException {
        this.nombre = nombre;
        this.servidor = servidor;
        this.display = display;
        servidor.registro(this);
    }
    
     @Override
    public void mensajeCliente(String mensaje) throws RemoteException {
        System.err.println(mensaje);
        //esto le agregue
        /*
         if (mensaje.equals("+")) {
            servidor.setNumber(0);             
            display.append(servidor.number1 + " + " + servidor.number2 + " = " + (servidor.number1 + servidor.number2));
        */
    }
    
     @Override
    public void run(){
        Scanner s = new Scanner(System.in);
        String mensaje;
        
        while(true){
            mensaje = s.nextLine();
            try {
                servidor.mensaje(nombre+ " : " + mensaje);
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
}
