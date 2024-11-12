package org.example.tpfinal;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.Optional;

public class Dialogo {
    private ObservableList<Vehiculo1> listaVehiculos = FXCollections.observableArrayList();

    public void mostrarVentanaPrincipal(Stage stage) {
        Button btnAgregarVehiculo = new Button("Agregar Vehículo");
        btnAgregarVehiculo.setOnAction(e -> mostrarDialogoTipoVehiculo());

        Button btnEliminarVehiculo = new Button("Eliminar Vehículo");
        btnEliminarVehiculo.setOnAction(e -> mostrarDialogoEliminarVehiculo());

        VBox layout = new VBox(10, btnAgregarVehiculo, btnEliminarVehiculo);
        Scene scene = new Scene(layout, 400, 300);

        stage.setScene(scene);
        stage.setTitle("Gestión de Vehículos");
        stage.show();
    }

    private void mostrarDialogoTipoVehiculo() {
        ChoiceDialog<String> dialogoTipo = new ChoiceDialog<>("Auto", "Auto", "Moto", "Camioneta");
        dialogoTipo.setTitle("Seleccionar Tipo de Vehículo");
        dialogoTipo.setHeaderText("Agregar un nuevo vehículo");
        dialogoTipo.setContentText("Selecciona el tipo de vehículo:");

        Optional<String> resultado = dialogoTipo.showAndWait();
        resultado.ifPresent(tipo -> mostrarDialogoAgregarVehiculo(tipo));
    }

    //Método mostrarDialogoAgregarVehiculo
    //Este método toma el tipo de vehículo como parámetro y muestra un formulario de entrada de datos para ingresar los detalles del vehículo. Luego, agrega el vehículo creado a la lista de listaVehiculos y guarda la lista en un archivo JSON.

      //      java
    //Copiar código
    private void mostrarDialogoAgregarVehiculo(String tipo) {
        // Crear campos de entrada
        TextField campoMarca = new TextField();
        campoMarca.setPromptText("Marca");
        TextField campoModelo = new TextField();
        campoModelo.setPromptText("Modelo");
        TextField campoAnio = new TextField();
        campoAnio.setPromptText("Año");
        TextField campoPrecio = new TextField();
        campoPrecio.setPromptText("Precio");

        // Configurar el diálogo
        Dialog<ButtonType> dialogo = new Dialog<>();
        dialogo.setTitle("Agregar " + tipo);
        dialogo.setHeaderText("Ingrese los detalles del " + tipo);

        // Crear el layout
        VBox layout = new VBox(10, new Label("Marca:"), campoMarca, new Label("Modelo:"), campoModelo,
                new Label("Año:"), campoAnio, new Label("Precio:"), campoPrecio);
        dialogo.getDialogPane().setContent(layout);

        // Botones de aceptar y cancelar
        dialogo.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        // Manejar el resultado del diálogo
        dialogo.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                try {
                    String marca = campoMarca.getText();
                    String modelo = campoModelo.getText();
                    int anio = Integer.parseInt(campoAnio.getText());
                    double precio = Double.parseDouble(campoPrecio.getText());

                    Vehiculo1 vehiculo1;
                    switch (tipo) {
                        case "Auto":
                            vehiculo1 = new Auto1(marca, modelo, anio, precio);
                            break;
                        case "Moto":
                            vehiculo1 = new Moto1(marca, modelo, anio, precio);
                            break;
                        case "Camioneta":
                            vehiculo1 = new Camioneta1(marca, modelo, anio, precio);
                            break;
                        default:
                            mostrarAlerta("Error", "Tipo de vehículo desconocido");
                            return;
                    }

                    // Agregar el vehículo a la lista y guardar en JSON
                    listaVehiculos.add(vehiculo1);
                    UtilidadesVehiculo.guardarVehiculosEnArchivo(listaVehiculos, "vehiculos.json");

                } catch (NumberFormatException e) {
                    mostrarAlerta("Error", "Por favor, ingrese un año y precio válidos.");
                }
            }
        });
    }

    private void mostrarAlerta(String titulo, String mensaje) {
    Alert alerta = new Alert(Alert.AlertType.ERROR);
    alerta.setTitle(titulo);
    alerta.setContentText(mensaje);
    alerta.showAndWait();
    }


    private void mostrarDialogoEliminarVehiculo() {
        ChoiceDialog<Vehiculo1> dialog = new ChoiceDialog<>(null, listaVehiculos);
        dialog.setTitle("Eliminar Vehículo");
        dialog.setHeaderText("Selecciona el vehículo a eliminar");
        dialog.setContentText("Vehículo:");

        Optional<Vehiculo1> result = dialog.showAndWait();
        result.ifPresent(vehiculo -> eliminarVehiculo(vehiculo));
    }

    private void eliminarVehiculo(Vehiculo1 vehiculo) {
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle("Confirmación de eliminación");
        alerta.setHeaderText("¿Estás seguro de eliminar este vehículo?");
        alerta.setContentText("Vehículo: " + vehiculo);

        Optional<ButtonType> resultado = alerta.showAndWait();
        if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
            listaVehiculos.remove(vehiculo);
            UtilidadesVehiculo.guardarVehiculosEnArchivo(listaVehiculos, "vehiculos.json");
        }
    }

}

