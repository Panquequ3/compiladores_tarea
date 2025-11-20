# Tarea Compiladores
## Especificacion de la tarea
El lenguaje LMA, posee los siguientes tokens:

· Palabras reservadas: PARTIR, CREAR, INSERTAR, ELIMINAR, DATO, ASIGNAR, FINALIZAR

· Identificadores: comienzan con la letra L y a continuación pueden tener más letras, para finalizar con un numero entero. (L2, L34, Las3)

· Constantes enteras

· Símbolos: ( ) = ,


El programa se ingresa por pantalla y la salida se produce también por pantalla

## Tarea realizada
Se utilizo JFlex para poder hacer la tarea, donde el archivo **Lexer.flex** contiene el lenguaje completo para poder hacer funcionar el codigo completo.

Se asumio que las palabras reservadas son exclusivamente mayusculas y no alguna variacion de estas.

## Requisitos previos
- Tener instalado JDK actual
- En caso de no poseer jflex dentro de la carpeta, descargar la version 1.9.1 y dejar la libreria dentro

## Forma de compilar

Para poder ejecutar todo este codigo se realiza desde consola.

Primero se tiene que compilar el .flex para obtener la clase que sirve para ejecutar en el main. Para esto se realiza 
```cmd
java -jar jflex-full-1.9.1.jar src/analizadorLexico/Lexer.flex
```

y asi se obtiene el Analizador.java

Luego de asegurarnos que esto ya esta creado se realiza
```cmd
javac -d bin src/analizadorLexico/*.java
```

Ademas para trabajar con el Analizador sintactico se tiene que usar
```cmd
java -jar java-cup-11b.jar -parser Parser -symbols Sym -destdir src/analizadorLexico src/analizadorLexico/Parse.cup
```

```cmd
javac -cp .;java-cup-11b-runtime.jar -d bin src/analizadorLexico/*.java
```

Para generar todas las clases y finalmente se usa
```cmd
java -cp bin;java-cup-11b-runtime.jar Main
```

Para hacer que este compile
