package compiler.syntax.nonTerminal;

public class TipoRetorno extends NonTerminal{

	private String tipo_retorno; 
	public TipoRetorno(String tipo_retorno) {
		this.tipo_retorno = tipo_retorno; 
	}
	
	public boolean existeRetorno() {
		if(tipo_retorno.equalsIgnoreCase("NO_RETORNO")) {
			return false; 
		}else {
			return true; 
		}		
	}
	public String getTipoRetorno() {
		return this.tipo_retorno; 
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
