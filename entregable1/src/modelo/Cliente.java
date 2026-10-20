package modelo;

public class Cliente {
    private int codigo;
    private String nombre;
    private String telefono;
    private String zona;

    // constructor vacio
    public Cliente() {

    }

    // constructor con parametros
    public Cliente(int codigo, String nombre, String telefono, String zona) {
        setCodigo(codigo);
        setNombre(nombre);
        setTelefono(telefono);
        setZona(zona);
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getZona() {
        return zona;
    }

    public void setCodigo(int codigo) {
        if (codigo > 0) {
            this.codigo = codigo;
        } else {
            throw new IllegalArgumentException("El código debe ser un número positivo.");
        }
    }

    public void setNombre(String nombre) {
        if (nombre != null && !nombre.trim().isEmpty()) {
            this.nombre = nombre;
        } else {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
    }

    public void setTelefono(String telefono) {
        if (telefono != null && !telefono.trim().isEmpty()) {
            this.telefono = telefono;
        } else {
            throw new IllegalArgumentException("El teléfono no puede estar vacío.");
        }
    }

    public void setZona(String zona) {
        if (zona != null && !zona.trim().isEmpty()) {
            this.zona = zona;
        } else {
            throw new IllegalArgumentException("La zona no puede estar vacía.");
        }
    }

    @Override
    public String toString() {
        return "Cliente #" + codigo + " - " + nombre + " - " + telefono + " - " + zona;
    }

}
