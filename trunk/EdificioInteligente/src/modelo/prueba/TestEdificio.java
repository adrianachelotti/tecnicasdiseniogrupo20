package modelo.prueba;

import junit.framework.TestCase;
import modelo.accion.AccionPrenderDispositivo;
import modelo.cliente.dispositivos.CalefactorMock;
import modelo.cliente.dispositivos.VentiladorMock;
import modelo.driver.CalefactorDriver;
import modelo.driver.DriverDispositivo;
import modelo.driver.DriverSensor;
import modelo.driver.SensorDeTemperaturaDriver;
import modelo.edificio.Dispositivo;
import modelo.edificio.Edificio;
import modelo.edificio.Piso;
import modelo.edificio.Sensor;
import modelo.edificio.Ubicacion;
import modelo.manejadorDeSucesos.Suceso;

public class TestEdificio extends TestCase {
	
	//TODO ver q se comento parte del test
	public void testEdificio(){
		Edificio edificio = Edificio.obtenerInstancia();
		Piso piso = new Piso(1);
		DriverSensor driverSensorTemperatura = new SensorDeTemperaturaDriver();
		
		Sensor sensor = new Sensor(driverSensorTemperatura);
		sensor.setUbicacion(new Ubicacion(1,1,1));
		
		DriverDispositivo driverCalefactor = new CalefactorDriver();
		Dispositivo ventilador = new Dispositivo(driverCalefactor);
		
		ventilador.setUbicacion(new Ubicacion(1,2,1));
		
		
		piso.agregarDispositivo(ventilador);
		piso.agregarSensores(sensor);
		
		CalefactorMock calefactorMock = new CalefactorMock(); 
		AccionPrenderDispositivo accionPrenderCalefactor = new AccionPrenderDispositivo();
		//accionPrenderCalefactor.establecerDispositivo(calefactorMock);
		
		piso.getManejadorDeSucesos().suscribirImplicacion(accionPrenderCalefactor, new Suceso("temperatura_18"));
		edificio.agregarPiso(piso);
		
		//calefactorMock.establecerEncendido(false);
		//assertEquals(false,calefactorMock.estaEncendido());
		//piso.getManejadorDeSucesos().agregarSuceso(sensor.notificarSuceso());
		//assertEquals(true,calefactorMock.estaEncendido());
		
		
	}

}
