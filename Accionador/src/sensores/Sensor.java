package sensores;

import edificio.Ubicacion;
import source.Suceso;

public abstract class Sensor {
	
	private Ubicacion ubicacion;
	
	public abstract int obtenerMedicion();
	
	public abstract void habilitar();
	
	public abstract void deshabilitar();
	
	public abstract Suceso notificarSuceso();

	public Ubicacion getUbicacion() {
		return ubicacion;
	}

	public void establecerUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}
}
