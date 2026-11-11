package modelo;

/**
 * Representa una camioneta dentro de la flota de vehículos del sistema CityLink.
 * Hereda de la clase abstracta {@link Vehiculo} e implementa el cálculo específico
 * del tiempo de entrega, considerando una demora fija adicional por carga y descarga.
 * 
 * @version 1.0
 * @author Grupo CityLink
 */
public class Camioneta extends Vehiculo {
    
    /** Minutos adicionales fijos que demora la camioneta por tareas de carga y descarga. */
    public static final double MINUTOS_EXTRA = 10;

    /**
     * Constructor para inicializar una nueva camioneta con su respectiva patente.
     * Configura por defecto los valores base de la clase padre:
     * capacidad de 500 kg, velocidad de 30 km/h y un costo por kilómetro de 300.
     * 
     * @param patente código de identificación o patente de la camioneta
     */
    public Camioneta(String patente) {
        super(patente, 500, 30, 300);
    }

    /**
     * Calcula el tiempo estimado de entrega en minutos para una distancia dada,
     * sumando el tiempo de viaje según la velocidad de la camioneta y los minutos extra de carga y descarga.
     * 
     * @param distanciaKm distancia en kilómetros hasta el destino del pedido
     * @return tiempo total estimado en minutos
     */
    @Override
    public double calcularTiempoEntrega(double distanciaKm) {
        return distanciaKm / getVelocidadKmH() * 60 + MINUTOS_EXTRA;
    }
    
}