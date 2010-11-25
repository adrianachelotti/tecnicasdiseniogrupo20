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
		
		//Se suscribe una implicacion y se notifican sucesos validos.		
		manejadorSucesos.suscribirImplicacion(accion, GeneradorDeSuceso.obtenerSucesos("pocaAgua presionAlta tanqueLleno"));
		//Se verifica que el dispositivo se encuentra apagado.
		assertEquals(false, dispositivo.isEncendido());
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("pocaAgua presionAlta tanqueLleno"));
		
		//Se verifica que el dispositivo se enciende.
		assertEquals(true, dispositivo.isEncendido());
	}
	
	public void testSucesosContinuosSinOrdenConEvaluadorSecuenciaContinua(){
		//Se crea el manejador de susceso  en modo secuencia continua y la accion para encender le dispositivo
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.obtenerConfiguracion().establecerEvaluadorSecuenciaContinua();
		AccionPrenderDispositivo accion = new AccionPrenderDispositivo();
		Dispositivo dispositivo = new Dispositivo(new CalefactorDriver());
		accion.establecerDispositivo(dispositivo);
		
		//Se suscribe un implicacion y se agregan sucesos sin respetar el orden necesario para validar la implicacion.
		manejadorSucesos.suscribirImplicacion(accion, GeneradorDeSuceso.obtenerSucesos("pocaAgua presionAlta tanqueLleno"));
		//Se verifica que el dispositivo se encuentra apagado.
		assertEquals(false, dispositivo.isEncendido());
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("presionAlta pocaAgua tanqueLleno"));
		
		//Se verifica que el dispositivo se mantiene apagado.
		assertEquals(false, dispositivo.isEncendido());
	}
	public void testSucesosNoValidosConEvaluadorSecuenciaContinua(){
		//Se crea el manejador de susceso en modo secuencia continua y la accion para encender le dispositivo
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.obtenerConfiguracion().establecerEvaluadorSecuenciaContinua();
		AccionPrenderDispositivo accion = new AccionPrenderDispositivo();
		Dispositivo dispositivo = new Dispositivo(new CalefactorDriver());
		accion.establecerDispositivo(dispositivo);
				
		//Se suscribe un implicacion y se agregan sucesos que no cumplen con la totalidad de la implicacion.
		manejadorSucesos.suscribirImplicacion(accion, GeneradorDeSuceso.obtenerSucesos("pocaAgua presionAlta tanqueLleno"));
		//Se verifica que el dispositivo se encuentra apagado.
		assertEquals(false, dispositivo.isEncendido());
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("presionAlta pocaAgua tanqueRoto"));
		
		//Se verifica que el dispositivo se mantiene apagado.
		assertEquals(false, dispositivo.isEncendido());
	}
	
	public void testSucesosNoContinuosConOrdenConEvaluadorSecuenciaContinua(){
		//Se crea el manejador de susceso en modo secuencia continua y la accion para encender le dispositivo
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.obtenerConfiguracion().establecerEvaluadorSecuenciaContinua();
		AccionPrenderDispositivo accion = new AccionPrenderDispositivo();
		Dispositivo dispositivo = new Dispositivo(new CalefactorDriver());
		accion.establecerDispositivo(dispositivo);
		
		//Se suscribe un implicacion y se agregan sucesos sin respetar la continuidad necesaria para validar la implicacion.
		manejadorSucesos.suscribirImplicacion(accion, GeneradorDeSuceso.obtenerSucesos("pocaAgua presionAlta tanqueLleno"));
		//Se verifica que el dispositivo se encuentra apagado.
		assertEquals(false, dispositivo.isEncendido());
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("pocaAgua tanqueRoto presionAlta tanqueLleno"));
		
		//Se verifica que el dispositivo se mantiene apagado.
		assertEquals(false, dispositivo.isEncendido());
	}
	
	/**
	 * Test para evaluador de Secuencias Discontinuas
	 */
	public void testSucesosContinuosConOrdenConEvaluadorSecuenciaDiscontinua(){
		//Se crea el manejador de sucesos en modo secuencia discontinua y la accion para encender el dispositivo.
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.obtenerConfiguracion().establecerEvaluadorSecuenciaDiscontinua();
		AccionPrenderDispositivo accion = new AccionPrenderDispositivo();
		Dispositivo dispositivo = new Dispositivo(new CalefactorDriver());
		accion.establecerDispositivo(dispositivo);		
		
		//Se suscribe un implicacion y se agregan sucesos respetando el orden necesario para validar la implicacion.
		manejadorSucesos.suscribirImplicacion(accion, GeneradorDeSuceso.obtenerSucesos("pocaAgua presionAlta tanqueLleno"));
		//Se verifica que el dispositivo se encuentra apagado.
		assertEquals(false, dispositivo.isEncendido());
		manejadorSucesos.agregarSucesos( GeneradorDeSuceso.obtenerSucesos("pocaAgua presionAlta tanqueLleno"));
		
		//Se verifica que el dispositivo se enciende.
		assertEquals(true, dispositivo.isEncendido());
	}
	
	public void testSucesosDisContinuosConOrdenConEvaluadorSecuenciaDiscontinua(){
		//Se crea el manejador de sucesos en modo secuencia discontinua y la accion para encender el dispositivo.
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.obtenerConfiguracion().establecerEvaluadorSecuenciaDiscontinua();
		AccionPrenderDispositivo accion = new AccionPrenderDispositivo();
		Dispositivo dispositivo = new Dispositivo(new CalefactorDriver());
		accion.establecerDispositivo(dispositivo);		
				
		//Se suscribe un implicacion y se agregan sucesos respetando el orden necesario para validar la implicacion.
		manejadorSucesos.suscribirImplicacion(accion, GeneradorDeSuceso.obtenerSucesos("pocaAgua presionAlta tanqueLleno"));
		//Se verifica que el dispositivo se encuentra apagado.
		assertEquals(false, dispositivo.isEncendido());
		manejadorSucesos.agregarSucesos( GeneradorDeSuceso.obtenerSucesos("pocaAgua presionBaja presionAlta algoDeAgua tanqueLleno"));
		
		//Se verifica que el dispositivo se enciende.
		assertEquals(true, dispositivo.isEncendido());
	}
	
	public void testSucesosDisContinuosConRepeticionesConOrdenConEvaluadorSecuenciaDiscontinua(){
		//Se crea el manejador de sucesos en modo secuencia discontinua y la accion para encender el dispositivo.
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.obtenerConfiguracion().establecerEvaluadorSecuenciaDiscontinua();
		AccionPrenderDispositivo accion = new AccionPrenderDispositivo();
		Dispositivo dispositivo = new Dispositivo(new CalefactorDriver());
		accion.establecerDispositivo(dispositivo);		
				
		//Se suscribe un implicacion y se agregan sucesos respetando el orden y continuidad necesaria para validar la implicacion.
		manejadorSucesos.suscribirImplicacion(accion, GeneradorDeSuceso.obtenerSucesos("pocaAgua presionAlta tanqueLleno"));
		//Se verifica que el dispositivo se encuentra apagado.
		assertEquals(false, dispositivo.isEncendido());
		manejadorSucesos.agregarSucesos( GeneradorDeSuceso.obtenerSucesos("pocaAgua pocaAgua presionBaja presionAlta algoDeAgua tanqueLleno tanqueLleno"));
		
		//Se verifica que el dispositivo se enciende.
		assertEquals(true, dispositivo.isEncendido());
	}
	
	public void testSucesosInvalidosConEvaluadorSecuenciaDiscontinua(){
		//Se crea el manejador de sucesos en modo secuencia discontinua y la accion para encender el dispositivo.
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.obtenerConfiguracion().establecerEvaluadorSecuenciaDiscontinua();
		AccionPrenderDispositivo accion = new AccionPrenderDispositivo();
		Dispositivo dispositivo = new Dispositivo(new CalefactorDriver());
		accion.establecerDispositivo(dispositivo);	
		
		//Se suscribe un implicacion y se agregan sucesos sin respetar el orden necesario para validar la implicacion.
		manejadorSucesos.suscribirImplicacion(accion, GeneradorDeSuceso.obtenerSucesos("pocaAgua presionAlta tanqueLleno"));
		//Se verifica que el dispositivo se encuentra apagado.
		assertEquals(false, dispositivo.isEncendido());
		manejadorSucesos.agregarSucesos( GeneradorDeSuceso.obtenerSucesos("algoDeAgua presionBaja presionAlta pocaAgua"));
	
		//Se verifica que el dispositivo se mantiene apagado.
		assertEquals(false, dispositivo.isEncendido());
	}
	
	public void testSucesoUnicoConEvaluadorSecuenciaDiscontinua(){
		//Se crea el manejador de sucesos en modo secuencia discontinua y la accion para encender el dispositivo.
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.obtenerConfiguracion().establecerEvaluadorSecuenciaDiscontinua();
		AccionPrenderDispositivo accion = new AccionPrenderDispositivo();
		Dispositivo dispositivo = new Dispositivo(new CalefactorDriver());
		accion.establecerDispositivo(dispositivo);
		
		//Se suscribe un implicacion y se agregan sucesos sin cumplir con la totalidad necesaria para validar la implicacion.
		manejadorSucesos.suscribirImplicacion(accion, GeneradorDeSuceso.obtenerSucesos("pocaAgua presionAlta tanqueLleno"));
		//Se verifica que el dispositivo se encuentra apagado.
		assertEquals(false, dispositivo.isEncendido());
		manejadorSucesos.agregarSucesos( GeneradorDeSuceso.obtenerSucesos("pocaAgua"));
		
		//Se verifica que el dispositivo se mantiene apagado.
		assertEquals(false, dispositivo.isEncendido());
	}
	
	public void testSucesosContinuosSinOrdenConEvaluadorSecuenciaDiscontinua(){
		//Se crea el manejador de sucesos en modo secuencia discontinua y la accion para encender el dispositivo.
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.obtenerConfiguracion().establecerEvaluadorSecuenciaDiscontinua();
		AccionPrenderDispositivo accion = new AccionPrenderDispositivo();
		Dispositivo dispositivo = new Dispositivo(new CalefactorDriver());
		accion.establecerDispositivo(dispositivo);
		
		//Se suscribe un implicacion y se agregan sucesos sin respetar el orden necesario para validar la implicacion.
		List<Suceso> sucesos = GeneradorDeSuceso.obtenerSucesos("pocaAgua presionAlta tanqueLleno");
		manejadorSucesos.suscribirImplicacion(accion, sucesos);
		//Se verifica que el dispositivo se encuentra apagado.
		assertEquals(false, dispositivo.isEncendido());
		manejadorSucesos.agregarSucesos( GeneradorDeSuceso.obtenerSucesos("presionAlta tanqueLleno pocaAgua"));
		
		//Se verifica que el dispositivo se mantiene apagado.
		assertEquals(false, dispositivo.isEncendido());
	}
	
	public void testSucesosDiscontinuosSinOrdenConEvaluadorSecuenciaDiscontinua(){
		//Se crea el manejador de sucesos en modo secuencia discontinua y la accion para encender el dispositivo.
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.obtenerConfiguracion().establecerEvaluadorSecuenciaDiscontinua();
		AccionPrenderDispositivo accion = new AccionPrenderDispositivo();
		Dispositivo dispositivo = new Dispositivo(new CalefactorDriver());
		accion.establecerDispositivo(dispositivo);
		
		//Se suscribe un implicacion y se agregan sucesos sin respetar el orden necesario para validar la implicacion.
		List<Suceso> sucesos = GeneradorDeSuceso.obtenerSucesos("pocaAgua presionAlta tanqueLleno");
		manejadorSucesos.suscribirImplicacion(accion, sucesos);
		//Se verifica que el dispositivo se encuentra apagado.
		assertEquals(false, dispositivo.isEncendido());
		manejadorSucesos.agregarSucesos( GeneradorDeSuceso.obtenerSucesos("presionAlta tanqueLleno muchoCalor pocaAgua"));
		
		//Se verifica que el dispositivo se mantiene apagado.
		assertEquals(false, dispositivo.isEncendido());
	}
	
	/**
	 * Test para evaluador de Sucesos Continuos
	 */
	public void testSucesosDiscontinuosSinOrdenConEvaluadorContinuo(){
		//Se crea el manejador de sucesos en modo continuo y la accion para encender el dispositivo.
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.obtenerConfiguracion().establecerEvaluadorContinuo();
		AccionPrenderDispositivo accion = new AccionPrenderDispositivo();
		Dispositivo dispositivo = new Dispositivo(new CalefactorDriver());
		accion.establecerDispositivo(dispositivo);
		
		//Se suscribe un implicacion y se agregan sucesos sin respetar la continuidad necesaria para validar la implicacion.
		List<Suceso> sucesos = GeneradorDeSuceso.obtenerSucesos("muchoViento pocaAgua");
		manejadorSucesos.suscribirImplicacion(accion, sucesos);
		//Se verifica que el dispositivo se encuentra apagado.
		assertEquals(false, dispositivo.isEncendido());
		manejadorSucesos.agregarSucesos( GeneradorDeSuceso.obtenerSucesos("pocaAgua muchoRuido muchoViento presionBaja"));
		
		//Se verifica que el dispositivo se mantiene apagado.
		assertEquals(false, dispositivo.isEncendido());
	}
	
	public void testSucesosInvalidosConEvaluadorContinuo(){
		//Se crea el manejador de sucesos en modo continuo y la accion para encender el dispositivo.
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.obtenerConfiguracion().establecerEvaluadorContinuo();
		AccionPrenderDispositivo accion = new AccionPrenderDispositivo();
		Dispositivo dispositivo = new Dispositivo(new CalefactorDriver());
		accion.establecerDispositivo(dispositivo);
		
		//Se suscribe un implicacion y se agregan sucesos sin respetar el tipo necesario para validar la implicacion.
		List<Suceso> sucesos = GeneradorDeSuceso.obtenerSucesos("muchoViento pocaAgua");
		manejadorSucesos.suscribirImplicacion(accion, sucesos);
		//Se verifica que el dispositivo se encuentra apagado.
		assertEquals(false, dispositivo.isEncendido());
		manejadorSucesos.agregarSucesos( GeneradorDeSuceso.obtenerSucesos("presionBaja muchoViento"));
				
		//Se verifica que el dispositivo se mantiene apagado.
		assertEquals(false, dispositivo.isEncendido());
	}
	
	public void testSucesosUnicoConEvaluadorContinuo(){
		//Se crea el manejador de sucesos en modo continuo y la accion para encender el dispositivo.
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.obtenerConfiguracion().establecerEvaluadorContinuo();
		AccionPrenderDispositivo accion = new AccionPrenderDispositivo();
		Dispositivo dispositivo = new Dispositivo(new CalefactorDriver());
		accion.establecerDispositivo(dispositivo);
		
		//Se suscribe un implicacion y se agregan sucesos sin respetar la totalidad necesaria para validar la implicacion.
		List<Suceso> sucesos = GeneradorDeSuceso.obtenerSucesos("muchoViento pocaAgua");
		manejadorSucesos.suscribirImplicacion(accion, sucesos);
		//Se verifica que el dispositivo se encuentra apagado.
		assertEquals(false, dispositivo.isEncendido());
		manejadorSucesos.agregarSucesos( GeneradorDeSuceso.obtenerSucesos("muchoViento"));
			
		//Se verifica que el dispositivo se mantiene apagado.
		assertEquals(false, dispositivo.isEncendido());
	}
	
	public void testSucesosContinuosSinOrdenConEvaluadorContinuo(){
		//Se crea el manejador de sucesos en modo continuo y la accion para encender el dispositivo.
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.obtenerConfiguracion().establecerEvaluadorContinuo();
		AccionPrenderDispositivo accion = new AccionPrenderDispositivo();
		Dispositivo dispositivo = new Dispositivo(new CalefactorDriver());
		accion.establecerDispositivo(dispositivo);
		
		//Se suscribe un implicacion y se agregan sucesos respetando la continuidad necesaria para validar la implicacion.
		List<Suceso> sucesos = GeneradorDeSuceso.obtenerSucesos("muchoViento pocaAgua");
		manejadorSucesos.suscribirImplicacion(accion, sucesos);
		//Se verifica que el dispositivo se encuentra apagado.
		assertEquals(false, dispositivo.isEncendido());
		manejadorSucesos.agregarSucesos( GeneradorDeSuceso.obtenerSucesos("pocaAgua muchoViento muchoRuido presionBaja"));
			
		//Se verifica que el dispositivo se enciende.
		assertEquals(true, dispositivo.isEncendido());
	}
	
	public void testSucesosContinuosConOrdenConEvaluadorContinuo(){
		//Se crea el manejador de sucesos en modo continuo y la accion para encender el dispositivo.
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.obtenerConfiguracion().establecerEvaluadorContinuo();
		AccionPrenderDispositivo accion = new AccionPrenderDispositivo();
		Dispositivo dispositivo = new Dispositivo(new CalefactorDriver());
		accion.establecerDispositivo(dispositivo);
				
		//Se suscribe un implicacion y se agregan sucesos respetando la continuidad necesaria para validar la implicacion.
		List<Suceso> sucesos = GeneradorDeSuceso.obtenerSucesos("muchoViento pocaAgua");
		manejadorSucesos.suscribirImplicacion(accion, sucesos);
		//Se verifica que el dispositivo se encuentra apagado.
		assertEquals(false, dispositivo.isEncendido());
		manejadorSucesos.agregarSucesos( GeneradorDeSuceso.obtenerSucesos("muchoViento pocaAgua"));
				
		//Se verifica que el dispositivo se enciende.
		assertEquals(true, dispositivo.isEncendido());
	}
	
	/**
	 * Test para evaluador de Sucesos Discontinuos
	 */
	public void testSucesosExactosContinuosConOrdenConEvaluadorDiscontinuo(){
		//Se crea el manejador de sucesos en modo discontinuo y la accion para encender el dispositivo.
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.obtenerConfiguracion().establecerEvaluadorDiscontinuo();
		AccionPrenderDispositivo accion = new AccionPrenderDispositivo();
		Dispositivo dispositivo = new Dispositivo(new CalefactorDriver());
		accion.establecerDispositivo(dispositivo);
				
		//Se suscribe un implicacion y se agregan sucesos respetando la totalidad necesaria para validar la implicacion.
		List<Suceso> sucesos = GeneradorDeSuceso.obtenerSucesos("muchoViento pocaAgua");
		manejadorSucesos.suscribirImplicacion(accion, sucesos);
		//Se verifica que el dispositivo se encuentra apagado.
		assertEquals(false, dispositivo.isEncendido());
		manejadorSucesos.agregarSucesos( GeneradorDeSuceso.obtenerSucesos("muchoViento pocaAgua"));
		
		//Se verifica que el dispositivo se enciende.
		assertEquals(true, dispositivo.isEncendido());
	}
	
	public void testSucesosDiscontinuosSinOrdenConEvaluadorDiscontinuo(){
		//Se crea el manejador de sucesos en modo discontinuo y la accion para encender el dispositivo.
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.obtenerConfiguracion().establecerEvaluadorDiscontinuo();
		AccionPrenderDispositivo accion = new AccionPrenderDispositivo();
		Dispositivo dispositivo = new Dispositivo(new CalefactorDriver());
		accion.establecerDispositivo(dispositivo);
				
		//Se suscribe un implicacion y se agregan sucesos sin respetar la totalidad necesaria para validar la implicacion.
		List<Suceso> sucesos = GeneradorDeSuceso.obtenerSucesos("muchoViento pocaAgua");
		manejadorSucesos.suscribirImplicacion(accion, sucesos);
		//Se verifica que el dispositivo se encuentra apagado.
		assertEquals(false, dispositivo.isEncendido());
		manejadorSucesos.agregarSucesos( GeneradorDeSuceso.obtenerSucesos("pocaAgua muchoRuido muchoViento presionBaja"));
				
		//Se verifica que el dispositivo se enciende.
		assertEquals(true, dispositivo.isEncendido());
	}
	
	public void testSucesosInvalidosConEvaluadorDiscontinuo(){
		//Se crea el manejador de sucesos en modo discontinuo y la accion para encender el dispositivo.
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.obtenerConfiguracion().establecerEvaluadorDiscontinuo();
		AccionPrenderDispositivo accion = new AccionPrenderDispositivo();
		Dispositivo dispositivo = new Dispositivo(new CalefactorDriver());
		accion.establecerDispositivo(dispositivo);
				
		//Se suscribe un implicacion y se agregan sucesos sin respetar la totalidad necesaria para validar la implicacion.
		List<Suceso> sucesos = GeneradorDeSuceso.obtenerSucesos("muchoViento pocaAgua");
		manejadorSucesos.suscribirImplicacion(accion, sucesos);
		//Se verifica que el dispositivo se encuentra apagado.
		assertEquals(false, dispositivo.isEncendido());
		manejadorSucesos.agregarSucesos( GeneradorDeSuceso.obtenerSucesos("presionBaja muchoViento"));
		
		//Se verifica que el dispositivo se mantiene apagado.
		assertEquals(false, dispositivo.isEncendido());
	}
	
	public void testSucesoUnicoConEvaluadorDiscontinuo(){
		//Se crea el manejador de sucesos en modo discontinuo y la accion para encender el dispositivo.
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.obtenerConfiguracion().establecerEvaluadorDiscontinuo();
		AccionPrenderDispositivo accion = new AccionPrenderDispositivo();
		Dispositivo dispositivo = new Dispositivo(new CalefactorDriver());
		accion.establecerDispositivo(dispositivo);
	
		//Se suscribe un implicacion y se agregan sucesos sin respetar la totalidad necesaria para validar la implicacion.
		List<Suceso> sucesos = GeneradorDeSuceso.obtenerSucesos("muchoViento pocaAgua");
		manejadorSucesos.suscribirImplicacion(accion, sucesos);
		//Se verifica que el dispositivo se encuentra apagado.
		assertEquals(false, dispositivo.isEncendido());
		manejadorSucesos.agregarSucesos( GeneradorDeSuceso.obtenerSucesos("muchoViento"));

		//Se verifica que el dispositivo se mantiene apagado.
		assertEquals(false, dispositivo.isEncendido());
	}
	
	public void testSucesosContinuosConOrdenConEvaluadorDiscontinuo(){
		//Se crea el manejador de sucesos en modo discontinuo y la accion para encender el dispositivo.
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.obtenerConfiguracion().establecerEvaluadorDiscontinuo();
		AccionPrenderDispositivo accion = new AccionPrenderDispositivo();
		Dispositivo dispositivo = new Dispositivo(new CalefactorDriver());
		accion.establecerDispositivo(dispositivo);
		
		//Se suscribe un implicacion y se agregan sucesos respetando la totalidad necesaria para validar la implicacion.
		List<Suceso> sucesos = GeneradorDeSuceso.obtenerSucesos("muchoViento pocaAgua");
		manejadorSucesos.suscribirImplicacion(accion, sucesos);
		//Se verifica que el dispositivo se encuentra apagado.
		assertEquals(false, dispositivo.isEncendido());
		manejadorSucesos.agregarSucesos( GeneradorDeSuceso.obtenerSucesos("muchoViento pocaAgua muchoRuido presionBaja"));
		
		//Se verifica que el dispositivo se enciende.
		assertEquals(true, dispositivo.isEncendido());
	}

}
