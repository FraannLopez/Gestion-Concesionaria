package Persona;

public class Persona {
    private static int idContador = 1;
    private int id;
    private String nombre;
    private String apellido;
    private String dni;

    public Persona(String nombre, String apellido, String dni) {
        this.id = idContador++;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getDni() {
        return dni;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "id = " + id +
                ", nombre = '" + nombre + '\'' +
                ", apellido = '" + apellido + '\'' +
                ", dni = '" + dni + '\'' +
                '}';
    }
}
