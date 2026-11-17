package com.mascotassanas;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Relaciona una mascota con un servicio médico.
 */
public class Cita {
	
	private final long id;
	private final Mascota mascota;
	private final ServicioMedico servicio;
	private final LocalDateTime fecha;
	
	public Cita(long id, Mascota mascota,
			ServicioMedico servicio, LocalDateTime fecha) {
		
		if (id <= 0) {
			throw new DatoInvalidoException(
					"El identificador de la cita no es válido.");
		}
		
		if (mascota == null) {
			throw new DatoInvalidoException(
					"La mascota es obligatoria.");
		}
		
		if (servicio == null) {
			throw new DatoInvalidoException(
					"El servicio es obligatorio.");
		}
		
		if (fecha == null) {
			throw new DatoInvalidoException(
					"La fecha es obligatoria.");
		}
		
		this.id = id;
		this.mascota = mascota;
		this.servicio = servicio;
		this.fecha = fecha;
	}
	
	public long getId() {
		return id;
	}
	
	public Mascota getMascota() {
		return mascota;
	}
	
	public ServicioMedico getServicio() {
		return servicio;
	}
	
	public LocalDateTime getFecha() {
		return fecha;
	}
	
	@Override
	public String toString() {
		
		DateTimeFormatter formato =
				DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		
		return "Cita " + id
				+ " | Mascota: " + mascota.getNombre()
				+ " | Servicio: " + servicio.getNombre()
				+ " | Fecha: " + fecha.format(formato);
	}
}
