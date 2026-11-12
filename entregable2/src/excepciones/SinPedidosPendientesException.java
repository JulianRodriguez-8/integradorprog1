package excepciones;//nueva excepcion

/**
 * Excepción lanzada cuando se intenta atender un pedido desde la cola,
 * pero no hay pedidos pendientes (ni normales ni urgentes).
 * 
 * @version 2.0
 * @author Grupo CityLink
 */
public class SinPedidosPendientesException extends Exception {
    
    /**
     * Crea la excepción con un mensaje descriptivo.
     * @param mensaje detalle del error
     */
    public SinPedidosPendientesException(String mensaje) {
        super(mensaje);
    }
}