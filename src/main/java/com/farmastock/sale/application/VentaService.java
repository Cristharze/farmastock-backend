package com.farmastock.sale.application;

import com.farmastock.sale.domain.Venta;
import com.farmastock.sale.exception.NroComprobanteDuplicadoException;
import com.farmastock.sale.exception.VentaNoEncontradaException;
import com.farmastock.sale.infrastructure.adapter.in.web.dto.CrearVentaRequest;
import com.farmastock.sale.port.VentaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VentaService {

    private final VentaRepository repository;

    public VentaService(VentaRepository repository) {
        this.repository = repository;
    }

    // Registro para Capítulo 04 (API REST con DTO)
    public Venta registrar(CrearVentaRequest request) {
        if (repository.existePorNroComprobante(request.nroComprobante())) {
            throw new NroComprobanteDuplicadoException("El número de comprobante ya existe: " + request.nroComprobante());
        }
        Integer idGenerado = repository.obtenerTodas().size() + 1;
        Venta nuevaVenta = new Venta(
                idGenerado,
                request.idUsuarioCajero(),
                request.nroComprobante(),
                request.totalVenta()
        );
        return repository.guardar(nuevaVenta);
    }

    // Registro para Capítulos 01/02 (Main.java)
    public Venta registrar(Venta venta) {
        if (repository.existePorNroComprobante(venta.getNroComprobante())) {
            throw new NroComprobanteDuplicadoException("El número de comprobante ya existe: " + venta.getNroComprobante());
        }
        return repository.guardar(venta);
    }

    public List<Venta> listar() {
        return repository.obtenerTodas();
    }

    public Optional<Venta> buscarPorId(Integer id) {
        return repository.obtenerPorId(id);
    }

    // Compatibilidad para Main.java
    public Venta obtenerPorId(Integer id) {
        return repository.obtenerPorId(id)
                .orElseThrow(() -> new VentaNoEncontradaException(id));
    }
}