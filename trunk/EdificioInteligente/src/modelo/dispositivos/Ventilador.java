package modelo.dispositivos;

public class Ventilador extends Dispositivo {

	private int velocidad;
	
	protected void apagarDispositivo() {
		this.velocidad=0;
	}

	protected void encenderDispositivo() {
		this.velocidad=50;
	}
	
	public void aumentarVelocidad(int aumento){
		this.velocidad+=aumento;
		if(velocidad>100){
			velocidad = 100;
		}
	}
}
