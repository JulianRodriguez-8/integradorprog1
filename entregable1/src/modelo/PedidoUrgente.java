package modelo;
import excepciones.PedidoInvalidoException;
import java.time.LocalDateTime;
public class PedidoUrgente extends Pedido {
    public PedidoUrgente(String codigo, Cliente cliente, String destino, double pesoKg, double distanciaKm, LocalDateTime fechaHora) throws PedidoInvalidoException {
        super(codigo, cliente, destino, pesoKg, distanciaKm, fechaHora);
        
    }

    @Override
    public double calcularCosto(Vehiculo vehiculo) {
        double total = COSTO_BASE + vehiculo.getCostoPorKm() * getDistanciaKm();
        return total+total*0.5; 
    }
}