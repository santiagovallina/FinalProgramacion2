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
        
        
        data.setAll(gestor.cargarDesdeJSON(PATH_JSON));
        table.setItems(data);
        
        Button btnAgregar = new Button("Agregar vehículo");
        Button btnEliminar = new Button("Eliminar seleccionado"); 
        Button btnActualizar = new Button("Actualizar seleccionado"); 
        Button btnLeer = new Button("Leer seleccionado");
        
        Button btnVolver = new Button("← Volver");
        btnVolver.setOnAction(e -> { 
            BienvenidaView bienvenida = new BienvenidaView(root, gestor);
            root.setCenter(bienvenida.getView()); 
        });
        
        
        btnAgregar.setOnAction(e -> { 
            FormularioAgregarView form = new FormularioAgregarView(root, gestor);
            root.setCenter(form.getView()); 
        });
        
        VBox botonesBox = new VBox(10, btnAgregar, btnEliminar, btnActualizar, btnLeer, btnVolver);
        
        view = new VBox(10, table, botonesBox);
        view.setAlignment(Pos.CENTER);
       
    }

    public VBox getView() {
        return view;
    }
    
    
}

