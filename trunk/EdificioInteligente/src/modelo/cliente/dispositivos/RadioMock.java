package modelo.cliente.dispositivos;

public class RadioMock{  
	private int dial;
	private char banda;
	private boolean encendida;
	
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

	public void apagarDispositivo() {
		this.encendida = false;
	}

	public void encenderDispositivo() {
		this.encendida = true;
	}

	public boolean estaEncendida(){
		return this.encendida;
	}
}
