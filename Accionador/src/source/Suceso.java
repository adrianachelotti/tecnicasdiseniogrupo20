package source;


/**
 * Clase que modela un suceso.
 * 
 * @author Grupo20
 *
 */
public class Suceso {
	
	/**
	 * Identificador del suceso.
	 */
	private String idSuceso;
	
	/**
	 * Identificador del suceso con el que se cancela.
	 */
	private String idSucesoCancelador;	
	
	
	/**
	 * Orden en que se suscribe el suceso.
	 */
	private long ordenDeSuscripcion;
	
	/**
	 * Constructor.
	 * @param idSuceso identificador del suceso.
	 */
	public Suceso(String idSuceso)
	{
		this.idSuceso = idSuceso;
	}
	
	/**
	 * Constructor.
	 * @param idSuceso identificador del suceso.
	 * @param idSucesoCancelador identificador del suceso con el que se cancela.
	 */
	public Suceso(String idSuceso, String idSucesoCancelador)
	{
		this(idSuceso);
		this.idSucesoCancelador = idSucesoCancelador;
	}
		
	/**
	 * Obtiene el identificador del suceso que lo cancela.
	 * @return identificador del suceso con el que se cancela.
	 */
	public String getIdSucesoCancelador() {
		return idSucesoCancelador;
	}
	
	/**
	 * Obtiene el identificador del suceso.
	 * @return identificador del suceso.
	 */
	public String getIdSuceso() {
		return idSuceso;
	}
			
	/**
	 * Obtiene el orden en que se suscribe el suceso.
	 * @return orden de suscripcion.
	 */
	long getOrdenDeSuscripcion() {
		return ordenDeSuscripcion;
	}

	/**
	 * Carga el orden en que se suscribe el suceso.
	 * @param ordenDeSuscripcion orden de suscripcion a cargar.
	 */
	void setOrdenDeSuscripcion(long ordenDeSuscripcion) {
		this.ordenDeSuscripcion = ordenDeSuscripcion;
	}

	@Override
	public boolean equals(Object obj) {
		if (((Suceso)obj).getIdSuceso().equals(this.idSuceso))
			return true;
		else 
			return false;
	}
	
	@Override
	public String toString() {
		return idSuceso+"-"+idSucesoCancelador+"-"+ordenDeSuscripcion;
	}
		
}
