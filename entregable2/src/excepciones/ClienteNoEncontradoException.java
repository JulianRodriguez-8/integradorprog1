package excepciones;

/**
 * Excepción checked lanzada cuando se intenta buscar o referenciar un cliente
 * en el sistema que no se encuentra registrado[cite: 3, 4].
 */
public class ClienteNoEncontradoException extends Exception {

    /**
     * Crea una nueva instancia de la excepción con un mensaje descriptivo.
     * 
     * @param mensaje detalle del error que causó la excepción por cliente no encontrado
     */
    public ClienteNoEncontradoException(String mensaje) {
        super(mensaje);
    }
    
}