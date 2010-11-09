package dispositivos;

public class RegadoraDePasto extends Dispositivo {

	private boolean abrirCanilla ;
	
	protected void apagarDispositivo() {
		this.abrirCanilla = false;
	}

	protected void encenderDispositivo() {
		this.abrirCanilla = true;

	}

}
