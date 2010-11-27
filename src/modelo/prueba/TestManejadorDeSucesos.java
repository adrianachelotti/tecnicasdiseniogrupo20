package modelo.prueba;

import java.util.List;

import junit.framework.TestCase;
import modelo.accion.AccionPrenderDispositivo;
import modelo.cliente.driver.dispositivos.CalefactorDriver;
import modelo.edificio.Dispositivo;
import modelo.manejadorDeSucesos.ManejadorDeSucesos;
import modelo.manejadorDeSucesos.Suceso;

/**
 * Test de funcionamiento del Manejador de Sucesos.
 * @author Grupo20
 *
 */

public class TestManejadorDeSucesos extends  TestCase {

	
	public void testSuscribirImplicacionYNotificarSucesoUnicoValido(){
		// Se crea el manejador de sucesos y la accion para encender el calefactor	
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		AccionPrenderDispositivo accion = new AccionPrenderDispositivo();
		Dispositivo dispositivo = new Dispositivo(new CalefactorDriver());
		accion.establecerDispositivo(dispositivo);
		
		
		//Se suscribe al manejador la implicacion de que si hay "TEMPERATURA_BAJA" 
		//se tiene que encender el calefactor y se notifica al manejador
		//que ha ocurrido "TEMPERATURA_BAJA"
		manejadorSucesos.suscribirImplicacion(accion, new Suceso("TEMPERATURA_BAJA"));
		//Se evalua que el dispositivo se encuentra inicialmente apagado.
		assertEquals(false, dispositivo.isEncendido());
		manejadorSucesos.agregarSuceso(new Suceso("TEMPERATURA_BAJA"));
		
		// Se prueba que el dispositivo se ha encendido luego de la notificacion
		assertEquals(true,dispositivo.isEncendido());
	}
	
	public void testSuscribirImplicacionYNotificarSucesoInvalido(){
		// Se crea el manejador de sucesos y la accion para encender el calefactor	
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		AccionPrenderDispositivo accion = new AccionPrenderDispositivo();
		Dispositivo dispositivo = new Dispositivo(new CalefactorDriver());
		accion.establecerDispositivo(dispositivo);
		
		//Se suscribe al manejador la implicacion de que si hay "TEMPERATURA_BAJA" 
		//se tiene que encender el calefactor y se notifica al manejador
		//que ha ocurrido "TEMPERATURA_ALTA"
		manejadorSucesos.suscribirImplicacion(accion, new Suceso("TEMPERATURA_BAJA"));
		//Se evalua que el dispositivo se encuentra inicialmente apagado.
		assertEquals(false,dispositivo.isEncendido());		
		manejadorSucesos.agregarSuceso(new Suceso("TEMPERATURA_ALTA"));
		
		// Se prueba que el dispositivo no se ha encendido luego de la notificacion
		assertEquals(false, dispositivo.isEncendido());
	}
	
	public void testSuscribirImplicacionYNotificarMultiplesSucesos(){
		// Se crea el manejador de sucesos y la accion para encender el calefactor	
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		AccionPrenderDispositivo accion = new AccionPrenderDispositivo();
		Dispositivo dispositivo = new Dispositivo(new CalefactorDriver());
		accion.establecerDispositivo(dispositivo);
				
		//Se suscribe al manejador la implicacion de que si hay "TEMPERATURA_MEDIA TEMPERATURA_BAJA" 
		//se tiene que encender el calefactor y se notifica al manejador
		//que han ocurrido "TEMPERATURA_ALTA" y luego "TEMPERATURA_BAJA"
		List<Suceso> sucesos = GeneradorDeSuceso.obtenerSucesos("TEMPERATURA_MEDIA TEMPERATURA_BAJA");
		manejadorSucesos.suscribirImplicacion(accion, sucesos);
		//Se evalua que el dispositivo se encuentra inicialmente apagado.
		assertEquals(false, dispositivo.isEncendido());
		manejadorSucesos.agregarSuceso(new Suceso("TEMPERATURA_MEDIA"));
		
		//Se prueba que el dispositivo no se ha encendido luego de la notificacion
		assertEquals(false, dispositivo.isEncendido());
		
		//Se agrega un suceso que cumple con la implicacion
		manejadorSucesos.agregarSuceso(new Suceso("TEMPERATURA_BAJA"));
		
		//Se prueba que el dispositivo se ha encendido luego de la notificacion
		assertEquals(true, dispositivo.isEncendido());
	}
	
		 
	 /**
	  * Se testea la suscripcion de implicaciones y la notificacion de varios suceos
	  *	ocurridos 
	  */
	public void testSuscribirImplicacionYNotificarMultiplesSucesosInvalidos(){
		// Se crea el manejador de sucesos y la accion para encender el calefactor	
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		AccionPrenderDispositivo accion = new AccionPrenderDispositivo();
		Dispositivo dispositivo = new Dispositivo(new CalefactorDriver());
		accion.establecerDispositivo(dispositivo);
		
		//Se suscribe al manejador la implicacion de que si hay "TEMPERATURA_BAJA HELADA" 
		//se tiene que encender el calefactor y se notifica al manejador
		//que han ocurrido "CALOR" y luego	"TEMPERATURA_ALTA"
		List<Suceso> sucesos = GeneradorDeSuceso.obtenerSucesos("TEMPERATURA_BAJA HELADA");		
		manejadorSucesos.suscribirImplicacion(accion, sucesos);
		//Se evalua que el dispositivo se encuentra inicialmente apagado.
		assertEquals(false, dispositivo.isEncendido());	
		List<Suceso> sucesosNuevos = GeneradorDeSuceso.obtenerSucesos("CALOR TEMPERATURA_ALTA");
		manejadorSucesos.agregarSucesos(sucesosNuevos);
		
		//Se prueba que el dispositivo se ha encendido luego de la notificacion
		assertEquals(false, dispositivo.isEncendido());
	}
			 	 
	/**
	 * CASO 1: Implicacion con un solo suceso como antecedente.	 
	 */
	public void testNotificarUnicoSuceso(){
		// Se crea el manejador de sucesos y la accion para encender el calefactor
		// Se establece el manejador en modo discontinuo
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		AccionPrenderDispositivo accion = new AccionPrenderDispositivo();
		Dispositivo dispositivo = new Dispositivo(new CalefactorDriver());
		accion.establecerDispositivo(dispositivo);
		manejadorSucesos.obtenerConfiguracion().establecerEvaluadorDiscontinuo();
		List<Suceso> lista = GeneradorDeSuceso.obtenerSucesos("A");
		
		// Se notifica varios sucesos dentro de los cuales uno es valido.		
		dispositivo.apagar();
		manejadorSucesos.suscribirImplicacion(accion, lista);
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("X Y Z A"));
		
		//Se prueba que la implicacion es valida y se prende el dispositivo
		assertEquals(dispositivo.isEncendido(),true);	
	}
	
	/**
	 * CASO 2: Configuracion Discontinua sin Cancelaciones.	 
	 */
	public void testSucesosContinuosOrdenadosConfiguracionDiscontinuaNoCancelaciones(){
		// Se crea el manejador de sucesos y la accion para encender el calefactor
		// Se establece el manejador en modo discontinuo sin cancelaciones.
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		AccionPrenderDispositivo accion = new AccionPrenderDispositivo();
		Dispositivo dispositivo = new Dispositivo(new CalefactorDriver());
		accion.establecerDispositivo(dispositivo);		
	    manejadorSucesos.obtenerConfiguracion().establecerEvaluadorDiscontinuo();
	    List<Suceso> lista = GeneradorDeSuceso.obtenerSucesos("A B");
	    
	    //Se suscribe una implicacion y se notifica una secuencia de sucesos continuos ordenados.
		//Esta ultima contiene a la implicacion y debe encender el dispositivo.
		manejadorSucesos.suscribirImplicacion(accion, lista);
		dispositivo.apagar();
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("X Y Z A B"));
		
		//Se evalua que la implicacion es valida y que se prende el dispositivo.
		assertEquals(dispositivo.isEncendido(),true);		
	}
	
	public void testSucesosContinuosDesordenadosConfiguracionDiscontinuaNoCancelaciones(){
		// Se crea el manejador de sucesos y la accion para encender el calefactor
		// Se establece el manejador en modo discontinuo sin cancelaciones.
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		AccionPrenderDispositivo accion = new AccionPrenderDispositivo();
		Dispositivo dispositivo = new Dispositivo(new CalefactorDriver());
		accion.establecerDispositivo(dispositivo);			
	    manejadorSucesos.obtenerConfiguracion().establecerEvaluadorDiscontinuo();
		
	    //Se suscribe una implicacion y se notifica una secuencia de sucesos continuos desordenados.
		//Esta ultima contiene a la implicacion y debe encender el dispositivo.
	    manejadorSucesos.suscribirImplicacion(accion, GeneradorDeSuceso.obtenerSucesos("A B"));
		dispositivo.apagar();
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("X Y Z B A"));
		
		//Se evalua que la implicacion es valida y que se prende el dispositivo.
		assertEquals(dispositivo.isEncendido(),true);		
	}
	
	public void testSucesosDiscontinuosOrdenadosConfiguracionDiscontinuaNoCancelaciones(){
		// Se crea el manejador de sucesos y la accion para encender el calefactor
		// Se establece el manejador en modo discontinuo sin cancelaciones.
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		AccionPrenderDispositivo accion = new AccionPrenderDispositivo();
		Dispositivo dispositivo = new Dispositivo(new CalefactorDriver());
		accion.establecerDispositivo(dispositivo);			
		manejadorSucesos.obtenerConfiguracion().establecerEvaluadorDiscontinuo();	
		
		//Se suscribe una implicacion y se notifica una secuencia de sucesos discontinuos ordenados.
		//Esta ultima contiene a la implicacion y debe encender el dispositivo.
		manejadorSucesos.suscribirImplicacion(accion, GeneradorDeSuceso.obtenerSucesos("A B"));
		dispositivo.apagar();
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("X Y Z A K;A B"));
		
		//Se evalua que la implicacion es valida y que se prende el dispositivo.
		assertEquals(dispositivo.isEncendido(),true);		
	}
	
	public void testSucesosDiscontinuosDesordenadosConfiguracionDiscontinuaNoCancelaciones(){
		// Se crea el manejador de sucesos y la accion para encender el calefactor
		// Se establece el manejador en modo discontinuo sin cancelaciones.
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		AccionPrenderDispositivo accion = new AccionPrenderDispositivo();
		Dispositivo dispositivo = new Dispositivo(new CalefactorDriver());
		accion.establecerDispositivo(dispositivo);
		manejadorSucesos.obtenerConfiguracion().establecerEvaluadorDiscontinuo();
		
		//Se suscribe una implicacion y se notifica una secuencia de sucesos discontinuos desordenados.
		//Esta ultima contiene a la implicacion y debe encender el dispositivo.
	    manejadorSucesos.suscribirImplicacion(accion, GeneradorDeSuceso.obtenerSucesos("A B"));
		dispositivo.apagar();
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("X Y Z B K;A A"));
		
		//Se prueba que la implicacion es valida y se prende el dispositivo
		assertEquals(dispositivo.isEncendido(),true);		
	}
	
	/**
	 * CASO 3: Configuracion Continua sin Cancelaciones.	 
	 */
	public void testSucesosContinuosOrdenadosConfiguracionContinuaNoCancelaciones(){
		// Se crea el manejador de sucesos y la accion para encender el calefactor
		// Se establece el manejador en modo continuo sin cancelaciones.
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		AccionPrenderDispositivo accion = new AccionPrenderDispositivo();
		Dispositivo dispositivo = new Dispositivo(new CalefactorDriver());
		accion.establecerDispositivo(dispositivo);		
	    manejadorSucesos.obtenerConfiguracion().establecerEvaluadorContinuo();
	    
	    //Se suscribe una implicacion y se notifica una secuencia de sucesos continuos ordenados.
		//Esta ultima contiene a la implicacion y debe encender el dispositivo.	    	    
		manejadorSucesos.suscribirImplicacion(accion, GeneradorDeSuceso.obtenerSucesos("A B"));
		dispositivo.apagar();
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("X Y Z A B"));
		
		//Se prueba que la implicacion es valida y se prende el dispositivo
		assertEquals(dispositivo.isEncendido(),true);		
	}
	
	public void testSucesosContinuosDesordenadosConfiguracionContinuaNoCancelaciones(){
		// Se crea el manejador de sucesos y la accion para encender el calefactor
		// Se establece el manejador en modo continuo sin cancelaciones.
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		AccionPrenderDispositivo accion = new AccionPrenderDispositivo();
		Dispositivo dispositivo = new Dispositivo(new CalefactorDriver());
		accion.establecerDispositivo(dispositivo);		
	    manejadorSucesos.obtenerConfiguracion().establecerEvaluadorContinuo();
	    
	    //Se suscribe una implicacion y se notifica una secuencia de sucesos continuos desordenados.
		//Esta ultima contiene a la implicacion y debe encender el dispositivo.	    	    
		manejadorSucesos.suscribirImplicacion(accion, GeneradorDeSuceso.obtenerSucesos("A B"));
		dispositivo.apagar();
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("X Y Z B A"));

		//Se prueba que la implicacion es valida y se prende el dispositivo
		assertEquals(dispositivo.isEncendido(),true);		
	}
	
	public void testSucesosDiscontinuosOrdenadosConfiguracionContinuaNoCancelaciones(){
		// Se crea el manejador de sucesos y la accion para encender el calefactor
		// Se establece el manejador en modo continuo sin cancelaciones.
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		AccionPrenderDispositivo accion = new AccionPrenderDispositivo();
		Dispositivo dispositivo = new Dispositivo(new CalefactorDriver());
		accion.establecerDispositivo(dispositivo);		
	    manejadorSucesos.obtenerConfiguracion().establecerEvaluadorContinuo();
	
	    //Se suscribe una implicacion y se notifica una secuencia de sucesos discontinuos ordenados.
		//Esta ultima contiene a la implicacion pero no en el orden necesario, el dispositivo se debe mantener apagado.	    	    
		manejadorSucesos.suscribirImplicacion(accion, GeneradorDeSuceso.obtenerSucesos("A B"));
		dispositivo.apagar();
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("X Y Z A K;A B"));

		//Se prueba que la implicacion no es valida y el dispositivo se mantiene apagado.
		assertEquals(dispositivo.isEncendido(),false);		
	}
	
	public void testSucesosDisontinuosDesordenadosConfiguracionContinuaNoCancelaciones(){
		// Se crea el manejador de sucesos y la accion para encender el calefactor
		// Se establece el manejador en modo continuo sin cancelaciones.
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		AccionPrenderDispositivo accion = new AccionPrenderDispositivo();
		Dispositivo dispositivo = new Dispositivo(new CalefactorDriver());
		accion.establecerDispositivo(dispositivo);		
	    manejadorSucesos.obtenerConfiguracion().establecerEvaluadorContinuo();		    
	    
	    //Se suscribe una implicacion y se notifica una secuencia de sucesos discontinuos desordenados.
		//Esta ultima contiene a la implicacion pero no en el orden necesario, el dispositivo se debe mantener apagado.
		manejadorSucesos.suscribirImplicacion(accion, GeneradorDeSuceso.obtenerSucesos("A B"));
		dispositivo.apagar();
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("X Y Z B K;A A"));
		
		//Se prueba que la implicacion no es valida y el dispositivo se mantiene apagado.
		assertEquals(dispositivo.isEncendido(),false);		
	}
	
	/**
	 * CASO 4: Configuracion Secuencia Discontinua sin Cancelaciones.	 
	 */
	public void testSucesosContinuosOrdenadosConfiguracionSecuenciaDiscontinuaNoCancelaciones(){
		// Se crea el manejador de sucesos y la accion para encender el calefactor
		// Se establece el manejador en modo secuencia discontinua sin cancelaciones.
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		AccionPrenderDispositivo accion = new AccionPrenderDispositivo();
		Dispositivo dispositivo = new Dispositivo(new CalefactorDriver());
		accion.establecerDispositivo(dispositivo);		
		manejadorSucesos.obtenerConfiguracion().establecerEvaluadorSecuenciaDiscontinua();
		    
		//Se suscribe una implicacion y se notifica una secuencia de sucesos continuos ordenados.
		//Esta ultima contiene a la implicacion y debe encender el dispositivo.
		manejadorSucesos.suscribirImplicacion(accion, GeneradorDeSuceso.obtenerSucesos("A B"));
		dispositivo.apagar();
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("X Y Z A B"));
		
		//Se prueba que la implicacion es valida y se prende el dispositivo
		assertEquals(dispositivo.isEncendido(),true);		
	}
	
	public void testSucesosContinuosDesordenadosConfiguracionSecuenciaDiscontinuaNoCancelaciones(){
		// Se crea el manejador de sucesos y la accion para encender el calefactor
		// Se establece el manejador en modo secuencia discontinua sin cancelaciones.
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		AccionPrenderDispositivo accion = new AccionPrenderDispositivo();
		Dispositivo dispositivo = new Dispositivo(new CalefactorDriver());
		accion.establecerDispositivo(dispositivo);		
		manejadorSucesos.obtenerConfiguracion().establecerEvaluadorSecuenciaDiscontinua();		
			
		//Se suscribe una implicacion y se notifica una secuencia de sucesos continuos desordenados.
		//Esta ultima contiene a la implicacion pero no en el orden necesario, el dispositivo se debe mantener apagado.
		manejadorSucesos.suscribirImplicacion(accion, GeneradorDeSuceso.obtenerSucesos("A B"));
		dispositivo.apagar();
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("X Y Z B A"));
		
		//Se prueba que la implicacion no es valida y el dispositivo se mantiene apagado.
		assertEquals(dispositivo.isEncendido(),false);		
	}
		
	public void testSucesosDiscontinuosOrdenadosConfiguracionSecuenciaDiscontinuaNoCancelaciones(){
		// Se crea el manejador de sucesos y la accion para encender el calefactor
		// Se establece el manejador en modo secuencia discontinua sin cancelaciones.
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		AccionPrenderDispositivo accion = new AccionPrenderDispositivo();
		Dispositivo dispositivo = new Dispositivo(new CalefactorDriver());
		accion.establecerDispositivo(dispositivo);		
		manejadorSucesos.obtenerConfiguracion().establecerEvaluadorSecuenciaDiscontinua();		
		
		//Se suscribe una implicacion y se notifica una secuencia de sucesos discontinuos ordenados.
		//Esta ultima contiene a la implicacion y en el orden necesario, el dispositivo se debe encender.
		manejadorSucesos.suscribirImplicacion(accion, GeneradorDeSuceso.obtenerSucesos("A B"));
		dispositivo.apagar();
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("X Y Z A K;A B"));
		
		//Se prueba que la implicacion es valida y se prende el dispositivo
		assertEquals(dispositivo.isEncendido(),true);		
	}
	
	public void testSucesosDiscontinuosDesordenadosConfiguracionSecuenciaDiscontinuaNoCancelaciones(){
		// Se crea el manejador de sucesos y la accion para encender el calefactor
		// Se establece el manejador en modo secuencia discontinua sin cancelaciones.
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		AccionPrenderDispositivo accion = new AccionPrenderDispositivo();
		Dispositivo dispositivo = new Dispositivo(new CalefactorDriver());
		accion.establecerDispositivo(dispositivo);		
		manejadorSucesos.obtenerConfiguracion().establecerEvaluadorSecuenciaDiscontinua();		
		
		//Se suscribe una implicacion y se notifica una secuencia de sucesos discontinuos desordenados.
		//Esta ultima contiene a la implicacion pero no en el orden necesario, el dispositivo se debe mantener apagado.
		manejadorSucesos.suscribirImplicacion(accion, GeneradorDeSuceso.obtenerSucesos("A B"));
		dispositivo.apagar();
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("X Y Z B K;A A"));
	
		//Se prueba que la implicacion no es valida y el dispositivo se mantiene apagado.
		assertEquals(dispositivo.isEncendido(),false);		
	}
	
	/**
	 * CASO 5: Configuracion Secuencia Continua sin Cancelaciones.	 
	 */
	public void testSucesosContinuosOrdenadosConfiguracionSecuenciaContinuaNoCancelaciones(){
		// Se crea el manejador de sucesos y la accion para encender el calefactor
		// Se establece el manejador en modo secuencia continua sin cancelaciones.
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		AccionPrenderDispositivo accion = new AccionPrenderDispositivo();
		Dispositivo dispositivo = new Dispositivo(new CalefactorDriver());
		accion.establecerDispositivo(dispositivo);		
		manejadorSucesos.obtenerConfiguracion().establecerEvaluadorSecuenciaDiscontinua();		
		
		//Se suscribe una implicacion y se notifica una secuencia de sucesos continuos ordenados.
		//Esta ultima contiene a la implicacion y en el orden necesario, el dispositivo se debe encender.
		manejadorSucesos.suscribirImplicacion(accion, GeneradorDeSuceso.obtenerSucesos("A B"));
		dispositivo.apagar();
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("X Y Z A B"));
		
		//Se prueba que la implicacion es valida y se prende el dispositivo
		assertEquals(dispositivo.isEncendido(),true);		
	}
	
	
	public void testSucesosContinuosDesordenadosConfiguracionSecuenciaContinuaNoCancelaciones(){
		// Se crea el manejador de sucesos y la accion para encender el calefactor.
		// Se establece el manejador en modo secuencia continua sin cancelaciones.
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		AccionPrenderDispositivo accion = new AccionPrenderDispositivo();
		Dispositivo dispositivo = new Dispositivo(new CalefactorDriver());
		accion.establecerDispositivo(dispositivo);		
		manejadorSucesos.obtenerConfiguracion().establecerEvaluadorSecuenciaDiscontinua();		
		
		//Se suscribe una implicacion y se notifica una secuencia de sucesos continuos desordenados.
		//Esta ultima contiene a la implicacion pero no en el orden necesario, el dispositivo se debe mantener apagado.
		manejadorSucesos.suscribirImplicacion(accion, GeneradorDeSuceso.obtenerSucesos("A B"));
		dispositivo.apagar();
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("X Y Z B A"));
		
		//Se prueba que la implicacion no es valida y el dispositivo se mantiene apagado.
		assertEquals(dispositivo.isEncendido(),false);		
	}
	
	public void testSucesosDiscontinuosOrdenadosConfiguracionSecuenciaContinuaNoCancelaciones(){
		// Se crea el manejador de sucesos y la accion para encender el calefactor.
		// Se establece el manejador en modo secuencia continua sin cancelaciones.
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		AccionPrenderDispositivo accion = new AccionPrenderDispositivo();
		Dispositivo dispositivo = new Dispositivo(new CalefactorDriver());
		accion.establecerDispositivo(dispositivo);		
		manejadorSucesos.obtenerConfiguracion().establecerEvaluadorSecuenciaContinua();		
		
		//Se suscribe una implicacion y se notifica una secuencia de sucesos discontinuos ordenados.
		//Esta ultima contiene a la implicacion pero no en el orden necesario, el dispositivo se debe mantener apagado.
		manejadorSucesos.suscribirImplicacion(accion, GeneradorDeSuceso.obtenerSucesos("A B"));
		dispositivo.apagar();
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("X Y Z A K;A B"));
	
		//Se prueba que la implicacion no es valida y el dispositivo se mantiene apagado.
		assertEquals(dispositivo.isEncendido(),false);		
	}
		
	public void testSucesosDiscontinuosDesordenadosConfiguracionSecuenciaContinuaNoCancelaciones(){
		// Se crea el manejador de sucesos y la accion para encender el calefactor.
		// Se establece el manejador en modo secuencia continua sin cancelaciones.
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		AccionPrenderDispositivo accion = new AccionPrenderDispositivo();
		Dispositivo dispositivo = new Dispositivo(new CalefactorDriver());
		accion.establecerDispositivo(dispositivo);		
		manejadorSucesos.obtenerConfiguracion().establecerEvaluadorSecuenciaContinua();		
		
		//Se suscribe una implicacion y se notifica una secuencia de sucesos discontinuos desordenados.
		//Esta ultima contiene a la implicacion pero no en el orden necesario, el dispositivo se debe mantener apagado.
		manejadorSucesos.suscribirImplicacion(accion, GeneradorDeSuceso.obtenerSucesos("A B"));
		dispositivo.apagar();
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("X Y Z B K;A A"));

		//Se prueba que la implicacion no es valida y el dispositivo se mantiene apagado.
		assertEquals(dispositivo.isEncendido(),false);
	}
	
	/**
	 * CASO 6: Configuracion Discontinua con Cancelaciones.
	 */
	public void testSucesosContinuosOrdenadosConfiguracionDiscontinuaCancelaciones(){
		// Se crea el manejador de sucesos y la accion para encender el calefactor.
		// Se establece el manejador en modo discontinuo con cancelaciones.
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.obtenerConfiguracion().establecerEvaluadorDiscontinuo();
		manejadorSucesos.obtenerConfiguracion().habilitarCancelador();
		AccionPrenderDispositivo accion = new AccionPrenderDispositivo();
		Dispositivo dispositivo = new Dispositivo(new CalefactorDriver());
		accion.establecerDispositivo(dispositivo);
		
		//Se suscribe una implicacion y se notifica una secuencia de sucesos continuos ordenados.
		//Esta ultima contiene a la implicacion y en el orden necesario, el dispositivo se debe encender.
		manejadorSucesos.suscribirImplicacion(accion, GeneradorDeSuceso.obtenerSucesos("A B"));
		dispositivo.apagar();
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("X Y Z A B"));
		
		//Se prueba que la implicacion es valida y se prende el dispositivo
		assertEquals(dispositivo.isEncendido(),true);		
	}
	
	public void testSucesosContinuosDesordenadosConfiguracionDiscontinuaCancelaciones(){
		// Se crea el manejador de sucesos y la accion para encender el calefactor.
		// Se establece el manejador en modo discontinuo con cancelaciones.
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.obtenerConfiguracion().establecerEvaluadorDiscontinuo();
		manejadorSucesos.obtenerConfiguracion().habilitarCancelador();
		AccionPrenderDispositivo accion = new AccionPrenderDispositivo();
		Dispositivo dispositivo = new Dispositivo(new CalefactorDriver());
		accion.establecerDispositivo(dispositivo);
		
		//Se suscribe una implicacion y se notifica una secuencia de sucesos continuos desordenados.
		//Esta ultima contiene a la implicacion y el dispositivo se debe encender.
		manejadorSucesos.suscribirImplicacion(accion, GeneradorDeSuceso.obtenerSucesos("A B"));
		dispositivo.apagar();		
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("X Y Z B A"));
		
		//Se prueba que la implicacion es valida y se prende el dispositivo
		assertEquals(dispositivo.isEncendido(),true);		
	}
			
	public void testSucesosDiscontinuosOrdenadosConfiguracionDiscontinuaCancelaciones(){
		// Se crea el manejador de sucesos y la accion para encender el calefactor.
		// Se establece el manejador en modo discontinuo con cancelaciones.
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.obtenerConfiguracion().establecerEvaluadorDiscontinuo();
		manejadorSucesos.obtenerConfiguracion().habilitarCancelador();
		AccionPrenderDispositivo accion = new AccionPrenderDispositivo();
		Dispositivo dispositivo = new Dispositivo(new CalefactorDriver());
		accion.establecerDispositivo(dispositivo);
		
		//Se suscribe una implicacion y se notifica una secuencia de sucesos discontinuos ordenados.
		//Esta ultima no contiene a la implicacion debido a cancelaciones, el dispositivo se debe mantener apagado.
		manejadorSucesos.suscribirImplicacion(accion, GeneradorDeSuceso.obtenerSucesos("A B"));
		dispositivo.apagar();		
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("X Y Z A K;A B"));
		
		//Se prueba que la implicacion no es valida y el dispositivo se mantiene apagado.
		assertEquals(dispositivo.isEncendido(),false);		
	}
	
	public void testSucesosDiscontinuosDesordenadosConfiguracionDiscontinuaCancelaciones(){
		// Se crea el manejador de sucesos y la accion para encender el calefactor.
		// Se establece el manejador en modo discontinuo con cancelaciones.
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.obtenerConfiguracion().establecerEvaluadorDiscontinuo();
		manejadorSucesos.obtenerConfiguracion().habilitarCancelador();
		AccionPrenderDispositivo accion = new AccionPrenderDispositivo();
		Dispositivo dispositivo = new Dispositivo(new CalefactorDriver());
		accion.establecerDispositivo(dispositivo);
		
		//Se suscribe una implicacion y se notifica una secuencia de sucesos discontinuos desordenados.
		//Esta ultima contiene a la implicacion y el dispositivo se debe encender.
		manejadorSucesos.suscribirImplicacion(accion, GeneradorDeSuceso.obtenerSucesos("A B"));
		dispositivo.apagar();		
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("X Y Z B K;A A"));
		
		//Se prueba que la implicacion es valida y se prende el dispositivo
		assertEquals(dispositivo.isEncendido(),true);		
	}
	
	/**
	 * CASO 7: Configuracion Continua con Cancelaciones.
	 */
	public void testSucesosContinuosOrdenadosConfiguracionContinuaCancelaciones(){
		// Se crea el manejador de sucesos y la accion para encender el calefactor.
		// Se establece el manejador en modo continuo con cancelaciones.
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.obtenerConfiguracion().establecerEvaluadorContinuo();
		manejadorSucesos.obtenerConfiguracion().habilitarCancelador();
		AccionPrenderDispositivo accion = new AccionPrenderDispositivo();
		Dispositivo dispositivo = new Dispositivo(new CalefactorDriver());
		accion.establecerDispositivo(dispositivo);
		
		//Se suscribe una implicacion y se notifica una secuencia de sucesos continuos ordenados.
		//Esta ultima contiene a la implicacion y el dispositivo se debe encender.
		manejadorSucesos.suscribirImplicacion(accion, GeneradorDeSuceso.obtenerSucesos("A B"));
		dispositivo.apagar();		
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("X Y Z A B"));
		
		//Se prueba que la implicacion es valida y se prende el dispositivo
		assertEquals(dispositivo.isEncendido(),true);		
	}
	
	public void testSucesosDiscontinuosOrdenadosConfiguracionContinuaCancelaciones(){
		// Se crea el manejador de sucesos y la accion para encender el calefactor.
		// Se establece el manejador en modo continuo con cancelaciones.
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.obtenerConfiguracion().establecerEvaluadorContinuo();
		manejadorSucesos.obtenerConfiguracion().habilitarCancelador();
		AccionPrenderDispositivo accion = new AccionPrenderDispositivo();
		Dispositivo dispositivo = new Dispositivo(new CalefactorDriver());
		accion.establecerDispositivo(dispositivo);
		
		//Se suscribe una implicacion y se notifica una secuencia de sucesos discontinuos ordenados.
		//Esta ultima contiene a la implicacion pero no en el orden necesario, el dispositivo se debe mantener apagado.
		manejadorSucesos.suscribirImplicacion(accion, GeneradorDeSuceso.obtenerSucesos("A B"));
		dispositivo.apagar();		
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("X Y Z A F B"));
		
		//Se prueba que la implicacion no es valida y el dispositivo se mantiene apagado.
		assertEquals(dispositivo.isEncendido(),false);		
	}
			
	public void testSucesosContinuosDesordenadosConfiguracionContinuaCancelaciones(){
		// Se crea el manejador de sucesos y la accion para encender el calefactor.
		// Se establece el manejador en modo continuo con cancelaciones.
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.obtenerConfiguracion().establecerEvaluadorContinuo();
		manejadorSucesos.obtenerConfiguracion().habilitarCancelador();
		AccionPrenderDispositivo accion = new AccionPrenderDispositivo();
		Dispositivo dispositivo = new Dispositivo(new CalefactorDriver());
		accion.establecerDispositivo(dispositivo);
		
		//Se suscribe una implicacion y se notifica una secuencia de sucesos continuos desordenados.
		//Esta ultima contiene a la implicacion y el dispositivo se debe encender.
		manejadorSucesos.suscribirImplicacion(accion, GeneradorDeSuceso.obtenerSucesos("A B"));
		dispositivo.apagar();		
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("X Y Z B A"));
		
		//Se prueba que la implicacion es valida y se prende el dispositivo
		assertEquals(dispositivo.isEncendido(),true);		
	}
	
	public void testSucesosDiscontinuosConCanceladorOrdenadosConfiguracionContinuaCancelaciones(){
		// Se crea el manejador de sucesos y la accion para encender el calefactor.
		// Se establece el manejador en modo continuo con cancelaciones.
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.obtenerConfiguracion().establecerEvaluadorContinuo();
		manejadorSucesos.obtenerConfiguracion().habilitarCancelador();
		AccionPrenderDispositivo accion = new AccionPrenderDispositivo();
		Dispositivo dispositivo = new Dispositivo(new CalefactorDriver());
		accion.establecerDispositivo(dispositivo);
		
		//Se suscribe una implicacion y se notifica una secuencia de sucesos discontinuos.
		//Esta ultima no contiene a la implicacion, debido a cancelaciones, y el dispositivo se debe mantener apagado.
		manejadorSucesos.suscribirImplicacion(accion, GeneradorDeSuceso.obtenerSucesos("A B"));
		dispositivo.apagar();		
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("X Y Z A K;A B"));
		
		//Se prueba que la implicacion no es valida y el dispositivo se mantiene apagado.
		assertEquals(dispositivo.isEncendido(),false);		
	}
	
	public void testSucesosDiscontinuosConCanceladorDesordenadosConfiguracionContinuaCancelaciones(){
		// Se crea el manejador de sucesos y la accion para encender el calefactor.
		// Se establece el manejador en modo continuo con cancelaciones.
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.obtenerConfiguracion().establecerEvaluadorContinuo();
		manejadorSucesos.obtenerConfiguracion().habilitarCancelador();
		AccionPrenderDispositivo accion = new AccionPrenderDispositivo();
		Dispositivo dispositivo = new Dispositivo(new CalefactorDriver());
		accion.establecerDispositivo(dispositivo);
		
		//Se suscribe una implicacion y se notifica una secuencia de sucesos discontinuos.
		//Esta ultima contiene a la implicacion pero no en el orden necesario, el dispositivo se debe mantener apagado.
		manejadorSucesos.suscribirImplicacion(accion, GeneradorDeSuceso.obtenerSucesos("A B"));
		dispositivo.apagar();		
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("X Y Z B K;A A"));
		
		//Se prueba que la implicacion no es valida y el dispositivo se mantiene apagado.
		assertEquals(dispositivo.isEncendido(),false);		
	}
	
	/**
	 * CASO 8: Configuracion Secuencia Discontinua con Cancelaciones.
	 */
	public void testSucesosContinuosOrdenadosConfiguracionSecuenciaDiscontinuaCancelaciones(){
		// Se crea el manejador de sucesos y la accion para encender el calefactor.
		// Se establece el manejador en modo secuencia discontinua con cancelaciones.
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.obtenerConfiguracion().establecerEvaluadorSecuenciaDiscontinua();
		manejadorSucesos.obtenerConfiguracion().habilitarCancelador();
		AccionPrenderDispositivo accion = new AccionPrenderDispositivo();
		Dispositivo dispositivo = new Dispositivo(new CalefactorDriver());
		accion.establecerDispositivo(dispositivo);
		
		//Se suscribe una implicacion y se notifica una secuencia de sucesos continuos ordenados.
		//Esta ultima contiene a la implicacion y el dispositivo se debe encender.
		manejadorSucesos.suscribirImplicacion(accion, GeneradorDeSuceso.obtenerSucesos("A B"));
		dispositivo.apagar();		
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("X Y Z A B"));
	
		//Se prueba que la implicacion es valida y se prende el dispositivo.
		assertEquals(dispositivo.isEncendido(),true);		
	}
	
	public void testSucesosDiscontinuosOrdenadosConfiguracionSecuenciaDiscontinuaCancelaciones(){
		// Se crea el manejador de sucesos y la accion para encender el calefactor.
		// Se establece el manejador en modo secuencia discontinua con cancelaciones.
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.obtenerConfiguracion().establecerEvaluadorSecuenciaDiscontinua();
		manejadorSucesos.obtenerConfiguracion().habilitarCancelador();
		AccionPrenderDispositivo accion = new AccionPrenderDispositivo();
		Dispositivo dispositivo = new Dispositivo(new CalefactorDriver());
		accion.establecerDispositivo(dispositivo);
		
		//Se suscribe una implicacion y se notifica una secuencia de sucesos discontinuos ordenados.
		//Esta ultima contiene a la implicacion y el dispositivo se debe encender.
		manejadorSucesos.suscribirImplicacion(accion, GeneradorDeSuceso.obtenerSucesos("A B"));
		dispositivo.apagar();		
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("X Y Z A F B"));
		
		//Se prueba que la implicacion es valida y se prende el dispositivo.
		assertEquals(dispositivo.isEncendido(),true);		
	}
			
	public void testSucesosContinuosDesordenadosConfiguracionSecuenciaDiscontinuaCancelaciones(){
		// Se crea el manejador de sucesos y la accion para encender el calefactor.
		// Se establece el manejador en modo secuencia discontinua con cancelaciones.
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.obtenerConfiguracion().establecerEvaluadorSecuenciaDiscontinua();
		manejadorSucesos.obtenerConfiguracion().habilitarCancelador();
		AccionPrenderDispositivo accion = new AccionPrenderDispositivo();
		Dispositivo dispositivo = new Dispositivo(new CalefactorDriver());
		accion.establecerDispositivo(dispositivo);
		
		//Se suscribe una implicacion y se notifica una secuencia de sucesos continuos desordenados.
		//Esta ultima contiene a la implicacion pero no en el orden necesario, el dispositivo se debe mantener apagado.
		manejadorSucesos.suscribirImplicacion(accion, GeneradorDeSuceso.obtenerSucesos("A B"));
		dispositivo.apagar();
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("X Y Z B A"));
		
		//Se prueba que la implicacion no es valida y el dispositivo se mantiene apagado.
		assertEquals(dispositivo.isEncendido(),false);		
	}
	
	public void testSucesosDiscontinuosConCanceladorOrdenadosConfiguracionSecuenciaDiscontinuaCancelaciones(){
		// Se crea el manejador de sucesos y la accion para encender el calefactor.
		// Se establece el manejador en modo secuencia discontinua con cancelaciones.
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.obtenerConfiguracion().establecerEvaluadorSecuenciaDiscontinua();
		manejadorSucesos.obtenerConfiguracion().habilitarCancelador();
		AccionPrenderDispositivo accion = new AccionPrenderDispositivo();
		Dispositivo dispositivo = new Dispositivo(new CalefactorDriver());
		accion.establecerDispositivo(dispositivo);
		
		//Se suscribe una implicacion y se notifica una secuencia de sucesos discontinuos.
		//Esta ultima contiene no contiene a la implicacion, debido a cancelaciones, y el dispositivo se debe mantener apagado.
		manejadorSucesos.suscribirImplicacion(accion, GeneradorDeSuceso.obtenerSucesos("A B"));
		dispositivo.apagar();
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("X Y Z A K;A B"));
		
		//Se prueba que la implicacion no es valida y el dispositivo se mantiene apagado.
		assertEquals(dispositivo.isEncendido(),false);		
	}
	
	public void testSucesosDiscontinuosConCanceladorDesordenadosConfiguracionSecuenciaDiscontinuaCancelaciones(){
		// Se crea el manejador de sucesos y la accion para encender el calefactor.
		// Se establece el manejador en modo secuencia discontinua con cancelaciones.
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.obtenerConfiguracion().establecerEvaluadorSecuenciaDiscontinua();
		manejadorSucesos.obtenerConfiguracion().habilitarCancelador();
		AccionPrenderDispositivo accion = new AccionPrenderDispositivo();
		Dispositivo dispositivo = new Dispositivo(new CalefactorDriver());
		accion.establecerDispositivo(dispositivo);
		
		//Se suscribe una implicacion y se notifica una secuencia de sucesos discontinuos.
		//Esta ultima contiene a la implicacion pero no en el orden necesario, el dispositivo se debe mantener apagado.
		manejadorSucesos.suscribirImplicacion(accion, GeneradorDeSuceso.obtenerSucesos("A B"));
		dispositivo.apagar();
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("X Y Z B K;A A"));
		
		//Se prueba que la implicacion no es valida y el dispositivo se mantiene apagado.
		assertEquals(dispositivo.isEncendido(),false);		
	}
	
	/**
	 * CASO 9: Configuracion Secuencia Continua con Cancelaciones.
	 */
	public void testSucesosContinuosOrdenadosConfiguracionSecuenciaContinuaCancelaciones(){
		// Se crea el manejador de sucesos y la accion para encender el calefactor.
		// Se establece el manejador en modo secuencia continua con cancelaciones.
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.obtenerConfiguracion().establecerEvaluadorSecuenciaContinua();
		manejadorSucesos.obtenerConfiguracion().habilitarCancelador();
		AccionPrenderDispositivo accion = new AccionPrenderDispositivo();
		Dispositivo dispositivo = new Dispositivo(new CalefactorDriver());
		accion.establecerDispositivo(dispositivo);
		
		//Se suscribe una implicacion y se notifica una secuencia de sucesos continuos ordenados.
		//Esta ultima contiene a la implicacion y en el orden necesario, el dispositivo se debe encender.
		manejadorSucesos.suscribirImplicacion(accion, GeneradorDeSuceso.obtenerSucesos("A B"));
		dispositivo.apagar();
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("X Y Z A B"));
	
		//Se prueba que la implicacion es valida y se prende el dispositivo.
		assertEquals(dispositivo.isEncendido(),true);		
	}
	
	public void testSucesosContinuosDesordenadosConfiguracionSecuenciaContinuaCancelaciones(){
		// Se crea el manejador de sucesos y la accion para encender el calefactor.
		// Se establece el manejador en modo secuencia continua con cancelaciones.
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.obtenerConfiguracion().establecerEvaluadorSecuenciaContinua();
		manejadorSucesos.obtenerConfiguracion().habilitarCancelador();
		AccionPrenderDispositivo accion = new AccionPrenderDispositivo();
		Dispositivo dispositivo = new Dispositivo(new CalefactorDriver());
		accion.establecerDispositivo(dispositivo);
		
		//Se suscribe una implicacion y se notifica una secuencia de sucesos continuos desordenados.
		//Esta ultima contiene a la implicacion pero no en el orden necesario, el dispositivo se debe mantener apagado.
		manejadorSucesos.suscribirImplicacion(accion, GeneradorDeSuceso.obtenerSucesos("A B"));
		dispositivo.apagar();
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("X Y Z B A"));
		
		//Se prueba que la implicacion no es valida y el dispositivo se mantiene apagado.
		assertEquals(dispositivo.isEncendido(),false);		
	}
			
	public void testSucesosDiscontinuosOrdenadosConfiguracionSecuenciaContinuaCancelaciones(){
		// Se crea el manejador de sucesos y la accion para encender el calefactor.
		// Se establece el manejador en modo secuencia continua con cancelaciones.
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.obtenerConfiguracion().establecerEvaluadorSecuenciaContinua();
		manejadorSucesos.obtenerConfiguracion().habilitarCancelador();
		AccionPrenderDispositivo accion = new AccionPrenderDispositivo();
		Dispositivo dispositivo = new Dispositivo(new CalefactorDriver());
		accion.establecerDispositivo(dispositivo);
		
		//Se suscribe una implicacion y se notifica una secuencia de sucesos discontinuos ordenados.
		//Esta ultima no contiene a la implicacion, debido a cancelaciones, y el dispositivo se debe mantener apagado.
		manejadorSucesos.suscribirImplicacion(accion, GeneradorDeSuceso.obtenerSucesos("A B"));
		dispositivo.apagar();
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("X Y Z A K;A B"));
		
		//Se prueba que la implicacion no es valida y el dispositivo se mantiene apagado.
		assertEquals(dispositivo.isEncendido(),false);
	}
	
	public void testSucesosDiscontinuosDesordenadosConfiguracionSecuenciaContinuaCancelaciones(){
		// Se crea el manejador de sucesos y la accion para encender el calefactor.
		// Se establece el manejador en modo secuencia continua con cancelaciones.
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		manejadorSucesos.obtenerConfiguracion().establecerEvaluadorSecuenciaContinua();
		manejadorSucesos.obtenerConfiguracion().habilitarCancelador();
		AccionPrenderDispositivo accion = new AccionPrenderDispositivo();
		Dispositivo dispositivo = new Dispositivo(new CalefactorDriver());
		accion.establecerDispositivo(dispositivo);
		
		//Se suscribe una implicacion y se notifica una secuencia de sucesos discontinuos desordenados.
		//Esta ultima contiene a la implicacion pero no en el orden necesario, el dispositivo se debe mantener apagado.
		manejadorSucesos.suscribirImplicacion(accion, GeneradorDeSuceso.obtenerSucesos("A B"));
		dispositivo.apagar();
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("X Y Z B K;A A"));
		
		//Se prueba que la implicacion no es valida y el dispositivo se mantiene apagado.
		assertEquals(dispositivo.isEncendido(),false);		
	}
		
	/**
	 * Test de robustez. 	
	 */
	public void testAgregoSucesoUnicoNulo(){
		//Se crea el manejador de susceso y la accion para encender el dispositivo.
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		AccionPrenderDispositivo accion = new AccionPrenderDispositivo();
		Dispositivo dispositivo = new Dispositivo(new CalefactorDriver());
		accion.establecerDispositivo(dispositivo);
		
		// se suscribe una implicacion y luego se notifica un suceso nulo.
		manejadorSucesos.suscribirImplicacion(accion, GeneradorDeSuceso.obtenerSucesos("pocaAgua presionAlta"));
		dispositivo.apagar();
		assertEquals(false, dispositivo.isEncendido());
		manejadorSucesos.agregarSuceso(null);
	
		//Se prueba que el dispositivo sigue apagado.
		assertEquals(false, dispositivo.isEncendido());
	}
	
	public void testAgregoSucesoNuloYOtrosNoNulosQueEjecutanAccion(){
		//Se crea el manejador de susceso y la accion para encender el dispositivo.
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		AccionPrenderDispositivo accion = new AccionPrenderDispositivo();
		Dispositivo dispositivo = new Dispositivo(new CalefactorDriver());
		accion.establecerDispositivo(dispositivo);		
		dispositivo.apagar();
	
		// se suscribe una implicacion y luego se notifica  sucesos esperados y luego uno nulo.
		manejadorSucesos.suscribirImplicacion(accion, GeneradorDeSuceso.obtenerSucesos("pocaAgua presionAlta"));
		assertEquals(false, dispositivo.isEncendido());		
		manejadorSucesos.agregarSuceso(new Suceso("pocaAgua"));
		manejadorSucesos.agregarSuceso(new Suceso("presionAlta"));
		manejadorSucesos.agregarSuceso(null);
		
		//Se prueba que el dispositivo se ha encendido.
		assertEquals(true, dispositivo.isEncendido());
		
	}
	
	public void testSuscribirImplicacionConListaNula(){
		//Se crea el manejador de susceso y la accion para encender el dispositivo.
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		AccionPrenderDispositivo accion = new AccionPrenderDispositivo();
		Dispositivo dispositivo = new Dispositivo(new CalefactorDriver());
		accion.establecerDispositivo(dispositivo);		
		dispositivo.apagar();
	
		// se suscribe una implicacion con sucesos nulos y luego se notifica varios sucesos
		List<Suceso> listaNula = null;
		manejadorSucesos.suscribirImplicacion(accion, listaNula);
		assertEquals(false, dispositivo.isEncendido());	
		manejadorSucesos.agregarSuceso(new Suceso("pocaAgua"));
		manejadorSucesos.agregarSuceso(new Suceso("presionAlta"));
		
		//Se prueba que el dispositivo sigue apagado.
		assertEquals(false, dispositivo.isEncendido());
	}
	
	public void testSuscribirImplicacionConSucesoNulo(){
		//Se crea el manejador de susceso y la accion para encender el dispositivo.
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		AccionPrenderDispositivo accion = new AccionPrenderDispositivo();
		Dispositivo dispositivo = new Dispositivo(new CalefactorDriver());
		accion.establecerDispositivo(dispositivo);		
		dispositivo.apagar();
	
		// se suscribe una implicacion con sucesos nulos y luego se notifica varios sucesos
		Suceso sucesoNulo =null;
		manejadorSucesos.suscribirImplicacion(accion, sucesoNulo);
		assertEquals(false, dispositivo.isEncendido());		
		manejadorSucesos.agregarSuceso(new Suceso("pocaAgua"));
		manejadorSucesos.agregarSuceso(new Suceso("presionAlta"));
	
		//Se prueba que el dispositivo sigue apagado.
		assertEquals(false, dispositivo.isEncendido());
	}
	
	public void testAgregarListaDeSucesosNulos(){
		//Se crea el manejador de susceso y la accion para encender el dispositivo.
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		AccionPrenderDispositivo accion = new AccionPrenderDispositivo();
		Dispositivo dispositivo = new Dispositivo(new CalefactorDriver());
		accion.establecerDispositivo(dispositivo);		
		dispositivo.apagar();
		
		// se suscribe una implicacion y se notifica un suceso nulo	
		manejadorSucesos.suscribirImplicacion(accion, GeneradorDeSuceso.obtenerSucesos("pocaAgua presionAlta"));
		assertEquals(false, dispositivo.isEncendido());			
		manejadorSucesos.agregarSucesos(null);
		
		//Se prueba que el dispositivo sigue apagado.
		assertEquals(false, dispositivo.isEncendido());		
	}
 	
	
	/**
	 * Test sobre maximos sucesos que puede manejar la API 
	 */
	public void testTamanioMaximoSucesosOcurridosNoSobrepasadoConImplicacionNoValida(){
		//Se crea el manejador de sucesos, la accion que enciende a un dispositivo y se pone
		//como maximo de suscesos en 3.
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		AccionPrenderDispositivo accion = new AccionPrenderDispositivo();
		Dispositivo dispositivo = new Dispositivo(new CalefactorDriver());
		accion.establecerDispositivo(dispositivo);		
		manejadorSucesos.obtenerConfiguracion().establecerEvaluadorContinuo();
		manejadorSucesos.obtenerConfiguracion().establecerTamanioMaximoDeSucesosOcurridos(3);
		
		//Se suscribe una implicacion, y se notifican 3 sucesos
		manejadorSucesos.suscribirImplicacion(accion, GeneradorDeSuceso.obtenerSucesos("A B"));
		dispositivo.apagar();
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("B K A"));
		
		//Se prueba que el dispositivo no se ha encendido.
		assertEquals(dispositivo.isEncendido(),false);
	}
	
	public void testTamanioMaximoSucesosOcurridosNoSobrepasadoConImplicacionValida(){
		//Se crea el manejador de sucesos, la accion que enciende a un dispositivo y se pone
		//como maximo de suscesos en 3.
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		AccionPrenderDispositivo accion = new AccionPrenderDispositivo();
		Dispositivo dispositivo = new Dispositivo(new CalefactorDriver());
		accion.establecerDispositivo(dispositivo);		
		manejadorSucesos.obtenerConfiguracion().establecerEvaluadorContinuo();
		manejadorSucesos.obtenerConfiguracion().establecerTamanioMaximoDeSucesosOcurridos(3);
		
		//Se suscribe una implicacion, y se notifican 2 sucesos validos y 1 invalido
		dispositivo.apagar();		
		manejadorSucesos.suscribirImplicacion(accion, GeneradorDeSuceso.obtenerSucesos("A B"));		
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("B A F"));
		
		//Se prueba que el dispositivo se ha encendido.
		assertEquals(dispositivo.isEncendido(),true);
	}
	
	public void testTamanioMaximoSucesosOcurridosSobrepasadoConImplicacionInvalida(){
		//Se crea el manejador de sucesos, la accion que enciende a un dispositivo y se pone
		//como maximo de suscesos en 3.
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		AccionPrenderDispositivo accion = new AccionPrenderDispositivo();
		Dispositivo dispositivo = new Dispositivo(new CalefactorDriver());
		accion.establecerDispositivo(dispositivo);		
		manejadorSucesos.obtenerConfiguracion().establecerEvaluadorContinuo();
		manejadorSucesos.obtenerConfiguracion().establecerTamanioMaximoDeSucesosOcurridos(3);
		
		//Se suscribe una implicacion y luego se notifican sucesos que por tener un tamanio de 3
		//no quedan validos
		dispositivo.apagar();
		manejadorSucesos.suscribirImplicacion(accion, GeneradorDeSuceso.obtenerSucesos("A B"));
		manejadorSucesos.agregarSuceso(new Suceso("A"));
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("F F F B"));
		
		//Se prueba que el dispositivo no se  ha encendido.
		assertEquals(dispositivo.isEncendido(),false);
	}
	
	public void testTamanioMaximoSucesosOcurridosSobrepasadoConImplicacionValida(){
		//Se crea el manejador de sucesos, la accion que enciende a un dispositivo y se pone
		//como maximo de suscesos en 3.
		ManejadorDeSucesos manejadorSucesos = new ManejadorDeSucesos();
		AccionPrenderDispositivo accion = new AccionPrenderDispositivo();
		Dispositivo dispositivo = new Dispositivo(new CalefactorDriver());
		accion.establecerDispositivo(dispositivo);		
		manejadorSucesos.obtenerConfiguracion().establecerEvaluadorContinuo();
		manejadorSucesos.obtenerConfiguracion().establecerTamanioMaximoDeSucesosOcurridos(3);
		
		//Suscribe una implicacion y luego notifica sucesos validos
		manejadorSucesos.suscribirImplicacion(accion, GeneradorDeSuceso.obtenerSucesos("A B"));
		dispositivo.apagar();				
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("G B"));
		manejadorSucesos.agregarSucesos(GeneradorDeSuceso.obtenerSucesos("A S"));
		
		//Se prueba que el dispositivo  se ha encendido.
		assertEquals(dispositivo.isEncendido(),true);
	}
}
