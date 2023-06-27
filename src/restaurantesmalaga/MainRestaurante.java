package restaurantesmalaga;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import java.util.List;

import java.util.Random;
import java.util.function.Supplier;



import restaurantesmalaga.model.ComparadorRestaurantes;
import restaurantesmalaga.model.Restaurante;

public class MainRestaurante {

//	private static final String RUTA_FICHERO = "restaurantes.txt";
	private static final String RUTA_FICHERO = "otros_restaurantes.txt";

	public static List<Restaurante> cargarRestaurantes(List<String> lineas) {
		List<Restaurante> lRestaurante = null;
		int numlinea = 0;
		Restaurante restauranteAux = null; // el restaurante en curso
		restauranteAux = new Restaurante(); // crea Restaurante vacio
		lRestaurante = new ArrayList<>();
		for (String linea : lineas) { // for each
			numlinea += 1;
			// Probar con módulo de 9 en lugar de un número que se incrementa.
			switch (numlinea) {
			case 1:
				restauranteAux.setNombre(linea);
				break;
			case 2:
				restauranteAux.setDireccion(linea);
				break;
			case 3:
				restauranteAux.setWeb(linea);
				break;
			case 4:
				// restauranteAux.setPrecioMedio(Float.parseFloat(linea));
				Supplier<Float> generaPrecioMedio = () -> {
					float numeroAleatorio = 0;
					Random random = new Random();
					numeroAleatorio = 10f + random.nextFloat(10f);
					return numeroAleatorio;
				};
				restauranteAux.setPrecioMedio(generaPrecioMedio.get());
				break;
			case 5:
				restauranteAux.setFichaGoogle(linea);
				break;
			case 6:
				restauranteAux.setLatitud(Float.parseFloat(linea));
				break;
			case 7:
				restauranteAux.setLongitud(Float.parseFloat(linea));
				break;
			case 8:
				restauranteAux.setBarrio(linea);
				break;
			case 9:
				String[] especialidades = linea.split(", "); // troceo esp1, esp2, esp3
				// paso de dato tipo Array a un dato tipo Lista
				List<String> lespecialidades = Arrays.asList(especialidades);
				restauranteAux.setEspecialidades(lespecialidades);
				// Añadimos el objeto auxiliar a la lista
				lRestaurante.add(restauranteAux);

				// El restaurante ha completado todos sus parámetros. El siguiente restaurante
				// comienza con el cont numlinea a 0.
				numlinea = 0;
				// Instaciamos un nuevo objeto Restaurante.
				restauranteAux = new Restaurante();
				break;
			}
			// lRestaurante es un tipo List "generico" pero a continuación hacemos una
			// conversión a un tipo específico de List.

		}
		return lRestaurante;
	}

	public static void main(String[] args) throws IOException {
		// TODO Cargar la lista de restaurantes del fichero
		File file = new File(RUTA_FICHERO);
		if (file.exists()) {
			System.out.println("FICHERO EXISTE!, a parsearlo :) ");

			// Parsear es inspeccionar, recorrer y clasificar el documento
			Path path = file.toPath();
			List<String> lineas = Files.readAllLines(path); // Nos devuelve una lista de lineas.

			// TODO Convertir cada línea asociada a restaurante en un registro de
			// restaurante.
			List<Restaurante> listRest = cargarRestaurantes(lineas);
			System.out.println("La lista tiene " + listRest.size() + " restaurantes. \n");

			// TODO Mostrar los datos del fichero cargado
			mostrarRestaurantes(listRest);

			// TODO Buscar un restaurante
			Restaurante restNuevo = new Restaurante();
			restNuevo.setNombre("McDonalds");
			restNuevo.setDireccion("McDonalds Plaza de la Marina");
			restNuevo.setWeb("www.mcdonalds.com");
			restNuevo.setFichaGoogle("https://goo.gl/maps/yzKMGxkYMzZBMPfD7");
			restNuevo.setLatitud(36.7313424f);
			restNuevo.setLongitud(-4.4578394f);
			restNuevo.setBarrio("centro");
			restNuevo.setEspecialidades(List.of("hamburguesas", "patatas fritas", "helados"));

			Restaurante r5 = listRest.get(4);
			boolean esta = buscarRestaurantes(listRest, r5);
			System.out.println("R5 está en la lista " + esta);
			esta = buscarRestaurantes(listRest, restNuevo);
			System.out.println("restNuevo está en la lista " + esta);

			// TODO Buscar por especialidad
			String especialidadBuscada = "    frutas".trim();
			List<Restaurante> listaPorEspecialidad = new ArrayList<Restaurante>();
			listaPorEspecialidad = buscarPorEspecialidad(listRest, especialidadBuscada);
			if (!listaPorEspecialidad.isEmpty()) {
				System.out.println("Los restaurantes que incluyen " + especialidadBuscada + " son: ");
				for (Restaurante elem : listaPorEspecialidad) {
					System.out.println("\t" + elem);
				}
			} else {
				System.out.println("No existen restaurantes para este criterio");
			}

			// TODO Buscar por nombre
			String nombreBuscado = " KFC ".trim();
			List<Restaurante> listaPorNombre = new ArrayList<Restaurante>();
			listaPorNombre = buscarPorNombre(listRest, nombreBuscado);
			if (!listaPorNombre.isEmpty()) {
				System.out.println("Los restaurantes de nombre " + nombreBuscado + " son: ");
				mostrarRestaurantesLambda(listaPorNombre);
//				for(Restaurante elem : listaPorNombre) {
//					System.out.println("\t" + elem);
//				}
			} else {
				System.out.println("No existen restaurantes para este criterio");
			}

			// TODO Buscar por barrio
			String barrioBuscado = " extraradio".trim();
			List<Restaurante> listaPorBarrio = new ArrayList<Restaurante>();
			listaPorBarrio = buscarPorBarrio(listRest, barrioBuscado);

			if (!listaPorBarrio.isEmpty()) {
				System.out.println("Los restaurantes que se situan en " + barrioBuscado + " son: ");
				for (Restaurante elem : listaPorBarrio) {
					System.out.println("\t" + elem);
				}
			} else {
				System.out.println("No existen restaurantes para este criterio");
			}
			buscarPorBarrioConStream(listRest, barrioBuscado);

			// TODO Buscar por rango de precio
			System.out.println("Los restaurantes NO ORDENADOS que tienen un precio según su criterio son:");
			for (Restaurante elem : buscarPorPrecioMedio(listRest, 10.0f)) {
				// for (Restaurante elem : listRest) {
				// if(elem.getPrecioMedio()>=10.0f && elem.getPrecioMedio()<=15.0f) {
				System.out.println("\t" + elem.getPrecioMedio());
				// }
			}
			// Orden NATURAL: método sort() para interface Comparable<> y método (Override) compareTo()
			Collections.sort(listRest);  // NATURAL
			System.out.println("Los restaurantes ORDENADOS [NATURAL] que tienen un precio según su criterio son:");
			for (Restaurante elem : buscarPorPrecioMedio(listRest, 10.0f)) {
				// for (Restaurante elem : listRest) {
				// if(elem.getPrecioMedio()>=10.0f && elem.getPrecioMedio()<=15.0f) {
				System.out.println("\t" + elem.getPrecioMedio());
				// }
			}
			// Orden TOTAL: método sort() para interface Comparator<> y método (Override) compare()
			System.out.println("Los restaurantes ORDENADOS [TOTAL] que tienen un precio según su criterio son:");
			ComparadorRestaurantes cr = new ComparadorRestaurantes();
			Collections.sort(listRest, cr);  // TOTAL
			for (Restaurante elem : buscarPorPrecioMedio(listRest, 10.0f)) {
				// for (Restaurante elem : listRest) {
				// if(elem.getPrecioMedio()>=10.0f && elem.getPrecioMedio()<=15.0f) {
				System.out.println("\t" + elem.getNombre() + "  -  " + elem.getPrecioMedio());
				// }
			}

		} else {
			System.out.println("NO EXISTE el fichero en esa ruta :( ");
		}

	}

	public static void mostrarRestaurantes(List<Restaurante> listRest) {
		System.out.println("Mostrando resturantes ...");
		for (Restaurante r : listRest) {
			// Mientras no se incluya el método toString() en la clase Restaurante se
			// obtendrá el nombre_canónico@dirección_memoria.
			System.out.println(r);
			System.out.println(r.toString());
		}
	}

	public static void mostrarRestaurantesLambda(List<Restaurante> listRest) {
		listRest.forEach(r -> System.out.println(r.toString()));
	}

	public static boolean buscarRestaurantes(List<Restaurante> listRest, Restaurante restauranteBuscado) {
		boolean estaRestaurante = false;
		int pos_actual = 0;
		int longitud = listRest.size();
		Restaurante restauranteAux = null;
		while (pos_actual < longitud && !estaRestaurante) { // Quede lista y restaurante==false
			restauranteAux = listRest.get(pos_actual);
			estaRestaurante = restauranteAux.equals(restauranteBuscado);
			pos_actual += 1;
		}
		System.out.println("Mostrando resturantes ...");
		return estaRestaurante;
	}

	public static void mostrarResultados(List<Restaurante> listRest) {
		System.out.println("Mostrando resultados ...");
		for (Restaurante r : listRest) {
			// Mientras no se incluya el método toString() en la clase Restaurante se
			// obtendrá el nombre_canónico@dirección_memoria.
			if (!listRest.isEmpty()) {
				System.out.println(r.toString());
			}
		}
	}

	public static List<Restaurante> buscarPorEspecialidad(List<Restaurante> listRest, String especialidadBuscada) {
		List<Restaurante> restParaEsaEspecialidad = null;
		Restaurante restauranteAux = null;

		restParaEsaEspecialidad = new ArrayList<Restaurante>();
		int pos_actual = 0;
		int longitud = listRest.size();
		while (pos_actual < longitud) {
			restauranteAux = listRest.get(pos_actual);
			if (restauranteAux.getEspecialidades().contains(especialidadBuscada)) {
				restParaEsaEspecialidad.add(restauranteAux);
			}
			pos_actual += 1;
		}
		return restParaEsaEspecialidad;
	}

	public static List<Restaurante> buscarPorNombre(List<Restaurante> listRest, String nombreBuscado) {
		List<Restaurante> restConEseNombre = new ArrayList<>(); // <> Operador diamante: infiere el tipo de objeto desde
																// el tipo de la asignación.
		Restaurante restauranteAux = null;
		int pos_actual = 0;
		int longitud = listRest.size();
		boolean estaNombre;

		while (pos_actual < longitud) {
			estaNombre = false;
			restauranteAux = listRest.get(pos_actual);
			estaNombre = restauranteAux.getNombre().equals(nombreBuscado);
			if (estaNombre) {
				restConEseNombre.add(restauranteAux);
			}
			pos_actual += 1;
		}
		return restConEseNombre;
	}

	public static List<Restaurante> buscarPorBarrio(List<Restaurante> listRest, String barrioBuscado) {
		List<Restaurante> restEnEseBarrio = new ArrayList<Restaurante>();
		Restaurante restauranteAux = null;
		int pos_actual = 0;
		int longitud = listRest.size();
		boolean estaBarrio;

		while (pos_actual < longitud) {
			estaBarrio = false;
			restauranteAux = listRest.get(pos_actual);
			estaBarrio = restauranteAux.getBarrio().equalsIgnoreCase(barrioBuscado);
			if (estaBarrio) {
				restEnEseBarrio.add(restauranteAux);
			}
			pos_actual += 1;
		}
		return restEnEseBarrio;
	}

	public static List<Restaurante> buscarPorBarrioConStream(List<Restaurante> listRest, String barrioBuscado) {
		List<Restaurante> listaRestBarrios = null;
		listaRestBarrios = listRest;

		listaRestBarrios.stream().count(); // count() es una operación terminal, todo stream debe cerrar con alguna
											// operación terminal
		// Consultar la documentación para más operaciones terminales de la clase Stream

		// listaRestBarrios.stream().filter(r -> { función predicado: test } );
		// filter() no es una operación terminal, por lo que el stream necesitará un
		// cierre.
		listaRestBarrios.stream().filter(r -> {
			return r.getBarrio().contains(barrioBuscado);
		}).toList();
		// listaRestBarrios.stream().filter((Restaurante r) -> {return
		// r.getBarrio().equals(barrioBuscado);});
		return listaRestBarrios;
	}

	public static List<Restaurante> buscarPorPrecioMedio(List<Restaurante> listRest, float presupuesto) {
		List<Restaurante> lRestConPrecioMaximoBuscado = null;
		// lRestConPrecioMaximoBuscado = listRest;

		lRestConPrecioMaximoBuscado = listRest.stream().filter(r -> r.getPrecioMedio() > presupuesto)
				// .filter(r -> r.getPrecioMedio()>=presupuesto/1.15 &&
				// r.getPrecioMedio()<=presupuesto*1.02)
				.toList();

		return lRestConPrecioMaximoBuscado;

	}

}
