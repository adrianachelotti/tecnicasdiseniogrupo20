package controladores;

import modelo.accion.AccionPrenderDispositivo;
import modelo.cliente.driver.dispositivos.CalefactorDriver;
import modelo.cliente.driver.dispositivos.LucesDriver;
import modelo.cliente.driver.dispositivos.PuertaDriver;
import modelo.cliente.driver.sensores.SensorDeHumoDriver;
import modelo.cliente.driver.sensores.SensorDeRuidoDriver;
import modelo.cliente.driver.sensores.SensorDeTemperaturaDriver;
import modelo.edificio.Dispositivo;
import modelo.edificio.Edificio;
import modelo.edificio.Piso;
import modelo.manejadorDeSucesos.Suceso;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class Autenticador extends ActionSupport {

	String usuario;
	
	String password;
	/**
	 * Obtiene el usuario ingresado en la autenticacion
	 * @return el usuario
	 */
	public String getUsuario() {
		return usuario;
	}
	
	/**
	 * Establece el usuario que se ingresa en la autenticacion
	 * @param usuario usuario ingresado
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	/**
	 * Obtiene el password del usuario
	 * @return el password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * Establece el password ingresado por el usuario 
	 * @param password password ingresado
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * Metodo para iniciar al edificio con 2 pisos, dispositivos y sensores 
	 * cargado por default 
	 */
	private void inicializarEdificio(){
		Edificio edificio = Edificio.obtenerInstancia();
		Piso piso0 = new Piso(0);
		Dispositivo dispositivo = new Dispositivo(new CalefactorDriver());
		dispositivo.establecerDescripcion("Estufa");
		piso0.agregarDispositivo(dispositivo);
		AccionPrenderDispositivo accion = new AccionPrenderDispositivo();
		accion.establecerDispositivo(dispositivo);
		piso0.obtenerManejadorDeSucesos().suscribirImplicacion(accion, new Suceso("TEMPERATURA_BAJA"));
		edificio.agregarPiso(piso0);
		edificio.agregarPiso(new Piso(1));
		//TODO: cambiar esta hardcodeado
		edificio.agregarDriverDispositivo(new CalefactorDriver());
		edificio.agregarDriverDispositivo(new LucesDriver());
		edificio.agregarDriverDispositivo(new PuertaDriver());
		edificio.agregarDriverSensor(new SensorDeHumoDriver());
		edificio.agregarDriverSensor(new SensorDeRuidoDriver());
		edificio.agregarDriverSensor(new SensorDeTemperaturaDriver());
		//CargadorDeDriver cargador = new CargadorDeDriver();
		//cargador.cargar();
	}
	
	/**
	 * Controla el ingreso del usuario
	 */
	public String execute(){
		if(this.usuario.equalsIgnoreCase("root")&&this.password.equalsIgnoreCase("root")){
			inicializarEdificio();
			return SUCCESS;
		}else{
			addActionError("Datos Incorrectos");
			return "error";
			
		}	
	}
}
