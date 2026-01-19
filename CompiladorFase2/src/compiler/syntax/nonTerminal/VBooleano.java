package compiler.syntax.nonTerminal;

public class VBooleano extends NonTerminal{
	private String valor_logico; 
	
	public VBooleano(String valor_logico) {
		this.valor_logico = valor_logico;
	}
	
	
	public String obtenerValorLogico() {
		return this.valor_logico; 
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
