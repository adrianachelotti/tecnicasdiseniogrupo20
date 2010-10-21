package source;


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
	 * Constructor de la clase.
	 */	
	public Configuracion(){
		this.evaluador = EvaluadorDiscontinuo.obtenerInstancia();
		this.cancelador = CanceladorPorDefecto.obtenerInstancia();
		this.canceladorActivo = false;
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
	 * Establece el cancelador por defecto.
	 * El modo de cancelacion por defecto proporciona la cancelacion uno a uno de
	 * sucesos cancelables.
	 */
	public void establecerCanceladorPorDefecto(){
		this.cancelador = CanceladorPorDefecto.obtenerInstancia();
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
