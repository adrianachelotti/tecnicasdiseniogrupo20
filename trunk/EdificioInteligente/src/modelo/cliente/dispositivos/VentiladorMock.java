package modelo.cliente.dispositivos;

/**
 * Clase que modela el mockup de un ventilador.
 * @author Grupo20
 *
 */
public class VentiladorMock {

	private int velocidad;
	
	/**
	 * Apaga el ventilador.
	 */
	public void apagarDispositivo() {
		this.velocidad=0;
	}

	/**
	 * Enciende el ventilador.
	 */
	public void encenderDispositivo() {
		this.velocidad=50;
	}
	
	/**
	 * Aumenta la velocidad del ventilador.
	 * @param aumento aumento a efectuar.
	 */
	public void aumentarVelocidad(int aumento){
		this.velocidad+=aumento;
		if(velocidad>100){
			velocidad = 100;
		}
	}
	
	/**
	 * Obtiene la velocidad del ventilador.
	 * @return velocidad del ventilador
	 */
	public int getVelocidad(){
		return this.velocidad;
	}
}
