package Vehiculo;

public class Auto extends Vehiculo{
    public Auto(String marca, String modelo, int anio, double precio, String tipo) {
        super(marca, modelo, anio, precio, "Auto");
    }

    @Override
    public String toString1() {
        return super.toString();
    }


}
