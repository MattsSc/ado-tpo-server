package server;

import interfaces.SistemaCliente;
import remoteObjects.ControladorClienteRO;
import utils.HibernateUtils;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class Servidor {

    public Servidor(){
        System.setProperty("java.security.policy", "java.policy");
        try{
            SistemaCliente sistemaCliente = new ControladorClienteRO();
            LocateRegistry.createRegistry(1099);
            Naming.rebind("//127.0.0.1:1099/ControladorCliente", sistemaCliente);
            HibernateUtils.getSessionFactory();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
