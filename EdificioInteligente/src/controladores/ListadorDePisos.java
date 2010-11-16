package controladores;
import java.util.List;
import java.util.ArrayList;
import modelo.edificio.*;
import com.opensymphony.xwork2.ActionSupport;

public class ListadorDePisos extends ActionSupport {
	
	private List<Piso> pisos ;
	

	public List<Piso> getPisos() {
		return pisos;
	}

	public void setPisos(List<Piso> pisos) {
		this.pisos = pisos;
	}
	
	private void cargarEdificios(){
		this.pisos = new ArrayList<Piso>();
		this.pisos.add(new Piso(1));
		this.pisos.add(new Piso(2));
		this.pisos.add(new Piso(3));
		this.pisos.add(new Piso(4));
		this.pisos.add(new Piso(5));
		this.pisos.add(new Piso(6));
	}
	public String execute(){
		return "success";
	}

}
