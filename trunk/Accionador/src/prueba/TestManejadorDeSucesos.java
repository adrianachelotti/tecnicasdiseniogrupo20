package prueba;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import junit.framework.TestCase;
import source.ManejadorDeSucesos;
import source.Suceso;
import cliente.AccionAgregarGaseosa;
import cliente.AccionApagarBomba;
import cliente.AccionPrenderBomba;
import cliente.AccionQuitarGaseosa;
import cliente.AccionRepararMaquinaGasesosa;
import cliente.AccionTirarGaseosa;
import cliente.Bomba;
import cliente.Gaseosa;
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
	 
	public void testAgregarSucesosYNotificar(){
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
		List<Suceso> lista0 = new ArrayList<Suceso>();
		List<Suceso> lista1 = new ArrayList<Suceso>();
		List<Suceso> lista2 = new ArrayList<Suceso>();
		List<Suceso> lista3 = new ArrayList<Suceso>();
		List<Suceso> lista4 = new ArrayList<Suceso>();
		List<Suceso> lista5 = new ArrayList<Suceso>();
		List<Suceso> lista6 = new ArrayList<Suceso>();
		List<Suceso> lista7 = new ArrayList<Suceso>();
		List<Suceso> lista8 = new ArrayList<Suceso>();
		List<Suceso> lista9 = new ArrayList<Suceso>();
		
		lista0.add(new Suceso("ponerFicha"));
		lista0.add(new Suceso("seleccionaCoca"));
		lista0.add(new Suceso("apretaBoton"));
		
		lista1.add(new Suceso("noHayCoca"));
		lista1.add(new Suceso("noHayCocaLight"));
		lista1.add(new Suceso("noHaySprite"));
		
		lista2.add(new Suceso("ingresaMoneda"));
		lista2.add(new Suceso("importeInsuficiente"));
		
		lista3.add(new Suceso("apretaBoton"));
		lista3.add(new Suceso("seleccionaNada"));
		
		lista4.add(new Suceso("ingresoSobranteMoneda"));
		lista4.add(new Suceso("noHayCambio"));
		
		lista5.add(new Suceso("ponerFicha"));
		lista5.add(new Suceso("fichaTrabada"));
		
		lista6.add(new Suceso("gaseosaCaliente"));
		
		lista7.add(new Suceso("pocaGaseosa"));
		lista7.add(new Suceso("maquinaDesconectada"));

		lista8.add(new Suceso("gaseosaFria"));
		lista8.add(new Suceso("stockLleno"));
		
		lista9.add(new Suceso("maquinaRota"));
		
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
	
	public void testCancelacionTotal(){
		
		this.manejadorSucesos.habilitarCancelador();
		
		//Si pocaAgua-------->PrenderBomBa
		sucesos.add(sucesoPocaAgua);
		this.manejadorSucesos.suscribirImplicacion(accionPrenderBomba, sucesos);			
		
		this.manejadorSucesos.agregarSuceso(sucesoPocaAgua);
		this.manejadorSucesos.agregarSuceso(sucesoPocaAgua);
		this.manejadorSucesos.agregarSuceso(sucesoPresionAlta);
		this.manejadorSucesos.agregarSuceso(new Suceso("muchaAgua", "pocaAgua"));
		assertEquals(true, this.bomba.isEncendida());
		
		this.manejadorSucesos.establecerCanceladorTotal();
		this.bomba.setEncendida(false);
		this.manejadorSucesos.agregarSuceso(sucesoPocaAgua);
		this.manejadorSucesos.agregarSuceso(sucesoPocaAgua);
		this.manejadorSucesos.agregarSuceso(sucesoPresionAlta);
		this.manejadorSucesos.agregarSuceso(new Suceso("muchaAgua", "pocaAgua"));
		assertEquals(false, this.bomba.isEncendida());
	}
	
}
