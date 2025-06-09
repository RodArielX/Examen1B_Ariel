package rmi.cliente;

import rmi.interfaces.Registro;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.rmi.Naming;

public class VentanaBusqueda extends JFrame {

    private JTextField correoField;
    private JTextArea salidaArea;
    private Registro registro;

    public VentanaBusqueda() {
        setTitle("Buscar Usuario");
        setSize(450, 300);
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

        JPanel campoPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        campoPanel.add(new JLabel("Correo electrónico:"));
        correoField = new JTextField();
        campoPanel.add(correoField);

        JPanel botonesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
        JButton btnBuscar = new JButton("Buscar");
        JButton btnSalir = new JButton("Salir");

        btnBuscar.addActionListener(e -> buscarUsuario());
        btnSalir.addActionListener(e -> dispose());

        botonesPanel.add(btnBuscar);
        botonesPanel.add(btnSalir);

        salidaArea = new JTextArea(4, 30);
        salidaArea.setEditable(false);
        salidaArea.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(salidaArea);

        panel.add(campoPanel, BorderLayout.NORTH);
        panel.add(botonesPanel, BorderLayout.CENTER);
        panel.add(scrollPane, BorderLayout.SOUTH);

        add(panel);
    }

    private void buscarUsuario() {
        String correo = correoField.getText().trim();

        if (correo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe ingresar el correo", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            String respuesta = registro.buscarUsuario(correo);
            salidaArea.setText(respuesta);
        } catch (Exception e) {
            salidaArea.setText("Error al buscar usuario.");
            e.printStackTrace();
        }
    }
}
