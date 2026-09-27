package com.farmastock.saledetail.application.command;

import java.math.BigDecimal;

public record RegistrarDetalleVentaCommand(
        Integer ventaId,
        Integer loteId,
        Integer cantidadVendida,
        BigDecimal precioUnitario
) {}