package modelo;

public  abstract class Pedido {
    private String codigo;
    private String cliente;
    private String destino;
    private double pesoKg;
    private double distanciaKm;
    private LocalDateTime fechaHora;
    private String estado; // "pendiente", "enviado", "entregado"

    public Pedido(String codigo, String cliente, String destino, double pesoKg, double distanciaKm, LocalDateTime fechaHora, String estado) {
        this.codigo = codigo;
        this.cliente = cliente;
        this.destino = destino;
        this.pesoKg = pesoKg;
        this.distanciaKm = distanciaKm;
        this.fechaHora = fechaHora;
        this.estado = estado;
    }
 
    
}
