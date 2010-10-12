package prueba;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import source.CanceladorTotal;
import source.Suceso;

/**
 * Test de funcionamiento del cancelador total.
 * 
 * @author Grupo20
 *
 */
public class TestCanceladorTotal extends TestCase {
	
	private CanceladorTotal cancelador;
	
	private List<Suceso> sucesosExistentes;	
	
	private List<Suceso> sucesosNuevos;
	
	private List<Suceso> sucesosResultantes;
	
	private Suceso suceso;
	
		
	protected void setUp() throws Exception {
		super.setUp();
		this.cancelador = CanceladorTotal.obtenerInstancia();
		this.sucesosExistentes = new ArrayList<Suceso>();
		this.sucesosNuevos = new ArrayList<Suceso>();
		this.sucesosResultantes = new ArrayList<Suceso>();
	}
	
	public void testCancelarSuceso(){
		
		sucesosExistentes.clear();
		sucesosNuevos.clear();
		
		this.suceso = new Suceso("pocaAgua");
		sucesosExistentes.add(new Suceso("pocaAgua","muchaAgua"));
		sucesosExistentes.add(new Suceso("muchaPresion","pocaPresion"));
		sucesosExistentes.add(new Suceso("muchaAgua","pocaAgua"));
		sucesosExistentes.add(new Suceso("pocaAgua","muchaAgua"));
				
		cancelador.cancelarSuceso(sucesosExistentes, suceso);
		
		sucesosResultantes.add(new Suceso("pocaAgua","muchaAgua"));
		sucesosResultantes.add(new Suceso("muchaPresion","pocaPresion"));
		sucesosResultantes.add(new Suceso("pocaAgua","muchaAgua"));
				
		assertEquals(sucesosResultantes, sucesosExistentes);
		
		sucesosExistentes.clear();
		sucesosExistentes.add(new Suceso("pocaAgua","muchaAgua"));
		sucesosExistentes.add(new Suceso("muchaPresion","pocaPresion"));
		sucesosExistentes.add(new Suceso("muchaAgua","pocaAgua"));
		sucesosExistentes.add(new Suceso("muchaAgua","pocaAgua"));
		sucesosExistentes.add(new Suceso("pocaAgua","muchaAgua"));
		
		cancelador.cancelarSuceso(sucesosExistentes, suceso);
		
		sucesosResultantes.clear();
		sucesosResultantes.add(new Suceso("pocaAgua","muchaAgua"));
		sucesosResultantes.add(new Suceso("muchaPresion","pocaPresion"));
		sucesosResultantes.add(new Suceso("pocaAgua","muchaAgua"));
		
		assertEquals(sucesosResultantes, sucesosExistentes);
				
		sucesosExistentes.clear();
		sucesosExistentes.add(new Suceso("pocaAgua","muchaAgua"));
		sucesosExistentes.add(new Suceso("muchaPresion","pocaPresion"));
		sucesosExistentes.add(new Suceso("muchaAgua","pocaAgua"));
		sucesosExistentes.add(new Suceso("muchaAgua","pocaAgua"));
		sucesosExistentes.add(new Suceso("pocaAgua","muchaAgua"));
		sucesosExistentes.add(new Suceso("muchaAgua"));
		
		cancelador.cancelarSuceso(sucesosExistentes, new Suceso("pocaTemperatura"));
		
		assertEquals(sucesosExistentes, sucesosExistentes);
		
		sucesosExistentes.clear();
		sucesosExistentes.add(new Suceso("pocaAgua","muchaAgua"));
		sucesosExistentes.add(new Suceso("muchaPresion","pocaPresion"));
		sucesosExistentes.add(new Suceso("muchaAgua","pocaAgua"));
		sucesosExistentes.add(new Suceso("pocaAgua","muchaAgua"));
		sucesosExistentes.add(new Suceso("muchaPresion","pocaPresion"));
		sucesosExistentes.add(new Suceso("muchaAgua"));
		sucesosExistentes.add(new Suceso("pocaTemperatura","pocaPresion"));
		
		cancelador.cancelarSuceso(sucesosExistentes, new Suceso("pocaPresion","pocaTemperatura"));
		
		sucesosResultantes.clear();
		sucesosResultantes.add(new Suceso("pocaAgua","muchaAgua"));
		sucesosResultantes.add(new Suceso("muchaAgua","pocaAgua"));
		sucesosResultantes.add(new Suceso("pocaAgua","muchaAgua"));
		sucesosResultantes.add(new Suceso("muchaAgua"));
		
		assertEquals(sucesosResultantes, sucesosExistentes);
	}
		
	public void testCancelarSucesos(){
		
		sucesosExistentes.add(new Suceso("pocaAgua","muchaAgua"));
		sucesosExistentes.add(new Suceso("muchaPresion","pocaPresion"));
		sucesosExistentes.add(new Suceso("muchaAgua","pocaAgua"));
		sucesosExistentes.add(new Suceso("pocaAgua","muchaAgua"));
		sucesosExistentes.add(new Suceso("muchaAgua"));
		
		sucesosNuevos.add(new Suceso("muchaAgua"));
		sucesosNuevos.add(new Suceso("muchaPresion","pocaPresion"));
		sucesosNuevos.add(new Suceso("muchaAgua","pocaAgua"));
		
		cancelador.cancelarSucesos(sucesosExistentes, sucesosNuevos);
		
		sucesosResultantes.add(new Suceso("muchaPresion","pocaPresion"));
		sucesosResultantes.add(new Suceso("muchaAgua","pocaAgua"));
		sucesosResultantes.add(new Suceso("muchaAgua"));
		
		assertEquals(sucesosResultantes, sucesosExistentes);
		
		sucesosExistentes.clear();
		sucesosNuevos.clear();
		sucesosExistentes.add(new Suceso("pocaAgua","muchaAgua"));
		sucesosExistentes.add(new Suceso("muchaPresion","pocaPresion"));
		sucesosExistentes.add(new Suceso("muchaAgua","pocaAgua"));
		sucesosExistentes.add(new Suceso("muchaAgua","pocaAgua"));
		sucesosExistentes.add(new Suceso("pocaAgua","muchaAgua"));
		sucesosExistentes.add(new Suceso("pocaPresion","muchaPresion"));
		sucesosExistentes.add(new Suceso("muchaAgua"));
		
		sucesosNuevos.add(new Suceso("muchaAgua"));
		sucesosNuevos.add(new Suceso("muchaPresion","pocaPresion"));
		sucesosNuevos.add(new Suceso("pocaAgua","muchaAgua"));
		
		cancelador.cancelarSucesos(sucesosExistentes, sucesosNuevos);
		
		sucesosResultantes.clear();
		sucesosResultantes.add(new Suceso("muchaPresion","pocaPresion"));
		
		assertEquals(sucesosResultantes, sucesosExistentes);
				
		sucesosExistentes.clear();
		sucesosNuevos.clear();
		sucesosExistentes.add(new Suceso("pocaAgua","muchaAgua"));
		sucesosExistentes.add(new Suceso("muchaPresion","pocaPresion"));
		sucesosExistentes.add(new Suceso("muchaAgua","pocaAgua"));
		sucesosExistentes.add(new Suceso("muchaAgua","pocaAgua"));
		sucesosExistentes.add(new Suceso("pocaAgua","muchaAgua"));
		sucesosExistentes.add(new Suceso("muchaAgua"));
		
		sucesosNuevos.add(new Suceso("bajaTemperatura"));
		sucesosNuevos.add(new Suceso("altaTemperatura","bajaTemperatura"));
		sucesosNuevos.add(new Suceso("pocaLuz","muchaLuz"));
		
		cancelador.cancelarSucesos(sucesosExistentes, sucesosNuevos);
		
		assertEquals(sucesosExistentes, sucesosExistentes);
		
		sucesosExistentes.clear();
		sucesosNuevos.clear();
		sucesosExistentes.add(new Suceso("pocaAgua","muchaAgua"));
		sucesosExistentes.add(new Suceso("muchaPresion","pocaPresion"));
		sucesosExistentes.add(new Suceso("muchaAgua","pocaAgua"));
		sucesosExistentes.add(new Suceso("muchaAgua","pocaAgua"));
		sucesosExistentes.add(new Suceso("pocaAgua","muchaAgua"));
				
		sucesosNuevos.add(new Suceso("bajaTemperatura"));
		sucesosNuevos.add(new Suceso("pocaPresion","bajaTemperatura"));
		sucesosNuevos.add(new Suceso("pocaAgua","muchaLuz"));
		
		cancelador.cancelarSucesos(sucesosExistentes, sucesosNuevos);
		
		sucesosResultantes.clear();
		sucesosResultantes.add(new Suceso("pocaAgua","muchaAgua"));
		sucesosResultantes.add(new Suceso("pocaAgua","muchaAgua"));
						
		assertEquals(sucesosResultantes, sucesosExistentes);
	}
		

}
