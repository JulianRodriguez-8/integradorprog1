package app;

import modelo.*;
import java.time.LocalDateTime;
import excepciones.*;

public class CityLink {

    public static final int MAX_CLIENTES = 50;
    public static final int MAX_PEDIDOS = 100;
    public static final int MAX_REPARTIDORES = 10;

    private Cliente[] clientes = new Cliente[MAX_CLIENTES];
    private Pedido[] pedidos = new Pedido[MAX_PEDIDOS];
    private Repartidor[] repartidores = new Repartidor[MAX_REPARTIDORES];

    private int cantidadClientes = 0;
    private int cantidadPedidos = 0;
    private int cantidadRepartidores = 0;

    public void registrarCliente(Cliente cliente) throws AlmacenamientoLlenoException {
        if (cantidadClientes >= MAX_CLIENTES) {
            throw new AlmacenamientoLlenoException("Se ha alcanzado el límite máximo de clientes.");
        }
        for (int i = 0; i < cantidadClientes; i++) {
            if (clientes[i].getCodigo() == cliente.getCodigo()) {
                throw new IllegalArgumentException(
                        "El cliente con código " + cliente.getCodigo() + " ya está registrado.");
            }
        }
        clientes[cantidadClientes] = cliente;
        cantidadClientes++;
    }

    public Cliente buscarCliente(int codigo) throws ClienteNoEncontradoException {
        for (int i = 0; i < cantidadClientes; i++) {
            if (clientes[i].getCodigo() == codigo) {
                return clientes[i];
            }
        }
        throw new ClienteNoEncontradoException("Cliente con código " + codigo + " no encontrado.");
    }

    public Cliente buscarCliente(String nombre) throws ClienteNoEncontradoException {
        for (int i = 0; i < cantidadClientes; i++) {
            if (clientes[i].getNombre().equalsIgnoreCase(nombre)) {
                return clientes[i];
            }
        }
        throw new ClienteNoEncontradoException("Cliente con nombre " + nombre + " no encontrado.");
    }

    public void registrarRepartidor(Repartidor repartidor) throws AlmacenamientoLlenoException {
        if (cantidadRepartidores >= MAX_REPARTIDORES) {
            throw new AlmacenamientoLlenoException("Se ha alcanzado el límite máximo de repartidores.");
        }
        for (int i = 0; i < cantidadRepartidores; i++) {
            if (repartidores[i].getLegajo().equals(repartidor.getLegajo())) {
                throw new IllegalArgumentException(
                        "Ya existe un repartidor con legajo " + repartidor.getLegajo() + ".");
            }
        }
        repartidores[cantidadRepartidores] = repartidor;
        cantidadRepartidores++;
    }

    public void registrarPedido(Pedido pedido)
            throws PedidoInvalidoException, ClienteNoEncontradoException, AlmacenamientoLlenoException {
        if (cantidadPedidos >= MAX_PEDIDOS) {
            throw new AlmacenamientoLlenoException("Se ha alcanzado el límite máximo de pedidos.");
        }
        buscarCliente(pedido.getCliente().getCodigo());
        for (int i = 0; i < cantidadPedidos; i++) {
            if (pedidos[i].getCodigo().equals(pedido.getCodigo())) {
                throw new PedidoInvalidoException("Ya existe un pedido con código " + pedido.getCodigo() + ".");
            }
        }
        pedidos[cantidadPedidos] = pedido;
        cantidadPedidos++;
    }

    public Pedido buscarPedido(String codigo) {
        for (int i = 0; i < cantidadPedidos; i++) {
            if (pedidos[i].getCodigo().equals(codigo)) {
                return pedidos[i];
            }
        }
        throw new IllegalArgumentException("Pedido con código " + codigo + " no encontrado.");
    }

    public Repartidor buscarRepartidor(String legajo) {
        for (int i = 0; i < cantidadRepartidores; i++) {
            if (repartidores[i].getLegajo().equals(legajo)) {
                return repartidores[i];
            }
        }
        throw new IllegalArgumentException("Repartidor con legajo " + legajo + " no encontrado.");
    }

    public void asignarPedido(String codigoPedido, String legajo)
            throws CapacidadExcedidaException, AlmacenamientoLlenoException {
        Pedido pedido = buscarPedido(codigoPedido);
        Repartidor repartidor = buscarRepartidor(legajo);
        if (pedido.getVehiculoAsignado() != null) {
            throw new IllegalArgumentException("El pedido " + codigoPedido + " ya está asignado.");
        }
        repartidor.asignarPedido(pedido);
    }

    public String listarPedidos() {
        String resultado = "";
        for (int i = 0; i < cantidadPedidos; i++) {
            Pedido p = pedidos[i];
            Vehiculo v = p.getVehiculoAsignado();
            resultado = resultado + p;
            if (v == null) {
                resultado = resultado + " | sin asignar\n";
            } else {
                resultado = resultado + " | costo: $" + p.calcularCosto(v)
                        + " | tiempo: " + v.calcularTiempoEntrega(p.getDistanciaKm()) + " min\n";
            }

        }
        return resultado;
    }

    public double calcularImporteTotal() {
        double total = 0;
        for (int i = 0; i < cantidadPedidos; i++) {
            total = total + pedidos[i].calcularImporte();
        }
        return total;
    }

    public String listarComisiones() {
        String resultado = "";
        for (int i = 0; i < cantidadRepartidores; i++) {
            resultado = resultado + repartidores[i].getNombre() + " (" + repartidores[i].getLegajo()
                    + "): comisión $" + repartidores[i].calcularImporte() + "\n";
        }
        return resultado;
    }

    public void cambiarEstadoPedido(String codigoPedido, String nuevoEstado) {
        Pedido pedido = buscarPedido(codigoPedido);
        if (!nuevoEstado.equals(Pedido.CANCELADO) && pedido.getVehiculoAsignado() == null) {
            throw new IllegalArgumentException("El pedido " + codigoPedido + " no tiene repartidor asignado.");
        }
        pedido.cambiarEstado(nuevoEstado);
    }

    public String consultarEstadoPedido(String codigoPedido) {
        Rastreable r = buscarPedido(codigoPedido);
        return r.obtenerEstadoActual();
    }

    public String consultarEstadoRepartidor(String legajo) {
        Rastreable r = buscarRepartidor(legajo);
        return r.obtenerEstadoActual();
    }

    public void eliminarCliente(int codigo) throws ClienteNoEncontradoException {
        int posicion = -1;
        for (int i = 0; i < cantidadClientes; i++) {
            if (clientes[i].getCodigo() == codigo) {
                posicion = i;
            }
        }
        if (posicion == -1) {
            throw new ClienteNoEncontradoException("Cliente con código " + codigo + " no encontrado.");
        }
        for (int i = posicion; i < cantidadClientes - 1; i++) {
            clientes[i] = clientes[i + 1];
        }
        clientes[cantidadClientes - 1] = null;
        cantidadClientes--;

    }

    public void cargarDatosPrueba() throws AlmacenamientoLlenoException, ClienteNoEncontradoException,
            PedidoInvalidoException, CapacidadExcedidaException {

        // 5 clientes
        registrarCliente(new Cliente(1, "Ana", "2657-111111", "Centro"));
        registrarCliente(new Cliente(2, "Luis", "2657-222222", "Norte"));
        registrarCliente(new Cliente(3, "Sara", "2657-333333", "Sur"));
        registrarCliente(new Cliente(4, "Marcos", "2657-444444", "Este"));
        registrarCliente(new Cliente(5, "Lucía", "2657-555555", "Oeste"));

        // 3 repartidores, uno por tipo de vehículo
        registrarRepartidor(new Repartidor("L-1", "Carlos", new Bicicleta("BIC-1")));
        registrarRepartidor(new Repartidor("L-2", "Marta", new Moto("MOT-1")));
        registrarRepartidor(new Repartidor("L-3", "Hugo", new Camioneta("CAM-1")));

        // 8 pedidos: 3 normales, 2 urgentes... (ver tabla abajo)
        registrarPedido(new PedidoNormal("P-1", buscarCliente(1), "Barrio Norte", 3, 4, LocalDateTime.now()));
        registrarPedido(new PedidoNormal("P-2", buscarCliente(2), "Barrio Sur", 8, 6, LocalDateTime.now()));
        registrarPedido(new PedidoUrgente("P-3", buscarCliente(3), "Zona Este", 5, 10, LocalDateTime.now()));
        registrarPedido(new PedidoFragil("P-4", buscarCliente(4), "Centro", 12, 8, LocalDateTime.now()));
        registrarPedido(new PedidoNormal("P-5", buscarCliente(5), "Zona Oeste", 25, 15, LocalDateTime.now()));
        registrarPedido(new PedidoUrgente("P-6", buscarCliente(1), "Barrio Norte", 200, 12, LocalDateTime.now()));
        registrarPedido(new PedidoFragil("P-7", buscarCliente(2), "Barrio Sur", 50, 20, LocalDateTime.now()));
        registrarPedido(new PedidoNormal("P-8", buscarCliente(3), "Centro", 2, 3, LocalDateTime.now()));

        // asignar algunos pedidos (P-5 y P-8 quedan sin asignar a propósito)
        asignarPedido("P-1", "L-1");
        asignarPedido("P-2", "L-1");
        asignarPedido("P-3", "L-2");
        asignarPedido("P-4", "L-2");
        asignarPedido("P-6", "L-3");
        asignarPedido("P-7", "L-3");

        // un pedido entregado y otro en camino, para que se vea la comisión
        cambiarEstadoPedido("P-1", Pedido.EN_CAMINO);
        buscarRepartidor("L-1").registrarEntrega(buscarPedido("P-1"));
        cambiarEstadoPedido("P-3", Pedido.EN_CAMINO);
    }

}
