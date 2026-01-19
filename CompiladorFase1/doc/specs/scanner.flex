package compiler.lexical;

import compiler.syntax.sym;
import compiler.lexical.Token;
import es.uned.lsi.compiler.lexical.ScannerIF;
import es.uned.lsi.compiler.lexical.LexicalError;
import es.uned.lsi.compiler.lexical.LexicalErrorManager;

//---------->DIRECTIVAS (Configuracion del escaneador)

%%
 
%public					//la clase sera publica
%class Scanner			//da nombre a clase generada y que escriba el codigo en un archivo java 

%char					//definicion de variable yychar para contar caracteres
%line					//definicion de variable yyline para indicar la linea
%column					//definicion  de variable yycolumn para indicar columna
%cup					//Activa modo de compatibilidad con CUP (parte sintactica)



%implements ScannerIF   
%scanerror LexicalError

//----------> DIRECTIVAS INSERTADAS POR EL ALUMNO

%full					         //extension del alfabeto basico a un alfabeto de 8 bits
%ignorecase                      //no se  diferenciara entre mayuscula ni minusculas

%{

  LexicalErrorManager lexicalErrorManager = new LexicalErrorManager ();
  private int commentCount = 0;
  
  /*dado que el fragmento de codigo que crea y retorna un token ha de repetirse constantemente
    dentro de la definicion de cada regla lexica, se hace una abstraccion para evitar duplicacion de codigo  */
  	  /*
  	   * Funcion ue crea y devuelve un token dependiendo de un identificador
  	   * @param codigo_identificador del token
  	   */	
	  private Token crear_RetornarToken(int codigo_identificador){
	  						   Token token = new Token (codigo_identificador);
	                           token.setLine (yyline + 1);
	                           token.setColumn (yycolumn + 1);
	                           token.setLexema (yytext ());
	           			       //Esto se le envia al parser.cup
	           			       return token;
  }
  
%}  

//----------> DEFINICIONES DE EXPRESIONES REGULARES MEDIANTE MACROS

LETRA = [a-zA-Z] 
DIGITO =[0-9]	
NUMERO_ENTERO = ({DIGITO})+
IDENTIFICADOR = ({LETRA}|_)({LETRA}|{DIGITO}|_)* 
COMILLAS_DOBLES = \" 
DELIMITADOR_COMENTARIO = "#"
COMENTARIO = {DELIMITADOR_COMENTARIO}(.)*{ESPACIO_BLANCO} 
CADENA_TEXTO = \" .* \"
ESPACIO_BLANCO=[ \t\r\n\f]

fin = "fin"{ESPACIO_BLANCO}


%%
  
   /* YYINITIAL es el estado inicial del analizador lexico al escanear*/


<YYINITIAL> 
{
    //---------->DEFINICION DE REGLAS LEXICAS DEL ALUMNO 
    //---------->PALABRAS RESERVADAS
     
    "booleano"          {return crear_RetornarToken(sym.BOOLEANO);}
    
    "cierto"            {return crear_RetornarToken(sym.CIERTO);}
     
    "comienzo"          {return crear_RetornarToken(sym.COMIENZO);}
       			       
    "constantes"		{return crear_RetornarToken(sym.CONSTANTES);}
    
    "de"         		{return crear_RetornarToken(sym.DE);}
    
    "en"				{return crear_RetornarToken(sym.EN);}
    
    "devolver"			{return crear_RetornarToken(sym.DEVOLVER);}
    
    "entero"			{return crear_RetornarToken(sym.ENTERO);}
    
    "entonces"			{return crear_RetornarToken(sym.ENTONCES);}
   
    "escribir"			{return crear_RetornarToken(sym.ESCRIBIR);}
   
    "falso"			    {return crear_RetornarToken(sym.FALSO);}
    
    "fin"			    {return crear_RetornarToken(sym.FIN);}   
    
    "funcion"			{return crear_RetornarToken(sym.FUNCION);} 
    
    "no"			    {return crear_RetornarToken(sym.NO);} 
    
    "para"			    {return crear_RetornarToken(sym.PARA);} 
    
    "procedimiento"		{return crear_RetornarToken(sym.PROCEDIMIENTO);}               
    
    "programa"          {return crear_RetornarToken(sym.PROGRAMA);}
    
    "si"				{return crear_RetornarToken(sym.SI);}
    
    "sino"				{return crear_RetornarToken(sym.SINO);}
    
    "Subprogramas"		{return crear_RetornarToken(sym.SUBPROGRAMAS);}
    
    "tipos"				{return crear_RetornarToken(sym.TIPOS);}
    
    "var"				{return crear_RetornarToken(sym.VAR);}
    
    "variables"			{return crear_RetornarToken(sym.VARIABLES);}
    
    "vector" 			{return crear_RetornarToken(sym.VECTOR);}
    
    "y"					{return crear_RetornarToken(sym.YLOGICO);}
     
    "fin si;"          {return crear_RetornarToken(sym.FINALCONDICIONAL);}
    "fin para; "      {return crear_RetornarToken(sym.FINPARA);}
     
    
    "procedure" 	    {return crear_RetornarToken(1);}
    

    
    //---------->DELIMITADORES
    		
    		
    {COMILLAS_DOBLES}         {return crear_RetornarToken(sym.COMILLASDOBLE);}
    
    "("					      {return crear_RetornarToken(sym.PARENIZQ);}
    
    ")"					      {return crear_RetornarToken(sym.PARENDER);}
    
    "["					      {return crear_RetornarToken(sym.CORCHEIZQ);}
    
    "]"					      {return crear_RetornarToken(sym.CORCHEDER);}
    
    
    "."						  {return crear_RetornarToken(sym.PUNTO);}
    
    ","					      {return crear_RetornarToken(sym.COMA);}
    
    ";"					      {return crear_RetornarToken(sym.PUNTOCOMA);}
			  
	":"					      {return crear_RetornarToken(sym.DOSPUNTOSVERTICALES);}
	
	".."					  {return crear_RetornarToken(sym.DOSPUNTOSHORIZONTALES);}        
   
   //---------->OPERADORES
   
   "+"					  {return crear_RetornarToken(sym.SUMA);}
   
   "*"					  {return crear_RetornarToken(sym.PRODUCTO);}
   
   "<"					  {return crear_RetornarToken(sym.MENOR);}
   
   "="					  {return crear_RetornarToken(sym.IGUAL);}
   
   "=="					  {return crear_RetornarToken(sym.CONDICIONIGUAL);}
   
   
   
   

               //CASOS ESPECIALES 
   {COMENTARIO}           {commentCount++;
   							System.out.println("Se ha leido el comentario: " + yytext()); 
    					  }
    					  
   {IDENTIFICADOR}        {return crear_RetornarToken(sym.IDENTIFICADOR);}
   
   
   {ESPACIO_BLANCO}	{}

   {fin} {}
   
   {NUMERO_ENTERO} {return crear_RetornarToken(sym.NUMEROENTERO);}
    
   {CADENA_TEXTO}  {return crear_RetornarToken(sym.CADENATEXTO);}
    
    // error en caso de coincidir con ningún patrón
	[^]     
                        {                                               
                           LexicalError error = new LexicalError ();
                           error.setLine (yyline + 1);
                           error.setColumn (yycolumn + 1);
                           error.setLexema (yytext ());
                           lexicalErrorManager.lexicalError (error);
                        }
    
}


                         


