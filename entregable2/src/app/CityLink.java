package app;

import modelo.*;
import java.time.LocalDateTime;
import excepciones.*;

//agrego
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Clase gestora principal del sistema CityLink.
 * Administra el almacenamiento dinámico mediante colecciones de java.util
 * TreeMap para clientes, ArrayList para pedidos y repartidores)
 * implementa las reglas de negocio, el registro, las búsquedas, las
 * asignaciones y la carga de datos de prueba.
 * 
 * @version 2.0
 * @author Grupo CityLink
 */
public class CityLink {
    // reemplazo los arreglos
    private Map<Integer, Cliente> clientes;
    private List<Pedido> pedidos;
    private List<Repartidor> repartidores;

    public CityLink {
        //// TreeMap ordena automáticamente a los clientes por su código entero.
        this.clientes = new TreeMap<>();
        this.clientes = new TreeMap<>();
        this.pedidos = new ArrayList<>();
        this.repartidores = new ArrayList<>();

    }

    /**
     * Registra un nuevo cliente en el sistema
     * 
     * @param cliente el objeto Cliente que se desea registrar
     * @throws IllegalArgumentException si ya existe un cliente registrado con el
     *                                  mismo código
     */
    public void registrarCliente(Cliente cliente) {
        // busca si el codigo busca si ya existe
        if (clientes.containsKey(cliente.getCodigo())) {
            throw new IllegalArgumentException(
                    "El cliente con código " + cliente.getCodigo() + " ya está registrado.");
        }
        // put inserta el cliente en el árbol asociado a su código.
        clientes.put(cliente.getCodigo(), cliente);

    }

    /**
     * Busca y retorna un cliente utilizando su código numérico entero.
     * 
     * @param codigo código entero del cliente a buscar
     * @return el objeto Cliente encontrado
     * @throws ClienteNoEncontradoException si el cliente no se encuentra en el
     *                                      sistema
     */
    public Cliente buscarCliente(int codigo) throws ClienteNoEncontradoException {
        //// get busca en el árbol directamente
        Cliente cliente = clientes.get(codigo);
        if (clientes == null) {
            throw new ClienteNoEncontradoException("Cliente con código " + codigo + " no encontrado.");

        }
        return cliente;
    }

    /**
     * Busca y retorna un cliente utilizando su nombre (ignorando mayúsculas y
     * minúsculas).
     * 
     * @param nombre nombre del cliente a buscar
     * @return el objeto Cliente encontrado
     * @throws ClienteNoEncontradoException si el cliente no se encuentra en el
     *                                      sistema
     */
    public Cliente buscarCliente(String nombre) throws ClienteNoEncontradoException {
        //rcorre los valores del TreeMap
        for (Cliente: c:clientes.values()) {
            if (c.getNombre().equalsIgnoreCase(nombre)) {
                return c;
            }
        }
        throw new ClienteNoEncontradoException("Cliente con nombre " + nombre + " no encontrado.");
    }

    /**
     * Registra un nuevo repartidor en el sistema validando la capacidad máxima
     * y que no exista un legajo duplicado.
     * 
     * @param repartidor el objeto Repartidor a registrar
     *
     * @throws IllegalArgumentException     si ya existe un repartidor con el mismo
     *                                      legajo
     */
    public void registrarRepartidor(Repartidor repartidor)  {
       
        for (Repartidor r:repartidores) {
            if (r.getLegajo().equals(repartidor.getLegajo())) {
                throw new IllegalArgumentException(
                        "Ya existe un repartidor con legajo " + repartidor.getLegajo() + ".");
            }
        }
        repartidores.add(repartidor);
    }

    /**
     * Registra un nuevo pedido verificando el límite de almacenamiento,
     * la existencia previa del cliente asociado y la unicidad del código de pedido.
     * 
     * @param pedido el objeto Pedido a registrar
     * @throws PedidoInvalidoException      si el código del pedido ya está
     *                                      duplicado
     * @throws ClienteNoEncontradoException si el cliente asociado no existe en el
     *                                      sistema
     * 
     */
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

    /**
     * Busca y retorna un pedido a partir de su código único en formato de texto.
     * 
     * @param codigo código alfanumérico del pedido
     * @return el objeto Pedido encontrado
     * @throws IllegalArgumentException si el pedido no es hallado
     */
    public Pedido buscarPedido(String codigo) {
        for (int i = 0; i < cantidadPedidos; i++) {
            if (pedidos[i].getCodigo().equals(codigo)) {
                return pedidos[i];
            }
        }
        throw new IllegalArgumentException("Pedido con código " + codigo + " no encontrado.");
    }

    /**
     * Busca y retorna un repartidor a partir de su legajo.
     * 
     * @param legajo legajo del repartidor
     * @return el objeto Repartidor encontrado
     * @throws IllegalArgumentException si el repartidor no es hallado
     */
    public Repartidor buscarRepartidor(String legajo) {
        for (int i = 0; i < cantidadRepartidores; i++) {
            if (repartidores[i].getLegajo().equals(legajo)) {
                return repartidores[i];
            }
        }
        throw new IllegalArgumentException("Repartidor con legajo " + legajo + " no encontrado.");
    }

    /**
     * Asigna un pedido a un repartidor específico, validando que el pedido no se
     * encuentre asignado previamente.
     * 
     * @param codigoPedido código del pedido a asignar
     * @param legajo       legajo del repartidor receptor
     * @throws CapacidadExcedidaException   si el vehículo no puede transportar el
     *                                      pedido
     * @throws AlmacenamientoLlenoException si el repartidor alcanzó su tope de
     *                                      pedidos
     * @throws IllegalArgumentException     si el pedido ya se encuentra asignado
     */
    public void asignarPedido(String codigoPedido, String legajo)
            throws CapacidadExcedidaException, AlmacenamientoLlenoException {
        Pedido pedido = buscarPedido(codigoPedido);
        Repartidor repartidor = buscarRepartidor(legajo);
        if (pedido.getVehiculoAsignado() != null) {
            throw new IllegalArgumentException("El pedido " + codigoPedido + " ya está asignado.");
        }
        repartidor.asignarPedido(pedido);
    }

    /**
     * Genera un listado en formato de texto con todos los pedidos registrados,
     * incluyendo sus costos y tiempos estimados calculados mediante polimorfismo.
     * 
     * @return cadena con la información detallada de los pedidos
     */
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

    /**
     * Calcula la suma monetaria total de los importes de todos los pedidos del
     * sistema.
     * 
     * @return importe total acumulado
     */
    public double calcularImporteTotal() {
        double total = 0;
        for (int i = 0; i < cantidadPedidos; i++) {
            total = total + pedidos[i].calcularImporte();
        }
        return total;
    }

    /**
     * Genera un reporte textual con el nombre, legajo y comisiones calculadas de
     * cada repartidor.
     * 
     * @return cadena con el detalle de las comisiones
     */
    public String listarComisiones() {
        String resultado = "";
        for (int i = 0; i < cantidadRepartidores; i++) {
            resultado = resultado + repartidores[i].getNombre() + " (" + repartidores[i].getLegajo()
                    + "): comisión $" + repartidores[i].calcularImporte() + "\n";
        }
        return resultado;
    }

    /**
     * Modifica el estado de un pedido validando que tenga un repartidor asignado
     * (excepto si se cancela).
     * 
     * @param codigoPedido código del pedido a modificar
     * @param nuevoEstado  nuevo estado a establecer
     * @throws IllegalArgumentException si el pedido no posee repartidor asignado al
     *                                  intentar cambiar de estado
     */
    public void cambiarEstadoPedido(String codigoPedido, String nuevoEstado) {
        Pedido pedido = buscarPedido(codigoPedido);
        if (!nuevoEstado.equals(Pedido.CANCELADO) && pedido.getVehiculoAsignado() == null) {
            throw new IllegalArgumentException("El pedido " + codigoPedido + " no tiene repartidor asignado.");
        }
        pedido.cambiarEstado(nuevoEstado);
    }

    /**
     * Consulta el estado actual de un pedido haciendo uso de la interfaz
     * {@link Rastreable}.
     * 
     * @param codigoPedido código del pedido a consultar
     * @return cadena con el estado actual del pedido
     */
    public String consultarEstadoPedido(String codigoPedido) {
        Rastreable r = buscarPedido(codigoPedido);
        return r.obtenerEstadoActual();
    }

    /**
     * Consulta el estado actual de un repartidor haciendo uso de la interfaz
     * {@link Rastreable}.
     * 
     * @param legajo legajo del repartidor a consultar
     * @return cadena con el estado actual del repartidor
     */
    public String consultarEstadoRepartidor(String legajo) {
        Rastreable r = buscarRepartidor(legajo);
        return r.obtenerEstadoActual();
    }

    /**
     * Elimina un cliente del arreglo estático a partir de su código,
     * desplazando los elementos subsiguientes para no dejar huecos.
     * 
     * @param codigo código del cliente a eliminar
     * @throws ClienteNoEncontradoException si el cliente no existe
     */
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

    /**
     * Precarga datos iniciales obligatorios en el sistema (clientes, repartidores
     * con sus vehículos,
     * pedidos de diversos tipos, asignaciones y cambios de estado de prueba).
     * 
     * @throws AlmacenamientoLlenoException si se excede la capacidad de los
     *                                      arreglos
     * @throws ClienteNoEncontradoException si un cliente referenciado no existe
     * @throws PedidoInvalidoException      si algún dato del pedido es inválido
     * @throws CapacidadExcedidaException   si un vehículo excede su capacidad al
     *                                      asignar
     */
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

        // 8 pedidos: 3 normales, 2 urgentes, etc.
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