package modelo.cliente;


public class Gaseosa {
	
	private boolean frio;
	
	private int stock;
	
	private String nombre;

	
	public Gaseosa(){
		
	}
	public Gaseosa(String nombreGaseosa, int stockGaseosa, boolean estaFrio){
		frio = estaFrio;
		nombre = nombreGaseosa;
		stock = stockGaseosa;
		
	}
	
	public boolean isFrio() {
		return frio;
	}

	public void setFrio(boolean frio) {
		this.frio = frio;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
	public void descontarGaseosa(){
		this.stock--;
	}
	public void aumentarGaseosa(){
		this.stock++;
	}
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
