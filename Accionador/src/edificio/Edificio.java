package edificio;

import java.util.ArrayList;
import java.util.List;

import source.ManejadorDeSucesos;

public class Edificio {

	private List<Piso> pisos;
	
	private ManejadorDeSucesos manejadorDeSucesos;
	
	private static Edificio instancia;
	
	private Edificio(){
		this.manejadorDeSucesos= new ManejadorDeSucesos();
		this.pisos = new ArrayList<Piso>();
	}
	public static Edificio obtenerInstancia(){
		if(instancia==null){
			instancia = new Edificio();
		}
		return instancia;
	}
		
	
	public void agregarPiso(Piso pisoAgregar){
		this.pisos.add(pisoAgregar);		
	}
	public ManejadorDeSucesos getManejadorDeSucesos() {
		return manejadorDeSucesos;
	}
	public void setManejadorDeSucesos(ManejadorDeSucesos manejadorDeSucesos) {
		this.manejadorDeSucesos = manejadorDeSucesos;
	}
	
	
	
}
