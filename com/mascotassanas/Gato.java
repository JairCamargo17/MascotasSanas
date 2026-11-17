package com.mascotassanas;

/**
 * Representa la especie felina.
 */
public class Gato extends Mascota {
	
	private String raza;
	
	public Gato(long id, String nombre, int edad,
			Dueno dueno, String raza) {
		
		super(id, nombre, edad, dueno);
		setRaza(raza);
	}
	
	public String getRaza() {
		return raza;
	}
	
	public void setRaza(String raza) {
		this.raza = Validacion.validarTexto(raza, "La raza");
	}
	
	@Override
	public String getEspecie() {
		return "Felino";
	}
	
	@Override
	public String getRasgoEspecifico() {
		return "Raza: " + raza;
	}
	
	@Override
	protected double getFactorAjuste() {
		return 1.05;
	}

}