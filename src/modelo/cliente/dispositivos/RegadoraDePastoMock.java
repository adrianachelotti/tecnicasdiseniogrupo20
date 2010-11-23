package modelo.cliente.dispositivos;

public class RegadoraDePastoMock{

	private boolean abrirCanilla ;
	
	protected void apagarDispositivo() {
		this.abrirCanilla = false;
	}

	protected void encenderDispositivo() {
		this.abrirCanilla = true;
	}

	public boolean estaCanillaAbierta() {
		return abrirCanilla;
	}
}
