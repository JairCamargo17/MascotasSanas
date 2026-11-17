package com.mascotassanas;

/**
 * Servicio de cirugía.
 */
public class Cirugia implements ServicioMedico {
	
	private static final double COSTO_BASE = 300000.0;
	
	@Override
	public String getNombre() {
		return "Cirugía";
	}
	
	@Override
	public double getCostoBase() {
		return COSTO_BASE;
	}
}