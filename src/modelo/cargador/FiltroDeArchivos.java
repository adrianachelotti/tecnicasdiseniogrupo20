package modelo.cargador;

import java.io.File;
import java.io.FilenameFilter;

/**
 * Clase que actua como filtro de archivos
 * @author Grupo20
 *
 */
public class FiltroDeArchivos implements FilenameFilter{
	
	String extension;
	
	/**
	 * Constructor de la clase.
	 * @param extension extension de los archivos a filtrar.
	 */
	public FiltroDeArchivos(String extension){
		this.extension=extension;
    }
	
	/**
	 * Determina si un archivo cumple con el filtro o no. 
	 * @param dir archivo a filtrar.
	 * @param name condiciones a cumplir.
	 * @return true si cumple con el filtro.
	 * 		   false en caso contrario.
	 */
	public boolean accept(File dir, String name){
		return name.endsWith(extension);
    }
		
}

