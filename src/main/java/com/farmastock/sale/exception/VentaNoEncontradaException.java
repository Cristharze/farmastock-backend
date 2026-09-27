package com.farmastock.sale.exception;

public class VentaNoEncontradaException extends RuntimeException {
    public VentaNoEncontradaException(Integer id) {
        super("No existe la venta con el ID: " + id);
    }
}