package modelo.cliente.dispositivos;

public class VentiladorMock {

	private int velocidad;
	
	public void apagarDispositivo() {
		this.velocidad=0;
	}

	public void encenderDispositivo() {
		this.velocidad=50;
	}
	
	public void aumentarVelocidad(int aumento){
		this.velocidad+=aumento;
		if(velocidad>100){
			velocidad = 100;
		}
	}
	
	public int getVelocidad(){
		return this.velocidad;
	}
}
