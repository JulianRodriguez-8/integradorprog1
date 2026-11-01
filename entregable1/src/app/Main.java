package app;

import excepciones.AlmacenamientoLlenoException;
import excepciones.CapacidadExcedidaException;
import excepciones.EstadoInvalidoException;
import excepciones.PedidoInvalidoException;
import excepciones.ClienteNoEncontradoException;
import java.time.LocalDateTime;
import modelo.*;

public class Main {

    public static void main(String[] args) {

        // 1. Cliente
        System.out.println("=== Cliente ===");
        Cliente cliente = new Cliente(1, "Ana", "2657-111111", "Centro");
        System.out.println(cliente);

        try {
            new Cliente(-5, "Luis", "123", "Norte");
        } catch (IllegalArgumentException e) {
            System.out.println("Error esperado: " + e.getMessage());
        }

        // 2. Vehículos (polimorfismo)
        System.out.println("\n=== Tiempo de entrega (10 km) ===");
        Vehiculo[] vehiculos = {
                new Bicicleta("BIC-1"),
                new Moto("MOT-1"),
                new Camioneta("CAM-1")
        };
        for (Vehiculo v : vehiculos) {
            System.out.println(v.getClass().getSimpleName() + ": "
                    + v.calcularTiempoEntrega(10) + " min");
        }

        // 3. Pedido normal y cambios de estado
        System.out.println("\n=== Pedido normal y estados ===");
        try {
            Pedido pedido = new PedidoNormal("P-1", cliente, "Barrio Norte", 5, 10, LocalDateTime.now());
            for (Vehiculo v : vehiculos) {
                System.out.println(v.getClass().getSimpleName() + ": $" + pedido.calcularCosto(v));
            }

            System.out.println("Estado inicial: " + pedido.getEstado());
            pedido.cambiarEstado(Pedido.EN_CAMINO);
            System.out.println("Pasó a: " + pedido.getEstado());

            try {
                pedido.cambiarEstado(Pedido.PENDIENTE); // inválido
            } catch (EstadoInvalidoException e) {
                System.out.println("Error esperado: " + e.getMessage());
            }

            pedido.cambiarEstado(Pedido.ENTREGADO);
            System.out.println("Pasó a: " + pedido.getEstado());

        } catch (PedidoInvalidoException e) {
            System.out.println("No se pudo crear el pedido: " + e.getMessage());
        }

        // 4. Los tres tipos de pedido (polimorfismo), costo con una moto
        System.out.println("\n=== Costo de los tres tipos (moto, 10 km) ===");
        try {
            Pedido[] pedidos = {
                    new PedidoNormal("P-10", cliente, "Norte", 5, 10, LocalDateTime.now()),
                    new PedidoUrgente("P-11", cliente, "Sur", 5, 10, LocalDateTime.now()),
                    new PedidoFragil("P-12", cliente, "Este", 5, 10, LocalDateTime.now())
            };
            Vehiculo moto = new Moto("MOT-2");

            for (Pedido p : pedidos) {
                System.out.println(p.getClass().getSimpleName() + ": $" + p.calcularCosto(moto));
            }
        } catch (PedidoInvalidoException e) {
            System.out.println("No se pudieron crear los pedidos: " + e.getMessage());
        }

                // 5. Gestor: clientes y búsquedas
        System.out.println("\n=== Gestor CityLink ===");
        try {
            CityLink gestor = new CityLink();
            gestor.registrarCliente(new Cliente(1, "Ana", "2657-111111", "Centro"));
            gestor.registrarCliente(new Cliente(2, "Luis", "2657-222222", "Norte"));

            System.out.println(gestor.buscarCliente(2));       // Cliente #2 - Luis - ...
            System.out.println(gestor.buscarCliente("ana"));   // Cliente #1 - Ana - ...
            gestor.buscarCliente(99);                          // lanza ClienteNoEncontradoException
        } catch (ClienteNoEncontradoException e) {
            System.out.println("Error esperado: " + e.getMessage());
        } catch (AlmacenamientoLlenoException e) {
            System.out.println("Almacenamiento lleno: " + e.getMessage());
        }

        // 6. Repartidor y sobrecarga de asignarPedido
        System.out.println("\n=== Repartidor ===");
        try {
            Repartidor rep = new Repartidor("L-4", "Sara", new Moto("MOT-11"));
            Pedido p1 = new PedidoNormal("C-1", cliente, "Norte", 5, 10, LocalDateTime.now());
            Pedido p2 = new PedidoUrgente("C-2", cliente, "Sur", 5, 10, LocalDateTime.now());

            rep.asignarPedido(p1);
            rep.asignarPedido(p2);
            System.out.println(rep.calcularImporte()); // 0.0: todavía no entregó nada

            p1.cambiarEstado(Pedido.EN_CAMINO);
            rep.registrarEntrega(p1);
            System.out.println(rep.calcularImporte()); // 220.0

            p2.cambiarEstado(Pedido.EN_CAMINO);
            rep.registrarEntrega(p2);
            System.out.println(rep.calcularImporte()); // 550.0
            System.out.println(rep.obtenerEstadoActual());
        } catch (CapacidadExcedidaException e) {
            System.out.println("Capacidad excedida: " + e.getMessage());
        } catch (AlmacenamientoLlenoException e) {
            System.out.println("Almacenamiento lleno: " + e.getMessage());
        } catch (PedidoInvalidoException e) {
            System.out.println("Pedido inválido: " + e.getMessage());

        }
    }

}