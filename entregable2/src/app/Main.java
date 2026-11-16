package app;

/**
 * Clase principal de entrada para la aplicación CityLink.
 * Contiene el método {@code main} encargado de inicializar y arrancar el menú interactivo por consola.
 * 
 */
public class Main {
    
    /**
     * Método principal (punto de entrada de la aplicación Java).
     * Instancia el menú de la aplicación y ejecuta su flujo de inicio

     */
    public static void main(String[] args) {
        new Menu().iniciar();
    }
}