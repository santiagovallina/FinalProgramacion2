package View;

import static AppConfig.AppConfig.PATH_JSON;
import Model.Auto;
import Model.Camion;
import Model.Color;
import Model.Moto;
import Model.Vehiculo;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import service.GestorVehiculos;

public class FormularioAgregarView {
    private VBox view;
    
    public FormularioAgregarView(BorderPane root, GestorVehiculos<Vehiculo> gestor) {
        Label lblTitulo = new Label("Agregar nuevo vehículo");

        // Selector de tipo
        Label lblTipo = new Label("Tipo de vehiculo");
        ComboBox<String> cmbTipo = new ComboBox<>();
        cmbTipo.getItems().addAll("Auto", "Moto", "Camión");
        cmbTipo.setPromptText("Seleccione el tipo de vehículo");

        Label lblPatente = new Label("Patente");
        TextField txtPatente = new TextField();
        txtPatente.setPromptText("Patente");

        Label lblModelo = new Label("Modelo");
        TextField txtModelo = new TextField();
        txtModelo.setPromptText("Modelo");
        
        Label lblMarca = new Label("Marca");
        TextField txtMarca = new TextField();
        txtMarca.setPromptText("Marca");

        Label lblColor = new Label("Color");
        ComboBox<Color> cmbColor = new ComboBox<>();
        cmbColor.getItems().addAll(Color.values());
        cmbColor.setPromptText("Seleccione el color del vehículo");
        
        Label lblPrecio = new Label("Precio");
        TextField txtPrecio = new TextField();
        txtPrecio.setPromptText("Precio");
        
        Button btnAgregar = new Button("Agregar");
        btnAgregar.setOnAction(e -> {  
            String patente = txtPatente.getText();
            int modelo = Integer.parseInt(txtModelo.getText());
            String marca = txtMarca.getText();
            Color color = cmbColor.getValue();
            double precio = Double.parseDouble(txtPrecio.getText());
            
            Vehiculo nuevo = null;
            switch (cmbTipo.getValue()) { 
                case "Auto":
                    nuevo = new Auto(patente, modelo, marca, color, precio, 5, 1.6);
                    break;
                case "Moto":
                    nuevo = new Moto(patente, modelo, marca, color, precio, 500, 200);
                    break;
                case "Camión":
                    nuevo = new Camion(patente, modelo, marca, color, precio, 10000);
                    break;
            }
            
            if (nuevo != null) { 
                gestor.getLista();
                gestor.agregar(nuevo);
                gestor.guardarEnJSON(PATH_JSON);
                root.setCenter(new TablaVehiculosView(root, gestor).getView());
            }
            
        });

        
        Button btnVolver = new Button("← Volver");
        btnVolver.setOnAction(e -> { 
            TablaVehiculosView tabla = new TablaVehiculosView(root, gestor);
            root.setCenter(tabla.getView()); 
        });

        view = new VBox(10, lblTitulo,
                lblTipo, cmbTipo,
                lblPatente, txtPatente,
                lblModelo, txtModelo,
                lblMarca, txtMarca,
                lblColor, cmbColor,
                lblPrecio, txtPrecio,
                btnAgregar,
                btnVolver);
    }

    public VBox getView() {
        return view;
    }
    
}
