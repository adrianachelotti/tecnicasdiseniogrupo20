package source;

import java.util.ArrayList;
import java.util.Iterator;
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
	 * Sucesos ocurridos.
	 */
	private List<Suceso> sucesosOcurridos;
	
	
	/**
	 * Orden en que se suscribe una implicacion o suceso.
	 */
	private static long ordenDeSuscripcion = 0;
	
	/**
	 * Tama�o maximo de la lista de sucesos que almacena la API
	 */
	private int tamanioMaximoDeSucesosOcurridos;


	/**
	 * Constructor de la clase.
	 */	
	public ManejadorDeSucesos(){
		this.implicaciones = new ArrayList<Implicacion>(); 
		this.sucesosOcurridos = new ArrayList<Suceso>();
		this.evaluador = EvaluadorDiscontinuo.obtenerInstancia();
		this.cancelador = CanceladorPorDefecto.obtenerInstancia();
		// por default la API trabajar con una lista de 50 sucesos ocurrridos
		this.tamanioMaximoDeSucesosOcurridos = 50;
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
	 * Cambia la configuracion a conjunto de sucesos discontinuos sin importar el orden
	 */
	public void establecerConfiguracionDiscontinuo(){
		this.evaluador = EvaluadorDiscontinuo.obtenerInstancia();
	}
	
	/**
	 * Cambia la configuracion a conjunto de sucesos continuos sin importar el orden
	 */
	public void establecerConfiguracionContinuo(){
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
	 * Obtiene el orden de suscripcion.
	 * @return orden de suscripcion.
	 */
	static long obtenerOrdenDeSuscripcion() {
		ordenDeSuscripcion++;
		return ordenDeSuscripcion;
	}
		

	/**	
	 * Suscribe una implicacion a evaluar.
	 * @param accionCliente accion a ejecutar si se cumplen los sucesos. 
	 * @param sucesos conjuntos de sucesos a evaluar. 
	 */
	public void suscribirImplicacion(Accion accionCliente, List<Suceso> sucesos){
		//Si la lista de sucesos es nula no suscribimos la implicacion
		if (sucesos!=null && !sucesos.isEmpty()){
			//sacamos los suceso que son nulos de la lista
			this.eliminarSucesosNulos(sucesos);
			Implicacion nuevaImplicacion =  new Implicacion();
			nuevaImplicacion.setAccion(accionCliente);
			nuevaImplicacion.setSucesos(sucesos);
			nuevaImplicacion.setOrdenUltimaSuscripcion(obtenerOrdenDeSuscripcion());
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
	 * Notifica los sucesos pendientes.
	 */
	private void notificar(){
		if (notificado!=true){
			notificado=true;
			List<Suceso> sucesosANotificar = null;
			for (Implicacion relacion : this.implicaciones) {
				sucesosANotificar = filtrarSucesosPorTiempoDeImplicacion(relacion);
				this.evaluador.avisarSucesosOcurridos(relacion, sucesosANotificar);
			}
			notificado=false;
		}
	}
		
	/**
	 * Agrega un suceso al conjunto de sucesos pendientes de notificacion.
	 * @param sucesoAgregar suceso a agregar.
	 */
	public void agregarSuceso(Suceso sucesoAgregar){
		if (sucesoAgregar!=null){
			//if(canceladorActivo) this.cancelador.cancelarSuceso(this.sucesosOcurridos,sucesoAgregar);
			sucesoAgregar.setOrdenDeSuscripcion(obtenerOrdenDeSuscripcion());
			this.sucesosOcurridos.add(sucesoAgregar);
			// verificamos que la lista no este llena
			if (this.sucesosOcurridos.size()>=(this.tamanioMaximoDeSucesosOcurridos+1) && tamanioMaximoDeSucesosOcurridos>0){
				// me quedo con los primeros				
				this.sucesosOcurridos = this.sucesosOcurridos.subList(0,this.tamanioMaximoDeSucesosOcurridos-1);
				
			}
			this.notificar();
		}
	}
	
	/**
	 * Agrega un conjunto de sucesos al conjunto de sucesos pendientes notificacion.
	 * @param sucesosAgregar conjunto de sucesos a agregar.
	 */
	public void agregarSucesos(List<Suceso> sucesosAgregar){
		//controlo que la lista no sea null
		if (sucesosAgregar!=null && !sucesosAgregar.isEmpty()){
			//Puede contener elementos que sean nulos, entonces los sacamos
			this.eliminarSucesosNulos(sucesosAgregar);
			//if(canceladorActivo) this.cancelador.cancelarSucesos(this.sucesosOcurridos,sucesosAgregar);
			for(Suceso sucesoActual: sucesosAgregar){
				sucesoActual.setOrdenDeSuscripcion(obtenerOrdenDeSuscripcion());
			}			
			this.sucesosOcurridos.addAll(sucesosAgregar);
			//Verificamos de no salirnos del maximo
			if (this.sucesosOcurridos.size()>this.tamanioMaximoDeSucesosOcurridos){
				// me quedo con los primeros				
				this.sucesosOcurridos = this.sucesosOcurridos.subList(this.sucesosOcurridos.size()-this.tamanioMaximoDeSucesosOcurridos,this.sucesosOcurridos.size()-1);
				
			}
			
			this.notificar();
		}
	}

	/**
	 * Elimina las implicaciones almacenadas.
	 */
	public void borrarImplicaciones(){
		this.implicaciones.clear();
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
	
	/**
	 * Filtra los sucesos ocurridos segun el tiempo de la implicacion pasada coo parametro.
	 * @param implicacion implicacion sobre la que se obtendra el tiempo para filtrar.
	 * @return Coleccion de sucesos filtrados.
	 */
	private List<Suceso> filtrarSucesosPorTiempoDeImplicacion(Implicacion implicacion) {
		List<Suceso> sucesosANotificar = new ArrayList<Suceso>();
		boolean encontrado = false;
		int posicionInicio = 0;
		int tamanioSucesos = sucesosOcurridos.size();
		Iterator<Suceso> it = this.sucesosOcurridos.iterator();
		Suceso sucesoActual = null;
		
		while(it.hasNext() && !encontrado){
			sucesoActual = it.next();
			if(sucesoActual.getOrdenDeSuscripcion()> implicacion.getOrdenUltimaSuscripcion()){
				encontrado = true;
			}else{
				posicionInicio++;
			}
		}
		if (encontrado) 
			sucesosANotificar = this.sucesosOcurridos.subList(posicionInicio, tamanioSucesos);
		
		return sucesosANotificar;
	}
			
	/**
	 * Obtiene el tamanio de la lista de sucesos ocurridos que almacena la API
	 * @return Tama�o de la lista de sucesos ocurridos
	 */
	public int obtenerTamanioMaximoaDeSucesosOcurridos() {
		return tamanioMaximoDeSucesosOcurridos;
	}
	/**
	 * Se configurar el tama�o de la lista de sucesos ocurridos
	 * @param tamanioDeListaDeSucesosOcurridos
	 */
	public void establecerTamanioMaximoaDeSucesosOcurridos(int tamanioMaximoDeSucesosOcurridos) {
		//controlo que no sean valores negativo o cero
		if (tamanioMaximoDeSucesosOcurridos>=1){
			this.tamanioMaximoDeSucesosOcurridos = tamanioMaximoDeSucesosOcurridos;
		}
	}
}
