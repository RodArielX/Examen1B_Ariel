package rmi.servidor;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class ServidorRMI {
    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1099);
            RegistroImpl registro = new RegistroImpl();
            Naming.rebind("rmi://localhost:1099/registro", registro);
            System.out.println("Servidor RMI corriendo en el puerto 1099...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

