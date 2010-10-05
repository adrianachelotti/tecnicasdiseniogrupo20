package source;
import java.util.LinkedList;
import java.util.List;


public class ManejadorDeSucesos {

	/**
	 * Instancia unica de la API 
	 */
	private static ManejadorDeSucesos instancia ;
	
	
	private Configuracion configuracion;
	
		
	/**
	 * Lista de implicaciones que van a ser notificados por la API
	 */
	private List<Implicacion> implicaciones;
	
	/**
	 * Se guardan en esta lista los sucesos que se notificaran proximamente
	 */
	private List<Suceso> sucesosPendientesNotificacion;
	
	/**
	 * Lista de sucesos donde se agregan los sucesos que se producen 
	 * como producto de una accion.
	 */
	private List<Suceso> sucesosNuevos;
	
	public Configuracion getConfiguracion() {
		return configuracion;
	}

	public void setConfiguracion(Configuracion configuracion) {
		this.configuracion = configuracion;
	}

	/**
	 * Constructor de la API.
	 */	
	private ManejadorDeSucesos(){
		implicaciones = new LinkedList<Implicacion> (); 
		sucesosPendientesNotificacion = new LinkedList<Suceso>();
		sucesosNuevos = new LinkedList<Suceso>();
	}
	
	/**
	 * Se obtiene una instancia del Accionador
	 * @return instancia de Accionador
	 */
	public static ManejadorDeSucesos getInstancia(){
		if (instancia==null){
			instancia=new ManejadorDeSucesos();
		}
		return instancia;
	}
	
	/**
	 * Cambia la configuracion a Secuencia de sucesos
	 */
	public void setConfiguracionSecuencia(){
		this.configuracion = new ConfiguracionSecuencia();
	}
	
	/**
	 * Se suscribe los sucesos que deben suceder y la accion que implementa 
	 * el cliente   
	 * @param accionCliente: accion a ejecutar si se cumplen los suceso 
	 * @param sucesos: conjuntos de sucesos 
	 */
	public void suscribirImplicacion(Accion accionCliente, List<Suceso> sucesos){
		Implicacion nuevaImplicacion =  new Implicacion();
		nuevaImplicacion.setAccion(accionCliente);
		nuevaImplicacion.setSucesos(sucesos);
		this.implicaciones.add(nuevaImplicacion);
	}
	
	
	/**
	 * Se suscribe el suceso que debe suceder para que sea avisado y la accion a ejecutar
	 * @param accionCliente: accion que quiere ejecutar el cliente cuando ocurre el suceso 
	 * @param suceso: suceso que se debe cumplir
	 */
	public void suscribirImplicacion(Accion accionCliente, Suceso suceso){
		List<Suceso> lista = new LinkedList<Suceso>();
		lista.add(suceso);
		this.suscribirImplicacion(accionCliente, lista);
		
		
	}
	
	/**
	 * Se corrobora si hay un suceso suscripto de ese tipo y se ejecuta la accion
	 * @param sucesoActual: suceso que se notifica
	 */
	public void notificar(Suceso sucesoActual){
		this.agregarSuceso(sucesoActual);
		for (Implicacion relacion : this.implicaciones) {
			relacion.avisarSucesosOcurridos(this.sucesosPendientesNotificacion);
		}
		//una vez terminada la notificacion
		this.sucesosPendientesNotificacion.clear();
	}
	
	/**
	 * Se corrobora entre los suceso que se suscriben y en caso de encontrar 
	 * dicho conjunto se ejecuta la accion
	 * @param sucesos que se corroboran
	 */
	public void notificar(List<Suceso> sucesos){
		this.agregarSucesos(sucesos);
		for (Implicacion relacion : this.implicaciones) {
			relacion.avisarSucesosOcurridos(this.sucesosPendientesNotificacion);			
		}
		
		//una vez terminada la notificacion
		this.sucesosPendientesNotificacion.clear();
	}
	
	/**
	 * notifica los sucesos que se han acumulado al momento
	 */
	public void notificar(){
		for (Implicacion relacion : this.implicaciones) {
			relacion.avisarSucesosOcurridos(this.sucesosPendientesNotificacion);			
		}
		
		//una vez terminada la notificacion
		this.sucesosPendientesNotificacion.clear();
	}
	
	
	/**
	 * Agrega un suceso en la lista de sucesos pendientes de notificacion
	 * @param sucesoAgregar: se agrega el suceso, pero no se notifica
	 */
	public void agregarSuceso(Suceso sucesoAgregar){
		
		this.sucesosPendientesNotificacion.add(sucesoAgregar);
	}
	
	/**
	 * Agrega un suceso en la lista de sucesos pendientes de notificacion
	 * @param sucesoAgregar: se agrega el suceso, pero no se notifica
	 */
	public void agregarSucesos(List<Suceso> sucesosAgregar){
		this.sucesosPendientesNotificacion.addAll(sucesosAgregar);
	}
	
	/**
	 * Agrega nuevos sucesos cuando la api se encuentra notificando
	 * @param sucesosAgregar:
	 */
	private void agregarNuevosSucesos(List<Suceso> sucesosAgregar){
		this.sucesosNuevos.addAll(sucesosAgregar);
	}
	/**
	 * Agrega un suceso cuando la api se encuentra notificando
	 * @param sucesosAgregar:
	 */
	private void agregarNuevosSucesos(Suceso sucesoAgregar){
		this.sucesosNuevos.add(sucesoAgregar);
	}
	
	
	/**
	 * El suceso se agrega a la lista de sucesos pendiente de notificacion
	 * solo si no se cancela con alguno de la lista
	 * @param sucesoCancelable: suceso con un id de suceso cancelador
	 */
	//TODO: ver como cancelar
	public void agregarSucesoCancelable(Suceso sucesoCancelable){
		List<Suceso> listaABorrar = new LinkedList<Suceso>();
		
		for (Suceso suceso: this.sucesosPendientesNotificacion) {
			if (sucesoCancelable.getIdSucesoCancelador()== suceso.getIdSuceso()){
				//recopilo los sucesos en una lista a borrar
				listaABorrar.add(suceso);			
			}
			
		}
		this.sucesosPendientesNotificacion.removeAll(listaABorrar);
	}
	
}
