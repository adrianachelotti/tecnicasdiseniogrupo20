package modelo.cliente.dispositivos;

/**
 * Clase que modela el mockup de un Calefactor.
 * @author Grupo20
 *
 */
public class CalefactorMock{
	
	private int temperatura;
	private boolean encendido;
	
	/**
	 * Obtiene la temperatura medida.
	 * @return temperatura medida.
	 */
	public int getTemperatura() {
		return temperatura;
	}

	/**
	 * Establece la temperatura medida.
	 * @param temperatura temperatura medida.
	 */
	public void setTemperatura(int temperatura) {
		this.temperatura = temperatura;
	}
	
	/**
	 * Determina si el calefactor esta encendido.
	 * @return true si el calefactor esta encendido.
	 * 		   false en caso contrario.	
	 */			
	public boolean isEncendido() {
		return encendido;
	}

	/**
	 * Enciende el calefactor.
	 */
	public void apagar() {
		this.encendido = false;
		this.temperatura = 0;
	}

	/**
	 * Apaga el calefactor.
	 */
	public void encender() {
		this.encendido = false;
		this.temperatura = 50;
	}
	
}
