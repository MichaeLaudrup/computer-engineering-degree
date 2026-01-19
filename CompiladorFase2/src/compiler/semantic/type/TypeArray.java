package compiler.semantic.type;

import compiler.CompilerContext;
import es.uned.lsi.compiler.code.ExecutionEnvironmentIF;
import es.uned.lsi.compiler.semantic.ScopeIF;
import es.uned.lsi.compiler.semantic.type.TypeBase;

/**
 * Class for TypeArray.
 */

// TODO: Student work
//       Include properties to characterize array type

public class TypeArray
    extends TypeBase
{   
    private String limite_inferior; 
    private String limite_superior; 
    private String tipoDatos_vector; 
    private int tamanio_vector; 
	/**
     * Constructor for TypeArray.
     * @param scope The declaration scope.
     */
    public TypeArray (ScopeIF scope)
    {
        super (scope);
    }

    /**
     * Constructor for TypeArray.
     * @param scope The declaration scope.
     * @param name The name of the type.
     */
    public TypeArray (ScopeIF scope, String name, String tipoDatos_vector)
    {
        super (scope, name);
        this.tipoDatos_vector = tipoDatos_vector; 
    }
    
    public void setLimiteInferior(String limite_inferior) {
    	this.limite_inferior = limite_inferior; 
    }
    
    public void setLimiteSuperior(String limite_superior) {
    	this.limite_superior = limite_superior; 
    }
    public int getLimiteSuperior() {
    	return Integer.parseInt(limite_superior);
    }
    
    public int getLimiteInferior() {
    	return Integer.parseInt(limite_inferior);	
    }
    
    public String getTipoDatoVector() {
    	return this.tipoDatos_vector; 
    }
    
    public int getLongitud() {
    	this.tamanio_vector = ((Integer.parseInt(limite_superior))-(Integer.parseInt(limite_inferior))) + 1; 
        return tamanio_vector;
    }
    
    public void setSize(int size) {
    	
    }
    /**
     * Returns the size of the type.
     * @return the size of the type.
     */
    @Override
    public int getSize ()
    {
    	ExecutionEnvironmentIF environment = CompilerContext.getExecutionEnvironment ();
        return ( getLongitud()* environment.getTypeSize((TypeSimple)this.getScope().getTypeTable().getType(tipoDatos_vector)));
    }
    
   
}
