package com.farmastock.sale.web.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record VentaDemoResponse(
        Long id,
        String nroComprobante,
        LocalDateTime fechaVenta,
        BigDecimal totalVenta,
        String estado
) {}