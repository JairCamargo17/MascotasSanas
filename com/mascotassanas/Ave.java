package com.mascotassanas;

/**
 * Representa la especie de tipo de Ave.
 */
public class Ave extends Mascota {
	
	private String tipoPlumaje;
	
	public Ave(long id, String nombre, int edad,
			Dueno dueno, String tipoPlumaje) {
		
		super(id, nombre, edad, dueno);
		setTipoPlumaje(tipoPlumaje);
	}
	
	public String getTipoPlumaje() {
		return tipoPlumaje;
	}
	
	public void setTipoPlumaje(String tipoPlumaje) {
		this.tipoPlumaje = Validacion.validarTexto(
				tipoPlumaje, "El tipo de plumaje");
	}
	
	@Override
	public String getEspecie() {
		return "Ave";
	}
	
	@Override
	public String getRasgoEspecifico() {
		return "Plumaje: " + tipoPlumaje;
	}
	
	@Override
	protected double getFactorAjuste() {
		return 0.90;
	}

}
