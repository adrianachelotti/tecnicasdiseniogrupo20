package modelo.cliente.dispositivos;

/**
 * Clase que modela el mockup de una ragadora de pasto.
 * @author Grupo20
 *
 */
public class RegadoraDePastoMock{

	private boolean abrirCanilla ;
	
	/**
	 * Apaga la regadora de pasto.
	 */
	protected void apagarDispositivo() {
		this.abrirCanilla = false;
	}

	/**
	 * Enciende la regadora de pasto.
	 */
	protected void encenderDispositivo() {
		this.abrirCanilla = true;
	}

	/**
	 * Determina si la canilla esta abierta.
	 * @return true si la canilla esta abierta.
	 * 		   false en caso contrario
	 */
	public boolean estaCanillaAbierta() {
		return abrirCanilla;
	}
}
