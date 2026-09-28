package com.farmastock.sale.infrastructure.adapter.in.web.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

public record CrearVentaRequest(
        @NotNull(message = "El ID del cajero es obligatorio")
        Integer idUsuarioCajero,

        @NotBlank(message = "El número de comprobante es obligatorio")
        @Size(min = 3, max = 20, message = "El número de comprobante debe tener entre 3 y 20 caracteres")
        String nroComprobante,

        @NotNull(message = "El total de la venta es obligatorio")
        @DecimalMin(value = "0.01", message = "El total debe ser mayor a 0")
        BigDecimal totalVenta
) {}