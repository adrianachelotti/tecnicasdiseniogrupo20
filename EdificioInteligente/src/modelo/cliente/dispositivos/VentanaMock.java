package modelo.cliente.dispositivos;

public class VentanaMock{

	private int nivelDeAbertura =0;
	private boolean abierta;
	
	public int getNivelDeAbertura() {
		return nivelDeAbertura;
	}

	public void setNivelDeAbertura(int nivelDeAbertura) {
		this.nivelDeAbertura = nivelDeAbertura;
	}

	public void apagarDispositivo() {
		this.nivelDeAbertura=0;
	}

	public void encenderDispositivo() {
		this.nivelDeAbertura=100;
	}
	
	public boolean estaAbierta(){
		return this.abierta;
	}
	
	
}
