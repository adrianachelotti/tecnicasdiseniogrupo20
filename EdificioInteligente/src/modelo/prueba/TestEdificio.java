package modelo.prueba;

import modelo.cliente.*;
import modelo.dispositivos.*;
import modelo.sensores.*;
import modelo.manejadorDeSucesos.*;
import modelo.edificio.*;
import junit.framework.TestCase;

public class TestEdificio extends TestCase {
	public void testEdificio(){
		Edificio edificio = Edificio.obtenerInstancia();
		Piso piso = new Piso(1);
		
		SensorDeTemperatura sensor = new SensorDeTemperatura();
		sensor.establecerUbicacion(new Ubicacion(1,1,1));
		
		Ventilador ventilador = new Ventilador();
		ventilador.establecerUbicacion(new Ubicacion(1,2,1));
		
		
		piso.agregarDispositivo(ventilador);
		piso.agregarSensores(sensor);
		
		Calefactor calefactor = new Calefactor(); 
		AccionPrenderDispositivo accionPrenderCalefactor = new AccionPrenderDispositivo();
		accionPrenderCalefactor.establecerDispositivo(calefactor);
		
		piso.getManejadorDeSucesos().suscribirImplicacion(accionPrenderCalefactor, new Suceso("temperatura_18"));
		edificio.agregarPiso(piso);
		
		calefactor.establecerEncendido(false);
		assertEquals(false,calefactor.estaEncendido());
		piso.getManejadorDeSucesos().agregarSuceso(sensor.notificarSuceso());
		assertEquals(true,calefactor.estaEncendido());
		
		
	}

}
