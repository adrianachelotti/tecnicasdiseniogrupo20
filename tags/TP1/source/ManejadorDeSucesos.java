package source;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Clase encargada del manejo de sucesos.
 * 
 * @author Grupo20 
 *
 */
public class ManejadorDeSucesos {
			
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
	 * Estado de etapa de notificacion.
	 */
	private boolean notificado = false;
		
	/**
	 * Implicaciones agregadas.
	 */
	private List<Implicacion> implicaciones;
	
	/**
	 * Sucesos no notificados.
	 */
	private List<Suceso> sucesosPendientesNotificacion;
	
	/**
	 * Constructor de la clase.
	 */	
	public ManejadorDeSucesos(){
		implicaciones = new ArrayList<Implicacion>(); 
		sucesosPendientesNotificacion = new ArrayList<Suceso>();
		evaluador = EvaluadorPorDefecto.obtenerInstancia();
		cancelador = CanceladorPorDefecto.obtenerInstancia();
	}
			
	/**
	 * Cambia la configuracion a secuencia continua de sucesos.
	 */
	public void establecerConfiguracionSecuenciaContinua(){
		this.evaluador = EvaluadorSecuenciaContinua.obtenerInstancia();
	}
	
	/**
	 * Cambia la configuracion a secuencia discontinua de sucesos.
	 */
	public void establecerConfiguracionSecuenciaDiscontinua(){
		this.evaluador = EvaluadorSecuenciaDiscontinua.obtenerInstancia();
	}
	
	/**
	 * Cambia la configuracion a secuencia por defecto de sucesos.
	 */
	public void establecerConfiguracionPorDefecto(){
		this.evaluador = EvaluadorPorDefecto.obtenerInstancia();
	}
	
	/**
	 * Cambia la configuracion a igualdad de conjunto de sucesos.
	 */
	public void establecerConfiguracionIgualdadConjunto(){
		this.evaluador = EvaluadorIgualdadConjunto.obtenerInstancia();
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
	 * Establece el cancelador total.
	 * El modo de cancelacion total proporciona la cancelacion de todos los sucesos
	 * que sean cancelables.
	 */
	public void establecerCanceladorTotal(){
		this.cancelador = CanceladorTotal.obtenerInstancia();
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
	 * Suscribe una implicacion a evaluar.
	 * @param accionCliente accion a ejecutar si se cumplen los sucesos. 
	 * @param sucesos conjuntos de sucesos a evaluar. 
	 */
	public void suscribirImplicacion(Accion accionCliente, List<Suceso> sucesos){
		//Si la lista de sucesos es nula no suscribimos la implicacion
		if (sucesos!=null){
			//sacamos los suceso que son nulos de la lista
			this.eliminarSucesosNulos(sucesos);
			Implicacion nuevaImplicacion =  new Implicacion();
			nuevaImplicacion.setAccion(accionCliente);
			nuevaImplicacion.setSucesos(sucesos);
			this.implicaciones.add(nuevaImplicacion);
		}
	}
	
	/**
	 * Suscribe una implicacion a evaluar.
	 * @param accionCliente accion a ejecutar si se cumple el suceso. 
	 * @param suceso suceso a evaluar.
	 */
	public void suscribirImplicacion(Accion accionCliente, Suceso suceso){
		//Si el suceso es nulo no suscribimos la implicacion 
		if(suceso!=null){
			List<Suceso> lista = new ArrayList<Suceso>();
			lista.add(suceso);
			this.suscribirImplicacion(accionCliente, lista);
		}
	}
	
	/**
	 * Notifica los sucesos pendientes junto con el pasado como parametro.
	 * @param sucesoActual suceso a notificar.
	 */
	public void notificar(Suceso sucesoActual){
		if (notificado!=true){
			notificado=true;
			this.agregarSuceso(sucesoActual);
			for (Implicacion relacion : this.implicaciones) {
				this.evaluador.avisarSucesosOcurridos(relacion, this.sucesosPendientesNotificacion);
			}
			this.sucesosPendientesNotificacion.clear();
			notificado=false;
		}
	}
	
	/**
	 * Notifica los sucesos pendientes junto con los pasados como parametro.
	 * @param sucesos sucesos a notificar.
	 */
	public void notificar(List<Suceso> sucesos){
		this.agregarSucesos(sucesos);
		for (Implicacion relacion : this.implicaciones) {
			this.evaluador.avisarSucesosOcurridos(relacion, this.sucesosPendientesNotificacion);
		}
		this.sucesosPendientesNotificacion.clear();
	}
	
	/**
	 * Notifica los sucesos pendientes.
	 */
	public void notificar(){
		if (notificado!=true){
			notificado=true;
			for (Implicacion relacion : this.implicaciones) {
				this.evaluador.avisarSucesosOcurridos(relacion, this.sucesosPendientesNotificacion);
			}
			notificado=false;
			this.sucesosPendientesNotificacion.clear();
		}
	}
		
	/**
	 * Agrega un suceso al conjunto de sucesos pendientes de notificacion.
	 * @param sucesoAgregar suceso a agregar.
	 */
	public void agregarSuceso(Suceso sucesoAgregar){
		if (sucesoAgregar!=null){
			if(canceladorActivo) this.cancelador.cancelarSuceso(this.sucesosPendientesNotificacion,sucesoAgregar);
			this.sucesosPendientesNotificacion.add(sucesoAgregar);
		}
	}
	
	/**
	 * Agrega un conjunto de sucesos al conjunto de sucesos pendientes notificacion.
	 * @param sucesosAgregar conjunto de sucesos a agregar.
	 */
	public void agregarSucesos(List<Suceso> sucesosAgregar){
		//controlo que la lista no sea null
		if (sucesosAgregar!=null){
			//Puede contener elementos que sean nulos, entonces los sacamos
			this.eliminarSucesosNulos(sucesosAgregar);
			if(canceladorActivo) this.cancelador.cancelarSucesos(this.sucesosPendientesNotificacion,sucesosAgregar);
			this.sucesosPendientesNotificacion.addAll(sucesosAgregar);
		}
	}

	/**
	 * Elimina las implicaciones almacenadas.
	 */
	public void borrarImplicaciones(){
		this.sucesosPendientesNotificacion.clear();
	}
		
	/**
	 * Elimina los elementos nulos de un conjunto de sucesos.
	 */
	private void eliminarSucesosNulos(List<Suceso> listaSucesos){
		List<Suceso> listaCopia = new ArrayList<Suceso>();
		for (Suceso suceso : listaSucesos) {
			if(suceso!=null){
				listaCopia.add(suceso);
			}
		}
		listaSucesos=listaCopia;
	}
		
}
