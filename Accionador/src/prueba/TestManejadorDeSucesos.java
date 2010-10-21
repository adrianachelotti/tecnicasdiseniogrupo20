package prueba;

import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;
import source.ManejadorDeSucesos;
import source.Suceso;
import cliente.AccionAgregarGaseosa;
import cliente.AccionPrenderBomba;
import cliente.AccionQuitarGaseosa;
import cliente.AccionRepararMaquinaGasesosa;
import cliente.AccionTirarGaseosa;
import cliente.Bomba;
import cliente.Gaseosa;
import cliente.GeneradorDeSuceso;
import cliente.MaquinaGaseosa;

public class TestManejadorDeSucesos extends  TestCase {

	
	public void testSuscribirImplicacionYNotificarSucesoUnicoValido(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		Bomba bomba = new Bomba();
		Suceso sucesoPocaAgua = new Suceso("pocaAgua");
		AccionPrenderBomba accionPrenderBomba = new AccionPrenderBomba();
		accionPrenderBomba.setBomba(bomba);
		//si pocaAgua ---> accionPrenderBomba
		manejadorSucesos.suscribirImplicacion(accionPrenderBomba, sucesoPocaAgua);
		assertEquals(false, bomba.isEncendida());
		manejadorSucesos.agregarSuceso(sucesoPocaAgua);
		assertEquals(true,bomba.isEncendida());
	}
	
	public void testSuscribirImplicacionYNotificarSucesoInvalido(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		Bomba bomba = new Bomba();		
		AccionPrenderBomba accionPrenderBomba = new AccionPrenderBomba();
		accionPrenderBomba.setBomba(bomba);
		//si pocaAgua ---> accionPrenderBomba
		manejadorSucesos.suscribirImplicacion(accionPrenderBomba, new Suceso("pocaAgua"));
		assertEquals(false,bomba.isEncendida());
		//notificacion sucesoPresionAlta
		manejadorSucesos.agregarSuceso(new Suceso("presionAlta"));
		assertEquals(false, bomba.isEncendida());
	}
	
	// El segundo suceso es valido	
	public void testSuscribirImplicacionYNotificarMultiplesSucesos(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		Bomba bomba = new Bomba();
		AccionPrenderBomba accionPrenderBomba = new AccionPrenderBomba();
		accionPrenderBomba.setBomba(bomba);
		//Si pocaAgua ^ presionAlta-------->PrenderBomBa
		List<Suceso> sucesos = GeneradorDeSuceso.obtenerSucesos("pocaAgua presionAlta");
		manejadorSucesos.suscribirImplicacion(accionPrenderBomba, sucesos);
		assertEquals(false, bomba.isEncendida());
		//notificacion presionAlta
		manejadorSucesos.agregarSuceso(new Suceso("presionAlta"));
		assertEquals(false, bomba.isEncendida());
		//notificacion pocaAgua
		manejadorSucesos.agregarSuceso(new Suceso("pocaAgua"));
		assertEquals(true, bomba.isEncendida());
	}
	
	
	// Se testea la suscripcion de implicaciones y la notificacion de varios suceos
	// ocurridos
	public void testSuscribirImplicacionYNotificarMultiplesSucesosInvalidos(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		Bomba bomba = new Bomba();
		Suceso sucesoPocaAgua = new Suceso("pocaAgua");
		Suceso sucesoPresionAlta = new Suceso("presionAlta");
		AccionPrenderBomba accionPrenderBomba = new AccionPrenderBomba();
		accionPrenderBomba.setBomba(bomba);
		List<Suceso> sucesos = new ArrayList<Suceso>();
		//Si pocaAgua ^ presionAlta-------->PrenderBomBa
		sucesos.add(sucesoPocaAgua);
		sucesos.add(sucesoPresionAlta);
		manejadorSucesos.suscribirImplicacion(accionPrenderBomba, sucesos);
		assertEquals(false, bomba.isEncendida());
		
		List<Suceso> sucesosNuevos = GeneradorDeSuceso.obtenerSucesos("pocaAgua tanqueLleno");
		//notificacion pocaAgua ^ presionAlta
		manejadorSucesos.agregarSucesos(sucesosNuevos);
		assertEquals(false, bomba.isEncendida());
	}
	
	
	 // Se realizan pruebas para ver si la funcionalidad de suscribir
	 // sucesos y acciones se realizan de la forma correcta
	 
	/****************************************************
	 *                      CASO 1	 
	 *****************************************************/
	public void testNotificarUnicoSuceso(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		Bomba bomba = new Bomba();
		AccionPrenderBomba accionPrenderBomba = new AccionPrenderBomba();
		accionPrenderBomba.setBomba(bomba);
		manejadorSucesos.getConfiguracion().establecerEvaluadorDiscontinuo();
		List<Suceso> lista = GeneradorDeSuceso.obtenerSucesos("A");
		bomba.setEncendida(false);
		manejadorSucesos.suscribirImplicacion(accionPrenderBomba, lista);
		//agrego el suceso y notifico
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("X Y Z A"));
		assertEquals("Implicacion Valida", bomba.isEncendida(),true);	
	}
	
	/****************************************************
	 *                      CASO 2	 
	 *****************************************************/
	public void testNotificarSucesosDiscontinuosSinImportarOrdenSinCancelaciones(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		Bomba bomba = new Bomba();
		AccionPrenderBomba accionPrenderBomba = new AccionPrenderBomba();
		accionPrenderBomba.setBomba(bomba);
	    manejadorSucesos.getConfiguracion().establecerEvaluadorDiscontinuo();
		List<Suceso> lista = GeneradorDeSuceso.obtenerSucesos("A B");
		manejadorSucesos.suscribirImplicacion(accionPrenderBomba, lista);
		//agrego el suceso y notifico
		bomba.setEncendida(false);
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("X Y Z A B"));
		assertEquals("Implicacion Valida", bomba.isEncendida(),true);		
	}
	public void testNotificarSucesosDiscontinuosSinImportarOrdenSinCancelaciones2(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		Bomba bomba = new Bomba();
		AccionPrenderBomba accionPrenderBomba = new AccionPrenderBomba();
		accionPrenderBomba.setBomba(bomba);
	    manejadorSucesos.getConfiguracion().establecerEvaluadorDiscontinuo();
		List<Suceso> lista = GeneradorDeSuceso.obtenerSucesos("A B");
		manejadorSucesos.suscribirImplicacion(accionPrenderBomba, lista);
		//agrego el suceso y notifico
		bomba.setEncendida(false);
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("X Y Z B A"));
		assertEquals("Implicacion Valida", bomba.isEncendida(),true);		
	}
	public void testNotificarSucesosDiscontinuosSinImportarOrdenSinCancelaciones3(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		Bomba bomba = new Bomba();
		AccionPrenderBomba accionPrenderBomba = new AccionPrenderBomba();
		accionPrenderBomba.setBomba(bomba);
		manejadorSucesos.getConfiguracion().establecerEvaluadorDiscontinuo();
		List<Suceso> lista = GeneradorDeSuceso.obtenerSucesos("A B");
		manejadorSucesos.suscribirImplicacion(accionPrenderBomba, lista);
		//agrego el suceso y notifico
		bomba.setEncendida(false);
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("X Y Z A K B"));
		assertEquals("Implicacion Valida", bomba.isEncendida(),true);		
	}
	
	public void testNotificarSucesosDiscontinuosSinImportarOrdenSinCancelaciones4(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		Bomba bomba = new Bomba();
		AccionPrenderBomba accionPrenderBomba = new AccionPrenderBomba();
		accionPrenderBomba.setBomba(bomba);
	    manejadorSucesos.getConfiguracion().establecerEvaluadorDiscontinuo();
		List<Suceso> lista = GeneradorDeSuceso.obtenerSucesos("A B");
		manejadorSucesos.suscribirImplicacion(accionPrenderBomba, lista);
		//agrego el suceso y notifico
		bomba.setEncendida(false);
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("X Y Z B K A"));
		assertEquals("Implicacion Valida", bomba.isEncendida(),true);		
	}
	
	/****************************************************
	 *                      CASO 3	 
	 *****************************************************/
	public void testNotificarSucesosContinuosSinImportarOrdenSinCancelaciones(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		Bomba bomba = new Bomba();
		AccionPrenderBomba accionPrenderBomba = new AccionPrenderBomba();
		accionPrenderBomba.setBomba(bomba);
	    manejadorSucesos.getConfiguracion().establecerEvaluadorContinuo();
	    List<Suceso> lista = GeneradorDeSuceso.obtenerSucesos("A B");
		manejadorSucesos.suscribirImplicacion(accionPrenderBomba, lista);
		//agrego el suceso y notifico
		bomba.setEncendida(false);
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("X Y Z A B"));
		assertEquals("Implicacion Valida", bomba.isEncendida(),true);		
	}
	
	
	public void testNotificarSucesosContinuosSinImportarOrdenSinCancelaciones2(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		Bomba bomba = new Bomba();
		AccionPrenderBomba accionPrenderBomba = new AccionPrenderBomba();
		accionPrenderBomba.setBomba(bomba);
	    manejadorSucesos.getConfiguracion().establecerEvaluadorContinuo();
	    List<Suceso> lista = GeneradorDeSuceso.obtenerSucesos("A B");
		manejadorSucesos.suscribirImplicacion(accionPrenderBomba, lista);
		//agrego el suceso y notifico
		bomba.setEncendida(false);
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("X Y Z B A"));
		assertEquals("Implicacion Valida", bomba.isEncendida(),true);		
	}
	
	public void testNotificarSucesosContinuosSinImportarOrdenSinCancelaciones3(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		Bomba bomba = new Bomba();
		AccionPrenderBomba accionPrenderBomba = new AccionPrenderBomba();
		accionPrenderBomba.setBomba(bomba);
	    manejadorSucesos.getConfiguracion().establecerEvaluadorContinuo();
	    List<Suceso> lista = GeneradorDeSuceso.obtenerSucesos("A B");
		manejadorSucesos.suscribirImplicacion(accionPrenderBomba, lista);
		//agrego el suceso y notifico
		bomba.setEncendida(false);
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("X Y Z A K B"));
		assertEquals("Implicacion Invalida", bomba.isEncendida(),false);		
	}
	
	public void testNotificarSucesosContinuosSinImportarOrdenSinCancelaciones4(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		Bomba bomba = new Bomba();
		AccionPrenderBomba accionPrenderBomba = new AccionPrenderBomba();
		accionPrenderBomba.setBomba(bomba);
	    manejadorSucesos.getConfiguracion().establecerEvaluadorContinuo();
	    List<Suceso> lista = GeneradorDeSuceso.obtenerSucesos("A B");
		manejadorSucesos.suscribirImplicacion(accionPrenderBomba, lista);
		//agrego el suceso y notifico
		bomba.setEncendida(false);
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("X Y Z B K A"));
		assertEquals("Implicacion Invalida", bomba.isEncendida(),false);		
	}
	/****************************************************
	 *                      CASO 4	 
	 *****************************************************/
	public void testNotificarSucesosDiscontinuosImportaOrdenSinCancelaciones(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		Bomba bomba = new Bomba();
		AccionPrenderBomba accionPrenderBomba = new AccionPrenderBomba();
		accionPrenderBomba.setBomba(bomba);
		manejadorSucesos.getConfiguracion().establecerEvaluadorSecuenciaDiscontinua();
	    List<Suceso> lista = GeneradorDeSuceso.obtenerSucesos("A B");
		manejadorSucesos.suscribirImplicacion(accionPrenderBomba, lista);
		//agrego el suceso y notifico
		bomba.setEncendida(false);
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("X Y Z A B"));
		assertEquals("Implicacion Valida", bomba.isEncendida(),true);		
	}
	
	public void testNotificarSucesosDiscontinuosImportaOrdenSinCancelaciones2(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		Bomba bomba = new Bomba();
		AccionPrenderBomba accionPrenderBomba = new AccionPrenderBomba();
		accionPrenderBomba.setBomba(bomba);
		manejadorSucesos.getConfiguracion().establecerEvaluadorSecuenciaDiscontinua();
		List<Suceso> lista = GeneradorDeSuceso.obtenerSucesos("A B");
		manejadorSucesos.suscribirImplicacion(accionPrenderBomba, lista);
		//agrego el suceso y notifico
		bomba.setEncendida(false);
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("X Y Z B A"));
		assertEquals("Implicacion Invalida", bomba.isEncendida(),false);		
	}
	
	
	public void testNotificarSucesosDiscontinuosImportaOrdenSinCancelaciones3(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		Bomba bomba = new Bomba();
		AccionPrenderBomba accionPrenderBomba = new AccionPrenderBomba();
		accionPrenderBomba.setBomba(bomba);
		manejadorSucesos.getConfiguracion().establecerEvaluadorSecuenciaDiscontinua();
		List<Suceso> lista = GeneradorDeSuceso.obtenerSucesos("A B");
		manejadorSucesos.suscribirImplicacion(accionPrenderBomba, lista);
		//agrego el suceso y notifico
		bomba.setEncendida(false);
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("X Y Z A K B"));
		assertEquals("Implicacion Valida", bomba.isEncendida(),true);		
	}
	
	public void testNotificarSucesosDiscontinuosImportaOrdenSinCancelaciones4(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		Bomba bomba = new Bomba();
		AccionPrenderBomba accionPrenderBomba = new AccionPrenderBomba();
		accionPrenderBomba.setBomba(bomba);
		manejadorSucesos.getConfiguracion().establecerEvaluadorSecuenciaDiscontinua();
		List<Suceso> lista = GeneradorDeSuceso.obtenerSucesos("A B");
		manejadorSucesos.suscribirImplicacion(accionPrenderBomba, lista);
		//agrego el suceso y notifico
		bomba.setEncendida(false);
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("X Y Z B K A"));
		assertEquals("Implicacion Invalida", bomba.isEncendida(),false);		
	}
	
	/****************************************************
	 *                      CASO 5	 
	 *****************************************************/
	public void testNotificarSucesosContinuosImportaOrdenSinCancelaciones(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		Bomba bomba = new Bomba();
		AccionPrenderBomba accionPrenderBomba = new AccionPrenderBomba();
		accionPrenderBomba.setBomba(bomba);
		manejadorSucesos.getConfiguracion().establecerEvaluadorSecuenciaContinua();
		List<Suceso> lista = GeneradorDeSuceso.obtenerSucesos("A B");
		manejadorSucesos.suscribirImplicacion(accionPrenderBomba, lista);
		//agrego el suceso y notifico
		bomba.setEncendida(false);
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("X Y Z A B"));
		assertEquals("Implicacion Valida", bomba.isEncendida(),true);		
	}
	public void testNotificarSucesosContinuosImportaOrdenSinCancelaciones2(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		Bomba bomba = new Bomba();
		AccionPrenderBomba accionPrenderBomba = new AccionPrenderBomba();
		accionPrenderBomba.setBomba(bomba);
		manejadorSucesos.getConfiguracion().establecerEvaluadorSecuenciaContinua();
		List<Suceso> lista = GeneradorDeSuceso.obtenerSucesos("A B");
		manejadorSucesos.suscribirImplicacion(accionPrenderBomba, lista);
		//agrego el suceso y notifico
		bomba.setEncendida(false);
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("X Y Z B A"));
		assertEquals("Implicacion Invalida", bomba.isEncendida(),false);		
	}
	
	public void testNotificarSucesosContinuosImportaOrdenSinCancelaciones3(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		Bomba bomba = new Bomba();
		AccionPrenderBomba accionPrenderBomba = new AccionPrenderBomba();
		accionPrenderBomba.setBomba(bomba);
		manejadorSucesos.getConfiguracion().establecerEvaluadorSecuenciaContinua();
		List<Suceso> lista = GeneradorDeSuceso.obtenerSucesos("A B");
		manejadorSucesos.suscribirImplicacion(accionPrenderBomba, lista);
		//agrego el suceso y notifico
		bomba.setEncendida(false);
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("X Y Z A K B"));
		assertEquals("Implicacion Invalida", bomba.isEncendida(),false);		
	}
	
	public void testNotificarSucesosContinuosImportaOrdenSinCancelaciones4(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		Bomba bomba = new Bomba();
		AccionPrenderBomba accionPrenderBomba = new AccionPrenderBomba();
		accionPrenderBomba.setBomba(bomba);
		manejadorSucesos.getConfiguracion().establecerEvaluadorSecuenciaContinua();
		List<Suceso> lista = GeneradorDeSuceso.obtenerSucesos("A B");
		manejadorSucesos.suscribirImplicacion(accionPrenderBomba, lista);
		//agrego el suceso y notifico
		bomba.setEncendida(false);
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("X Y Z B K A"));
		assertEquals("Implicacion Invalida", bomba.isEncendida(),false);
	}
	/****************************************************
	 *                     TODO: CASO 6
	 *****************************************************/
	
			
	
	public void testAgregoSucesoUnicoNulo(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		Bomba bomba = new Bomba();
		Suceso sucesoPocaAgua = new Suceso("pocaAgua");
		Suceso sucesoPresionAlta = new Suceso("presionAlta");
		AccionPrenderBomba accionPrenderBomba = new AccionPrenderBomba();
		accionPrenderBomba.setBomba(bomba);
		List<Suceso> sucesos = new ArrayList<Suceso>();
		sucesos.add(sucesoPocaAgua);
		sucesos.add(sucesoPresionAlta);
		manejadorSucesos.suscribirImplicacion(accionPrenderBomba, sucesos);
		assertEquals(false, bomba.isEncendida());
		//no agrega el suceso nulo,entonces no notifica nada
		manejadorSucesos.agregarSuceso(null);
		assertEquals(false, bomba.isEncendida());
	}
	public void testAgregoSucesoNuloYOtrosNoNulosQueEjecutanAccion(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		Bomba bomba = new Bomba();
		AccionPrenderBomba accionPrenderBomba = new AccionPrenderBomba();
		accionPrenderBomba.setBomba(bomba);
		List<Suceso> sucesos = GeneradorDeSuceso.obtenerSucesos("pocaAgua presionAlta");
		manejadorSucesos.suscribirImplicacion(accionPrenderBomba, sucesos);
		assertEquals("Bomba apagada",false, bomba.isEncendida());
	
		//ignora el suceso nulo y notifica los sucesos
		manejadorSucesos.agregarSuceso(new Suceso("pocaAgua"));
		manejadorSucesos.agregarSuceso(new Suceso("presionAlta"));
		manejadorSucesos.agregarSuceso(null);
		assertEquals("Bomba encendida",true, bomba.isEncendida());
		
	}
	public void testSuscribirImplicacionConListaNula(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		Bomba bomba = new Bomba();
		AccionPrenderBomba accionPrenderBomba = new AccionPrenderBomba();
		accionPrenderBomba.setBomba(bomba);
		List<Suceso> sucesos = null;
		manejadorSucesos.suscribirImplicacion(accionPrenderBomba, sucesos);
		assertEquals("Bomba apagada",false, bomba.isEncendida());
	
		//ignora el suceso nulo y notifica los sucesos
		manejadorSucesos.agregarSuceso(new Suceso("pocaAgua"));
		manejadorSucesos.agregarSuceso(new Suceso("presionAlta"));
		assertEquals("Se mantiene apagada", false, bomba.isEncendida());
		
	}
	public void testSuscribirImplicacionConSucesoNulo(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		Bomba bomba = new Bomba();
		AccionPrenderBomba accionPrenderBomba = new AccionPrenderBomba();
		accionPrenderBomba.setBomba(bomba);
		Suceso sucesoNulo =null;
		manejadorSucesos.suscribirImplicacion(accionPrenderBomba, sucesoNulo);
		assertEquals("Bomba apagada",false, bomba.isEncendida());
		
		//ignora el suceso nulo y notifica los sucesos
		manejadorSucesos.agregarSuceso(new Suceso("pocaAgua"));
		manejadorSucesos.agregarSuceso(new Suceso("presionAlta"));
		assertEquals("Se mantiene apagada", false, bomba.isEncendida());
		
	}
	public void testAgregarListaDeSucesosNulos(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		Bomba bomba = new Bomba();
		AccionPrenderBomba accionPrenderBomba = new AccionPrenderBomba();
		accionPrenderBomba.setBomba(bomba);
		List<Suceso> sucesos = GeneradorDeSuceso.obtenerSucesos("pocaAgua presionAlta");
		manejadorSucesos.suscribirImplicacion(accionPrenderBomba, sucesos);
		assertEquals("Bomba apagada",false, bomba.isEncendida());	
		
		manejadorSucesos.agregarSucesos(null);
		assertEquals("Se mantiene apagada",false, bomba.isEncendida());		
	}
	
 
	
	
	/**
	 * En esta test se prueba el funcionamiento del manejador de Sucesos
	 * con 10 implicaciones y se evaluan los resultados de las accion.
	 */
	public void testDeEstres(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		List<Suceso> lista0 = GeneradorDeSuceso.obtenerSucesos("ponerFicha seleccionaCoca apretaBoton");
		List<Suceso> lista1 = GeneradorDeSuceso.obtenerSucesos("noHayCoca noHayCocaLight noHaySprite");
		List<Suceso> lista2 = GeneradorDeSuceso.obtenerSucesos("ingresaMoneda importeInsuficiente");
		List<Suceso> lista3 = GeneradorDeSuceso.obtenerSucesos("apretaBoton seleccionaNada");
		List<Suceso> lista4 = GeneradorDeSuceso.obtenerSucesos("ingresoSobranteMoneda noHayCambio");
		List<Suceso> lista5 = GeneradorDeSuceso.obtenerSucesos("ponerFicha fichaTrabada");
		List<Suceso> lista6 = GeneradorDeSuceso.obtenerSucesos("gaseosaCaliente");
		List<Suceso> lista7 = GeneradorDeSuceso.obtenerSucesos("pocaGaseosa maquinaDesconectada");
		List<Suceso> lista8 = GeneradorDeSuceso.obtenerSucesos("gaseosaFria stockLleno");
		List<Suceso> lista9 = GeneradorDeSuceso.obtenerSucesos("maquinaRota");
		
		
		
		Gaseosa gaseosa = new Gaseosa();
		Gaseosa gaseosa1 = new Gaseosa();
		gaseosa.setFrio(true);
		gaseosa.setNombre("Coca");
		gaseosa.setStock(10);
		
		
		gaseosa.setFrio(true);
		gaseosa.setNombre("Sprite");
		gaseosa.setStock(10);
		
		MaquinaGaseosa maquina = new MaquinaGaseosa();
		maquina.agregarGaseosa(gaseosa1);
		maquina.agregarGaseosa(gaseosa);
		
		AccionAgregarGaseosa accionAgregarGaseosa = new AccionAgregarGaseosa();
		accionAgregarGaseosa.setGaseosa(new Gaseosa("Fanta",10,false));
		
		AccionRepararMaquinaGasesosa accionReparar = new AccionRepararMaquinaGasesosa();
		accionReparar.setMaquina(maquina);
		
		AccionQuitarGaseosa accionQuitarGaseosa = new AccionQuitarGaseosa();
		accionQuitarGaseosa.setGaseosa(new Gaseosa("coca", 20, false));
		//para mayor facilidad pongo que si cumple la implicancia
		assertEquals(false,maquina.isRota());
		manejadorSucesos.suscribirImplicacion(accionReparar, lista0);
		manejadorSucesos.suscribirImplicacion(accionReparar, lista1);
		manejadorSucesos.suscribirImplicacion(accionReparar, lista2);
		manejadorSucesos.suscribirImplicacion(accionReparar, lista3);
		manejadorSucesos.suscribirImplicacion(accionReparar, lista4);
		manejadorSucesos.suscribirImplicacion(accionReparar, lista5);
		manejadorSucesos.suscribirImplicacion(accionReparar, lista6);
		manejadorSucesos.suscribirImplicacion(accionReparar, lista7);
		manejadorSucesos.suscribirImplicacion(accionReparar, lista8);
		manejadorSucesos.suscribirImplicacion(accionReparar, lista9);
		
		//Suceso que ninguna de las implicaciones espera
		manejadorSucesos.agregarSuceso(new Suceso("stockLleno"));
		assertEquals(false,maquina.isRota());
		
				
		//la implicacion de la lista 8 espera este conjunto de sucesos
		manejadorSucesos.agregarSuceso(new Suceso("stockLleno"));
		manejadorSucesos.agregarSuceso(new Suceso("gaseosaFria"));
		assertEquals(true,maquina.isRota());
		
		
		//como se que se ejecuta el comando de la implicacion 8, le cambio el comando
		assertEquals(20,accionQuitarGaseosa.getGaseosa().getStock());
		manejadorSucesos.borrarImplicaciones();
		manejadorSucesos.suscribirImplicacion(accionReparar, lista0);
		manejadorSucesos.suscribirImplicacion(accionReparar, lista1);
		manejadorSucesos.suscribirImplicacion(accionReparar, lista2);
		manejadorSucesos.suscribirImplicacion(accionReparar, lista3);
		manejadorSucesos.suscribirImplicacion(accionReparar, lista4);
		manejadorSucesos.suscribirImplicacion(accionReparar, lista5);
		manejadorSucesos.suscribirImplicacion(accionReparar, lista6);
		manejadorSucesos.suscribirImplicacion(accionAgregarGaseosa, lista7);
		manejadorSucesos.suscribirImplicacion(accionQuitarGaseosa, lista8);
		manejadorSucesos.suscribirImplicacion(accionQuitarGaseosa, lista9);
		manejadorSucesos.agregarSuceso(new Suceso("stockLleno"));
		manejadorSucesos.agregarSuceso(new Suceso("gaseosaFria"));
		assertEquals(19,accionQuitarGaseosa.getGaseosa().getStock());
		
		//Para el caso que se ejecute las acciones de las implicaciones de la
		//lista 8 y 9
		manejadorSucesos.agregarSuceso(new Suceso("stockLleno"));
		manejadorSucesos.agregarSuceso(new Suceso("gaseosaFria"));
		manejadorSucesos.agregarSuceso(new Suceso("maquinaRota"));
				
		assertEquals(17,accionQuitarGaseosa.getGaseosa().getStock());
		
		manejadorSucesos.agregarSuceso(new Suceso("stockLleno"));
		manejadorSucesos.agregarSuceso(new Suceso("gaseosaFria"));
		manejadorSucesos.agregarSuceso(new Suceso("maquinaRota"));
		manejadorSucesos.agregarSuceso(new Suceso("pocaGaseosa"));
		manejadorSucesos.agregarSuceso(new Suceso("maquinaDesconectada"));
		
		assertEquals(15,accionQuitarGaseosa.getGaseosa().getStock());
		assertEquals(11,accionAgregarGaseosa.getGaseosa().getStock());
					
	}
	
	public void testImplicacionAgregaSucesoEnEjecucionAntesDeSuscribirOtraQueEsperaEseSuceso(){		
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		//Esta accion agrega un suceso dentro del ejecutar
		AccionTirarGaseosa accionTirarGaseosa = new AccionTirarGaseosa(manejadorSucesos);
		AccionAgregarGaseosa accionAgregarGaseosa = new AccionAgregarGaseosa();
		accionAgregarGaseosa.setGaseosa(new Gaseosa("Fanta",10,false));
		assertEquals("Stock Inicial de Gaseosa",10,accionAgregarGaseosa.getGaseosa().getStock());
		List<Suceso> sucesos = GeneradorDeSuceso.obtenerSucesos("ponerFicha seleccionaCoca apretaBoton");			
		//Esta implacion agrega un suceso en la ejecucion de la accion
		manejadorSucesos.suscribirImplicacion(accionTirarGaseosa, sucesos);			
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("ponerFicha seleccionaCoca apretaBoton"));
		//Esta implicacion no ve el suceso anteriormente agregado al notificar	
		manejadorSucesos.suscribirImplicacion(accionAgregarGaseosa, new Suceso("pocaGaseosa"));
		manejadorSucesos.agregarSuceso(new Suceso("cualquiera"));
		//agrega
		assertEquals("Se mantuvo el stock",10, accionAgregarGaseosa.getGaseosa().getStock());
	}
	public void testImplicacionAgregaSucesoEnEjecucionDespuesDeSuscribirOtraQueEsperaEseSuceso(){		
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		//Esta accion agrega un suceso dentro del ejecutar
		AccionTirarGaseosa accionTirarGaseosa = new AccionTirarGaseosa(manejadorSucesos);
		AccionAgregarGaseosa accionAgregarGaseosa = new AccionAgregarGaseosa();
		accionAgregarGaseosa.setGaseosa(new Gaseosa("Fanta",10,false));
		assertEquals("Stock Inicial de Gaseosa",10,accionAgregarGaseosa.getGaseosa().getStock());		
		List<Suceso> sucesos = GeneradorDeSuceso.obtenerSucesos("ponerFicha seleccionaCoca apretaBoton");		
		//Esta implacion agrega un suceso en la ejecucion de la accion
		manejadorSucesos.suscribirImplicacion(accionTirarGaseosa, sucesos);		
		//Esta implicacion no ve el suceso anteriormente agregado al notificar
		manejadorSucesos.suscribirImplicacion(accionAgregarGaseosa, new Suceso("pocaGaseosa"));
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("ponerFicha seleccionaCoca apretaBoton"));
				
		assertEquals("Aumenta el stock",11, accionAgregarGaseosa.getGaseosa().getStock());
	}
	
	/*********************************************************************************
	 * Test sobre maximos sucesos que puede manejar la API 
	 *********************************************************************************/
	public void testTamanioMaximoSucesosOcurridosNoSobrepasadoConImplicacionNoValida(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		Bomba bomba = new Bomba();
		AccionPrenderBomba accionPrenderBomba = new AccionPrenderBomba();
		accionPrenderBomba.setBomba(bomba);
							
		manejadorSucesos.getConfiguracion().establecerEvaluadorContinuo();
		manejadorSucesos.establecerTamanioMaximoaDeSucesosOcurridos(3);
		List<Suceso> listaSucesos = GeneradorDeSuceso.obtenerSucesos("A B");
		manejadorSucesos.suscribirImplicacion(accionPrenderBomba, listaSucesos);
		//agrego el suceso y notifico
		bomba.setEncendida(false);
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("B K A"));
		assertEquals("Implicancia Invalida", bomba.isEncendida(),false);
		
	}
	public void testTamanioMaximoSucesosOcurridosNoSobrepasadoConImplicacionValida(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		Bomba bomba = new Bomba();
		AccionPrenderBomba accionPrenderBomba = new AccionPrenderBomba();
		accionPrenderBomba.setBomba(bomba);
				
		manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.getConfiguracion().establecerEvaluadorContinuo();
		manejadorSucesos.establecerTamanioMaximoaDeSucesosOcurridos(3);
		
	    List<Suceso> lista = GeneradorDeSuceso.obtenerSucesos("A B");
		manejadorSucesos.suscribirImplicacion(accionPrenderBomba, lista);
		//agrego el suceso y notifico
		bomba.setEncendida(false);
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("B A F"));
		assertEquals("Implicacion valida", bomba.isEncendida(),true);
		
	}
	
	public void testTamanioMaximoSucesosOcurridosSobrepasadoConImplicacionInvalida(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		Bomba bomba = new Bomba();
		AccionPrenderBomba accionPrenderBomba = new AccionPrenderBomba();
		accionPrenderBomba.setBomba(bomba);
				
		manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.getConfiguracion().establecerEvaluadorContinuo();
		manejadorSucesos.establecerTamanioMaximoaDeSucesosOcurridos(3);
		
	    List<Suceso> lista = GeneradorDeSuceso.obtenerSucesos("A B");
		manejadorSucesos.suscribirImplicacion(accionPrenderBomba, lista);
		//agrego el suceso y notifico
		bomba.setEncendida(false);
		// debe quedarse con los 3 ultimos
		manejadorSucesos.agregarSuceso(new Suceso("A"));
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("B A F F F"));
	
		assertEquals("Implicancia Invalida", bomba.isEncendida(),false);
		
	}
	public void testTamanioMaximoSucesosOcurridosSobrepasadoConImplicacionValida(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		Bomba bomba = new Bomba();
		AccionPrenderBomba accionPrenderBomba = new AccionPrenderBomba();
		accionPrenderBomba.setBomba(bomba);
		
		manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.getConfiguracion().establecerEvaluadorContinuo();
		manejadorSucesos.establecerTamanioMaximoaDeSucesosOcurridos(3);
		
		List<Suceso> lista = GeneradorDeSuceso.obtenerSucesos("A B");
		manejadorSucesos.suscribirImplicacion(accionPrenderBomba, lista);
		//agrego el suceso y notifico
		bomba.setEncendida(false);
		// debe quedarse con los 3 ultimos
			
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("G B"));
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("A S"));
		assertEquals("Implicancia Valida", bomba.isEncendida(),true);
		
	}
	
	public void testCancelacionPorDefecto(){
		
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		Bomba bomba = new Bomba();
		Suceso sucesoPocaAgua = new Suceso("pocaAgua");
		Suceso sucesoPresionAlta = new Suceso("presionAlta");
		AccionPrenderBomba accionPrenderBomba = new AccionPrenderBomba();
		accionPrenderBomba.setBomba(bomba);
		List<Suceso> sucesos = new ArrayList<Suceso>();
		
		manejadorSucesos.getConfiguracion().habilitarCancelador();
		
		//Si pocaAgua-------->PrenderBomBa
		sucesos.add(sucesoPocaAgua);
		manejadorSucesos.suscribirImplicacion(accionPrenderBomba, sucesos);			
		
		manejadorSucesos.agregarSuceso(sucesoPocaAgua);
		manejadorSucesos.agregarSuceso(sucesoPresionAlta);
		assertEquals(true, bomba.isEncendida());
		
		bomba.setEncendida(false);
		manejadorSucesos.agregarSuceso(sucesoPocaAgua);
		manejadorSucesos.agregarSuceso(new Suceso("muchaAgua", "pocaAgua"));
		assertEquals(true, bomba.isEncendida());
	}	
	
	public void testCancelacionPorDefecto2(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		Bomba bomba = new Bomba();
		Suceso sucesoPocaAgua = new Suceso("pocaAgua");
		Suceso sucesoPresionAlta = new Suceso("presionAlta");
		AccionPrenderBomba accionPrenderBomba = new AccionPrenderBomba();
		accionPrenderBomba.setBomba(bomba);
		List<Suceso> sucesos = new ArrayList<Suceso>();
		
		manejadorSucesos.getConfiguracion().habilitarCancelador();
		
		//Si pocaAgua-------->PrenderBomBa
		sucesos.add(sucesoPocaAgua);
		manejadorSucesos.suscribirImplicacion(accionPrenderBomba, sucesos);			

		bomba.setEncendida(false);
		manejadorSucesos.agregarSuceso(sucesoPocaAgua);
		manejadorSucesos.agregarSuceso(sucesoPresionAlta);
		manejadorSucesos.agregarSuceso(new Suceso("presionBaja", "presionAlta"));
		assertEquals(true, bomba.isEncendida());
	}
	
	public void testCancelacionPorDefecto3(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		Bomba bomba = new Bomba();
		AccionPrenderBomba accionPrenderBomba = new AccionPrenderBomba();
		accionPrenderBomba.setBomba(bomba);
		List<Suceso> sucesos = new ArrayList<Suceso>();
		
		manejadorSucesos.getConfiguracion().habilitarCancelador();
		
		//Si pocaAgua-------->PrenderBomBa
		sucesos.add(new Suceso("pocaAgua"));
		sucesos.add(new Suceso("muchaAgua"));
		manejadorSucesos.suscribirImplicacion(accionPrenderBomba, sucesos);
		
		List<Suceso> sucesosCanceladores = new ArrayList<Suceso>();
		sucesosCanceladores.add(new Suceso("pocaAgua"));
		sucesosCanceladores.add(new Suceso("muchaAgua", "pocaAgua"));
		
		bomba.setEncendida(false);
		manejadorSucesos.agregarSucesos(sucesosCanceladores);
		assertEquals(false, bomba.isEncendida());
	
		bomba.setEncendida(false);
		manejadorSucesos.agregarSuceso(new Suceso("pocaAgua"));
		manejadorSucesos.agregarSuceso(new Suceso("muchaAgua", "pocaAgua"));
		assertEquals(false, bomba.isEncendida());
		
	}
	/*
	public void testCancelacionPorDefecto4(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		Bomba bomba = new Bomba();
		Suceso sucesoPocaAgua = new Suceso("pocaAgua");
		Suceso sucesoPresionAlta = new Suceso("presionAlta");
		AccionPrenderBomba accionPrenderBomba = new AccionPrenderBomba();
		accionPrenderBomba.setBomba(bomba);
		List<Suceso> sucesos = new ArrayList<Suceso>();
		
		manejadorSucesos.getConfiguracion().habilitarCancelador();
		
		//Si pocaAgua-------->PrenderBomBa
		sucesos.add(sucesoPocaAgua);
		manejadorSucesos.suscribirImplicacion(accionPrenderBomba, sucesos);
		bomba.setEncendida(false);
		manejadorSucesos.agregarSuceso(sucesoPocaAgua);
		manejadorSucesos.agregarSuceso(sucesoPresionAlta);
		manejadorSucesos.agregarSucesos(sucesosCanceladores);
		assertEquals(false, bomba.isEncendida());
		
		sucesosCanceladores.remove(new Suceso("muchaAgua", "pocaAgua"));
		bomba.setEncendida(false);
		manejadorSucesos.agregarSuceso(sucesoPocaAgua);
		manejadorSucesos.agregarSuceso(sucesoPresionAlta);
		manejadorSucesos.agregarSucesos(sucesosCanceladores);
		assertEquals(true, bomba.isEncendida());
		
		sucesosCanceladores.add(new Suceso("muchaAgua", "pocaAgua"));
		bomba.setEncendida(false);
		manejadorSucesos.agregarSuceso(sucesoPocaAgua);
		manejadorSucesos.agregarSuceso(sucesoPresionAlta);
		manejadorSucesos.agregarSucesos(sucesosCanceladores);
		assertEquals(false, bomba.isEncendida());
	}
	
	public void testHabilitacionDeshabilitacionCancelador(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		Bomba bomba = new Bomba();
		Suceso sucesoPocaAgua = new Suceso("pocaAgua");
		Suceso sucesoPresionAlta = new Suceso("presionAlta");
		AccionPrenderBomba accionPrenderBomba = new AccionPrenderBomba();
		accionPrenderBomba.setBomba(bomba);
		List<Suceso> sucesos = new ArrayList<Suceso>();
		sucesos.add(sucesoPocaAgua);
		manejadorSucesos.suscribirImplicacion(accionPrenderBomba, sucesos);
		
		manejadorSucesos.getConfiguracion().habilitarCancelador();
		bomba.setEncendida(false);
		manejadorSucesos.agregarSuceso(sucesoPocaAgua);
		manejadorSucesos.agregarSuceso(sucesoPresionAlta);
		manejadorSucesos.agregarSuceso(new Suceso("muchaAgua", "pocaAgua"));
		assertEquals(false, bomba.isEncendida());
		
		manejadorSucesos.getConfiguracion().deshabilitarCancelador();
		bomba.setEncendida(false);
		manejadorSucesos.agregarSuceso(sucesoPocaAgua);
		manejadorSucesos.agregarSuceso(sucesoPresionAlta);
		manejadorSucesos.agregarSuceso(new Suceso("muchaAgua", "pocaAgua"));
		assertEquals(true, bomba.isEncendida());
	}
	*/
	
	
}