package app;

import excepciones.*;
import java.util.Scanner;
import modelo.*;
import java.time.LocalDateTime;

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
                registrarRepartidor();
                break;
            case 3:
                registrarPedido();
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

    private double leerDecimal(String mensaje) {
        double numero = 0;
        boolean valido = false;
        do {
            System.out.print(mensaje);
            try {
                numero = Double.parseDouble(teclado.nextLine().trim().replace(",", "."));
                valido = true;
            } catch (NumberFormatException e) {
                System.out.println("Ingresá un número decimal válido.");
            }
        } while (!valido);
        return numero;
    }

    // 1
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

    // 2
    private void registrarRepartidor() throws AlmacenamientoLlenoException {
        System.out.println("\n--- Registrar Repartidor ---");
        String legajo = leerTexto("Legajo: ");
        String nombre = leerTexto("Nombre: ");
        System.out.println("Tipo de vehículo (1. Bicicleta, 2. Moto, 3. Camioneta): ");
        int tipo = leerEntero("Ingrese el número correspondiente al tipo de vehículo: ");
        String patente = leerTexto("Patente: ");

        Vehiculo vehiculo;
        switch (tipo) {
            case 1:
                vehiculo = new Bicicleta(patente);
                break;
            case 2:
                vehiculo = new Moto(patente);
                break;
            case 3:
                vehiculo = new Camioneta(patente);
                break;
            default:
                throw new IllegalArgumentException("Tipo de vehículo inválido.");
        }

        Repartidor repartidor = new Repartidor(legajo, nombre,
                vehiculo);
        gestor.registrarRepartidor(repartidor);
        System.out.println("Repartidor registrado exitosamente.");
    }

    // 3
    private void registrarPedido()
            throws AlmacenamientoLlenoException, ClienteNoEncontradoException, PedidoInvalidoException {
        System.out.println("\n--- Registrar Pedido ---");
        String codigo = leerTexto("Código del pedido: ");
        int codigoCliente = leerEntero("Código del cliente: ");
        Cliente cliente = gestor.buscarCliente(codigoCliente);
        String destino = leerTexto("Destino: ");
        double pesoKg = leerDecimal("Peso en kg: ");
        double distanciaKm = leerDecimal("Distancia en km: ");
        int tipo = leerEntero("Tipo de pedido (1. Normal, 2. Urgente, 3. Frágil): ");

        Pedido pedido;
        switch (tipo) {
            case 1:
                pedido = new PedidoNormal(codigo, cliente, destino, pesoKg, distanciaKm, LocalDateTime.now());
                break;
            case 2:
                pedido = new PedidoUrgente(codigo, cliente, destino, pesoKg, distanciaKm, LocalDateTime.now());
                break;
            case 3:
                pedido = new PedidoFragil(codigo, cliente, destino, pesoKg, distanciaKm, LocalDateTime.now());
                break;
            default:
                throw new IllegalArgumentException("Tipo de pedido inválido.");
        }
        gestor.registrarPedido(pedido);
        System.out.println("Pedido registrado exitosamente.");
    }

    // 4
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

    //5
    private void cambiarEstadoPedido() throws PedidoInvalidoException {
        System.out.println("\n--- Cambiar Estado de Pedido ---");
        String codigoPedido = leerTexto("Código del pedido: ");
        int opcion = leerEntero("Nuevo estado (1. En camino, 2. Entregado, 3. Cancelado): ");

        String nuevoEstado;
        switch (opcion) {
            case 1:
                nuevoEstado = Pedido.EN_CAMINO;
                break;
            case 2:
                nuevoEstado = Pedido.ENTREGADO;
                break;
            case 3:
                nuevoEstado = Pedido.CANCELADO;
                break;
            default:
                throw new IllegalArgumentException("Opción de estado inválida.");
        }
        gestor.cambiarEstadoPedido(codigoPedido, nuevoEstado);
        System.out.println("Estado del pedido cambiado exitosamente.");
    }






















    //6
}