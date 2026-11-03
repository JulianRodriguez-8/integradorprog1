package modelo;

/**
 * Representa un vehículo de tipo bicicleta perteneciente a la flota de CityLink.
 * Posee una capacidad de carga de 10 kg, una velocidad promedio de 15 km/h,
 * un costo de $50 por kilómetro y no posee tiempo extra. Además, no puede transportar
 * pedidos frágiles por restricciones de seguridad[cite: 1, 2, 3].
 */
public class Bicicleta extends Vehiculo {
 
    /**
     * Crea una bicicleta inicializando sus atributos base mediante la clase padre {@link Vehiculo}
     * con valores predeterminados (patente, 10 kg de capacidad, 15 km/h y $50 por km)[cite: 1, 2, 3].
     * 
     * @param patente patente única de la bicicleta, no puede estar vacía
     */
    public Bicicleta(String patente) {
        super(patente, 10, 15, 50);
    }

    /**
     * Calcula el tiempo estimado de entrega para una bicicleta en minutos.
     * Se obtiene dividiendo la distancia entre la velocidad y multiplicando por 60,
     * sin aplicar ningún tiempo extra[cite: 1, 2, 3].
     * 
     * @param distanciaKm distancia a recorrer en kilómetros
     * @return el tiempo estimado de entrega en minutos
     */
    @Override 
    public double calcularTiempoEntrega(double distanciaKm){
        return distanciaKm / getVelocidadKmH() * 60;
    }

    /**
     * Verifica si la bicicleta puede transportar un pedido determinado.
     * Redefine el método para rechazar explícitamente los pedidos frágiles y 
     * validar que el peso no supere la capacidad máxima de la bicicleta[cite: 2, 3].
     * 
     * @param pedido pedido que se quiere evaluar para el transporte
     * @return {@code true} si el pedido no es frágil y cumple con la capacidad de peso, {@code false} en caso contrario
     */
    @Override 
    public boolean puedeTransportar(Pedido pedido) {
        return !pedido.esFragil() && super.puedeTransportar(pedido);
    }
    
}