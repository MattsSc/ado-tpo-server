package utils;

import model.*;
import model.enums.TipoMovimiento;

import java.util.Date;

public class DatosUtils {

    private static int INCREMENT = 1;

    public static Cliente crearCliente(){

        Cliente cliente = new Cliente(
                12345,
                "Cliente",
                "Falso",
                "La rioja 241",
                "20-111111111-5",
                "Razon Social",
                200000F,
                200000F
        );

        cliente.save();

        return cliente;
    }

    public static Articulo crearArticulo(int id, float precio){
        Articulo articulo = new Articulo(
                id,
                "Articulo " + id,
                "BOTELLA",
                1,
                1,
                precio,
                10000
        );

        articulo.save();

        return articulo;
    }

    public static Proveedor crearProveedor(int id){
        Proveedor proveedor = new Proveedor(
              "Proveedor " +id,
              20354
        );

        proveedor.save();

        return proveedor;
    }

    public static void registrarMovimiento(Articulo articulo, int cantidad, TipoMovimiento tipoMovimiento){
        MovimientoBasico movimientoBasico = new MovimientoBasico(
                new Date(),
                cantidad,
                tipoMovimiento,
                "Completo"
        );

        movimientoBasico.save(articulo);
    }

    public static void crearOC(Articulo articulo, int cantidad, Proveedor proveedor){
        OrdenDeCompra oc = new OrdenDeCompra(
                articulo,
                cantidad,
                true,
                proveedor
        );

        registrarMovimiento(articulo,cantidad, TipoMovimiento.COMPRA);

        oc.save();
    }

    public static ItemPedido crearItem(Articulo articulo, int cantidad){
        return new ItemPedido(
                cantidad,
                articulo
        );
    }

    public static Lote crearLote(Articulo articulo, int stock, Proveedor proveedor){
        Lote lote = new Lote(
                new Date(),
                stock,
                proveedor
        );

        lote.save(articulo);

        return lote;
    }

    public static void crearUbicacionLlena(Lote lote){
        Ubicacion ubicacion = new Ubicacion(
                "A01010" + INCREMENT,
                true,
                lote,
                lote.getStock()
        );

        INCREMENT++;

        ubicacion.save();

    }

    public static void crearUbicacion(Lote lote, int cantidad){
        Ubicacion ubicacion = new Ubicacion(
                "A01010" + INCREMENT,
                true,
                lote,
                cantidad
        );

        INCREMENT++;

        ubicacion.save();
    }

}
