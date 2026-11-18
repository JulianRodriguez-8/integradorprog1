package app;

import excepciones.*;
import java.util.Scanner;
import modelo.*;
import algoritmos.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * Clase encargada de la interfaz de usuario por consola en el sistema CityLink.
 * Contiene el menú interactivo, la lectura y validación de datos por teclado,
 * y la gestión centralizada de excepciones para evitar la interrupción del
 * programa.
 */
public class Menu {

    private Scanner teclado = new Scanner(System.in);
    private CityLink gestor = new CityLink();

    /**
     * Inicia el flujo de la aplicación. Precarga los datos de prueba obligatorios
     * y mantiene el bucle interactivo del menú hasta que el usuario decide salir.
     */
    public void iniciar() {

        try {
            gestor.cargarDatosPrueba();
            System.out.println("Datos de prueba cargados exitosamente.");
        } catch (ClienteNoEncontradoException | PedidoInvalidoException | CapacidadExcedidaException e) {
            System.out.println("Error al cargar datos de prueba: " + e.getMessage());
        }
        int opcion = 0;

        do {
            mostrarMenu();
            opcion = leerEntero("Opción: ");
            try {
                ejecutar(opcion);
            } catch (ClienteNoEncontradoException e) {
                System.out.println("Cliente no encontrado: " + e.getMessage());
            } catch (PedidoInvalidoException e) {
                System.out.println("Pedido inválido: " + e.getMessage());
            } catch (CapacidadExcedidaException e) {
                System.out.println("Capacidad excedida: " + e.getMessage());
            } catch (EstadoInvalidoException e) {
                System.out.println("Estado inválido: " + e.getMessage());
            } catch (SinPedidosPendientesException e) {
                System.out.println("Sin pedidos pendientes: " + e.getMessage());

            } catch (IllegalArgumentException e) {
                System.out.println("Dato inválido: " + e.getMessage());
            } catch (IllegalStateException e) {
                System.out.println("Operación no permitida: " + e.getMessage());
            }
        } while (opcion != 10);
    }

    /**
     * Despliega las opciones disponibles del sistema en la consola.
     */
    private void mostrarMenu() {
        System.out.println("\n===== CityLink =====");
        System.out.println("1. Gestión de clientes");
        System.out.println("2. Gestión de repartidor");
        System.out.println("3. Registrar pedido(normal,urgente o frágil)");
        System.out.println("4. Atender siguiente pedido");
        System.out.println("5. Registrar entrega de pedido");
        System.out.println("6. Deshacer última operación");
        System.out.println("7. Ordenar y listar pedidos");
        System.out.println("8. Buscar pedido por código");
        System.out.println("9. Intercalar pedidos de dos turnos");
        System.out.println("10. Salir");
    }

    /**
     * Dirige la ejecución hacia el método correspondiente según la opción elegida
     * en el menú.
     * 
     * @param opcion número entero que representa la opción del menú seleccionado
     * 
     * @throws ClienteNoEncontradoException  si no se halla el cliente asociado
     * @throws PedidoInvalidoException       si los datos del pedido son incorrectos
     * @throws CapacidadExcedidaException    si se supera la capacidad de carga del
     *                                       vehículo
     * @throws SinPedidosPendientesException si se intenta atender un pedido con las
     *                                       colas vacías
     */
    private void ejecutar(int opcion) throws SinPedidosPendientesException,
            ClienteNoEncontradoException, PedidoInvalidoException, CapacidadExcedidaException {
        switch (opcion) {
            case 1:
                gestionClientes();
                break;
            case 2:
                gestionRepartidores();
                break;
            case 3:
                registrarPedido();
                break;
            case 4:
                atenderSiguientePedido();
                break;
            case 5:
                registrarEntrega();
                break;
            case 6:

                gestor.deshacerUltimaOperacion();
                System.out.println("Operación deshecha correctamente.");
                break;
            case 7:
                ordenarYListarPedidos();
                break;
            case 8:
                buscarPedidoAlgoritmo();
                break;
            case 9:
                intercalarPedidosTurnos();
                break;
            case 10:
                System.out.println("Hasta luego.");
                break;
            default:
                System.out.println("Opción inválida.");
        }
    }

    /**
     * Lee un número entero por teclado asegurando que el programa no finalice ante
     * un error de formato.
     * 
     * @param mensaje texto indicador que se muestra al usuario
     * @return el número entero ingresado válidamente
     */
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

    /**
     * Lee una cadena de texto por teclado.
     * 
     * @param mensaje texto indicador que se muestra al usuario
     * @return la cadena de texto ingresada y sin espacios sobrantes
     */
    private String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return teclado.nextLine().trim();
    }

    /**
     * Lee un número decimal por teclado aceptando tanto puntos como comas,
     * previniendo cierres inesperados por errores de tipeo.
     * 
     * @param mensaje texto indicador que se muestra al usuario
     * @return el valor decimal ingresado válidamente
     */
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

    /**
     * /*Opción 1: Gestiona el ABM y consulta de clientesalta, baja, búsqueda y
     * listado ordenado
     * por código mediante {@code TreeMap}
     * 
     * 
     */

    private void gestionClientes() throws ClienteNoEncontradoException {
        System.out.println("\n--- GESTIÓN DE CLIENTES ---");
        System.out.println("1. Alta de cliente");
        System.out.println("2. Baja de cliente");
        System.out.println("3. Buscar cliente por código");
        System.out.println("4. Listar clientes ordenados");
        int sub = leerEntero("Seleccione una opción: ");

        if (sub == 1) {

            int codigo = leerEntero("Código: ");
            String nombre = leerTexto("Nombre: ");
            String telefono = leerTexto("Teléfono: ");
            String zona = leerTexto("Zona: ");

            gestor.registrarCliente(new Cliente(codigo, nombre, telefono, zona));
            System.out.println("Cliente registrado con éxito");

        } else if (sub == 2) {
            int codigo = leerEntero("Código:");
            gestor.eliminarCliente(codigo);
            System.out.println("Cliente eliminado con éxito.");
        } else if (sub == 3) {
            int codigo = leerEntero("Código a buscar: ");
            Cliente c = gestor.buscarCliente(codigo);
            System.out.println("Cliente encontrado: " + c);
        } else if (sub == 4) {
            System.out.println("\n--- LISTADO DE CLIENTES ---");
            System.out.println(gestor.listarClientesOrdenados());
        } else {
            System.out.println("Opción inválida");
        }

    }

    /**
     * Opción 2: Gestión de repartidores (alta y listado).
     * 
     * 
     */
    private void gestionRepartidores() {
        System.out.println("\n--- GESTIÓN DE REPARTIDORES ---");
        System.out.println("1. Registrar repartidor");
        System.out.println("2. Listar repartidores");
        int sub = leerEntero("Seleccione una opción: ");
        if (sub == 1) {
            System.out.println("\n--- Registrar Repartidor ---");
            String legajo = leerTexto("Legajo: ");
            String nombre = leerTexto("Nombre: ");
            int tipo = leerEntero(
                    "Ingrese el número correspondiente al tipo de vehículo: 1.Bicicleta, 2. Moto, 3. Camioneta: ");
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
            Repartidor repartidor = new Repartidor(legajo, nombre, vehiculo);
            gestor.registrarRepartidor(repartidor);
            System.out.println("Repartidor registrado exitosamente.");

        } else if (sub == 2) {
            System.out.println("\n--- LISTADO DE REPARTIDORES ---");
            System.out.println(gestor.listarRepartidores());

        }

    }

    /**
     * Opción 3: Registrar un pedido (lo encola y registra en la pila).
     * 
     * 
     * @throws ClienteNoEncontradoException si el código de cliente ingresado no
     *                                      existe
     * @throws PedidoInvalidoException      si los datos del pedido son inválidos
     */
    private void registrarPedido()
            throws ClienteNoEncontradoException, PedidoInvalidoException {
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

    /**
     * Opción 4: Atiende el siguiente pedido según prioridad (urgentes primero)
     * y lo asigna al repartidor indicado poniéndolo en camino.
     * 
     * @throws SinPedidosPendientesException si no hay pedidos pendientes en las
     *                                       colas
     * @throws CapacidadExcedidaException    si el vehículo del repartidor no
     *                                       soporta el pedido
     */
    private void atenderSiguientePedido() throws SinPedidosPendientesException, CapacidadExcedidaException {
        System.out.println("\n--- Atender Siguiente Pedido ---");
        String legajo = leerTexto("Legajo del repartidor que atenderá: ");
        gestor.atenderSiguientePedido(legajo);
        System.out.println("Siguiente pedido atendido, asignado y puesto en camino.");
    }

    /**
     * 5: Registrar una entrega y actualizar historial/estado.
     */
    private void registrarEntrega() {
        System.out.println("\n--- REGISTRAR ENTREGA ---");
        String codPedido = leerTexto("Código del pedido entregado: ");
        String legajo = leerTexto("Legajo del repartidor: ");

        Pedido p = gestor.buscarPedido(codPedido);
        Repartidor r = gestor.buscarRepartidor(legajo);

        r.registrarEntrega(p);
        System.out.println("Entrega registrada correctamente.");
    }

    /**
     * Opción 7: Ordena y lista los pedidos utilizando el criterio (costo, distancia
     * o fecha)
     * y el algoritmo de ordenamiento (Burbuja o Inserción) elegidos por el usuario.
     */
    private void ordenarYListarPedidos() {
        System.out.println("\n--- Ordenar y Listar Pedidos ---");
        List<Pedido> copiaPedidos = new ArrayList<>(gestor.getPedidos());

        if (copiaPedidos.isEmpty()) {
            System.out.println("No hay pedidos registrados.");
            return;
        }
        int criterioOpt = leerEntero("Criterio de orden (1. Costo, 2. Distancia, 3. Fecha): ");
        int algoOpt = leerEntero("Algoritmo a usar (1. Burbuja, 2. Inserción): ");

        Comparator<Pedido> comparador;
        if (criterioOpt == 1) {
            comparador = (p1, p2) -> Double.compare(p1.calcularImporte(), p2.calcularImporte());
        } else {
            if (criterioOpt == 2) {
                comparador = (p1, p2) -> Double.compare(p1.getDistanciaKm(), p2.getDistanciaKm());
            } else {
                comparador = Comparator.comparing(Pedido::getFechaHora);
            }
        }

        if (algoOpt == 1) {
            Ordenador.ordenarBurbuja(copiaPedidos, comparador);
            System.out.println("-> Ordenado por Método de Burbuja:");
        } else {
            Ordenador.ordenarPorInsercion(copiaPedidos, comparador);
            System.out.println("-> Ordenado por Método de Inserción:");
        }

        for (Pedido p : copiaPedidos) {
            System.out.println(
                    " - " + p + " | Importe: $" + p.calcularImporte() + " | Distancia: " + p.getDistanciaKm() + "km");
        }
    }

    /**
     * Opción 8: Buscar un pedido por código (Lineal o Binaria).
     * (ordenando previamente una copia de la lista por código).
     */
    private void buscarPedidoAlgoritmo() {
        System.out.println("\n--- Buscar Pedido ---");
        String codigo = leerTexto("Ingrese el código del pedido a buscar: ");
        int metodo = leerEntero("Método de búsqueda (1. Lineal, 2. Binaria): ");

        List<Pedido> lista = gestor.getPedidos();
        Pedido encontrado = null;

        if (metodo == 1) {
            encontrado = Buscador.busquedaLinealPedido(lista, codigo);
        } else {
            if (metodo == 2) {
                List<Pedido> ordenada = new ArrayList<>(lista);
                Ordenador.ordenarBurbuja(ordenada, Comparator.comparing(Pedido::getCodigo));
                encontrado = Buscador.busquedaBinariaPedido(ordenada, codigo);
            } else {
                System.out.println("Método de búsqueda inválido.");
                return;
            }
        }

        if (encontrado != null) {
            System.out.println("Pedido encontrado: " + encontrado);
        } else {
            System.out.println("Pedido con código " + codigo + " no encontrado.");
        }
    }

    /**
     * Opción 9: Divide los pedidos registrados en dos turnos ordenados por código
     * y los combina en una única lista ordenada mediante intercalación.
     */
    private void intercalarPedidosTurnos() {
        System.out.println("\n--- Intercalar Pedidos de Dos Turnos ---");
        List<Pedido> todos = gestor.getPedidos();
        if (todos.size() < 2) {
            System.out.println("Se necesitan al menos 2 pedidos registrados para intercalar.");
            return;
        }

        List<Pedido> turno1 = new ArrayList<>();
        List<Pedido> turno2 = new ArrayList<>();

        for (int i = 0; i < todos.size(); i++) {
            if (i % 2 == 0) {
                turno1.add(todos.get(i));
            } else {
                turno2.add(todos.get(i));
            }
        }

        Comparator<Pedido> comp = Comparator.comparing(Pedido::getCodigo);
        Ordenador.ordenarBurbuja(turno1, comp);
        Ordenador.ordenarBurbuja(turno2, comp);

        List<Pedido> resultado = Intercalador.intercalarListas(turno1, turno2, comp);
        System.out.println("\n Listado combinado e intercalado con éxito:");
        for (Pedido p : resultado) {
            System.out.println(" - " + p.getCodigo());
        }
    }
}