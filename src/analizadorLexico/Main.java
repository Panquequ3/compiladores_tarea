package analizadorLexico;

import java.io.StringReader;
import java.util.Scanner;
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        // Para ordenarlos
        ArrayList<String> Operador = new ArrayList<>();
        ArrayList<String> Identificador = new ArrayList<>();
        ArrayList<String> Constante = new ArrayList<>();
        ArrayList<String> PalabraClave = new ArrayList<>();
        // Para separar las palabras claves
        Set<String> palabrasClave = new HashSet<>();
        palabrasClave.add("PARTIR");
        palabrasClave.add("CREAR");
        palabrasClave.add("INSERTAR");
        palabrasClave.add("ELIMINAR");
        palabrasClave.add("DATO");
        palabrasClave.add("ASIGNAR");
        palabrasClave.add("FINALIZAR");
        // Para los Operadores
        Set<String> tokensOperadores = new HashSet<>();
        tokensOperadores.add("PARENTESIS_IZQ");
        tokensOperadores.add("PARENTESIS_DER");
        tokensOperadores.add("COMA");
        tokensOperadores.add("IGUAL");
        
        //Funcion loop para el analizador
        while (true) {
            // Limpiamos los arreglos
            Operador.clear();
            Identificador.clear();
            Constante.clear();
            PalabraClave.clear();
            // Input
            System.out.println("Escribe una entrada (Enter para procesar, línea vacía para salir):");
            System.out.print("> ");
            String linea = sc.nextLine();

            // Si la entrada esta vacia, se sale del programa
            if (linea.isEmpty()) {
                System.out.println("Saliendo...");
                break;
            }

            // Limpiar la terminal
            System.out.print("\033[H\033[2J");
            System.out.flush();

            // Se muestra el texto
            System.out.println("Texto ingresado:\n> " + linea);
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

            Analizador lexer = new Analizador(new StringReader(linea));
            String token;
            	
            // Se muestran los token
            while (!(token = lexer.yylex()).equals("EOF")) {
                //Separa
                System.out.println("Token \"" + lexer.yytext() + "\" fue identificado como: " + token);

                if (palabrasClave.contains(token)) {
                    PalabraClave.add(lexer.yytext());
                } else if (tokensOperadores.contains(token)) {
                    Operador.add(lexer.yytext());
                } else if (token.equals("IDENTIFICADOR")) {
                    Identificador.add(lexer.yytext());
                } else if (token.equals("NUMERO")) {
                    Constante.add(lexer.yytext());
                }
            }
            //muestra el resultado final
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
            System.out.println("Palabras Clave :"+PalabraClave );
            System.out.println("Operadores :"+Operador );
            System.out.println("Identificadores :"+Identificador );
            System.out.println("Constantes :"+Constante );
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
        }

        sc.close();
    }
}
