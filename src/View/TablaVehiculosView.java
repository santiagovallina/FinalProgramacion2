package View;

import static AppConfig.AppConfig.PATH_JSON;
import Model.Auto;
import Model.Camion;
import Model.Color;
import Model.Moto;
import Model.Vehiculo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import service.GestorVehiculos;
import finalprogramacionii.Test;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

public class TablaVehiculosView {
    private VBox view;
    private TableView<Vehiculo> table;
    private ObservableList<Vehiculo> data;
    private GestorVehiculos<Vehiculo> gestor;
    
    private VBox formularioContainer;
    

    public TablaVehiculosView(BorderPane root, GestorVehiculos<Vehiculo> gestor) {
        
        table = new TableView<>();
        data = FXCollections.observableArrayList();
        

        // Columnas
        TableColumn<Vehiculo, String> colPatente = new TableColumn<>("Patente");
        colPatente.setCellValueFactory(new PropertyValueFactory<>("patente"));

        TableColumn<Vehiculo, Integer> colModelo = new TableColumn<>("Modelo");
        colModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));

        TableColumn<Vehiculo, String> colMarca = new TableColumn<>("Marca");
        colMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));

        TableColumn<Vehiculo, Color> colColor = new TableColumn<>("Color");
        colColor.setCellValueFactory(new PropertyValueFactory<>("color"));

        TableColumn<Vehiculo, Double> colPrecio = new TableColumn<>("Precio");
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));

        table.getColumns().addAll(colPatente, colModelo, colMarca, colColor, colPrecio);
        
        
        data.setAll(gestor.getLista());
        table.setItems(data);
        
        Button btnAgregar = new Button("Agregar vehículo");
        Button btnEliminar = new Button("Eliminar seleccionado"); 
        Button btnActualizar = new Button("Actualizar seleccionado"); 
        Button btnLeer = new Button("Ver seleccionado");
        
        Button btnVolver = new Button("← Volver");
        btnVolver.setOnAction(e -> { 
            BienvenidaView bienvenida = new BienvenidaView(root, gestor);
            root.setCenter(bienvenida.getView()); 
        });
        
        
        btnAgregar.setOnAction(e -> { 
            FormularioAgregarView form = new FormularioAgregarView(root, gestor);
            root.setCenter(form.getView()); 
        });
        
        
        btnEliminar.setOnAction(e -> {
            Vehiculo seleccionado = table.getSelectionModel().getSelectedItem();
            
            if (seleccionado != null) {
                Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
                confirmacion.setTitle("Confirmar eliminación");
                confirmacion.setHeaderText("¿Está seguro que desea eliminar este vehículo?");
                confirmacion.setContentText(
                    "Patente: " + seleccionado.getPatente() + "\n" +
                    "Marca: " + seleccionado.getMarca() + "\n" +
                    "Modelo: " + seleccionado.getModelo()
                );
                
                Optional<ButtonType> resultado = confirmacion.showAndWait();
                
                if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
                    gestor.eliminarPorPatente(seleccionado.getPatente());
                    gestor.guardarEnJSON(PATH_JSON);
                    data.setAll(gestor.getLista());
                }
                
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Selección requerida");
                alert.setHeaderText("No hay vehículo seleccionado");
                alert.setContentText("Seleccione un vehículo de la tabla antes de eliminar");
                alert.showAndWait();
            }
        });
        
        btnLeer.setOnAction(e -> {
            Vehiculo seleccionado = table.getSelectionModel().getSelectedItem();
            
            if (seleccionado != null) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Detalle del vehículo");
                alert.setHeaderText("Información completa");
                
                // Mostrar todos los detalles
                String detalles = seleccionado.toString();
                
                // O si quieres un formato más bonito:
                String detallesFormateados = String.format(
                    "Tipo: %s\n" +
                    "Patente: %s\n" +
                    "Modelo: %d\n" +
                    "Marca: %s\n" +
                    "Color: %s\n" +
                    "Precio: $%.2f",
                    seleccionado.getClass().getSimpleName(),
                    seleccionado.getPatente(),
                    seleccionado.getModelo(),
                    seleccionado.getMarca(),
                    seleccionado.getColor(),
                    seleccionado.getPrecio()
                );
                
                alert.setContentText(detallesFormateados);
                alert.showAndWait();
                
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Selección requerida");
                alert.setHeaderText("No hay vehículo seleccionado");
                alert.setContentText("Por favor, seleccione un vehículo de la tabla");
                alert.showAndWait();
            }
        });
        
        btnActualizar.setOnAction(e -> {
            Vehiculo seleccionado = table.getSelectionModel().getSelectedItem();

            if (seleccionado != null) {
                FormularioActualizarView formActualizar = new FormularioActualizarView(root, gestor, seleccionado);
                root.setCenter(formActualizar.getView()); // root es tu BorderPane principal
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Selección requerida");
                alert.setHeaderText("No hay vehículo seleccionado");
                alert.setContentText("Por favor, seleccione un vehículo de la tabla");
                alert.showAndWait();
            }
        });

        
        VBox botonesBox = new VBox(10, btnAgregar, btnEliminar, btnActualizar, btnLeer, btnVolver);
        
        view = new VBox(10, table, botonesBox);
        view.setAlignment(Pos.CENTER);

    }

    public VBox getView() {
        return view;
    }
    
    
}

