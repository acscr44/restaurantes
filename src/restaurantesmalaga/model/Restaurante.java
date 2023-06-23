package restaurantesmalaga.model;

import java.util.Arrays;
import java.util.List;
// Interface Comparable<> para orden NATURAL
// Interface Comparator<> para orden TOTAL
public class Restaurante implements Comparable<Restaurante> {
	// id ¿?
	private String nombre;
	private String direccion;
	private String web;
	private float precioMedio;
	private String fichaGoogle;
	private float latitud;
	private float longitud;
	private String barrio;
	private List<String> especialidades;

	// Constructor
	public Restaurante(String nombre, String direccion, String web, float precioMedio, String fichaGoogle, float latitud, float longitud,
			String barrio, String... especialidades) {
		// ... varargs -> número de argumentos variables, en lugar de List<String>. En
		// la variable del constructor además hay
		// que cambiar this.especialidades por Arrays.asList(especialidades).
		super();
		this.nombre = nombre;
		this.direccion = direccion;
		this.web = web;
		this.precioMedio = precioMedio;
		this.fichaGoogle = fichaGoogle;
		this.latitud = latitud;
		this.longitud = longitud;
		this.barrio = barrio;
		this.especialidades = Arrays.asList(especialidades);
	}

	public Restaurante() {
	}

	// Getters and Setters
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getWeb() {
		return web;
	}

	public void setWeb(String web) {
		this.web = web;
	}

	public float getPrecioMedio() {
		return precioMedio;
	}

	public void setPrecioMedio(float precioMedio) {
		this.precioMedio = precioMedio;
	}

	public String getFichaGoogle() {
		return fichaGoogle;
	}

	public void setFichaGoogle(String fichaGoogle) {
		this.fichaGoogle = fichaGoogle;
	}

	public float getLatitud() {
		return latitud;
	}

	public void setLatitud(float latitud) {
		this.latitud = latitud;
	}

	public float getLongitud() {
		return longitud;
	}

	public void setLongitud(float longitud) {
		this.longitud = longitud;
	}

	public String getBarrio() {
		return barrio;
	}

	public void setBarrio(String barrio) {
		this.barrio = barrio;
	}

	public List<String> getEspecialidades() {
		return especialidades;
	}

	public void setEspecialidades(List<String> especialidades) {
		this.especialidades = especialidades;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		boolean iguales = false;
			if (obj!=null) {
				if (obj instanceof Restaurante r) {  // Si obj es instancia de Restaurante, entonces r es creado como Restaurante.
					iguales = this.direccion.equals(r.direccion);
				}
			}
		
		return iguales;
	}
	
	@Override
	public String toString() {
		return "Restaurante [nombre=" + nombre + 
					", direccion=" + direccion + 
					", web=" + web +
					", precio_medio=" + precioMedio + 
					", fichaGoogle=" + fichaGoogle + 
					", latitud=" + latitud + 
					", longitud=" + longitud + 
					", barrio=" + barrio + 
					", especialidades=" + especialidades + 
					"]";
	}


	// Comparación NATURAL: se implementa la interface Comparable<T>
	// Comparación por precio medio

	@Override
	public int compareTo(Restaurante r) {
		// TODO Auto-generated method stub
		int num = 0; 
			if((float)this.precioMedio < (float)r.precioMedio) {
				num=-1;
			}
			else if((float)this.precioMedio > (float)r.precioMedio) {
				num=1;
			}
		return num;
		// No funciona con float, pero sí con double
//		return  (int) (this.precioMedio-r.precioMedio);
	}


//	@Override
//	public int compare(Restaurante o1, Restaurante o2) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
}
