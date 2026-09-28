package com.farmastock.sale.port;

import com.farmastock.sale.domain.Venta;
import java.util.List;
import java.util.Optional;

public interface VentaRepository {
    Venta guardar(Venta venta);
    List<Venta> obtenerTodas();
    Optional<Venta> obtenerPorId(Integer id);
    boolean existePorNroComprobante(String nroComprobante);
}