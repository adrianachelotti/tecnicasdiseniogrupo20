package modelo.prueba;
import java.util.ArrayList;
import java.util.List;
import modelo.manejadorDeSucesos.*;

/**
 * Clase encargada de generar sucesos.
 * 
 * @author Grupo20
 *
 */

public class GeneradorDeSuceso {
	
	private static List<Suceso> listaDeSucesosGeneradas ;
	
	/**
	 * Obtiene una coleccion de sucesos segun el parametro ingresado.
	 * @param sucesoACrear coleccion de sucesos a crear.
	 * @return Coleccion de sucesos creados.
	 */
	public static List<Suceso> obtenerSucesos(String sucesoACrear){
		String[] sucesosAGenerar = sucesoACrear.split(" ");
		listaDeSucesosGeneradas = new ArrayList<Suceso>();
		for (String identificadorActual : sucesosAGenerar) {
			if (identificadorActual.contains(";")){
				String[] identificadores =identificadorActual.split(";"); 
				listaDeSucesosGeneradas.add(new Suceso(identificadores[0],identificadores[1]));
			}
			else
				listaDeSucesosGeneradas.add(new Suceso(identificadorActual));
		}
		return listaDeSucesosGeneradas;
	}

}
