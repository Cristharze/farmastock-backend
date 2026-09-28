package com.farmastock.sale.infrastructure.adapter.in.web.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record VentaResponse(
        Integer idVenta,
        Integer idUsuarioCajero,
        String nroComprobante,
        LocalDateTime fechaVenta,
        BigDecimal totalVenta,
        String estado
) {}