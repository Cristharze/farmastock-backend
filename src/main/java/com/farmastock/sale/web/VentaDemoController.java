package com.farmastock.sale.web;

import com.farmastock.sale.application.VentaDemoService;
import com.farmastock.sale.web.response.VentaDemoResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ventas")
public class VentaDemoController {

    private final VentaDemoService service;

    public VentaDemoController(VentaDemoService service) {
        this.service = service;
    }

    @GetMapping("/demo")
    public VentaDemoResponse demo() {
        return service.obtenerDemo();
    }
}