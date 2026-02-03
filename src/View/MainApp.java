package View;

import static AppConfig.AppConfig.PATH_JSON;
import Model.Auto;
import Model.Camion;
import Model.Color;
import Model.Moto;
import Model.Vehiculo;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import service.GestorVehiculos;

public class MainApp extends Application {
    
    @Override
    public void start(Stage stage) {
        
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.setTitle("Gestión de Vehículos");
        
        GestorVehiculos<Vehiculo> gestor = new GestorVehiculos<>();
        
        BienvenidaView bienvenida = new BienvenidaView(root, gestor);
        root.setCenter(bienvenida.getView());
        
        
        
        Vehiculo v = new Auto("ZAE321", 2020, "Chevrolet", Color.AZUL, 6000000);
        Vehiculo v1 = new Auto("ABC123", 2020, "Toyota", Color.ROJO, 5000000);
        Vehiculo v2 = new Auto("DEF456", 2019, "Ford", Color.AZUL, 4500000);
        Vehiculo v3 = new Auto("GHI789", 2021, "Fiat", Color.BLANCO, 3800000);
        Vehiculo c1 = new Camion("PLD789", 2021, "Scania", Color.VERDE, 7800000);
        Vehiculo m1 = new Moto("RTE123", 2021, "Honda", Color.VERDE, 1800000);
        
        gestor.agregar(v);
        gestor.agregar(v1);
        gestor.agregar(v2);
        gestor.agregar(v3);
        gestor.agregar(m1);
        gestor.agregar(c1);
        
        
        
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

