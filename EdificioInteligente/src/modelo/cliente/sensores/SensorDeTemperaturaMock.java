package modelo.cliente.sensores;

import java.util.ArrayList;
import java.util.List;

public class SensorDeTemperaturaMock{
	private enum Temperatura{TEMPERATURA_ALTA,TEMPERATURA_MEDIA,TEMPERATURA_BAJA};
	private boolean encendido;
	private String temperatura;
		
	/**
	 * Obtiene la medicion de temperatura.
	 * @return medicion de temperatura.
	 */
	public String getTemperatura() {
		return temperatura;
	}

	/**
	 * Establece la medicion de temperatura.
	 * @param temperatura
	 */
	public void setTemperatura(String temperatura) {
		this.temperatura = temperatura;
	}

	/**
	 * Enciende el sensor de temperatura.
	 */
	public void encender(){
		this.encendido=true;
	}
	
	/**
	 * Apaga el sensor de temperatura.
	 */
	public void apagar(){
		this.encendido=false;
	}
	
	/**
	 * Determina si el sensor de temperatura esta encendido.
	 * @return true si esta encendido
	 * 		   false en caso contrario.
	 */
	public boolean estaEncendido() {
		return this.encendido;
	}
	
	/**
	 * Obtiene las mediciones posible que realiza el sensor.
	 * @return mediciones posibles del sensor.
	 */
	public List<String> medicionesPosibles(){
		Temperatura[] valores = Temperatura.values();
		List<String> mediciones = new ArrayList<String>();
		for (Temperatura temperatura : valores) {
			mediciones.add(temperatura.toString());
		}
		return mediciones;
	}
}
