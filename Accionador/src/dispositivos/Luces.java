package dispositivos;

public class Luces extends Dispositivo{
	
	private int nivelLuminosidad;

	public int getNivelLuminosidad() {
		return nivelLuminosidad;
	}

	public void setNivelLuminosidad(int nivelLuminosidad) {
		this.nivelLuminosidad = nivelLuminosidad;
	}

	public void disminuirLuminosidad(int disminuirLuz){
		if(disminuirLuz>this.nivelLuminosidad){
			this.nivelLuminosidad-=disminuirLuz;
			
		}else{
			this.nivelLuminosidad=0;
		}
		
	}
	public void aumentarLuminosidad(int aumentarLuz){
		if(this.nivelLuminosidad<100-aumentarLuz){
			this.nivelLuminosidad+=aumentarLuz;
			
		}else{
			this.nivelLuminosidad=100;
		}
		
	}

	protected void apagarDispositivo() {
		this.nivelLuminosidad=0;

	}

	protected void encenderDispositivo() {
		
		this.nivelLuminosidad=100;

	}
	

}
