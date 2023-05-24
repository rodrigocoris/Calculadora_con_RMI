
package com.mycompany.chat_rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;


public class implementacionChat extends UnicastRemoteObject implements chatServidor {
    
        public ArrayList<chatCliente> clientes;
        ArrayList<Integer> numbers;
        
        //esto nuevo agregue
       double number1, number2;//variables para los numeros
    
    public implementacionChat() throws RemoteException{
        clientes = new ArrayList<chatCliente>();
         numbers = new ArrayList<>();
    }
    
     @Override
    public void mensaje(String mensaje) throws RemoteException {
        int a = 0;
        while(a < clientes.size()){
            clientes.get(a++).mensajeCliente(mensaje);
        }
    }
     @Override
    public void registro(chatCliente cliente) throws RemoteException {
        this.clientes.add(cliente);
    }
    
    @Override
    public void Numeros(int numero) throws RemoteException {
        numbers.add(numero);
    }

    @Override
    public ArrayList<Integer> ObtNumeros() throws RemoteException {
        return numbers;
    }
    
    //esto nuevo agregue
    /* 
    @Override
    public double getNumber() throws RemoteException {
        return number1;
    }
   
    
      @Override
    public void setNumber(double number) throws RemoteException {
        if (number1 == 0) {
            number1 = number;
        } else {
            number2 = number;
        }

    }
*/
    }
    


    

