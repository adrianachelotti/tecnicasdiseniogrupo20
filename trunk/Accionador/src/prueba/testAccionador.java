package prueba;


import java.util.LinkedList;
import java.util.List;

import source.*;
import junit.framework.TestCase;

public class testAccionador extends  TestCase {

	private ManejadorDeSucesos accionador;
	
	private Tanque tanque;
	
	private Bomba bomba;
	
	private Suceso evento;
	
	private Suceso evento1;
	
	private Suceso evento2;
	
	private List<Suceso> lista;	
	
	private Accion commandPrenderBomba;
	
	private Accion commandApagarBomba;
	
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();		
		this.accionador = ManejadorDeSucesos.getInstancia();
		this.bomba = new Bomba();
		this.tanque = new Tanque();
		this.evento = new Suceso();
		this.evento1 = new Suceso();
		this.evento2 = new Suceso();
		this.lista = new LinkedList<Suceso>();
		this.commandApagarBomba = new ComandoApagarBomba();
		
		((ComandoApagarBomba)this.commandApagarBomba).setBomba(bomba);
		((ComandoApagarBomba)this.commandApagarBomba).setTanque(tanque);
		
		this.commandPrenderBomba = new ComandoPrenderBomba();
		((ComandoPrenderBomba)this.commandPrenderBomba).setBomba(bomba);
		
		
		this.evento.setIdSuceso("pocaAgua");
		this.evento1.setIdSuceso("tanqueLleno");
		this.evento2.setIdSuceso("presionAlta");

	
		
	}
	
	public void testSuscribirEvento(){
		//si pocaAgua ---> comando=PrenderBomba
		this.accionador.suscribirImplicacion(commandPrenderBomba, evento);
	}
	
	public void testSuscribirEventosObjeto(){
		//Si pocaAgua ^ presionAlta-------->PrenderBomBa
		lista.add(evento);
		lista.add(evento2);
		this.accionador.suscribirImplicacion(commandPrenderBomba, lista);
		
	}
	public void testEventoOcurrido(){
		//Si pocaAgua ^ presionAlta-------->PrenderBomBa
		lista.add(evento);
		lista.add(evento2);
		this.accionador.suscribirImplicacion(commandPrenderBomba, lista);
		//Si muchaAgua -----------------> apagarBomba
		this.accionador.suscribirImplicacion(commandApagarBomba, evento1);
		this.accionador.notificar(evento1);	
		assertEquals(10,tanque.getNivelAgua());
		assertEquals(false,bomba.isEncendida());
	}
	
	public void testEventosOcurridos(){
		
		lista.add(evento);
		lista.add(evento2);
		this.accionador.suscribirImplicacion(commandPrenderBomba, lista);
		this.accionador.suscribirImplicacion(commandApagarBomba, evento1);
		this.accionador.notificar(lista);		
	}
	
	
}
