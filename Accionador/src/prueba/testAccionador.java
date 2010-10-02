package prueba;


import java.util.LinkedList;
import java.util.List;

import source.*;
import junit.framework.TestCase;

public class testAccionador extends TestCase {

	private Accionador accionador;
	
	private Tanque tanque;
	
	private Bomba bomba;
	
	private Evento evento;
	
	private Evento evento1;
	
	private Evento evento2;
	
	private Evento evento3;
	
	private List<Evento> lista;	
	
	private Command commandPrenderBomba;
	
	private Command commandApagarBomba;
	
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();		
		this.accionador = Accionador.getInstancia();
		this.bomba = new Bomba();
		this.tanque = new Tanque();
		this.evento = new Evento();
		this.evento1 = new Evento();
		this.evento2 = new Evento();
		this.lista = new LinkedList<Evento>();
		this.commandApagarBomba = new ComandoApagarBomba();
		
		((ComandoApagarBomba)this.commandApagarBomba).setBomba(bomba);
		((ComandoApagarBomba)this.commandApagarBomba).setTanque(tanque);
		
		this.commandPrenderBomba = new ComandoPrenderBomba();
		((ComandoPrenderBomba)this.commandPrenderBomba).setBomba(bomba);
		
		
		this.evento.setIdEvento("pocaAgua");
		this.evento1.setIdEvento("tanqueLleno");
		this.evento2.setIdEvento("presionAlta");

	
		
	}
	
	public void testSuscribirEvento(){
		//si pocaAgua ---> comando=PrenderBomba
		this.accionador.suscribirEventoYRespuesta(commandPrenderBomba, evento);
	}
	
	public void testSuscribirEventosObjeto(){
		//Si pocaAgua ^ presionAlta-------->PrenderBomBa
		lista.add(evento);
		lista.add(evento2);
		this.accionador.suscribirEventosYRespuesta(commandPrenderBomba, lista);
		
	}
	public void testEventoOcurrido(){
		//Si pocaAgua ^ presionAlta-------->PrenderBomBa
		lista.add(evento);
		lista.add(evento2);
		this.accionador.suscribirEventosYRespuesta(commandPrenderBomba, lista);
		//Si muchaAgua -----------------> apagarBomba
		this.accionador.suscribirEventoYRespuesta(commandApagarBomba, evento1);
		this.accionador.update(evento1);	
		assertEquals(10,tanque.getNivelAgua());
		assertEquals(false,bomba.isEncendida());
	}
	
	public void testEventosOcurridos(){
		
		lista.add(evento);
		lista.add(evento2);
		this.accionador.suscribirEventosYRespuesta(commandPrenderBomba, lista);
		this.accionador.suscribirEventoYRespuesta(commandApagarBomba, evento1);
		this.accionador.update(lista);		
	}
	
	
}
