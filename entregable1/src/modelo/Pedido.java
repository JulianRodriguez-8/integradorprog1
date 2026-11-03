package modelo;

import java.time.LocalDateTime;
import excepciones.EstadoInvalidoException;
import excepciones.PedidoInvalidoException;

/**
 * Clase abstracta que modela un pedido de envío en el sistema CityLink.
 * Es la raíz de la jerarquía compuesta por {@link PedidoNormal}, {@link PedidoUrgente}
 * y {@link PedidoFragil}. Implementa las interfaces {@link Rastreable} y {@link Facturable}.
 */
public abstract class Pedido implements Rastreable, Facturable {
    /** Estado inicial del pedido al ser creado. */
    public static final String PENDIENTE = "PENDIENTE";
    /** Estado cuando el pedido se encuentra en camino a su destino. */
    public static final String EN_CAMINO = "EN_CAMINO";
    /** Estado cuando el pedido ha sido entregado exitosamente. */
    public static final String ENTREGADO = "ENTREGADO";
    /** Estado cuando el pedido ha sido cancelado. */
    public static final String CANCELADO = "CANCELADO";
    /** Costo base estipulado para cualquier envío. */
    public static final double COSTO_BASE = 1000;

    private String codigo;
    private Cliente cliente;
    private String destino;
    private double pesoKg;
    private double distanciaKm;
    private LocalDateTime fechaHora;
    private String estado;
    private Vehiculo vehiculoAsignado;

    /**
     * Crea un pedido validando cada dato a través de los setters correspondientes.
     * Su estado inicial se establece como {@link #PENDIENTE}.
     * 
     * @param codigo      código único del pedido, no puede estar vacío
     * @param cliente     cliente que realiza el pedido, no puede ser nulo
     * @param destino     destino o zona de entrega, no puede estar vacío
     * @param pesoKg      peso en kilogramos, debe ser mayor a 0
     * @param distanciaKm distancia en kilómetros, debe ser mayor a 0
     * @param fechaHora   fecha y hora de ingreso del pedido, no puede ser nula
     * @throws PedidoInvalidoException si el destino, peso o distancia son inválidos
     */
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

    /** @return el código único del pedido */
    public String getCodigo() {
        return codigo;
    }

    /** @return el cliente asociado al pedido */
    public Cliente getCliente() {
        return cliente;
    }

    /** @return el destino o zona de entrega */
    public String getDestino() {
        return destino;
    }

    /** @return el peso del pedido en kilogramos */
    public double getPesoKg() {
        return pesoKg;
    }

    /** @return la distancia de entrega en kilómetros */
    public double getDistanciaKm() {
        return distanciaKm;
    }

    /** @return la fecha y hora de ingreso del pedido */
    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    /** @return el estado actual del pedido */
    public String getEstado() {
        return estado;
    }

    /** @return el vehículo asignado para el transporte del pedido */
    public Vehiculo getVehiculoAsignado() {
        return vehiculoAsignado;
    }

    /**
     * Cambia el código del pedido.
     * 
     * @param codigo nuevo código, no puede estar vacío
     * @throws IllegalArgumentException si el código es nulo o está vacío
     */
    public void setCodigo(String codigo) {
        if (codigo != null && !codigo.trim().isEmpty()) {
            this.codigo = codigo;
        } else {
            throw new IllegalArgumentException("El código no puede estar vacío.");
        }
    }

    /**
     * Cambia el cliente asociado al pedido.
     * 
     * @param cliente nuevo cliente, no puede ser nulo
     * @throws IllegalArgumentException si el cliente es nulo
     */
    public void setCliente(Cliente cliente) {
        if (cliente != null) {
            this.cliente = cliente;
        } else {
            throw new IllegalArgumentException("El cliente no puede estar vacío.");
        }
    }

    /**
     * Cambia el destino del pedido.
     * 
     * @param destino nuevo destino, no puede estar vacío
     * @throws PedidoInvalidoException si el destino es nulo o está vacío
     */
    public void setDestino(String destino) throws PedidoInvalidoException {
        if (destino != null && !destino.trim().isEmpty()) {
            this.destino = destino;
        } else {
            throw new PedidoInvalidoException("El destino no puede estar vacío.");
        }
    }

    /**
     * Cambia el peso en kilogramos del pedido.
     * 
     * @param pesoKg nuevo peso, debe ser mayor a 0
     * @throws PedidoInvalidoException si el peso no es positivo
     */
    public void setPesoKg(double pesoKg) throws PedidoInvalidoException {
        if (pesoKg > 0) {
            this.pesoKg = pesoKg;
        } else {
            throw new PedidoInvalidoException("El peso debe ser un valor positivo.");
        }
    }

    /**
     * Cambia la distancia en kilómetros del pedido.
     * 
     * @param distanciaKm nueva distancia, debe ser mayor a 0
     * @throws PedidoInvalidoException si la distancia no es positiva
     */
    public void setDistanciaKm(double distanciaKm) throws PedidoInvalidoException {
        if (distanciaKm > 0) {
            this.distanciaKm = distanciaKm;
        } else {
            throw new PedidoInvalidoException("La distancia debe ser un valor positivo.");
        }
    }

    /**
     * Cambia la fecha y hora de ingreso del pedido.
     * 
     * @param fechaHora nueva fecha y hora, no puede ser nula
     * @throws IllegalArgumentException si la fecha y hora es nula
     */
    public void setFechaHora(LocalDateTime fechaHora) {
        if (fechaHora != null) {
            this.fechaHora = fechaHora;
        } else {
            throw new IllegalArgumentException("La fecha y hora no pueden estar vacías.");
        }
    }

    /**
     * Establece el vehículo asignado para el transporte del pedido.
     * 
     * @param vehiculoAsignado vehículo que transportará el pedido
     */
    public void setVehiculoAsignado(Vehiculo vehiculoAsignado) {
        this.vehiculoAsignado = vehiculoAsignado;
    }

    /**
     * Método abstracto para calcular el costo del envío según las especificaciones
     * particulares de cada tipo de pedido y las características del vehículo.
     * 
     * @param vehiculo vehículo encargado de realizar el transporte
     * @return el costo total calculado del envío
     */
    public abstract double calcularCosto(Vehiculo vehiculo);

    /**
     * Cambia el estado del pedido validando las reglas de negocio y transiciones permitidas:
     * - Desde PENDIENTE se puede pasar a EN_CAMINO o CANCELADO.
     * - Desde EN_CAMINO se puede pasar a ENTREGADO.
     * 
     * @param nuevoEstado el nuevo estado al que se desea transicionar
     * @throws EstadoInvalidoException si la transición de estado no está permitida
     */
    public void cambiarEstado(String nuevoEstado) {
        if (estado.equals(PENDIENTE) && (nuevoEstado.equals(EN_CAMINO) || nuevoEstado.equals(CANCELADO))) {
            this.estado = nuevoEstado;
        } else if (estado.equals(EN_CAMINO) && (nuevoEstado.equals(ENTREGADO))) {
            this.estado = nuevoEstado;
        } else {
            throw new EstadoInvalidoException("Transición inválida: " + estado + " -> " + nuevoEstado);
        }
    }

    /**
     * Indica si el pedido es de tipo frágil. Por defecto retorna falso, 
     * siendo redefinido en {@link PedidoFragil}.
     * 
     * @return {@code false} por defecto
     */
    public boolean esFragil() {
        return false;
    }

    /**
     * Obtiene el estado actual del pedido implementando la interfaz {@link Rastreable}.
     * 
     * @return cadena con el código y estado actual del pedido
     */
    @Override
    public String obtenerEstadoActual() {
        return "pedido: " + codigo + ", estado: " + estado;
    }

    /**
     * Calcula el importe total del pedido implementando la interfaz {@link Facturable}.
     * Si no tiene vehículo asignado retorna 0, de lo contrario calcula el costo usando el vehículo.
     * 
     * @return el importe monetario del pedido
     */
    @Override
    public double calcularImporte() {
        if (vehiculoAsignado == null) {
            return 0;
        } else {
            return calcularCosto(vehiculoAsignado);
        }
    }

    /**
     * Devuelve una representación en texto del pedido con sus datos principales.
     * 
     * @return cadena formateada con la información del pedido
     */
    @Override
    public String toString() {
        return getClass().getSimpleName() + " " + codigo + " [" + cliente.getNombre()
                + " -> " + destino + ", " + pesoKg + " kg, " + distanciaKm + " km, " + estado + "]";
    }
}