package Vehiculo;

public class Camioneta extends Vehiculo{
    public Camioneta(String marca, String modelo, int anio, double precio, String tipo) {
        super(marca, modelo, anio, precio, "Camioneta");
    }

    @Override
    public String toString1() {
        return super.toString();
    }

}
