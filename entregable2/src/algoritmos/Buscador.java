package algoritmos;

import modelo.Pedido;
import modelo.Cliente;
import java.util.List;

/**
 * Contiene los algoritmos de búsqueda lineal y binaria implementados desde
 * cero,
 * 
 * @version 2.0
 * @author Grupo CityLink
 */

public class Buscador {
    /**
     * Búsqueda lineal de un pedido por su código dentro de una lista.
     * Recorre elemento por elemento desde el principio hasta el final.
     * 
     * @ lista de pedidos donde se realizará la búsqueda
     * 
     * @param codigo código alfanumérico del pedido a buscar
     * @return el Pedido encontrado, o null si no existe
     */
    public static Pedido busquedaLinealPedido(List<Pedido> lista, String codigo) {
        Pedido encontrado = null;
        int i = 0;
        while (i < lista.size() && encontrado == null) {
            Pedido p = lista.get(i);
            if (p.getCodigo().equals(codigo)) {
                encontrado = p; // ¡Lo encontramos!
            }
            i++;
        }
        return encontrado;

    }

    /**
     * Búsqueda lineal de un cliente por su nombre 
     * 
     * 
     * @param lista  lista de clientes a recorrer
     * @param nombre nombre del cliente que se busca
     * @return el Cliente encontrado, o null si no existe
     */
    public static Cliente busquedaLinealCliente(List<Cliente> lista, String nombre) {
        for (int i = 0; i < lista.size(); i++) {
            Cliente c = lista.get(i);
            if (c.getNombre().equalsIgnoreCase(nombre)) {
                return c;
            }
        }
        return null;
    }


    /**
     * Búsqueda binaria de un pedido por su código en una lista previamente ordenada.
     
     * @param listaOrdenada lista de pedidos ordenada alfabéticamente por código
     * @param codigoBuscado código que se desea encontrar
     * @return el Pedido encontrado, o null si no existe
     */
    public static Pedido busquedaBinariaPedido(List<Pedido> listaOrdenada, String codigoBuscado) {
        int inicio = 0;
        int fin = listaOrdenada.size() - 1;
        Pedido encontrado = null;

        while (inicio <= fin && encontrado == null) {
            int medio = inicio + (fin - inicio) / 2; // Posición central
            Pedido medioPedido = listaOrdenada.get(medio);
            
            // Comparamos los códigos alfanuméricamente
            int comparacion = medioPedido.getCodigo().compareTo(codigoBuscado);

            if (comparacion == 0) {
                encontrado = medioPedido; // en el medio exacto
            } else if (comparacion < 0) {
                inicio = medio + 1; // descarto la mitad izquierda
            } else {
                fin = medio - 1;    // descarto la mitad derecha
            }
        }
        return encontrado;
    }
}

