package compiler.syntax.nonTerminal;

import java.util.ArrayList;

public class Sentencias extends NonTerminal{

	private boolean existeRetorno; 
	private boolean existenSentencias; 
	private String tipoRetorno; //puede existir varias sentencias return y hay comprobar que cada una de ellas coincide con el tipo de dato de la cabecera de la funcion marcado como retorno formal
	
	public Sentencias() {
		existeRetorno = false; 
		existenSentencias = true; 
		tipoRetorno = "NO RETORNO"; 
	}
	public void setTipoRetorno(String retorno) {
		this.tipoRetorno = retorno; 
	}
	
	public String getTipoRetorno() {
		return this.tipoRetorno; 
	}

	
	public void retornoEncontrado() {
		this.existeRetorno = true; 
	}
	
	public boolean existeSentenciaReturn() {
		return this.existeRetorno; 
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

	public boolean contieneSentencias() {
		return existenSentencias; 
		
		
	}
	
	public void noExistenSentencias() {
		this.existenSentencias = false; 
	}



}
