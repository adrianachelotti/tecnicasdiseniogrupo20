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
		assertEquals("Bomba Apagada",false, bomba.isEncendida());
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("pocaAgua presionAlta tanqueLleno"));
		assertEquals("Bomba Prendida",true, bomba.isEncendida());
	}
	public void testSucesosContinuosSinOrdenConEvaluadorSecuenciaContinua(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.getConfiguracion().establecerEvaluadorSecuenciaContinua();
		Bomba bomba = new Bomba();
		AccionPrenderBomba accionPrenderBomba = new AccionPrenderBomba();
		accionPrenderBomba.setBomba(bomba);
		List<Suceso> sucesos = GeneradorDeSuceso.obtenerSucesos("pocaAgua presionAlta tanqueLleno");
		manejadorSucesos.suscribirImplicacion(accionPrenderBomba, sucesos);
		assertEquals("Bomba Apagada",false, bomba.isEncendida());
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("presionAlta pocaAgua tanqueLleno"));
		assertEquals("Bomba se mantiene apagada",false, bomba.isEncendida());
	}
	public void testSucesosNoValidosConEvaluadorSecuenciaContinua(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.getConfiguracion().establecerEvaluadorSecuenciaContinua();
		Bomba bomba = new Bomba();
		AccionPrenderBomba accionPrenderBomba = new AccionPrenderBomba();
		accionPrenderBomba.setBomba(bomba);
		List<Suceso> sucesos = GeneradorDeSuceso.obtenerSucesos("pocaAgua presionAlta tanqueLleno");
		manejadorSucesos.suscribirImplicacion(accionPrenderBomba, sucesos);
		assertEquals("Bomba Apagada",false, bomba.isEncendida());
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("presionAlta pocaAgua tanqueRoto"));
		assertEquals("Bomba se mantiene apagada",false, bomba.isEncendida());
	}
	
	public void testSucesosNoContinuosConOrdenConEvaluadorSecuenciaContinua(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.getConfiguracion().establecerEvaluadorSecuenciaContinua();
		Bomba bomba = new Bomba();
		AccionPrenderBomba accionPrenderBomba = new AccionPrenderBomba();
		accionPrenderBomba.setBomba(bomba);
		List<Suceso> sucesos = GeneradorDeSuceso.obtenerSucesos("pocaAgua presionAlta tanqueLleno");
		manejadorSucesos.suscribirImplicacion(accionPrenderBomba, sucesos);
		assertEquals("Bomba Apagada",false, bomba.isEncendida());
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("pocaAgua tanqueRoto presionAlta tanqueLleno"));
		assertEquals("Bomba se mantiene apagada",false, bomba.isEncendida());
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
		assertEquals("Bomba Apagada",false, bomba.isEncendida());
		manejadorSucesos.agregarSucesos( GeneradorDeSuceso.obtenerSucesos("pocaAgua presionAlta tanqueLleno"));
		assertEquals("Bomba Prendida",true, bomba.isEncendida());
	}
	public void testSucesosDisContinuosConOrdenConEvaluadorSecuenciaDiscontinua(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.getConfiguracion().establecerEvaluadorSecuenciaDiscontinua();
		Bomba bomba = new Bomba();
		AccionPrenderBomba accionPrenderBomba = new AccionPrenderBomba();
		accionPrenderBomba.setBomba(bomba);
		List<Suceso> sucesos = GeneradorDeSuceso.obtenerSucesos("pocaAgua presionAlta tanqueLleno");
		manejadorSucesos.suscribirImplicacion(accionPrenderBomba, sucesos);
		assertEquals("Bomba Apagada",false, bomba.isEncendida());
		manejadorSucesos.agregarSucesos( GeneradorDeSuceso.obtenerSucesos("pocaAgua presionBaja presionAlta algoDeAgua tanqueLleno"));
		assertEquals("Bomba Prendida",true, bomba.isEncendida());
	}
	public void testSucesosDisContinuosConRepeticionesConOrdenConEvaluadorSecuenciaDiscontinua(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.getConfiguracion().establecerEvaluadorSecuenciaDiscontinua();
		Bomba bomba = new Bomba();
		AccionPrenderBomba accionPrenderBomba = new AccionPrenderBomba();
		accionPrenderBomba.setBomba(bomba);
		List<Suceso> sucesos = GeneradorDeSuceso.obtenerSucesos("pocaAgua presionAlta tanqueLleno");
		manejadorSucesos.suscribirImplicacion(accionPrenderBomba, sucesos);
		assertEquals("Bomba Apagada",false, bomba.isEncendida());
		manejadorSucesos.agregarSucesos( GeneradorDeSuceso.obtenerSucesos("pocaAgua pocaAgua presionBaja presionAlta algoDeAgua tanqueLleno tanqueLleno"));
		assertEquals("Bomba Prendida",true, bomba.isEncendida());
	}
	
	public void testSucesosInvalidosConEvaluadorSecuenciaDiscontinua(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.getConfiguracion().establecerEvaluadorSecuenciaDiscontinua();
		Bomba bomba = new Bomba();
		AccionPrenderBomba accionPrenderBomba = new AccionPrenderBomba();
		accionPrenderBomba.setBomba(bomba);
		List<Suceso> sucesos = GeneradorDeSuceso.obtenerSucesos("pocaAgua presionAlta tanqueLleno");
		manejadorSucesos.suscribirImplicacion(accionPrenderBomba, sucesos);
		assertEquals("Bomba Apagada",false, bomba.isEncendida());
		manejadorSucesos.agregarSucesos( GeneradorDeSuceso.obtenerSucesos("algoDeAgua presionBaja presionAlta pocaAgua"));
		assertEquals("Bomba se mantiene apagada",false, bomba.isEncendida());
	}
	public void testSucesoUnicoConEvaluadorSecuenciaDiscontinua(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.getConfiguracion().establecerEvaluadorSecuenciaDiscontinua();
		Bomba bomba = new Bomba();
		AccionPrenderBomba accionPrenderBomba = new AccionPrenderBomba();
		accionPrenderBomba.setBomba(bomba);
		List<Suceso> sucesos = GeneradorDeSuceso.obtenerSucesos("pocaAgua presionAlta tanqueLleno");
		manejadorSucesos.suscribirImplicacion(accionPrenderBomba, sucesos);
		assertEquals("Bomba Apagada",false, bomba.isEncendida());
		manejadorSucesos.agregarSucesos( GeneradorDeSuceso.obtenerSucesos("pocaAgua"));
		assertEquals("Bomba se mantiene apagada",false, bomba.isEncendida());
	}
	
	public void testSucesosContinuosSinOrdenConEvaluadorSecuenciaDiscontinua(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.getConfiguracion().establecerEvaluadorSecuenciaDiscontinua();
		Bomba bomba = new Bomba();
		AccionPrenderBomba accionPrenderBomba = new AccionPrenderBomba();
		accionPrenderBomba.setBomba(bomba);
		List<Suceso> sucesos = GeneradorDeSuceso.obtenerSucesos("pocaAgua presionAlta tanqueLleno");
		manejadorSucesos.suscribirImplicacion(accionPrenderBomba, sucesos);
		assertEquals("Bomba Apagada",false, bomba.isEncendida());
		manejadorSucesos.agregarSucesos( GeneradorDeSuceso.obtenerSucesos("presionAlta tanqueLleno pocaAgua"));
		assertEquals("Bomba se mantiene apagada",false, bomba.isEncendida());
	}
	public void testSucesosDiscontinuosSinOrdenConEvaluadorSecuenciaDiscontinua(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.getConfiguracion().establecerEvaluadorSecuenciaDiscontinua();
		Bomba bomba = new Bomba();
		AccionPrenderBomba accionPrenderBomba = new AccionPrenderBomba();
		accionPrenderBomba.setBomba(bomba);
		List<Suceso> sucesos = GeneradorDeSuceso.obtenerSucesos("pocaAgua presionAlta tanqueLleno");
		manejadorSucesos.suscribirImplicacion(accionPrenderBomba, sucesos);
		assertEquals("Bomba Apagada",false, bomba.isEncendida());
		manejadorSucesos.agregarSucesos( GeneradorDeSuceso.obtenerSucesos("presionAlta tanqueLleno muchoCalor pocaAgua"));
		assertEquals("Bomba se mantiene apagada",false, bomba.isEncendida());
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
		assertEquals("Bomba Apagada",false, bomba.isEncendida());
		manejadorSucesos.agregarSucesos( GeneradorDeSuceso.obtenerSucesos("pocaAgua muchoRuido muchoViento presionBaja"));
		assertEquals("Bomba se mantiene apagada",false, bomba.isEncendida());
	}
	public void testSucesosInvalidosConEvaluadorContinuo(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.getConfiguracion().establecerEvaluadorContinuo();
		Bomba bomba = new Bomba();
		AccionPrenderBomba accionPrenderBomba = new AccionPrenderBomba();
		accionPrenderBomba.setBomba(bomba);
		List<Suceso> sucesos = GeneradorDeSuceso.obtenerSucesos("muchoViento pocaAgua");
		manejadorSucesos.suscribirImplicacion(accionPrenderBomba, sucesos);
		assertEquals("Bomba Apagada",false, bomba.isEncendida());
		manejadorSucesos.agregarSucesos( GeneradorDeSuceso.obtenerSucesos("presionBaja muchoViento"));
		assertEquals("Bomba se mantiene apagada",false, bomba.isEncendida());
	}
	public void testSucesosUnicoConEvaluadorContinuo(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.getConfiguracion().establecerEvaluadorContinuo();
		Bomba bomba = new Bomba();
		AccionPrenderBomba accionPrenderBomba = new AccionPrenderBomba();
		accionPrenderBomba.setBomba(bomba);
		List<Suceso> sucesos = GeneradorDeSuceso.obtenerSucesos("muchoViento pocaAgua");
		manejadorSucesos.suscribirImplicacion(accionPrenderBomba, sucesos);
		assertEquals("Bomba Apagada",false, bomba.isEncendida());
		manejadorSucesos.agregarSucesos( GeneradorDeSuceso.obtenerSucesos("muchoViento"));
		assertEquals("Bomba se mantiene apagada",false, bomba.isEncendida());
	}
	public void testSucesosContinuosSinOrdenConEvaluadorContinuo(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.getConfiguracion().establecerEvaluadorContinuo();
		Bomba bomba = new Bomba();
		AccionPrenderBomba accionPrenderBomba = new AccionPrenderBomba();
		accionPrenderBomba.setBomba(bomba);
		List<Suceso> sucesos = GeneradorDeSuceso.obtenerSucesos("muchoViento pocaAgua");
		manejadorSucesos.suscribirImplicacion(accionPrenderBomba, sucesos);
		assertEquals("Bomba Apagada",false, bomba.isEncendida());
		manejadorSucesos.agregarSucesos( GeneradorDeSuceso.obtenerSucesos("pocaAgua muchoViento muchoRuido presionBaja"));
		assertEquals("Bomba Prendida",true, bomba.isEncendida());
	}
	
	public void testSucesosContinuosConOrdenConEvaluadorContinuo(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.getConfiguracion().establecerEvaluadorContinuo();
		Bomba bomba = new Bomba();
		AccionPrenderBomba accionPrenderBomba = new AccionPrenderBomba();
		accionPrenderBomba.setBomba(bomba);
		List<Suceso> sucesos = GeneradorDeSuceso.obtenerSucesos("muchoViento pocaAgua");
		manejadorSucesos.suscribirImplicacion(accionPrenderBomba, sucesos);
		assertEquals("Bomba Apagada",false, bomba.isEncendida());
		manejadorSucesos.agregarSucesos( GeneradorDeSuceso.obtenerSucesos("muchoViento pocaAgua"));
		assertEquals("Bomba Prendida",true, bomba.isEncendida());
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
		assertEquals("Bomba Apagada",false, bomba.isEncendida());
		manejadorSucesos.agregarSucesos( GeneradorDeSuceso.obtenerSucesos("muchoViento pocaAgua"));
		assertEquals("Bomba Prendida",true, bomba.isEncendida());
	}
	public void testSucesosDiscontinuosSinOrdenConEvaluadorDiscontinuo(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.getConfiguracion().establecerEvaluadorDiscontinuo();
		Bomba bomba = new Bomba();
		AccionPrenderBomba accionPrenderBomba = new AccionPrenderBomba();
		accionPrenderBomba.setBomba(bomba);
		List<Suceso> sucesos = GeneradorDeSuceso.obtenerSucesos("muchoViento pocaAgua");
		manejadorSucesos.suscribirImplicacion(accionPrenderBomba, sucesos);
		assertEquals("Bomba Apagada",false, bomba.isEncendida());
		manejadorSucesos.agregarSucesos( GeneradorDeSuceso.obtenerSucesos("pocaAgua muchoRuido muchoViento presionBaja"));
		assertEquals("Bomba Prendida",true, bomba.isEncendida());
	}
	public void testSucesosInvalidosConEvaluadorDiscontinuo(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.getConfiguracion().establecerEvaluadorDiscontinuo();
		Bomba bomba = new Bomba();
		AccionPrenderBomba accionPrenderBomba = new AccionPrenderBomba();
		accionPrenderBomba.setBomba(bomba);
		List<Suceso> sucesos = GeneradorDeSuceso.obtenerSucesos("muchoViento pocaAgua");
		manejadorSucesos.suscribirImplicacion(accionPrenderBomba, sucesos);
		assertEquals("Bomba Apagada",false, bomba.isEncendida());
		manejadorSucesos.agregarSucesos( GeneradorDeSuceso.obtenerSucesos("presionBaja muchoViento"));
		assertEquals("Bomba se mantiene apagada",false, bomba.isEncendida());
	}
	
	public void testSucesoUnicoConEvaluadorDiscontinuo(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.getConfiguracion().establecerEvaluadorDiscontinuo();
		Bomba bomba = new Bomba();
		AccionPrenderBomba accionPrenderBomba = new AccionPrenderBomba();
		accionPrenderBomba.setBomba(bomba);
		List<Suceso> sucesos = GeneradorDeSuceso.obtenerSucesos("muchoViento pocaAgua");
		manejadorSucesos.suscribirImplicacion(accionPrenderBomba, sucesos);
		assertEquals("Bomba Apagada",false, bomba.isEncendida());
		manejadorSucesos.agregarSucesos( GeneradorDeSuceso.obtenerSucesos("muchoViento"));
		assertEquals("Bomba se mantiene apagada",false, bomba.isEncendida());
	}
	public void testSucesosContinuosConOrdenConEvaluadorDiscontinuo(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.getConfiguracion().establecerEvaluadorDiscontinuo();
		Bomba bomba = new Bomba();
		AccionPrenderBomba accionPrenderBomba = new AccionPrenderBomba();
		accionPrenderBomba.setBomba(bomba);
		List<Suceso> sucesos = GeneradorDeSuceso.obtenerSucesos("muchoViento pocaAgua");
		manejadorSucesos.suscribirImplicacion(accionPrenderBomba, sucesos);
		assertEquals("Bomba Apagada",false, bomba.isEncendida());
		manejadorSucesos.agregarSucesos( GeneradorDeSuceso.obtenerSucesos("muchoViento pocaAgua muchoRuido presionBaja"));
		assertEquals("Bomba Prendida",true, bomba.isEncendida());
	}

}
