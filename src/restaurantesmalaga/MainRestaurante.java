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
				// El restaurante ha completado todos sus parámetros. El siguiente restaurante comienza con el cont numlinea a 0.
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
			System.out.println("FICHERO EXISTE!, a parsearlo");
			// Parsear es inspeccionar, recorrer y clasificar el documento
			Path path = file.toPath();
			List<String> lineas = Files.readAllLines(path); // Nos devuelve una lista de lineas.
			// TODO Convertir cada línea asociada a restaurante en un registro de
			// restaurante.
			List<Restaurante> listRest = cargarRestaurantes(lineas);
			System.out.println("La lista tiene " + listRest.size() + " restaurantes. \n");
			// TODO Mostrar los datos del fichero cargado
//			for (String linea : lineas) { // for each
//				System.out.println(linea);
//			}
		} else {
			System.out.println("NO EXISTE el fichero en esa ruta : (");
		}

	}

}
