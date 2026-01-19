package compiler.syntax.nonTerminal;

public class CabProcedure extends NonTerminal{
	private String nombre_proceso;
	private String tipo_proceso; 
	private String tipo_retorno_proceso; 
	
	
	public CabProcedure(String nombre_proceso) {
		this.nombre_proceso = nombre_proceso; 
		this.tipo_retorno_proceso = ""; 
	}
	public CabProcedure( CabProcedure cabProcedure) {
	}
	
	
	public String getNombreProceso() {
		return this.nombre_proceso; 
	}
	
	public void setTipoSubProgram(String tipo_proceso) {
		this.tipo_proceso = tipo_proceso; 
	}
	
	public String getTipoSubprogram() {
		return this.tipo_proceso; 
	}
	
	
	public String getTipoRetorno() {
		return this.tipo_retorno_proceso; 
	}
	
	public void setTipoRetorno(String tipo_retorno) {
		this.tipo_retorno_proceso = tipo_retorno; 
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
