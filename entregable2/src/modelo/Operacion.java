package modelo;

/**
 * Representa un registro (ticket) de una acción realizada en el sistema.
 * Sirve para guardar el historial en una Pila y poder deshacer operaciones.
 * 
 * @version 2.0
 * @author Grupo CityLink
 */
public class Operacion {

    // ALTA_PEDIDO ATENDER_PEDIDO CAMBIO_ESTADO
    private String tipoAccion;

    private Pedido pedidoAfectado;
    private String estadoAnterior;
    private Repartidor repartidorAsignado; // Guarda a quién se le asignó para poder quitárselo

    /**
     * Constructor para guardar los detalles de la operación.
     */
    public Operacion(String tipoAccion, Pedido pedidoAfectado, String estadoAnterior, Repartidor repartidorAsignado) {
        this.tipoAccion = tipoAccion;
        this.pedidoAfectado = pedidoAfectado;
        this.estadoAnterior = estadoAnterior;
        this.repartidorAsignado = repartidorAsignado;
    }

    public String getTipoAccion() {
        return tipoAccion;
    }

    public Pedido getPedidoAfectado() {
        return pedidoAfectado;
    }

    public String getEstadoAnterior() {
        return estadoAnterior;
    }

    public Repartidor getRepartidorAsignado() {
        return repartidorAsignado;
    }
}