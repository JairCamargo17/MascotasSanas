package com.mascotassanas;

import java.time.LocalDateTime;
import java.util.Scanner;

/**
 * Clase principal del sistema Mascotas Sanas.
 */
public class Main {
	
	private static final Scanner scanner = new Scanner(System.in);
	
	private static final ClinicaVeterinaria clinica = 
			new ClinicaVeterinaria();
	
	public static void main(String[] args) {
		
		int opcion;
		
		System.out.println("===================================");
		System.out.println("      CLINICIA MASCOTAS SANAS      ");
		System.out.println("===================================");
		
		do {
			mostrarMenu();
			opcion = leerEntero("Seleccione una opción: ");
			
			try {
				switch (opcion) {
				case 1:
					registrarMascota();
					break;
				
				case 2:
					mostrarMascotas();
					break;
					
				case 3:
					agendarCitaYFacturar();
					break;
					
				case 4:
					pruebaPolimorfismo();
					break;
					
				case 0:
					System.out.println(
							"Programa finalizado correctamente.");
					break;
					
				default:
					System.out.println(
							"Error: la opcion seleccionada no existe.");
				}
				
			} catch (DatoInvalidoException e) {
				System.out.println(
						"Dato rechazado: " + e.getMessage());
				
			} catch (Exception e) {
				System.out.println(
						"Se presentó un error controlado: "
						+ e.getMessage());
			}
			
		} while (opcion !=0);
		
		scanner.close();
	}
	
	private static void mostrarMenu() {
		
		System.out.println("\n---------- MENÚ ----------");
		System.out.println("1. Registrar mascota");
		System.out.println("2. Mostrar mascotas");
		System.out.println("3. Agendar cita y generar factura");
		System.out.println("4. Ejecutar prueba de polimorfismo");
		System.out.println("0. Salir");
		System.out.println("----------------------------");
	}
	
	private static void registrarMascota() {
		
		System.out.println("\n--- REGISTRO DE MASCOTA ---");
		
		long id = leerLong("Identificador de la mascota: ");
		
		System.out.print("Nombre de la mascota: ");
		String nombreMascota = scanner.nextLine();
		
		int edad;
		
		while (true) {
			
			edad = leerEntero("Edad de la mascota: ");
			
			if (edad < 0 || edad > 100) {
				System.out.println(
						"Error: la edad debe estar entre 1 y 100 años.");
			} else {
				break;
			}
		}
		
		System.out.print("Nombre del dueño: ");
		String nombreDueno = scanner.nextLine();
		
		System.out.print("Teléfono del dueño: ");
		String telefono = scanner.nextLine();
		
		System.out.print("Correo del dueño: ");
		String correo = scanner.nextLine();
		
		Dueno dueno = new Dueno(
				nombreDueno, telefono, correo);
		
		System.out.println("\nTipo de mascota: ");
		System.out.println("1. Perro");
		System.out.println("2. Gato");
		System.out.println("3. Ave");
		
		int tipo = leerEntero("Seleccione el tipo: ");
		
		Mascota mascota;
		
		switch (tipo) {
		case 1:
			System.out.print("Raza del perro: ");
			String razaPerro = scanner.nextLine();
			
			mascota = new Perro(
					id, nombreMascota, edad, dueno, razaPerro);
			break;
			
		case 2:
			System.out.print("Raza del gato: ");
			String razaGato = scanner.nextLine();
			
			mascota = new Gato(
					id, nombreMascota, edad, dueno, razaGato);
			break;
			
		case 3:
			System.out.print("Tipo de plumaje: ");
			String plumaje = scanner.nextLine();
			
			mascota = new Ave(
					id, nombreMascota, edad, dueno, plumaje);
			break;
			
		default:
			throw new DatoInvalidoException(
					"El tipo de mascota no existe.");
		}
		
		clinica.registrarMascota(mascota);
		
		System.out.println(
				"Mascota registrada correctamente.");
	}
	
	private static void mostrarMascotas() {
		
		System.out.println("\n--- MASCOTAS REGISTRADAS ---");
		
		if (clinica.getMascotas().isEmpty()) {
			System.out.println(
					"No hay mascotas registradas.");
			return;
		}
		
		for (Mascota mascota : clinica.getMascotas()) {
			System.out.println(mascota);
		}
	}
	
	private static void agendarCitaYFacturar() {
		
		if (clinica.getMascotas().isEmpty()) {
			System.out.println(
					"Primero debe registrar una mascota.");
			return;
		}
		
		mostrarMascotas();
		
		long id = leerLong(
				"\nIngrese el ID de la mascota: ");
		
		Mascota mascota = clinica.buscarMascotaPorId(id);
		
		if (mascota == null) {
			throw new DatoInvalidoException(
					"No se encontró una macota con ese ID.");
		}
		
		ServicioMedico servicio = seleccionarServicio();
		
		Cita cita = clinica.agendarCita(
				mascota, servicio, LocalDateTime.now());
		
		System.out.println("\nCita creada correctamente:");
		System.out.println(cita);
		
		Factura factura = clinica.generarFactura(cita);
		
		System.out.println(factura);
	}
	
	private static ServicioMedico seleccionarServicio() {
		
		System.out.println("\nServicios disponibles:");
		System.out.println("1. Vacunación - $80.000");
		System.out.println("2. Desparasitación - $50.000");
		System.out.println("3. Cirugía - $300.000");
		
		int opcion = leerEntero(
				"Seleccione el servicio: ");
		
		switch (opcion) {
		case 1:
			return new Vacunacion();
			
		case 2:
			return new Desparasitacion();
			
		case 3:
			return new Cirugia();
			
		default:
			throw new DatoInvalidoException(
					"El servicio seleccionado no existe.");
		}	
	}
	
	private static void pruebaPolimorfismo() {
		
		System.out.println(
				"\n--- PRUEBA DE POLIMORFISMO ---");
		
		Dueno dueno = new Dueno(
				"Ana Gómez",
				"3001234567",
				"ana.gomez@gmail.com");
		
		Mascota perro = new Perro(
				1001, "Max", 5, dueno, "Labrador");
		
		Mascota gato = new Gato(
				1002, "Luna", 3, dueno, "Siamés");
		
		Mascota ave = new Ave(
				1001, "Paco", 5, dueno, "Verde");
		
		ServicioMedico servicio = new Vacunacion();
		
		double precio = servicio.getCostoBase();
		
		System.out.printf(
				"Precio base de vacunación: $%,.0f%n", precio);
		
		System.out.printf(
				"Canino con recargo del 10%%: $%,.0f%n",
				perro.calcularCostoServicio(precio));
		
		System.out.printf(
				"Felino con recargo del 5%%: $%,.0f%n",
				gato.calcularCostoServicio(precio));
		
		System.out.printf(
				"Ave con descuento del 10%%: $%,.0f%n",
				ave.calcularCostoServicio(precio));
	}
	
	private static int leerEntero(String mensaje) {
		
		while (true) {
			System.out.print(mensaje);
			String entrada = scanner.nextLine();
			
			try {
				return Integer.parseInt(entrada);
				
			} catch (NumberFormatException e) {
				System.out.println(
						"Error: debe ingresar un número entero.");
			}
		}
	}
	
	private static long leerLong(String mensaje) {
		
		while (true) {
			System.out.print(mensaje);
			String entrada = scanner.nextLine();
			
			try {
				return Long.parseLong(entrada);
				
			} catch (NumberFormatException e) {
				System.out.println(
						"Error: debe ingresar un número válido.");
			}
		}
	}
}