package modelo.edificio;

public class Ubicacion {
	
	private  int posicionX;
	
	private  int posicionY;
	
	private  int posicionZ;
	
	/**
	 * Contructor de Ubicacion cuyas coordenadas son pasada por parametros
	 * @param posicionX posicion en X
	 * @param posicionY posicion en Y
	 * @param posicionZ posicion en Z
	 */
	public Ubicacion(int posicionX,int posicionY, int posicionZ){
		this.posicionX = posicionX;
		this.posicionY = posicionY;
		this.posicionZ = posicionZ;
		
	}
	
	/**
	 * Obtiene la posicion en X 
	 * @return posicion en X
	 */
	public int obtenerPosicionX() {
		return posicionX;
	}
	
	/**
	 * Establece la posicion X
	 * @param posicionX posicion en X
	 */
	public void establecerPosicionX(int posicionX) {
		this.posicionX = posicionX;
	}
	
	/**
	 * Obtiene la posicion en Y
	 * @return la posicion en Y
	 */
	public int obtenerPosicionY() {
		return posicionY;
	}
	
	/**
	 * Establece la posicion Y
	 * @param posicionY posicion en Y
	 */
	public void establecePosicionY(int posicionY) {
		this.posicionY = posicionY;
	}
	
	/**
	 * Obtiene la posicion en Z
	 * @return la posicion en Z
	 */
	public int obtenerPosicionZ() {
		return posicionZ;
	}
	
	/**
	 * Establece la posicion en Z 
	 * @param posicionZ
	 */
	
	public void establecerPosicionZ(int posicionZ) {
		this.posicionZ = posicionZ;
	}
	/**
	 * Metodo para imprimir todas la coordenadas
	 */
	public String toString(){
		String cadena = "x: "+this.posicionX + "  y:" + this.posicionY + "  z:"+this.posicionZ;
		return cadena;
	}
	
}
