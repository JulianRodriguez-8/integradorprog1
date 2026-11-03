package modelo;

import excepciones.PedidoInvalidoException;
import java.time.LocalDateTime;

/**
 * Representa un pedido de tipo urgente dentro del sistema CityLink.
 * Los pedidos urgentes aplican un recargo del 50% sobre el costo base del envío.
 */
public class PedidoUrgente extends Pedido {

    /**
     * Crea un pedido urgente validando sus datos a través de la clase padre {@link Pedido}.
     * 
     * @param codigo      código único del pedido, no puede estar vacío
     * @param cliente     cliente que realiza el pedido, no puede ser nulo
     * @param destino     zona o destino de entrega, no puede estar vacío
     * @param pesoKg      peso en kilogramos, debe ser mayor a 0
     * @param distanciaKm distancia en kilómetros, debe ser mayor a 0
     * @param fechaHora   fecha y hora de ingreso del pedido, no puede ser nula
     * @throws PedidoInvalidoException si algún parámetro de validación falla
     */
    public PedidoUrgente(String codigo, Cliente cliente, String destino, double pesoKg, double distanciaKm, LocalDateTime fechaHora) throws PedidoInvalidoException {
        super(codigo, cliente, destino, pesoKg, distanciaKm, fechaHora);
    }

    /**
     * Calcula el costo total del envío para un pedido urgente.
     * Se compone del costo base más el producto de la distancia por el costo por kilómetro del vehículo,
     * adicionando un 50% de recargo sobre dicho subtotal.
     * 
     * @param vehiculo vehículo asignado para realizar el transporte
     * @return el costo total calculado del envío urgente
     */
    @Override
    public double calcularCosto(Vehiculo vehiculo) {
        double total = COSTO_BASE + vehiculo.getCostoPorKm() * getDistanciaKm();
        return total + total * 0.5; 
    }
}