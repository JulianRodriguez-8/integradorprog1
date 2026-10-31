package modelo;

import excepciones.CapacidadExcedidaException;
import excepciones.AlmacenamientoLLenoException;

public class Repartidor {
    public static final int MAX_PEDIDOS = 20;
    private String legajo;
    private String nombre;
    private Vehiculo vehiculoAsig;
    private Pedido[] pedidosAsignados = new Pedido[MAX_PEDIDOS];
    private int cantidadPedidos = 0;

    public Repartidor(String legajo, String nombre, Vehiculo vehiculoAsignado) {
        setLegajo(legajo);
        setNombre(nombre);
        setVehiculoAsig(vehiculoAsignado);
    }

    public String getLegajo() {
        return legajo;
    }

    public String getNombre() {
        return nombre;
    }

    public Vehiculo getVehiculoAsig() {
        return vehiculoAsig;
    }

    public int getCantidadPedidos() {
        return cantidadPedidos;
    }

    public void setLegajo(String legajo) {
        if (legajo == null || legajo.isEmpty()) {
            throw new IllegalArgumentException("El legajo no puede ser nulo o vacío.");
        } else {
            this.legajo = legajo;
        }

    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede ser nulo o vacío.");
        } else {
            this.nombre = nombre;
        }

    }

    public void setVehiculoAsig(Vehiculo vehiculoAsig) {
        if (vehiculoAsig == null) {
            throw new IllegalArgumentException("El vehículo asignado no puede ser nulo.");
        } else {
            this.vehiculoAsig = vehiculoAsig;
        }
    }

    public void asignarPedido(Pedido pedido) throws CapacidadExcedidaException, AlmacenamientoLLenoException {

        if (cantidadPedidos >= MAX_PEDIDOS) {
            throw new AlmacenamientoLLenoException("No se pueden asignar más pedidos. Capacidad máxima alcanzada.");
        }
        if (!vehiculoAsig.puedeTransportar(pedido)) {
            throw new CapacidadExcedidaException("El vehículo asignado no puede transportar el pedido.");
        }
        pedidosAsignados[cantidadPedidos] = pedido;
        cantidadPedidos++;
    }

    
}
