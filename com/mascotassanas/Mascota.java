package com.mascotassanas;

/**
 * Clase que contiene la información común de todas las mascotas.
 */
public abstract class Mascota {
	
	private final long id;
	private String nombre;
	private int edad;
	private Dueno dueno;
	
	public Mascota(long id, String nombre, int edad, Dueno dueno) {
		
		if (id <= 0) {
			throw new DatoInvalidoException(
					"El identificador debe ser mayor que cero.");
		}
		
		this.id = id;
		setNombre(nombre);
		setEdad(edad);
		setDueno(dueno);
	}
	
	public long getId() {
		return id;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public int getEdad() {
		return edad;
	}
	
	public Dueno getDueno() {
		return dueno;
	}
	
	public void setNombre(String nombre) {
		this.nombre = Validacion.validarTexto(
				nombre, "El nombre de la mascota");
	}
	
	public void setEdad(int edad) {
		this.edad = Validacion.validarEdad(edad);
	}
	
	public void setDueno(Dueno dueno) {
		
		if (dueno == null) {
			throw new DatoInvalidoException(
					"El dueño de la mascota es obligatorio.");
		}
		
		this.dueno = dueno;
	}
	
	public abstract String getEspecie();
	
	public abstract String getRasgoEspecifico();
	
	protected abstract double getFactorAjuste();
	
	/**
	 * Aplica el facto definido por cada especie.
	 */
	public final double calcularCostoServicio(double costoBase) {
		
		Validacion.validarCosto(costoBase);
		
		double total = costoBase * getFactorAjuste();
		
		return Math.round(total * 100.0) / 100.0;
	}
	
	@Override
	public String toString() {
		return id + " - " + nombre
				+ " | Especie: " + getEspecie()
				+ " | Edad: " + edad
				+ " | " + getRasgoEspecifico()
				+ " | Dueño: " + dueno.getNombre();
	}

}