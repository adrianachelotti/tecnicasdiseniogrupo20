package controladores;

import modelo.cliente.driver.dispositivos.*;
import modelo.cliente.driver.sensores.*;
import modelo.edificio.Edificio;
import modelo.edificio.Piso;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class Autenticador extends ActionSupport {

	String usuario;
	
	String password;
	
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	private void inicializarEdificio(){
		Edificio edificio = Edificio.obtenerInstancia();	
		edificio.agregarPiso(new Piso(0));
		edificio.agregarPiso(new Piso(1));
		//TODO: cambiar esta hardcodeado
		edificio.agregarDriverDispositivo(new CalefactorDriver());
		edificio.agregarDriverDispositivo(new LucesDriver());
		edificio.agregarDriverDispositivo(new PuertaDriver());
		edificio.agregarDriverSensor(new SensorDeHumoDriver());
		edificio.agregarDriverSensor(new SensorDeRuidoDriver());
		edificio.agregarDriverSensor(new SensorDeTemperaturaDriver());
	}

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
