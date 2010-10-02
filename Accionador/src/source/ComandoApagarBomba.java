package source;

public class ComandoApagarBomba implements Command {
	
	private Bomba bomba;
	
	private Tanque tanque;
	
	public void ejecutar() {
		bomba.setEncendida(false);
		bomba.mostrarEstadoBomba();
		tanque.setNivelAgua(10);
		System.out.println("Nivel de agua en el tanque"+ tanque.getNivelAgua());

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
