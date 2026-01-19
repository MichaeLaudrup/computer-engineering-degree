package compiler.syntax.nonTerminal;

import java.util.ArrayList; 
import es.uned.lsi.compiler.semantic.symbol.SymbolBase;

public class ProcListParam extends NonTerminal{
	private ArrayList<String> lista_parametros_formales; 
	
	public ProcListParam(String tipo) {
		lista_parametros_formales = new ArrayList<String>(); 
		lista_parametros_formales.add(tipo); 	
	}
	
	public ProcListParam() {
		 lista_parametros_formales = new ArrayList<String>(); 
	}
	
	public void insertarParametroFormal(String nuevo_param) {
		lista_parametros_formales.add(nuevo_param); 
	}
	
	
	public ArrayList<String> getListaParametrosFormales() {
		return this.lista_parametros_formales; 
	}
	
	public boolean esVacio() {
		return lista_parametros_formales.isEmpty(); 
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
