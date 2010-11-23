package modelo.cargador;

import java.io.File;

import modelo.driver.DriverDispositivo;
import modelo.driver.DriverSensor;
import modelo.edificio.Edificio;

public class CargadorDeDriver{
	
	private static final String DIRECTORIO_BASE = "C:/Users/Dario/Desktop/TecnicasII/trunk/";
	private static final String DIRECTORIO_ORIGEN_DISPOSITIVOS = DIRECTORIO_BASE+"drivers/dispositivos/";
	private static final String DIRECTORIO_ORIGEN_SENSORES = DIRECTORIO_BASE+"drivers/sensores/";
	private static final String DIRECTORIO_DESTINO_DISPOSITIVOS = DIRECTORIO_BASE+"drivers/dispositivos/cargados/";
	private static final String DIRECTORIO_DESTINO_SENSORES = DIRECTORIO_BASE+"drivers/sensores/cargados/";
	private static final String EXT_CLASS = ".class";
		
	private void moverArchivos(String nombreArchivo,String directorioOrigen,String directorioDestino){
		File archivo= new File(directorioOrigen,nombreArchivo); 
		File dir = new File(directorioDestino); 
		archivo.renameTo(new File(dir, archivo.getName()));
	}
	
	@SuppressWarnings("unchecked")
	private void moverDriversDispositivos(File directorioDrivers,FiltroDeArchivos filtro){
		String[] listaArchivos = directorioDrivers.list(filtro);
		if(listaArchivos!=null){
			Edificio edificio = Edificio.obtenerInstancia();
	       	for(int i=0; i<listaArchivos.length; i++){
	       		String nombreArchivo = listaArchivos[i];
	       		moverArchivos(listaArchivos[i],DIRECTORIO_ORIGEN_DISPOSITIVOS,DIRECTORIO_DESTINO_DISPOSITIVOS);
	       		String nombreClase = nombreArchivo.substring(0, nombreArchivo.lastIndexOf("."));
				try {
					System.out.println("PISOS:"+edificio.getPisos().size());
					Class clase = Class.forName(nombreClase);
					edificio.agregarDriverDispositivo((DriverDispositivo) clase.newInstance());
				} catch (Exception e) {
					//TODO ver manejo exception
					e.printStackTrace();
				}
			}
       	}
	}
		
	@SuppressWarnings("unchecked")
	private void moverDriversSensores(File directorioDrivers,FiltroDeArchivos filtro){
		String[] listaArchivos = directorioDrivers.list(filtro);
		if(listaArchivos!=null){
			Edificio edificio = Edificio.obtenerInstancia();
			for(int i=0; i<listaArchivos.length; i++){
				String nombreArchivo = listaArchivos[i]; 
				moverArchivos(nombreArchivo,DIRECTORIO_ORIGEN_SENSORES,DIRECTORIO_DESTINO_SENSORES);
				String nombreClase = nombreArchivo.substring(0, nombreArchivo.lastIndexOf("."));
				try {
					Class clase = Class.forName(nombreClase);
					edificio.agregarDriverSensor((DriverSensor)clase.newInstance());
				} catch (Exception e) {
					//TODO ver manejo exception
					e.printStackTrace();
				}
			}
		}
	}
	
	public void cargar() {
		File directorioDispositivos=new File(DIRECTORIO_ORIGEN_DISPOSITIVOS);
		File directorioSensores=new File(DIRECTORIO_ORIGEN_SENSORES);
		FiltroDeArchivos filtro = new FiltroDeArchivos(EXT_CLASS);
		moverDriversDispositivos(directorioDispositivos,filtro);
		moverDriversSensores(directorioSensores,filtro);
	}
	
	
}