package Model;

import java.io.Serializable;
import service.CSVSerializable;

public class Moto extends Vehiculo {
    
    private int cilindrada;
    private TipoMoto tipomoto;

    public Moto(String patente, int modelo, String marca, Color color, double precio,  int cilindrada, TipoMoto tipomoto) {
        super(patente, modelo, marca, color, precio);
        this.cilindrada = cilindrada;
        this.tipomoto = tipomoto;
    }
    
    public Moto(String patente, int modelo, String marca,  Color color, double precio, int cilindrada) {
        super(patente, modelo, marca, color, precio);
        this.cilindrada = cilindrada;
    }
    
    public Moto(String patente, int modelo, String marca,  Color color, double precio) {
        super(patente, modelo, marca, color, precio);
    }

    @Override
    public String toString() {
        return super.toString() + "Moto{" + "cilindrada=" + cilindrada + ", tipo=" + tipomoto + '}';
    }

    public int getCilindrada() {
        return cilindrada;
    }

    public TipoMoto getTipoMoto() {
        return tipomoto;
    }
    
    @Override
    public void encender(){
        System.out.println("Encendiendo la moto");
    }
    
    @Override
    public void acelerar(){
        System.out.println("Acelerando la moto");
    }
    
    @Override
    public void frenar(){
        System.out.println("Frenando la moto");
    }

    
    
    @Override
    public String toHeaderCSV() {
        return "patente,modelo,marca,color,precio,cilindrada,tipo";
    }
    
    
    
    
}
