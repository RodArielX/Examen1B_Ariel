package rmi.cliente;

import rmi.interfaces.Registro;

import javax.swing.*;
import java.awt.*;
import java.rmi.Naming;

public class Cliente extends JFrame {

    private Registro registro;

    private JTextField nombreField;
    private JTextField correoField;
    private JTextArea salidaArea;

    public Cliente() {
        setTitle("Registro de Usuarios RMI");
        setSize(500, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());

        // Conexión al servidor RMI
        try {
            registro = (Registro) Naming.lookup("rmi://localhost:1099/registro");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "No se pudo conectar al servidor RMI", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }

        // Panel de entrada
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));

        nombreField = new JTextField();
        correoField = new JTextField();

        inputPanel.add(new JLabel("Nombre completo:"));
        inputPanel.add(nombreField);
        inputPanel.add(new JLabel("Correo electrónico:"));
        inputPanel.add(correoField);

        JButton btnRegistrar = new JButton("Registrar");
        JButton btnBuscar = new JButton("Buscar");
        JButton btnSalir = new JButton("Salir");

        btnRegistrar.addActionListener(e -> registrarUsuario());
        btnBuscar.addActionListener(e -> buscarUsuario());
        btnSalir.addActionListener(e -> System.exit(0));

        inputPanel.add(btnRegistrar);
        inputPanel.add(btnBuscar);
        inputPanel.add(btnSalir);

        add(inputPanel, BorderLayout.NORTH);

        // Área de salida
        salidaArea = new JTextArea();
        salidaArea.setEditable(false);
        salidaArea.setBorder(BorderFactory.createTitledBorder("Resultado"));
        salidaArea.setFont(new Font("Arial", Font.PLAIN, 14));

        JScrollPane scrollPane = new JScrollPane(salidaArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));
        add(scrollPane, BorderLayout.CENTER);
    }

    private void registrarUsuario() {
        try {
            String nombre = nombreField.getText().trim();
            String correo = correoField.getText().trim();

            if (nombre.isEmpty() || correo.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe llenar todos los campos", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (!correo.contains("@")) {
                JOptionPane.showMessageDialog(this, "El correo debe contener '@'", "Correo inválido", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String respuesta = registro.registrarUsuario(nombre, correo);
            salidaArea.append("▶ " + respuesta + "\n");
        } catch (Exception ex) {
            salidaArea.append(" Error al registrar usuario\n");
            ex.printStackTrace();
        }
    }

    private void buscarUsuario() {
        try {
            String correo = correoField.getText().trim();
            if (correo.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe ingresar el correo", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String usuario = registro.buscarUsuario(correo);
            salidaArea.append("🔍 Resultado: " + usuario + "\n");
        } catch (Exception ex) {
            salidaArea.append(" Error al buscar usuario\n");
            ex.printStackTrace();
        }
    }
}


