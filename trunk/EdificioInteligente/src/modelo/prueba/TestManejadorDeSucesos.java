package modelo.prueba;

import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;
import modelo.manejadorDeSucesos.*;
import modelo.cliente.*;

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
			 	 
	/**
	 * CASO 1: Implicacion con un solo suceso como antecedente.	 
	 */
	public void testNotificarUnicoSuceso(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		Bomba bomba = new Bomba();
		AccionPrenderBomba accionPrenderBomba = new AccionPrenderBomba();
		accionPrenderBomba.setBomba(bomba);
		manejadorSucesos.obtenerConfiguracion().establecerEvaluadorDiscontinuo();
		List<Suceso> lista = GeneradorDeSuceso.obtenerSucesos("A");
		bomba.setEncendida(false);
		manejadorSucesos.suscribirImplicacion(accionPrenderBomba, lista);
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("X Y Z A"));
		assertEquals(bomba.isEncendida(),true);	
	}
	
	/**
	 * CASO 2: Configuracion Discontinua sin Cancelaciones.	 
	 */
	public void testSucesosContinuosOrdenadosConfiguracionDiscontinuaNoCancelaciones(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		Bomba bomba = new Bomba();
		AccionPrenderBomba accionPrenderBomba = new AccionPrenderBomba();
		accionPrenderBomba.setBomba(bomba);
	    manejadorSucesos.obtenerConfiguracion().establecerEvaluadorDiscontinuo();
		List<Suceso> lista = GeneradorDeSuceso.obtenerSucesos("A B");
		manejadorSucesos.suscribirImplicacion(accionPrenderBomba, lista);
		bomba.setEncendida(false);
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("X Y Z A B"));
		assertEquals(bomba.isEncendida(),true);		
	}
	
	public void testSucesosContinuosDesordenadosConfiguracionDiscontinuaNoCancelaciones(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		Bomba bomba = new Bomba();
		AccionPrenderBomba accionPrenderBomba = new AccionPrenderBomba();
		accionPrenderBomba.setBomba(bomba);
	    manejadorSucesos.obtenerConfiguracion().establecerEvaluadorDiscontinuo();
		List<Suceso> lista = GeneradorDeSuceso.obtenerSucesos("A B");
		manejadorSucesos.suscribirImplicacion(accionPrenderBomba, lista);
		bomba.setEncendida(false);
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("X Y Z B A"));
		assertEquals(bomba.isEncendida(),true);		
	}
	
	public void testSucesosDiscontinuosOrdenadosConfiguracionDiscontinuaNoCancelaciones(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		Bomba bomba = new Bomba();
		AccionPrenderBomba accionPrenderBomba = new AccionPrenderBomba();
		accionPrenderBomba.setBomba(bomba);
		manejadorSucesos.obtenerConfiguracion().establecerEvaluadorDiscontinuo();
		List<Suceso> lista = GeneradorDeSuceso.obtenerSucesos("A B");
		manejadorSucesos.suscribirImplicacion(accionPrenderBomba, lista);
		bomba.setEncendida(false);
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("X Y Z A K;A B"));
		assertEquals(bomba.isEncendida(),true);		
	}
	
	public void testSucesosDiscontinuosDesordenadosConfiguracionDiscontinuaNoCancelaciones(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		Bomba bomba = new Bomba();
		AccionPrenderBomba accionPrenderBomba = new AccionPrenderBomba();
		accionPrenderBomba.setBomba(bomba);
	    manejadorSucesos.obtenerConfiguracion().establecerEvaluadorDiscontinuo();
		List<Suceso> lista = GeneradorDeSuceso.obtenerSucesos("A B");
		manejadorSucesos.suscribirImplicacion(accionPrenderBomba, lista);
		bomba.setEncendida(false);
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("X Y Z B K;A A"));
		assertEquals(bomba.isEncendida(),true);		
	}
	
	/**
	 * CASO 3: Configuracion Continua sin Cancelaciones.	 
	 */
	public void testSucesosContinuosOrdenadosConfiguracionContinuaNoCancelaciones(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		Bomba bomba = new Bomba();
		AccionPrenderBomba accionPrenderBomba = new AccionPrenderBomba();
		accionPrenderBomba.setBomba(bomba);
	    manejadorSucesos.obtenerConfiguracion().establecerEvaluadorContinuo();
	    List<Suceso> lista = GeneradorDeSuceso.obtenerSucesos("A B");
		manejadorSucesos.suscribirImplicacion(accionPrenderBomba, lista);
		bomba.setEncendida(false);
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("X Y Z A B"));
		assertEquals(bomba.isEncendida(),true);		
	}
	
	public void testSucesosContinuosDesordenadosConfiguracionContinuaNoCancelaciones(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		Bomba bomba = new Bomba();
		AccionPrenderBomba accionPrenderBomba = new AccionPrenderBomba();
		accionPrenderBomba.setBomba(bomba);
	    manejadorSucesos.obtenerConfiguracion().establecerEvaluadorContinuo();
	    List<Suceso> lista = GeneradorDeSuceso.obtenerSucesos("A B");
		manejadorSucesos.suscribirImplicacion(accionPrenderBomba, lista);
		bomba.setEncendida(false);
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("X Y Z B A"));
		assertEquals(bomba.isEncendida(),true);		
	}
	
	public void testSucesosDiscontinuosOrdenadosConfiguracionContinuaNoCancelaciones(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		Bomba bomba = new Bomba();
		AccionPrenderBomba accionPrenderBomba = new AccionPrenderBomba();
		accionPrenderBomba.setBomba(bomba);
	    manejadorSucesos.obtenerConfiguracion().establecerEvaluadorContinuo();
	    List<Suceso> lista = GeneradorDeSuceso.obtenerSucesos("A B");
		manejadorSucesos.suscribirImplicacion(accionPrenderBomba, lista);
		bomba.setEncendida(false);
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("X Y Z A K;A B"));
		assertEquals(bomba.isEncendida(),false);		
	}
	
	public void testSucesosDisontinuosDesordenadosConfiguracionContinuaNoCancelaciones(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		Bomba bomba = new Bomba();
		AccionPrenderBomba accionPrenderBomba = new AccionPrenderBomba();
		accionPrenderBomba.setBomba(bomba);
	    manejadorSucesos.obtenerConfiguracion().establecerEvaluadorContinuo();
	    List<Suceso> lista = GeneradorDeSuceso.obtenerSucesos("A B");
		manejadorSucesos.suscribirImplicacion(accionPrenderBomba, lista);
		bomba.setEncendida(false);
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("X Y Z B K;A A"));
		assertEquals(bomba.isEncendida(),false);		
	}
	
	/**
	 * CASO 4: Configuracion Secuencia Discontinua sin Cancelaciones.	 
	 */
	public void testSucesosContinuosOrdenadosConfiguracionSecuenciaDiscontinuaNoCancelaciones(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		Bomba bomba = new Bomba();
		AccionPrenderBomba accionPrenderBomba = new AccionPrenderBomba();
		accionPrenderBomba.setBomba(bomba);
		manejadorSucesos.obtenerConfiguracion().establecerEvaluadorSecuenciaDiscontinua();
	    List<Suceso> lista = GeneradorDeSuceso.obtenerSucesos("A B");
		manejadorSucesos.suscribirImplicacion(accionPrenderBomba, lista);
		bomba.setEncendida(false);
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("X Y Z A B"));
		assertEquals(bomba.isEncendida(),true);		
	}
	
	public void testSucesosContinuosDesordenadosConfiguracionSecuenciaDiscontinuaNoCancelaciones(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		Bomba bomba = new Bomba();
		AccionPrenderBomba accionPrenderBomba = new AccionPrenderBomba();
		accionPrenderBomba.setBomba(bomba);
		manejadorSucesos.obtenerConfiguracion().establecerEvaluadorSecuenciaDiscontinua();
		List<Suceso> lista = GeneradorDeSuceso.obtenerSucesos("A B");
		manejadorSucesos.suscribirImplicacion(accionPrenderBomba, lista);
		bomba.setEncendida(false);
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("X Y Z B A"));
		assertEquals(bomba.isEncendida(),false);		
	}
		
	public void testSucesosDiscontinuosOrdenadosConfiguracionSecuenciaDiscontinuaNoCancelaciones(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		Bomba bomba = new Bomba();
		AccionPrenderBomba accionPrenderBomba = new AccionPrenderBomba();
		accionPrenderBomba.setBomba(bomba);
		manejadorSucesos.obtenerConfiguracion().establecerEvaluadorSecuenciaDiscontinua();
		List<Suceso> lista = GeneradorDeSuceso.obtenerSucesos("A B");
		manejadorSucesos.suscribirImplicacion(accionPrenderBomba, lista);
		bomba.setEncendida(false);
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("X Y Z A K;A B"));
		assertEquals(bomba.isEncendida(),true);		
	}
	
	public void testSucesosDiscontinuosDesordenadosConfiguracionSecuenciaDiscontinuaNoCancelaciones(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		Bomba bomba = new Bomba();
		AccionPrenderBomba accionPrenderBomba = new AccionPrenderBomba();
		accionPrenderBomba.setBomba(bomba);
		manejadorSucesos.obtenerConfiguracion().establecerEvaluadorSecuenciaDiscontinua();
		List<Suceso> lista = GeneradorDeSuceso.obtenerSucesos("A B");
		manejadorSucesos.suscribirImplicacion(accionPrenderBomba, lista);
		bomba.setEncendida(false);
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("X Y Z B K;A A"));
		assertEquals(bomba.isEncendida(),false);		
	}
	
	/**
	 * CASO 5: Configuracion Secuencia Continua sin Cancelaciones.	 
	 */
	public void testSucesosContinuosOrdenadosConfiguracionSecuenciaContinuaNoCancelaciones(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		Bomba bomba = new Bomba();
		AccionPrenderBomba accionPrenderBomba = new AccionPrenderBomba();
		accionPrenderBomba.setBomba(bomba);
		manejadorSucesos.obtenerConfiguracion().establecerEvaluadorSecuenciaContinua();
		List<Suceso> lista = GeneradorDeSuceso.obtenerSucesos("A B");
		manejadorSucesos.suscribirImplicacion(accionPrenderBomba, lista);
		bomba.setEncendida(false);
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("X Y Z A B"));
		assertEquals(bomba.isEncendida(),true);		
	}
	public void testSucesosContinuosDesordenadosConfiguracionSecuenciaContinuaNoCancelaciones(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		Bomba bomba = new Bomba();
		AccionPrenderBomba accionPrenderBomba = new AccionPrenderBomba();
		accionPrenderBomba.setBomba(bomba);
		manejadorSucesos.obtenerConfiguracion().establecerEvaluadorSecuenciaContinua();
		List<Suceso> lista = GeneradorDeSuceso.obtenerSucesos("A B");
		manejadorSucesos.suscribirImplicacion(accionPrenderBomba, lista);
		bomba.setEncendida(false);
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("X Y Z B A"));
		assertEquals(bomba.isEncendida(),false);		
	}
	
	public void testSucesosDiscontinuosOrdenadosConfiguracionSecuenciaContinuaNoCancelaciones(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		Bomba bomba = new Bomba();
		AccionPrenderBomba accionPrenderBomba = new AccionPrenderBomba();
		accionPrenderBomba.setBomba(bomba);
		manejadorSucesos.obtenerConfiguracion().establecerEvaluadorSecuenciaContinua();
		List<Suceso> lista = GeneradorDeSuceso.obtenerSucesos("A B");
		manejadorSucesos.suscribirImplicacion(accionPrenderBomba, lista);
		bomba.setEncendida(false);
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("X Y Z A K;A B"));
		assertEquals(bomba.isEncendida(),false);		
	}
		
	public void testSucesosDiscontinuosDesordenadosConfiguracionSecuenciaContinuaNoCancelaciones(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		Bomba bomba = new Bomba();
		AccionPrenderBomba accionPrenderBomba = new AccionPrenderBomba();
		accionPrenderBomba.setBomba(bomba);
		manejadorSucesos.obtenerConfiguracion().establecerEvaluadorSecuenciaContinua();
		List<Suceso> lista = GeneradorDeSuceso.obtenerSucesos("A B");
		manejadorSucesos.suscribirImplicacion(accionPrenderBomba, lista);
		bomba.setEncendida(false);
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("X Y Z B K;A A"));
		assertEquals(bomba.isEncendida(),false);
	}
	
	/**
	 * CASO 6: Configuracion Discontinua con Cancelaciones.
	 */
	public void testSucesosContinuosOrdenadosConfiguracionDiscontinuaCancelaciones(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.obtenerConfiguracion().establecerEvaluadorDiscontinuo();
		manejadorSucesos.obtenerConfiguracion().habilitarCancelador();
		Bomba bomba = new Bomba();
		AccionPrenderBomba accionPrenderBomba = new AccionPrenderBomba();
		accionPrenderBomba.setBomba(bomba);
		List<Suceso> lista = GeneradorDeSuceso.obtenerSucesos("A B");
		manejadorSucesos.suscribirImplicacion(accionPrenderBomba, lista);
		bomba.setEncendida(false);
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("X Y Z A B"));
		assertEquals(bomba.isEncendida(),true);		
	}
	
	public void testSucesosContinuosDesordenadosConfiguracionDiscontinuaCancelaciones(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.obtenerConfiguracion().establecerEvaluadorDiscontinuo();
		manejadorSucesos.obtenerConfiguracion().habilitarCancelador();
		Bomba bomba = new Bomba();
		AccionPrenderBomba accionPrenderBomba = new AccionPrenderBomba();
		accionPrenderBomba.setBomba(bomba);
		List<Suceso> lista = GeneradorDeSuceso.obtenerSucesos("A B");
		manejadorSucesos.suscribirImplicacion(accionPrenderBomba, lista);
		bomba.setEncendida(false);
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("X Y Z B A"));
		assertEquals(bomba.isEncendida(),true);		
	}
			
	public void testSucesosDiscontinuosOrdenadosConfiguracionDiscontinuaCancelaciones(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.obtenerConfiguracion().establecerEvaluadorDiscontinuo();
		manejadorSucesos.obtenerConfiguracion().habilitarCancelador();
		Bomba bomba = new Bomba();
		AccionPrenderBomba accionPrenderBomba = new AccionPrenderBomba();
		accionPrenderBomba.setBomba(bomba);
		List<Suceso> lista = GeneradorDeSuceso.obtenerSucesos("A B");
		manejadorSucesos.suscribirImplicacion(accionPrenderBomba, lista);
		bomba.setEncendida(false);
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("X Y Z A K;A B"));
		assertEquals(bomba.isEncendida(),false);		
	}
	
	public void testSucesosDiscontinuosDesordenadosConfiguracionDiscontinuaCancelaciones(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.obtenerConfiguracion().establecerEvaluadorDiscontinuo();
		manejadorSucesos.obtenerConfiguracion().habilitarCancelador();
		Bomba bomba = new Bomba();
		AccionPrenderBomba accionPrenderBomba = new AccionPrenderBomba();
		accionPrenderBomba.setBomba(bomba);
		List<Suceso> lista = GeneradorDeSuceso.obtenerSucesos("A B");
		manejadorSucesos.suscribirImplicacion(accionPrenderBomba, lista);
		bomba.setEncendida(false);
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("X Y Z B K;A A"));
		assertEquals(bomba.isEncendida(),true);		
	}
	
	/**
	 * CASO 7: Configuracion Continua con Cancelaciones.
	 */
	public void testSucesosContinuosOrdenadosConfiguracionContinuaCancelaciones(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.obtenerConfiguracion().establecerEvaluadorContinuo();
		manejadorSucesos.obtenerConfiguracion().habilitarCancelador();
		Bomba bomba = new Bomba();
		AccionPrenderBomba accionPrenderBomba = new AccionPrenderBomba();
		accionPrenderBomba.setBomba(bomba);
		List<Suceso> lista = GeneradorDeSuceso.obtenerSucesos("A B");
		manejadorSucesos.suscribirImplicacion(accionPrenderBomba, lista);
		bomba.setEncendida(false);
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("X Y Z A B"));
		assertEquals(bomba.isEncendida(),true);		
	}
	
	public void testSucesosDiscontinuosOrdenadosConfiguracionContinuaCancelaciones(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.obtenerConfiguracion().establecerEvaluadorContinuo();
		manejadorSucesos.obtenerConfiguracion().habilitarCancelador();
		Bomba bomba = new Bomba();
		AccionPrenderBomba accionPrenderBomba = new AccionPrenderBomba();
		accionPrenderBomba.setBomba(bomba);
		List<Suceso> lista = GeneradorDeSuceso.obtenerSucesos("A B");
		manejadorSucesos.suscribirImplicacion(accionPrenderBomba, lista);
		bomba.setEncendida(false);
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("X Y Z A F B"));
		assertEquals(bomba.isEncendida(),false);		
	}
			
	public void testSucesosContinuosDesordenadosConfiguracionContinuaCancelaciones(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.obtenerConfiguracion().establecerEvaluadorContinuo();
		manejadorSucesos.obtenerConfiguracion().habilitarCancelador();
		Bomba bomba = new Bomba();
		AccionPrenderBomba accionPrenderBomba = new AccionPrenderBomba();
		accionPrenderBomba.setBomba(bomba);
		List<Suceso> lista = GeneradorDeSuceso.obtenerSucesos("A B");
		manejadorSucesos.suscribirImplicacion(accionPrenderBomba, lista);
		bomba.setEncendida(false);
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("X Y Z B A"));
		assertEquals(bomba.isEncendida(),true);		
	}
	
	public void testSucesosDiscontinuosConCanceladorOrdenadosConfiguracionContinuaCancelaciones(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.obtenerConfiguracion().establecerEvaluadorContinuo();
		manejadorSucesos.obtenerConfiguracion().habilitarCancelador();
		Bomba bomba = new Bomba();
		AccionPrenderBomba accionPrenderBomba = new AccionPrenderBomba();
		accionPrenderBomba.setBomba(bomba);
		List<Suceso> lista = GeneradorDeSuceso.obtenerSucesos("A B");
		manejadorSucesos.suscribirImplicacion(accionPrenderBomba, lista);
		bomba.setEncendida(false);
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("X Y Z A K;A B"));
		assertEquals(bomba.isEncendida(),false);		
	}
	
	public void testSucesosDiscontinuosConCanceladorDesordenadosConfiguracionContinuaCancelaciones(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.obtenerConfiguracion().establecerEvaluadorContinuo();
		manejadorSucesos.obtenerConfiguracion().habilitarCancelador();
		Bomba bomba = new Bomba();
		AccionPrenderBomba accionPrenderBomba = new AccionPrenderBomba();
		accionPrenderBomba.setBomba(bomba);
		List<Suceso> lista = GeneradorDeSuceso.obtenerSucesos("A B");
		manejadorSucesos.suscribirImplicacion(accionPrenderBomba, lista);
		bomba.setEncendida(false);
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("X Y Z B K;A A"));
		assertEquals(bomba.isEncendida(),false);		
	}
	
	/**
	 * CASO 8: Configuracion Secuencia Discontinua con Cancelaciones.
	 */
	public void testSucesosContinuosOrdenadosConfiguracionSecuenciaDiscontinuaCancelaciones(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.obtenerConfiguracion().establecerEvaluadorSecuenciaDiscontinua();
		manejadorSucesos.obtenerConfiguracion().habilitarCancelador();
		Bomba bomba = new Bomba();
		AccionPrenderBomba accionPrenderBomba = new AccionPrenderBomba();
		accionPrenderBomba.setBomba(bomba);
		List<Suceso> lista = GeneradorDeSuceso.obtenerSucesos("A B");
		manejadorSucesos.suscribirImplicacion(accionPrenderBomba, lista);
		bomba.setEncendida(false);
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("X Y Z A B"));
		assertEquals(bomba.isEncendida(),true);		
	}
	
	public void testSucesosDiscontinuosOrdenadosConfiguracionSecuenciaDiscontinuaCancelaciones(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.obtenerConfiguracion().establecerEvaluadorSecuenciaDiscontinua();
		manejadorSucesos.obtenerConfiguracion().habilitarCancelador();
		Bomba bomba = new Bomba();
		AccionPrenderBomba accionPrenderBomba = new AccionPrenderBomba();
		accionPrenderBomba.setBomba(bomba);
		List<Suceso> lista = GeneradorDeSuceso.obtenerSucesos("A B");
		manejadorSucesos.suscribirImplicacion(accionPrenderBomba, lista);
		//agrego el suceso y notifico
		bomba.setEncendida(false);
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("X Y Z A F B"));
		assertEquals(bomba.isEncendida(),true);		
	}
			
	public void testSucesosContinuosDesordenadosConfiguracionSecuenciaDiscontinuaCancelaciones(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.obtenerConfiguracion().establecerEvaluadorSecuenciaDiscontinua();
		manejadorSucesos.obtenerConfiguracion().habilitarCancelador();
		Bomba bomba = new Bomba();
		AccionPrenderBomba accionPrenderBomba = new AccionPrenderBomba();
		accionPrenderBomba.setBomba(bomba);
		List<Suceso> lista = GeneradorDeSuceso.obtenerSucesos("A B");
		manejadorSucesos.suscribirImplicacion(accionPrenderBomba, lista);
		bomba.setEncendida(false);
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("X Y Z B A"));
		assertEquals(bomba.isEncendida(),false);		
	}
	
	public void testSucesosDiscontinuosConCanceladorOrdenadosConfiguracionSecuenciaDiscontinuaCancelaciones(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.obtenerConfiguracion().establecerEvaluadorSecuenciaDiscontinua();
		manejadorSucesos.obtenerConfiguracion().habilitarCancelador();
		Bomba bomba = new Bomba();
		AccionPrenderBomba accionPrenderBomba = new AccionPrenderBomba();
		accionPrenderBomba.setBomba(bomba);
		List<Suceso> lista = GeneradorDeSuceso.obtenerSucesos("A B");
		manejadorSucesos.suscribirImplicacion(accionPrenderBomba, lista);
		bomba.setEncendida(false);
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("X Y Z A K;A B"));
		assertEquals(bomba.isEncendida(),false);		
	}
	
	public void testSucesosDiscontinuosConCanceladorDesordenadosConfiguracionSecuenciaDiscontinuaCancelaciones(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.obtenerConfiguracion().establecerEvaluadorSecuenciaDiscontinua();
		manejadorSucesos.obtenerConfiguracion().habilitarCancelador();
		Bomba bomba = new Bomba();
		AccionPrenderBomba accionPrenderBomba = new AccionPrenderBomba();
		accionPrenderBomba.setBomba(bomba);
		List<Suceso> lista = GeneradorDeSuceso.obtenerSucesos("A B");
		manejadorSucesos.suscribirImplicacion(accionPrenderBomba, lista);
		bomba.setEncendida(false);
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("X Y Z B K;A A"));
		assertEquals(bomba.isEncendida(),false);		
	}
	
	/**
	 * CASO 9: Configuracion Secuencia Continua con Cancelaciones.
	 */
	public void testSucesosContinuosOrdenadosConfiguracionSecuenciaContinuaCancelaciones(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.obtenerConfiguracion().establecerEvaluadorSecuenciaContinua();
		manejadorSucesos.obtenerConfiguracion().habilitarCancelador();
		Bomba bomba = new Bomba();
		AccionPrenderBomba accionPrenderBomba = new AccionPrenderBomba();
		accionPrenderBomba.setBomba(bomba);
		List<Suceso> lista = GeneradorDeSuceso.obtenerSucesos("A B");
		manejadorSucesos.suscribirImplicacion(accionPrenderBomba, lista);
		bomba.setEncendida(false);
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("X Y Z A B"));
		assertEquals(bomba.isEncendida(),true);		
	}
	
	public void testSucesosContinuosDesordenadosConfiguracionSecuenciaContinuaCancelaciones(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.obtenerConfiguracion().establecerEvaluadorSecuenciaContinua();
		manejadorSucesos.obtenerConfiguracion().habilitarCancelador();
		Bomba bomba = new Bomba();
		AccionPrenderBomba accionPrenderBomba = new AccionPrenderBomba();
		accionPrenderBomba.setBomba(bomba);
		List<Suceso> lista = GeneradorDeSuceso.obtenerSucesos("A B");
		manejadorSucesos.suscribirImplicacion(accionPrenderBomba, lista);
		bomba.setEncendida(false);
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("X Y Z B A"));
		assertEquals(bomba.isEncendida(),false);		
	}
			
	public void testSucesosDiscontinuosOrdenadosConfiguracionSecuenciaContinuaCancelaciones(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.obtenerConfiguracion().establecerEvaluadorSecuenciaContinua();
		manejadorSucesos.obtenerConfiguracion().habilitarCancelador();
		Bomba bomba = new Bomba();
		AccionPrenderBomba accionPrenderBomba = new AccionPrenderBomba();
		accionPrenderBomba.setBomba(bomba);
		List<Suceso> lista = GeneradorDeSuceso.obtenerSucesos("A B");
		manejadorSucesos.suscribirImplicacion(accionPrenderBomba, lista);
		bomba.setEncendida(false);
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("X Y Z A K;A B"));
		assertEquals(bomba.isEncendida(),false);		
	}
	
	public void testSucesosDiscontinuosDesordenadosConfiguracionSecuenciaContinuaCancelaciones(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.obtenerConfiguracion().establecerEvaluadorSecuenciaContinua();
		manejadorSucesos.obtenerConfiguracion().habilitarCancelador();
		Bomba bomba = new Bomba();
		AccionPrenderBomba accionPrenderBomba = new AccionPrenderBomba();
		accionPrenderBomba.setBomba(bomba);
		List<Suceso> lista = GeneradorDeSuceso.obtenerSucesos("A B");
		manejadorSucesos.suscribirImplicacion(accionPrenderBomba, lista);
		bomba.setEncendida(false);
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("X Y Z B K;A A"));
		assertEquals(bomba.isEncendida(),false);		
	}
		
	/**
	 * Test de robustez. 	
	 */
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
		assertEquals(false, bomba.isEncendida());
	
		//ignora el suceso nulo y notifica los sucesos
		manejadorSucesos.agregarSuceso(new Suceso("pocaAgua"));
		manejadorSucesos.agregarSuceso(new Suceso("presionAlta"));
		manejadorSucesos.agregarSuceso(null);
		assertEquals(true, bomba.isEncendida());
		
	}
	public void testSuscribirImplicacionConListaNula(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		Bomba bomba = new Bomba();
		AccionPrenderBomba accionPrenderBomba = new AccionPrenderBomba();
		accionPrenderBomba.setBomba(bomba);
		List<Suceso> sucesos = null;
		manejadorSucesos.suscribirImplicacion(accionPrenderBomba, sucesos);
		assertEquals(false, bomba.isEncendida());
	
		//ignora el suceso nulo y notifica los sucesos
		manejadorSucesos.agregarSuceso(new Suceso("pocaAgua"));
		manejadorSucesos.agregarSuceso(new Suceso("presionAlta"));
		assertEquals(false, bomba.isEncendida());
		
	}
	public void testSuscribirImplicacionConSucesoNulo(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		Bomba bomba = new Bomba();
		AccionPrenderBomba accionPrenderBomba = new AccionPrenderBomba();
		accionPrenderBomba.setBomba(bomba);
		Suceso sucesoNulo =null;
		manejadorSucesos.suscribirImplicacion(accionPrenderBomba, sucesoNulo);
		assertEquals(false, bomba.isEncendida());
		
		//ignora el suceso nulo y notifica los sucesos
		manejadorSucesos.agregarSuceso(new Suceso("pocaAgua"));
		manejadorSucesos.agregarSuceso(new Suceso("presionAlta"));
		assertEquals(false, bomba.isEncendida());
	}
	
	public void testAgregarListaDeSucesosNulos(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		Bomba bomba = new Bomba();
		AccionPrenderBomba accionPrenderBomba = new AccionPrenderBomba();
		accionPrenderBomba.setBomba(bomba);
		List<Suceso> sucesos = GeneradorDeSuceso.obtenerSucesos("pocaAgua presionAlta");
		manejadorSucesos.suscribirImplicacion(accionPrenderBomba, sucesos);
		assertEquals(false, bomba.isEncendida());	
		
		manejadorSucesos.agregarSucesos(null);
		assertEquals(false, bomba.isEncendida());		
	}
 	
	/**
	 * En esta test se prueba el funcionamiento del manejador de Sucesos
	 * con 10 implicaciones y se evaluan los resultados de las acciones.
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
	
	/**
	 * Test de suscripcion de sucesos en ejecucion.
	 */
	public void testImplicacionAgregaSucesoEnEjecucionAntesDeSuscribirOtraQueEsperaEseSuceso(){		
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		//Esta accion agrega un suceso dentro del ejecutar
		AccionTirarGaseosa accionTirarGaseosa = new AccionTirarGaseosa(manejadorSucesos);
		AccionAgregarGaseosa accionAgregarGaseosa = new AccionAgregarGaseosa();
		accionAgregarGaseosa.setGaseosa(new Gaseosa("Fanta",10,false));
		assertEquals(10,accionAgregarGaseosa.getGaseosa().getStock());
		List<Suceso> sucesos = GeneradorDeSuceso.obtenerSucesos("ponerFicha seleccionaCoca apretaBoton");			
		//Esta implacion agrega un suceso en la ejecucion de la accion
		manejadorSucesos.suscribirImplicacion(accionTirarGaseosa, sucesos);			
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("ponerFicha seleccionaCoca apretaBoton"));
		//Esta implicacion no ve el suceso anteriormente agregado al notificar	
		manejadorSucesos.suscribirImplicacion(accionAgregarGaseosa, new Suceso("pocaGaseosa"));
		manejadorSucesos.agregarSuceso(new Suceso("cualquiera"));
		//agrega
		assertEquals(10, accionAgregarGaseosa.getGaseosa().getStock());
	}
	
	public void testImplicacionAgregaSucesoEnEjecucionDespuesDeSuscribirOtraQueEsperaEseSuceso(){		
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		//Esta accion agrega un suceso dentro del ejecutar
		AccionTirarGaseosa accionTirarGaseosa = new AccionTirarGaseosa(manejadorSucesos);
		AccionAgregarGaseosa accionAgregarGaseosa = new AccionAgregarGaseosa();
		accionAgregarGaseosa.setGaseosa(new Gaseosa("Fanta",10,false));
		assertEquals(10,accionAgregarGaseosa.getGaseosa().getStock());		
		List<Suceso> sucesos = GeneradorDeSuceso.obtenerSucesos("ponerFicha seleccionaCoca apretaBoton");		
		//Esta implacion agrega un suceso en la ejecucion de la accion
		manejadorSucesos.suscribirImplicacion(accionTirarGaseosa, sucesos);		
		//Esta implicacion no ve el suceso anteriormente agregado al notificar
		manejadorSucesos.suscribirImplicacion(accionAgregarGaseosa, new Suceso("pocaGaseosa"));
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("ponerFicha seleccionaCoca apretaBoton"));
				
		assertEquals(11, accionAgregarGaseosa.getGaseosa().getStock());
	}
	
	/**
	 * Test sobre maximos sucesos que puede manejar la API 
	 */
	public void testTamanioMaximoSucesosOcurridosNoSobrepasadoConImplicacionNoValida(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		Bomba bomba = new Bomba();
		AccionPrenderBomba accionPrenderBomba = new AccionPrenderBomba();
		accionPrenderBomba.setBomba(bomba);
		manejadorSucesos.obtenerConfiguracion().establecerEvaluadorContinuo();
		manejadorSucesos.obtenerConfiguracion().establecerTamanioMaximoDeSucesosOcurridos(3);
		List<Suceso> listaSucesos = GeneradorDeSuceso.obtenerSucesos("A B");
		manejadorSucesos.suscribirImplicacion(accionPrenderBomba, listaSucesos);
		bomba.setEncendida(false);
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("B K A"));
		assertEquals(bomba.isEncendida(),false);
	}
	
	public void testTamanioMaximoSucesosOcurridosNoSobrepasadoConImplicacionValida(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		Bomba bomba = new Bomba();
		AccionPrenderBomba accionPrenderBomba = new AccionPrenderBomba();
		accionPrenderBomba.setBomba(bomba);
		manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.obtenerConfiguracion().establecerEvaluadorContinuo();
		manejadorSucesos.obtenerConfiguracion().establecerTamanioMaximoDeSucesosOcurridos(3);
		List<Suceso> lista = GeneradorDeSuceso.obtenerSucesos("A B");
		manejadorSucesos.suscribirImplicacion(accionPrenderBomba, lista);
		bomba.setEncendida(false);
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("B A F"));
		assertEquals(bomba.isEncendida(),true);
	}
	
	public void testTamanioMaximoSucesosOcurridosSobrepasadoConImplicacionInvalida(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		Bomba bomba = new Bomba();
		AccionPrenderBomba accionPrenderBomba = new AccionPrenderBomba();
		accionPrenderBomba.setBomba(bomba);
		manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.obtenerConfiguracion().establecerEvaluadorContinuo();
		manejadorSucesos.obtenerConfiguracion().establecerTamanioMaximoDeSucesosOcurridos(3);
		
	    List<Suceso> lista = GeneradorDeSuceso.obtenerSucesos("A B");
		manejadorSucesos.suscribirImplicacion(accionPrenderBomba, lista);
		bomba.setEncendida(false);
		manejadorSucesos.agregarSuceso(new Suceso("A"));
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("F F F B"));
		assertEquals(bomba.isEncendida(),false);
	}
	
	public void testTamanioMaximoSucesosOcurridosSobrepasadoConImplicacionValida(){
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		Bomba bomba = new Bomba();
		AccionPrenderBomba accionPrenderBomba = new AccionPrenderBomba();
		accionPrenderBomba.setBomba(bomba);
		
		manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.obtenerConfiguracion().establecerEvaluadorContinuo();
		manejadorSucesos.obtenerConfiguracion().establecerTamanioMaximoDeSucesosOcurridos(3);
		
		List<Suceso> lista = GeneradorDeSuceso.obtenerSucesos("A B");
		manejadorSucesos.suscribirImplicacion(accionPrenderBomba, lista);
		bomba.setEncendida(false);
				
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("G B"));
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("A S"));
		assertEquals(bomba.isEncendida(),true);
	}
}
