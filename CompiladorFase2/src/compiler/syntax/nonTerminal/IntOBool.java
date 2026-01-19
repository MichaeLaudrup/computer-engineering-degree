package compiler.syntax.nonTerminal;

public class IntOBool extends NonTerminal{

	private String tipo; 
	public IntOBool() {
	}
	public IntOBool(String tipo) {
		this.tipo = tipo; 
	}
	
	public String getTipo() {
		return tipo; 
	}
	public void setTipo(String tipo) {
		this.tipo = tipo; 
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
