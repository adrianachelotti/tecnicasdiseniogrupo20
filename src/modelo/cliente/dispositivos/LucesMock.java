package modelo.cliente.dispositivos;

/**
 * Clase que modela el mockup de una luz.
 * @author Grupo20
 *
 */
public class LucesMock {
	
	private int nivelLuminosidad;

	/**
	 * Obtiene el nivel de luminosidad medido.
	 * @return
	 */
	public int getNivelLuminosidad() {
		return nivelLuminosidad;
	}

	/**
	 * Establece el nivel de luminocidad medido.
	 * @param nivelLuminosidad nivel de luminocidad medido.
	 */
	public void setNivelLuminosidad(int nivelLuminosidad) {
		this.nivelLuminosidad = nivelLuminosidad;
	}

	/**
	 * Disminuye el nivel de luminocidad en la cantidad pasada como parametro.
	 * @param disminuirLuz disminucion del nivel de luminocidad a realizar.
	 */
	public void disminuirLuminosidad(int disminuirLuz){
		if(disminuirLuz>this.nivelLuminosidad){
			this.nivelLuminosidad-=disminuirLuz;			
		}else{
			this.nivelLuminosidad=0;
		}
	}
	
	/**
	 * Aumenta el nivel de luminocidad en la cantidad pasada como parametro.
	 * @param aumentarLuz aumento del nivel de luminocidad a realizar.
	 */
	public void aumentarLuminosidad(int aumentarLuz){
		if(this.nivelLuminosidad<100-aumentarLuz){
			this.nivelLuminosidad+=aumentarLuz;
		}else{
			this.nivelLuminosidad=100;
		}
	}

	/**
	 * Apaga el dispositivo.
	 */
	public void apagar() {
		this.nivelLuminosidad=0;
	}

	/**
	 * Enciende el dispositivo.
	 */
	public void encender() {
		this.nivelLuminosidad=100;
	}
}
