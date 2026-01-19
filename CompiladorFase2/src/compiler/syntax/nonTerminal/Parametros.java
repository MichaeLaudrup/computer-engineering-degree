package compiler.syntax.nonTerminal;
import java.util.ArrayList; 


public class Parametros extends NonTerminal{
	private ArrayList<String> lista_parametros; 
	
	
	public Parametros() {
		lista_parametros = new ArrayList<String>(); 
	}
	
	
	
	public ArrayList<String> getListaParametros(){
		return this.lista_parametros; 
	}
	
	
	public void insertarParametro(String nuevo_param) {
		lista_parametros.add(nuevo_param); 
	}
	public void imprimirLista() {
		for(int i = 0; i < this.lista_parametros.size();i++) {
			System.out.println(lista_parametros.get(i));	
		}
		
		
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
