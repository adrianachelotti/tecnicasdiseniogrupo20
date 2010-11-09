package dispositivos;

public class Ventana extends Dispositivo {

	private int nivelDeAbertura =0;

	
	public int getNivelDeAbertura() {
		return nivelDeAbertura;
	}

	public void setNivelDeAbertura(int nivelDeAbertura) {
		this.nivelDeAbertura = nivelDeAbertura;
	}

	protected void apagarDispositivo() {
		this.nivelDeAbertura=0;

	}

	protected void encenderDispositivo() {
		this.nivelDeAbertura=100;

	}

}
