package modelo;

public class Cliente {
    private int codigo;
    private String nombre;  
    private String telefono;
    private String zona;
    //constructor
    public Cliente(int codigo, String nombre, String telefono, String zona) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.telefono = telefono;
        this.zona = zona;
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
      
        this.codigo = codigo;
    }
    public void setNombre(String nombre) {
        if (nombre != null && !nombre.trim().isEmpty()) {
            this.nombre = nombre;
        }else {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public void setZona(String zona) {
        this.zona = zona;
    }

}
