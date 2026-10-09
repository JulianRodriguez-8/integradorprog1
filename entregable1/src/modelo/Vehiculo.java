public class Vehiculo{
    private string patente;
    private double capacidadCarga;
    private double velocidadPromedio;
    private double costoPorKm;

    public Vehiculo(string patente, double capacidadCarga, double velocidadPromedio, double costoPorKm) {
        this.patente = patente;
        this.capacidadCarga = capacidadCarga;
        this.velocidadPromedio = velocidadPromedio;
        this.costoPorKm = costoPorKm;
    }
}