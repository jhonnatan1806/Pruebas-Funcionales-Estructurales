# Pruebas basadas en especificaciones

## Ejercicio 1

:question: **Pregunta**

Escribe el código de prueba y considera las entradas str = "axcaycazc", open = "a" y close = "c" y explica lo que hace el código anterior.

```java
    public static  final String[] EMPTY_STRING_ARRAY = new String[0];

    public static boolean isEmpty(final CharSequence cs) {
        return cs == null || cs.isEmpty();
    }

    public static String[] substringsBetween(final String str, final String open, final String close) {
        if (str == null || isEmpty(open) || isEmpty(close)) {
            return null;
        }
        final int strLen = str.length();
        if (strLen == 0) {
            return EMPTY_STRING_ARRAY;
        }
        final int closeLen = close.length();
        final int openLen = open.length();
        final List<String> list = new ArrayList<>();
        int pos = 0;
        while (pos < strLen - closeLen) {
            int start = str.indexOf(open, pos);
            if (start < 0) {
                break;
            }
            start += openLen;
            final int end = str.indexOf(close, start);
            if (end < 0) {
                break;
            }
            list.add(str.substring(start, end));
            pos = end + closeLen;
        }
        if (list.isEmpty()) {
            return null;
        }
        return list.toArray(EMPTY_STRING_ARRAY);
    }
```

:white_check_mark: **Respuesta**

El código anterior es una implementación del método `substringBetween()` que recibe una cadena de texto y dos caracteres, `open` y `close`,
y devuelve un array de cadenas de texto que están entre los caracteres `open` y `close` en la cadena de texto.
El método recorre la cadena de texto y busca las ocurrencias de `open` y `close` para extraer las subcadenas que están entre ellos.

```java
    @Test
    void testSubstringsBetween() {
        Assertions.assertThat(StringUtils.substringsBetween("axcaycazc", "a", "c")).isEqualTo(new String[] { "x", "y", "z" });
    }
```

## Ejercicio 2

:question: **Pregunta**

Revisa los requisitos una vez más y escribe todos los casos de prueba que se te ocurran.
El formato no importa, puede ser algo así como "todos los parámetros son nulos". Cuando hayas
terminado con esta nota, compara tu conjunto de pruebas inicial con el que estamos a punto de
derivar.

**Paso 1: Comprensión de los requisitos, entradas y salidas**

Para el método substringsBetween():

- El objetivo de este método es recopilar todas las subcadenas en una cadena que están
delimitadas por una etiqueta open y una etiqueta close (el usuario las proporciona).
- El programa recibe tres parámetros:
  - str, que representa la cadena de la que el programa extraerá las subcadenas
  - La etiqueta open, que indica el inicio de una subcadena
  - La etiqueta close, que indica el final de la subcadena
- El programa devuelve un arreglo compuesto por todas las subcadenas encontradas por el
programa.

**Paso 2: Explora lo que hace el programa para varias entradas**

- Pasa la cadena "abcd" con la etiqueta open "a" y la etiqueta close "d". Se espera que devuelva un
arreglo con un solo elemento: ["bc"]. Intenta eso (en una prueba unitaria), y el programa devuelve
lo que se esperaba.
- A continuación, vemos qué sucede si hay varias subcadenas en la cadena principal. Pasa la cadena
"abcdabcdab" con las mismas etiquetas de open y close. Se espera que devuelva un arreglo con dos
cadenas: ["bc", "bc"]. El programa devuelve lo que se esperaba.
- Se espera que el programa se comporte de la misma manera con etiquetas open y close más
grandes que un solo carácter. Repite la segunda prueba, doblando las “a”s y las “d”s en todos los
parámetros. También cambia uno de los "bc" a "bf", para que sea más fácil comprobar que el
método devuelve dos subcadenas diferentes: ["bc", "bf"]. El programa devuelve lo que se esperaba.

:white_check_mark: **Respuesta**

Casos de prueba

- Caso 1: substring dentro de la cadena
- Caso 2: más de un substring dentro de la cadena
- Caso 3: cadena con tags de inicio y fin de longitud > 1

```java
    @Test
    void simpleCase() {
        assertThat(stringUtils.substringsBetween("abcd", "a", "d")).isEqualTo(new String[] { "bc" });
    }

    @Test
    void manyStrings() {
        assertThat(stringUtils.substringsBetween("abcdabcdab", "a", "d")).isEqualTo(new String[] { "bc", "bc" });
    }
    
    @Test
    void openAndCloseTagsThatAreLongerThan1Char() {
        assertThat(stringUtils.substringsBetween("aabcddaabfddaab", "aa", "dd")).isEqualTo(new String[] { "bc", "bf" });
    }
```

## Ejercicio 3

:question: **Pregunta**

Escribe un código de prueba llamado stringUtilsExploracionTest.java que albergue el
código anterior.

**Paso 3: Explora las posibles entradas y salidas, e identifica las particiones**

- Cada entrada individualmente: "¿Cuáles son las posibles clases de entradas que puedo
proporcionar?"
- Cada entrada en combinación con otras entradas: "¿Qué combinaciones puedo probar entre
las etiquetas de open y close?"
- Las diferentes clases de resultados que se esperan de este programa: “¿Devuelve arreglos?
¿Puede devolver un arreglo vacío? ¿Puede devolver valores nulos?"

**Paso 4: Analiza los límites**

Cada vez que identificamos un límite, ideamos dos pruebas para él, una para cada lado del límite.
Para el límite “sin subcadena”/“una subcadena”, las dos pruebas son las siguientes:

- str contiene etiquetas tanto de open como de close, sin caracteres entre ellas.
- str contiene etiquetas tanto de open como de close, con caracteres entre ellas.

**Paso 5: Idear casos de prueba**

Con las entradas, salidas y límites correctamente diseccionados, podemos generar casos de prueba
concretos.

:white_check_mark: **Respuesta**

```bash
# Completar aqui
```

## Ejercicio 4

:question: **Pregunta**

¿En nuestro ejemplo cuál es el número de pruebas?

Puede haber otras particiones que no necesiten combinarse por completo. En este problema hay
dos:

- Para el caso de cadena de longitud 1, dado que la cadena tiene longitud 1, dos pruebas
pueden ser suficientes: una en la que el carácter único de la cadena coincida con open y
close, y otra en la que no.
- A menos que tengamos una buena razón para creer que el programa maneja etiquetas de
open y close de diferentes longitudes de diferentes maneras, no necesitamos las cuatro
combinaciones de (longitud de open = 1, longitud de close = 1), (longitud de open > 1,
longitud de close = 1), (longitud de open = 1, longitud de close > 1), y (longitud de open > 1,
longitud de close > 1). Solo (longitud de open = 1, longitud de close = 1)
y (longitud de open > 1, longitud de close > 1) son suficientes.

:white_check_mark: **Respuesta**

```bash
# Completar aqui
```

## Ejercicio 5

:question: **Pregunta**

¿Encontramos más casos donde se pueda simplificar el número de pruebas?

**Paso 6: Automatiza los casos de prueba**

Ahora es el momento de transformar los casos de prueba en pruebas JUnit automatizadas. Escribir
esas pruebas es principalmente una tarea mecánica. Cada llamada al método substringsBetween es
uno de nuestros casos de prueba. Las 21 llamadas a él se distribuyen entre los métodos de prueba,
cada una de las cuales coincide con los casos de prueba que ideamos anteriormente.

**Paso 7: Ejecuta las pruebas**

Siempre es bueno tener variación en las pruebas. Por ejemplo, cuando revisas las pruebas, nota que
nunca probamos cadenas con espacios. Entonces se diseñaron dos pruebas adicionales basadas en
T15 y T20, ambas sobre "str contiene etiquetas open y close varias veces": una para etiquetas open
y close con longitudes 1, otra para etiquetas open y close con longitudes más grandes. Estos
verifican si la implementación funciona si hay espacios en blanco en la cadena.

:white_check_mark: **Respuesta**

**Cadena vacia o nula:**

- str es nulo.
- str está vacío.
- open es nulo.
- open está vacío.
- close es nulo.
- close está vacío.

**Cadena de longitud 1:**

- El carácter único en str coincide con la etiqueta open.
- El carácter único en str coincide con la etiqueta de close.
- El carácter único en str no coincide ni con la etiqueta de open ni con la de close.
- El carácter único en str coincide con las etiquetas de open y close.

**Cadena de longitud > 1, longitud de open = 1, longitud de close = 1:**

- str no contiene ni la etiqueta de open ni la de close.
- str contiene la etiqueta open pero no contiene la etiqueta close.
- str contiene la etiqueta de close pero no contiene la etiqueta de open.
- str contiene las etiquetas de open y close.
- str contiene las etiquetas de open y close varias veces.

**Cadena de longitud > 1, longitud de open > 1, longitud de close > 1:**

- str no contiene ni la etiqueta de open ni la de close.
- str contiene la etiqueta open pero no contiene la etiqueta close.
- str contiene la etiqueta de close pero no contiene la etiqueta de open.
- str contiene las etiquetas de open y close.
- str contiene las etiquetas de open y close varias veces.

**La prueba para el límite:**

- str contiene las etiquetas de open y close sin caracteres entre ellas.

## Ejercicio 6

:question: **Pregunta**

Escribe un archivo stringUtilsTest.java y completa el código anterior.

Tómate el tiempo para revisar todos los pasos que hemos
trabajado y luego considera esta pregunta: ¿hemos terminado?.

:white_check_mark: **Respuesta**

```java
    @Test
    void strIsNullOrEmpty() {
        // T1: str es nulo.
        assertThat(substringsBetween(null, "a", "b")).isEqualTo(null);
        // T2: str está vacío.
        assertThat(substringsBetween("", "a", "b")).isEqualTo(new String[]{});
    }

    @Test
    void openIsNullOrEmpty() {
        // T3: open es nulo.
        assertThat(substringsBetween("abc", null, "b")).isEqualTo(null);
        // T4: open está vacío.
        assertThat(substringsBetween("abc", "", "c")).isEqualTo(null);
    }

    @Test
    void closeIsNullOrEmpty() {
        // T5: close es nulo.
        assertThat(substringsBetween("abc", "a", null)).isEqualTo(null);
        // T6: close está vacío.
        assertThat(substringsBetween("abc", "a", "")).isEqualTo(null);
    }

    // Cadena de longitud 1
    @Test
    void strOfLength1() {
        // T7: el carácter único en str coincide con la etiqueta open.
        assertThat(substringsBetween("a", "a", "b")).isEqualTo(null);
        // T8: El carácter único en str coincide con la etiqueta de close.
        assertThat(substringsBetween("a", "b", "a")).isEqualTo(null);
        // T9: El carácter único en str no coincide ni con la etiqueta de open ni con la de close.
        assertThat(substringsBetween("a", "b", "b")).isEqualTo(null);
        // T10: el carácter único en str coincide con las etiquetas de open y close.
        assertThat(substringsBetween("a", "a", "a")).isEqualTo(null);
    }

    // cadena de longitud > 1, longitud de open = 1, longitud de close = 1:
    @Test
    void openAndCloseOfLength1() {
        // T11: str no contiene ni la etiqueta de open ni la de close.
        assertThat(substringsBetween("abc", "x", "y")).isEqualTo(null);
        // T12: str contiene la etiqueta open pero no contiene la etiqueta close.
        assertThat(substringsBetween("abc", "a", "y")).isEqualTo(null);
        // T13: str contiene la etiqueta de close pero no contiene la etiqueta de open.
        assertThat(substringsBetween("abc", "x", "c")).isEqualTo(null);
        // T14: str contiene las etiquetas de open y close.
        assertThat(substringsBetween("abc", "a", "c")).isEqualTo(new String[]{"b"});
        // T15: str contiene las etiquetas de open y close varias veces. <- falta implementar
        assertThat(substringsBetween("abcabcabc", "a", "c")).isEqualTo(new String[]{"b", "b", "b"});
        // ... previas aseveraciones aquí
        Assertions.assertThat(StringUtils.substringsBetween("abcabyt byrc", "a", "c")).isEqualTo(new String[]{"b", "byt byr"});
    }

    // cadena de longitud > 1, longitud de open > 1, longitud de close > 1:
    @Test
    void openAndCloseTagsOfDifferentSizes() {
        // T16: str no contiene ni la etiqueta de open ni la de close.
        assertThat(substringsBetween("aabcc", "xx", "yy")).isEqualTo(null);
        // T17: str contiene la etiqueta open pero no contiene la etiqueta close.
        assertThat(substringsBetween("aabcc", "aa", "yy")).isEqualTo(null);
        // T18: str contiene la etiqueta de close pero no contiene la etiqueta de open.
        assertThat(substringsBetween("aabcc", "xx", "cc")).isEqualTo(null);
        // T19: str contiene las etiquetas de open y close.
        assertThat(substringsBetween("aabbcc", "aa", "cc")).isEqualTo(new String[]{"bb"});
        // T20: str contiene las etiquetas de open y close varias veces.
        assertThat(substringsBetween("aabbccaaeecc", "aa", "cc")).isEqualTo(new String[]{"bb", "ee"});
        // ... previas aseveraciones aquí
        Assertions.assertThat(StringUtils.substringsBetween("a abb ddc ca abbcc", "a a", "c c")).isEqualTo(new String[]{"bb dd"});
    }

    //  str contiene las etiquetas de open y close sin caracteres entre ellas.
    @Test
    void notSubstringBetweenOpenAndCloseTags() {
        // T21: str contiene las etiquetas de open y close sin caracteres entre ellas.
        assertThat(substringsBetween("aabb", "aa", "bb")).isEqualTo(new String[]{""});
    }
```

No hemos terminado. Aunque hemos escrito 23 pruebas, no hemos probado todas las combinaciones
posibles, ademas podemos realizar mejoras sobre el código para validar errores o validar las salidas de
manera correcta.

## Ejercicio 7

:question: **Pregunta**

Modifica el método substringsBetween para manejar casos especiales adicionales y asegurar que
todas las pruebas existentes pasen.

Instrucciones:

1. Modifica el método substringsBetween para que si la cadena str contiene caracteres especiales
(como *, ?, !, etc.), los ignore y solo considere letras y números para la búsqueda  de subcadenas.
2. Asegúrate de que todas las pruebas existentes aún pasen.
3. Escribe nuevas pruebas que validen esta funcionalidad

:white_check_mark: **Respuesta**

Clase `StringUtils` con el método `substringsBetween()` modificado

```java
public static String[] substringsBetween(final String str, final String open, final String close) {
    if (str == null || isEmpty(open) || isEmpty(close)) {
        return null;
    }
    final String sanitizedStr = str.replaceAll("[^a-zA-Z0-9]", "");
    final int strLen = sanitizedStr.length();
    if (strLen == 0) {
        return EMPTY_STRING_ARRAY;
    }
    final int closeLen = close.length();
    final int openLen = open.length();
    final List<String> list = new ArrayList<>();
    int pos = 0;
    while (pos < strLen - closeLen) {
        int start = sanitizedStr.indexOf(open, pos);
        if (start < 0) {
            break;
        }
        start += openLen;
        final int end = sanitizedStr.indexOf(close, start);
        if (end < 0) {
            break;
        }
        list.add(sanitizedStr.substring(start, end));
        pos = end + closeLen;
    }
    if (list.isEmpty()) {
        return null;
    }
    return list.toArray(EMPTY_STRING_ARRAY);
}
```

Pruebas para validar la funcionalidad

```java
    @Test
    void ignoresSpecialCharacters() {
        // T24: str contiene caracteres especiales.
        Assertions.assertThat(stringUtils.substringsBetween("a*b?c!a@d", "a", "d")).isEqualTo(new String[] { "bca" });
    }
```

## Ejercicio 8

:question: **Pregunta**

Implementa excepciones personalizadas para manejar casos específicos de errores en el método
substringsBetween.

Instrucciones:

1. Crea una excepción personalizada llamada InvalidDelimiterException.
2. Modifica el método substringsBetween para lanzar esta excepción si las etiquetas open o
   close están vacías.
3. Escribe pruebas unitarias para asegurar que la excepción se lanza en los casos adecuados.

:white_check_mark: **Respuesta**

Clase `InvalidDelimiterException`

```java
public class InvalidDelimiterException extends RuntimeException {
    public InvalidDelimiterException(String message) {
        super(message);
    }
}
```

Clase `StringUtils` con el método `substringsBetween()` modificado

```java
    public static String[] substringsBetween(final String str, final String open, final String close) {
        if (str == null) {
            return null;
        }
        if (isEmpty(open) || isEmpty(close)) {
            throw new InvalidDelimiterException("Open or close delimiter cannot be empty");
        }
        final String sanitizedStr = str.replaceAll("[^a-zA-Z0-9]", "");
        final int strLen = sanitizedStr.length();
        if (strLen == 0) {
            return EMPTY_STRING_ARRAY;
        }
        final int closeLen = close.length();
        final int openLen = open.length();
        final List<String> list = new ArrayList<>();
        int pos = 0;
        while (pos < strLen - closeLen) {
            int start = sanitizedStr.indexOf(open, pos);
            if (start < 0) {
                break;
            }
            start += openLen;
            final int end = sanitizedStr.indexOf(close, start);
            if (end < 0) {
                break;
            }
            list.add(sanitizedStr.substring(start, end));
            pos = end + closeLen;
        }
        if (list.isEmpty()) {
            return null;
        }
        return list.toArray(EMPTY_STRING_ARRAY);
    }
```

Pruebas para validar la excepción

```java
    @Test
    void openOrCloseDelimiterCannotBeEmpty() {
        // T25: open es nulo.
        Assertions.assertThatThrownBy(() -> stringUtils.substringsBetween("abc", null, "b"))
                .isInstanceOf(InvalidDelimiterException.class)
                .hasMessage("Open or close delimiter cannot be empty");
        // T26: open está vacío.
        Assertions.assertThatThrownBy(() -> stringUtils.substringsBetween("abc", "", "c"))
                .isInstanceOf(InvalidDelimiterException.class)
                .hasMessage("Open or close delimiter cannot be empty");
        // T27: close es nulo.
        Assertions.assertThatThrownBy(() -> stringUtils.substringsBetween("abc", "a", null))
                .isInstanceOf(InvalidDelimiterException.class)
                .hasMessage("Open or close delimiter cannot be empty");
        // T28: close está vacío.
        Assertions.assertThatThrownBy(() -> stringUtils.substringsBetween("abc", "a", ""))
                .isInstanceOf(InvalidDelimiterException.class)
                .hasMessage("Open or close delimiter cannot be empty");
    }
```

## Ejercicio 9: Implementación de un método alternativo

:question: **Pregunta**

Implementa un método alternativo que utilice expresiones regulares para encontrar subcadenas
entre dos delimitadores.

Instrucciones:

1. Implementa un método llamado regexSubstringsBetween que use expresiones regulares
   para encontrar subcadenas entre las etiquetas open y close.
2. Asegúrate de que el método pase las mismas pruebas unitarias que substringsBetween.

:white_check_mark: **Respuesta**

Clase `StringUtils` con el método `regexSubstringsBetween()`

```java
    public static String[] regexSubstringsBetween(final String str, final String open, final String close) {
        if (str == null || isEmpty(open) || isEmpty(close)) {
            return null;
        }
        final String regex = Pattern.quote(open) + "(.*?)" + Pattern.quote(close);
        final Pattern pattern = Pattern.compile(regex);
        final Matcher matcher = pattern.matcher(str);
        final List<String> list = new ArrayList<>();
        while (matcher.find()) {
            list.add(matcher.group(1));
        }
        if (list.isEmpty()) {
            return null;
        }
    return list.toArray(EMPTY_STRING_ARRAY);
    }
```

```java
    @Test
    void regexSimpleCase() {
        assertThat(stringUtils.regexSubstringsBetween("abcd", "a", "d")).isEqualTo(new String[] { "bc" });
    }
    
    @Test
    void regexManyStrings() {
        assertThat(stringUtils.regexSubstringsBetween("abcdabcdab", "a", "d")).isEqualTo(new String[]
                { "bc", "bc" });
    }
    
    @Test
    void regexOpenAndCloseTagsThatAreLongerThan1Char() {
        assertThat(stringUtils.regexSubstringsBetween("aabcddaabfddaab", "aa", "dd")).isEqualTo(new
                String[] { "bc", "bf" });
    }
```

## Ejercicio 10: Optimización del método original

:question: **Pregunta**

Optimiza el método substringsBetween para mejorar su eficiencia en términos de tiempo y espacio.

Instrucciones:

1. Revisa y optimiza el método substringsBetween para que tenga una mejor eficiencia.
2. Asegúrate de que el método pase todas las pruebas unitarias existentes.

:white_check_mark: **Respuesta**

Clase `StringUtils` con el método `substringsBetween()` optimizado

```java
    public static String[] substringsBetween(final String str, final String open, final String close) {
        if (str == null) {
            return null;
        }
        if (isEmpty(open) || isEmpty(close)) {
            throw new InvalidDelimiterException("Open or close delimiter cannot be empty");
        }
        final String sanitizedStr = str.replaceAll("[^a-zA-Z0-9]", "");
        final int strLen = sanitizedStr.length();
        if (strLen == 0) {
            return EMPTY_STRING_ARRAY;
        }
        final int closeLen = close.length();
        final int openLen = open.length();
        final List<String> list = new ArrayList<>();
        int pos = 0;
        while (pos < strLen - closeLen) {
            int start = sanitizedStr.indexOf(open, pos);
            if (start < 0) {
                break;
            }
            start += openLen;
            final int end = sanitizedStr.indexOf(close, start);
            if (end < 0) {
                break;
            }
            list.add(sanitizedStr.substring(start, end));
            pos = end + closeLen;
        }
        if (list.isEmpty()) {
            return null;
        }
        return list.toArray(new String[list.size()]);
    }
```
