package View.Botones;

import View.FormularioAgregarView;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import service.GestorVehiculos;

/**
 * Botón personalizado para agregar un nuevo vehículo
 */
public class BtnAgregar extends Button {
    
    private BorderPane root;
    private GestorVehiculos gestor;
    
    public BtnAgregar(BorderPane root, GestorVehiculos gestor) {
        super("➕ Agregar vehículo");
        
        this.root = root;
        this.gestor = gestor;
        
        // Estilo del botón
        this.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white; -fx-font-weight: bold;");
        
        configurarAccion();
    }
    
    /**
     * Configura la acción del botón
     */
    private void configurarAccion() {
        this.setOnAction(e -> {
            FormularioAgregarView formulario = new FormularioAgregarView(root, gestor);
            root.setCenter(formulario.getView());
        });
    }
}
    

