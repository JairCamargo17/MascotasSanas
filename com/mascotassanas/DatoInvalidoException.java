package com.mascotassanas;


/**
 * Excepción personalizada para los datos que no cumplen
 * las reglas del sistema.
 */
public class DatoInvalidoException extends IllegalArgumentException {
	
	private static final long serialVersionUID = 1L;

	public DatoInvalidoException(String  mensaje) {
		super(mensaje);
	}
}