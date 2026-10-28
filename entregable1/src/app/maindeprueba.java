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

        // 2. Vehículos (polimorfismo: un arreglo de Vehiculo con los tres tipos)
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

        // 3. Pedido normal: costo con cada vehículo
        System.out.println("\n=== Costo del pedido normal (10 km) ===");
        try {
            Pedido pedido = new PedidoNormal("P-1", cliente, "Barrio Norte", 5, 10, LocalDateTime.now());
            for (Vehiculo v : vehiculos) {
                System.out.println(v.getClass().getSimpleName() + ": $"
                        + pedido.calcularCosto(v));
            }

            // 4. Cambios de estado
            System.out.println("\n=== Estados ===");
            System.out.println("Estado inicial: " + pedido.getEstado());

            pedido.cambiarEstado(Pedido.EN_CAMINO);
            System.out.println("Pasó a: " + pedido.getEstado());

            try {
                pedido.cambiarEstado(Pedido.PENDIENTE);   // inválido
            } catch (EstadoInvalidoException e) {
                System.out.println("Error esperado: " + e.getMessage());
            }

            pedido.cambiarEstado(Pedido.ENTREGADO);
            System.out.println("Pasó a: " + pedido.getEstado());

        } catch (PedidoInvalidoException e) {
            System.out.println("No se pudo crear el pedido: " + e.getMessage());
        }

        // 5. Pedido inválido (peso negativo)
        System.out.println("\n=== Pedido inválido ===");
        try {
            new PedidoNormal("P-2", cliente, "Sur", -3, 10, LocalDateTime.now());
        } catch (PedidoInvalidoException e) {
            System.out.println("Error esperado: " + e.getMessage());
        }
    }
}