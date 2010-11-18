package modelo.cliente.sensores;

import java.util.ArrayList;
import java.util.List;

public class SensorDeHumoMock {
	
	enum Humo{HUMO_EXISTENTE,HUMO_NO_EXISTENTE};
	private boolean encendido;
	private String humo;
		
	public String getHumo() {
		return humo;
	}

	public void setHumo(String humo) {
		this.humo = humo;
	}

	public void encender(){
		this.encendido=true;
	}
	
	public void apagar(){
		this.encendido=false;
	}
	
	public boolean estaEncendido() {
		return this.encendido;
	}
	
	public List<String> medicionesPosibles(){
		Humo[] valores = Humo.values();
		List<String> mediciones = new ArrayList<String>();
		for (Humo humo : valores) {
			mediciones.add(humo.toString());
		}
		return mediciones;
	}
}
