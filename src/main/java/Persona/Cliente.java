package Persona;

public class Cliente extends Persona {
    private String direccion;
    private String mail;

    public Cliente(String nombre, String apellido, String dni, String direccion, String mail) {
        super(nombre, apellido, dni);
        this.direccion = direccion;
        this.mail = mail;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "nombre=" + getNombre() +
                ", apellido=" + getApellido() +
                ", dni=" + getDni() +
                ", direccion='" + direccion + '\'' +
                ", mail='" + mail + '\'' +
                '}';
    }

}
