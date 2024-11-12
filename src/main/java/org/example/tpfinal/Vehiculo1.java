package org.example.tpfinal;

public abstract class Vehiculo1 {
    private String marca;
    private String modelo;
    private int anio;
    private double precio;

    public Vehiculo1(String marca, String modelo, int anio, double precio) {
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
        this.precio = precio;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public int getAnio() {
        return anio;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return marca + " " + modelo + " - Año: " + anio + " - Precio: $" + precio;
    }
}
