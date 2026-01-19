package compiler.semantic.symbol;

import es.uned.lsi.compiler.semantic.ScopeIF;
import es.uned.lsi.compiler.semantic.symbol.SymbolBase;
import es.uned.lsi.compiler.semantic.type.TypeIF;

/**
 * Class for SymbolConstant.
 */

// TODO: Student work
//       Include properties to characterize constants

public class SymbolConstant
    extends SymbolBase
{
	private String valor; 
    
    /**
     * Constructor for SymbolConstant.
     * @param scope The declaration scope.
     * @param name The symbol name.
     * @param type The symbol type.
     */
    public SymbolConstant (ScopeIF scope, String name, TypeIF type, String valor)
    {
        super (scope, name, type);
        this.valor = valor; 
    }
    
    public String getValor() {
    	return valor; 
    }
    
    
    public boolean esCteNumerica() {
    	 if(super.getType().getName().equalsIgnoreCase("ENTERO")) {
         	return true; 	
         }else {
         	return false; 
         }
    }
    
    public boolean getTipoSimbolo() {
    	return true; 
    }
}
