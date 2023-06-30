package com.mycompany.chat_rmi;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Calculadora extends JFrame {

    class ManejadorDeEventos implements ActionListener {

        String numero = "";
        String operacion = "";

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println(e.getActionCommand());

            String comando = e.getActionCommand();

            switch (comando) {
                case "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" -> {
                    numero += comando;
                    display.setText("" + Double.valueOf(numero));
                }
                case "+", "-", "*", "/" -> {
                    try {
                        operacion = comando;
                        display.setText(servidor.getNumeroUno() + " " + operacion + " " + servidor.getNumeroDos());
                        // display.setText(display.getText() + " " + comando);
                    } catch (RemoteException ex) {
                        Logger.getLogger(Calculadora.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                case "=" -> {
                    try {
                        //num2 = Double.parseDouble(display.getText());
                        double resultado = 0;
                        switch (operacion) {
                            case "+" ->
                                resultado = servidor.getNumeroUno() + servidor.getNumeroDos();
                            case "-" ->
                                resultado = servidor.getNumeroUno() - servidor.getNumeroDos();
                            case "*" ->
                                resultado = servidor.getNumeroUno() * servidor.getNumeroDos();
                            case "/" ->
                                resultado = servidor.getNumeroUno() / servidor.getNumeroDos();
                        }
                        display.setText(servidor.getNumeroUno() + " " + operacion + " " + servidor.getNumeroDos() + " = " + resultado);
                        // display.setText(String.valueOf(resultado));
                    } catch (RemoteException ex) {
                        Logger.getLogger(Calculadora.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                case "." -> {
                    if (!display.getText().contains(".")) {
                        numero += ".";
                        display.setText(display.getText() + ".");
                    }
                }
                case "Enviar" -> {
                    if (numero.length() == 0) {
                        JOptionPane.showMessageDialog(null, "Selecciona un número primero");
                        return;
                    }
                    cliente.mensaje(numero);
                    // System.out.println("Se envio!");
                }
                case "Limpiar" -> {
                    try {
                        numero = "";
                        if (servidor.getNumeroUno() != 0.0) {
                            servidor.setNumeroUno(0.0);
                        } else {
                            servidor.setNumeroDos(0.0);
                        }
                        display.setText("");
                    } catch (RemoteException ex) {
                        Logger.getLogger(Calculadora.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                default -> {
                }
            }
        }

    }

    private static JTextField display;

    //creacion de mis variables de mis botones
    private final JButton btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
    private final JButton btnSuma, btnResta, btnMultiplicacion, btnDivision, btnIgual, btnPunto, btnEnviar, btnLimpiar;
    private static Servidor servidor;
    private static ClienteImpl cliente;

    public Calculadora() {
        // Configuración de la ventana
        setTitle("Calculadora");
        setSize(250, 300);//tamanio de los botones de textFile
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);//PARA que no se ajuste la vetana
        setLocationRelativeTo(null);

        // Creación de componentes
        display = new JTextField(10);
        display.setEditable(false);

        btn0 = new JButton("0");
        btn0.setBackground(Color.blue);
        btn1 = new JButton("1");
        btn1.setBackground(Color.blue);
        btn2 = new JButton("2");
        btn2.setBackground(Color.blue);
        btn3 = new JButton("3");
        btn3.setBackground(Color.blue);
        btn4 = new JButton("4");
        btn4.setBackground(Color.blue);
        btn5 = new JButton("5");
        btn5.setBackground(Color.blue);
        btn6 = new JButton("6");
        btn6.setBackground(Color.blue);
        btn7 = new JButton("7");
        btn7.setBackground(Color.blue);
        btn8 = new JButton("8");
        btn8.setBackground(Color.blue);
        btn9 = new JButton("9");
        btn9.setBackground(Color.blue);
        btnSuma = new JButton("+");
        btnSuma.setBackground(Color.blue);
        btnResta = new JButton("-");
        btnResta.setBackground(Color.blue);
        btnMultiplicacion = new JButton("*");
        btnMultiplicacion.setBackground(Color.blue);
        btnDivision = new JButton("/");
        btnDivision.setBackground(Color.blue);
        btnIgual = new JButton("=");
        btnIgual.setBackground(Color.blue);
        btn0.setBackground(Color.blue);
        btnPunto = new JButton(".");
        btnPunto.setEnabled(false);
        btnEnviar = new JButton("Enviar");
        btnLimpiar = new JButton("Limpiar");

        // Configuración del panel
        JPanel panel = new JPanel(), panelBtns = new JPanel();
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
        panelBtns.setLayout(new GridLayout(2, 1));

        panelBtns.add(btnEnviar);
        panelBtns.add(btnLimpiar);
        // panel.add(panelBtns);

        // Agregar componentes a la ventana
        setLayout(new BorderLayout());
        add(display, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
        add(panelBtns, BorderLayout.SOUTH);

        // Configuración de eventos
        ManejadorDeEventos manejadorDeEventos = new ManejadorDeEventos();
        btn0.addActionListener(manejadorDeEventos);
        btn1.addActionListener(manejadorDeEventos);
        btn2.addActionListener(manejadorDeEventos);
        btn3.addActionListener(manejadorDeEventos);
        btn4.addActionListener(manejadorDeEventos);
        btn5.addActionListener(manejadorDeEventos);
        btn6.addActionListener(manejadorDeEventos);
        btn7.addActionListener(manejadorDeEventos);
        btn8.addActionListener(manejadorDeEventos);
        btn9.addActionListener(manejadorDeEventos);
        btnSuma.addActionListener(manejadorDeEventos);
        btnResta.addActionListener(manejadorDeEventos);
        btnMultiplicacion.addActionListener(manejadorDeEventos);
        btnDivision.addActionListener(manejadorDeEventos);
        btnIgual.addActionListener(manejadorDeEventos);
        btnPunto.addActionListener(manejadorDeEventos);
        btnEnviar.addActionListener(manejadorDeEventos);
        btnLimpiar.addActionListener(manejadorDeEventos);
    }

    public static void main(String[] args) {
        try {
            
            // String nombre = JOptionPane.showInputDialog("Ingresa tu nombre");
            Registry registry = LocateRegistry.getRegistry(Env.HOST, Env.PORT);

            servidor = (Servidor) registry.lookup("chat");

            cliente = new ClienteImpl("", servidor);
            new Thread(cliente).start();
            Calculadora itefaz = new Calculadora();
            itefaz.setVisible(true);

        } catch (HeadlessException | NotBoundException | RemoteException e) {
            System.out.println(e.getMessage());
        }

    }
}
