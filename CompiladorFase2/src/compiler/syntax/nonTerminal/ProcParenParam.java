package compiler.syntax.nonTerminal;
import java.util.ArrayList;
import java.util.Collections; 

public class ProcParenParam extends NonTerminal{
	private boolean existenParametros; 
	private ArrayList<String> listaParametrosFormales; 
	
	public ProcParenParam() {
	}
	
	
	public void setExistenParametros(boolean existenParametros) {
		this.existenParametros = existenParametros; 
	}
	
	public boolean existenParametrosEntrada() {
		return this.existenParametros; 
	}
	
	public void insertarTodosParametros(ArrayList<String> param_formales) {
		//SE INVIERTE EL VECTOR DE PARAMETROS FORMALES RECIBIDOS POR CUESTION DE ORDEN
		Collections.reverse(param_formales); 
		this.listaParametrosFormales = param_formales; 
		
	}
	
	
	
	public int getNumParam() {
		return this.listaParametrosFormales.size();
	}
	public ArrayList<String> getTiposFormarles() {
		return this.listaParametrosFormales; 
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
