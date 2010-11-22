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
	
	/*
	private List<ImplicacionDTO> cargarDatos(){
		Accion acc1 = new AccionApagarDispositivo();
		Suceso suc1 = new Suceso("TEMPERATURA_ALTA");
		Suceso suc2 = new Suceso("PRESION_ALTA");
		List<Suceso> antecedente = new ArrayList<Suceso>();
		antecedente.add(suc1);
		antecedente.add(suc2);
				
		Implicacion impl1 = new Implicacion(antecedente, acc1);
		List<Implicacion> implicaciones = new ArrayList<Implicacion>();
		implicaciones .add(impl1);
		
		return convertirImplicacionDTO(implicaciones);
	}

	public ImplicacionDTO[] obtenerReglasPorPisoBETA(int nivel){
		List<ImplicacionDTO> reglasDTO = cargarDatos();
		return reglasDTO.toArray(new ImplicacionDTO[reglasDTO.size()]);
	}
	*/
	
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
		
	/*
	public static void main(String[] args) {
			Edificio edificio = Edificio.obtenerInstancia();
			Piso piso0 = new Piso(0);
			Dispositivo dispositivo = new Dispositivo(new CalefactorDriver());
			dispositivo.establecerDescripcion("Estufa");
			piso0.agregarDispositivo(dispositivo);
			AccionPrenderDispositivo accion = new AccionPrenderDispositivo();
			accion.establecerDispositivo(dispositivo);
			piso0.obtenerManejadorDeSucesos().suscribirImplicacion(accion, new Suceso("TEMPERATURA_BAJA"));
			edificio.agregarPiso(piso0);
			edificio.agregarPiso(new Piso(1));
			//TODO: cambiar esta hardcodeado
			edificio.agregarDriverDispositivo(new CalefactorDriver());
			edificio.agregarDriverDispositivo(new LucesDriver());
			edificio.agregarDriverDispositivo(new PuertaDriver());
			edificio.agregarDriverSensor(new SensorDeHumoDriver());
			edificio.agregarDriverSensor(new SensorDeRuidoDriver());
			edificio.agregarDriverSensor(new SensorDeTemperaturaDriver());
			
			ReglasService rService = new ReglasService();
			
			ImplicacionDTO[] implicaciones = rService.obtenerReglasPorPiso(0);
			
			for (ImplicacionDTO implicacionDTO : implicaciones) {
				System.out.println(implicacionDTO.getAccion());
				for (String s : implicacionDTO.getSucesos()) {
					System.out.println(s);
				}
				
			}
		
	}
	*/
}
