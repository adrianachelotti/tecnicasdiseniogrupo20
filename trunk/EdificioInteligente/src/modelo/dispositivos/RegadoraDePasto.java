package modelo.dispositivos;

public class RegadoraDePasto extends Dispositivo {

	private boolean abrirCanilla ;
	
	protected void apagarDispositivo() {
		this.abrirCanilla = false;
	}

	protected void encenderDispositivo() {
		this.abrirCanilla = true;
	}

	public boolean isAbrirCanilla() {
		return abrirCanilla;
	}

	public void setAbrirCanilla(boolean abrirCanilla) {
		this.abrirCanilla = abrirCanilla;
	}
	
	

}
