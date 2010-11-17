package modelo.dispositivos;

public class Radio extends Dispositivo {
  
	private int dial;
	private char banda;
	
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

	}

	protected void encenderDispositivo() {

	}

}
