package compiler.syntax.nonTerminal;

public class ExprArit extends NonTerminal{
	private String valorExpresionNumSimple; 
	
	public ExprArit() {
		 
	}
	public ExprArit(String valor) {
		this.valorExpresionNumSimple = valor; 
	}
	
	public String getValorNumericoSimple() {
		return valorExpresionNumSimple; 
	}
	
	public boolean esUnNumeroSimple() {
		return (valorExpresionNumSimple != null); 
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
