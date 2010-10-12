package cliente;

import source.Accion;

public class AccionRepararMaquinaGasesosa implements Accion {

	private MaquinaGaseosa maquina;

	public void ejecutar() {
		maquina.setRota(!maquina.isRota());
		
	}
	public MaquinaGaseosa getMaquina() {
		return maquina;
	}
	public void setMaquina(MaquinaGaseosa maquina) {
		this.maquina = maquina;
	}

}
