package modelo;

public class Bicicleta extends Vehiculo {
 
    public Bicicleta(String patente) {
        super(patente, 10,15,50);
        
       
    }

    @Override 
    public double calcularTiempoEntrega(double distanciaKm){
        return distanciaKm / getVelocidadKmH()*60;
        
    }

  

   
    
}
