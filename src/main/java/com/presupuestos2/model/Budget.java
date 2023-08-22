package com.presupuestos2.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Arrays;

public class Budget implements Serializable {

    private String cliente, total, fecha;
    private String[] trabajos, detalles;

    public Budget(String cliente, String fecha, String total, String[] trabajos, String[] detalles) {
        this.cliente = cliente;
        this.fecha = fecha;
        this.total = total;
        this.trabajos = trabajos;
        this.detalles = detalles;
    }

    public String getCliente() {
        return cliente;
    }

    public String getFecha() {
        return fecha;
    }

    public String getTotal() {
        return total;
    }

    public String[] getTrabajos() {
        return trabajos;
    }

    public String[] getDetalles() {
        return detalles;
    }

    @Override
    public String toString() {
        return "Budget{" +
                "cliente='" + cliente + '\'' +
                ", fecha='" + fecha + '\'' +
                ", total='" + total + '\'' +
                ", trabajos=" + Arrays.toString(trabajos) +
                ", detalles=" + Arrays.toString(detalles) +
                '}';
    }
}
