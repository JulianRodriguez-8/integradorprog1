package modelo;

/**
 * Representa un registro (ticket) de una acción realizada en el sistema.
 * Se almacena en una Pila ({@code ArrayDeque}) para mantener el historial y poder
 * deshacer operaciones en orden inverso (LIFO).
 * 
 * @version 2.0
 * @author Grupo CityLink
 */
public class Operacion {

    private String tipoAccion;
    private Pedido pedidoAfectado;
    private String estadoAnterior;
    private Repartidor repartidorAsignado;

    /**
     * Crea un nuevo registro de operación con los datos necesarios para revertirla.
     * 
     * @param tipoAccion         identificador del tipo de acción realizada (ej. "ALTA_PEDIDO", "CAMBIO_ESTADO")
     * @param pedidoAfectado     pedido involucrado en la operación
     * @param estadoAnterior     estado que tenía el pedido antes de la acción (puede ser {@code null} si es un alta)
     * @param repartidorAsignado repartidor involucrado en la asignación (puede ser {@code null} si no aplica)
     */
    public Operacion(String tipoAccion, Pedido pedidoAfectado, String estadoAnterior, Repartidor repartidorAsignado) {
        this.tipoAccion = tipoAccion;
        this.pedidoAfectado = pedidoAfectado;
        this.estadoAnterior = estadoAnterior;
        this.repartidorAsignado = repartidorAsignado;
    }

    /** @return el tipo de acción registrada en la operación */
    public String getTipoAccion() {
        return tipoAccion;
    }

    /** @return el pedido afectado por la operación */
    public Pedido getPedidoAfectado() {
        return pedidoAfectado;
    }

    /** @return el estado previo del pedido antes de ejecutarse la operación, o {@code null} si no aplica */
    public String getEstadoAnterior() {
        return estadoAnterior;
    }

    /** @return el repartidor asignado durante la operación, o {@code null} si no aplica */
    public Repartidor getRepartidorAsignado() {
        return repartidorAsignado;
    }
}