package source;

public class ComandoPrenderBomba implements Command{

	private Bomba bomba;
	
	public void ejecutar() {
		this.bomba.setEncendida(true);
		this.bomba.mostrarEstadoBomba();
		
		
	}
	public Bomba getBomba() {
		return bomba;
	}
	public void setBomba(Bomba bomba) {
		this.bomba = bomba;
	}

	
}
