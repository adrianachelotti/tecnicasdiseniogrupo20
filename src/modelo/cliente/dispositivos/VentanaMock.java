package modelo.cliente.dispositivos;
/**
 * Clase que modela el mockup de una ventana.
 * @author Grupo20
 *
 */
public class VentanaMock{

	private int nivelDeAbertura =0;
	private boolean abierta;
	
	/**
	 * Obtiene el nivel de abertura.
	 * @return nivel de abertura.
	 */
	public int getNivelDeAbertura() {
		return nivelDeAbertura;
	}

	/**
	 * Establece el nivel de abertura.
	 * @param nivelDeAbertura nivel de abertura.
	 */
	public void setNivelDeAbertura(int nivelDeAbertura) {
		this.nivelDeAbertura = nivelDeAbertura;
	}

	/**
	 * Cierra la ventana.
	 */
	public void apagarDispositivo() {
		this.nivelDeAbertura=0;
	}

	/**
	 * Abre la ventana.
	 */
	public void encenderDispositivo() {
		this.nivelDeAbertura=100;
	}
	
	/**
	 * Determina si la ventana esta abierta.
	 * @return true si se encuentra abierta.
	 * 		   false en caso contrario.
	 */
	public boolean estaAbierta(){
		return this.abierta;
	}
	
	
}
