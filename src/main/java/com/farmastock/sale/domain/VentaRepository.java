package com.farmastock.sale.domain;

import java.util.Optional;

public interface VentaRepository {
    Venta guardar(Venta venta);
    Optional<Venta> buscarPorId(Integer id);
}