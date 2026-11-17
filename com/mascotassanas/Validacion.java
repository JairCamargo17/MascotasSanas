package com.mascotassanas;

/**
 * Contiene las validaciones utilizadas por las clases del sistema.
 */
public class Validacion {

	private Validacion() {
		//Evita que esta clase sea instanciada.
	}
	
	public static String validarTexto(String valor, String campo) {
		
		if (valor == null || valor.isBlank()) {
			throw new DatoInvalidoException(
					campo + " no puede estar vacío.");
		}
		
		return valor.trim();
	}
	
	public static int validarEdad(int edad) {
		
		if (edad < 1 || edad > 100) {
			throw new DatoInvalidoException(
					"La edad debe estar entre 1 y 100 años.");
		}
		
		return edad;
	}
	
	public static String validarCorreo(String correo) {
		
		correo = validarTexto(correo, "El correo");
		
		if (!correo.contains("@") || !correo.contains(".")) {
			throw new DatoInvalidoException(
					"El correo electronico no es válido.");
		}
		
		return correo;
	}
	
	public static double validarCosto(double costo) {
		
		if (costo <= 0) {
			throw new DatoInvalidoException(
					"El costo debe ser mayor que cero.");
		}
		
		return costo;
	}
}