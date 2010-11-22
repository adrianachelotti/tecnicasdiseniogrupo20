package controladores;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.config.entities.ActionConfig;

import modelo.manejadorDeSucesos.*;
import modelo.edificio.*;
import controladores.beans.*;
import modelo.manejadorDeSucesos.*;
import modelo.accion.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.ActionMap;

public class AgregarRegla extends ActionSupport {
	
	private int nivel;
	
	private List<String> sucesosEsperados = new ArrayList<String>();
	
	@SuppressWarnings("unused")
	private Map<String,Object> session ;
	
	private String sucesoAgregar;
	
	private String dispositivoElegido;
	
	private String accion;
	
	/**
	 * Se establece la session 
	 * @param session
	 */
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	

	/**
	 * Devuelve el nivel elegido por usuario
	 * @return nivel: piso elegido para agregarle la regla
	 */
	public int getNivel() {
		return nivel;
	}
	/**
	 * Establece el nivel elegido por el usuario
	 * @param nivel: piso elegido
	 */
	public void setNivel(int nivel) {
		this.nivel = nivel;
	}
	
	/**
	 * Obtiene la lista de sucesos a agregar a la regla
	 * @return lista de sucesos
	 */
	public List<String> getSucesosEsperados() {
		return sucesosEsperados;
	}

	/**
	 * Establece la lista de sucesos a agregar a la regla
	 * @param sucesosAgregar sucesos a agregar a la regla
	 */
	public void setSucesosEsperados(List<String> sucesosAgregar) {
		this.sucesosEsperados = sucesosAgregar;
	}
	
	/**
	 * Obtiene el id del suceso a agregar a los esperados 
	 * @return suceso a agregar
	 */
	public String getSucesoAgregar() {
		return sucesoAgregar;
	}

	/**
	 * Establece el suceso a agregar a los esperados
	 * @param sucesoAgregar suceso a agregar
	 */
	public void setSucesoAgregar(String sucesoAgregar) {
		this.sucesoAgregar = sucesoAgregar;
	}

	/**
	 * Obtiene el dispositivo elegido
	 * @return dispositivo elegido
	 */
	public String getDispositivoElegido() {
		return dispositivoElegido;
	}

	/**
	 * Establece el dispositivo elegido
	 * @param dispositivoElegido dispositvo elegido
	 */
	public void setDispositivoElegido(String dispositivoElegido) {
		this.dispositivoElegido = dispositivoElegido;
	}

	/**
	 * Obtiene la accion elegida para la implicacion
	 * @return encender o apagar
	 */
	public String getAccion() {
		return accion;
	}

	/**
	 * Establece la accion elegida para la implicacion
	 * @param accion accion elegida
	 */
	public void setAccion(String accion) {
		this.accion = accion;
	}


	/**
	 * Prepara los datos para configurar la regla
	 */
	public String execute(){
		Map<String,Object> session = ActionContext.getContext().getSession();
		Edificio edificio = Edificio.obtenerInstancia();		
		Piso piso = edificio.obtenerPiso(this.nivel);
		EdificioBean contenedor = new EdificioBean();		
		
		contenedor.establecerListadoDeDispositivos(piso.obtenerDispositivos());
		contenedor.establecerMedicionesPosibles(piso.obtenerCatalogoMedicionesPosibles());
		contenedor.establecerMedicionesEsperadas(this.sucesosEsperados);
		
		session.put("piso",contenedor);
		this.setSession(session);
		
		return "success";
	}
	/**
	 * Agrega un suceso a los esperados por la regla
	 * @return "success"
	 */
	public String agregarSucesoEsperado(){
		Map<String,Object> session = ActionContext.getContext().getSession();
		EdificioBean contenedor = (EdificioBean)session.get("piso");
		
		this.sucesosEsperados = contenedor.obtenerMedicionesEsperadas();		
		this.sucesosEsperados.add(sucesoAgregar);
		
		return execute();
	}
	/**
	 * Recupera los datos obtenidos  suscribe la implicacion al manejador
	 * @return "guardar"
	 */
	public String guardar(){
		
		Edificio edificio = Edificio.obtenerInstancia();		
		Map<String,Object> session = ActionContext.getContext().getSession();
		EdificioBean contenedor = (EdificioBean)session.get("piso");
		int indiceDispositivo = Integer.parseInt(dispositivoElegido);
		Piso piso = edificio.obtenerPiso(nivel);
		Accion accionAgregar;	
		
		Dispositivo dispositivoAgregar = piso.obtenerDispositivos().get(indiceDispositivo);
		if (accion.equalsIgnoreCase("encender")){
			accionAgregar = new AccionPrenderDispositivo();			
		}else{
			accionAgregar = new AccionApagarDispositivo();
		}
		accionAgregar.establecerDispositivo(dispositivoAgregar);
		piso.obtenerManejadorDeSucesos().suscribirImplicacion(accionAgregar, obtenerSucesosRegla(contenedor));
		
		return "guardar";
		
	}

	/**
	 * Obtiene una lista de sucesos a partir de los id ingresados
	 * @param contenedor contiene las mediciones esperadas
	 * @return lista de sucesos
	 */
	private List<Suceso> obtenerSucesosRegla(EdificioBean contenedor) {
		List<String> listaIdSucesosEsperados = contenedor.obtenerMedicionesEsperadas();
		List<Suceso> sucesosEsperados = new ArrayList<Suceso>();
		for (String idSuceso : listaIdSucesosEsperados) {
			sucesosEsperados.add(new Suceso(idSuceso));
		}
		return sucesosEsperados;
	}

}
