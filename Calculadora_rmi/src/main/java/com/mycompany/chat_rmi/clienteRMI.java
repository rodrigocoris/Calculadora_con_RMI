
package com.mycompany.chat_rmi;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;


public class clienteRMI{
    
   /*
    public static void main(String[] args){
        try {
            
            String nombre = JOptionPane.showInputDialog("Ingresa tu nombre");
            String nom = nombre;
            
            Registry rmii = LocateRegistry.getRegistry("localhost",1005);
            
            
            chatServidor servidor = (chatServidor) rmii.lookup("chat");
            
            new Thread(new implementacionClienteChat(nom,servidor)).start();
            Intefaz itefaz = new Intefaz(servidor);
            itefaz.setVisible(true);
            
        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    
        
      
    }
*/
    
}
