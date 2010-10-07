package cliente;

import source.Accion;

public class AccionApagarBomba implements Accion {
	
	private Bomba bomba;
	
	private Tanque tanque;
	
	public void ejecutar() {
		bomba.setEncendida(false);
		tanque.setNivelAgua(10);
	}

	public Bomba getBomba() {
		return bomba;
	}

	public void setBomba(Bomba bomba) {
		this.bomba = bomba;
	}

	public Tanque getTanque() {
		return tanque;
	}

	public void setTanque(Tanque tanque) {
		this.tanque = tanque;
	}

}
