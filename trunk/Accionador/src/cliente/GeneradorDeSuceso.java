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
			listaDeSucesosGeneradas.add(new Suceso(identificadorActual));
		}
		return listaDeSucesosGeneradas;
	}

}
