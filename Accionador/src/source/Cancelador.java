package source;

import java.util.List;

public abstract class Cancelador {

	public abstract void cancelarSucesos(List<Suceso> sucesosExistentes,List<Suceso> sucesosNuevos);

	public abstract void cancelarSuceso(List<Suceso> sucesosExistentes,	Suceso sucesoNuevo) ;
}
