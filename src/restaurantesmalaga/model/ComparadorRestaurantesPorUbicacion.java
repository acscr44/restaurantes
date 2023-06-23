package restaurantesmalaga.model;

import java.util.Comparator;

// Orden TOTAL: implementar interface Comparator<>
public class ComparadorRestaurantesPorUbicacion implements Comparator<Restaurante> {

	// Comparador para un solo criterio (por Nombre).
	@Override
	public int compare(Restaurante res1, Restaurante res2) {
		// TODO Auto-generated method stub
		
		return res1.getNombre().compareTo(res2.getNombre());
	}

	//Comparador para más de un criterio.
//	@Override
//	public int compare(Restaurante res1, Restaurante res2) {
//		// TODO Auto-generated method stub
//		int resultado = res1.getNombre().equals(res2.getNombre()) ? res1.getNombre().compareTo(res2.getNombre()) : 
//			(Double)res1.getPrecioMedio().compareTo((Double)res2.getPrecioMedio());
//		return resultado;
//	}
}

