package compiler.syntax.nonTerminal;

public class IdArray extends NonTerminal{

	private String valor; 
	
	public IdArray() {
		
	}
	
	public void setValor(String valor) {
		
		this.valor = valor; 
	}
	
	public int getValor() {
		return Integer.parseInt(valor); 
	}
	
	public boolean tieneValorCalculableEnCompilacion() {	
		return (valor != null); 	
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
