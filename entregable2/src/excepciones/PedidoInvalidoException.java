package excepciones;

/**
 * Excepción checked lanzada cuando se intenta registrar o manipular un pedido
 * con datos inválidos (por ejemplo, peso o distancia no positivos, destino vacío
 * o código de pedido duplicado).
 */
public class PedidoInvalidoException extends Exception {

    /**
     * Crea una nueva instancia de la excepción con un mensaje descriptivo.
     * 
     * @param mensaje detalle del error que causó la excepción
     */
    public PedidoInvalidoException(String mensaje) {
        super(mensaje);
    }
    
}