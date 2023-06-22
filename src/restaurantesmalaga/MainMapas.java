package restaurantesmalaga;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import restaurantesmalaga.model.Restaurante;

public class MainMapas {
	
	
	public static boolean mostrarMapa() {
		
		return false;
	}
	
	private static final String RUTA_FICHERO = "otros_restaurantes.txt";
	
	
	
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
			List<Restaurante> listRest = MainRestaurante.cargarRestaurantes(lineas);
			
			
			// Creación de Mapa a partir de la lista de Restaurantes.
			// Map<k, v> es una interface.
			Map<String, Restaurante> mapRest = null;
			// Clases que implementa esta interface: Treemap, LinkedHashMap, HashMap
			mapRest = new HashMap<>();
			
			// ForEach para leer y grabar en el HashMap la lista usando como clave el nombre del Rest.
			for (Restaurante r : listRest) {
				mapRest.put(r.getNombre(), r);
			}
			System.out.println(mapRest);
			// Mostramos algún restaurante cuya clave coincida.
			Restaurante rp = mapRest.get("La Parrilla");
			System.out.println(rp.toString()); 
			
			
			
			
		} else {
			System.out.println("NO EXISTE el fichero en esa ruta :( ");
		}

	}

	
	
	
	
	
	
	
	
	
	
	
	
	
//	==================================================================	
//	==================================================================	
//	==================================================================	
	
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
