package rmi.cliente;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Cliente extends JFrame {

    public Cliente() {
        setTitle("Menú Principal - Registro RMI");
        setSize(400, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Panel principal con margen
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Título
        JLabel titulo = new JLabel("Sistema de Registro", SwingConstants.CENTER);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 20));
        titulo.setBorder(new EmptyBorder(10, 0, 20, 0));
        panelPrincipal.add(titulo, BorderLayout.NORTH);

        // Botones centrados con espaciado
        JPanel panelBotones = new JPanel(new GridLayout(3, 1, 10, 10));
        JButton btnRegistro = new JButton("Registrar Usuario");
        JButton btnBuscar = new JButton("Buscar Usuario");
        JButton btnSalir = new JButton("Salir");

        // Estilo de botones
        Font botonFont = new Font("SansSerif", Font.PLAIN, 16);
        btnRegistro.setFont(botonFont);
        btnBuscar.setFont(botonFont);
        btnSalir.setFont(botonFont);

        btnRegistro.addActionListener(e -> new VentanaRegistro().setVisible(true));
        btnBuscar.addActionListener(e -> new VentanaBusqueda().setVisible(true));
        btnSalir.addActionListener(e -> System.exit(0));

        panelBotones.add(btnRegistro);
        panelBotones.add(btnBuscar);
        panelBotones.add(btnSalir);

        panelPrincipal.add(panelBotones, BorderLayout.CENTER);

        add(panelPrincipal);
    }
}

