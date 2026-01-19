package compiler.code;

import java.util.Arrays;
import java.util.List;

import compiler.intermediate.*;
import compiler.semantic.symbol.SymbolVariable;
import compiler.semantic.type.TypeSimple;

import es.uned.lsi.compiler.code.ExecutionEnvironmentIF;
import es.uned.lsi.compiler.code.MemoryDescriptorIF;
import es.uned.lsi.compiler.code.RegisterDescriptorIF;
import es.uned.lsi.compiler.intermediate.LabelFactory;
import es.uned.lsi.compiler.intermediate.LabelIF;
import es.uned.lsi.compiler.intermediate.OperandIF;
import es.uned.lsi.compiler.intermediate.QuadrupleIF;
import es.uned.lsi.compiler.intermediate.TemporalIF;

/**
 * Class for the ENS2001 Execution environment.
 */

public class ExecutionEnvironmentEns2001 
    implements ExecutionEnvironmentIF
{    
    private final static int      MAX_ADDRESS = 65535; 
    private final static String[] REGISTERS   = {
       ".PC", ".SP", ".SR", ".IX", ".IY", ".A", 
       ".R0", ".R1", ".R2", ".R3", ".R4", 
       ".R5", ".R6", ".R7", ".R8", ".R9"
    };
    
    private RegisterDescriptorIF registerDescriptor;
    private MemoryDescriptorIF   memoryDescriptor;
    
    /**
     * Constructor for ENS2001Environment.
     */
    public ExecutionEnvironmentEns2001 ()
    {       
        super ();
    }
    
    /**
     * Returns the size of the type within the architecture.
     * @return the size of the type within the architecture.
     */
    @Override
    public final int getTypeSize (TypeSimple type)
    {      
        return 1;  
    }
    
    /**
     * Returns the registers.
     * @return the registers.
     */
    @Override
    public final List<String> getRegisters ()
    {
        return Arrays.asList (REGISTERS);
    }
    
    /**
     * Returns the memory size.
     * @return the memory size.
     */
    @Override
    public final int getMemorySize ()
    {
        return MAX_ADDRESS;
    }
           
    /**
     * Returns the registerDescriptor.
     * @return Returns the registerDescriptor.
     */
    @Override
    public final RegisterDescriptorIF getRegisterDescriptor ()
    {
        return registerDescriptor;
    }

    /**
     * Returns the memoryDescriptor.
     * @return Returns the memoryDescriptor.
     */
    @Override
    public final MemoryDescriptorIF getMemoryDescriptor ()
    {
        return memoryDescriptor;
    }

    /**
     * Translate a quadruple into a set of final code instructions. 
     * @param cuadruple The quadruple to be translated.
     * @return a quadruple into a set of final code instructions. 
     */
    @Override
    public final String translate (QuadrupleIF quadruple)
    {      
        String instruccionEnCodigoFinal = ""; 
        String operando1 = "", operando2 = "", resultado = ""; 
        LabelFactory fabrica_etiquetas = new LabelFactory(); 
    	switch(quadruple.getOperation()) {
    	case "INICIAR_DATOS_GLOBALES": 
    		resultado = operandoATexto(quadruple.getResult()); 
    		instruccionEnCodigoFinal += "MOVE #0," + resultado;  
    		break; 
    	case "SUB": 
        	operando1 = operandoATexto(quadruple.getFirstOperand()); 
        	operando2 =operandoATexto(quadruple.getSecondOperand()); 
        	resultado = operandoATexto(quadruple.getResult()); 
        	instruccionEnCodigoFinal += "SUB " + operando1 + "," + operando2 + "\n"; 
        	instruccionEnCodigoFinal += "MOVE .A," + resultado ;
        	break; 
    	case "ADD": //SOLO SE UTILIZA LA SUMA PARA INCREMENTAR INDICE DE UN FOR 
    		operando1 = operandoATexto(quadruple.getFirstOperand()); 
        	operando2 =operandoATexto(quadruple.getSecondOperand()); 
        	resultado = operandoATexto(quadruple.getResult()); 
        	instruccionEnCodigoFinal += "ADD " + operando1 + "," + operando2 + "\n"; 
        	instruccionEnCodigoFinal += "MOVE .A," + resultado ;    		
    		break; 
       	case "MUL": 
    		operando1 = operandoATexto(quadruple.getFirstOperand()); 
        	operando2 =operandoATexto(quadruple.getSecondOperand()); 
        	resultado = operandoATexto(quadruple.getResult()); 
        	instruccionEnCodigoFinal += "MUL " + operando1 + "," + operando2 + "\n"; 
        	instruccionEnCodigoFinal += "MOVE .A," + resultado ;
    		break;
    	case "GR": 
    		operando1 = operandoATexto(quadruple.getFirstOperand()); 
        	operando2 =operandoATexto(quadruple.getSecondOperand()); 
        	resultado = operandoATexto(quadruple.getResult()); 
        	LabelIF etiqueta_menor = fabrica_etiquetas.create(); 
        	LabelIF etiqueta_fin = fabrica_etiquetas.create(); 
        	instruccionEnCodigoFinal += "CMP " + operando1 + ", " + operando2 + "\n"; 
        	instruccionEnCodigoFinal += "BN /"+ etiqueta_menor+ "\n";
        	instruccionEnCodigoFinal += "MOVE #1," + resultado + "\n";
        	instruccionEnCodigoFinal += "BR /"+ etiqueta_fin +"\n";
        	instruccionEnCodigoFinal += etiqueta_menor+":"+ "MOVE #0," + resultado + "\n";
        	instruccionEnCodigoFinal += etiqueta_fin + ":";
    		break; 
    	case "EQ": 
    		operando1 = operandoATexto(quadruple.getFirstOperand());
    		operando2 = operandoATexto(quadruple.getSecondOperand());
    		resultado = operandoATexto(quadruple.getResult()); 
    		LabelIF caso_igual = fabrica_etiquetas.create(); 
    		LabelIF fin_comparacion = fabrica_etiquetas.create(); 
    		instruccionEnCodigoFinal += "CMP "+ operando1 + "," + operando2 + "\n"; 
    		instruccionEnCodigoFinal += "BZ /"+ caso_igual+ "\n"; 
    		instruccionEnCodigoFinal += "MOVE #0, " + resultado + "\n";
    		instruccionEnCodigoFinal += "BR /" + fin_comparacion + "\n"; 
    		instruccionEnCodigoFinal += caso_igual+ ": MOVE #1, " + resultado + "\n";
    		instruccionEnCodigoFinal += fin_comparacion+ ":"; 
    		break; 
    	case "NOT": 
    		operando1 = operandoATexto(quadruple.getFirstOperand());
    		resultado = operandoATexto(quadruple.getResult()); 
    		LabelIF caso_negar_true = fabrica_etiquetas.create(); 
    		LabelIF fin_negacion = fabrica_etiquetas.create(); 
    		instruccionEnCodigoFinal += "CMP #1," + operando1 + "\n";
    		instruccionEnCodigoFinal += "BZ /" + caso_negar_true+"\n"; 
    		instruccionEnCodigoFinal += "MOVE #1," +resultado+"\n"; 
    		instruccionEnCodigoFinal += "BR /" + fin_negacion+"\n"; 
    		instruccionEnCodigoFinal += caso_negar_true+ ":MOVE #0," +resultado+"\n"; 
    		instruccionEnCodigoFinal += fin_negacion+":";  
    		break;  
    	case "NOP":
    		instruccionEnCodigoFinal += "NOP"; 
    		break; 
    	case "OR": 
    		operando1 = operandoATexto(quadruple.getFirstOperand()); 
    		operando2 = operandoATexto(quadruple.getSecondOperand());
    		resultado = operandoATexto(quadruple.getResult()); 
    		instruccionEnCodigoFinal += "OR " + operando1 + ", " + operando2+ "\n"; 
    		instruccionEnCodigoFinal += "MOVE .A," + resultado; 
    		break; 
        case "MVA":
        	resultado = operandoATexto(quadruple.getResult()); 
        	operando1 = String.valueOf(((Variable)quadruple.getFirstOperand()).getAddress()); 
        	instruccionEnCodigoFinal += "MOVE #" + operando1 + ","+ resultado;   
        	break; 
        case "MV":	
        	operando1 = operandoATexto(quadruple.getFirstOperand());
        	resultado = operandoATexto(quadruple.getResult()); 
        	instruccionEnCodigoFinal += "MOVE " + operando1 +","+ resultado; 
        	break; 
        case "MVP":
        	operando1 = operandoATexto(quadruple.getFirstOperand());
        	resultado = operandoATexto(quadruple.getResult()); 
        	instruccionEnCodigoFinal += "MOVE " + operando1 + ", .R1\n"; 
        	instruccionEnCodigoFinal += "MOVE " + "[.R1]" + "," + resultado; 
        	break; 
        case "STP":
        	operando1 = operandoATexto(quadruple.getFirstOperand()); 
        	resultado = operandoATexto(quadruple.getResult()); 
        	//{STP , resultado, operando1}
        	instruccionEnCodigoFinal += "MOVE " + resultado + "," + ".R1\n"; 
        	instruccionEnCodigoFinal += "MOVE " + operando1 +  ",[.R1]" ; 
        	break;
        case "INL":
        	resultado = operandoATexto(quadruple.getResult());
        	instruccionEnCodigoFinal += resultado + ":" ; 
        	break; 
        case "INLFIN": //SI HA ANIDAMIENTOS DE IF_ELSE o FOR SE PRODUCEN VARIAS ETIQUETAS SEGUIDAS SIN CONTENIDO, LO CUAL DA PROBLEMAS EN ENS2001
        	resultado = operandoATexto(quadruple.getResult());
        	instruccionEnCodigoFinal += resultado + ":" + "NOP"; 
        	break; 
        case "BRF": //salta si no se cumple condicion
        	operando1 = operandoATexto(quadruple.getFirstOperand()); 
        	resultado = operandoATexto(quadruple.getResult()); 
        	instruccionEnCodigoFinal += "CMP #0," +resultado+ "\n";   //se le resta al numero 0 al contenido dentro del IF
           	instruccionEnCodigoFinal += "BZ /"+ operando1;      //en caso de que la resta sea cero, implica que la condicion es falsa y se ejecuta el else
        	break; 
        case "BRT":
        	operando1 = operandoATexto(quadruple.getFirstOperand()); 
        	resultado = operandoATexto(quadruple.getResult()); 
        	instruccionEnCodigoFinal += "CMP #0," +resultado+ "\n";   //se le resta al numero 0 al contenido dentro del IF
           	instruccionEnCodigoFinal += "BN /"+ operando1;      //en caso de que la resta sea cero, implica que la condicion es falsa y se ejecuta el else
        	break; 
        case "BR": //salta incondicionalmente
        	operando1 = operandoATexto(quadruple.getResult()); 
        	instruccionEnCodigoFinal += "BR /" + operando1; 
        	break; 
        case "ESCRIBIR_ENTERO": 
        	operando1 = operandoATexto(quadruple.getResult()); 
        	instruccionEnCodigoFinal += "WRINT " + operando1 + "\n";
        	instruccionEnCodigoFinal += "WRCHAR #10";
        	break; 
        case "ESCRIBE_TEXTO": 
        	operando1 = operandoATexto(quadruple.getResult()); 
        	instruccionEnCodigoFinal += "WRSTR /" + operando1 +"\n"; 
        	instruccionEnCodigoFinal += "WRCHAR #10"; 
           	break; 
        case "ETIQUETA":
        	operando1 = operandoATexto(quadruple.getFirstOperand()); 
        	resultado = operandoATexto(quadruple.getResult()); 
        	instruccionEnCodigoFinal += operando1 + ":DATA \""+resultado+"\"";
        	break; 
        case "HALT":
        	instruccionEnCodigoFinal += "HALT"; 
        	break; 
        case "WRITELN": 
        	instruccionEnCodigoFinal += "WRCHAR #10"; 
        	break; 
        default: 
        	instruccionEnCodigoFinal += quadruple.toString(); 
        
        }
    	instruccionEnCodigoFinal += "\t\t\t\t;" + quadruple.toString() + "\n"; 
		return instruccionEnCodigoFinal; 
    	  
    }
    
    private String operandoATexto(OperandIF operando) {
    	
    	if(operando instanceof Variable) {
    		return  "/" + ((Variable)operando).getAddress(); 
    		
    	}else if(operando instanceof Value ) {
    		return "#" + ((Value)operando).getValue();
    		
    	}else if(operando instanceof Temporal) {
    		return "/" + ((Temporal)operando).getAddress(); 
    		
    	}else if(operando instanceof Label) {
    		return ((Label)operando).getName();
    		
    	}else {
    		return operando.toString(); 
    	}
    	
    	
    	
    	
    	
    }
    
    
}
