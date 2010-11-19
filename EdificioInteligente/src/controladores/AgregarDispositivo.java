package controladores;

import java.util.List;
import java.util.Map;

import modelo.driver.DriverDispositivo;
import modelo.edificio.Edificio;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import controladores.beans.EdificioBean;

@SuppressWarnings("serial")
public class AgregarDispositivo extends ActionSupport {
	private int nivel;
	@SuppressWarnings("unused")
	private Map<String,Object> session ;
	
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	public String execute(){
		Map<String,Object> session = ActionContext.getContext().getSession();
		Edificio edificio = Edificio.obtenerInstancia();
		List<DriverDispositivo> driversDispositivos = edificio.getCataloDriversDeDispositivos();
		EdificioBean contenedor = new EdificioBean();
		contenedor.setCatalogoDriversDeDisposititvos(driversDispositivos);
		session.put("edificio",contenedor);
		setSession(session);
		return SUCCESS;
	}
	public int getNivel() {
		return nivel;
	}
	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

}
