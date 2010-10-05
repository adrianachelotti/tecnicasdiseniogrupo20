package source;

public class Suceso {
	
	/**
	 * Atributo que representa el id del suceso
	 */
	private String idSuceso;
	
	/**
	 * Atributo que representa el id del suceso que cancela al actual
	 */
	private String idSucesoCancelador;	
	
	
	public Suceso(String idSuceso)
	{
		this.idSuceso = idSuceso;
	}
	
	
	/**
	 * Obtiene el id del suceso cancelador
	 * @return id del suceso
	 */
	public String getIdSucesoCancelador() {
		return idSucesoCancelador;
	}

	/**
	 * Se carga el id del suceso cancelador
	 * @param idSucesoCancelador: valor a ser seteado
	 */
	public void setIdSucesoCancelador(String idSucesoCancelador) {
		this.idSucesoCancelador = idSucesoCancelador;
	}
	
	
	/**
	 * Se obtiene una cadena de caracteres que representa el id del suceso actual
	 * @return: valor de id del suceso
	 */
	public String getIdSuceso() {
		return idSuceso;
	}

	/**
	 * Se carga el valor del id del suceso actual
	 * @param idSuceso: valor del id del suceso a ser seteado
	 */
	public void setIdSuceso(String idSuceso) {
		this.idSuceso = idSuceso;
	}
	/**
	 * Compara dos suceso por id
	 * @param suceso: suceso a ser comparado con el actual
	 * @return true: si los id son iguales
	 * 		false: en caso contrario 
	 */
		
	@Override
	public boolean equals(Object obj) {
		if (((Suceso)obj).getIdSuceso()== this.idSuceso)
			return true;
		else 
			return false;
	}
	
	
}
