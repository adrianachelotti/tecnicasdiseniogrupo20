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
		

	/**
	 * Se establece la session 
	 * @param session session actual
	 */
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	
	/**
	 * Se pasa a través de la session los pisos del edificio
	 */
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
