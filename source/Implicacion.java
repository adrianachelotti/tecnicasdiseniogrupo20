package source;
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
	 * Accion que representa al consecuente.	 
	 */
	private Accion accion;
	

	/**
	 * Conjunto de sucesos que representan al antecedente.
	 */
	private List<Suceso> sucesos = new ArrayList<Suceso>();
	
	
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
			
}
