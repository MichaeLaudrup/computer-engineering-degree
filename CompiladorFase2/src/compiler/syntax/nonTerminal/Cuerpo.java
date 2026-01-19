package compiler.syntax.nonTerminal;

public class Cuerpo extends NonTerminal {
	private String identificadorFinalCuerpo;
	private int linea_id_fin_cuerpo; 
	private boolean existeSentenciaReturn; 
	private String tipoRetorno; 
	public Cuerpo() {
		existeSentenciaReturn = false; 
		tipoRetorno = "NO DEFINIDO"; 
		identificadorFinalCuerpo = ""; 
	}
	public Cuerpo( Cuerpo cuerpo) {
	
	}
	public void setTipoRetorno(String tipo_retorno) {
		this.tipoRetorno = tipo_retorno; 
	}
	
	public String getTipoRetorno() {
		return this.tipoRetorno; 
	}
	
	public boolean existeReturn() {
		return this.existeSentenciaReturn; 
	}
	
	public void sentenciaReturnEncontrada() {
		existeSentenciaReturn = true; 
	}
	
	
	/*
	 * Este metodo cambia el identificador del final de un cuerpo de programa o subrprograma
	 * @param String nuevo atributo a asignar
	 */
	public void setIdentificadorFinal(String identificador) {
		this.identificadorFinalCuerpo = identificador; 
	}
	/*
	 * Este metodo devuelve el identificador puesto justo antes del END de un cuerpo de programa
	 */
	public String getIdentificadorFinal() {
		return this.identificadorFinalCuerpo; 
	}
	
	/*
	 * Este metodo es para controlar el numero de linea en el que esta el identificador del programa con el fin de controlar la linea en la que se produce error
	 */
	public void setNumLineaIdFin(int linea ) {
		this.linea_id_fin_cuerpo = linea; 
	}
	
	public int getNumLineaIdFin() {
		return linea_id_fin_cuerpo; 	
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
