package excepciones;

/**
 * Excepción checked lanzada cuando se intenta agregar un elemento (cliente, pedido o repartidor)
 * a un arreglo de almacenamiento estático que ya ha alcanzado su capacidad máxima[cite: 3, 4].
 */
public class AlmacenamientoLlenoException extends Exception {

    /**
     * Crea una nueva instancia de la excepción con un mensaje descriptivo.
     * 
     * @param mensaje detalle del error que causó la excepción por almacenamiento lleno
     */
    public AlmacenamientoLlenoException(String mensaje) {
        super(mensaje);
    }
}