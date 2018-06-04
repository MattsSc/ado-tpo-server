package server;

import interfaces.SistemaArticulo;
import interfaces.SistemaCliente;
import interfaces.SistemaCompra;
import interfaces.SistemaPedido;
import remoteObjects.ControladorArticuloRO;
import remoteObjects.ControladorClienteRO;
import remoteObjects.ControladorCompraRO;
import remoteObjects.ControladorPedidoRO;
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
            SistemaPedido sistemaPedido = new ControladorPedidoRO();
            SistemaCompra sistemaCompra = new ControladorCompraRO();
            SistemaArticulo sistemaArticulo = new ControladorArticuloRO();
            LocateRegistry.createRegistry(1099);
            Naming.rebind("//127.0.0.1:1099/ControladorCliente", sistemaCliente);
            Naming.rebind("//127.0.0.1:1099/ControladorPedido", sistemaPedido);
            Naming.rebind("//127.0.0.1:1099/ControladorCompra", sistemaCompra);
            Naming.rebind("//127.0.0.1:1099/ControladorArticulo", sistemaArticulo);
            HibernateUtils.getSessionFactory();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
