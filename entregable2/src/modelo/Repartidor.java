package modelo;

import excepciones.CapacidadExcedidaException;
//no va mas almacenamientolleno

import java.util.ArrayList;
import java.util.List;

/**
 * Representa a un repartidor de la empresa CityLink.
 * Cuenta con un legajo, un nombre, un vehículo asignado y un arreglo de pedidos
 * a su cargo.
 * Implementa las interfaces {@link Rastreable} y {@link Facturable}.
 */
public class Repartidor implements Rastreable, Facturable {

    private List<Pedido> pedidosAsignados;

    private String legajo;
    private String nombre;
    private Vehiculo vehiculoAsig;

    /**
     * Crea un repartidor validando sus datos principales a través de los setters.
     * 
     * @param legajo           legajo único del repartidor, no puede ser nulo ni
     *                         vacío
     * @param nombre           nombre del repartidor, no puede ser nulo ni vacío
     * @param vehiculoAsignado vehículo asignado al repartidor, no puede ser nulo
     * @throws IllegalArgumentException si algún parámetro es inválido
     */
    public Repartidor(String legajo, String nombre, Vehiculo vehiculoAsignado) {
        setLegajo(legajo);
        setNombre(nombre);
        setVehiculoAsig(vehiculoAsignado);
        // inicializo la lista
        this.pedidosAsignados = new ArrayList<>();
    }

    /** @return el legajo del repartidor */
    public String getLegajo() {
        return legajo;
    }

    /** @return el nombre del repartidor */
    public String getNombre() {
        return nombre;
    }

    /** @return el vehículo asignado al repartidor */
    public Vehiculo getVehiculoAsig() {
        return vehiculoAsig;
    }

    /** @return la cantidad actual de pedidos asignados pero ahora con size */
    public int getCantidadPedidos() {
        return pedidosAsignados.size();
    }

    /**
     * Cambia el legajo del repartidor.
     * 
     * @param legajo nuevo legajo, no puede ser nulo ni vacío
     * @throws IllegalArgumentException si el legajo es nulo o vacío
     */
    public void setLegajo(String legajo) {
        if (legajo == null || legajo.isEmpty()) {
            throw new IllegalArgumentException("El legajo no puede ser nulo o vacío.");
        } else {
            this.legajo = legajo;
        }
    }

    /**
     * Cambia el nombre del repartidor.
     * 
     * @param nombre nuevo nombre, no puede ser nulo ni vacío
     * @throws IllegalArgumentException si el nombre es nulo o vacío
     */
    public void setNombre(String nombre) {
        if (nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede ser nulo o vacío.");
        } else {
            this.nombre = nombre;
        }
    }

    /**
     * Cambia el vehículo asignado al repartidor.
     * 
     * @param vehiculoAsig nuevo vehículo, no puede ser nulo
     * @throws IllegalArgumentException si el vehículo es nulo
     */
    public void setVehiculoAsig(Vehiculo vehiculoAsig) {
        if (vehiculoAsig == null) {
            throw new IllegalArgumentException("El vehículo asignado no puede ser nulo.");
        } else {
            this.vehiculoAsig = vehiculoAsig;
        }
    }

    /**
     * Asigna un pedido al repartidor utilizando por defecto su vehículo asignado.
     * 
     * @param pedido pedido que se desea asignar
     * @throws CapacidadExcedidaException   si el vehículo no puede transportar el pedido
     */
    public void asignarPedido(Pedido pedido) throws CapacidadExcedidaException {
        asignarPedido(pedido, vehiculoAsig);
    }

    /**
     * Asigna un pedido al repartidor validando la capacidad del vehículo indicado y
     * el límite de almacenamiento.
     * 
     * @param pedido   pedido que se desea asignar
     * @param vehiculo vehículo con el cual se evaluarán las restricciones de
     *                 transporte
     * @throws CapacidadExcedidaException si el vehículo no puede transportar el
     *                                    pedido
     * 
     */
    public void asignarPedido(Pedido pedido, Vehiculo vehiculo)
            throws CapacidadExcedidaException {

        if (!vehiculo.puedeTransportar(pedido)) {
            throw new CapacidadExcedidaException("El vehículo no puede transportar el pedido.");
        }
        pedidosAsignados.add(pedido); // antes era pedidosAsignados[cantidadPedidos] = pedido;

        pedido.setVehiculoAsignado(vehiculo);
    }

    /**
     * Registra la entrega efectiva de un pedido asignado, actualizando su estado a
     * entregado.
     * 
     * @param pedido pedido entregado
     * @throws IllegalArgumentException si el pedido no se encuentra asignado a este
     *                                  repartidor
     */
    public void registrarEntrega(Pedido pedido) {
        // contains busca si el obj esta en la lista
        if (!pedidosAsignados.contains(pedido)) {
            throw new IllegalArgumentException("El pedido no está asignado a este repartidor.");
        }
        pedido.cambiarEstado(Pedido.ENTREGADO);
    }

    /**
     * Obtiene el estado actual del repartidor implementando la interfaz
     * {@link Rastreable}.
     * 
     * @return cadena con información del legajo, nombre y cantidad de pedidos
     *         asignados
     */
    @Override
    public String obtenerEstadoActual() {         //cantidadPedido la cambio por get
        return "Repartidor " + legajo + " (" + nombre + "): " + getCantidadPedidos() + " pedidos asignados";
    }

    /**
     * Calcula la comisión total del repartidor implementando la interfaz
     * {@link Facturable}.
     * Corresponde al 10% de la suma de los importes de los pedidos que ya fueron
     * entregados.
     * 
     * @return importe total de la comisión en dinero
     */
    @Override
    public double calcularImporte() {
        double total = 0;
        //for-each, para cada Pedido 'p' en la lista pedidosAsignados
        for (Pedido p : pedidosAsignados) {
            if (p.getEstado().equals(Pedido.ENTREGADO)) {
                total = total + p.calcularImporte();
            }
        }
        return total * 0.1;
    }

}