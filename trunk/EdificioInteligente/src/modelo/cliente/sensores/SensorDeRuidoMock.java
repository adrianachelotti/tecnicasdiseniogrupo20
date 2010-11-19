package modelo.cliente.sensores;

import java.util.ArrayList;
import java.util.List;


public class SensorDeRuidoMock {
	private enum Ruido{RUIDO_BAJO,RUIDO_MEDIO,RUIDO_ALTO};
	private boolean encendido;
	private String ruido;
		
	public String getRuido() {
		return ruido;
	}

	public void setRuido(String ruido) {
		this.ruido = ruido;
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
		Ruido[] valores = Ruido.values();
		List<String> mediciones = new ArrayList<String>();
		for (Ruido ruido : valores) {
			mediciones.add(ruido.toString());
		}
		return mediciones;
	}
}
