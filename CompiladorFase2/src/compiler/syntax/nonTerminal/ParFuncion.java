package compiler.syntax.nonTerminal;

import java.util.ArrayList;

public class ParFuncion extends NonTerminal{
	ArrayList<String> param_actuales; 
	
	public ParFuncion() {
		param_actuales = new ArrayList<String>(); 
	}
	public ParFuncion(ArrayList<String> lista_parametros) {
		this.param_actuales= lista_parametros;  
		
	}
	
	
	public ArrayList<String> getListaParametrosLlamada(){
		return this.param_actuales; 
	}
	
	public boolean existenParametrosLlamada() {
		if (param_actuales == null )
			return false;
		else
			return true; 
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
