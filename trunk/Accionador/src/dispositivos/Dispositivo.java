package dispositivos;

import edificio.Ubicacion;

public abstract class Dispositivo {
	
	private Ubicacion ubicacion;	
	
	private boolean encendido;
	
	protected abstract void encenderDispositivo();
	
	protected abstract void apagarDispositivo();
	
	public final void apagar(){
		this.encendido =false;
		apagarDispositivo();	
	}
	
	/**
	 * template method 
	 */
	public final void encender(){
		this.encendido =true;
		encenderDispositivo();
		
	}
	
	
	public Ubicacion obtenerUbicacion() {
		return ubicacion;
	}

	public void establecerUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}
	public boolean estaEncendido(){
		return this.encendido;
	}
	public void establecerEncendido(boolean encendido){
		this.encendido = encendido;
	}

}
