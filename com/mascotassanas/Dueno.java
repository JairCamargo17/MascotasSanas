package com.mascotassanas;

/**
 * Representa al propietario de una mascota.
 */
public class Dueno {
	
	private String nombre;
	private String telefono;
	private String correo;
	
	public Dueno(String nombre, String telefono, String correo) {
		setNombre(nombre);
		setTelefono(telefono);
		setCorreo(correo);
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getTelefono() {
		return telefono;
	}
	
	public String getCorreo() {
		return correo;
	}
	
	public void setNombre(String nombre) {
		this.nombre = Validacion.validarTexto(
				nombre, "El nombre del dueño");
	}
	
	public void setTelefono(String telefono) {
		this.telefono = Validacion.validarTexto(
				telefono, "El teléfono");
	}
	
	public void setCorreo(String correo) {
		this.correo = Validacion.validarCorreo(correo);
	}
	
	@Override
	public String toString() {
		return nombre + " - " + correo;
	}
}