package com.mascotassanas;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Administra mascotas, citas y facturas.
 */
public class ClinicaVeterinaria {
	
	private final ArrayList<Mascota> mascotas;
	private final ArrayList<Cita> citas;
	private final ArrayList<Factura> facturas;
	
	private long siguienteCita;
	private long siguienteFactura;
	
	public ClinicaVeterinaria() {
		mascotas = new ArrayList<>();
		citas = new ArrayList<>();
		facturas = new ArrayList<>();
		
		siguienteCita = 1;
		siguienteFactura = 1;
	}
	
	public void registrarMascota(Mascota mascota) {
		
		if (mascota == null) {
			throw new DatoInvalidoException(
					"La mascota es obligatoria.");
		}
		
		Mascota existente = buscarMascotaPorId(mascota.getId());
		
		if (existente != null) {
			throw new DatoInvalidoException(
					"Ya existe una mascota con ese identificador.");
		}
		
		mascotas.add(mascota);
	}
	
	public Mascota buscarMascotaPorId(long id) {
		
		for (Mascota mascota : mascotas) {
			if (mascota.getId() == id) {
				return mascota;
			}
		}
		
		return null;
	}
	
	public Cita agendarCita(Mascota mascota,
			ServicioMedico servicio, LocalDateTime fecha) {
		
		if (!mascotas.contains(mascota)) {
			throw new DatoInvalidoException(
					"La mascota no está registrada.");	
		}
		
		Cita cita = new Cita(
				siguienteCita, mascota, servicio, fecha);
		
		siguienteCita++;
		citas.add(cita);
		
		return cita;
	}
	
	public Factura generarFactura(Cita cita) {
		
		if (!citas.contains(cita)) {
			throw new DatoInvalidoException(
					"La cita no pertenece a la clínica.");
		}
		
		Factura factura = new Factura(
				siguienteFactura, cita);
		
		siguienteFactura++;
		facturas.add(factura);
		
		return factura;
	}
	
	public List<Mascota> getMascotas() {
		return Collections.unmodifiableList(mascotas);
	}
	
	public List<Cita> getCitas() {
		return Collections.unmodifiableList(citas);
	}
	
	public List<Factura> getFacturas() {
		return Collections.unmodifiableList(facturas);
	}
}