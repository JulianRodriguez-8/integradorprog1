package app;

import excepciones.AlmacenamientoLlenoException;
import excepciones.CapacidadExcedidaException;
import excepciones.EstadoInvalidoException;
import excepciones.PedidoInvalidoException;
import excepciones.ClienteNoEncontradoException;
import java.time.LocalDateTime;
import modelo.*;

public class PruebaModelo {

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
            Cliente ana = new Cliente(1, "Ana", "2657-111111", "Centro");
            Cliente luis = new Cliente(2, "Luis", "2657-222222", "Norte");
            gestor.registrarCliente(ana);
            gestor.registrarCliente(luis);

            System.out.println("Buscando cliente 1: " + gestor.buscarCliente(1));
            System.out.println("Buscando cliente 'luis': " + gestor.buscarCliente("luis"));

            try {
                gestor.buscarCliente(3); // no existe
            } catch (ClienteNoEncontradoException e) {
                System.out.println("Error esperado: " + e.getMessage());
            }

        } catch (AlmacenamientoLlenoException e) {
            System.out.println("Almacenamiento lleno: " + e.getMessage());
        } catch (ClienteNoEncontradoException e) {
            System.out.println("Cliente no encontrado: " + e.getMessage());
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

        // 7. Gestor: asignación y listado de pedidos (polimorfismo)
        System.out.println("\n=== Listado de pedidos ===");
        try {
            CityLink gestor = new CityLink();
            Cliente ana = new Cliente(1, "Ana", "2657-111111", "Centro");
            gestor.registrarCliente(ana);

            gestor.registrarRepartidor(new Repartidor("L-1", "Carlos", new Moto("MOT-1")));
            gestor.registrarRepartidor(new Repartidor("L-2", "Marta", new Bicicleta("BIC-1")));

            gestor.registrarPedido(new PedidoNormal("K-1", ana, "Norte", 5, 10, LocalDateTime.now()));
            gestor.registrarPedido(new PedidoUrgente("K-2", ana, "Sur", 5, 10, LocalDateTime.now()));
            gestor.registrarPedido(new PedidoFragil("K-3", ana, "Este", 5, 10, LocalDateTime.now()));

            gestor.asignarPedido("K-1", "L-1");
            gestor.asignarPedido("K-2", "L-1");

            System.out.println(gestor.listarPedidos());

            // La bicicleta no puede llevar frágiles
            try {
                gestor.asignarPedido("K-3", "L-2");
            } catch (CapacidadExcedidaException e) {
                System.out.println("Error esperado: " + e.getMessage());
            }

            // Un pedido ya asignado no se puede asignar de nuevo
            try {
                gestor.asignarPedido("K-1", "L-2");
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println("Error esperado: " + e.getMessage());
            }

        } catch (AlmacenamientoLlenoException e) {
            System.out.println("Almacenamiento lleno: " + e.getMessage());
        } catch (ClienteNoEncontradoException e) {
            System.out.println("Cliente no encontrado: " + e.getMessage());
        } catch (PedidoInvalidoException e) {
            System.out.println("Pedido inválido: " + e.getMessage());
        } catch (CapacidadExcedidaException e) {
            System.out.println("Capacidad excedida: " + e.getMessage());
        }

        // 8. Gestor: importe total, comisiones, cambio de estado y consultas
        // (Rastreable)
        System.out.println("\n=== Estados, importes y comisiones ===");
        try {
            CityLink gestor = new CityLink();
            Cliente ana = new Cliente(1, "Ana", "2657-111111", "Centro");
            gestor.registrarCliente(ana);
            gestor.registrarRepartidor(new Repartidor("L-1", "Carlos", new Moto("MOT-1")));
            gestor.registrarPedido(new PedidoNormal("N-1", ana, "Norte", 5, 10, LocalDateTime.now()));
            gestor.registrarPedido(new PedidoUrgente("N-2", ana, "Sur", 5, 10, LocalDateTime.now()));

            gestor.asignarPedido("N-1", "L-1");
            gestor.asignarPedido("N-2", "L-1");

            System.out.println("Total: " + gestor.calcularImporteTotal()); // 5500.0
            System.out.print(gestor.listarComisiones()); // comisión $0.0

            System.out.println(gestor.consultarEstadoPedido("N-1")); // PENDIENTE
            gestor.cambiarEstadoPedido("N-1", Pedido.EN_CAMINO);
            System.out.println(gestor.consultarEstadoPedido("N-1")); // EN_CAMINO
            System.out.println(gestor.consultarEstadoRepartidor("L-1"));

            try {
                gestor.cambiarEstadoPedido("N-1", Pedido.PENDIENTE); // transición inválida
            } catch (EstadoInvalidoException e) {
                System.out.println("Error esperado: " + e.getMessage());
            }

            gestor.buscarRepartidor("L-1").registrarEntrega(gestor.buscarPedido("N-1"));
            System.out.print(gestor.listarComisiones()); // comisión $220.0

        } catch (AlmacenamientoLlenoException e) {
            System.out.println("Almacenamiento lleno: " + e.getMessage());
        } catch (ClienteNoEncontradoException e) {
            System.out.println("Cliente no encontrado: " + e.getMessage());
        } catch (PedidoInvalidoException e) {
            System.out.println("Pedido inválido: " + e.getMessage());
        } catch (CapacidadExcedidaException e) {
            System.out.println("Capacidad excedida: " + e.getMessage());
        }

        // 9. Pedido inválido
        System.out.println("\n=== Pedido inválido ===");
        try {
            new PedidoNormal("P-2", cliente, "Sur", -3, 10, LocalDateTime.now());
        } catch (PedidoInvalidoException e) {
            System.out.println("Error esperado: " + e.getMessage());
        }

        // eliminar clientes
        try {
            CityLink gestor = new CityLink();
            gestor.registrarCliente(new Cliente(1, "Ana", "2657-111111", "Centro"));
            gestor.registrarCliente(new Cliente(2, "Luis", "2657-222222", "Norte"));
            gestor.registrarCliente(new Cliente(3, "Sara", "2657-333333", "Sur"));

            gestor.eliminarCliente(2); // el del medio
            System.out.println(gestor.buscarCliente(3)); // Sara sigue estando

            try {
                gestor.buscarCliente(2); // ya no existe
            } catch (ClienteNoEncontradoException e) {
                System.out.println("Error esperado: " + e.getMessage());
            }

            gestor.eliminarCliente(1); // el primero
            gestor.eliminarCliente(3); // el último, que ahora es el único
        } catch (ClienteNoEncontradoException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (AlmacenamientoLlenoException e) {
            System.out.println("Almacenamiento lleno: " + e.getMessage());
        }

    }
}