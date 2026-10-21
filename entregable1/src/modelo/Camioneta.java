package modelo;

public class Camioneta extends Vehiculo {
    
    public Camioneta(String patente) {
        super(patente, 500, 30, 300);
        
    }

    public static final double MINUTOS_EXTRA = 10;

    @Override
    public double calcularTiempoEntrega(double distanciaKm){
        return distanciaKm / getVelocidadKmH()*60+MINUTOS_EXTRA;
    }
    
}
