package analizadorLexico;
import java.io.*;
import java_cup.runtime.Symbol;

/* ==================== Declaraciones JFlex ==================== */
%%
%class Analizador
%unicode
%public
%cup
%line
%column

/* Constantes */
DIGITO  = [0-9]
LETRA   = [a-zA-Z]
ESPACIO = [ \t\r\n]+

%%

/* ==================== Reglas léxicas ==================== */

// Palabras clave
"PARTIR"      { return new Symbol(sym.PARTIR); }
"CREAR"       { return new Symbol(sym.CREAR); }
"INSERTAR"    { return new Symbol(sym.INSERTAR); }
"ELIMINAR"    { return new Symbol(sym.ELIMINAR); }
"DATO"        { return new Symbol(sym.DATO); }
"ASIGNAR"     { return new Symbol(sym.ASIGNAR); }
"FINALIZAR"   { return new Symbol(sym.FINALIZAR); }
"VER"         { return new Symbol(sym.VER); }

// Identificadores
L{LETRA}*{DIGITO}+   { return new Symbol(sym.IDENTIFICADOR, yytext()); } // Identificadores de listas
{LETRA}({LETRA}|{DIGITO})* { return new Symbol(sym.IDVALOR, yytext()); } // Identificadores generales

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