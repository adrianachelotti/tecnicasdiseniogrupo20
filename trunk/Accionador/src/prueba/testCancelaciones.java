package prueba;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import source.AccionApagarBomba;
import source.AccionPrenderBomba;
import source.Bomba;
import source.ManejadorDeSucesos;
import source.Suceso;
import source.Tanque;
import junit.framework.TestCase;

public class testCancelaciones extends TestCase {

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
		this.manejadorSucesos = ManejadorDeSucesos.obtenerInstancia();
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
	}
	
	
	private boolean seCancelan(Suceso suceso1, Suceso suceso2)
	{
		String idSuceso1 = suceso1.getIdSuceso();
		String idSuceso2 = suceso2.getIdSuceso();
		String idCanceladorSuceso1 = suceso1.getIdSucesoCancelador();
		String idCanceladorSuceso2 = suceso2.getIdSucesoCancelador();
		
		if(idCanceladorSuceso1==null && idCanceladorSuceso2==null)
			return false;
		
		if(idCanceladorSuceso1!=null && idCanceladorSuceso1.equals(idSuceso2))
			return true;
		
		if(idCanceladorSuceso2!=null && idCanceladorSuceso2.equals(idSuceso1))
			return true;
		
		return false;
	}
	
	public void testFuncionSeCancelan(){
		
		Suceso suceso1 = new Suceso("A","B"); 
		Suceso suceso2 = new Suceso("C","B");
		
		assertEquals(false, seCancelan(suceso1, suceso2));
		
		Suceso suceso3 = new Suceso("B","D");
		assertEquals(true, seCancelan(suceso1, suceso3));
		assertEquals(true, seCancelan(suceso2, suceso3));
		
		Suceso suceso4 = new Suceso("D","B");
		assertEquals(true, seCancelan(suceso3, suceso4));
		
		Suceso suceso5 = new Suceso("D");
		assertEquals(true, seCancelan(suceso3, suceso5));
		assertEquals(false,seCancelan(suceso4, suceso5));
		
		Suceso suceso6 = new Suceso("A");
		assertEquals(false, seCancelan(suceso5, suceso6));
		
		Suceso suceso7 = new Suceso("B");
		assertEquals(true, seCancelan(suceso1, suceso7));
			
	}
	
	public void testCancelacionPorDefecto(){
		
		this.manejadorSucesos.habilitarCancelador();
		
		//Si pocaAgua-------->PrenderBomBa
		sucesos.add(sucesoPocaAgua);
		this.manejadorSucesos.suscribirImplicacion(accionPrenderBomba, sucesos);			
		
		this.manejadorSucesos.agregarSuceso(sucesoPocaAgua);
		this.manejadorSucesos.agregarSuceso(sucesoPresionAlta);
		this.manejadorSucesos.notificar();
		assertEquals(true, this.bomba.isEncendida());
		
		this.bomba.setEncendida(false);
		this.manejadorSucesos.agregarSuceso(sucesoPocaAgua);
		this.manejadorSucesos.agregarSuceso(sucesoPresionAlta);
		this.manejadorSucesos.agregarSuceso(new Suceso("muchaAgua", "pocaAgua"));
		this.manejadorSucesos.notificar();
		assertEquals(false, this.bomba.isEncendida());
		
		this.bomba.setEncendida(false);
		this.manejadorSucesos.agregarSuceso(sucesoPocaAgua);
		this.manejadorSucesos.agregarSuceso(sucesoPresionAlta);
		this.manejadorSucesos.agregarSuceso(new Suceso("presionBaja", "presionAlta"));
		this.manejadorSucesos.notificar();
		assertEquals(true, this.bomba.isEncendida());
		
		List<Suceso> sucesosCanceladores = new ArrayList<Suceso>();
		sucesosCanceladores.add(new Suceso("muchaAgua", "pocaAgua"));
		sucesosCanceladores.add(new Suceso("presionBaja", "presionAlta"));
		sucesosCanceladores.add(new Suceso("bajaTemperatura", "altaTemperatura"));
		
		this.bomba.setEncendida(false);
		this.manejadorSucesos.agregarSuceso(sucesoPocaAgua);
		this.manejadorSucesos.agregarSuceso(sucesoPresionAlta);
		this.manejadorSucesos.agregarSucesos(sucesosCanceladores);
		this.manejadorSucesos.notificar();
		assertEquals(false, this.bomba.isEncendida());
		
		this.bomba.setEncendida(false);
		this.manejadorSucesos.agregarSuceso(sucesoPocaAgua);
		this.manejadorSucesos.agregarSuceso(sucesoPresionAlta);
		this.manejadorSucesos.notificar(sucesosCanceladores);
		assertEquals(false, this.bomba.isEncendida());
		
		sucesosCanceladores.remove(new Suceso("muchaAgua", "pocaAgua"));
		this.bomba.setEncendida(false);
		this.manejadorSucesos.agregarSuceso(sucesoPocaAgua);
		this.manejadorSucesos.agregarSuceso(sucesoPresionAlta);
		this.manejadorSucesos.notificar(sucesosCanceladores);
		assertEquals(true, this.bomba.isEncendida());
		
		sucesosCanceladores.add(new Suceso("muchaAgua", "pocaAgua"));
		this.bomba.setEncendida(false);
		this.manejadorSucesos.agregarSuceso(sucesoPocaAgua);
		this.manejadorSucesos.agregarSuceso(sucesoPresionAlta);
		this.manejadorSucesos.notificar(sucesosCanceladores);
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
		this.manejadorSucesos.notificar();
		assertEquals(false, this.bomba.isEncendida());
		
		this.manejadorSucesos.deshabilitarCancelador();
		this.bomba.setEncendida(false);
		this.manejadorSucesos.agregarSuceso(sucesoPocaAgua);
		this.manejadorSucesos.agregarSuceso(sucesoPresionAlta);
		this.manejadorSucesos.agregarSuceso(new Suceso("muchaAgua", "pocaAgua"));
		this.manejadorSucesos.notificar();
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
		this.manejadorSucesos.notificar();
		assertEquals(true, this.bomba.isEncendida());
		
		this.manejadorSucesos.establecerCanceladorTotal();
		this.bomba.setEncendida(false);
		this.manejadorSucesos.agregarSuceso(sucesoPocaAgua);
		this.manejadorSucesos.agregarSuceso(sucesoPocaAgua);
		this.manejadorSucesos.agregarSuceso(sucesoPresionAlta);
		this.manejadorSucesos.agregarSuceso(new Suceso("muchaAgua", "pocaAgua"));
		this.manejadorSucesos.notificar();
		assertEquals(false, this.bomba.isEncendida());
		
	}
	
}
