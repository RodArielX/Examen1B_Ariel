package rmi.servidor;

import rmi.interfaces.Registro;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Random;

public class RegistroImpl extends UnicastRemoteObject implements Registro {

    private static final HashMap<String, String> correoAUsuario = new HashMap<>();

    public RegistroImpl() throws RemoteException {
        super();
    }

    @Override
    public String registrarUsuario(String nombreCompleto, String correo) throws RemoteException {
        if (correoAUsuario.containsKey(correo)) {
            return "Correo ya registrado. Usuario: " + correoAUsuario.get(correo);
        }
        String usuario = generarNombreUsuario(nombreCompleto);
        correoAUsuario.put(correo, usuario);
        return "Usuario registrado correctamente: " + usuario;
    }

    @Override
    public String buscarUsuario(String correo) throws RemoteException {
        return correoAUsuario.getOrDefault(correo, "Correo no encontrado.");
    }

    private static String generarNombreUsuario(String nombreCompleto) {
        String[] partes = nombreCompleto.trim().split(" ");
        String base = partes[0].toLowerCase();
        int numero = new Random().nextInt(9000) + 1000;
        return base + numero;
    }
}

