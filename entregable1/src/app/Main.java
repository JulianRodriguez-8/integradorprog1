package app;

import excepciones.AlmacenamientoLlenoException;
import excepciones.CapacidadExcedidaException;
import excepciones.EstadoInvalidoException;
import excepciones.PedidoInvalidoException;
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

        // 5. Pedido inválido
        System.out.println("\n=== Pedido inválido ===");
        try {
            Pedido p = new PedidoUrgente("F-1", cliente, "Sur", 5, 10, LocalDateTime.now());
            System.out.println(p.calcularImporte()); // 0.0
            p.setVehiculoAsignado(new Moto("MOT-3"));
            System.out.println(p.calcularImporte()); // 3300.0
            System.out.println(p.obtenerEstadoActual()); // pedido: F-1, estado: PENDIENTE
        } catch (PedidoInvalidoException e) {
            System.out.println("Error esperado: " + e.getMessage());
        }

                // 6. Repartidor y sobrecarga de asignarPedido
        System.out.println("\n=== Repartidor ===");
        try {
            Repartidor rep = new Repartidor("L-2", "Marta", new Moto("MOT-7"));
            Pedido p = new PedidoNormal("E-1", cliente, "Norte", 5, 10, LocalDateTime.now());
            Pedido q = new PedidoNormal("E-2", cliente, "Sur", 5, 10, LocalDateTime.now());

            rep.asignarPedido(p);                             // usa la moto
            rep.asignarPedido(q, new Camioneta("CAM-8"));     // usa la camioneta

            System.out.println("Pedidos asignados: " + rep.getCantidadPedidos());
            System.out.println(p.calcularImporte());          // 2200.0
            System.out.println(q.calcularImporte());          // 4000.0
        } catch (CapacidadExcedidaException e) {
            System.out.println("Capacidad excedida: " + e.getMessage());
        } catch (AlmacenamientoLlenoException e) {
            System.out.println("Almacenamiento lleno: " + e.getMessage());
        } catch (PedidoInvalidoException e) {
            System.out.println("Pedido inválido: " + e.getMessage());
        }
    }
    
}