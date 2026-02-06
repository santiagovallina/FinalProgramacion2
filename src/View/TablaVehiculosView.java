package View;

import static AppConfig.AppConfig.PATH_JSON;
import Model.Auto;
import Model.Camion;
import Model.Color;
import Model.Moto;
import Model.Vehiculo;
import View.Botones.BtnActualizar;
import View.Botones.BtnAgregar;
import View.Botones.BtnEliminar;
import View.Botones.BtnLeer;
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
        TableColumn<Vehiculo, String> colTipo = new TableColumn<>("Tipo");
        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        
        TableColumn<Vehiculo, String> colPatente = new TableColumn<>("Patente");
        colPatente.setCellValueFactory(new PropertyValueFactory<>("patente"));

        TableColumn<Vehiculo, Integer> colModelo = new TableColumn<>("Año");
        colModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));

        TableColumn<Vehiculo, String> colMarca = new TableColumn<>("Marca");
        colMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));

        TableColumn<Vehiculo, Color> colColor = new TableColumn<>("Color");
        colColor.setCellValueFactory(new PropertyValueFactory<>("color"));

        TableColumn<Vehiculo, Double> colPrecio = new TableColumn<>("Precio");
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));

        table.getColumns().addAll(colTipo, colPatente, colModelo, colMarca, colColor, colPrecio);
        
        
        data.setAll(gestor.getLista());
        table.setItems(data);
        
        Button btnAgregar = new BtnAgregar(root, gestor);
        Button btnEliminar = new BtnEliminar(table, data, gestor);
        Button btnActualizar = new BtnActualizar(root, table, gestor); 
        Button btnLeer = new BtnLeer(table);
        Button btnVolver = new Button("← Volver");
        btnVolver.setOnAction(e -> { 
            BienvenidaView bienvenida = new BienvenidaView(root, gestor);
            root.setCenter(bienvenida.getView()); 
        });
        
        VBox botonesBox = new VBox(10, btnAgregar, btnEliminar, btnActualizar, btnLeer, btnVolver);
        
        view = new VBox(10, table, botonesBox);
        view.setAlignment(Pos.CENTER);
        
        
    }

    public VBox getView() {
        return view;
    }
    
    
}

