package modelo.dispositivos;

public class Calefactor extends Dispositivo {

	private int temperatura;
	

	public int getTemperatura() {
		return temperatura;
	}

	public void setTemperatura(int temperatura) {
		this.temperatura = temperatura;
	}
	
	protected void apagarDispositivo() {
		this.establecerEncendido(false);
		this.temperatura = 0;		
	}

	protected void encenderDispositivo() {
		this.establecerEncendido(true);
		this.temperatura = 50;
	}

}
