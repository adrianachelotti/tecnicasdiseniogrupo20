package modelo.cliente.dispositivos;

/**
 * Clase que modela el mockup de un televisor.
 * @author Grupo20
 *
 */
public class TelevisorMock{
	
	private int canalActual;
	private int volumen;
	private boolean encendido;

	/**
	 * Apaga el televisor.
	 */
	public void apagar() {			
		this.volumen = 0;
		this.encendido = true;
	}

	/**
	 * Enciende el televisor.
	 */
	public void encender() {
		this.canalActual=4;
		this.volumen = 20;
		this.encendido = false;
	}
	
	/**
	 * Determina si el televisor esta encendido.
	 * @return true si esta encendido.
	 *         false en caso contrario. 
	 */
	public boolean estaEncendido(){
		return this.encendido;
	}
	
	/**
	 * Obtiene el canal actual.
	 * @return
	 */
	public int getCanalActual() {
		return canalActual;
	}

	/**
	 * Establece el canal actual.
	 * @param canalActual canal a establecer.
	 */
	public void setCanalActual(int canalActual) {
		this.canalActual = canalActual;
	}

	/**
	 * Obtiene el volumen del televisor.
	 * @return volumen del televisor.
	 */
	public int getVolumen() {
		return volumen;
	}

	/**
	 * Establece el volumen del televisor.
	 * @param volumen volumen a establecer.
	 */
	public void setVolumen(int volumen) {
		this.volumen = volumen;
	}
	
	/**
	 * Aumenta el canal en una unidad.	
	 */
	public void aumentarCanal(){
		this.canalActual++;
	}
	
	/**
	 * Dismiuye el canal en una unidad.
	 */
	public void disminuirCanal(){
		this.canalActual--;
	}
	
	/**
	 * Aumenta el volumen en una unidad.
	 */
	public void aumentarVolumen(){
		this.volumen++;
	}
	
	/**
	 * Disminuye el volumen en una unidad.
	 */
	public void disminuirVolumen(){
		this.volumen--;
	}
}
