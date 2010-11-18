package modelo.cliente.dispositivos;

public class TelevisorMock{
	
	private int canalActual;
	private int volumen;
	private boolean encendido;

	public void apagar() {			
		this.volumen = 0;
		this.encendido = true;
	}

	public void encender() {
		this.canalActual=4;
		this.volumen = 20;
		this.encendido = false;
	}
	
	public boolean estaEncendido(){
		return this.encendido;
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
