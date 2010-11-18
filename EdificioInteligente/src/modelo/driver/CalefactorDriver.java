package modelo.driver;

import modelo.cliente.dispositivos.CalefactorMock;

public class CalefactorDriver implements DriverDispositivo{

	private CalefactorMock calefactor;
	
	public CalefactorDriver(){
		calefactor = new CalefactorMock();
	}
	
	public void apagar() {
		calefactor.apagar();
	}

	public void encender() {
		calefactor.encender();
	}

	public boolean isEncendido() {
		return calefactor.isEncendido();
	}
	
}
