package cliente;

import source.Accion;
import source.ManejadorDeSucesos;
import source.Suceso;

public class AccionTirarGaseosa implements Accion {
	private ManejadorDeSucesos manejador = ManejadorDeSucesos.obtenerInstancia();
	public void ejecutar() {
		manejador.agregarSuceso(new Suceso("pocaGaseosa"));
	}

}
