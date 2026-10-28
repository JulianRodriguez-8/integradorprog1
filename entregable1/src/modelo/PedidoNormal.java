package modelo;
import excepciones.PedidoInvalidoException;
import java.time.LocalDateTime;

public class PedidoNormal extends Pedido {

    public PedidoNormal(String codigo, Cliente cliente, String destino, double pesoKg, double distanciaKm, LocalDateTime fechaHora) throws PedidoInvalidoException {
        super(codigo, cliente, destino, pesoKg, distanciaKm, fechaHora);
    }
    
    @Override 
    public double calcularCosto (Vehiculo vehiculo) {
        return COSTO_BASE+ vehiculo.getCostoPorKm() * getDistanciaKm();
    }
}