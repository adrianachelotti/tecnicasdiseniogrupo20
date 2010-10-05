package prueba;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import source.*;
import junit.framework.TestCase;

public class testManejadorDeSucesos extends  TestCase {

	private ManejadorDeSucesos manejadorSucesos;
	
	private Tanque tanque;
	
	private Bomba bomba;
	
	private Suceso sucesoPocaAgua;
	
	private Suceso sucesoTanqueLleno;
	
	private Suceso sucesoPresionAlta;
	
	private List<Suceso> sucesos;	
	
	private AccionPrenderBomba accionPrenderBomba;
	
	private AccionApagarBomba accionApagarBomba;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();		
		this.manejadorSucesos = ManejadorDeSucesos.getInstancia();
		this.bomba = new Bomba();
		this.tanque = new Tanque();
		this.sucesoPocaAgua = new Suceso("pocaAgua");
		this.sucesoTanqueLleno = new Suceso("tanqueLleno");
		this.sucesoPresionAlta = new Suceso("presionAlta");
		this.sucesos = new LinkedList<Suceso>();
		this.accionApagarBomba = new AccionApagarBomba();
		
		this.accionApagarBomba.setBomba(bomba);
		this.accionApagarBomba.setTanque(tanque);
		
		this.accionPrenderBomba = new AccionPrenderBomba();
		this.accionPrenderBomba.setBomba(bomba);
	}
	
	public void testSuscribirImplicacionYNotificarSuceso(){
		//si pocaAgua ---> accionPrenderBomba
		this.manejadorSucesos.suscribirImplicacion(accionPrenderBomba, sucesoPocaAgua);
		assertEquals(false, this.bomba.isEncendida());
		// notificacion sucesoPocaAgua
		this.manejadorSucesos.notificar(sucesoPocaAgua);
		assertEquals(true, this.bomba.isEncendida());
	}
	
	public void testSuscribirImplicacionYNotificarSucesoInvalido(){
		//si pocaAgua ---> accionPrenderBomba
		this.manejadorSucesos.suscribirImplicacion(accionPrenderBomba, sucesoPocaAgua);
		assertEquals(false, this.bomba.isEncendida());
		//notificacion sucesoPresionAlta
		this.manejadorSucesos.notificar(sucesoPresionAlta);
		assertEquals(false, this.bomba.isEncendida());
	}
	
		
	public void testSuscribirImplicacionYNotificarMultiplesSucesos(){
		//Si pocaAgua ^ presionAlta-------->PrenderBomBa
		sucesos.add(sucesoPocaAgua);
		sucesos.add(sucesoPresionAlta);
		this.manejadorSucesos.suscribirImplicacion(accionPrenderBomba, sucesos);
		assertEquals(false, this.bomba.isEncendida());
		//notificacion presionAlta
		this.manejadorSucesos.notificar(sucesoPresionAlta);
		assertEquals(false, this.bomba.isEncendida());
		//notificacion pocaAgua
		this.manejadorSucesos.notificar(sucesoPocaAgua);
		assertEquals(false, this.bomba.isEncendida());
		
		Suceso nuevoSucesoPocaAgua = new Suceso("pocaAgua");
		Suceso nuevoSucesoPresionAlta = new Suceso("presionAlta");
		List<Suceso> sucesosNuevos = new ArrayList<Suceso>();
		sucesosNuevos.add(nuevoSucesoPocaAgua);
		sucesosNuevos.add(nuevoSucesoPresionAlta);
		//notificacion pocaAgua ^ presionAlta
		this.manejadorSucesos.notificar(sucesosNuevos);
		assertEquals(true, this.bomba.isEncendida());
	}
	
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
		this.manejadorSucesos.notificar(sucesosNuevos);
		assertEquals(false, this.bomba.isEncendida());
	}
	
	
	public void testAgregarSucesosYNotificar(){
		//Si pocaAgua ^ presionAlta-------->PrenderBomBa
		sucesos.add(sucesoPocaAgua);
		sucesos.add(sucesoPresionAlta);
		this.manejadorSucesos.suscribirImplicacion(accionPrenderBomba, sucesos);
		assertEquals(false, this.bomba.isEncendida());
		
		this.manejadorSucesos.agregarSuceso(new Suceso("pocaAgua"));
		this.manejadorSucesos.agregarSuceso(new Suceso("presionAlta"));
		this.manejadorSucesos.notificar();
		assertEquals(true, this.bomba.isEncendida());
		
		this.bomba.setEncendida(false);
		this.manejadorSucesos.agregarSuceso(new Suceso("pocaAgua"));
		this.manejadorSucesos.agregarSuceso(new Suceso("presionAlta"));
		this.manejadorSucesos.agregarSuceso(new Suceso("tanqueLleno"));
		this.manejadorSucesos.notificar();
		assertEquals(true, this.bomba.isEncendida());
		
		this.bomba.setEncendida(false);
		this.manejadorSucesos.agregarSuceso(new Suceso("pocaAgua"));
		this.manejadorSucesos.agregarSuceso(new Suceso("tanqueLleno"));
		this.manejadorSucesos.notificar();
		assertEquals(false, this.bomba.isEncendida());
		
		this.bomba.setEncendida(false);
		this.manejadorSucesos.notificar();
		assertEquals(false, this.bomba.isEncendida());
	}
	
	
	public void testEventoOcurrido(){
		//Si pocaAgua ^ presionAlta-------->PrenderBomBa
		sucesos.add(sucesoPocaAgua);
		sucesos.add(sucesoPresionAlta);
		this.manejadorSucesos.suscribirImplicacion(accionPrenderBomba, sucesos);
		//Si muchaAgua -----------------> apagarBomba
		this.manejadorSucesos.suscribirImplicacion(accionApagarBomba, sucesoTanqueLleno);
		this.manejadorSucesos.notificar(sucesoTanqueLleno);	
		assertEquals(10,tanque.getNivelAgua());
		assertEquals(false,bomba.isEncendida());
	}
	
	public void testEventosOcurridos(){
		
		sucesos.add(sucesoPocaAgua);
		sucesos.add(sucesoPresionAlta);
		this.manejadorSucesos.suscribirImplicacion(accionPrenderBomba, sucesos);
		this.manejadorSucesos.suscribirImplicacion(accionApagarBomba, sucesoTanqueLleno);
		this.manejadorSucesos.notificar(sucesos);		
	}
	
	
}
