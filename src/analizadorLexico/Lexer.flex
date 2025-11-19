package analizadorLexico;
import java.io.*;
import java_cup.runtime.Symbol;


/* ==================== Declaraciones JFlex ==================== */
/* Define las directivas de JFlex */
%%
%class Analizador /* Define el nombre de la clase generada */
%unicode
%public /* Define la clase como publica */
%cup /* Tipo de dato que retornan las acciones */
%line /* contador de lineas (Guarda el número de línea actual donde se encontró el token) */
%column /* Habilita contador de columnas (Guarda la posición en la línea actual) */
		/* line y column sirven para el MANEJO DE ERRORES precisos */

/*Constantes*/
/* Definición de expresiones regulares */
DIGITO  = [0-9]
LETRA   = [a-zA-Z]
ESPACIO = [ \t\r\n]+

%%

/* ==================== Reglas léxicas ==================== */

// Palabras clave primero
"PARTIR"      { return new Symbol(sym.PARTIR); }
"CREAR"       { return new Symbol(sym.CREAR); }
"INSERTAR"    { return new Symbol(sym.INSERTAR); }
"ELIMINAR"    { return new Symbol(sym.ELIMINAR); }
"DATO"        { return new Symbol(sym.DATO); }
"ASIGNAR"     { return new Symbol(sym.ASIGNAR); }
"FINALIZAR"   { return new Symbol(sym.FINALIZAR); }
"VER"         { return new Symbol(sym.VER); }

// Identificadores (no coincide con palabras clave)
L({LETRA}*{DIGITO}+)   { return new Symbol(sym.IDENTIFICADOR, yytext()); }
{LETRA} ({LETRA}|{DIGITO})* { return new Symbol(sym.ID_VALOR, yytext()); }

// Números
{DIGITO}+   { return new Symbol(sym.NUMERO, Integer.parseInt(yytext())); }

// Operadores
"("         { return new Symbol(sym.PARENTESIS_IZQ); }
")"         { return new Symbol(sym.PARENTESIS_DER); }
","         { return new Symbol(sym.COMA); }
"="         { return new Symbol(sym.IGUAL); }

// Ignorar espacios
{ESPACIO}   { /* ignorar espacios y saltos de línea */ }

// Fin de archivo
<<EOF>>     { return new Symbol(sym.EOF); }


// Cualquier otro caracter
. { 
    System.err.println("Caracter ilegal: " + yytext());
    return new Symbol(sym.DESCONOCIDO, yytext());
}


