package source;

import java.util.List;

public class EvaluadorSecuenciaContinua extends Evaluador {
	
	@Override
	public void avisarSucesosOcurridos(Implicacion implicacion,List<Suceso> sucesos) {
		if (implicacion.getSucesos().equals(sucesos))
			implicacion.getAccion().ejecutar();
	}
		
	
}
