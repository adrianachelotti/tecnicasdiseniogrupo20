package dispositivos;

public class Puerta extends Dispositivo {
	
	private int nivelAbertura;
	
	private boolean bloqueada;
	

	public int getNivelAbertura() {
		return nivelAbertura;
	}

	public void setNivelAbertura(int nivelAbertura) {
		this.nivelAbertura = nivelAbertura;
	}

	public boolean isBloqueada() {
		return bloqueada;
	}

	public void setBloqueada(boolean bloqueada) {
		this.bloqueada = bloqueada;
	}

	protected void apagarDispositivo() {
		this.bloqueada=true;
		this.nivelAbertura=0;

	}

	protected void encenderDispositivo() {
		this.bloqueada=false;
		this.nivelAbertura=100;
	

	}

}
