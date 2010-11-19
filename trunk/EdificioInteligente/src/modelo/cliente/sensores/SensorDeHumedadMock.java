package modelo.cliente.sensores;

import java.util.ArrayList;
import java.util.List;


public class SensorDeHumedadMock {
	
	private enum Humedad{HUMEDAD_BAJA,HUMEDAD_MEDIA,HUMEDAD_ALTA};
	private boolean encendido;
	private String humedad;
		
	public String getHumedad() {
		return humedad;
	}

	public void setHumedad(String humedad) {
		this.humedad = humedad;
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
		Humedad[] valores = Humedad.values();
		List<String> mediciones = new ArrayList<String>();
		for (Humedad humedad : valores) {
			mediciones.add(humedad.toString());
		}
		return mediciones;
	}
}
