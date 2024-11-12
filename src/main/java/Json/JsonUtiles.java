package Json;

import Vehiculo.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonUtiles {

    //Metodo para guardar los vehiculos en un archivo JSON
    public static void guardarVehiculosEnArchivo(List<Vehiculo> vehiculos, String nombreArchivo) {
        JSONArray vehiculosJson = new JSONArray();

        // Convertimos cada vehículo en un JSONObject y lo agregamos al JSONArray
        for (Vehiculo vehiculo : vehiculos) {
            JSONObject vehiculoJson = new JSONObject();
            vehiculoJson.put("id", vehiculo.getId());
            vehiculoJson.put("marca", vehiculo.getMarca());
            vehiculoJson.put("modelo", vehiculo.getModelo());
            vehiculoJson.put("anio", vehiculo.getAnio());
            vehiculoJson.put("precio", vehiculo.getPrecio());
            vehiculoJson.put("tipoVehiculo", vehiculo.getTipoVehiculo());
            vehiculosJson.put(vehiculoJson);
        }

        // Guardar el JSONArray en un archivo
        try (FileWriter file = new FileWriter(nombreArchivo)) {
            file.write(vehiculosJson.toString(4)); // Escribir con una indentación de 4 espacios
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Vehiculo> cargarVehiculosDesdeArchivo(String nombreArchivo) {
        List<Vehiculo> vehiculos = new ArrayList<>(); // Utilizamos esta coleccion ya que podemos tener mas de un auto igual.

        try (FileReader reader = new FileReader(nombreArchivo)) {
            JSONArray vehiculosJson = new JSONArray(new JSONTokener(reader));

            for (int i = 0; i < vehiculosJson.length(); i++) {
                JSONObject vehiculoJson = vehiculosJson.getJSONObject(i);
                String tipoVehiculo = vehiculoJson.getString("tipoVehiculo");
                String marca = vehiculoJson.getString("marca");
                String modelo = vehiculoJson.getString("modelo");
                int anio = vehiculoJson.getInt("anio");
                double precio = vehiculoJson.getDouble("precio");

                Vehiculo vehiculo = null;
                switch (tipoVehiculo) {
                    case "Auto":
                        vehiculo = new Auto(marca, modelo, anio, precio, "Auto");
                        break;
                    case "Moto":
                        vehiculo = new Moto(marca, modelo, anio, precio, "Moto");
                        break;
                    case "Camioneta":
                        vehiculo = new Camioneta(marca, modelo, anio, precio, "Camioneta");
                        break;
                }


                if (vehiculo != null) {
                    vehiculos.add(vehiculo);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return vehiculos;
    }
}
