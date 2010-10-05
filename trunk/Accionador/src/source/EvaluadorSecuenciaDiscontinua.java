package source;

import java.util.ArrayList;
import java.util.List;

public class EvaluadorSecuenciaDiscontinua extends Evaluador {

	@Override
	public void avisarSucesosOcurridos(Implicacion implicacion,	List<Suceso> sucesos) {
		
		if(sucesos.size()>= implicacion.getSucesos().size()){
		
			List<Suceso> sucesosBeta = new ArrayList<Suceso>();
			for(Suceso s: sucesos)
				sucesosBeta.add(s);
						
			sucesosBeta.retainAll(implicacion.getSucesos());
			if(sucesosBeta.equals(implicacion.getSucesos())) implicacion.getAccion().ejecutar();
		}
	}

	
	public static void main(String[] args) {
		List<String> implicacion = new ArrayList<String>();
		implicacion.add("A");
		implicacion.add("B");
		implicacion.add("C");
		implicacion.add("D");
		
		List<String> sucesos = new ArrayList<String>();
		sucesos.add("A");
		sucesos.add("B");
		sucesos.add("X");
		sucesos.add("C");
		sucesos.add("Y");
		sucesos.add("D");
		sucesos.add("Z");
		
		List<String> sucesosBeta = new ArrayList<String>();
		for(String s: sucesos)
		{
			sucesosBeta.add(s);
		}
		
		sucesosBeta.retainAll(implicacion);
		
		for(String s: sucesosBeta)
		{
			System.out.println(s);
		}
		
		if(sucesosBeta.equals(implicacion)) System.out.println("Iguales");
		
		
	}
	
}
