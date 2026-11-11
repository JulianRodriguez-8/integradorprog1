package modelo;

import excepciones.PedidoInvalidoException;
import java.time.LocalDateTime;

/**
 * Representa un pedido de tipo normal dentro del sistema CityLink.
 * Los pedidos normales no poseen ningún recargo adicional sobre el costo base y la distancia.
 */
public class PedidoNormal extends Pedido {

    /**
     * Crea un pedido normal validando sus datos a través de la clase padre {@link Pedido}.
     * 
     * @param codigo      código único del pedido, no puede estar vacío
     * @param cliente     cliente que realiza el pedido, no puede ser nulo
     * @param destino     zona o destino de entrega, no puede estar vacío
     * @param pesoKg      peso en kilogramos, debe ser mayor a 0
     * @param distanciaKm distancia en kilómetros, debe ser mayor a 0
     * @param fechaHora   fecha y hora de ingreso del pedido, no puede ser nula
     * @throws PedidoInvalidoException si algún parámetro de validación falla
     */
    public PedidoNormal(String codigo, Cliente cliente, String destino, double pesoKg, double distanciaKm, LocalDateTime fechaHora) throws PedidoInvalidoException {
        super(codigo, cliente, destino, pesoKg, distanciaKm, fechaHora);
    }
    
    /**
     * Calcula el costo total del envío para un pedido normal.
     * Se compone del costo base más el producto de la distancia por el costo por kilómetro del vehículo,
     * sin aplicar ningún recargo adicional.
     * 
     * @param vehiculo vehículo asignado para realizar el transporte
     * @return el costo total calculado del envío normal
     */
    @Override 
    public double calcularCosto (Vehiculo vehiculo) {
        return COSTO_BASE + vehiculo.getCostoPorKm() * getDistanciaKm();
    }
}