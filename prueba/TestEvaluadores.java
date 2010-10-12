package prueba;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import cliente.AccionApagarBomba;
import cliente.AccionPrenderBomba;
import cliente.Bomba;
import cliente.Tanque;

import source.ManejadorDeSucesos;
import source.Suceso;
import junit.framework.TestCase;

public class TestEvaluadores extends TestCase {
	private ManejadorDeSucesos manejadorSucesos;
	
	private Tanque tanque;
	
	private Bomba bomba;
	
	private Suceso sucesoPocaAgua;
	
	private Suceso sucesoPresionAlta;
	
	private List<Suceso> sucesos;	
	
	private AccionPrenderBomba accionPrenderBomba;
	
	private AccionApagarBomba accionApagarBomba;
	protected void setUp() throws Exception {
		super.setUp();		
		this.manejadorSucesos = new ManejadorDeSucesos();
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
	
	public void testEvaluadorSecuenciaContinua(){
		//Si pocaAgua ^ presionAlta-------->PrenderBomBa
		sucesos.add(sucesoPocaAgua);
		sucesos.add(sucesoPresionAlta);
		sucesos.add(new Suceso("tanqueLleno"));
		this.manejadorSucesos.suscribirImplicacion(accionPrenderBomba, sucesos);
		assertEquals(false, this.bomba.isEncendida());
		// Se establece la configuracion de Susecos en secuencia Continua
		this.manejadorSucesos.establecerConfiguracionSecuenciaContinua();
		
		this.manejadorSucesos.agregarSuceso(new Suceso("pocaAgua"));
		this.manejadorSucesos.agregarSuceso(new Suceso("presionAlta"));
		this.manejadorSucesos.agregarSuceso(new Suceso("tanqueLleno"));
		this.manejadorSucesos.notificar();
		assertEquals(true, this.bomba.isEncendida());
		
		this.bomba.setEncendida(false);
		this.manejadorSucesos.agregarSuceso(new Suceso("presionAlta"));
		this.manejadorSucesos.agregarSuceso(new Suceso("pocaAgua"));
		this.manejadorSucesos.agregarSuceso(new Suceso("tanqueLleno"));
		this.manejadorSucesos.notificar();
		assertEquals(false, this.bomba.isEncendida());
		
		this.bomba.setEncendida(false);
		this.manejadorSucesos.agregarSuceso(new Suceso("presionAlta"));
		this.manejadorSucesos.agregarSuceso(new Suceso("pocaAgua"));
		this.manejadorSucesos.agregarSuceso(new Suceso("tanqueRoto"));
		this.manejadorSucesos.notificar();
		assertEquals(false, this.bomba.isEncendida());
		
		this.bomba.setEncendida(false);
		this.manejadorSucesos.agregarSuceso(new Suceso("pocaAgua"));
		this.manejadorSucesos.agregarSuceso(new Suceso("presionAlta"));
		this.manejadorSucesos.agregarSuceso(new Suceso("tanqueLleno"));
		this.manejadorSucesos.agregarSuceso(new Suceso("tanqueRoto"));
		this.manejadorSucesos.notificar();
		assertEquals(true, this.bomba.isEncendida());
	}
	
	public void testEvaluadorSecuenciaDiscontinua(){
		//Si pocaAgua ^ presionAlta-------->PrenderBomBa
		sucesos.add(sucesoPocaAgua);
		sucesos.add(sucesoPresionAlta);		
		sucesos.add(new Suceso("tanqueLleno"));
		this.manejadorSucesos.suscribirImplicacion(accionPrenderBomba, sucesos);
		assertEquals(false, this.bomba.isEncendida());
		
		this.manejadorSucesos.establecerConfiguracionSecuenciaDiscontinua();
		
			
		this.manejadorSucesos.agregarSuceso(new Suceso("pocaAgua"));
		this.manejadorSucesos.agregarSuceso(new Suceso("presionBaja"));
		this.manejadorSucesos.agregarSuceso(new Suceso("presionAlta"));
		this.manejadorSucesos.agregarSuceso(new Suceso("algoDeAgua"));
		this.manejadorSucesos.agregarSuceso(new Suceso("tanqueLleno"));
		this.manejadorSucesos.notificar();
		assertEquals(true, this.bomba.isEncendida());
		
		this.bomba.setEncendida(false);
		this.manejadorSucesos.agregarSuceso(new Suceso("pocaAgua"));
		this.manejadorSucesos.agregarSuceso(new Suceso("pocaAgua"));
		this.manejadorSucesos.agregarSuceso(new Suceso("presionBaja"));
		this.manejadorSucesos.agregarSuceso(new Suceso("presionAlta"));
		this.manejadorSucesos.agregarSuceso(new Suceso("algoDeAgua"));
		this.manejadorSucesos.agregarSuceso(new Suceso("tanqueLleno"));
		this.manejadorSucesos.agregarSuceso(new Suceso("tanqueLleno"));
		this.manejadorSucesos.notificar();
		assertEquals(true, this.bomba.isEncendida());
		
		this.bomba.setEncendida(false);
		this.manejadorSucesos.agregarSuceso(new Suceso("AlgoDeAgua"));
		this.manejadorSucesos.agregarSuceso(new Suceso("presionBaja"));
		this.manejadorSucesos.agregarSuceso(new Suceso("presionAlta"));
		this.manejadorSucesos.agregarSuceso(new Suceso("pocaAgua"));
		this.manejadorSucesos.notificar();
		assertEquals(false, this.bomba.isEncendida());
		
		this.bomba.setEncendida(false);
		this.manejadorSucesos.agregarSuceso(new Suceso("pocaAgua"));
		this.manejadorSucesos.notificar();
		assertEquals(false, this.bomba.isEncendida());
		
		this.bomba.setEncendida(false);
		this.manejadorSucesos.agregarSuceso(new Suceso("pocaLuz"));
		this.manejadorSucesos.agregarSuceso(new Suceso("pocoAire"));
		this.manejadorSucesos.agregarSuceso(new Suceso("pocaTierra"));
		this.manejadorSucesos.notificar();
		assertEquals(false, this.bomba.isEncendida());
		
	}
	
	public void testEvaluadorPorDefecto(){
		// muchoViento ^ pocaAgua ----------------->accionApagarBomba
		this.manejadorSucesos.agregarSuceso(new Suceso("pocaAgua"));
		this.manejadorSucesos.agregarSuceso(new Suceso("muchoRuido"));
		this.manejadorSucesos.agregarSuceso(new Suceso("muchoViento"));
		this.manejadorSucesos.agregarSuceso(new Suceso("presionBaja"));
		
		sucesos.add(new Suceso("muchoViento"));
		sucesos.add(new Suceso("pocaAgua"));
		
		this.bomba.setEncendida(true);
				
		this.manejadorSucesos.suscribirImplicacion(accionApagarBomba,sucesos);
		// solo se fija si la lista de sucesos suscriptos esta dentro de los sucesos agregados
		this.manejadorSucesos.establecerConfiguracionPorDefecto();
		this.manejadorSucesos.notificar();
		
		assertEquals(false, this.bomba.isEncendida());
		
		this.manejadorSucesos.agregarSuceso(new Suceso("presionBaja"));
		
		sucesos.add(new Suceso("muchoViento"));
		sucesos.add(new Suceso("pocaAgua"));
		
		this.manejadorSucesos.suscribirImplicacion(accionPrenderBomba,sucesos);
		this.manejadorSucesos.agregarSuceso(new Suceso("muchoViento"));
		this.manejadorSucesos.agregarSuceso(new Suceso("presionBaja"));
		this.manejadorSucesos.notificar();
		assertEquals(false, this.bomba.isEncendida());
		
		sucesos.add(new Suceso("muchoViento"));
		sucesos.add(new Suceso("pocaAgua"));
		
		this.manejadorSucesos.suscribirImplicacion(accionPrenderBomba,sucesos);
		this.manejadorSucesos.agregarSuceso(new Suceso("muchoViento"));
		this.manejadorSucesos.notificar();
		assertEquals(false, this.bomba.isEncendida());
		
		
		List<Suceso> listaSucesos = new ArrayList<Suceso>();
		
		listaSucesos.add(new Suceso("pocaAgua"));
		listaSucesos.add(new Suceso("muchoRuido"));
		listaSucesos.add(new Suceso("muchoViento"));
		listaSucesos.add(new Suceso("presionBaja"));
		
		sucesos.add(new Suceso("muchoViento"));
		sucesos.add(new Suceso("pocaAgua"));
		
		this.manejadorSucesos.suscribirImplicacion(accionPrenderBomba,sucesos);
		
		this.manejadorSucesos.notificar(listaSucesos);
		assertEquals(true, this.bomba.isEncendida());	
			
	}
	
	public void testEvaluadorIgualdadConjunto(){
		// muchoViento ^ pocaAgua ----------------->accionApagarBomba
		this.manejadorSucesos.agregarSuceso(new Suceso("pocaAgua"));
		this.manejadorSucesos.agregarSuceso(new Suceso("muchoRuido"));
		this.manejadorSucesos.agregarSuceso(new Suceso("muchoViento"));
		this.manejadorSucesos.agregarSuceso(new Suceso("presionBaja"));
		
		sucesos.add(new Suceso("muchoViento"));
		sucesos.add(new Suceso("pocaAgua"));
		
		this.bomba.setEncendida(false);
				
		this.manejadorSucesos.suscribirImplicacion(accionApagarBomba,sucesos);
		// solo se fija si la lista de sucesos suscriptos esta dentro de los sucesos agregados
		this.manejadorSucesos.establecerConfiguracionIgualdadConjunto();
		this.manejadorSucesos.notificar();
		
		assertEquals(false, this.bomba.isEncendida());		
		
		
		sucesos.add(new Suceso("muchoViento"));
		sucesos.add(new Suceso("pocaAgua"));
		sucesos.add(new Suceso("muchoCalor"));
					
		
		this.manejadorSucesos.suscribirImplicacion(accionPrenderBomba,sucesos);
		this.manejadorSucesos.agregarSuceso(new Suceso("muchoViento"));
		this.manejadorSucesos.agregarSuceso(new Suceso("presionBaja"));
		this.manejadorSucesos.notificar();
		assertEquals(false, this.bomba.isEncendida());
			
		
		sucesos.add(new Suceso("muchoViento"));
		sucesos.add(new Suceso("pocaAgua"));
		sucesos.add(new Suceso("pocoSodio"));
		sucesos.add(new Suceso("bajaTemperatura"));
		
		this.manejadorSucesos.suscribirImplicacion(accionPrenderBomba,sucesos);
		this.manejadorSucesos.agregarSuceso(new Suceso("muchoViento"));
		this.manejadorSucesos.agregarSuceso(new Suceso("pocaAgua"));
		this.manejadorSucesos.agregarSuceso(new Suceso("pocoSodio"));
		this.manejadorSucesos.agregarSuceso(new Suceso("bajaTemperatura"));
		this.manejadorSucesos.notificar();
		assertEquals(false, this.bomba.isEncendida());
					
	}

}
