package algoritmos;

import modelo.Pedido;
import java.util.List;
import java.util.Comparator;

/**
 * Contiene algoritmos de ordenamiento implementados desde cero a mano
 * (Burbuja e Inserción), permitiendo ordenar listas de pedidos mediante
 * distintos criterios.
 * 
 * @version 2.0
 * @author Grupo CityLink
 */

public class Ordenador {
    /**
     * Ordena una lista de pedidos utilizando el algoritmo de la Burbuja (Bubble
     * Sort).
     * Compara elementos adyacentes y los intercambia si están en el orden
     * incorrecto.
     * 
     * @param lista      la lista de pedidos a ordenar
     * @param comparador el criterio de ordenamiento (por costo, distancia, fecha,
     *                   etc.)
     */

    public static void ordenarBurbuja(List<Pedido> lista, Comparator<Pedido> comparador) {
        int n = lista.size();
        boolean huboCambios;

        for (int i = 0; i < n - 1; i++) {
            huboCambios = false;
            for (int j = 0; j < n - 1 - i; j++) {
                Pedido p1 = lista.get(j);
                Pedido p2 = lista.get(j + 1);

                // Si p1 es "mayor" que p2 según el criterio, los intercambiamos
                if (comparador.compare(p1, p2) > 0) {
                    lista.set(j, p2);
                    lista.set(j + 1, p1);
                    huboCambios = true;
                }
            }
            // Optimización: si en una vuelta completa no hubo cambios, la lista ya está ordenada
            if (!huboCambios) {
                break;
            }
        }
    }

    /**
     * Ordena una lista de pedidos utilizando el algoritmo de Inserción (Insertion Sort).
     * Construye la lista ordenada elemento por elemento, insertando cada uno en su lugar correcto.
     * 
     * @param lista la lista de pedidos a ordenar
     * @param comparador el criterio de ordenamiento (por costo, distancia, fecha, etc.)
     */
    public static void ordenarPorInsercion(List<Pedido> lista, Comparator<Pedido> comparador) {
        int n = lista.size();

        for (int i = 1; i < n; i++) {
            Pedido actual = lista.get(i);
            int j = i - 1;

            // Desplazamos los elementos mayores hacia la derecha para hacer hueco
            while (j >= 0 && comparador.compare(lista.get(j), actual) > 0) {
                lista.set(j + 1, lista.get(j));
                j--;
            }
            // Insertamos el elemento en su posición correcta
            lista.set(j + 1, actual);
        }
    }

}
