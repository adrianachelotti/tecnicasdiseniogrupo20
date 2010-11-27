package modelo.service;

import java.util.ArrayList;
import java.util.List;

import modelo.edificio.Edificio;
import modelo.edificio.Piso;
import modelo.manejadorDeSucesos.Implicacion;
import modelo.manejadorDeSucesos.Suceso;
import modelo.service.dto.ImplicacionDTO;

/**
 * Clase que modela al servicio web de reglas.
 * @author Grupo20.
 *
 */
public class ReglasService {

	private Edificio edificio = Edificio.obtenerInstancia();
	
	/**
	 * Obtiene las reglas que posee el piso cuyo nivel es pasado como parametro.
	 * @param nivel nivel del piso del cual se obtienen las reglas.
	 * @return reglas existentes en el piso cuyo nivel es pasado como parametro.
	 */
	public ImplicacionDTO[] obtenerReglasPorPiso(int nivel){
			Piso piso = edificio.obtenerPiso(nivel);
			List<Implicacion> reglas = piso.obtenerManejadorDeSucesos().obtenerImplicaciones();
			List<ImplicacionDTO> reglasDTO = convertirImplicacionDTO(reglas);
			return reglasDTO.toArray(new ImplicacionDTO[reglasDTO.size()]);
	}
	
	/**
	 * Obtiene las reglas del edificio.
	 * @return reglas del edificio.
	 */
	public ImplicacionDTO[] obtenerReglas(){
		List<Implicacion> reglas = edificio.obtenerReglas();
		List<ImplicacionDTO> reglasDTO = convertirImplicacionDTO(reglas);
		return reglasDTO.toArray(new ImplicacionDTO[reglas.size()]);
	}
		
	/**
	 * Retorna la cantidad de pisos del edificio.
	 * @return cantidad de pisos del edificio.
	 */
	public int cantidadDePisos(){
		return edificio.getPisos().size();
	}
	
	/**
	 * Habilita la regla cuyo identificador es pasado como parametro.
	 * @param identificador identificador de la regla a habilitar.
	 */
	public void habilitarRegla(int identificador){
		edificio.habilitarRegla(identificador);
	}
	
	/**
	 * Deshabilita la regla cuyo identificador es pasado como parametro.
	 * @param identificador identificador de la regla a deshabilitar.
	 */
	public void deshabilitarRegla(int identificador){
		edificio.deshabilitarRegla(identificador);
	}
	
	/**
	 * Convierte las reglas pasadas como parametro a reglas con objetos de 
	 * transferencia de reglas.
	 * @param reglas reglas a convertir.
	 * @return reglas convertidas en objetos de transferencia de reglas.
	 */
	private List<ImplicacionDTO> convertirImplicacionDTO(List<Implicacion> reglas) {
		List<ImplicacionDTO> reglasDTO = new ArrayList<ImplicacionDTO>();
		for (Implicacion implicacion : reglas) {
			ImplicacionDTO implicacionDTO = new ImplicacionDTO();
			implicacionDTO.setAccion(implicacion.getAccion().obtenerNombre());
			implicacionDTO.setDispositivo(implicacion.getAccion().obtenerDispositivo().obtenerDescripcion());
			implicacionDTO.setRegla(implicacion.getIdentificador());
			implicacionDTO.setEstado(implicacion.estaHabilitada()?"HABILITADA":"DESHABILITADA");
			String sucesosString[] = new String[implicacion.getSucesos().size()];
			int i=0;
			for(Suceso suceso: implicacion.getSucesos()){
				sucesosString[i]= suceso.getIdSuceso();
				i++;
			}
			implicacionDTO.setSucesos(sucesosString);
			reglasDTO.add(implicacionDTO);
		}
		return reglasDTO;
	}
		
}
