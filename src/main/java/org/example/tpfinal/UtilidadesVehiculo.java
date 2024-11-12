package org.example.tpfinal;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
public class UtilidadesVehiculo {

    public static void guardarVehiculosEnArchivo(List<Vehiculo1> vehiculos, String nombreArchivo) {
        JSONArray jsonArray = new JSONArray();
        for (Vehiculo1 vehiculo : vehiculos) {
            JSONObject jsonVehiculo = new JSONObject();
            jsonVehiculo.put("marca", vehiculo.getMarca());
            jsonVehiculo.put("modelo", vehiculo.getModelo());
            jsonVehiculo.put("anio", vehiculo.getAnio());
            jsonVehiculo.put("precio", vehiculo.getPrecio());
            jsonArray.put(jsonVehiculo);
        }
        try (FileWriter archivo = new FileWriter(nombreArchivo)) {
            archivo.write(jsonArray.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Vehiculo1> cargarVehiculosDesdeArchivo(String nombreArchivo) {
        List<Vehiculo1> listaVehiculos = new ArrayList<>();
        try {
            String contenido = new String(Files.readAllBytes(Paths.get(nombreArchivo)));
            JSONArray jsonArray = new JSONArray(contenido);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonVehiculo = jsonArray.getJSONObject(i);
                String marca = jsonVehiculo.getString("marca");
                String modelo = jsonVehiculo.getString("modelo");
                int anio = jsonVehiculo.getInt("anio");
                double precio = jsonVehiculo.getDouble("precio");
                Vehiculo1 vehiculo = new Auto1(marca, modelo, anio, precio); // Ajustar tipo según los datos
                listaVehiculos.add(vehiculo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listaVehiculos;
    }
}
