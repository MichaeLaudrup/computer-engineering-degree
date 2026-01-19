package compiler.syntax.nonTerminal;

import java.util.ArrayList;

public class CadIdVar extends NonTerminal{
	private String id_var; 
	private int contador_ids;  
	private ArrayList<String> listado_identificadores; 
	
	public CadIdVar(String primer_identificador) {
		listado_identificadores = new ArrayList<String>(); 
		listado_identificadores.add(primer_identificador);  
		contador_ids = 1; 	
		
	}
	
	/*
	 * Este metodo inserta un nuevo identificador en una estructura estatica de tipo vector, para luchar contra
	 * esta estaticidad y no tener que estar utilizando estructuras de datos dinamicas, cada vez que se inserta un 
	 * elemento se crea un nuevo vector con una posicion incrementada con respecto del anterior vector de identificadores
	 * existentes, se copia el contenido del anterior vector de ids y finalmente se añade el nuevo elemento
	 * @params nuevo_id: identificador que se quiere agregar.
	 */
	public void insertarNuevoIdentificador(String nuevo_id) {
		listado_identificadores.add(nuevo_id); 
	}
	
	
	
	public void setIdentificador(String id_var) {
		this.id_var = id_var; 
	}
	
	public String getIdentificador() {
		return id_var; 
	}
	
	public int getNumIds() {
		return this.contador_ids; 
	}
	
	
	
	public ArrayList<String> getListadoIdentificadores() {
		return this.listado_identificadores; 
	}
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}



	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}



}
