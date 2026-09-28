package com.farmastock.sale.infrastructure.adapter.in.web;

import com.farmastock.sale.application.VentaService;
import com.farmastock.sale.domain.Venta;
import com.farmastock.sale.infrastructure.adapter.in.web.dto.CrearVentaRequest;
import com.farmastock.sale.infrastructure.adapter.in.web.dto.VentaResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    private final VentaService service;

    public VentaController(VentaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<VentaResponse> crear(@Valid @RequestBody CrearVentaRequest request) {
        Venta venta = service.registrar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapearAResponse(venta));
    }

    @GetMapping
    public List<VentaResponse> listar(@RequestParam(required = false) String nroComprobante) {
        return service.listar().stream()
                .filter(v -> nroComprobante == null || (v.getNroComprobante() != null && v.getNroComprobante().toLowerCase().contains(nroComprobante.toLowerCase())))
                .map(this::mapearAResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<VentaResponse> buscarPorId(@PathVariable Integer id) {
        return service.buscarPorId(id)
                .map(venta -> ResponseEntity.ok(mapearAResponse(venta)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    private VentaResponse mapearAResponse(Venta venta) {
        return new VentaResponse(
                venta.getIdVenta(),
                venta.getIdUsuarioCajero(),
                venta.getNroComprobante(),
                venta.getFechaVenta(),
                venta.getTotalVenta(),
                venta.getEstado() != null ? venta.getEstado().name() : "CONFIRMADA"
        );
    }
}