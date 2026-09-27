package com.farmastock.sale.port;

import com.farmastock.sale.domain.Venta;
import java.util.List;
import java.util.Optional;

public interface VentaRepository {
    Venta guardar(Venta venta);
    Optional<Venta> buscarPorId(Integer id);
    List<Venta> listarTodos();
    boolean existePorNroComprobante(String nroComprobante);
}