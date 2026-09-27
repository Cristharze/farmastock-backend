package com.farmastock;

import com.farmastock.sale.domain.Venta;
import com.farmastock.saledetail.domain.DetalleVenta;
import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {
        System.out.println("=== Ejecutando Prueba del Dominio Java 21 ===");

        // 1. Crear la entidad padre
        Venta venta = new Venta(1, "F001-00001");

        // 2. Crear las entidades dependientes
        DetalleVenta det1 = new DetalleVenta(10, 2, new BigDecimal("15.50"));
        DetalleVenta det2 = new DetalleVenta(12, 1, new BigDecimal("45.00"));

        // 3. Asociar mediante método de negocio
        venta.agregarDetalle(det1);
        venta.agregarDetalle(det2);

        // 4. Mostrar evidencia en consola
        System.out.println("Comprobante: " + venta.getNroComprobante());
        System.out.println("Estado: " + venta.getEstado());
        System.out.println("Fecha de registro: " + venta.getFechaVenta());
        System.out.println("Cantidad de productos: " + venta.getDetalles().size());
        System.out.println("Total calculado: S/ " + venta.getTotalVenta());
    }
}