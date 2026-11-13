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

}
