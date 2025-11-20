Aquí tienes todo en **Markdown limpio, ordenado y listo para entregar**:

---

# Tarea Compiladores

## Especificación de la tarea

El lenguaje **LMA** posee los siguientes tokens:

* **Palabras reservadas:**
  `PARTIR`, `CREAR`, `INSERTAR`, `ELIMINAR`, `DATO`, `ASIGNAR`, `FINALIZAR`

* **Identificadores:**
  Comienzan con la letra `L` y pueden contener más letras, finalizando siempre con un número entero.
  Ejemplos: `L2`, `L34`, `Las3`

* **Constantes enteras**

* **Símbolos:**
  `(` `)` `=` `,`

El programa se ingresa por pantalla y la salida se genera por pantalla.

---

## Tarea realizada

Se utilizó **JFlex** para construir el analizador léxico del lenguaje LMA.
El archivo **Lexer.flex** contiene todas las reglas del lenguaje y produce la clase `Analizador.java`.

Se asumió que las palabras reservadas se escriben **exclusivamente en mayúsculas**, sin variaciones.

También se utilizó **Java CUP** para el analizador sintáctico, generando las clases necesarias para el parser.

---

## Requisitos previos

* Contar con un **JDK** actual instalado.
* Tener los siguientes archivos `.jar` en el directorio raíz del proyecto:

  * `jflex-full-1.9.1.jar`
  * `java-cup-11b.jar`
  * `java-cup-11b-runtime.jar`

### Estructura recomendada del proyecto

```
/compiladores_tarea
 ├─ src/
 │   └─ analizadorLexico/
 │        ├─ Lexer.flex
 │        ├─ Parse.cup
 │        ├─ Main.java
 │        ├─ (Archivos generados por JFlex y CUP)
 ├─ bin/
 ├─ jflex-full-1.9.1.jar
 ├─ java-cup-11b.jar
 ├─ java-cup-11b-runtime.jar
```

# Forma de compilar

Todos los comandos deben ejecutarse desde el directorio raíz del proyecto.


## 1. Generar el Analizador Léxico (JFlex)

```cmd
java -jar jflex-full-1.9.1.jar src/analizadorLexico/Lexer.flex
```

Esto genera:

```
src/analizadorLexico/Analizador.java
```


## 2. Generar el Analizador Sintáctico (Java CUP)

```cmd
java -jar java-cup-11b.jar -parser Parser -symbols sym -destdir src/analizadorLexico src/analizadorLexico/Parse.cup
```
Esto genera:

```
src/analizadorLexico/Parser.java
src/analizadorLexico/sym.java
```


## 3. Compilar todos los archivos .java

### En Windows CMD

```cmd
javac -cp .;java-cup-11b.jar;java-cup-11b-runtime.jar -d bin src/analizadorLexico/*.java
```

### En PowerShell (obligatorio usar comillas)

```powershell
javac -cp ".;java-cup-11b.jar;java-cup-11b-runtime.jar" -d bin src/analizadorLexico/*.java
```


## 4. Ejecutar el programa

### CMD

```cmd
java -cp bin;java-cup-11b-runtime.jar analizadorLexico.Main
```

### PowerShell

```powershell
java -cp "bin;java-cup-11b-runtime.jar" analizadorLexico.Main
```