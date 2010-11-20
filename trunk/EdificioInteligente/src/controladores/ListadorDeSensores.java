package controladores;

import java.util.Map;

import modelo.edificio.Dispositivo;
import modelo.edificio.Edificio;
import modelo.edificio.Sensor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import controladores.beans.EdificioBean;

public class ListadorDeSensores extends ActionSupport {
	private Map<String, Object> session;
	
	private int nivel;
	
	private String idSensor;
	
	private String medicionCambiar;
	
	
	
	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}


	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	public String getIdSensor() {
		return idSensor;
	}
	
	public void setIdSensor(String idSensor) {
		this.idSensor = idSensor;
	}
	public String getMedicionCambiar() {
		return medicionCambiar;
	}

	public void setMedicionCambiar(String medicionCambiar) {
		this.medicionCambiar = medicionCambiar;
	}

	public String execute(){
		Map<String,Object> session = ActionContext.getContext().getSession();
		Edificio edificio = Edificio.obtenerInstancia();
		EdificioBean contenedor = new EdificioBean();
		contenedor.establecerListadoDeSensores(edificio.getPisos().get(this.nivel).obtenerSensores());
		contenedor.establecerNivel(this.nivel);
		session.put("piso", contenedor);
		setSession(session);
		return "success";
	}
	
	public String seleccionarPisos(){		
		Map<String,Object> session = ActionContext.getContext().getSession();
		Edificio edificio = Edificio.obtenerInstancia();
		EdificioBean contenedor = new EdificioBean();
		contenedor.establecerListadoDePisos(edificio.getPisos());
		session.put("edificio", contenedor);
		setSession(session);
		return "listarPisos";
	}
	
	public String cambiarMedicion(){
		Edificio edificio = Edificio.obtenerInstancia();
		int indice = Integer.parseInt(idSensor);
		Sensor sensor = edificio.getPisos().get(this.nivel).obtenerSensores().get(indice);
		sensor.establecerMedicion(medicionCambiar);
		return execute();
	}
	
	


}
