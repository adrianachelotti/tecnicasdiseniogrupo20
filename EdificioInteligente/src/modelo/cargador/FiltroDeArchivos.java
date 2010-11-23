package modelo.cargador;

import java.io.File;
import java.io.FilenameFilter;

public class FiltroDeArchivos implements FilenameFilter{
	
	String extension;
	    
	FiltroDeArchivos(String extension){
		this.extension=extension;
    }
	
	public boolean accept(File dir, String name){
		return name.endsWith(extension);
    }
}

