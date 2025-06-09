package rmi.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Registro extends Remote {
    String registrarUsuario(String nombreCompleto, String correo) throws RemoteException;
    String buscarUsuario(String correo) throws RemoteException;
}



