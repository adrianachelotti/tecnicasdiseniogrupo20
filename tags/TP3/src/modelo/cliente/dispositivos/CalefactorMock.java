package modelo.cliente.dispositivos;


public class CalefactorMock{
	
	private int temperatura;
	private boolean encendido;
		
	public int getTemperatura() {
		return temperatura;
	}

	public void setTemperatura(int temperatura) {
		this.temperatura = temperatura;
	}
	
	public boolean isEncendido() {
		return encendido;
	}

	public void apagar() {
		this.encendido = false;
		this.temperatura = 0;
	}

	public void encender() {
		this.encendido = false;
		this.temperatura = 50;
	}
	
}
