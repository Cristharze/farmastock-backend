package com.farmastock.saledetail.domain;

import java.math.BigDecimal;

public class DetalleVenta {

    private Integer idDetalleVenta;
    private Integer idLote;
    private Integer cantidadVendida;
    private BigDecimal precioUnitarioVenta;
    private BigDecimal subtotal;

    public DetalleVenta(Integer idLote, Integer cantidadVendida, BigDecimal precioUnitarioVenta) {
        // Regla de negocio: no se aceptan cantidades o precios <= 0
        if (cantidadVendida == null || cantidadVendida <= 0) {
            throw new IllegalArgumentException("La cantidad vendida debe ser mayor a cero.");
        }
        if (precioUnitarioVenta == null || precioUnitarioVenta.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El precio unitario debe ser mayor a cero.");
        }

        this.idLote = idLote;
        this.cantidadVendida = cantidadVendida;
        this.precioUnitarioVenta = precioUnitarioVenta;
        this.subtotal = precioUnitarioVenta.multiply(BigDecimal.valueOf(cantidadVendida));
    }

    // Getters
    public Integer getIdDetalleVenta() { return idDetalleVenta; }
    public void setIdDetalleVenta(Integer idDetalleVenta) { this.idDetalleVenta = idDetalleVenta; }

    public Integer getIdLote() { return idLote; }
    public Integer getCantidadVendida() { return cantidadVendida; }
    public BigDecimal getPrecioUnitarioVenta() { return precioUnitarioVenta; }
    public BigDecimal getSubtotal() { return subtotal; }
}