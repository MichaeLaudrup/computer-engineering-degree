package es.uned.Servidor;

import java.util.ArrayList;

public class MetaDato {
	private String id_propietario; 
	private String nombreFichero;
	private ArrayList<String> listadoDeUsuariosConPermiso;
	
	public MetaDato(String id_propietario, String nombreFichero) {
		this.id_propietario = id_propietario; 
		this.nombreFichero = nombreFichero; 
		listadoDeUsuariosConPermiso = new ArrayList<>(); 
	}
	
	public String getPropietario() {
		return id_propietario; 
	}
	
	public String getNombreArchivo() {
		return this.nombreFichero; 
	}
	
	public boolean tienePermiso(String id_cliente) {
		return listadoDeUsuariosConPermiso.contains(id_cliente); 
	}
	public void compartir(String id_cliente_compartir) {
		listadoDeUsuariosConPermiso.add(id_cliente_compartir); 
	}
}
