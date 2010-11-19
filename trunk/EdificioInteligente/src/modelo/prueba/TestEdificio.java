package modelo.prueba;

import junit.framework.TestCase;
import modelo.accion.AccionPrenderDispositivo;
import modelo.cliente.driver.dispositivos.CalefactorDriver;
import modelo.cliente.driver.dispositivos.VentiladorDriver;
import modelo.cliente.driver.sensores.SensorDeTemperaturaDriver;
import modelo.driver.DriverDispositivo;
import modelo.driver.DriverSensor;
import modelo.edificio.Dispositivo;
import modelo.edificio.Edificio;
import modelo.edificio.Piso;
import modelo.edificio.Sensor;
import modelo.edificio.Ubicacion;
import modelo.manejadorDeSucesos.Suceso;

public class TestEdificio extends TestCase {
	
	public void testEdificio(){
		Edificio edificio = Edificio.obtenerInstancia();
		Piso piso = new Piso(1);
		DriverSensor driverSensorTemperatura = new SensorDeTemperaturaDriver();
		
		Sensor sensor = new Sensor(driverSensorTemperatura);
		sensor.setUbicacion(new Ubicacion(1,1,1));
				
		DriverDispositivo driverVentilador = new VentiladorDriver();
		Dispositivo ventilador = new Dispositivo(driverVentilador);
		
		ventilador.setUbicacion(new Ubicacion(1,2,1));
				
		piso.agregarDispositivo(ventilador);
		piso.agregarSensores(sensor);
		
		DriverDispositivo driverCalefactor = new CalefactorDriver();
		Dispositivo calefactor = new Dispositivo(driverCalefactor);
		AccionPrenderDispositivo accionPrenderCalefactor = new AccionPrenderDispositivo();
		accionPrenderCalefactor.establecerDispositivo(calefactor);
		
		piso.getManejadorDeSucesos().suscribirImplicacion(accionPrenderCalefactor, new Suceso("TEMPERATURA_BAJA"));
		edificio.agregarPiso(piso);
		
		sensor.establecerMedicion("TEMPERATURA_BAJA");
		
		calefactor.apagar();
		assertEquals(false,calefactor.isEncendido());
		piso.getManejadorDeSucesos().agregarSuceso(sensor.notificarSuceso());
		assertEquals(true,calefactor.isEncendido());
	}

}
