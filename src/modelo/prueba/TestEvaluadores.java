package modelo.prueba;

import java.util.List;

import modelo.accion.AccionPrenderDispositivo;
import modelo.cliente.driver.dispositivos.CalefactorDriver;
import modelo.edificio.Dispositivo;
import modelo.manejadorDeSucesos.*;
import junit.framework.TestCase;
/**
 * Test de funcionamiento de los Evaluadores.
 * 
 * @author Grupo20
 *
 */
public class TestEvaluadores extends TestCase {

	/**
	 * Test para evaluador de Secuencias Continuas
	 */
	public void testSucesosContinuosConOrdenConEvaluadorSecuenciaContinua(){
		//Se crea el manejador de susceso en modo secuencia continua y la accion para encender le dispositivo
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.obtenerConfiguracion().establecerEvaluadorSecuenciaContinua();
		AccionPrenderDispositivo accion = new AccionPrenderDispositivo();
		Dispositivo dispositivo = new Dispositivo(new CalefactorDriver());
		accion.establecerDispositivo(dispositivo);
		//se suscribe una implicacion y notifica sucesos validos		
		manejadorSucesos.suscribirImplicacion(accion, GeneradorDeSuceso.obtenerSucesos("pocaAgua presionAlta tanqueLleno"));
		assertEquals(false, dispositivo.isEncendido());
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("pocaAgua presionAlta tanqueLleno"));
		//se verifica que el dispositivo esta encendido
		assertEquals(true, dispositivo.isEncendido());
	}
	public void testSucesosContinuosSinOrdenConEvaluadorSecuenciaContinua(){
		//Se crea el manejador de susceso  en modo secuencia continua y la accion para encender le dispositivo
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.obtenerConfiguracion().establecerEvaluadorSecuenciaContinua();
		AccionPrenderDispositivo accion = new AccionPrenderDispositivo();
		Dispositivo dispositivo = new Dispositivo(new CalefactorDriver());
		accion.establecerDispositivo(dispositivo);
		//
		manejadorSucesos.suscribirImplicacion(accion, GeneradorDeSuceso.obtenerSucesos("pocaAgua presionAlta tanqueLleno"));
		assertEquals(false, dispositivo.isEncendido());
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("presionAlta pocaAgua tanqueLleno"));
		//se verifica que el dispositivo esta apagado
		assertEquals(false, dispositivo.isEncendido());
	}
	public void testSucesosNoValidosConEvaluadorSecuenciaContinua(){
		//Se crea el manejador de susceso en modo secuencia continua y la accion para encender le dispositivo
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.obtenerConfiguracion().establecerEvaluadorSecuenciaContinua();
		AccionPrenderDispositivo accion = new AccionPrenderDispositivo();
		Dispositivo dispositivo = new Dispositivo(new CalefactorDriver());
		accion.establecerDispositivo(dispositivo);
				
		manejadorSucesos.suscribirImplicacion(accion, GeneradorDeSuceso.obtenerSucesos("pocaAgua presionAlta tanqueLleno"));
		assertEquals(false, dispositivo.isEncendido());
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("presionAlta pocaAgua tanqueRoto"));
		
		assertEquals(false, dispositivo.isEncendido());
	}
	
	public void testSucesosNoContinuosConOrdenConEvaluadorSecuenciaContinua(){
		//Se crea el manejador de susceso en modo secuencia continua y la accion para encender le dispositivo
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.obtenerConfiguracion().establecerEvaluadorSecuenciaContinua();
		AccionPrenderDispositivo accion = new AccionPrenderDispositivo();
		Dispositivo dispositivo = new Dispositivo(new CalefactorDriver());
		accion.establecerDispositivo(dispositivo);
		
		manejadorSucesos.suscribirImplicacion(accion, GeneradorDeSuceso.obtenerSucesos("pocaAgua presionAlta tanqueLleno"));
		assertEquals(false, dispositivo.isEncendido());
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("pocaAgua tanqueRoto presionAlta tanqueLleno"));
		
		assertEquals(false, dispositivo.isEncendido());
	}
	/**
	 * Test para evaluador de Secuencias Discontinuas
	 */
	public void testSucesosContinuosConOrdenConEvaluadorSecuenciaDiscontinua(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.obtenerConfiguracion().establecerEvaluadorSecuenciaDiscontinua();
		AccionPrenderDispositivo accion = new AccionPrenderDispositivo();
		Dispositivo dispositivo = new Dispositivo(new CalefactorDriver());
		accion.establecerDispositivo(dispositivo);		
		
		manejadorSucesos.suscribirImplicacion(accion, GeneradorDeSuceso.obtenerSucesos("pocaAgua presionAlta tanqueLleno"));
		assertEquals(false, dispositivo.isEncendido());
		manejadorSucesos.agregarSucesos( GeneradorDeSuceso.obtenerSucesos("pocaAgua presionAlta tanqueLleno"));
		
		assertEquals(true, dispositivo.isEncendido());
	}
	public void testSucesosDisContinuosConOrdenConEvaluadorSecuenciaDiscontinua(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.obtenerConfiguracion().establecerEvaluadorSecuenciaDiscontinua();
		AccionPrenderDispositivo accion = new AccionPrenderDispositivo();
		Dispositivo dispositivo = new Dispositivo(new CalefactorDriver());
		accion.establecerDispositivo(dispositivo);		
				
		manejadorSucesos.suscribirImplicacion(accion, GeneradorDeSuceso.obtenerSucesos("pocaAgua presionAlta tanqueLleno"));
		assertEquals(false, dispositivo.isEncendido());
		manejadorSucesos.agregarSucesos( GeneradorDeSuceso.obtenerSucesos("pocaAgua presionBaja presionAlta algoDeAgua tanqueLleno"));
		
		assertEquals(true, dispositivo.isEncendido());
	}
	public void testSucesosDisContinuosConRepeticionesConOrdenConEvaluadorSecuenciaDiscontinua(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.obtenerConfiguracion().establecerEvaluadorSecuenciaDiscontinua();
		AccionPrenderDispositivo accion = new AccionPrenderDispositivo();
		Dispositivo dispositivo = new Dispositivo(new CalefactorDriver());
		accion.establecerDispositivo(dispositivo);		
				
		manejadorSucesos.suscribirImplicacion(accion, GeneradorDeSuceso.obtenerSucesos("pocaAgua presionAlta tanqueLleno"));
		assertEquals(false, dispositivo.isEncendido());
		manejadorSucesos.agregarSucesos( GeneradorDeSuceso.obtenerSucesos("pocaAgua pocaAgua presionBaja presionAlta algoDeAgua tanqueLleno tanqueLleno"));
		
		assertEquals(true, dispositivo.isEncendido());
	}
	
	public void testSucesosInvalidosConEvaluadorSecuenciaDiscontinua(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.obtenerConfiguracion().establecerEvaluadorSecuenciaDiscontinua();
		AccionPrenderDispositivo accion = new AccionPrenderDispositivo();
		Dispositivo dispositivo = new Dispositivo(new CalefactorDriver());
		accion.establecerDispositivo(dispositivo);	
		
		manejadorSucesos.suscribirImplicacion(accion, GeneradorDeSuceso.obtenerSucesos("pocaAgua presionAlta tanqueLleno"));
		assertEquals(false, dispositivo.isEncendido());
		manejadorSucesos.agregarSucesos( GeneradorDeSuceso.obtenerSucesos("algoDeAgua presionBaja presionAlta pocaAgua"));
		
		assertEquals(false, dispositivo.isEncendido());
	}
	public void testSucesoUnicoConEvaluadorSecuenciaDiscontinua(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.obtenerConfiguracion().establecerEvaluadorSecuenciaDiscontinua();
		AccionPrenderDispositivo accion = new AccionPrenderDispositivo();
		Dispositivo dispositivo = new Dispositivo(new CalefactorDriver());
		accion.establecerDispositivo(dispositivo);
		
		manejadorSucesos.suscribirImplicacion(accion, GeneradorDeSuceso.obtenerSucesos("pocaAgua presionAlta tanqueLleno"));
		assertEquals(false, dispositivo.isEncendido());
		manejadorSucesos.agregarSucesos( GeneradorDeSuceso.obtenerSucesos("pocaAgua"));
		
		assertEquals(false, dispositivo.isEncendido());
	}
	
	public void testSucesosContinuosSinOrdenConEvaluadorSecuenciaDiscontinua(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.obtenerConfiguracion().establecerEvaluadorSecuenciaDiscontinua();
		AccionPrenderDispositivo accion = new AccionPrenderDispositivo();
		Dispositivo dispositivo = new Dispositivo(new CalefactorDriver());
		accion.establecerDispositivo(dispositivo);
		
		List<Suceso> sucesos = GeneradorDeSuceso.obtenerSucesos("pocaAgua presionAlta tanqueLleno");
		manejadorSucesos.suscribirImplicacion(accion, sucesos);
		assertEquals(false, dispositivo.isEncendido());
		manejadorSucesos.agregarSucesos( GeneradorDeSuceso.obtenerSucesos("presionAlta tanqueLleno pocaAgua"));
		assertEquals(false, dispositivo.isEncendido());
	}
	public void testSucesosDiscontinuosSinOrdenConEvaluadorSecuenciaDiscontinua(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.obtenerConfiguracion().establecerEvaluadorSecuenciaDiscontinua();
		AccionPrenderDispositivo accion = new AccionPrenderDispositivo();
		Dispositivo dispositivo = new Dispositivo(new CalefactorDriver());
		accion.establecerDispositivo(dispositivo);
		
		List<Suceso> sucesos = GeneradorDeSuceso.obtenerSucesos("pocaAgua presionAlta tanqueLleno");
		manejadorSucesos.suscribirImplicacion(accion, sucesos);
		assertEquals(false, dispositivo.isEncendido());
		manejadorSucesos.agregarSucesos( GeneradorDeSuceso.obtenerSucesos("presionAlta tanqueLleno muchoCalor pocaAgua"));
		assertEquals(false, dispositivo.isEncendido());
	}
	
	/**
	 * Test para evaluador de Sucesos Continuos
	 */
	public void testSucesosDiscontinuosSinOrdenConEvaluadorContinuo(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.obtenerConfiguracion().establecerEvaluadorContinuo();
		AccionPrenderDispositivo accion = new AccionPrenderDispositivo();
		Dispositivo dispositivo = new Dispositivo(new CalefactorDriver());
		accion.establecerDispositivo(dispositivo);
		
		List<Suceso> sucesos = GeneradorDeSuceso.obtenerSucesos("muchoViento pocaAgua");
		manejadorSucesos.suscribirImplicacion(accion, sucesos);
		assertEquals(false, dispositivo.isEncendido());
		manejadorSucesos.agregarSucesos( GeneradorDeSuceso.obtenerSucesos("pocaAgua muchoRuido muchoViento presionBaja"));
		assertEquals(false, dispositivo.isEncendido());
	}
	public void testSucesosInvalidosConEvaluadorContinuo(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.obtenerConfiguracion().establecerEvaluadorContinuo();
		AccionPrenderDispositivo accion = new AccionPrenderDispositivo();
		Dispositivo dispositivo = new Dispositivo(new CalefactorDriver());
		accion.establecerDispositivo(dispositivo);
		
		List<Suceso> sucesos = GeneradorDeSuceso.obtenerSucesos("muchoViento pocaAgua");
		manejadorSucesos.suscribirImplicacion(accion, sucesos);
		assertEquals(false, dispositivo.isEncendido());
		manejadorSucesos.agregarSucesos( GeneradorDeSuceso.obtenerSucesos("presionBaja muchoViento"));
		assertEquals(false, dispositivo.isEncendido());
	}
	public void testSucesosUnicoConEvaluadorContinuo(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.obtenerConfiguracion().establecerEvaluadorContinuo();
		AccionPrenderDispositivo accion = new AccionPrenderDispositivo();
		Dispositivo dispositivo = new Dispositivo(new CalefactorDriver());
		accion.establecerDispositivo(dispositivo);
		
		List<Suceso> sucesos = GeneradorDeSuceso.obtenerSucesos("muchoViento pocaAgua");
		manejadorSucesos.suscribirImplicacion(accion, sucesos);
		assertEquals(false, dispositivo.isEncendido());
		manejadorSucesos.agregarSucesos( GeneradorDeSuceso.obtenerSucesos("muchoViento"));
		assertEquals(false, dispositivo.isEncendido());
	}
	public void testSucesosContinuosSinOrdenConEvaluadorContinuo(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.obtenerConfiguracion().establecerEvaluadorContinuo();
		AccionPrenderDispositivo accion = new AccionPrenderDispositivo();
		Dispositivo dispositivo = new Dispositivo(new CalefactorDriver());
		accion.establecerDispositivo(dispositivo);
		
		List<Suceso> sucesos = GeneradorDeSuceso.obtenerSucesos("muchoViento pocaAgua");
		manejadorSucesos.suscribirImplicacion(accion, sucesos);
		assertEquals(false, dispositivo.isEncendido());
		manejadorSucesos.agregarSucesos( GeneradorDeSuceso.obtenerSucesos("pocaAgua muchoViento muchoRuido presionBaja"));
		assertEquals(true, dispositivo.isEncendido());
	}
	
	public void testSucesosContinuosConOrdenConEvaluadorContinuo(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.obtenerConfiguracion().establecerEvaluadorContinuo();
		AccionPrenderDispositivo accion = new AccionPrenderDispositivo();
		Dispositivo dispositivo = new Dispositivo(new CalefactorDriver());
		accion.establecerDispositivo(dispositivo);
		
		List<Suceso> sucesos = GeneradorDeSuceso.obtenerSucesos("muchoViento pocaAgua");
		manejadorSucesos.suscribirImplicacion(accion, sucesos);
		assertEquals(false, dispositivo.isEncendido());
		manejadorSucesos.agregarSucesos( GeneradorDeSuceso.obtenerSucesos("muchoViento pocaAgua"));
		assertEquals(true, dispositivo.isEncendido());
	}
	
	/**
	 * Test para evaluador de Sucesos Discontinuos
	 */
	public void testSucesosExactosContinuosConOrdenConEvaluadorDiscontinuo(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.obtenerConfiguracion().establecerEvaluadorDiscontinuo();
		AccionPrenderDispositivo accion = new AccionPrenderDispositivo();
		Dispositivo dispositivo = new Dispositivo(new CalefactorDriver());
		accion.establecerDispositivo(dispositivo);
		
		List<Suceso> sucesos = GeneradorDeSuceso.obtenerSucesos("muchoViento pocaAgua");
		manejadorSucesos.suscribirImplicacion(accion, sucesos);
		assertEquals(false, dispositivo.isEncendido());
		manejadorSucesos.agregarSucesos( GeneradorDeSuceso.obtenerSucesos("muchoViento pocaAgua"));
		assertEquals(true, dispositivo.isEncendido());
	}
	public void testSucesosDiscontinuosSinOrdenConEvaluadorDiscontinuo(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.obtenerConfiguracion().establecerEvaluadorDiscontinuo();
		AccionPrenderDispositivo accion = new AccionPrenderDispositivo();
		Dispositivo dispositivo = new Dispositivo(new CalefactorDriver());
		accion.establecerDispositivo(dispositivo);
		
		List<Suceso> sucesos = GeneradorDeSuceso.obtenerSucesos("muchoViento pocaAgua");
		manejadorSucesos.suscribirImplicacion(accion, sucesos);
		assertEquals(false, dispositivo.isEncendido());
		manejadorSucesos.agregarSucesos( GeneradorDeSuceso.obtenerSucesos("pocaAgua muchoRuido muchoViento presionBaja"));
		assertEquals(true, dispositivo.isEncendido());
	}
	public void testSucesosInvalidosConEvaluadorDiscontinuo(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.obtenerConfiguracion().establecerEvaluadorDiscontinuo();
		AccionPrenderDispositivo accion = new AccionPrenderDispositivo();
		Dispositivo dispositivo = new Dispositivo(new CalefactorDriver());
		accion.establecerDispositivo(dispositivo);
		
		List<Suceso> sucesos = GeneradorDeSuceso.obtenerSucesos("muchoViento pocaAgua");
		manejadorSucesos.suscribirImplicacion(accion, sucesos);
		assertEquals(false, dispositivo.isEncendido());
		manejadorSucesos.agregarSucesos( GeneradorDeSuceso.obtenerSucesos("presionBaja muchoViento"));
		assertEquals(false, dispositivo.isEncendido());
	}
	
	public void testSucesoUnicoConEvaluadorDiscontinuo(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.obtenerConfiguracion().establecerEvaluadorDiscontinuo();
		AccionPrenderDispositivo accion = new AccionPrenderDispositivo();
		Dispositivo dispositivo = new Dispositivo(new CalefactorDriver());
		accion.establecerDispositivo(dispositivo);
		
		List<Suceso> sucesos = GeneradorDeSuceso.obtenerSucesos("muchoViento pocaAgua");
		manejadorSucesos.suscribirImplicacion(accion, sucesos);
		assertEquals(false, dispositivo.isEncendido());
		manejadorSucesos.agregarSucesos( GeneradorDeSuceso.obtenerSucesos("muchoViento"));
		assertEquals(false, dispositivo.isEncendido());
	}
	public void testSucesosContinuosConOrdenConEvaluadorDiscontinuo(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.obtenerConfiguracion().establecerEvaluadorDiscontinuo();
		AccionPrenderDispositivo accion = new AccionPrenderDispositivo();
		Dispositivo dispositivo = new Dispositivo(new CalefactorDriver());
		accion.establecerDispositivo(dispositivo);
		
		List<Suceso> sucesos = GeneradorDeSuceso.obtenerSucesos("muchoViento pocaAgua");
		manejadorSucesos.suscribirImplicacion(accion, sucesos);
		assertEquals(false, dispositivo.isEncendido());
		manejadorSucesos.agregarSucesos( GeneradorDeSuceso.obtenerSucesos("muchoViento pocaAgua muchoRuido presionBaja"));
		assertEquals(true, dispositivo.isEncendido());
	}

}
