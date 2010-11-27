package modelo.cliente.dispositivos;

/**
 * Clase que modela el mockup de una puerta.
 * @author Grupo20
 *
 */
public class PuertaMock{
	private int nivelAbertura;
	private boolean abierta;
	
	/**
	 * Obtiene el nivel de apertura de la puerta.
	 * @return nivel de apertura de la puerta.
	 */
	public int getNivelAbertura() {
		return nivelAbertura;
	}

	/**
	 * Establece el nivel de apertura de la puerta.
	 * @param nivelAbertura nivel de apertura de la puerta.
	 */
	public void setNivelAbertura(int nivelAbertura) {
		this.nivelAbertura = nivelAbertura;
	}
	
	/**
	 * Abre la puerta.
	 */
	public void abrir() {
		this.abierta=true;
		this.nivelAbertura=50;
	}

	/**
	 * Cierra la puerta.
	 */
	public void cerrar() {
		this.abierta=false;
		this.nivelAbertura=0;
	}
	
	/**
	 * Determina si la puerta se encuentra abierta.
	 * @return true si esta abierta
	 * 		   false en caso contrario.	
	 */
	public boolean estaAbierta() {
		return this.abierta;
	}
}
