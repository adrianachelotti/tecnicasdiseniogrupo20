package cliente;
import java.util.ArrayList;
import java.util.List;

import source.*;

public class GeneradorDeSuceso {
	
	private static List<Suceso> listaDeSucesosGeneradas ;
	
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
