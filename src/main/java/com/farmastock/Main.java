package com.farmastock;

import com.farmastock.sale.application.VentaService;
import com.farmastock.sale.domain.Venta;
import com.farmastock.sale.infrastructure.memory.VentaRepositoryEnMemoria;
import com.farmastock.sale.port.VentaRepository;
import com.farmastock.saledetail.domain.DetalleVenta;

import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {
        System.out.println("=== TEST CAPÍTULO 02: PARKFLOW / FARMASTOCK ===");

        // Inyección manual de dependencias
        VentaRepository repository = new VentaRepositoryEnMemoria();
        VentaService service = new VentaService(repository);

        // 1. Registro exitoso de primera venta
        Venta v1 = new Venta(101, "F001-00001");
        v1.agregarDetalle(new DetalleVenta(5, 2, new BigDecimal("12.50")));
        service.registrar(v1);

        // 2. Registro exitoso de segunda venta
        Venta v2 = new Venta(102, "F001-00002");
        v2.agregarDetalle(new DetalleVenta(8, 1, new BigDecimal("30.00")));
        service.registrar(v2);

        System.out.println("Ventas registradas en el repositorio: " + service.listar().size());

        // 3. Búsqueda por ID existente
        Venta encontrada = service.obtenerPorId(1);
        System.out.println("Venta encontrada #1 Comprobante: " + encontrada.getNroComprobante());

        // 4. Caso negativo: Búsqueda por ID inexistente (debe capturar excepción)
        try {
            service.obtenerPorId(999);
        } catch (RuntimeException ex) {
            System.out.println("ERROR CONTROLADO BUSQUEDA: " + ex.getMessage());
        }

        // 5. Caso negativo: Intento de guardar comprobante duplicado
        try {
            Venta vDuplicada = new Venta(103, "F001-00001");
            service.registrar(vDuplicada);
        } catch (RuntimeException ex) {
            System.out.println("ERROR CONTROLADO UNIQUE: " + ex.getMessage());
        }
    }
}