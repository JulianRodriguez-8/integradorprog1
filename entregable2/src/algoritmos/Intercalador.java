package algoritmos;

import modelo.Pedido;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;

/**
 * Contiene el algoritmo de intercalación (merge) para combinar dos listas
 * de pedidos previamente ordenadas en una sola lista ordenada en un único recorrido.
 * 
 * @version 2.0
 * @author Grupo CityLink
 */
public class Intercalador {

    /**
     * Intercala dos listas de pedidos ya ordenadas en una única lista ordenada.
     * 
     * @param lista1 primera lista de pedidos ordenada según el comparador
     * @param lista2 segunda lista de pedidos ordenada según el comparador
     * @param comparador criterio de ordenamiento utilizado en ambas listas
     * @return una nueva List con todos los elementos combinados y ordenados
     */
    public static List<Pedido> intercalarListas(List<Pedido> lista1, List<Pedido> lista2, Comparator<Pedido> comparador) {
        List<Pedido> resultado = new ArrayList<>();
        int i = 0; // Índice para recorrer la lista1
        int j = 0; // Índice para recorrer la lista2

        // Recorremos ambas listas simultáneamente mientras tengan elementos
        while (i < lista1.size() && j < lista2.size()) {
            Pedido p1 = lista1.get(i);
            Pedido p2 = lista2.get(j);

            // Comparamos cuál es menor según el criterio
            if (comparador.compare(p1, p2) <= 0) {
                resultado.add(p1);
                i++; // Avanzamos en la lista 1
            } else {
                resultado.add(p2);
                j++; // Avanzamos en la lista 2
            }
        }

        // Si quedaron elementos sobrantes en la lista 1, los agregamos todos al final
        while (i < lista1.size()) {
            resultado.add(lista1.get(i));
            i++;
        }

        // Si quedaron elementos sobrantes en la lista 2, los agregamos todos al final
        while (j < lista2.size()) {
            resultado.add(lista2.get(j));
            j++;
        }

        return resultado;
    }
}