package controladores;

import java.util.Map;

import modelo.edificio.Edificio;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import controladores.beans.EdificioBean;

public class ListadorDeDispositivos extends ActionSupport {
	
	private Map<String, Object> session;
	
	private int nivel;
	
	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public String execute(){
		Map<String,Object> session = ActionContext.getContext().getSession();
		Edificio edificio = Edificio.obtenerInstancia();
		EdificioBean contenedor = new EdificioBean();
		contenedor.establecerListadoDeDispositivos(edificio.getPisos().get(this.nivel-1).obtenerDispositivos());
		contenedor.establecerNivel(this.nivel);
		session.put("piso", contenedor);
		setSession(session);
		return "success";
	}
	
	public String seleccionarPisos(){		
		Map<String,Object> session = ActionContext.getContext().getSession();
		Edificio edificio = Edificio.obtenerInstancia();
		EdificioBean contenedor = new EdificioBean();
		contenedor.establecerListadoDePisos(edificio.getPisos());
		session.put("edificio", contenedor);
		setSession(session);
		return "listarPisos";
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

}
