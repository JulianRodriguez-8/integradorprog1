package app;

import excepciones.*;
import java.util.Scanner;
import modelo.*;

public class Menu {

    private Scanner teclado = new Scanner(System.in);
    private CityLink gestor = new CityLink();

    public void iniciar() {
        int opcion = 0;
        do {
            mostrarMenu();
            opcion = leerEntero("Opción: ");
            try {
                ejecutar(opcion);
            } catch (AlmacenamientoLlenoException e) {
                System.out.println("Almacenamiento lleno: " + e.getMessage());
            } catch (ClienteNoEncontradoException e) {
                System.out.println("Cliente no encontrado: " + e.getMessage());
            } catch (PedidoInvalidoException e) {
                System.out.println("Pedido inválido: " + e.getMessage());
            } catch (CapacidadExcedidaException e) {
                System.out.println("Capacidad excedida: " + e.getMessage());
            } catch (EstadoInvalidoException e) {
                System.out.println("Estado inválido: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("Dato inválido: " + e.getMessage());
            } catch (IllegalStateException e) {
                System.out.println("Operación no permitida: " + e.getMessage());
            }
        } while (opcion != 10);
    }

    private void mostrarMenu() {
        System.out.println("\n===== CityLink =====");
        System.out.println("1. Registrar cliente");
        System.out.println("2. Registrar repartidor con su vehículo");
        System.out.println("3. Registrar pedido(normal,urgente o frágil)");
        System.out.println("4. Asignar pedido a un repartidor");
        System.out.println("5. Cambiar estado de un pedido(en camino, entregado o cancelado)");
        System.out.println("6. Buscar un cliente por código o nombre");
        System.out.println("7. Listar pedidos con su costo y tiempo estimado");
        System.out.println("8. Consultar el estado de un pedido o de un repartidor(Rastreable)");
        System.out.println("9. Ver el importe total de los pedidos y la comisión de cada repartidor(Facturable)");
        System.out.println("10. Salir");
    }

    private void ejecutar(int opcion) throws AlmacenamientoLlenoException,
            ClienteNoEncontradoException, PedidoInvalidoException, CapacidadExcedidaException {
        switch (opcion) {
            case 1:
                registrarCliente();
                break;
            case 2:
                 
                break;
            case 3:
              
                break;
            case 4:
                asignarPedido();
                break;
            case 5:
                
                break;
            case 6:
               
                break;
            case 7:
              
                break;
            case 8:
                
                break;
            case 9:
                
                break;
            case 10:
                System.out.println("Hasta luego.");
                break;
            default:
                System.out.println("Opción inválida.");
        }
    }

    private int leerEntero(String mensaje) {
        int numero = 0;
        boolean valido = false;
        do {
            System.out.print(mensaje);
            try {
                numero = Integer.parseInt(teclado.nextLine().trim());
                valido = true;
            } catch (NumberFormatException e) {
                System.out.println("Ingresá un número entero válido.");
            }
        } while (!valido);
        return numero;
    }

    private String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return teclado.nextLine().trim();
    }

    private void registrarCliente() throws AlmacenamientoLlenoException {
        System.out.println("\n--- Registrar Cliente ---");
        int codigo = leerEntero("Código: ");
        String nombre = leerTexto("Nombre: ");
        String telefono = leerTexto("Teléfono: ");
        String zona = leerTexto("Zona: ");

        Cliente cliente = new Cliente(codigo, nombre, telefono, zona);
        gestor.registrarCliente(cliente);
        System.out.println("Cliente registrado exitosamente.");
    }

    private void asignarPedido() throws AlmacenamientoLlenoException, CapacidadExcedidaException {
        System.out.println("\n--- Asignar Pedido a Repartidor ---");
        String codigoPedido = leerTexto("Código del pedido: ");
        String legajoRepartidor = leerTexto("Legajo del repartidor: ");

        gestor.buscarPedido(codigoPedido); 

       try {
            gestor.asignarPedido(codigoPedido, legajoRepartidor);
            System.out.println("Pedido asignado exitosamente.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error al asignar pedido: " + e.getMessage());
        } finally {
            System.out.println(gestor.consultarEstadoPedido(codigoPedido));
        }
    }
}