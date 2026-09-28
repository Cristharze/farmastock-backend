package com.farmastock.sale.domain;

import com.farmastock.saledetail.domain.DetalleVenta;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Venta {

    private Integer idVenta;
    private Integer idUsuarioCajero;
    private String nroComprobante;
    private LocalDateTime fechaVenta;
    private BigDecimal totalVenta;
    private EstadoVenta estado;

    // Colección privada para la relación 1:N
    private final List<DetalleVenta> detalles = new ArrayList<>();

    // Constructor base (Capítulos 01 y 02)
    public Venta(Integer idUsuarioCajero, String nroComprobante) {
        if (nroComprobante == null || nroComprobante.isBlank()) {
            throw new IllegalArgumentException("El número de comprobante es obligatorio.");
        }
        this.idUsuarioCajero = idUsuarioCajero;
        this.nroComprobante = nroComprobante;
        this.fechaVenta = LocalDateTime.now();
        this.estado = EstadoVenta.CONFIRMADA;
        this.totalVenta = BigDecimal.ZERO;
    }

    // Constructor extendido (Para registrar desde API REST en Capítulo 04)
    public Venta(Integer idVenta, Integer idUsuarioCajero, String nroComprobante, BigDecimal totalVenta) {
        this(idUsuarioCajero, nroComprobante);
        this.idVenta = idVenta;
        if (totalVenta != null) {
            this.totalVenta = totalVenta;
        }
    }

    // Método de negocio para proteger la agregación de ítems
    public void agregarDetalle(DetalleVenta detalle) {
        if (this.estado == EstadoVenta.ANULADA) {
            throw new IllegalStateException("No se pueden agregar detalles a una venta anulada.");
        }
        if (detalle == null) {
            throw new IllegalArgumentException("El detalle no puede ser nulo.");
        }
        this.detalles.add(detalle);
        recalcularTotal();
    }

    private void recalcularTotal() {
        this.totalVenta = detalles.stream()
                .map(DetalleVenta::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    // Getters y Setters
    public Integer getIdVenta() { return idVenta; }
    public void setIdVenta(Integer idVenta) { this.idVenta = idVenta; }

    // Alias genérico para compatibilidad
    public Integer getId() { return idVenta; }

    public Integer getIdUsuarioCajero() { return idUsuarioCajero; }
    public String getNroComprobante() { return nroComprobante; }
    public LocalDateTime getFechaVenta() { return fechaVenta; }
    public BigDecimal getTotalVenta() { return totalVenta; }
    public EstadoVenta getEstado() { return estado; }

    // Retorna una lista inmutable para proteger el encapsulamiento
    public List<DetalleVenta> getDetalles() {
        return Collections.unmodifiableList(detalles);
    }
}