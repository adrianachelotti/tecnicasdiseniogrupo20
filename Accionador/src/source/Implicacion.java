package source;
import java.util.ArrayList;
import java.util.List;


public class Implicacion {
	
	/**
	 * Accion que se ejecuta cuando se cumple los sucesos suscriptos al Accionador	 
	 */
	private Accion accion;
	

	/**
	 * Conjunto de sucesos que tienen que ocurrir para que el objeto de realiza una accion
	 */
	private List<Suceso> sucesos = new ArrayList<Suceso>();
	
	
	/**
	 * Devuelve el conjunto de sucesos suscriptos a la api
	 * @return conjuntos de sucesos
	 */
	public List<Suceso> getSucesos() {
		return sucesos;
	}

	/**
	 * Carga la lista de sucesos que se suscriben a la api
	 * @param sucesos: conjuntos de sucesos
	 */
	public void setSucesos(List<Suceso> sucesos) {
		this.sucesos = sucesos;
	}
	
	/**
	 * Devuelve la accion a realizarse 
	 * @return Accion: respuesta ante el conjunto de sucesos
	 */
	public Accion getAccion() {
		return accion;
	}

	/**
	 * Se carga la accion a ser implementada por el cliente
	 * @param accion: accion a cargarse
	 */
	public void setAccion(Accion accion) {
		this.accion = accion;
	}
			
}
