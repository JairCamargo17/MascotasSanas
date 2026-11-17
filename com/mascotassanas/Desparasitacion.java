package com.mascotassanas;

/**
 * Servicio de desparasitación.
 */
public class Desparasitacion implements ServicioMedico {
	
	private static final double COSTO_BASE = 50000.0;
	
	@Override
	public String getNombre() {
		return "Desparasitación";
	}
	
	@Override
	public double getCostoBase() {
		return COSTO_BASE;
	}
}