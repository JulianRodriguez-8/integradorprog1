package modelo;

import java.time.LocalDateTime;

import excepciones.EstadoInvalidoException;
import excepciones.PedidoInvalidoException;

public abstract class Pedido {
    public static final String PENDIENTE = "PENDIENTE";
    public static final String EN_CAMINO = "EN_CAMINO";
    public static final String ENTREGADO = "ENTREGADO";
    public static final String CANCELADO = "CANCELADO";
    public static final double COSTO_BASE = 1000;
    private String codigo;
    private Cliente cliente;
    private String destino;
    private double pesoKg;
    private double distanciaKm;
    private LocalDateTime fechaHora;
    private String estado;

    public Pedido(String codigo, Cliente cliente, String destino, double pesoKg, double distanciaKm,
            LocalDateTime fechaHora) throws PedidoInvalidoException {
        setCodigo(codigo);
        setCliente(cliente);
        setDestino(destino);
        setPesoKg(pesoKg);
        setDistanciaKm(distanciaKm);
        setFechaHora(fechaHora);
        this.estado = PENDIENTE;
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

    public void setCliente(Cliente cliente) {
        if (cliente != null) {
            this.cliente = cliente;

        } else {
            throw new IllegalArgumentException("El cliente no puede estar vacío.");
        }
    }

    public void setDestino(String destino) throws PedidoInvalidoException {
        if (destino != null && !destino.trim().isEmpty()) {
            this.destino = destino;
        } else {
            throw new PedidoInvalidoException("El destino no puede estar vacío.");
        }
    }

    public void setPesoKg(double pesoKg) throws PedidoInvalidoException {
        if (pesoKg > 0) {
            this.pesoKg = pesoKg;
        } else {
            throw new PedidoInvalidoException("El peso debe ser un valor positivo.");
        }
    }

    public void setDistanciaKm(double distanciaKm) throws PedidoInvalidoException {
        if (distanciaKm > 0) {
            this.distanciaKm = distanciaKm;
        } else {
            throw new PedidoInvalidoException("La distancia debe ser un valor positivo.");
        }
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        if (fechaHora != null) {
            this.fechaHora = fechaHora;
        } else {
            throw new IllegalArgumentException("La fecha y hora no pueden estar vacías.");
        }
    }

    public abstract double calcularCosto(Vehiculo vehiculo);

    public void cambiarEstado(String nuevoEstado) {
        if (estado.equals(PENDIENTE) && (nuevoEstado.equals(EN_CAMINO) || nuevoEstado.equals(CANCELADO))) {
            this.estado = nuevoEstado;
        } else if (estado.equals(EN_CAMINO) && (nuevoEstado.equals(ENTREGADO))) {
            this.estado = nuevoEstado;
        } else {
            throw new EstadoInvalidoException("Transición inválida: " + estado + " -> " + nuevoEstado);
        }
    }

    public boolean esFragil() {
        return false;

    }
}
