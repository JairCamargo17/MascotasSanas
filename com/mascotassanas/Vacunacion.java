package com.mascotassanas;

/**
 * Servicio de vacunación.
 */
public class Vacunacion implements ServicioMedico {
	
	private static final double COSTO_BASE = 80000.0;
	
	@Override
	public String getNombre() {
		return "Vacunación";
	}
	
	@Override
	public double getCostoBase() {
		return COSTO_BASE;
	}
}