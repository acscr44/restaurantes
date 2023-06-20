package restaurantesmalaga;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import restaurantesmalaga.model.Restaurante;

public class MainRestaurante {

	private static final String RUTA_FICHERO = "restaurantes.txt";

	public static List<Restaurante> cargarRestaurantes(List<String> lineas) {
		List<Restaurante> lRestaurante = null;
		int numlinea = 0;
		Restaurante restauranteAux = null; // el restaurante en curso
		restauranteAux = new Restaurante(); // crea Restaurante vacio
		lRestaurante = new ArrayList<>();
		for (String linea : lineas) { // for each
			numlinea += 1;
			// Probar con módulo de 8 en lugar de un número que se incrementa.
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
				restauranteAux.setFichaGoogle(linea);
				break;
			case 5:
				restauranteAux.setLatitud(Float.parseFloat(linea));
				break;
			case 6:
				restauranteAux.setLongitud(Float.parseFloat(linea));
				break;
			case 7:
				restauranteAux.setBarrio(linea);
				break;
			case 8:
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
			restNuevo.setEspecialidades(List.of("hamburguesas","patatas fritas","helados"));
			
			
			Restaurante r5 = listRest.get(4);
			boolean esta = buscarRestaurantes(listRest, r5);
			System.out.println("R5 está en la lista " + esta);
			esta = buscarRestaurantes(listRest, restNuevo);
			System.out.println("restNuevo está en la lista " + esta);
			
			
			// TODO Buscar por especialidad
			Restaurante restNuevoEsp = new Restaurante();
			restNuevoEsp.setNombre("McDonalds");
			restNuevoEsp.setDireccion("McDonalds Plaza de la Marina");
			restNuevoEsp.setWeb("www.mcdonalds.com");
			restNuevoEsp.setFichaGoogle("https://goo.gl/maps/yzKMGxkYMzZBMPfD7");
			restNuevoEsp.setLatitud(36.7313424f);
			restNuevoEsp.setLongitud(-4.4578394f);
			restNuevoEsp.setBarrio("centro");
			restNuevoEsp.setEspecialidades(List.of("hamburguesas","patatas fritas","helados","frutas"));
			String especialidadBuscada = "    frutas".trim();
			List<Restaurante> listaPorEspecialidad = new ArrayList<Restaurante>();
			listaPorEspecialidad = buscarPorEspecialidad(listRest, especialidadBuscada);
			System.out.println("Los restaurantes que incluyen " + especialidadBuscada + " son: " + listaPorEspecialidad.toString());
			
			
			
			// TODO Buscar por nombre
			Restaurante restNuevoNombre = new Restaurante();
			restNuevoNombre.setNombre("McDonalds");
			restNuevoNombre.setDireccion("McDonalds Plaza de la Marina");
			restNuevoNombre.setWeb("www.mcdonalds.com");
			restNuevoNombre.setFichaGoogle("https://goo.gl/maps/yzKMGxkYMzZBMPfD7");
			restNuevoNombre.setLatitud(36.7313424f);
			restNuevoNombre.setLongitud(-4.4578394f);
			restNuevoNombre.setBarrio("centro");
			restNuevoNombre.setEspecialidades(List.of("hamburguesas","patatas fritas","helados", "frutas"));
			String nombreBuscado = " KFC ".trim();
			List<Restaurante> listaPorNombre = new ArrayList<Restaurante>();
			listaPorNombre = buscarPorNombre(listRest, nombreBuscado);
			if (!listaPorNombre.isEmpty()) {
				System.out.println("Los restaurantes que incluyen " + nombreBuscado + " son: ");
				for(Restaurante elem : listaPorNombre) {
					System.out.println("\t" + elem);
				}
			} else {
				System.out.println("No existen restaurantes para este criterio");
			}
			// TODO Buscar por barrio
			Restaurante restNuevoBarrio = new Restaurante();
			restNuevoBarrio.setNombre("McDonalds");
			restNuevoBarrio.setDireccion("McDonalds Plaza de la Marina");
			restNuevoBarrio.setWeb("www.mcdonalds.com");
			restNuevoBarrio.setFichaGoogle("https://goo.gl/maps/yzKMGxkYMzZBMPfD7");
			restNuevoBarrio.setLatitud(36.7313424f);
			restNuevoBarrio.setLongitud(-4.4578394f);
			restNuevoBarrio.setBarrio("centro");
			restNuevoBarrio.setEspecialidades(List.of("hamburguesas","patatas fritas","helados", "frutas"));
			String barrioBuscado = " extraradio".trim();
			List<Restaurante> listaPorBarrio = new ArrayList<Restaurante>();
			listaPorBarrio = buscarPorBarrio(listRest, barrioBuscado);
			System.out.println("Los restaurantes que incluyen " + barrioBuscado + " son: " + listaPorBarrio.toString()); 
			
//			for (String linea : lineas) { // for each
//				System.out.println(linea);
//			}
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

	public static boolean buscarRestaurantes(List<Restaurante> listRest, Restaurante restauranteBuscado) {
		boolean estaRestaurante = false;
		int pos_actual = 0;
		int longitud = listRest.size();
		Restaurante restauranteAux = null;
		while (pos_actual < longitud && !estaRestaurante) { // Quede lista y restaurante==false
			restauranteAux = listRest.get(pos_actual);
			estaRestaurante = restauranteAux.equals(restauranteBuscado);
			pos_actual+=1;
		}
		System.out.println("Mostrando resturantes ...");
		return estaRestaurante;
	}
	
	public static List<Restaurante> buscarPorEspecialidad(List<Restaurante> listRest, String especialidadBuscada) {
		List<Restaurante> restParaEsaEspecialidad = null ;
		Restaurante restauranteAux = null;
		
		restParaEsaEspecialidad = new ArrayList<Restaurante>();
		int pos_actual = 0;
		int longitud = listRest.size();
		while (pos_actual < longitud) {
			restauranteAux = listRest.get(pos_actual);
			if (restauranteAux.getEspecialidades().contains(especialidadBuscada)) {
				restParaEsaEspecialidad.add(restauranteAux);
			}
			pos_actual +=1;
		}
		return restParaEsaEspecialidad;
	}
	
	public static List<Restaurante> buscarPorNombre(List<Restaurante> listRest, String nombreBuscado) {
		List<Restaurante> restConEseNombre = new ArrayList<Restaurante>();
		Restaurante restauranteAux = null;
		int pos_actual = 0;
		int longitud = listRest.size();
		
		while (pos_actual < longitud) {
			boolean estaNombre = false;
			restauranteAux = listRest.get(pos_actual);
			estaNombre = restauranteAux.getNombre().equals(nombreBuscado);
			if (estaNombre) {
				restConEseNombre.add(restauranteAux);
			}
			pos_actual +=1;
		}
		return restConEseNombre;
	}
	
	public static List<Restaurante> buscarPorBarrio(List<Restaurante> listRest, String barrioBuscado) {
		List<Restaurante> restEnEseBarrio = new ArrayList<Restaurante>();
		Restaurante restauranteAux = null;
		int pos_actual = 0;
		int longitud = listRest.size();
		
		while (pos_actual < longitud) {
			boolean estaBarrio = false;
			restauranteAux = listRest.get(pos_actual);
			estaBarrio = restauranteAux.getBarrio().equals(barrioBuscado);
			if (estaBarrio) {
				restEnEseBarrio.add(restauranteAux);
			}
			pos_actual +=1;
		}
		return restEnEseBarrio;
	}
	
	}
