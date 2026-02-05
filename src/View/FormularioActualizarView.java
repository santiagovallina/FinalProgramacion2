package View;

import static AppConfig.AppConfig.PATH_JSON;
import Model.*;
import service.GestorVehiculos;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class FormularioActualizarView {

    private VBox view;
    private GestorVehiculos<Vehiculo> gestor;
    private Vehiculo vehiculo;

    public FormularioActualizarView(BorderPane root, GestorVehiculos<Vehiculo> gestor, Vehiculo actualizado) {
        this.gestor = gestor;
        this.vehiculo = actualizado;
        construirFormulario(root);
    }

    private void construirFormulario(BorderPane root) {
        
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setHgap(10);
        grid.setVgap(10);

        // Campos prellenados
        TextField txtPatente = new TextField(vehiculo.getPatente());
        txtPatente.setDisable(true); // la patente no debería cambiar

        TextField txtMarca = new TextField(vehiculo.getMarca());
        TextField txtModelo = new TextField(String.valueOf(vehiculo.getModelo()));
        TextField txtColor = new TextField(vehiculo.getColor().toString());
        TextField txtPrecio = new TextField(String.valueOf(vehiculo.getPrecio()));

        Button btnGuardar = new Button("Guardar cambios");
        btnGuardar.setOnAction(e -> {
            try {
                String marca = txtMarca.getText();
                int modelo = Integer.parseInt(txtModelo.getText());
                String color = txtColor.getText();
                double precio = Double.parseDouble(txtPrecio.getText());

                Vehiculo actualizado = null;

                if (vehiculo instanceof Auto) {
                    actualizado = new Auto(vehiculo.getPatente(), modelo, marca, vehiculo.getColor(), precio);
                } else if (vehiculo instanceof Moto) {
                    actualizado = new Moto(vehiculo.getPatente(), modelo, marca, vehiculo.getColor(), precio);
                } else if (vehiculo instanceof Camion) {
                    actualizado = new Camion(vehiculo.getPatente(), modelo, marca, vehiculo.getColor(), precio);
                }

                if (actualizado != null) {
                    gestor.actualizar(vehiculo.getPatente(), actualizado);
                    // 🔹 persistir cambios en JSON 
                    gestor.guardarEnJSON(PATH_JSON);
                    // 🔹 volver a la tabla 
                    
                    root.setCenter(new TablaVehiculosView(root, gestor).getView()); 
                }

            } catch (NumberFormatException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Modelo y precio deben ser números válidos");
                alert.showAndWait();
            }
        });

        // Añadir al grid
        grid.add(new Label("Patente:"), 0, 0);
        grid.add(txtPatente, 1, 0);
        grid.add(new Label("Marca:"), 0, 1);
        grid.add(txtMarca, 1, 1);
        grid.add(new Label("Modelo:"), 0, 2);
        grid.add(txtModelo, 1, 2);
        grid.add(new Label("Color:"), 0, 3);
        grid.add(txtColor, 1, 3);
        grid.add(new Label("Precio:"), 0, 4);
        grid.add(txtPrecio, 1, 4);
        grid.add(btnGuardar, 1, 5);

        view = new VBox(grid);
    }

    public VBox getView() {
        return view;
    }
}
