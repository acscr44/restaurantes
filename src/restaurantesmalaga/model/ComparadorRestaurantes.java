package restaurantesmalaga.model;

import java.util.Comparator;

// Orden TOTAL: implementar interface Comparator<>
// Comparador para un solo criterio.
public class ComparadorRestaurantes implements Comparator<Restaurante> {
	@Override
	public int compare(Restaurante res1, Restaurante res2) {
		// TODO Auto-generated method stub
		
		return res1.getNombre().compareTo(res2.getNombre());
	}
}
