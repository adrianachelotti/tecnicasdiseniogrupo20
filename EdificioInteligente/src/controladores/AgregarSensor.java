package controladores;

import java.util.List;
import java.util.Map;

import modelo.cargador.CargadorDeDriver;
import modelo.driver.DriverSensor;
import modelo.edificio.Edificio;
import modelo.edificio.Piso;
import modelo.edificio.Sensor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import controladores.beans.EdificioBean;

@SuppressWarnings("serial")
public class AgregarSensor extends ActionSupport {
	
	/**
	 * Nivel del piso donde se agrega el sensor
	 */
	private int nivel;
	
	/**
	 * Driver elegido para agregar el sensor
	 */
	private String driverElegido;
	
	/**
	 * Descripcion del sensor ingresada por el usuario
	 */
	private String descripcionSensor;
	
	
	@SuppressWarnings("unused")
	private Map<String,Object> session ;
	
	/**
	 * Se obtiene el driver del sensor elegido por el usuario
	 * @return driver Elegido
	 */
	public String getDriverElegido() {
		return driverElegido;
	}
	
	/**
	 * Se establece el driver del sensor elegido por el usuario
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
	 * @return descripcion del sensor a agregar
	 */
	public String getDescripcionSensor() {
		return descripcionSensor;
	}

	/**
	 * Establece la descripcion del sensor a agregar
	 * @param descripcionDispositivo
	 */
	public void setDescripcionSensor(String sensorAgregar) {
		this.descripcionSensor = sensorAgregar;
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
	 * agrega un sensor a un piso del edificio
	 * @return "success" para ir a la vista correspondiente
	 */
	public String execute(){
		// Se toma la session actual , el edificio y se le agrega los drivers del 
		//catalogo
		Map<String,Object> session = ActionContext.getContext().getSession();
		Edificio edificio = Edificio.obtenerInstancia();	
		List<DriverSensor> drivers = edificio.obtenerCatalogoDriversDeSensores();

		// Mediante un bean se pasa a traves de la session objetos para mostrar en la vista	
		EdificioBean contenedor = new EdificioBean();
		contenedor.establecerNivel(this.nivel);
		contenedor.establecerCatalogoDriversDeSensores(drivers);
		session.put("edificio",contenedor);
		setSession(session);
		return SUCCESS;
	}
	/**
	 * Agrega el sensor al piso elejido
	 * @return "agregar" para redireccionar a la vista
	 */
	public String agregar(){
		Edificio edificio = Edificio.obtenerInstancia();
		Piso piso = edificio.getPisos().get(this.nivel);
		Sensor sensorAgregar = null;
		for (DriverSensor driver: edificio.obtenerCatalogoDriversDeSensores()){
			//Todo: clonar el driver
			if (driverElegido.equals(driver.obtenerNombre())){
				try {
					sensorAgregar = new Sensor(driver.getClass().newInstance());
					sensorAgregar.establecerDescripcion(descripcionSensor);
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
		piso.agregarSensores(sensorAgregar);
		return "grabar";
	}
	
	/**
	 * Metodo que carga el sensor en runtime
	 * @return
	 */
	public String cargarSensorDrivers(){
		CargadorDeDriver cargador = new CargadorDeDriver();
		cargador.cargar();
		return execute();
	}

}
