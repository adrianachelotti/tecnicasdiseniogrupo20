package source;
/**
 * Clase encargada de representar la configuracion del manejador de sucesos.
 * @author Grupo20
 *
 */

public class Configuracion {
	/**
	 * Evaluador de sucesos ocurridos.
	 */
	private Evaluador evaluador;
	
	/**
	 * Cancelador de sucesos.
	 */
	private Cancelador cancelador;
	
	/**
	 * Estado del cancelador.
	 */
	private boolean canceladorActivo;
	
	/**
	 * Tamaño maximo de la coleccion de sucesos que se almacenan.
	 */
	private int tamanioMaximoDeSucesosOcurridos = 50;
	
	/**
	 * Constructor de la clase.
	 */	
	public Configuracion(){
		this.evaluador = EvaluadorDiscontinuo.obtenerInstancia();
		this.cancelador = CanceladorPorDefecto.obtenerInstancia();
		this.canceladorActivo = false;
	}
	
	/**
	 * Obtiene el tamanio maximo de sucesos ocurridos.
	 * @return Tamanio de la lista de sucesos ocurridos.
	 */
	public int obtenerTamanioMaximoaDeSucesosOcurridos() {
		return tamanioMaximoDeSucesosOcurridos;
	}
	
	/**
	 * Carga el tamanio maximo de sucesos ocurridos.
	 * @param tamanioMaximoDeSucesosOcurridos tamanio maximo de sucesos a almacenar.
	 */
	public void establecerTamanioMaximoDeSucesosOcurridos(int tamanioMaximoDeSucesosOcurridos) {
		if (tamanioMaximoDeSucesosOcurridos>=1){
			this.tamanioMaximoDeSucesosOcurridos = tamanioMaximoDeSucesosOcurridos;
		}
	}
	
	/**
	 * Cambia el evaluador a secuencia continua de sucesos.
	 */
	public void establecerEvaluadorSecuenciaContinua(){
		this.evaluador = EvaluadorSecuenciaContinua.obtenerInstancia();
	}
	
	/**
	 * Cambia el evaluador a secuencia discontinua de sucesos.
	 */
	public void establecerEvaluadorSecuenciaDiscontinua(){
		this.evaluador = EvaluadorSecuenciaDiscontinua.obtenerInstancia();
	}
	
	/**
	 * Cambia el evaluador a conjunto de sucesos discontinuos.
	 */
	public void establecerEvaluadorDiscontinuo(){
		this.evaluador = EvaluadorDiscontinuo.obtenerInstancia();
	}
	
	/**
	 * Cambia el evaluador a conjunto de sucesos continuos.
	 */
	public void establecerEvaluadorContinuo(){
		this.evaluador = EvaluadorContinuo.obtenerInstancia();
	}
			
	/**
	 * Deshabilita la cancelacion de sucesos.
	 */
	public void deshabilitarCancelador(){
		this.canceladorActivo = false;
	}
	
	/**
	 * Habilita la cancelacion de sucesos. 
	 */
	public void habilitarCancelador(){
		this.canceladorActivo = true;
	}
	
	/**
	 * Obtiene el estado del cancelador.
	 * @return true si el cancelador esta activo.
	 * 		   false en caso contrario.
	 */
	public boolean estaCanceladorActivo(){
		return this.canceladorActivo;
	}

	/**
	 * Obtiene el evaluador de la configuracion.
	 * @return Evaluador actual de la configuracion.
	 */
	public Evaluador getEvaluador() {
		return evaluador;
	}

	/**
	 * Obtiene el cancelador de la configuracion.
	 * @return Cancelador actual de la configuracion.
	 */
	public Cancelador getCancelador() {
		return cancelador;
	}
	
}
