package app;

import modelo.*;
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

}
