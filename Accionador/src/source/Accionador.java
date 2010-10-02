package source;
import java.util.LinkedList;
import java.util.List;


public class Accionador {

	/**
	 * Instancia unica de la API 
	 */
	private static Accionador instancia ;
	
		
	/**
	 * Lista de ObjetosDominio que van a ser observados por la API
	 */
	private List<ObjetoDominio> objetosObservados;
	
	/**
	 * Constructor de la API.
	 */	
	private Accionador(){
		objetosObservados = new LinkedList<ObjetoDominio> (); 
	}
	
	/**
	 * Se obtiene una instancia del Accionador
	 * @return instancia de Accionador
	 */
	public static Accionador getInstancia(){
		if (instancia==null){
			instancia=new Accionador();
		}
		return instancia;
	}
	
	/**
	 * Se suscribe los eventos deben suceder y la respuesta que da 
	 * el objeto   
	 * @param respuesta: comando a ejecutar si se cumplen los
	 * eventos 
	 * @param sucesos: conjuntos de eventos 
	 */
	public void suscribirEventosYRespuesta(Command respuesta, List<Evento> sucesos){
		ObjetoDominio obj =  new ObjetoDominio();
		obj.setComando(respuesta);
		obj.setEventos(sucesos);
		this.objetosObservados.add(obj);
	}
	
	/**
	 * Se suscribe el evento que debe suceder para que sea avisado y la respuesta
	 * @param respuesta: respuesta del objeto ante el evento 
	 * @param evento: evento 
	 */
	public void suscribirEventoYRespuesta(Command respuesta, Evento evento){
		List<Evento> lista = new LinkedList<Evento>();
		lista.add(evento);
		this.suscribirEventosYRespuesta(respuesta, lista);
		
		
	}
	
	/**
	 * Se corrobora si hay un suceso suscripto de ese tipo y se ejecuta el comando
	 * @param eventoActual: evento que se corrobora
	 */
	public void update(Evento eventoActual){
		for (ObjetoDominio objetoActual : this.objetosObservados) {
			System.out.println(objetoActual.avisarEventoOcurrido(eventoActual));			
		}
	}
	
	/**
	 * Se corrobora entre los eventos que se suscriben y en caso de encontrar 
	 * dicho conjunto se ejecuta el comando
	 * @param eventos que se corrobora
	 */
	public void update(List<Evento> eventos){
		for (ObjetoDominio objetoActual : this.objetosObservados) {
			System.out.println(objetoActual.avisarEventosOcurridos(eventos));			
		}
	}
}
