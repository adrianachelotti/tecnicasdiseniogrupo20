package modelo.manejadorDeSucesos;
import java.util.ArrayList;
import java.util.List;
/**
 * Clase que modela un implicacion.
 * Una implicacion esta formada por un antecedente compuesto por un conjunto de 
 * sucesos y un consecuente compuesto por una accion.
 * En caso de que se cumpla con el antecedente se ejecutara el consecuente.
 *  
 * @author Grupo20
 *
 */

public class Implicacion {
	
	/**
	 * Cantidad de implicaciones existentes.
	 */
	private static int cantidadDeImplicaciones = 0;
	
	/**
	 * Identificador de la implicacion.
	 */
	private int identificador;
	
	/**
	 * Accion que representa al consecuente.	 
	 */
	private Accion accion;
		
	/**
	 * Conjunto de sucesos que representan al antecedente.
	 */
	private List<Suceso> sucesos = new ArrayList<Suceso>();
	
	/**
	 * Orden de la ultima vez que se ejecuta la accion.
	 */
	private long ordenUltimaSuscripcion;
	
	/**
	 * Determina si la implicacion esta habilitada.
	 */
	private boolean habilitada;
	
	/**
	 * Constructor de la implicacion.
	 * @param antecedente conjunto de sucesos que forman el antecedente.
	 * @param consecuente accion que forman el consecuente.
	 */
	public Implicacion(List<Suceso> antecedente,Accion consecuente){
		this.sucesos = antecedente;
		this.accion = consecuente;
		this.habilitada = true;
		this.identificador = generarIdentificador(); 
	}
	
	/**
	 * Genera el identificador de la implicacion.
	 * @return identificador de la implicacion.
	 */
	private static int generarIdentificador(){
		return cantidadDeImplicaciones++;
	}
		
	/**
	 * Retorna el identificador de la implicacion.
	 * @return identificador de la implicacion.
	 */
	public int getIdentificador() {
		return this.identificador;
	}

	/**
	 * Obtiene el estado de la implicacion
	 * @return true si esta habilitada.
	 * 		   false en caso contrario.
	 */
	public boolean estaHabilitada() {
		return habilitada;
	}

	/**
	 * Habilita la implicacion.
	 */
	public void habilitar() {
		this.habilitada = true;
	}
	
	/**
	 * Deshabilita la implicacion
	 */
	public void deshabilitar(){
		this.habilitada = false;
	}

	/**
	 * Obtiene el conjunto de sucesos del antecedente.
	 * @return conjuntos de sucesos que forman el antecedente.
	 */
	public List<Suceso> getSucesos() {
		return sucesos;
	}

	/**
	 * Carga la lista de sucesos del antecedente.
	 * @param sucesos conjuntos de sucesos a cargarse.
	 */
	public void setSucesos(List<Suceso> sucesos) {
		this.sucesos = sucesos;
	}
	
	/**
	 * Obtiene la accion del consecuente. 
	 * @return accion que forma el consecuente.
	 */
	public Accion getAccion() {
		return accion;
	}

	/**
	 * Carga la accion del consecuente.
	 * @param accion accion a cargarse.
	 */
	public void setAccion(Accion accion) {
		this.accion = accion;
	}

	/**
	 * Obtiene el orden de la ultima suscripcion de la implicacion.
	 * @return orden de la ultima suscripcion de la implicacion.
	 */
	long getOrdenUltimaSuscripcion() {
		return ordenUltimaSuscripcion;
	}

	/**
	 * Carga el orden de la ultima suscripcion de la implicacion.
	 * @param ordenUltimaEjecucion orden de la ultima suscripcion de la implicacion.
	 */
	void setOrdenUltimaSuscripcion(long ordenUltimaSucripcion) {
		this.ordenUltimaSuscripcion = ordenUltimaSucripcion;
	}
			
	@Override
	public boolean equals(Object obj) {
		return this.identificador==((Implicacion)obj).getIdentificador();
	}
}
