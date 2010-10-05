package source;
import java.util.LinkedList;
import java.util.List;


public class Implicacion {
	
	/**
	 * Accion que se ejecuta cuando se cumple los sucesos suscriptos al Accionador	 
	 */
	private Accion accion;
	

	/**
	 * Conjunto de sucesos que tienen que ocurrir para que el objeto de realiza una accion
	 */
	private List<Suceso> sucesos = new LinkedList<Suceso>();
	
	
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

	
	/**
	 * Se verifica si un suceso ocurrido es igual al de la implicacion 
	 * @param sucesoOcurrido: suceso que ha ocurrido y notificado
	 * @return resultado de la notificacion
	 */
	public void avisarSucesoOcurrido(Suceso sucesoOcurrido){
		// Si mi lista de sucesos solo tiene un solo, entonces puede 
		// ser posible que haya sido el mismo
		if (this.sucesos.size()==1){
			for (Suceso suceso : this.sucesos) {
				if (suceso.getIdSuceso().equals(sucesoOcurrido.getIdSuceso())){
					if (this.getAccion()!=null)
							this.getAccion().ejecutar();
				}
			}
		}
	}
	
	/**
	 * Verifica si la implicacion de sucesos fue cumplida
	 * En caso de hacerlo, se ejecuta la accion 
	 * @param sucesosOcurridos: conjunto de sucesos ocurridos
	 */
	public void avisarSucesosOcurridos(List<Suceso> sucesosOcurridos){
		
		if (sucesosOcurridos.containsAll(this.sucesos))
				this.accion.ejecutar();
	}

	
	/**
	 * Verifica si la implicacion de sucesos ocurrieron en la secuencia 
	 * especificada
	 * @param sucesosOcurridos
	 */
	public void avisarSucesosOcurridosSecuencia(List<Suceso> sucesosOcurridos){
		
		
	}
	
	/**
	 * Verifica si la implicacion de sucesos ocurrieron en la secuencia 
	 * especificada
	 * @param sucesosOcurridos
	 */
	public void avisarSucesosOcurridosSecuenciaPerfecta(List<Suceso> sucesosOcurridos){
		
	}
		
}
