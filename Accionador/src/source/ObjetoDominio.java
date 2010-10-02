package source;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


public class ObjetoDominio {
	
	/**
	 * Comando que ejecuta una accion en particular, puede agregar nuevos 
	 * eventos al Accionador
	 */
	private Command comando;
	public Command getComando() {
		return comando;
	}


	public void setComando(Command comando) {
		this.comando = comando;
	}
	/**
	 * Conjunto de eventos que tienen que suceder para que el objeto de
	 * una respuesta
	 */
	private List<Evento> eventos = new LinkedList<Evento>();
	
	/**
	 * Constructor se instancia la lista de eventos, para el caso en que el 
	 * objeto no suscribe ningun evento
	 */
	public ObjetoDominio(){
		this.eventos= new LinkedList<Evento>();
	}
	
	
	public List<Evento> getEventos() {
		return eventos;
	}

	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
	}
	
	
	public void agregarSuceso(Evento evento){
		this.eventos.add(evento);
	}
	
	public String avisarEventoOcurrido(Evento evento){
		if (this.eventos.size()==1){
			for (Evento eventoActual : this.eventos) {
				if (eventoActual.getIdEvento().equals(evento.getIdEvento())){
					if (this.getComando()!=null)
							this.getComando().ejecutar();
					return "Evento ";
				}
			}
			return "No sucede";
		}
		return "No completo";
		
	}
	public String avisarEventosOcurridos(List<Evento> eventosSucedidos){
		
		
		if(eventosSucedidos.size()==this.eventos.size()){
			Iterator<Evento> it = this.eventos.iterator();
			boolean conjEventosSucedidos = true;
			for (Evento evento : eventosSucedidos ) {
				Evento evento2 = (Evento)it.next();
				if (!evento.equals(evento2)){
					conjEventosSucedidos = false;
				}
				
			}
			//Si termina de recorrer la lista y no salio previamente entonces
			if (!conjEventosSucedidos )
			return "No cumple conjunto";
			else{
				this.comando.ejecutar();
				return "Se Cumplio con todos los eventos";
			}
		}
		
		return "No sucede";
	}

}
