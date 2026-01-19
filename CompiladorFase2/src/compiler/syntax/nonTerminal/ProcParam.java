package compiler.syntax.nonTerminal;

public class ProcParam extends NonTerminal{
	private int num_parametros; 
	private String nombre_tipo; 
	
	public ProcParam() {
		this.num_parametros = 0; 
	}
	public void incrementarNumParametros() {
		this.num_parametros++; 
	}
	
	public void setNombreTipo(String nombre_tipo) {
		this.nombre_tipo = nombre_tipo; 
	}
	
	public String getNombreTipo() {
		return nombre_tipo; 
	}
	 public int getNumParametros() {
		 return this.num_parametros; 
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
