package com.farmastock.sale.infrastructure.memory;

import com.farmastock.sale.domain.Venta;
import com.farmastock.sale.port.VentaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class VentaRepositoryEnMemoria implements VentaRepository {

    private final List<Venta> lista = new ArrayList<>();

    @Override
    public Venta guardar(Venta venta) {
        lista.add(venta);
        return venta;
    }

    @Override
    public List<Venta> obtenerTodas() {
        return List.copyOf(lista);
    }

    @Override
    public Optional<Venta> obtenerPorId(Integer id) {
        return lista.stream()
                .filter(v -> v.getIdVenta() != null && v.getIdVenta().equals(id))
                .findFirst();
    }

    @Override
    public boolean existePorNroComprobante(String nroComprobante) {
        return lista.stream()
                .anyMatch(v -> v.getNroComprobante() != null && v.getNroComprobante().equalsIgnoreCase(nroComprobante));
    }
}