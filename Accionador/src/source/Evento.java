package source;

public class Evento {
	
	/**
	 * Atributo que representa el id del evento
	 */
	private String idEvento;
	
	/**
	 * Atributo que representa el id del evento que cancela al actual
	 */
	private String idEventoCancelador;	
	
	/**
	 * Obtiene el id del evento cancelador
	 * @return id del evento
	 */
	public String getIdEventoCancelador() {
		return idEventoCancelador;
	}

	/**
	 * Se carga el id del evento cancelador
	 * @param idEventoCancelador: valor a ser seteado
	 */
	public void setIdEventoCancelador(String idEventoCancelador) {
		this.idEventoCancelador = idEventoCancelador;
	}
	
	
	/**
	 * Se obtiene una cadena de caracteres que representa el id del evento actual
	 * @return: valor de id del evento
	 */
	public String getIdEvento() {
		return idEvento;
	}

	/**
	 * Se carga el valor del id del evento actual
	 * @param idEvento: valor del id a ser seteado
	 */
	public void setIdEvento(String idEvento) {
		this.idEvento = idEvento;
	}
	/**
	 * Compara dos eventos por id
	 * @param evento: evento a ser comparado con el actual
	 * @return true: si los id son iguales
	 * 		false: en caso contrario 
	 */
	public boolean equals(Evento evento){
		if (evento.getIdEvento()== this.idEvento)
			return true;
		else 
			return false;
	
	}

}
