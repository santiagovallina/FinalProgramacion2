package View;

import Model.Vehiculo;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import service.GestorVehiculos;

public class BienvenidaView {
    
    private VBox view;
    
    public BienvenidaView (BorderPane root, GestorVehiculos<Vehiculo> gestor){
    
        Label lblBienvenida = new Label("Bienvenido al sistema de gestión de vehículos");
        
        Button btnVerLista = new Button("Ver lista de vehículos");
        Button btnAgregar = new Button("Agregar vehículo");
        Button btnEliminar = new Button("Eliminar por patente");
        Button btnBuscar = new Button("Buscar por patente");

        view = new VBox(20, lblBienvenida, btnVerLista, btnAgregar, btnEliminar, btnBuscar);
        view.setAlignment(Pos.CENTER);


        // Acciones de los botones
        btnVerLista.setOnAction(e -> root.setCenter(new TablaVehiculosView(root , gestor).getView()));
        
    }
    
    public VBox getView() { 
        return view; 
    }
}
