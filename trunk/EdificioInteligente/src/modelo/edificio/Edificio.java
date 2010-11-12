package modelo.edificio;

import java.util.ArrayList;
import java.util.List;
import modelo.manejadorDeSucesos.*;

public class Edificio {

	private List<Piso> pisos;
	
	
	
	private static Edificio instancia;
	
	private Edificio(){		
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
	
	
	
}
