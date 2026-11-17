package com.mascotassanas;

/**
 * Calcula la factura a partir de una cita.
 */
public class Factura {
	
	private final long id;
	private final Cita cita;
	private final double subtotal;
	private final double ajuste;
	private final double total;
	
	public Factura(long id, Cita cita) {
		
		if (id <= 0) {
			throw new DatoInvalidoException(
					"El identificador de la factura no es válido.");
		}
		
		if (cita == null) {
			throw new DatoInvalidoException(
					"La cita es obligatoria.");
		}
		
		this.id = id;
		this.cita = cita;
		this.subtotal = cita.getServicio().getCostoBase();
		
		/*
		 * Aquí ocurre el polimorfismo:
		 * cada especie aplica su propio factor.
		 */
		this.total = cita.getMascota().calcularCostoServicio(subtotal);
		
		this.ajuste = total - subtotal;
	}
	
	public long getId() {
		return id;
	}
	
	public Cita getCita() {
		return cita;
	}
	
	public double getSubtotal() {
		return subtotal;
	}
	
	public double getAjuste() {
		return ajuste;
	}
	
	public double getTotal() {
		return total;
	}
	
	@Override
	public String toString() {
		
		String tipoAjuste;
		
		if (ajuste > 0) {
			tipoAjuste = "Recargo";
		} else if (ajuste < 0) {
			tipoAjuste = "Descuento";
		} else {
			tipoAjuste = "Sin ajuste";
		}
		
		return String.format(
				"%n============ FACTURA ============%n"
				+ "Número: %d%n"
				+ "Mascota: %s%n"
				+ "Especie: %s%n"
				+ "Dueño: %s%n"
				+ "Servicio: %s%n"
				+ "Subtotal: $%,.0f%n"
				+ "%s: $%,.0f%n"
				+ "TOTAL: $%,.0f%n"
				+ "=================================%n",
				id,
				cita.getMascota().getNombre(),
				cita.getMascota().getEspecie(),
				cita.getMascota().getDueno().getNombre(),
				cita.getServicio().getNombre(),
				subtotal,
				tipoAjuste,
				Math.abs(ajuste),
				total);
	}
}