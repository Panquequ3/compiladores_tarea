package analizadorLexico;

import java.io.*;
import java_cup.runtime.*;

public class Main {

    public static java.util.HashMap<String,Object> symtab = new java.util.HashMap<>();

    public static void main(String[] args) throws Exception {
        PipedReader reader = new PipedReader();
        PipedWriter writer = new PipedWriter(reader);

        Analizador lexer = new Analizador(reader);
        Parser parser = new Parser(lexer);

        Thread parserThread = new Thread(() -> {
            try {
                parser.parse();
            } catch (Exception e) {
                System.err.println("Error en parser: " + e.getMessage());
            }
        });
        parserThread.start();

        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

        boolean iniciado = false;
        String linea;

        System.out.println("Intérprete interactivo continuo. La primera instrucción debe ser PARTIR. FINALIZAR para salir.");

        while ((linea = console.readLine()) != null) {
            linea = linea.trim();

            if (!iniciado) {
                if (!linea.equalsIgnoreCase("PARTIR")) {
                    System.out.println("Error: la primera instrucción debe ser PARTIR.");
                    continue;
                }
                iniciado = true;
                System.out.println("PARTIR aceptado. Puedes ejecutar comandos.");
            }

            writer.write(linea + "\n");
            writer.flush();

            if (linea.equalsIgnoreCase("FINALIZAR")) {
                writer.close();
                break;
            }
        }

        parserThread.join();
        System.out.println("Intérprete terminado.");
    }
}
