package modelo;

/**
 * Vehículo de reparto. Es la raíz abstracta de la jerarquía formada por
 * {@link Bicicleta}, {@link Moto} y {@link Camioneta}. Cada subclase define
 * cómo calcula el tiempo de entrega.
 */
public abstract class Vehiculo {
    private String patente;
    private double capacidadKg;
    private double velocidadKmH;
    private double costoPorKm;

    /**
     * Crea un vehículo validando cada dato a través de los setters.
     *
     * @param patente      patente del vehículo, no puede estar vacía
     * @param capacidadKg  capacidad máxima de carga en kg, debe ser mayor a 0
     * @param velocidadKmH velocidad promedio en km/h, debe ser mayor a 0
     * @param costoPorKm   costo por kilómetro recorrido, debe ser mayor a 0
     * @throws IllegalArgumentException si algún dato es inválido
     */
    public Vehiculo(String patente, double capacidadKg, double velocidadKmH, double costoPorKm) {
        setPatente(patente);
        setCapacidadKg(capacidadKg);
        setVelocidadKmH(velocidadKmH);
        setCostoPorKm(costoPorKm);
    }

    /** @return la patente del vehículo */
    public String getPatente() {
        return patente;
    }

    /** @return la capacidad máxima de carga en kg */
    public double getCapacidadKg() {
        return capacidadKg;
    }

    /** @return la velocidad promedio en km/h */
    public double getVelocidadKmH() {
        return velocidadKmH;
    }

    /** @return el costo por kilómetro recorrido */
    public double getCostoPorKm() {
        return costoPorKm;
    }

    /**
     * Cambia la patente del vehículo.
     *
     * @param patente nueva patente, no puede ser nula ni vacía
     * @throws IllegalArgumentException si la patente es nula o está vacía
     */
    public void setPatente(String patente) {
        if (patente != null && !patente.trim().isEmpty()) {
            this.patente = patente;
        } else {
            throw new IllegalArgumentException("La patente no puede estar vacía.");
        }
    }

    /**
     * Cambia la capacidad máxima de carga.
     *
     * @param capacidadKg nueva capacidad en kg, debe ser mayor a 0
     * @throws IllegalArgumentException si la capacidad no es positiva
     */
    public void setCapacidadKg(double capacidadKg) {
        if (capacidadKg > 0) {
            this.capacidadKg = capacidadKg;
        } else {
            throw new IllegalArgumentException("La capacidad debe ser un número positivo.");
        }
    }

    /**
     * Cambia la velocidad promedio.
     *
     * @param velocidadKmH nueva velocidad en km/h, debe ser mayor a 0
     * @throws IllegalArgumentException si la velocidad no es positiva
     */
    public void setVelocidadKmH(double velocidadKmH) {
        if (velocidadKmH > 0) {
            this.velocidadKmH = velocidadKmH;
        } else {
            throw new IllegalArgumentException("La velocidad debe ser un número positivo.");
        }
    }

    /**
     * Cambia el costo por kilómetro.
     *
     * @param costoPorKm nuevo costo por km, debe ser mayor a 0
     * @throws IllegalArgumentException si el costo no es positivo
     */
    public void setCostoPorKm(double costoPorKm) {
        if (costoPorKm > 0) {
            this.costoPorKm = costoPorKm;
        } else {
            throw new IllegalArgumentException("El costo por km debe ser un número positivo.");
        }
    }

    /**
     * Calcula el tiempo estimado de entrega, incluyendo el tiempo extra propio
     * de cada tipo de vehículo. Lo implementa cada subclase.
     *
     * @param km distancia a recorrer en kilómetros
     * @return el tiempo estimado en minutos
     */
    // metodo abstracto
  public abstract double calcularTiempoEntrega(double km);

  /**
   * Indica si el vehículo puede transportar el pedido. La regla general es que
   * el peso del pedido no supere la capacidad del vehículo. Las subclases pueden
   * agregar restricciones (por ejemplo, {@link Bicicleta}).
   *
   * @param pedido pedido que se quiere transportar
   * @return {@code true} si el peso del pedido es menor o igual a la capacidad
   */
  public boolean puedeTransportar(Pedido pedido) {
    return pedido.getPesoKg() <= capacidadKg;
  }
}