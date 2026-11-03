package modelo;

import excepciones.PedidoInvalidoException;
import java.time.LocalDateTime;

/**
 * Representa un pedido de tipo frágil dentro del sistema CityLink.
 * Los pedidos frágiles no pueden ser transportados en bicicleta y suman
 * un adicional fijo de $800 sobre el costo base y la distancia.
 */
public class PedidoFragil extends Pedido {

    /**
     * Crea un pedido frágil validando sus datos a través de la clase padre {@link Pedido}.
     * 
     * @param codigo      código único del pedido, no puede estar vacío
     * @param cliente     cliente que realiza el pedido, no puede ser nulo
     * @param destino     zona o destino de entrega, no puede estar vacío
     * @param pesoKg      peso en kilogramos, debe ser mayor a 0
     * @param distanciaKm distancia en kilómetros, debe ser mayor a 0
     * @param fechaHora   fecha y hora de ingreso del pedido, no puede ser nula
     * @throws PedidoInvalidoException si algún parámetro de validación falla
     */
    public PedidoFragil(String codigo, Cliente cliente, String destino, double pesoKg, double distanciaKm, LocalDateTime fechaHora) throws PedidoInvalidoException {
        super(codigo, cliente, destino, pesoKg, distanciaKm, fechaHora);
    }

    /**
     * Calcula el costo total del envío para un pedido frágil.
     * Se compone del costo base más el producto de la distancia por el costo por kilómetro del vehículo,
     * adicionando un recargo fijo de $800 debido a su naturaleza frágil.
     * 
     * @param vehiculo vehículo asignado para realizar el transporte
     * @return el costo total calculado del envío frágil
     */
    @Override
    public double calcularCosto(Vehiculo vehiculo) {
        return COSTO_BASE + vehiculo.getCostoPorKm() * getDistanciaKm() + 800;
    }

    /**
     * Indica si el pedido es de tipo frágil.
     * 
     * @return {@code true} ya que este pedido es frágil
     */
    @Override
    public boolean esFragil() {
        return true;
    }
    
}