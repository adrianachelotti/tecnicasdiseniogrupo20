package prueba;

import cliente.AccionPrenderBomba;
import cliente.AccionPrenderDispositivo;
import cliente.Bomba;
import dispositivos.*;
import sensores.Sensor;
import sensores.SensorDeTemperatura;
import source.Accion;
import source.Suceso;
import edificio.Edificio;
import edificio.Piso;
import edificio.Ubicacion;
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
		
		edificio.getManejadorDeSucesos().suscribirImplicacion(accionPrenderCalefactor, new Suceso("temperatura_18"));
		edificio.agregarPiso(piso);
		
		calefactor.establecerEncendido(false);
		assertEquals(false,calefactor.estaEncendido());
		edificio.getManejadorDeSucesos().agregarSuceso(sensor.notificarSuceso());
		assertEquals(true,calefactor.estaEncendido());
		
		
	}

}
