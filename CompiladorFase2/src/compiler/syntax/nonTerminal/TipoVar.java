package compiler.syntax.nonTerminal;

public class TipoVar extends NonTerminal{

	private String tipo_variable; 
	public TipoVar(String tipo_variable) {
		this.tipo_variable = tipo_variable; 
	}
	
	public String getTipoVar() {
		return tipo_variable; 
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
