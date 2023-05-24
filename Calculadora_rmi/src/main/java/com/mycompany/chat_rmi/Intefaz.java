
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


public class Intefaz extends JFrame implements ActionListener {
    
     private static JTextField display;

    //creacion de mis variables de mis botones
    private JButton btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
    private JButton btnSuma, btnResta, btnMultiplicacion, btnDivision, btnIgual, btnPunto, btnLimpiar;
    //creacion de mis variables para hacer las operaciones con los numeros
    private double num1, num2;
    private String operacion;
    private chatServidor servidor;
   
    
     public Intefaz(chatServidor servidor) {
        // Configuración de la ventana
        this.servidor = servidor;
        setTitle("Calculadora");
        setSize(250, 300);//tamanio de los botones de textFile
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);//PARA que no se ajuste la vetana
        setLocationRelativeTo(null);

        // Creación de componentes
        display = new JTextField(10);
        display.setEditable(false);

        btn0 = new JButton("0");
        btn1 = new JButton("1");
        btn2 = new JButton("2");
        btn3 = new JButton("3");
        btn4 = new JButton("4");
        btn5 = new JButton("5");
        btn6 = new JButton("6");
        btn7 = new JButton("7");
        btn8 = new JButton("8");
        btn9 = new JButton("9");
        btnSuma = new JButton("+");
        btnResta = new JButton("-");
        btnMultiplicacion = new JButton("*");
        btnDivision = new JButton("/");
        btnIgual = new JButton("=");
        btnPunto = new JButton(".");
        btnLimpiar = new JButton("Limpiar");

        // Configuración del panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4));
        panel.add(btn7);
        panel.add(btn8);
        panel.add(btn9);
        panel.add(btnDivision);
        panel.add(btn4);
        panel.add(btn5);
        panel.add(btn6);
        panel.add(btnMultiplicacion);
        panel.add(btn1);
        panel.add(btn2);
        panel.add(btn3);
        panel.add(btnResta);
        panel.add(btn0);
        panel.add(btnPunto);
        panel.add(btnIgual);
        panel.add(btnSuma);
        panel.add(btnLimpiar);

        // Agregar componentes a la ventana
        setLayout(new BorderLayout());
        add(display, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
        add(btnLimpiar, BorderLayout.SOUTH);

        // Configuración de eventos
        btn0.addActionListener(this);
        btn1.addActionListener(this);
        btn2.addActionListener(this);
        btn3.addActionListener(this);
        btn4.addActionListener(this);
        btn5.addActionListener(this);
        btn6.addActionListener(this);
        btn7.addActionListener(this);
        btn8.addActionListener(this);
        btn9.addActionListener(this);
        btnSuma.addActionListener(this);
        btnResta.addActionListener(this);
        btnMultiplicacion.addActionListener(this);
        btnDivision.addActionListener(this);
        btnIgual.addActionListener(this);
        btnPunto.addActionListener(this);
        btnLimpiar.addActionListener(this);
        
        
    }

    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        if (comando.equals("0") || comando.equals("1") || comando.equals("2") || comando.equals("3") ||
                comando.equals("4") || comando.equals("5") || comando.equals("6") || comando.equals("7") ||
                comando.equals("8") || comando.equals("9")) {
            display.setText(display.getText() + comando);
        } else if (comando.equals("+") || comando.equals("-") || comando.equals("*") || comando.equals("/")) {
            num1 = Double.parseDouble(display.getText());
            operacion = comando;
            display.setText("");
        } else if (comando.equals("=")) {
            num2 = Double.parseDouble(display.getText());
            double resultado = 0;
            switch (operacion) {
                case "+":
                    resultado = num1 + num2;
                    break;
                case "-":
                    resultado = num1 - num2;
                    break;
                case "*":
                    resultado = num1 * num2;
                    break;
                case "/":
                    resultado = num1 / num2;
                    break;
            }
            display.setText(String.valueOf(resultado));
        } else if (comando.equals(".")) {
            if (!display.getText().contains(".")) {
                display.setText(display.getText() + ".");
            }
        } else if (comando.equals("Limpiar")) {
            display.setText("");
        }
    }
    
       public static void main(String[] args){
        try {
            
            String nombre = JOptionPane.showInputDialog("Ingresa tu nombre");
            String nom = nombre;
            
            Registry rmii = LocateRegistry.getRegistry("localhost",1005);
            
            
            chatServidor servidor = (chatServidor) rmii.lookup("chat");
            
            new Thread(new implementacionClienteChat(nom,servidor,display)).start();
            Intefaz itefaz = new Intefaz(servidor);
            itefaz.setVisible(true);
            
        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    
       }
}
