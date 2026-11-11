package modelo;

/**
 * Contrato para los objetos cuyo estado se puede consultar. Lo implementan
 * {@link Pedido} y {@link Repartidor}.
 */
public interface Rastreable {

    /** @return un texto que describe el estado actual del objeto */
    String obtenerEstadoActual();
}