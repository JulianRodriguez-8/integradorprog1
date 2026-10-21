package modelo;

public class Moto extends Vehiculo {
  

    public Moto(String patente) {
        super(patente, 30, 40, 120);
        
    }

    public static final double MINUTOS_EXTRA = 5;

    @Override
    public double calcularTiempoEntrega(double distanciaKm){
        return distanciaKm / getVelocidadKmH()*60+MINUTOS_EXTRA;
    }

   
    
}
