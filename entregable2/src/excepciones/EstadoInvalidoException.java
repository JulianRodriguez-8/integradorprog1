package excepciones;

/**
 * Excepción unchecked (runtime) lanzada cuando se intenta realizar
 * una transición de estado de pedido que no está permitida por las reglas de negocio[cite: 4].
 */
public class EstadoInvalidoException extends RuntimeException {

    /**
     * Crea una nueva instancia de la excepción con un mensaje descriptivo.
     * 
     * @param mensaje detalle del error que causó la excepción por estado inválido
     */
    public EstadoInvalidoException(String mensaje) {
        super(mensaje);
    }
    
}