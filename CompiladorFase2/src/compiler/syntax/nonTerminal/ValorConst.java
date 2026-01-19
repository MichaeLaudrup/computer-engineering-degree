package compiler.syntax.nonTerminal;

public class ValorConst extends NonTerminal{

	
	private String valor;
	private String tipo_cte;

	public ValorConst(String tipo_cte, String valor) {
		this.tipo_cte = tipo_cte; 
		this.valor = valor; 
	}
	
	
	public String getTipoConstante() {
		return tipo_cte; 
	}
	
	public String getValorConstante() {
		return valor; 	
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
