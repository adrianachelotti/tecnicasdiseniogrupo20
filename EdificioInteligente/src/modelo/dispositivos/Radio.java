package modelo.dispositivos;

public class Radio extends Dispositivo {
 
 
	private int dial;
	private char banda;
	private boolean encendida;
	
	public boolean isEncendida() {
		return encendida;
	}

	public void setEncendida(boolean encendida) {
		this.encendida = encendida;
	}

	public int getDial() {
		return dial;
	}

	public void setDial(int dial) {
		this.dial = dial;
	}

	public char getBanda() {
		return banda;
	}

	public void setBanda(char banda) {
		this.banda = banda;
	}


	
	protected void apagarDispositivo() {
		this.setEncendida(false);

	}

	protected void encenderDispositivo() {
		this.setEncendida(false);
	}

}
