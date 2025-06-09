package rmi.cliente;

import rmi.interfaces.Registro;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.rmi.Naming;

public class VentanaRegistro extends JFrame {

    private JTextField nombreField;
    private JTextField correoField;
    private JTextArea salidaArea;
    private Registro registro;

    public VentanaRegistro() {
        setTitle("Registro de Usuario");
        setSize(450, 350);
        setLocationRelativeTo(null);
        setResizable(false);

        try {
            registro = (Registro) Naming.lookup("rmi://192.168.56.1:1099/registro");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error conectando al servidor RMI", "Error", JOptionPane.ERROR_MESSAGE);
            dispose();
            return;
        }

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(new EmptyBorder(15, 15, 15, 15));

        // Entrada de datos
        JPanel camposPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        camposPanel.add(new JLabel("Nombre completo:"));
        nombreField = new JTextField();
        camposPanel.add(nombreField);
        camposPanel.add(new JLabel("Correo electrónico:"));
        correoField = new JTextField();
        camposPanel.add(correoField);

        // Botones
        JPanel botonesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
        JButton btnRegistrar = new JButton("Registrar");
        JButton btnSalir = new JButton("Salir");

        btnRegistrar.addActionListener(e -> registrarUsuario());
        btnSalir.addActionListener(e -> dispose());

        botonesPanel.add(btnRegistrar);
        botonesPanel.add(btnSalir);

        // Área de salida
        salidaArea = new JTextArea(5, 30);
        salidaArea.setEditable(false);
        salidaArea.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(salidaArea);

        panel.add(camposPanel, BorderLayout.NORTH);
        panel.add(botonesPanel, BorderLayout.CENTER);
        panel.add(scrollPane, BorderLayout.SOUTH);

        add(panel);
    }

    private void registrarUsuario() {
        String nombre = nombreField.getText().trim();
        String correo = correoField.getText().trim();

        if (nombre.isEmpty() || correo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!correo.contains("@")) {
            JOptionPane.showMessageDialog(this, "El correo debe contener '@'", "Correo inválido", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            String respuesta = registro.registrarUsuario(nombre, correo);
            salidaArea.setText(respuesta);
        } catch (Exception e) {
            salidaArea.setText("Error al registrar usuario.");
            e.printStackTrace();
        }
    }
}


