package modelo;
import excepciones.PedidoInvalidoException;
import java.time.LocalDateTime;
public class PedidoFragil extends Pedido {
    public PedidoFragil(String codigo, Cliente cliente, String destino, double pesoKg, double distanciaKm, LocalDateTime fechaHora) throws PedidoInvalidoException {
        super(codigo, cliente, destino, pesoKg, distanciaKm, fechaHora);
    }

    @Override
    public double calcularCosto(Vehiculo vehiculo) {
        return COSTO_BASE + vehiculo.getCostoPorKm() * getDistanciaKm() +800;
    }

    @Override
    public boolean esFragil() {
        return true;
    }
    
}
