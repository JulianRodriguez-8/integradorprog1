package app;

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
            Vehiculo bici = new Bicicleta("BIC-9");
            Pedido normal = new PedidoNormal("T-1", cliente, "Norte", 5, 10, LocalDateTime.now());
            Pedido fragil = new PedidoFragil("T-2", cliente, "Sur", 5, 10, LocalDateTime.now());
            Pedido pesado = new PedidoNormal("T-3", cliente, "Este", 15, 10, LocalDateTime.now());

            System.out.println(bici.puedeTransportar(normal)); // true
            System.out.println(bici.puedeTransportar(fragil)); // false: es frágil
            System.out.println(bici.puedeTransportar(pesado)); // false: 15 kg > 10 kgnow());
        } catch (PedidoInvalidoException e) {
            System.out.println("Error esperado: " + e.getMessage());
        }
    }
}