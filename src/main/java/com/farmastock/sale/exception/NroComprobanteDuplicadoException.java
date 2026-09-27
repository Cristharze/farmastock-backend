package com.farmastock.sale.exception;

public class NroComprobanteDuplicadoException extends RuntimeException {
    public NroComprobanteDuplicadoException(String nroComprobante) {
        super("Ya existe una venta registrada con el número de comprobante: " + nroComprobante);
    }
}
