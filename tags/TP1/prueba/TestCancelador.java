package prueba;

import junit.framework.TestCase;
import source.Suceso;

/**
 * Test de funcionamiento del cancelador.
 * @author Dario
 *
 */
public class TestCancelador extends TestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();		
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
					
}
