package modelo.sensores;

import modelo.edificio.Ubicacion;
import modelo.manejadorDeSucesos.Suceso;

public abstract class Sensor {
	
	private Ubicacion ubicacion;
	
	private boolean encendido;
	
	public abstract int obtenerMedicion();
	
	public abstract Suceso notificarSuceso();
		
	public void habilitar(){
		this.encendido=true;
	}
	
	public void deshabilitar(){
		this.encendido=false;
	}
		
	public boolean estaHabilitado() {
		return encendido;
	}

	public Ubicacion getUbicacion() {
		return ubicacion;
	}

	public void establecerUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}
		
}
