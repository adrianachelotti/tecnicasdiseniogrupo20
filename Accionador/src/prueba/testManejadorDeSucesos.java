package prueba;


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
		this.sucesoPocaAgua = new Suceso();
		this.sucesoTanqueLleno = new Suceso();
		this.sucesoPresionAlta = new Suceso();
		this.sucesos = new LinkedList<Suceso>();
		this.accionApagarBomba = new AccionApagarBomba();
		
		this.accionApagarBomba.setBomba(bomba);
		this.accionApagarBomba.setTanque(tanque);
		
		this.accionPrenderBomba = new AccionPrenderBomba();
		this.accionPrenderBomba.setBomba(bomba);
				
		this.sucesoPocaAgua.setIdSuceso("pocaAgua");
		this.sucesoTanqueLleno.setIdSuceso("tanqueLleno");
		this.sucesoPresionAlta.setIdSuceso("presionAlta");
	}
	
	public void testSuscribirImplicacionYNotificarEvento(){
		//si pocaAgua ---> accionPrenderBomba
		this.manejadorSucesos.suscribirImplicacion(accionPrenderBomba, sucesoPocaAgua);
		assertEquals(false, this.bomba.isEncendida());
		// notificacion sucesoPocaAgua
		this.manejadorSucesos.notificar(sucesoPocaAgua);
		assertEquals(true, this.bomba.isEncendida());
	}
	
	public void testSuscribirImplicacionYNotificarEventoInvalido(){
		//si pocaAgua ---> accionPrenderBomba
		this.manejadorSucesos.suscribirImplicacion(accionPrenderBomba, sucesoPocaAgua);
		assertEquals(false, this.bomba.isEncendida());
		//notificacion sucesoPresionAlta
		this.manejadorSucesos.notificar(sucesoPresionAlta);
		assertEquals(false, this.bomba.isEncendida());
	}
	
		
	public void testSuscribirImplicacionYNotificarMultiplesEventos(){
		//Si pocaAgua ^ presionAlta-------->PrenderBomBa
		sucesos.add(sucesoPocaAgua);
		sucesos.add(sucesoPresionAlta);
		this.manejadorSucesos.suscribirImplicacion(accionPrenderBomba, sucesos);
		
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
