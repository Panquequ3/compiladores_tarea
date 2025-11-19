package analizadorLexico;
import java.io.*;

/* ==================== Declaraciones JFlex ==================== */
/* Define las directivas de JFlex */
%%
%class Analizador /* Define el nombre de la clase generada */
%unicode
%public /* Define la clase como publica */
%type String /* Tipo de dato que retornan las acciones */
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
"PARTIR"       { return "PARTIR"; }
"CREAR"     { return "CREAR"; }
"INSERTAR"    { return "INSERTAR"; }
"ELIMINAR"  { return "ELIMINAR";}
"DATO"   { return "DATO";}
"ASIGNAR"   { return "ASIGNAR";}
"FINALIZAR"   { return "FINALIZAR";}
"VER"	{return "VER";}

// Identificadores (no coincide con palabras clave)
L({LETRA}*{DIGITO}+)   { return "IDENTIFICADOR"; }
{LETRA} ({LETRA}|{DIGITO})* { return "ID_VALOR";}

// Números
{DIGITO}+   { return "NUMERO"; }

// Operadores
"("         { return "PARENTESIS_IZQ"; }
")"         { return "PARENTESIS_DER"; }
","         { return "COMA"; }
"="         { return "IGUAL"; }

// Ignorar espacios
{ESPACIO}   { /* ignorar espacios y saltos de línea */ }

// Fin de archivo
<<EOF>>     { return "EOF"; }

// Cualquier otro caracter
.           { return "DESCONOCIDO"; }

