package controladores;
import java.util.List;
import java.util.Map;

import modelo.edificio.*;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import controladores.beans.EdificioBean;

@SuppressWarnings("serial")
public class ListadorDePisos extends ActionSupport {
	
	@SuppressWarnings("unused")
	private Map<String,Object> session ;
		
	private List<Piso> pisos ;
	
	public List<Piso> getPisos() {
		return pisos;
	}

	public void setPisos(List<Piso> pisos) {
		this.pisos = pisos;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public String execute(){
		Map<String,Object> session = ActionContext.getContext().getSession();
		Edificio edificio = Edificio.obtenerInstancia();
		EdificioBean contenedor = new EdificioBean();
		contenedor.establecerListadoDePisos(edificio.getPisos());
		session.put("edificio", contenedor);
		setSession(session);
		return "success";
	}

}
