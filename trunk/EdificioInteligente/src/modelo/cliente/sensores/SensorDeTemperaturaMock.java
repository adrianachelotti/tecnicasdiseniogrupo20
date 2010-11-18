package modelo.cliente.sensores;

import java.util.ArrayList;
import java.util.List;

public class SensorDeTemperaturaMock{
	enum Temperatura{TEMPERATURA_ALTA,TEMPERATURA_MEDIA,TEMPERATURA_BAJA};
	private boolean encendido;
	private String temperatura;
		
	public String getTemperatura() {
		return temperatura;
	}

	public void setTemperatura(String temperatura) {
		this.temperatura = temperatura;
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
		Temperatura[] valores = Temperatura.values();
		List<String> mediciones = new ArrayList<String>();
		for (Temperatura temperatura : valores) {
			mediciones.add(temperatura.toString());
		}
		return mediciones;
	}
}
