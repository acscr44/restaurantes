package restaurantesmalaga;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import restaurantesmalaga.model.Restaurante;

public class MainMapas_val {
	
	
	private static final String RUTA_FICHERO = "otros_restaurantes.txt";
	
	
	public static Map<String, List<Restaurante>> crearMapRestaurantePorBarrios (List<Restaurante> lr){
		Map<String, List<Restaurante>> mapa = new HashMap<>();
		
		// recorro la lista
			// si el barrio ya está en el mapa 
				// añado restaurante a esa lista
			// si no, creo lista nueva y add ese restaurante
		for (Restaurante r : lr)
		{
			// Lista auxiliar en la que se identifican los barrios que ya se han incorporado.
			List<Restaurante> lrb = mapa.get(r.getBarrio());
			if(lrb != null) {
				System.out.println("ya existen restaurantes con ese barrio");
				// Si el barrio ya existe en la lrb, solo queda añadir el siguiente restaurante. 
				lrb.add(r);
			} else {
				// Lista nueva que va registrando un restaurante nuevo de ese barrio concreto, por 
				// el cual después haremos un put con el primer restaurante que inicia la lista.
				List<Restaurante> lnueva = new ArrayList<>();
				lnueva.add(r);
				mapa.put(r.getBarrio(), lnueva);
			}
		}
		
		return mapa;	
	}
	
	/*
	 * Hacer un método con el método mapa___restaurante de ayer que nos devuelva los más caros de cada barrio. 
	 * Leida esa estructura obtengamos una lista con el más caro de cada barrio. 
	 */
	public static List<Restaurante> getRestauranteMasCarosPorBarrio(Map<String, List<Restaurante>> mapaR){
		List<Restaurante> listaCaros = null;
		// TODO recorrer el mapa por barrios y quedarme con el más caro de cada barrio.
		
		listaCaros = new ArrayList<>();
		// barrios == clavesMapaR
		Set<String> barrios = mapaR.keySet(); 
		for(String key : barrios) {
			// Obtenemos el value del mapa para esa key.
			List<Restaurante> lrb = mapaR.get(key);
			// Obtener el restaurante más caro de esa lista.
			Restaurante rMasCaro = obtenerMasCaro(lrb);
			listaCaros.add(rMasCaro);
		}
		
		
		return listaCaros;
	}
	
	private static Restaurante obtenerMasCaro(List<Restaurante> lrb) {
		Restaurante rMasCaro = null;
		float precioMayor = 0;
		for (Restaurante r : lrb) {
			if(r.getPrecioMedio()>precioMayor) {
				rMasCaro = r;
				precioMayor = r.getPrecioMedio();
			}
		}
		return rMasCaro;
	}

	@SuppressWarnings("unused")
	private static Restaurante obtenerMasCaro2(List<Restaurante> lrb) {
		Restaurante rMasCaro = null;
		// Primero realizamos una ordenación por el método compareTo que hicimos (@override)  
		Collections.sort(lrb);
		// Cogemos el último
		rMasCaro = lrb.get(lrb.size()-1);
		return rMasCaro;
	}
	
	@SuppressWarnings("unused")
	private static Restaurante obtenerMasCaro_lambda(List<Restaurante> lrb) {
		Restaurante rMasCaro = null;
		
		
		return rMasCaro;
	}
	

	public static void mostrarMapa(Map<String, List<Restaurante>> mapaRestaurantesPorBarrios) {
		// Recorrer el mapa consiste en obtener todas las claves (keySet: las keys de
		// los mapas no se pueden repetir.)
		Set<String> clavesMapa = mapaRestaurantesPorBarrios.keySet();
		for (String barrio : clavesMapa) {
			// El metodo Map<k, v>.get() devuelve el valor para esa clave
			List<Restaurante> lrb = mapaRestaurantesPorBarrios.get(barrio);
			System.out.println("BARRIO = " + barrio);
			for (Restaurante rb : lrb) {
				System.out.println(rb.toString());
			}
		}
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
			List<Restaurante> listRest = MainRestaurante.cargarRestaurantes(lineas);
			
	
			// ======================================= Método mapaRestaurantePorBarrios ===========================
			Map<String, List<Restaurante>> mapaRestPorBarrios = null;
			
			mapaRestPorBarrios = crearMapRestaurantePorBarrios(listRest);
			
			mostrarMapa(mapaRestPorBarrios);			
			// ======================================= Método getRestauranteMasCarosPorBarrio ===========================
			
			System.out.println("\nLista de los restaurantes más caros por barrio: ");
			for(Restaurante r : getRestauranteMasCarosPorBarrio(mapaRestPorBarrios)) {
				System.out.println(r.toString());
			}
			
			
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
