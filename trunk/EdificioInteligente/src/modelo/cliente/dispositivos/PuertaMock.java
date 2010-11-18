package modelo.cliente.dispositivos;

public class PuertaMock{
	private int nivelAbertura;
	private boolean abierta;
	
	public int getNivelAbertura() {
		return nivelAbertura;
	}

	public void setNivelAbertura(int nivelAbertura) {
		this.nivelAbertura = nivelAbertura;
	}
	
	public void abrir() {
		this.abierta=true;
		this.nivelAbertura=50;
	}

	public void cerrar() {
		this.abierta=false;
		this.nivelAbertura=0;
	}
	
	public boolean estaAbierta() {
		return this.abierta;
	}
}
