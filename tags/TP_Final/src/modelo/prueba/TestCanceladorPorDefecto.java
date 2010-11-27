package modelo.prueba;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import modelo.manejadorDeSucesos.CanceladorPorDefecto;
import modelo.manejadorDeSucesos.Suceso;

/**
 * Test de funcionamiento del cancelador por defecto.
 * 
 * @author Grupo20
 *
 */
public class TestCanceladorPorDefecto extends TestCase {
			
	public void testCancelacionValidaDeSuceso(){
		CanceladorPorDefecto cancelador = CanceladorPorDefecto.obtenerInstancia();
		Suceso sucesoCancelador = new Suceso("pocaAgua","muchaPresion");
		List<Suceso> sucesosExistentes = new ArrayList<Suceso>();
		List<Suceso> sucesosResultantes = new ArrayList<Suceso>();
		sucesosExistentes.add(new Suceso("pocaAgua","muchaAgua"));
		sucesosExistentes.add(new Suceso("muchaPresion","pocaPresion"));
		sucesosExistentes.add(new Suceso("muchaAgua"));
		
		cancelador.cancelarSuceso(sucesosExistentes, sucesoCancelador);
		sucesosResultantes.add(new Suceso("pocaAgua","muchaAgua"));
		sucesosResultantes.add(new Suceso("muchaAgua"));
					
		assertEquals(sucesosResultantes, sucesosExistentes);
	}
	
	public void testCancelacionValidaUnitariaDeSucesos(){
		CanceladorPorDefecto cancelador = CanceladorPorDefecto.obtenerInstancia();
		Suceso sucesoCancelador = new Suceso("pocaAgua","muchaPresion");
		List<Suceso> sucesosExistentes = new ArrayList<Suceso>();
		List<Suceso> sucesosResultantes = new ArrayList<Suceso>();
		sucesosExistentes.add(new Suceso("muchaPresion","muchaAgua"));
		sucesosExistentes.add(new Suceso("muchaPresion","pocaPresion"));
		sucesosExistentes.add(new Suceso("muchaPresion","muchaTemperatura"));
				
		cancelador.cancelarSuceso(sucesosExistentes, sucesoCancelador);
		sucesosResultantes.add(new Suceso("muchaPresion","pocaPresion"));
		sucesosResultantes.add(new Suceso("muchaPresion","muchaTemperatura"));
							
		assertEquals(sucesosResultantes, sucesosExistentes);
	}
	
	
	public void testCancelacionFallidaDeSucesoPorIdCanceladorNoEncontrado(){
		CanceladorPorDefecto cancelador = CanceladorPorDefecto.obtenerInstancia();
		Suceso sucesoCancelador = new Suceso("pocaAgua","muchaTemperatura");
		List<Suceso> sucesosExistentes = new ArrayList<Suceso>();
		List<Suceso> sucesosResultantes = new ArrayList<Suceso>();
		sucesosExistentes.add(new Suceso("pocaAgua","muchaAgua"));
		sucesosExistentes.add(new Suceso("muchaPresion","pocaPresion"));
		sucesosExistentes.add(new Suceso("muchaAgua"));
		
		cancelador.cancelarSuceso(sucesosExistentes, sucesoCancelador);
		sucesosResultantes.add(new Suceso("pocaAgua","muchaAgua"));
		sucesosResultantes.add(new Suceso("muchaPresion","pocaPresion"));
		sucesosResultantes.add(new Suceso("muchaAgua"));
					
		assertEquals(sucesosResultantes, sucesosExistentes);
	}	
	
	public void testCancelacionFallidaDeSucesoPorIdCanceladorInexistente(){
		CanceladorPorDefecto cancelador = CanceladorPorDefecto.obtenerInstancia();
		Suceso sucesoCancelador = new Suceso("pocaAgua");
		List<Suceso> sucesosExistentes = new ArrayList<Suceso>();
		List<Suceso> sucesosResultantes = new ArrayList<Suceso>();
		sucesosExistentes.add(new Suceso("pocaAgua","muchaAgua"));
		sucesosExistentes.add(new Suceso("muchaPresion","pocaPresion"));
		sucesosExistentes.add(new Suceso("muchaPresion","pocaAgua"));
		sucesosExistentes.add(new Suceso("muchaAgua"));
		
		cancelador.cancelarSuceso(sucesosExistentes, sucesoCancelador);
		sucesosResultantes.add(new Suceso("pocaAgua","muchaAgua"));
		sucesosResultantes.add(new Suceso("muchaPresion","pocaPresion"));
		sucesosResultantes.add(new Suceso("muchaPresion","pocaAgua"));
		sucesosResultantes.add(new Suceso("muchaAgua"));
					
		assertEquals(sucesosResultantes, sucesosExistentes);
	}	
	
					
	public void testCancelarMultiplesSucesosValidos(){
		CanceladorPorDefecto cancelador = CanceladorPorDefecto.obtenerInstancia();
		List<Suceso> sucesosExistentes = new ArrayList<Suceso>();
		List<Suceso> sucesosNuevosCanceladores = new ArrayList<Suceso>();
		List<Suceso> sucesosResultantes = new ArrayList<Suceso>();
		sucesosExistentes.add(new Suceso("pocaAgua"));
		sucesosExistentes.add(new Suceso("muchaPresion"));
		sucesosExistentes.add(new Suceso("muchaAgua"));
		sucesosExistentes.add(new Suceso("pocaPresion"));
		sucesosExistentes.add(new Suceso("muchaTemperatura"));
		sucesosNuevosCanceladores.add(new Suceso("muchaAgua"));
		sucesosNuevosCanceladores.add(new Suceso("muchaPresion","pocaPresion"));
		sucesosNuevosCanceladores.add(new Suceso("muchaAgua","pocaAgua"));
	
		cancelador.cancelarSucesos(sucesosExistentes, sucesosNuevosCanceladores);
		sucesosResultantes.add(new Suceso("muchaPresion"));
		sucesosResultantes.add(new Suceso("muchaAgua"));
		sucesosResultantes.add(new Suceso("muchaTemperatura"));
		
		assertEquals(sucesosResultantes, sucesosExistentes);
	}
	
	public void testCancelacionFallidaDeMultiplesSucesosPorIdCanceladorInexistenteONoEncontrado(){
		CanceladorPorDefecto cancelador = CanceladorPorDefecto.obtenerInstancia();
		List<Suceso> sucesosExistentes = new ArrayList<Suceso>();
		List<Suceso> sucesosNuevosCanceladores = new ArrayList<Suceso>();
		List<Suceso> sucesosResultantes = new ArrayList<Suceso>();
		sucesosExistentes.add(new Suceso("pocaAgua","muchaAgua"));
		sucesosExistentes.add(new Suceso("muchaPresion","pocaPresion"));
		sucesosExistentes.add(new Suceso("muchaAgua","pocaAgua"));
		sucesosExistentes.add(new Suceso("muchaAgua","pocaAgua"));
		sucesosExistentes.add(new Suceso("pocaAgua","muchaAgua"));
		sucesosExistentes.add(new Suceso("muchaAgua"));
		sucesosNuevosCanceladores.add(new Suceso("muchaAgua"));
		sucesosNuevosCanceladores.add(new Suceso("muchaPresion","pocaPresion"));
		sucesosNuevosCanceladores.add(new Suceso("pocaAgua","muchaTemperatura"));
		
		cancelador.cancelarSucesos(sucesosExistentes, sucesosNuevosCanceladores);
		sucesosResultantes.add(new Suceso("pocaAgua","muchaAgua"));
		sucesosResultantes.add(new Suceso("muchaPresion","pocaPresion"));
		sucesosResultantes.add(new Suceso("muchaAgua","pocaAgua"));
		sucesosResultantes.add(new Suceso("muchaAgua","pocaAgua"));
		sucesosResultantes.add(new Suceso("pocaAgua","muchaAgua"));
		sucesosResultantes.add(new Suceso("muchaAgua"));
		
		assertEquals(sucesosResultantes, sucesosExistentes);
	}
		
}
