package com.farmastock.sale.application;

import com.farmastock.sale.web.response.VentaDemoResponse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class VentaDemoService {

    public VentaDemoResponse obtenerDemo() {
        return new VentaDemoResponse(
                1L,
                "F001-00000001",
                LocalDateTime.now(),
                new BigDecimal("76.00"),
                "CONFIRMADA"
        );
    }
}