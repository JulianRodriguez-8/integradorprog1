package modelo;

/**
 * Representa a un cliente dentro del sistema CityLink que realiza pedidos de envío.
 * Contiene información de identificación, contacto y la zona de la ciudad a la que pertenece.
 */
public class Cliente {
    private int codigo;
    private String nombre;
    private String telefono;
    private String zona;

    /**
     * Constructor vacío por defecto.
     */
    public Cliente() {

    }

    /**
     * Crea un cliente validando cada dato a través de los setters.
     * 
     * @param codigo   código entero único del cliente, debe ser mayor a 0
     * @param nombre   nombre del cliente, no puede estar vacío
     * @param telefono teléfono de contacto, no puede estar vacío
     * @param zona     zona de la ciudad donde se ubica, no puede estar vacía
     * @throws IllegalArgumentException si algún dato es inválido
     */
    public Cliente(int codigo, String nombre, String telefono, String zona) {
        setCodigo(codigo);
        setNombre(nombre);
        setTelefono(telefono);
        setZona(zona);
    }

    /** @return el código único del cliente */
    public int getCodigo() {
        return codigo;
    }

    /** @return el nombre del cliente */
    public String getNombre() {
        return nombre;
    }

    /** @return el teléfono de contacto del cliente */
    public String getTelefono() {
        return telefono;
    }

    /** @return la zona de la ciudad del cliente */
    public String getZona() {
        return zona;
    }

    /**
     * Cambia el código del cliente.
     * 
     * @param codigo nuevo código, debe ser mayor a 0
     * @throws IllegalArgumentException si el código no es positivo
     */
    public void setCodigo(int codigo) {
        if (codigo > 0) {
            this.codigo = codigo;
        } else {
            throw new IllegalArgumentException("El código debe ser un número positivo.");
        }
    }

    /**
     * Cambia el nombre del cliente.
     * 
     * @param nombre nuevo nombre, no puede ser nulo ni estar vacío
     * @throws IllegalArgumentException si el nombre es nulo o está vacío
     */
    public void setNombre(String nombre) {
        if (nombre != null && !nombre.trim().isEmpty()) {
            this.nombre = nombre;
        } else {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
    }

    /**
     * Cambia el teléfono del cliente.
     * 
     * @param telefono nuevo teléfono, no puede ser nulo ni estar vacío
     * @throws IllegalArgumentException si el teléfono es nulo o está vacío
     */
    public void setTelefono(String telefono) {
        if (telefono != null && !telefono.trim().isEmpty()) {
            this.telefono = telefono;
        } else {
            throw new IllegalArgumentException("El teléfono no puede estar vacío.");
        }
    }

    /**
     * Cambia la zona del cliente.
     * 
     * @param zona nueva zona, no puede ser nula ni estar vacía
     * @throws IllegalArgumentException si la zona es nula o está vacía
     */
    public void setZona(String zona) {
        if (zona != null && !zona.trim().isEmpty()) {
            this.zona = zona;
        } else {
            throw new IllegalArgumentException("La zona no puede estar vacía.");
        }
    }

    /**
     * Devuelve una representación en cadena del cliente.
     * 
     * @return texto formateado con los datos principales del cliente
     */
    @Override
    public String toString() {
        return "Cliente #" + codigo + " - " + nombre + " - " + telefono + " - " + zona;
    }

}