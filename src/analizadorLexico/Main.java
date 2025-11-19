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
            
            // Input de múltiples líneas
            System.out.println("Escribe tu entrada (múltiples líneas permitidas):");
            System.out.println("Para procesar: presiona Enter en una línea vacía");
            System.out.println("Para salir: escribe 'SALIR' en una línea vacía");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            
            StringBuilder entradaCompleta = new StringBuilder();
            String linea;
            
            while (true) {
                System.out.print("> ");
                linea = sc.nextLine();
                
                // Si escriben SALIR en una línea vacía, terminar programa
                if (linea.trim().equalsIgnoreCase("SALIR")) {
                    System.out.println("Saliendo...");
                    sc.close();
                    return;
                }
                
                // Si la línea está vacía, procesar la entrada acumulada
                if (linea.isEmpty()) {
                    if (entradaCompleta.length() == 0) {
                        System.out.println("No se ingresó ningún texto. Intenta de nuevo.\n");
                        break;
                    }
                    // Salir del bucle de entrada para procesar
                    break;
                }
                
                // Agregar la línea a la entrada completa
                entradaCompleta.append(linea).append("\n");
            }
            
            // Si no hay entrada, continuar al siguiente ciclo
            if (entradaCompleta.length() == 0) {
                continue;
            }
            
            String textoCompleto = entradaCompleta.toString();

            // Limpiar la terminal
            System.out.print("\033[H\033[2J");
            System.out.flush();

            // Se muestra el texto
            System.out.println("Texto ingresado:");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println(textoCompleto);
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

            Analizador lexer = new Analizador(new StringReader(textoCompleto));
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
    }
}
