package modelo;

public abstract class Vehiculo {
    private String patente;
    private double capacidadKg;
    private double velocidadKmH;
    private double costoPorKm;

    public Vehiculo(String patente, double capacidadKg, double velocidadKmH, double costoPorKm) {
        setPatente(patente);
        setCapacidadKg(capacidadKg);
        setVelocidadKmH(velocidadKmH);
        setCostoPorKm(costoPorKm);
    }

    public String getPatente() {
        return patente;
    }

    public double getCapacidadKg() {
        return capacidadKg;
    }

    public double getVelocidadKmH() {
        return velocidadKmH;
    }

    public double getCostoPorKm() {
        return costoPorKm;
    }

    public void setPatente(String patente) {
        if (patente != null && !patente.trim().isEmpty()) {
            this.patente = patente;
        } else {
            throw new IllegalArgumentException("La patente no puede estar vacía.");
        }
    }

    public void setCapacidadKg(double capacidadKg) {
        if (capacidadKg > 0) {
            this.capacidadKg = capacidadKg;
        } else {
            throw new IllegalArgumentException("La capacidad debe ser un número positivo.");
        }
    }

    public void setVelocidadKmH(double velocidadKmH) {
        if (velocidadKmH > 0) {
            this.velocidadKmH = velocidadKmH;
        } else {
            throw new IllegalArgumentException("La velocidad debe ser un número positivo.");
        }
    }

    public void setCostoPorKm(double costoPorKm) {
        if (costoPorKm > 0) {
            this.costoPorKm = costoPorKm;
        } else {
            throw new IllegalArgumentException("El costo por km debe ser un número positivo.");
        }
    }

    // metodo abstracto
  public abstract double calcularTiempoEntrega(double km);

  public boolean puedeTransportar(Pedido pedido) {
    return pedido.getPesoKg() <= capacidadKg;

  }
  


}