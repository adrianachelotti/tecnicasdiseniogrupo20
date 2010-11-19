package controladores;

import java.util.List;
import java.util.Map;
import modelo.driver.CalefactorDriver;
import modelo.driver.DriverDispositivo;
import modelo.edificio.Dispositivo;
import modelo.edificio.Edificio;
import modelo.edificio.Piso;


import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import controladores.beans.EdificioBean;

@SuppressWarnings("serial")
public class AgregarDispositivo extends ActionSupport {

	private int |nivel;
	
	private String driverElegido;
	

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
		edificio.agregarDriverDispositivo(new CalefactorDriver());
		edificio.agregarDriverDispositivo(new CalefactorDriver());
		edificio.agregarDriverDispositivo(new CalefactorDriver());
		List<DriverDispositivo> drivers = edificio.getCataloDriversDeDispositivos();

		// Mediante un bean se pasa a traves de la session objetos para mostrar en la vista	
		EdificioBean contenedor = new EdificioBean();
		contenedor.setNivel(this.nivel);
		contenedor.setDispositivosDrivers(drivers);
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
		for (DriverDispositivo driver: edificio.getCataloDriversDeDispositivos()){
			if (driverElegido.equals(driver.getNombre())){
				dispositivoAgregar = new Dispositivo(driver);
				break;
			}
		}
		piso.agregarDispositivo(dispositivoAgregar);
		return "grabar";
	}
	
	

}
