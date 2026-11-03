package modelo;

/**
 * Contrato para los objetos que generan un importe. Lo implementan
 * {@link Pedido} (costo del envío) y {@link Repartidor} (comisión).
 */
public interface Facturable {

    /** @return el importe en pesos que corresponde a este objeto */
    double calcularImporte();
}