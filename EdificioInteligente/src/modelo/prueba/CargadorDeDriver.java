package modelo.prueba;

import java.io.File;

import modelo.edificio.Dispositivo;
import modelo.edificio.Edificio;
import modelo.edificio.Sensor;

public class CargadorDeDriver extends Thread {
	
	private static final String DIRECTORIO_ORIGEN_DISPOSITIVOS = "drivers/dispositivos/";
	private static final String DIRECTORIO_ORIGEN_SENSORES = "drivers/sensores/";
	private static final String DIRECTORIO_DESTINO_DISPOSITIVOS = "drivers/dispositivos/cargados/";
	private static final String DIRECTORIO_DESTINO_SENSORES = "drivers/sensores/cargados/";
	private static final String EXT_CLASS = ".class";
		
	private void moverArchivos(String nombreArchivo,String directorioOrigen,String directorioDestino){
		File archivo= new File(directorioOrigen,nombreArchivo); 
		File dir = new File(directorioDestino); 
		archivo.renameTo(new File(dir, archivo.getName()));
	}
	
	@SuppressWarnings("unchecked")
	private void moverDriversDispositivos(File directorioDrivers,FiltroDeArchivos filtro){
		String[] listaArchivos = directorioDrivers.list(filtro);
		Edificio edificio = Edificio.obtenerInstancia();
       	for(int i=0; i<listaArchivos.length; i++){
       		String nombreArchivo = listaArchivos[i];
       		moverArchivos(listaArchivos[i],DIRECTORIO_ORIGEN_DISPOSITIVOS,DIRECTORIO_DESTINO_DISPOSITIVOS);
       		String nombreClase = nombreArchivo.substring(0, nombreArchivo.lastIndexOf("."));
			try {
				Class clase = Class.forName(nombreClase);
				edificio.agregarDriverDispositivo((Dispositivo) clase.newInstance());
			} catch (Exception e) {
				//TODO ver manejo exception
				e.printStackTrace();
			}
		}
	}
		
	@SuppressWarnings("unchecked")
	private void moverDriversSensores(File directorioDrivers,FiltroDeArchivos filtro){
		String[] listaArchivos = directorioDrivers.list(filtro);
		Edificio edificio = Edificio.obtenerInstancia();
		for(int i=0; i<listaArchivos.length; i++){
			String nombreArchivo = listaArchivos[i]; 
			moverArchivos(nombreArchivo,DIRECTORIO_ORIGEN_SENSORES,DIRECTORIO_DESTINO_SENSORES);
			String nombreClase = nombreArchivo.substring(0, nombreArchivo.lastIndexOf("."));
			try {
				Class clase = Class.forName(nombreClase);
				edificio.agregarDriverSensor((Sensor)clase.newInstance());
			} catch (Exception e) {
				//TODO ver manejo exception
				e.printStackTrace();
			}
	   }
	}
	
	public void run() {
		File directorioDispositivos=new File(DIRECTORIO_ORIGEN_DISPOSITIVOS);
		File directorioSensores=new File(DIRECTORIO_ORIGEN_SENSORES);
		FiltroDeArchivos filtro = new FiltroDeArchivos(EXT_CLASS);
		while(true){
			try {
				Thread.sleep(20000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			moverDriversDispositivos(directorioDispositivos,filtro);
			moverDriversSensores(directorioSensores,filtro);
		}
	}

	public static void main (String args[]) {
		Edificio edificio = Edificio.obtenerInstancia();
				
		CargadorDeDriver uno = new CargadorDeDriver();
		uno.start();
		while(true){
			System.out.println("Drivers Sens.: " + edificio.getDriversSensores().size());
			System.out.println("Drivers Disp.: " + edificio.getDriversDispositivos().size());
		}
	}
}