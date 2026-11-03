package modelo;

/**
 * Representa un vehículo de tipo moto perteneciente a la flota de CityLink.
 * Posee una capacidad de carga de 30 kg, una velocidad promedio de 40 km/h,
 * un costo de $120 por kilómetro y un tiempo extra de 5 minutos por estacionamiento[cite: 1, 2].
 */
public class Moto extends Vehiculo {
    
    /** Tiempo extra en minutos agregado al tiempo de entrega por concepto de estacionamiento[cite: 2]. */
    public static final double MINUTOS_EXTRA = 5;

    /**
     * Crea una moto inicializando sus atributos base mediante la clase padre {@link Vehiculo}
     * con valores predeterminados (patente, 30 kg de capacidad, 40 km/h y $120 por km)[cite: 1, 2].
     * 
     * @param patente patente única de la moto, no puede estar vacía
     */
    public Moto(String patente) {
        super(patente, 30, 40, 120);
    }

    /**
     * Calcula el tiempo estimado de entrega para una moto en minutos.
     * Se obtiene dividiendo la distancia entre la velocidad, multiplicando por 60 
     * y sumando los 5 minutos extra correspondientes al estacionamiento[cite: 1, 2].
     * 
     * @param distanciaKm distancia a recorrer en kilómetros
     * @return el tiempo estimado de entrega en minutos
     */
    @Override
    public double calcularTiempoEntrega(double distanciaKm){
        return distanciaKm / getVelocidadKmH() * 60 + MINUTOS_EXTRA;
    }
    
}