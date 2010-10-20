package prueba;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import junit.framework.TestCase;
import source.ManejadorDeSucesos;
import source.Suceso;
import cliente.AccionAgregarGaseosa;
import cliente.AccionApagarBomba;
import cliente.AccionCliente;
import cliente.AccionPrenderBomba;
import cliente.AccionQuitarGaseosa;
import cliente.AccionRepararMaquinaGasesosa;
import cliente.AccionTirarGaseosa;
import cliente.Bomba;
import cliente.Gaseosa;
import cliente.GeneradorDeSuceso;
import cliente.MaquinaGaseosa;
import cliente.Tanque;

public class TestManejadorDeSucesos extends  TestCase {

	private ManejadorDeSucesos manejadorSucesos;
	
	private Tanque tanque;
	
	private Bomba bomba;
	
	private Suceso sucesoPocaAgua;
	
	private Suceso sucesoPresionAlta;
	
	private List<Suceso> sucesos;	
	
	private AccionPrenderBomba accionPrenderBomba;
	
	private AccionApagarBomba accionApagarBomba;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();		
		this.manejadorSucesos = new ManejadorDeSucesos();
		this.manejadorSucesos.establecerConfiguracionDiscontinuo();
		this.bomba = new Bomba();
		this.tanque = new Tanque();
		this.sucesoPocaAgua = new Suceso("pocaAgua");
		this.sucesoPresionAlta = new Suceso("presionAlta");
		this.sucesos = new LinkedList<Suceso>();
		this.accionApagarBomba = new AccionApagarBomba();
		
		this.accionApagarBomba.setBomba(bomba);
		this.accionApagarBomba.setTanque(tanque);
		
		this.accionPrenderBomba = new AccionPrenderBomba();
		this.accionPrenderBomba.setBomba(bomba);
		this.manejadorSucesos.borrarImplicaciones();
	}
	
	public void testSuscribirImplicacionYNotificarSuceso(){
		//si pocaAgua ---> accionPrenderBomba
		this.manejadorSucesos.suscribirImplicacion(accionPrenderBomba, sucesoPocaAgua);
		assertEquals(false, this.bomba.isEncendida());
		// notificacion sucesoPocaAgua
		this.manejadorSucesos.agregarSuceso(sucesoPocaAgua);
		assertEquals(true, this.bomba.isEncendida());
	}
	
	public void testSuscribirImplicacionYNotificarSucesoInvalido(){
		//si pocaAgua ---> accionPrenderBomba
		this.manejadorSucesos.suscribirImplicacion(accionPrenderBomba, sucesoPocaAgua);
		assertEquals(false, this.bomba.isEncendida());
		//notificacion sucesoPresionAlta
		this.manejadorSucesos.agregarSuceso(sucesoPresionAlta);
		assertEquals(false, this.bomba.isEncendida());
	}
	
		
	public void testSuscribirImplicacionYNotificarMultiplesSucesos(){
		//Si pocaAgua ^ presionAlta-------->PrenderBomBa
		sucesos.add(sucesoPocaAgua);
		sucesos.add(sucesoPresionAlta);
		this.manejadorSucesos.suscribirImplicacion(accionPrenderBomba, sucesos);
		assertEquals(false, this.bomba.isEncendida());
		//notificacion presionAlta
		this.manejadorSucesos.agregarSuceso(sucesoPresionAlta);
		assertEquals(false, this.bomba.isEncendida());
		//notificacion pocaAgua
		this.manejadorSucesos.agregarSuceso(sucesoPocaAgua);
		assertEquals(true, this.bomba.isEncendida());
	}
	
	
	// Se testea la suscripcion de implicaciones y la notificacion de varios suceos
	// ocurridos
	public void testSuscribirImplicacionYNotificarMultiplesSucesosInvalidos(){
		//Si pocaAgua ^ presionAlta-------->PrenderBomBa
		sucesos.add(sucesoPocaAgua);
		sucesos.add(sucesoPresionAlta);
		this.manejadorSucesos.suscribirImplicacion(accionPrenderBomba, sucesos);
		assertEquals(false, this.bomba.isEncendida());
		
		Suceso nuevoSucesoPocaAgua = new Suceso("pocaAgua");
		Suceso nuevoSucesoTanqueLleno = new Suceso("tanqueLleno");
		List<Suceso> sucesosNuevos = new ArrayList<Suceso>();
		sucesosNuevos.add(nuevoSucesoPocaAgua);
		sucesosNuevos.add(nuevoSucesoTanqueLleno);
		//notificacion pocaAgua ^ presionAlta
		this.manejadorSucesos.agregarSucesos(sucesosNuevos);
		assertEquals(false, this.bomba.isEncendida());
	}
	
	
	 // Se realizan pruebas para ver si la funcionalidad de suscribir
	 // sucesos y acciones se realizan de la forma correcta
	 
	/****************************************************
	 *                      CASO 1	 
	 *****************************************************/
	public void testNotificarUnicoSuceso(){
		this.manejadorSucesos = new ManejadorDeSucesos();
	    this.manejadorSucesos.establecerConfiguracionDiscontinuo();
		List<Suceso> lista = GeneradorDeSuceso.obtenerSucesos("A");
		bomba.setEncendida(false);
		this.manejadorSucesos.suscribirImplicacion(accionPrenderBomba, lista);
		//agrego el suceso y notifico
		this.manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("X Y Z A"));
		assertEquals("Implicacion Valida", bomba.isEncendida(),true);	
	}
	
	/****************************************************
	 *                      CASO 2	 
	 *****************************************************/
	public void testNotificarSucesosDiscontinuosSinImportarOrdenSinCancelaciones(){
		this.manejadorSucesos = new ManejadorDeSucesos();
	    this.manejadorSucesos.establecerConfiguracionDiscontinuo();
		List<Suceso> lista = GeneradorDeSuceso.obtenerSucesos("A B");
		this.manejadorSucesos.suscribirImplicacion(accionPrenderBomba, lista);
		//agrego el suceso y notifico
		bomba.setEncendida(false);
		this.manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("X Y Z A B"));
		assertEquals("Implicacion Valida", bomba.isEncendida(),true);		
	}
	public void testNotificarSucesosDiscontinuosSinImportarOrdenSinCancelaciones2(){
		this.manejadorSucesos = new ManejadorDeSucesos();
	    this.manejadorSucesos.establecerConfiguracionDiscontinuo();
		List<Suceso> lista = GeneradorDeSuceso.obtenerSucesos("A B");
		this.manejadorSucesos.suscribirImplicacion(accionPrenderBomba, lista);
		//agrego el suceso y notifico
		bomba.setEncendida(false);
		this.manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("X Y Z B A"));
		assertEquals("Implicacion Valida", bomba.isEncendida(),true);		
	}
	public void testNotificarSucesosDiscontinuosSinImportarOrdenSinCancelaciones3(){
		this.manejadorSucesos = new ManejadorDeSucesos();
	    this.manejadorSucesos.establecerConfiguracionDiscontinuo();
		List<Suceso> lista = GeneradorDeSuceso.obtenerSucesos("A B");
		this.manejadorSucesos.suscribirImplicacion(accionPrenderBomba, lista);
		//agrego el suceso y notifico
		bomba.setEncendida(false);
		this.manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("X Y Z A K B"));
		assertEquals("Implicacion Valida", bomba.isEncendida(),true);		
	}
	
	public void testNotificarSucesosDiscontinuosSinImportarOrdenSinCancelaciones4(){
		this.manejadorSucesos = new ManejadorDeSucesos();
	    this.manejadorSucesos.establecerConfiguracionDiscontinuo();
		List<Suceso> lista = GeneradorDeSuceso.obtenerSucesos("A B");
		this.manejadorSucesos.suscribirImplicacion(accionPrenderBomba, lista);
		//agrego el suceso y notifico
		bomba.setEncendida(false);
		this.manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("X Y Z B K A"));
		assertEquals("Implicacion Valida", bomba.isEncendida(),true);		
	}
	
	/****************************************************
	 *                      CASO 3	 
	 *****************************************************/
	public void testNotificarSucesosContinuosSinImportarOrdenSinCancelaciones(){
		this.manejadorSucesos = new ManejadorDeSucesos();
	    this.manejadorSucesos.establecerConfiguracionContinuo();
	    List<Suceso> lista = GeneradorDeSuceso.obtenerSucesos("A B");
		this.manejadorSucesos.suscribirImplicacion(accionPrenderBomba, lista);
		//agrego el suceso y notifico
		bomba.setEncendida(false);
		this.manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("X Y Z A B"));
		assertEquals("Implicacion Valida", bomba.isEncendida(),true);		
	}
	
	
	public void testNotificarSucesosContinuosSinImportarOrdenSinCancelaciones2(){
		this.manejadorSucesos = new ManejadorDeSucesos();
	    this.manejadorSucesos.establecerConfiguracionContinuo();
	    List<Suceso> lista = GeneradorDeSuceso.obtenerSucesos("A B");
		this.manejadorSucesos.suscribirImplicacion(accionPrenderBomba, lista);
		//agrego el suceso y notifico
		bomba.setEncendida(false);
		this.manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("X Y Z B A"));
		assertEquals("Implicacion Valida", bomba.isEncendida(),true);		
	}
	
	public void testNotificarSucesosContinuosSinImportarOrdenSinCancelaciones3(){
		this.manejadorSucesos = new ManejadorDeSucesos();
	    this.manejadorSucesos.establecerConfiguracionContinuo();
	    List<Suceso> lista = GeneradorDeSuceso.obtenerSucesos("A B");
		this.manejadorSucesos.suscribirImplicacion(accionPrenderBomba, lista);
		//agrego el suceso y notifico
		bomba.setEncendida(false);
		this.manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("X Y Z A K B"));
		assertEquals("Implicacion Invalida", bomba.isEncendida(),false);		
	}
	public void testNotificarSucesosContinuosSinImportarOrdenSinCancelaciones4(){
		this.manejadorSucesos = new ManejadorDeSucesos();
	    this.manejadorSucesos.establecerConfiguracionContinuo();
	    List<Suceso> lista = GeneradorDeSuceso.obtenerSucesos("A B");
		this.manejadorSucesos.suscribirImplicacion(accionPrenderBomba, lista);
		//agrego el suceso y notifico
		bomba.setEncendida(false);
		this.manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("X Y Z B K A"));
		assertEquals("Implicacion Invalida", bomba.isEncendida(),false);		
	}
	/****************************************************
	 *                      CASO 4	 
	 *****************************************************/
	public void testNotificarSucesosDiscontinuosImportaOrdenSinCancelaciones(){
		this.manejadorSucesos = new ManejadorDeSucesos();
	    this.manejadorSucesos.establecerConfiguracionSecuenciaDiscontinua();
	    List<Suceso> lista = GeneradorDeSuceso.obtenerSucesos("A B");
		this.manejadorSucesos.suscribirImplicacion(accionPrenderBomba, lista);
		//agrego el suceso y notifico
		bomba.setEncendida(false);
		this.manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("X Y Z A B"));
		assertEquals("Implicacion Valida", bomba.isEncendida(),true);		
	}
	
	public void testNotificarSucesosDiscontinuosImportaOrdenSinCancelaciones2(){
		this.manejadorSucesos = new ManejadorDeSucesos();
		this.manejadorSucesos.establecerConfiguracionSecuenciaDiscontinua();
		List<Suceso> lista = GeneradorDeSuceso.obtenerSucesos("A B");
		this.manejadorSucesos.suscribirImplicacion(accionPrenderBomba, lista);
		//agrego el suceso y notifico
		bomba.setEncendida(false);
		this.manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("X Y Z B A"));
		assertEquals("Implicacion Invalida", bomba.isEncendida(),false);		
	}
	
	
	public void testNotificarSucesosDiscontinuosImportaOrdenSinCancelaciones3(){
		this.manejadorSucesos = new ManejadorDeSucesos();
		this.manejadorSucesos.establecerConfiguracionSecuenciaDiscontinua();
		List<Suceso> lista = GeneradorDeSuceso.obtenerSucesos("A B");
		this.manejadorSucesos.suscribirImplicacion(accionPrenderBomba, lista);
		//agrego el suceso y notifico
		bomba.setEncendida(false);
		this.manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("X Y Z A K B"));
		assertEquals("Implicacion Valida", bomba.isEncendida(),true);		
	}
	
	public void testNotificarSucesosDiscontinuosImportaOrdenSinCancelaciones4(){
		this.manejadorSucesos = new ManejadorDeSucesos();
		this.manejadorSucesos.establecerConfiguracionSecuenciaDiscontinua();
		List<Suceso> lista = GeneradorDeSuceso.obtenerSucesos("A B");
		this.manejadorSucesos.suscribirImplicacion(accionPrenderBomba, lista);
		//agrego el suceso y notifico
		bomba.setEncendida(false);
		this.manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("X Y Z B K A"));
		assertEquals("Implicacion Invalida", bomba.isEncendida(),false);		
	}
	
	/****************************************************
	 *                      CASO 5	 
	 *****************************************************/
	public void testNotificarSucesosContinuosImportaOrdenSinCancelaciones(){
		this.manejadorSucesos = new ManejadorDeSucesos();
		this.manejadorSucesos.establecerConfiguracionSecuenciaContinua();
		List<Suceso> lista = GeneradorDeSuceso.obtenerSucesos("A B");
		this.manejadorSucesos.suscribirImplicacion(accionPrenderBomba, lista);
		//agrego el suceso y notifico
		bomba.setEncendida(false);
		this.manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("X Y Z A B"));
		assertEquals("Implicacion Valida", bomba.isEncendida(),true);		
	}
	public void testNotificarSucesosContinuosImportaOrdenSinCancelaciones2(){
		this.manejadorSucesos = new ManejadorDeSucesos();
		this.manejadorSucesos.establecerConfiguracionSecuenciaContinua();
		List<Suceso> lista = GeneradorDeSuceso.obtenerSucesos("A B");
		this.manejadorSucesos.suscribirImplicacion(accionPrenderBomba, lista);
		//agrego el suceso y notifico
		bomba.setEncendida(false);
		this.manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("X Y Z B A"));
		assertEquals("Implicacion Invalida", bomba.isEncendida(),false);		
	}
	
	public void testNotificarSucesosContinuosImportaOrdenSinCancelaciones3(){
		this.manejadorSucesos = new ManejadorDeSucesos();
		this.manejadorSucesos.establecerConfiguracionSecuenciaContinua();
		List<Suceso> lista = GeneradorDeSuceso.obtenerSucesos("A B");
		this.manejadorSucesos.suscribirImplicacion(accionPrenderBomba, lista);
		//agrego el suceso y notifico
		bomba.setEncendida(false);
		this.manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("X Y Z A K B"));
		assertEquals("Implicacion Invalida", bomba.isEncendida(),false);		
	}
	
	public void testNotificarSucesosContinuosImportaOrdenSinCancelaciones4(){
		this.manejadorSucesos = new ManejadorDeSucesos();
		this.manejadorSucesos.establecerConfiguracionSecuenciaContinua();
		List<Suceso> lista = GeneradorDeSuceso.obtenerSucesos("A B");
		this.manejadorSucesos.suscribirImplicacion(accionPrenderBomba, lista);
		//agrego el suceso y notifico
		bomba.setEncendida(false);
		this.manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("X Y Z B K A"));
		assertEquals("Implicacion Invalida", bomba.isEncendida(),false);		
	}
	/****************************************************
	 *                     TODO CASO 6
	 *****************************************************/
	
	
	public void testAgregarSucesosYNotificar(){
		this.manejadorSucesos = new ManejadorDeSucesos();
		//Si pocaAgua ^ presionAlta-------->PrenderBomBa
		sucesos.add(sucesoPocaAgua);
		sucesos.add(sucesoPresionAlta);
		this.manejadorSucesos.suscribirImplicacion(accionPrenderBomba, sucesos);
		assertEquals(false, this.bomba.isEncendida());
		
		this.manejadorSucesos.agregarSuceso(new Suceso("pocaAgua"));
		this.manejadorSucesos.agregarSuceso(new Suceso("presionAlta"));
		assertEquals(true, this.bomba.isEncendida());
	
		this.bomba.setEncendida(false);
		//notifica pocaAgua ^ presionAlta ^ tanqueLleno
		this.manejadorSucesos.agregarSuceso(new Suceso("pocaAgua"));
		this.manejadorSucesos.agregarSuceso(new Suceso("presionAlta"));
		this.manejadorSucesos.agregarSuceso(new Suceso("tanqueLleno"));
		assertEquals(true, this.bomba.isEncendida());
		
		this.bomba.setEncendida(false);
		//notifica pocaAgua ^  tanqueLleno
		this.manejadorSucesos.agregarSuceso(new Suceso("pocaAgua"));
		assertEquals(false, this.bomba.isEncendida());
		this.manejadorSucesos.agregarSuceso(new Suceso("tanqueLleno"));
		assertEquals(false, this.bomba.isEncendida());

	}
	
	
	 // En este test se evalua que el manejador de sucesos controle si 
	 // el suceso o conjuntos de sucesos que le llegan sean nulos o si la
	 // lista contiene algun elemento nulo  
	public void testNotificacionSucesosNulos(){
		this.manejadorSucesos = new ManejadorDeSucesos();
		sucesos.add(sucesoPocaAgua);
		sucesos.add(sucesoPresionAlta);
		this.manejadorSucesos.suscribirImplicacion(accionPrenderBomba, sucesos);
		assertEquals(false, this.bomba.isEncendida());
		//no agrega el suceso nulo,entonces no notifica nada
		this.manejadorSucesos.agregarSuceso(null);
		assertEquals(false, this.bomba.isEncendida());
		
		//ignora el suceso nulo y notifica los sucesos
		this.manejadorSucesos.agregarSuceso(sucesoPocaAgua);
		this.manejadorSucesos.agregarSuceso(sucesoPresionAlta);
		this.manejadorSucesos.agregarSuceso(null);
		assertEquals(true, this.bomba.isEncendida());
		
		bomba.setEncendida(false);
		this.manejadorSucesos.agregarSucesos(null);
		assertEquals(false, this.bomba.isEncendida());
		
		this.manejadorSucesos.agregarSuceso(sucesoPocaAgua);
		this.manejadorSucesos.agregarSuceso(sucesoPresionAlta);
		List<Suceso> listaNula=null;
		
		this.manejadorSucesos.agregarSucesos(listaNula);
		assertEquals(true, this.bomba.isEncendida());
		
		bomba.setEncendida(false);
		this.manejadorSucesos.agregarSuceso(sucesoPocaAgua);
		this.manejadorSucesos.agregarSuceso(sucesoPresionAlta);
		Suceso sucesoNulo = null;
		this.manejadorSucesos.agregarSuceso(sucesoNulo);
		assertEquals(true, this.bomba.isEncendida());		
		
	}
	
	/**
	 * En esta test se prueba el funcionamiento del manejador de Sucesos
	 * con 10 implicaciones y se evaluan los resultados de las accion.
	 */
	public void testDeEstres(){
		this.manejadorSucesos = new ManejadorDeSucesos();
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
		this.manejadorSucesos.suscribirImplicacion(accionReparar, lista0);
		this.manejadorSucesos.suscribirImplicacion(accionReparar, lista1);
		this.manejadorSucesos.suscribirImplicacion(accionReparar, lista2);
		this.manejadorSucesos.suscribirImplicacion(accionReparar, lista3);
		this.manejadorSucesos.suscribirImplicacion(accionReparar, lista4);
		this.manejadorSucesos.suscribirImplicacion(accionReparar, lista5);
		this.manejadorSucesos.suscribirImplicacion(accionReparar, lista6);
		this.manejadorSucesos.suscribirImplicacion(accionReparar, lista7);
		this.manejadorSucesos.suscribirImplicacion(accionReparar, lista8);
		this.manejadorSucesos.suscribirImplicacion(accionReparar, lista9);
		
		//Suceso que ninguna de las implicaciones espera
		this.manejadorSucesos.agregarSuceso(new Suceso("stockLleno"));
		assertEquals(false,maquina.isRota());
		
				
		//la implicacion de la lista 8 espera este conjunto de sucesos
		this.manejadorSucesos.agregarSuceso(new Suceso("stockLleno"));
		this.manejadorSucesos.agregarSuceso(new Suceso("gaseosaFria"));
		assertEquals(true,maquina.isRota());
		
		
		//como se que se ejecuta el comando de la implicacion 8, le cambio el comando
		assertEquals(20,accionQuitarGaseosa.getGaseosa().getStock());
		this.manejadorSucesos.borrarImplicaciones();
		this.manejadorSucesos.suscribirImplicacion(accionReparar, lista0);
		this.manejadorSucesos.suscribirImplicacion(accionReparar, lista1);
		this.manejadorSucesos.suscribirImplicacion(accionReparar, lista2);
		this.manejadorSucesos.suscribirImplicacion(accionReparar, lista3);
		this.manejadorSucesos.suscribirImplicacion(accionReparar, lista4);
		this.manejadorSucesos.suscribirImplicacion(accionReparar, lista5);
		this.manejadorSucesos.suscribirImplicacion(accionReparar, lista6);
		this.manejadorSucesos.suscribirImplicacion(accionAgregarGaseosa, lista7);
		this.manejadorSucesos.suscribirImplicacion(accionQuitarGaseosa, lista8);
		this.manejadorSucesos.suscribirImplicacion(accionQuitarGaseosa, lista9);
		this.manejadorSucesos.agregarSuceso(new Suceso("stockLleno"));
		this.manejadorSucesos.agregarSuceso(new Suceso("gaseosaFria"));
		assertEquals(19,accionQuitarGaseosa.getGaseosa().getStock());
		
		//Para el caso que se ejecute las acciones de las implicaciones de la
		//lista 8 y 9
		this.manejadorSucesos.agregarSuceso(new Suceso("stockLleno"));
		this.manejadorSucesos.agregarSuceso(new Suceso("gaseosaFria"));
		this.manejadorSucesos.agregarSuceso(new Suceso("maquinaRota"));
				
		assertEquals(17,accionQuitarGaseosa.getGaseosa().getStock());
		
		this.manejadorSucesos.agregarSuceso(new Suceso("stockLleno"));
		this.manejadorSucesos.agregarSuceso(new Suceso("gaseosaFria"));
		this.manejadorSucesos.agregarSuceso(new Suceso("maquinaRota"));
		this.manejadorSucesos.agregarSuceso(new Suceso("pocaGaseosa"));
		this.manejadorSucesos.agregarSuceso(new Suceso("maquinaDesconectada"));
		
		assertEquals(15,accionQuitarGaseosa.getGaseosa().getStock());
		assertEquals(11,accionAgregarGaseosa.getGaseosa().getStock());
		
			
	}
	
	public void testAgregarSucesoEnNotificar(){
		MaquinaGaseosa maquina = new MaquinaGaseosa();
		//Esta accion agrega un suceso dentro del ejecutar
		AccionTirarGaseosa accionTirarGaseosa = new AccionTirarGaseosa(this.manejadorSucesos);
		AccionAgregarGaseosa accionAgregarGaseosa = new AccionAgregarGaseosa();
		accionAgregarGaseosa.setGaseosa(new Gaseosa("Fanta",10,false));

		sucesos.add(new Suceso("ponerFicha"));
		sucesos.add(new Suceso("seleccionaCoca"));
		sucesos.add(new Suceso("apretaBoton"));
		assertEquals(false, maquina.isRota());
				
		this.manejadorSucesos.suscribirImplicacion(accionTirarGaseosa, sucesos);
		this.manejadorSucesos.suscribirImplicacion(accionAgregarGaseosa, new Suceso("pocaGaseosa"));
		
		
		assertEquals(10, accionAgregarGaseosa.getGaseosa().getStock());
	}
	
	public void testTamanioMaximoSucesosOcurridosNoSobrepasadoConImplicacionNoValida(){
		this.manejadorSucesos = new ManejadorDeSucesos();
		this.manejadorSucesos.establecerConfiguracionContinuo();
		this.manejadorSucesos.establecerTamanioMaximoaDeSucesosOcurridos(3);
		
	    List<Suceso> lista = GeneradorDeSuceso.obtenerSucesos("A B");
		this.manejadorSucesos.suscribirImplicacion(accionPrenderBomba, lista);
		//agrego el suceso y notifico
		bomba.setEncendida(false);
		this.manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("B K A"));
		assertEquals("Implicancia Invalida", bomba.isEncendida(),false);
		
	}
	public void testTamanioMaximoSucesosOcurridosNoSobrepasadoConImplicacionValida(){
		this.manejadorSucesos = new ManejadorDeSucesos();
		this.manejadorSucesos.establecerConfiguracionContinuo();
		this.manejadorSucesos.establecerTamanioMaximoaDeSucesosOcurridos(3);
		
	    List<Suceso> lista = GeneradorDeSuceso.obtenerSucesos("A B");
		this.manejadorSucesos.suscribirImplicacion(accionPrenderBomba, lista);
		//agrego el suceso y notifico
		bomba.setEncendida(false);
		this.manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("B A F"));
		assertEquals("Implicancia valida", bomba.isEncendida(),true);
		
	}
	public void testTamanioMaximoSucesosOcurridosSobrepasadoConImplicacionInvalida(){
		this.manejadorSucesos = new ManejadorDeSucesos();
		this.manejadorSucesos.establecerConfiguracionContinuo();
		this.manejadorSucesos.establecerTamanioMaximoaDeSucesosOcurridos(3);
		
	    List<Suceso> lista = GeneradorDeSuceso.obtenerSucesos("A B");
		this.manejadorSucesos.suscribirImplicacion(accionPrenderBomba, lista);
		//agrego el suceso y notifico
		bomba.setEncendida(false);
		// debe quedarse con los 3 ultimos
		this.manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("B A F F F"));
		assertEquals("Implicancia Invalida", bomba.isEncendida(),false);
		
	}
	
	public void testCancelacionPorDefecto(){
		
		this.manejadorSucesos.habilitarCancelador();
		
		//Si pocaAgua-------->PrenderBomBa
		sucesos.add(sucesoPocaAgua);
		this.manejadorSucesos.suscribirImplicacion(accionPrenderBomba, sucesos);			
		
		this.manejadorSucesos.agregarSuceso(sucesoPocaAgua);
		this.manejadorSucesos.agregarSuceso(sucesoPresionAlta);
		assertEquals(true, this.bomba.isEncendida());
		
		this.bomba.setEncendida(false);
		this.manejadorSucesos.agregarSuceso(sucesoPocaAgua);
		this.manejadorSucesos.agregarSuceso(sucesoPresionAlta);
		this.manejadorSucesos.agregarSuceso(new Suceso("muchaAgua", "pocaAgua"));
		assertEquals(false, this.bomba.isEncendida());
		
		this.bomba.setEncendida(false);
		this.manejadorSucesos.agregarSuceso(sucesoPocaAgua);
		this.manejadorSucesos.agregarSuceso(sucesoPresionAlta);
		this.manejadorSucesos.agregarSuceso(new Suceso("presionBaja", "presionAlta"));
		assertEquals(true, this.bomba.isEncendida());
		
		List<Suceso> sucesosCanceladores = new ArrayList<Suceso>();
		sucesosCanceladores.add(new Suceso("muchaAgua", "pocaAgua"));
		sucesosCanceladores.add(new Suceso("presionBaja", "presionAlta"));
		sucesosCanceladores.add(new Suceso("bajaTemperatura", "altaTemperatura"));
		
		this.bomba.setEncendida(false);
		this.manejadorSucesos.agregarSuceso(sucesoPocaAgua);
		this.manejadorSucesos.agregarSuceso(sucesoPresionAlta);
		this.manejadorSucesos.agregarSucesos(sucesosCanceladores);
		assertEquals(false, this.bomba.isEncendida());
		
		this.bomba.setEncendida(false);
		this.manejadorSucesos.agregarSuceso(sucesoPocaAgua);
		this.manejadorSucesos.agregarSuceso(sucesoPresionAlta);
		this.manejadorSucesos.agregarSucesos(sucesosCanceladores);
		assertEquals(false, this.bomba.isEncendida());
		
		sucesosCanceladores.remove(new Suceso("muchaAgua", "pocaAgua"));
		this.bomba.setEncendida(false);
		this.manejadorSucesos.agregarSuceso(sucesoPocaAgua);
		this.manejadorSucesos.agregarSuceso(sucesoPresionAlta);
		this.manejadorSucesos.agregarSucesos(sucesosCanceladores);
		assertEquals(true, this.bomba.isEncendida());
		
		sucesosCanceladores.add(new Suceso("muchaAgua", "pocaAgua"));
		this.bomba.setEncendida(false);
		this.manejadorSucesos.agregarSuceso(sucesoPocaAgua);
		this.manejadorSucesos.agregarSuceso(sucesoPresionAlta);
		this.manejadorSucesos.agregarSucesos(sucesosCanceladores);
		assertEquals(false, this.bomba.isEncendida());
	}
	
	public void testHabilitacionDeshabilitacionCancelador(){
		this.manejadorSucesos = new ManejadorDeSucesos();
		sucesos.add(sucesoPocaAgua);
		this.manejadorSucesos.suscribirImplicacion(accionPrenderBomba, sucesos);
		
		this.manejadorSucesos.habilitarCancelador();
		this.bomba.setEncendida(false);
		this.manejadorSucesos.agregarSuceso(sucesoPocaAgua);
		this.manejadorSucesos.agregarSuceso(sucesoPresionAlta);
		this.manejadorSucesos.agregarSuceso(new Suceso("muchaAgua", "pocaAgua"));
		assertEquals(false, this.bomba.isEncendida());
		
		this.manejadorSucesos.deshabilitarCancelador();
		this.bomba.setEncendida(false);
		this.manejadorSucesos.agregarSuceso(sucesoPocaAgua);
		this.manejadorSucesos.agregarSuceso(sucesoPresionAlta);
		this.manejadorSucesos.agregarSuceso(new Suceso("muchaAgua", "pocaAgua"));
		assertEquals(true, this.bomba.isEncendida());
	}
	
	
	
}
