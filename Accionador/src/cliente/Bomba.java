package cliente;

public class Bomba {
	
	boolean encendida;
	
	public boolean isEncendida() {
		return encendida;
	}
	public void setEncendida(boolean encendida) {
		this.encendida = encendida;
	}
	public String toString(){
		return this.getClass().toString();
	}
	
}
