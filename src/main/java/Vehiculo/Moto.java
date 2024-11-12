package Vehiculo;

public class Moto extends Vehiculo {
    public Moto(String marca, String modelo, int anio, double precio, String tipo) {
        super(marca, modelo, anio, precio, "Moto");
    }

    @Override
    public String toString1() {
        return super.toString();
    }


}
