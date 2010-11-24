package controladores;

import java.util.List;
import java.util.Map;

import modelo.cargador.CargadorDeDriver;
import modelo.driver.DriverDispositivo;
import modelo.edificio.Dispositivo;
import modelo.edificio.Edificio;
import modelo.edificio.Piso;


import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import controladores.beans.EdificioBean;

@SuppressWarnings("serial")
public class AgregarDispositivo extends ActionSupport {
	/**
	 * Nivel del piso donde se agrega el dispositivo
	 */
	private int nivel;
	
	/**
	 * Driver elegido para el dispositivo
	 */
	private String driverElegido;
	
	/**
	 * Descripcion del dispositivo ingresada
	 */
	private String descripcionDispositivo;
	
	
	@SuppressWarnings("unused")
	private Map<String,Object> session ;
	
	/**
	 * Se obtiene el driver del dispositivo elegido por el usuario
	 * @return driver Elegido
	 */
	public String getDriverElegido() {
		return driverElegido;
	}
	
	/**
	 * Se establece el driver del dispositivo elegido por el usuario
	 * @param driverElegido
	 */
	public void setDriverElegido(String driverElegido) {
		this.driverElegido = driverElegido;
	}
	
	/**
	 * Devuelve el nivel elegido por usuario
	 * @return nivel: piso elegido para agregarle el dispositivo
	 */
	public int getNivel() {
		return nivel;
	}
	/**
	 * Establece el nivel elegido por el usuario
	 * @param nivel: piso elegido
	 */
	public void setNivel(int nivel) {
		this.nivel = nivel;
	}
	
	/**
	 * Devuelve la descripcion elegida por el usuario
	 * @return descripcion del dispositivo a agregar
	 */
	public String getDescripcionDispositivo() {
		return descripcionDispositivo;
	}

	/**
	 * Establece la descripcion del dispositivo a agregar
	 * @param descripcionDispositivo
	 */
	public void setDescripcionDispositivo(String descripcionDispositivo) {
		this.descripcionDispositivo = descripcionDispositivo;
	}

	/**
	 * Se establece la session 
	 * @param session
	 */
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	


	/**
	 * Metodo para controlar el flujo de datos entre la vista que 
	 * agrega dispositivo a un piso del edificio
	 * @return "success" para ir a la vista correspondiente
	 */
	public String execute(){
		// Se toma la session actual , el edificio y se le agrega los drivers del 
		//catalogo
		Map<String,Object> session = ActionContext.getContext().getSession();
		Edificio edificio = Edificio.obtenerInstancia();	
		List<DriverDispositivo> drivers = edificio.obtenerCataloDriversDeDispositivos();

		// Mediante un bean se pasa a traves de la session objetos para mostrar en la vista	
		EdificioBean contenedor = new EdificioBean();
		contenedor.establecerNivel(this.nivel);
		contenedor.establecerCatalogoDriversDeDisposititvos(drivers);
		session.put("edificio",contenedor);
		setSession(session);
		return SUCCESS;
	}
	/**
	 * Agrega el dispositivo al piso elejido
	 * @return "agregar" para redireccionar a la vista
	 */
	public String agregar(){
		Edificio edificio = Edificio.obtenerInstancia();
		Piso piso = edificio.getPisos().get(this.nivel);
		Dispositivo dispositivoAgregar = null;
		for (DriverDispositivo driver: edificio.obtenerCataloDriversDeDispositivos()){
			//Todo: clonar el driver
			if (driverElegido.equals(driver.obtenerNombre())){
				try {
					dispositivoAgregar = new Dispositivo((DriverDispositivo)driver.getClass().newInstance());
					dispositivoAgregar.establecerDescripcion(descripcionDispositivo);
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				break;
			}
		}
		piso.agregarDispositivo(dispositivoAgregar);
		return "grabar";
	}
	
	/**
	 * Metodo que carga en run time driver de dispositivos
	 * @return
	 */
	public String cargarDispositivoDrivers(){
		CargadorDeDriver cargador = new CargadorDeDriver();
		cargador.cargar();
		return execute();
	}
	

}
