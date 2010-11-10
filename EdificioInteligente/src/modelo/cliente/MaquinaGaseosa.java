package modelo.cliente;

import java.util.ArrayList;
import java.util.List;

public class MaquinaGaseosa {
	
	private boolean rota = false;
	public boolean isRota() {
		return rota;
	}

	public void setRota(boolean rota) {
		this.rota = rota;
	}
	private List<Gaseosa> gaseosas = new ArrayList<Gaseosa>();

	public List<Gaseosa> getGaseosas() {
		return gaseosas;
	}

	public void agregarGaseosa(Gaseosa gaseosa){
		this.gaseosas.add(gaseosa);
	}
	public void setGaseosas(List<Gaseosa> gaseosas) {
		this.gaseosas = gaseosas;
	}

}
