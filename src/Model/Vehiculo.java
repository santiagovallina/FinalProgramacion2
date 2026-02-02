package Model;

import com.google.gson.JsonObject;
import java.io.Serializable;
import java.time.LocalDate;
import service.CSVSerializable;

public abstract class Vehiculo implements Comparable<Vehiculo>, CSVSerializable, Serializable{
    
    private String patente;
    private int modelo;
    private String marca;
    private Color color;
    private double precio;

    public Vehiculo(String patente, int modelo, String marca, Color color, double precio) {
        this.patente = patente;
        this.modelo = modelo;
        this.marca = marca;
        this.color = color;
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Vehiculo{" + "patente=" + patente + ", modelo=" + modelo + ", marca=" + marca + ", color=" + color + ", precio=" + precio + '}';
    }
    
    
    public void encender(){
        System.out.println("Encender vehiculo");
    }
    
    public void acelerar(){
        System.out.println("Acelerar vehiculo");
    }
    
    public void frenar(){
        System.out.println("Frenar vehiculo");
    }

    public String getPatente() {
        return patente;
    }

    public int getModelo() {
        return modelo;
    }

    public String getMarca() {
        return marca;
    }

    public Color getColor() {
        return color;
    }

    public double getPrecio() {
        return precio;
    }

    

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    
    @Override
    public int compareTo(Vehiculo otro) {
        return this.patente.compareToIgnoreCase(otro.patente);
    }
    
    @Override
    public String toCSV(){
        return patente + ", " + modelo + ", " + marca + ", " + color.toString() + ", " +precio;
    }
    
    @Override
    public String toHeaderCSV() {
        return "patente,modelo,marca,color,precio";
    }
    
    public static Vehiculo fromCSV(String linea) {
    String[] values = linea.split(",");
    for (int i = 0; i < values.length; i++) {
        values[i] = values[i].trim(); // limpia espacios extra
    }
    String tipo = values[0];

        switch (tipo.toUpperCase()) {
            case "AUTO":
                return new Auto(
                    values[1], // patente
                    Integer.parseInt(values[2]), // modelo
                    values[3], // marca
                    Color.valueOf(values[4]), // color
                    Double.parseDouble(values[5]) // precio
                );
               
            case "MOTO":
                return new Moto(
                    values[1],
                    Integer.parseInt(values[2]),
                    values[3],
                    Color.valueOf(values[4].toUpperCase()),
                    Double.parseDouble(values[5])
                );

            case "CAMION":
                return new Camion(
                    values[1],
                    Integer.parseInt(values[2]),
                    values[3],
                    Color.valueOf(values[4].toUpperCase()),
                    Double.parseDouble(values[5])
                );

            default:
                throw new IllegalArgumentException("Tipo de vehículo desconocido: " + tipo);
        }
    }
    
    public static Vehiculo fromJSON(JsonObject json) {
        if (!json.has("tipo")) {
            throw new IllegalArgumentException("El JSON no contiene campo 'tipo': " + json);
        }
        String tipo = json.get("tipo").getAsString();

        switch (tipo.toUpperCase()) {
            case "AUTO":
                return new Auto(
                    json.get("patente").getAsString(),
                    json.get("modelo").getAsInt(),
                    json.get("marca").getAsString(),
                    Color.valueOf(json.get("color").getAsString().toUpperCase()),
                    json.get("precio").getAsDouble()
                );

            case "MOTO":
                return new Moto(
                    json.get("patente").getAsString(),
                    json.get("modelo").getAsInt(),
                    json.get("marca").getAsString(),
                    Color.valueOf(json.get("color").getAsString().toUpperCase()),
                    json.get("precio").getAsDouble()
                );

            case "CAMION":
                return new Camion(
                    json.get("patente").getAsString(),
                    json.get("modelo").getAsInt(),
                    json.get("marca").getAsString(),
                    Color.valueOf(json.get("color").getAsString().toUpperCase()),
                    json.get("precio").getAsDouble()
                );

            default:
                throw new IllegalArgumentException("Tipo de vehículo desconocido: " + tipo);
        }
    }

 
    

 
}
