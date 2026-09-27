package com.farmastock.sale.application;

import com.farmastock.sale.domain.Venta;
import com.farmastock.sale.exception.NroComprobanteDuplicadoException;
import com.farmastock.sale.exception.VentaNoEncontradaException;
import com.farmastock.sale.port.VentaRepository;

import java.util.List;

public class VentaService {

    private final VentaRepository repository;

    public VentaService(VentaRepository repository) {
        this.repository = repository;
    }

    public Venta registrar(Venta venta) {
        if (repository.existePorNroComprobante(venta.getNroComprobante())) {
            throw new NroComprobanteDuplicadoException(venta.getNroComprobante());
        }
        return repository.guardar(venta);
    }

    public Venta obtenerPorId(Integer id) {
        return repository.buscarPorId(id)
                .orElseThrow(() -> new VentaNoEncontradaException(id));
    }

    public List<Venta> listar() {
        return repository.listarTodos();
    }
}