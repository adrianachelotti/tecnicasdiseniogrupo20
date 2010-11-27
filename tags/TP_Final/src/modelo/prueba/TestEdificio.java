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

/**
 * Test de funcionamiento de carga de componentes al edificio.
 * @author Dario
 *
 */
public class TestEdificio extends TestCase {
	
	public void testEdificio(){
		//Se genera un edificio con un piso. En este ultimo se agregan un sensor de temperatura, un calefactor
		// y un ventilador.
		Edificio edificio = Edificio.obtenerInstancia();
		Piso piso = new Piso(1);
		DriverSensor driverSensorTemperatura = new SensorDeTemperaturaDriver();
		Sensor sensor = new Sensor(driverSensorTemperatura);
		sensor.setUbicacion(new Ubicacion(1,1,1));
		DriverDispositivo driverVentilador = new VentiladorDriver();
		Dispositivo ventilador = new Dispositivo(driverVentilador);
		ventilador.establecerUbicacion(new Ubicacion(1,2,1));
		piso.agregarDispositivo(ventilador);
		piso.agregarSensores(sensor);
		DriverDispositivo driverCalefactor = new CalefactorDriver();
		Dispositivo calefactor = new Dispositivo(driverCalefactor);
		AccionPrenderDispositivo accionPrenderCalefactor = new AccionPrenderDispositivo();
		accionPrenderCalefactor.establecerDispositivo(calefactor);
		
		//Se suscribe una implicacion para prender el calefactor en caso de TEMPERATURA_BAJA. Luego se simula una medicion
		// de TEMPERATURA_BAJA por parte del sensor, ante esto se debe prender el calefactor.
		piso.obtenerManejadorDeSucesos().suscribirImplicacion(accionPrenderCalefactor, new Suceso("TEMPERATURA_BAJA"));
		edificio.agregarPiso(piso);
		sensor.establecerMedicion("TEMPERATURA_BAJA");
		calefactor.apagar();
		//Se verifica que el calefactor esta apagado.
		assertEquals(false,calefactor.isEncendido());
		piso.obtenerManejadorDeSucesos().agregarSuceso(sensor.notificarSuceso());
		
		//Se verifica que el calefactor se encendio.
		assertEquals(true,calefactor.isEncendido());
	}

}
