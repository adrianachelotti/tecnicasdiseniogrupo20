package controladores;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class Autenticador extends ActionSupport {

	String usuario;
	
	String password;
	
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String execute(){
		if(this.usuario.equalsIgnoreCase("root")&&this.password.equalsIgnoreCase("root")){
			return SUCCESS;
		}else{
			addActionError("Datos Incorrectos");
			return "error";
			
		}
			
		
	}
}
