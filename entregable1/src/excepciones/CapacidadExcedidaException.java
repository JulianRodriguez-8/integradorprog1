package excepciones;

/**
 * Excepción checked lanzada cuando un pedido supera la capacidad de peso del vehículo
 * o cuando se intenta asignar un pedido frágil a una bicicleta[cite: 3, 4].
 */
public class CapacidadExcedidaException extends Exception {

    /**
     * Crea una nueva instancia de la excepción con un mensaje descriptivo.
     * 
     * @param mensaje detalle del error que causó la excepción por capacidad excedida
     */
    public CapacidadExcedidaException(String mensaje) {
        super(mensaje);
    }
}