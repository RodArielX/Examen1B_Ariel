package rmi.servidor;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class ServidorRMI {
    public static void main(String[] args) {
        try {
            // Crea el registro RMI en el puerto 1099
            LocateRegistry.createRegistry(1099);

            System.setProperty("java.rmi.server.hostname", "192.168.1.100");

            RegistroImpl registro = new RegistroImpl();

            // Aquí solo usa el nombre del servicio, no la URL completa
            Naming.rebind("registro", registro);

            System.out.println("Servidor RMI corriendo en el puerto 1099...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


