package com.farmastock.sale.infrastructure.memory;

import com.farmastock.sale.domain.Venta;
import com.farmastock.sale.port.VentaRepository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class VentaRepositoryEnMemoria implements VentaRepository {

    private final Map<Integer, Venta> datos = new LinkedHashMap<>();
    private Integer autoincrementId = 1;

    @Override
    public Venta guardar(Venta venta) {
        if (venta.getIdVenta() == null) {
            venta.setIdVenta(autoincrementId++);
        }
        datos.put(venta.getIdVenta(), venta);
        return venta;
    }

    @Override
    public Optional<Venta> buscarPorId(Integer id) {
        return Optional.ofNullable(datos.get(id));
    }

    @Override
    public List<Venta> listarTodos() {
        return new ArrayList<>(datos.values());
    }

    @Override
    public boolean existePorNroComprobante(String nroComprobante) {
        return datos.values().stream()
                .anyMatch(v -> v.getNroComprobante().equalsIgnoreCase(nroComprobante));
    }
}