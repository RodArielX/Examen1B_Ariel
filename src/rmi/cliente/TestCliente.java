package rmi.cliente;

public class TestCliente {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            Cliente ventana = new Cliente();
            ventana.setVisible(true);
        });
    }
}


