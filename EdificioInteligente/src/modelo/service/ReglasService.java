package modelo.service;

import java.util.ArrayList;
import java.util.List;

import modelo.edificio.Edificio;
import modelo.edificio.Piso;
import modelo.manejadorDeSucesos.Implicacion;
import modelo.manejadorDeSucesos.Suceso;
import modelo.service.dto.ImplicacionDTO;

public class ReglasService {

	private Edificio edificio = Edificio.obtenerInstancia();
	
	public ImplicacionDTO[] obtenerReglasPorPiso(int nivel){
			Piso piso = edificio.obtenerPiso(nivel);
			List<Implicacion> reglas = piso.obtenerManejadorDeSucesos().obtenerImplicaciones();
			List<ImplicacionDTO> reglasDTO = convertirImplicacionDTO(reglas);
			return reglasDTO.toArray(new ImplicacionDTO[reglasDTO.size()]);
	}
	
	public ImplicacionDTO[] obtenerReglas(){
		List<Implicacion> reglas = edificio.obtenerReglas();
		List<ImplicacionDTO> reglasDTO = convertirImplicacionDTO(reglas);
		return reglasDTO.toArray(new ImplicacionDTO[reglas.size()]);
	}
		
	public int cantidadDePisos(){
		return edificio.getPisos().size();
	}
	
		
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

	public void habilitarRegla(int identificador){
		edificio.habilitarRegla(identificador);
	}
	
	public void deshabilitarRegla(int identificador){
		edificio.deshabilitarRegla(identificador);
	}
		
}
