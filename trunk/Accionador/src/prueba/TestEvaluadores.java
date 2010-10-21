package prueba;

import java.util.List;
import cliente.AccionPrenderBomba;
import cliente.Bomba;
import cliente.GeneradorDeSuceso;
import source.ManejadorDeSucesos;
import source.Suceso;
import junit.framework.TestCase;

public class TestEvaluadores extends TestCase {

	/*********************************************************************************
	 *                 Test para evaluador de Secuencias Continuas
	*********************************************************************************/
	public void testSucesosContinuosConOrdenConEvaluadorSecuenciaContinua(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.getConfiguracion().establecerEvaluadorSecuenciaContinua();
		Bomba bomba = new Bomba();
		AccionPrenderBomba accionPrenderBomba = new AccionPrenderBomba();
		accionPrenderBomba.setBomba(bomba);
		List<Suceso> sucesos = GeneradorDeSuceso.obtenerSucesos("pocaAgua presionAlta tanqueLleno");
		manejadorSucesos.suscribirImplicacion(accionPrenderBomba, sucesos);
		assertEquals(false, bomba.isEncendida());
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("pocaAgua presionAlta tanqueLleno"));
		assertEquals(true, bomba.isEncendida());
	}
	public void testSucesosContinuosSinOrdenConEvaluadorSecuenciaContinua(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.getConfiguracion().establecerEvaluadorSecuenciaContinua();
		Bomba bomba = new Bomba();
		AccionPrenderBomba accionPrenderBomba = new AccionPrenderBomba();
		accionPrenderBomba.setBomba(bomba);
		List<Suceso> sucesos = GeneradorDeSuceso.obtenerSucesos("pocaAgua presionAlta tanqueLleno");
		manejadorSucesos.suscribirImplicacion(accionPrenderBomba, sucesos);
		assertEquals(false, bomba.isEncendida());
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("presionAlta pocaAgua tanqueLleno"));
		assertEquals(false, bomba.isEncendida());
	}
	public void testSucesosNoValidosConEvaluadorSecuenciaContinua(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.getConfiguracion().establecerEvaluadorSecuenciaContinua();
		Bomba bomba = new Bomba();
		AccionPrenderBomba accionPrenderBomba = new AccionPrenderBomba();
		accionPrenderBomba.setBomba(bomba);
		List<Suceso> sucesos = GeneradorDeSuceso.obtenerSucesos("pocaAgua presionAlta tanqueLleno");
		manejadorSucesos.suscribirImplicacion(accionPrenderBomba, sucesos);
		assertEquals(false, bomba.isEncendida());
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("presionAlta pocaAgua tanqueRoto"));
		assertEquals(false, bomba.isEncendida());
	}
	
	public void testSucesosNoContinuosConOrdenConEvaluadorSecuenciaContinua(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.getConfiguracion().establecerEvaluadorSecuenciaContinua();
		Bomba bomba = new Bomba();
		AccionPrenderBomba accionPrenderBomba = new AccionPrenderBomba();
		accionPrenderBomba.setBomba(bomba);
		List<Suceso> sucesos = GeneradorDeSuceso.obtenerSucesos("pocaAgua presionAlta tanqueLleno");
		manejadorSucesos.suscribirImplicacion(accionPrenderBomba, sucesos);
		assertEquals(false, bomba.isEncendida());
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("pocaAgua tanqueRoto presionAlta tanqueLleno"));
		assertEquals(false, bomba.isEncendida());
	}
	/*********************************************************************************
	 *                 Test para evaluador de Secuencias Discontinuas
	*********************************************************************************/
	public void testSucesosContinuosConOrdenConEvaluadorSecuenciaDiscontinua(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.getConfiguracion().establecerEvaluadorSecuenciaDiscontinua();
		Bomba bomba = new Bomba();
		AccionPrenderBomba accionPrenderBomba = new AccionPrenderBomba();
		accionPrenderBomba.setBomba(bomba);
		List<Suceso> sucesos = GeneradorDeSuceso.obtenerSucesos("pocaAgua presionAlta tanqueLleno");
		manejadorSucesos.suscribirImplicacion(accionPrenderBomba, sucesos);
		assertEquals(false, bomba.isEncendida());
		manejadorSucesos.agregarSucesos( GeneradorDeSuceso.obtenerSucesos("pocaAgua presionAlta tanqueLleno"));
		assertEquals(true, bomba.isEncendida());
	}
	public void testSucesosDisContinuosConOrdenConEvaluadorSecuenciaDiscontinua(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.getConfiguracion().establecerEvaluadorSecuenciaDiscontinua();
		Bomba bomba = new Bomba();
		AccionPrenderBomba accionPrenderBomba = new AccionPrenderBomba();
		accionPrenderBomba.setBomba(bomba);
		List<Suceso> sucesos = GeneradorDeSuceso.obtenerSucesos("pocaAgua presionAlta tanqueLleno");
		manejadorSucesos.suscribirImplicacion(accionPrenderBomba, sucesos);
		assertEquals(false, bomba.isEncendida());
		manejadorSucesos.agregarSucesos( GeneradorDeSuceso.obtenerSucesos("pocaAgua presionBaja presionAlta algoDeAgua tanqueLleno"));
		assertEquals(true, bomba.isEncendida());
	}
	public void testSucesosDisContinuosConRepeticionesConOrdenConEvaluadorSecuenciaDiscontinua(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.getConfiguracion().establecerEvaluadorSecuenciaDiscontinua();
		Bomba bomba = new Bomba();
		AccionPrenderBomba accionPrenderBomba = new AccionPrenderBomba();
		accionPrenderBomba.setBomba(bomba);
		List<Suceso> sucesos = GeneradorDeSuceso.obtenerSucesos("pocaAgua presionAlta tanqueLleno");
		manejadorSucesos.suscribirImplicacion(accionPrenderBomba, sucesos);
		assertEquals(false, bomba.isEncendida());
		manejadorSucesos.agregarSucesos( GeneradorDeSuceso.obtenerSucesos("pocaAgua pocaAgua presionBaja presionAlta algoDeAgua tanqueLleno tanqueLleno"));
		assertEquals(true, bomba.isEncendida());
	}
	
	public void testSucesosInvalidosConEvaluadorSecuenciaDiscontinua(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.getConfiguracion().establecerEvaluadorSecuenciaDiscontinua();
		Bomba bomba = new Bomba();
		AccionPrenderBomba accionPrenderBomba = new AccionPrenderBomba();
		accionPrenderBomba.setBomba(bomba);
		List<Suceso> sucesos = GeneradorDeSuceso.obtenerSucesos("pocaAgua presionAlta tanqueLleno");
		manejadorSucesos.suscribirImplicacion(accionPrenderBomba, sucesos);
		assertEquals(false, bomba.isEncendida());
		manejadorSucesos.agregarSucesos( GeneradorDeSuceso.obtenerSucesos("algoDeAgua presionBaja presionAlta pocaAgua"));
		assertEquals(false, bomba.isEncendida());
	}
	public void testSucesoUnicoConEvaluadorSecuenciaDiscontinua(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.getConfiguracion().establecerEvaluadorSecuenciaDiscontinua();
		Bomba bomba = new Bomba();
		AccionPrenderBomba accionPrenderBomba = new AccionPrenderBomba();
		accionPrenderBomba.setBomba(bomba);
		List<Suceso> sucesos = GeneradorDeSuceso.obtenerSucesos("pocaAgua presionAlta tanqueLleno");
		manejadorSucesos.suscribirImplicacion(accionPrenderBomba, sucesos);
		assertEquals(false, bomba.isEncendida());
		manejadorSucesos.agregarSucesos( GeneradorDeSuceso.obtenerSucesos("pocaAgua"));
		assertEquals(false, bomba.isEncendida());
	}
	
	public void testSucesosContinuosSinOrdenConEvaluadorSecuenciaDiscontinua(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.getConfiguracion().establecerEvaluadorSecuenciaDiscontinua();
		Bomba bomba = new Bomba();
		AccionPrenderBomba accionPrenderBomba = new AccionPrenderBomba();
		accionPrenderBomba.setBomba(bomba);
		List<Suceso> sucesos = GeneradorDeSuceso.obtenerSucesos("pocaAgua presionAlta tanqueLleno");
		manejadorSucesos.suscribirImplicacion(accionPrenderBomba, sucesos);
		assertEquals(false, bomba.isEncendida());
		manejadorSucesos.agregarSucesos( GeneradorDeSuceso.obtenerSucesos("presionAlta tanqueLleno pocaAgua"));
		assertEquals(false, bomba.isEncendida());
	}
	public void testSucesosDiscontinuosSinOrdenConEvaluadorSecuenciaDiscontinua(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.getConfiguracion().establecerEvaluadorSecuenciaDiscontinua();
		Bomba bomba = new Bomba();
		AccionPrenderBomba accionPrenderBomba = new AccionPrenderBomba();
		accionPrenderBomba.setBomba(bomba);
		List<Suceso> sucesos = GeneradorDeSuceso.obtenerSucesos("pocaAgua presionAlta tanqueLleno");
		manejadorSucesos.suscribirImplicacion(accionPrenderBomba, sucesos);
		assertEquals(false, bomba.isEncendida());
		manejadorSucesos.agregarSucesos( GeneradorDeSuceso.obtenerSucesos("presionAlta tanqueLleno muchoCalor pocaAgua"));
		assertEquals(false, bomba.isEncendida());
	}
	
	/*********************************************************************************
	 *                 Test para evaluador de Sucesos Continuos
	*********************************************************************************/
	public void testSucesosDiscontinuosSinOrdenConEvaluadorContinuo(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.getConfiguracion().establecerEvaluadorContinuo();
		Bomba bomba = new Bomba();
		AccionPrenderBomba accionPrenderBomba = new AccionPrenderBomba();
		accionPrenderBomba.setBomba(bomba);
		List<Suceso> sucesos = GeneradorDeSuceso.obtenerSucesos("muchoViento pocaAgua");
		manejadorSucesos.suscribirImplicacion(accionPrenderBomba, sucesos);
		assertEquals(false, bomba.isEncendida());
		manejadorSucesos.agregarSucesos( GeneradorDeSuceso.obtenerSucesos("pocaAgua muchoRuido muchoViento presionBaja"));
		assertEquals(false, bomba.isEncendida());
	}
	public void testSucesosInvalidosConEvaluadorContinuo(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.getConfiguracion().establecerEvaluadorContinuo();
		Bomba bomba = new Bomba();
		AccionPrenderBomba accionPrenderBomba = new AccionPrenderBomba();
		accionPrenderBomba.setBomba(bomba);
		List<Suceso> sucesos = GeneradorDeSuceso.obtenerSucesos("muchoViento pocaAgua");
		manejadorSucesos.suscribirImplicacion(accionPrenderBomba, sucesos);
		assertEquals(false, bomba.isEncendida());
		manejadorSucesos.agregarSucesos( GeneradorDeSuceso.obtenerSucesos("presionBaja muchoViento"));
		assertEquals(false, bomba.isEncendida());
	}
	public void testSucesosUnicoConEvaluadorContinuo(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.getConfiguracion().establecerEvaluadorContinuo();
		Bomba bomba = new Bomba();
		AccionPrenderBomba accionPrenderBomba = new AccionPrenderBomba();
		accionPrenderBomba.setBomba(bomba);
		List<Suceso> sucesos = GeneradorDeSuceso.obtenerSucesos("muchoViento pocaAgua");
		manejadorSucesos.suscribirImplicacion(accionPrenderBomba, sucesos);
		assertEquals(false, bomba.isEncendida());
		manejadorSucesos.agregarSucesos( GeneradorDeSuceso.obtenerSucesos("muchoViento"));
		assertEquals(false, bomba.isEncendida());
	}
	public void testSucesosContinuosSinOrdenConEvaluadorContinuo(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.getConfiguracion().establecerEvaluadorContinuo();
		Bomba bomba = new Bomba();
		AccionPrenderBomba accionPrenderBomba = new AccionPrenderBomba();
		accionPrenderBomba.setBomba(bomba);
		List<Suceso> sucesos = GeneradorDeSuceso.obtenerSucesos("muchoViento pocaAgua");
		manejadorSucesos.suscribirImplicacion(accionPrenderBomba, sucesos);
		assertEquals(false, bomba.isEncendida());
		manejadorSucesos.agregarSucesos( GeneradorDeSuceso.obtenerSucesos("pocaAgua muchoViento muchoRuido presionBaja"));
		assertEquals(true, bomba.isEncendida());
	}
	
	public void testSucesosContinuosConOrdenConEvaluadorContinuo(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.getConfiguracion().establecerEvaluadorContinuo();
		Bomba bomba = new Bomba();
		AccionPrenderBomba accionPrenderBomba = new AccionPrenderBomba();
		accionPrenderBomba.setBomba(bomba);
		List<Suceso> sucesos = GeneradorDeSuceso.obtenerSucesos("muchoViento pocaAgua");
		manejadorSucesos.suscribirImplicacion(accionPrenderBomba, sucesos);
		assertEquals(false, bomba.isEncendida());
		manejadorSucesos.agregarSucesos( GeneradorDeSuceso.obtenerSucesos("muchoViento pocaAgua"));
		assertEquals(true, bomba.isEncendida());
	}
	
	/*********************************************************************************
	 *                 Test para evaluador de Sucesos Discontinuos
	*********************************************************************************/
	public void testSucesosExactosContinuosConOrdenConEvaluadorDiscontinuo(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.getConfiguracion().establecerEvaluadorDiscontinuo();
		Bomba bomba = new Bomba();
		AccionPrenderBomba accionPrenderBomba = new AccionPrenderBomba();
		accionPrenderBomba.setBomba(bomba);
		List<Suceso> sucesos = GeneradorDeSuceso.obtenerSucesos("muchoViento pocaAgua");
		manejadorSucesos.suscribirImplicacion(accionPrenderBomba, sucesos);
		assertEquals(false, bomba.isEncendida());
		manejadorSucesos.agregarSucesos( GeneradorDeSuceso.obtenerSucesos("muchoViento pocaAgua"));
		assertEquals(true, bomba.isEncendida());
	}
	public void testSucesosDiscontinuosSinOrdenConEvaluadorDiscontinuo(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.getConfiguracion().establecerEvaluadorDiscontinuo();
		Bomba bomba = new Bomba();
		AccionPrenderBomba accionPrenderBomba = new AccionPrenderBomba();
		accionPrenderBomba.setBomba(bomba);
		List<Suceso> sucesos = GeneradorDeSuceso.obtenerSucesos("muchoViento pocaAgua");
		manejadorSucesos.suscribirImplicacion(accionPrenderBomba, sucesos);
		assertEquals(false, bomba.isEncendida());
		manejadorSucesos.agregarSucesos( GeneradorDeSuceso.obtenerSucesos("pocaAgua muchoRuido muchoViento presionBaja"));
		assertEquals(true, bomba.isEncendida());
	}
	public void testSucesosInvalidosConEvaluadorDiscontinuo(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.getConfiguracion().establecerEvaluadorDiscontinuo();
		Bomba bomba = new Bomba();
		AccionPrenderBomba accionPrenderBomba = new AccionPrenderBomba();
		accionPrenderBomba.setBomba(bomba);
		List<Suceso> sucesos = GeneradorDeSuceso.obtenerSucesos("muchoViento pocaAgua");
		manejadorSucesos.suscribirImplicacion(accionPrenderBomba, sucesos);
		assertEquals(false, bomba.isEncendida());
		manejadorSucesos.agregarSucesos( GeneradorDeSuceso.obtenerSucesos("presionBaja muchoViento"));
		assertEquals(false, bomba.isEncendida());
	}
	
	public void testSucesoUnicoConEvaluadorDiscontinuo(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.getConfiguracion().establecerEvaluadorDiscontinuo();
		Bomba bomba = new Bomba();
		AccionPrenderBomba accionPrenderBomba = new AccionPrenderBomba();
		accionPrenderBomba.setBomba(bomba);
		List<Suceso> sucesos = GeneradorDeSuceso.obtenerSucesos("muchoViento pocaAgua");
		manejadorSucesos.suscribirImplicacion(accionPrenderBomba, sucesos);
		assertEquals(false, bomba.isEncendida());
		manejadorSucesos.agregarSucesos( GeneradorDeSuceso.obtenerSucesos("muchoViento"));
		assertEquals(false, bomba.isEncendida());
	}
	public void testSucesosContinuosConOrdenConEvaluadorDiscontinuo(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.getConfiguracion().establecerEvaluadorDiscontinuo();
		Bomba bomba = new Bomba();
		AccionPrenderBomba accionPrenderBomba = new AccionPrenderBomba();
		accionPrenderBomba.setBomba(bomba);
		List<Suceso> sucesos = GeneradorDeSuceso.obtenerSucesos("muchoViento pocaAgua");
		manejadorSucesos.suscribirImplicacion(accionPrenderBomba, sucesos);
		assertEquals(false, bomba.isEncendida());
		manejadorSucesos.agregarSucesos( GeneradorDeSuceso.obtenerSucesos("muchoViento pocaAgua muchoRuido presionBaja"));
		assertEquals(true, bomba.isEncendida());
	}

}
