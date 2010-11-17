package modelo.dispositivos;

public class Televisor extends Dispositivo {
	
	private int canalActual;
	private int volumen;

	protected void apagarDispositivo() {			
		this.volumen = 0;
	}

	protected void encenderDispositivo() {
		this.canalActual=4;
		this.volumen = 20;
	}
	
	public int getCanalActual() {
		return canalActual;
	}

	public void setCanalActual(int canalActual) {
		this.canalActual = canalActual;
	}

	public int getVolumen() {
		return volumen;
	}

	public void setVolumen(int volumen) {
		this.volumen = volumen;
	}
	
	public void cambiarCanal(int canal){
		this.canalActual  = canal;
	}
	
	public void aumentarCanal(){
		this.canalActual++;
	}
	
	public void disminuirCanal(){
		this.canalActual--;
	}
	
	public void aumentarVolumen(){
		this.volumen++;
	}
	
	public void disminuirVolumen(){
		this.volumen--;
	}
}
