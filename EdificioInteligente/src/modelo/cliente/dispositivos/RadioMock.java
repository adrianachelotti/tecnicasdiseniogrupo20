package modelo.cliente.dispositivos;

/**
 * Clase que modela el mockup de una radio.
 * @author Grupo20
 *
 */
public class RadioMock{  
	private int dial;
	private char banda;
	private boolean encendida;
	
	/**
	 * Obtiene el dial de la radio.
	 * @return dial de la radio.
	 */
	public int getDial() {
		return dial;
	}

	/**
	 * Establece el dial de la radio.
	 * @param dial dial a establecer.
	 */
	public void setDial(int dial) {
		this.dial = dial;
	}

	/**
	 * Obtiene la banda de la radio.
	 * @return banda de la radio.
	 */
	public char getBanda() {
		return banda;
	}

	/**
	 * Establede la banda de la radio.
	 * @param banda banda a establecer.
	 */
	public void setBanda(char banda) {
		this.banda = banda;
	}

	/**
	 * Apaga la radio.
	 */
	public void apagarDispositivo() {
		this.encendida = false;
	}

	/**
	 * Enciende la radio.
	 */
	public void encenderDispositivo() {
		this.encendida = true;
	}

	/**
	 * Determina si la radio esta encendida.
	 * @return true si la radio esta encendida
	 * 		   false en caso contrario.
	 */
	public boolean estaEncendida(){
		return this.encendida;
	}
}
