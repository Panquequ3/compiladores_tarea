package analizadorLexico;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static java.util.HashMap<String, Object> symtab = new java.util.HashMap<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String linea;
        boolean yaPartio = false;

        System.out.println("Empieza a Codificar");

        while (true) {
            System.out.print("> ");
            linea = br.readLine();
            if (linea == null) break; // EOF
            linea = linea.trim();
            if (linea.isEmpty()) continue;
            
            if(!yaPartio) {
                if(linea.equalsIgnoreCase("PARTIR")) {
                    yaPartio = true;
                } else {
                    System.out.println("Error: debes comenzar con PARTIR");
                    return;
                }
            }else if(yaPartio){
                if(linea.equalsIgnoreCase("PARTIR")) {
                    System.out.println("Error: se esperaba una instruccion o FINALIZAR");
                    return;
                }
            } else if(linea.equalsIgnoreCase("FINALIZAR")) {
                break;
            }

            try {
                // Lexer y parser temporal solo para esta línea
                Analizador lexerLinea = new Analizador(new java.io.StringReader(linea));
                Parser parserLinea = new Parser(lexerLinea);
                parserLinea.parse();
            } catch (Exception e) {
                System.out.println("Error en la instrucción: " + e.getMessage());
                System.out.println("Termino por error...");
                return;
            }
        }

        System.out.println("Programa terminado.");
    }
}
