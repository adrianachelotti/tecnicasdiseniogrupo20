package modelo.manejadorDeSucesos;

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
	 * Configuracion del manejador de sucesos.
	 */
	private Configuracion configuracion;
	
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
	 * Constructor de la clase.
	 */	
	public ManejadorDeSucesos(){
		this.configuracion = new Configuracion();
		this.implicaciones = new ArrayList<Implicacion>(); 
		this.sucesosOcurridos = new ArrayList<Suceso>();
	}
	
	/**
	 * Obtiene la configuracion del manejador de sucesos.
	 * @return Configuracion del manejador de sucesos.
	 */
	public Configuracion obtenerConfiguracion() {
		return configuracion;
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
		if (esConjuntoValidoDeSucesos(sucesos)){
			//sacamos los suceso que son nulos de la lista
			this.eliminarSucesosNulos(sucesos);
			Implicacion nuevaImplicacion =  new Implicacion(sucesos,accionCliente);
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
				this.configuracion.obtenerEvaluador().avisarSucesosOcurridos(relacion, sucesosANotificar);
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
			cancelarSuceso(sucesoAgregar);
			sucesoAgregar.setOrdenDeSuscripcion(obtenerOrdenDeSuscripcion());
			this.sucesosOcurridos.add(sucesoAgregar);
			quitarSucesosExcedentes();
			this.notificar();
		}
	}
	
	/**
	 * Agrega un conjunto de sucesos al conjunto de sucesos pendientes notificacion.
	 * @param sucesosAgregar conjunto de sucesos a agregar.
	 */
	public void agregarSucesos(List<Suceso> sucesosAgregar){
		
		if (esConjuntoValidoDeSucesos(sucesosAgregar)){
			//Puede contener elementos que sean nulos, entonces los sacamos
			this.eliminarSucesosNulos(sucesosAgregar);
			for(Suceso sucesoActual: sucesosAgregar){
				agregarSuceso(sucesoActual);
			}			
		}
	}
	
	/**
	 * Elimina las implicaciones almacenadas.
	 */
	public void borrarImplicaciones(){
		this.implicaciones.clear();
	}
		
	/**
	 * Obtiene las implicaciones almacenadas.
	 * @return implicaciones almacenadas.
	 */
	public List<Implicacion> obtenerImplicaciones(){
		return this.implicaciones;
	}
	
	/**
	 * Segun el tamanio maximo de la lista se quitan los mas viejos 
	 */
	private void quitarSucesosExcedentes() {
		//Verificamos de no salirnos del maximo
		if (this.sucesosOcurridos.size()>this.configuracion.obtenerTamanioMaximoaDeSucesosOcurridos()){
			// me quedo con los primeros				
			int posicionInicio = this.sucesosOcurridos.size()-this.configuracion.obtenerTamanioMaximoaDeSucesosOcurridos();
			int posicionFinal = this.sucesosOcurridos.size();
			this.sucesosOcurridos = this.sucesosOcurridos.subList(posicionInicio,posicionFinal);
		}
	}
	
	/**
	 * Efectua la cancelacion del suceso a agregar.
	 * @param sucesoAgregar suceso cancelador a agegar.
	 */
	private void cancelarSuceso(Suceso sucesoAgregar){
		if(this.configuracion.estaCanceladorActivo()) 
			this.configuracion.obtenerCancelador().cancelarSuceso(this.sucesosOcurridos,sucesoAgregar);
	}
		
	/**
	 * Determina si un conjunto de sucesos es valido o no.
	 * Se considera como valido si no es vacio o nulo.
	 * @param sucesosAValidar sucesos a validar.
	 * @return true si el conjunto de sucesos no son vacios o nulos.
	 *		   false en caso contrario. 
	 */
	private boolean esConjuntoValidoDeSucesos(List<Suceso> sucesosAValidar){
		return sucesosAValidar!=null && !sucesosAValidar.isEmpty();
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
		
	public void habilitarImplicacion(int identificador){
		obtenerImplicacionPorIdentificador(identificador).habilitar();
	}
	
	public void deshabilitarImplicacion(int identificador){
		obtenerImplicacionPorIdentificador(identificador).deshabilitar();
	}
	
	public Implicacion obtenerImplicacionPorIdentificador(int identificador){
		boolean encontrado = false;
		Iterator<Implicacion> it =implicaciones.iterator();
		Implicacion implicacionActual=null;
		while(it.hasNext() && !encontrado){
			implicacionActual=it.next();
			if(identificador==implicacionActual.getIdentificador()){
				encontrado=true;
			}
		}
		if(!encontrado)
			return null;
		return implicacionActual;
	}
}
