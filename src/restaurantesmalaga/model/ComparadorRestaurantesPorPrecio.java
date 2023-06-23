package restaurantesmalaga.model;

import java.util.Comparator;

// Orden TOTAL: implementar interface Comparator<>
public class ComparadorRestaurantesPorPrecio implements Comparator<Restaurante> {

	// Comparador para un solo criterio (por Nombre).
	@Override
	public int compare(Restaurante res1, Restaurante res2) {
		// TODO Auto-generated method stub
		int num = 0; 
		if((float)res1.getPrecioMedio() < (float)res2.getPrecioMedio()) {
			num=-1;
		}
		else if((float)res1.getPrecioMedio() > (float)res2.getPrecioMedio()) {
			num=1;
		}
	return num;
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

