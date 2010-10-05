package source;

import java.util.List;

public class EvaluadorIgualdadConjunto extends Evaluador {

	@Override
	public void avisarSucesosOcurridos(Implicacion implicacion,	List<Suceso> sucesos) {
		if (sucesos.containsAll(implicacion.getSucesos())&&(implicacion.getSucesos().size()==sucesos.size()))
			implicacion.getAccion().ejecutar();		
	}

}
