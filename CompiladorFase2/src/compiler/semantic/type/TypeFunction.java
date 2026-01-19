package compiler.semantic.type;

import es.uned.lsi.compiler.semantic.ScopeIF;
import java.util.ArrayList; 
/**
 * Class for TypeFunction.
 */

// TODO: Student work
//       Include properties to characterize function declarations

public class TypeFunction
    extends TypeProcedure
{   
	private String tipo_retorno;  
    
    /**
     * Constructor for TypeFunction.
     * @param scope The declaration scope.
     */
    public TypeFunction (ScopeIF scope)
    {
        super (scope);
    }

    /**
     * Constructor for TypeFunction.
     * @param scope The declaration scope
     * @param name The name of the function.
     */
    public TypeFunction (ScopeIF scope, String name, String tipo_retorno, ArrayList<String> tipos_param_formales)
    {
        super(scope, name, tipos_param_formales);
        this.tipo_retorno = tipo_retorno; 
        
        
    }
    
    /**
     * Returns the size of the type.
     * @return the size of the type.
     */
    @Override
    public int getSize ()
    {
        // TODO: Student work
        return 1;
    }
}
