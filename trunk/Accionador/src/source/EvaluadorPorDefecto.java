package source;

import java.util.List;

public class EvaluadorPorDefecto extends Evaluador {

	@Override
	public void avisarSucesosOcurridos(Implicacion implicacion,List<Suceso> sucesos) {
		if (sucesos.containsAll(implicacion.getSucesos()))
			implicacion.getAccion().ejecutar();
	}

}
