package model.manager;

import model.ReservaArticulo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReservaManager {

    private Map<Integer, List<ReservaArticulo>> reservas;

    public ReservaManager() {
        this.reservas = new HashMap<>();
    }

    public void agregarReserva(Integer codigoArticulo, ReservaArticulo reservaArticulo){
        if(reservas.containsKey(codigoArticulo))
            reservas.get(codigoArticulo).add(reservaArticulo);
        else
            reservas.put(codigoArticulo, Arrays.asList(reservaArticulo));
    }
}
