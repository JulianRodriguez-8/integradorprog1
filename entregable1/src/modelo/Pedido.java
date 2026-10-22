package modelo;
import java.time.LocalDateTime;

public  abstract class Pedido {
    private String codigo;
    private Cliente cliente;
    private String destino;
    private double pesoKg;
    private double distanciaKm;
    private LocalDateTime fechaHora;
    private String estado; // "pendiente", "enviado", "entregado"

    public Pedido(String codigo, String cliente, String destino, double pesoKg, double distanciaKm, LocalDateTime fechaHora, String estado) {
        setCodigo(codigo);
        setCliente(cliente);    
        setDestino(destino);
        setPesoKg(pesoKg);
        setDistanciaKm(distanciaKm);
        setFechaHora(fechaHora);
        setEstado(estado);
    }

    public String getCodigo() {
        return codigo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public String getDestino() {
        return destino;
    }

    public double getPesoKg() {
        return pesoKg;
    }

    public double getDistanciaKm() {
        return distanciaKm;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public String getEstado() {
        return estado;
    }

    
    public void setCodigo(String codigo) {
        if (codigo != null && !codigo.trim().isEmpty()) {
            this.codigo = codigo;
        } else {
            throw new IllegalArgumentException("El código no puede estar vacío.");
        }
    }

    public void setCliente(String cliente) {
        if (cliente != null && !cliente.trim().isEmpty()) {
            this.cliente = new Cliente(); // Crear un nuevo objeto Cliente
            this.cliente.setNombre(cliente); // Establecer el nombre del cliente
        } else {
            throw new IllegalArgumentException("El cliente no puede estar vacío.");
        }
    }

    public void setDestino(String destino) {
        if (destino != null && !destino.trim().isEmpty()) {
            this.destino = destino;
        } else {
            throw new IllegalArgumentException("El destino no puede estar vacío.");
        }
    }

    public void setPesoKg(double pesoKg) {
        if (pesoKg > 0) {
            this.pesoKg = pesoKg;
        } else {
            throw new IllegalArgumentException("El peso debe ser un valor positivo.");
        }
    }

    public void setDistanciaKm(double distanciaKm) {
        if (distanciaKm > 0) {
            this.distanciaKm = distanciaKm;
        } else {
            throw new IllegalArgumentException("La distancia debe ser un valor positivo.");
        }
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        if (fechaHora != null) {
            this.fechaHora = fechaHora;
        } else {
            throw new IllegalArgumentException("La fecha y hora no pueden estar vacías.");
        }
    }

    public void setEstado(String estado) {
        if (estado != null && !estado.trim().isEmpty()) {
            this.estado = estado;
        } else {
            throw new IllegalArgumentException("El estado no puede estar vacío.");
        }
    }

    public abstract double calcularCosto(Vehiculo vehiculo);

    public double cambiarEstado(String nuevoEstado) {
        this.estado = nuevoEstado;
        return 0; 
    }


 
    
}
