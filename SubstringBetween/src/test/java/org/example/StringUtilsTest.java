package org.example;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class StringUtilsTest {

    @Test
    void strIsNullOrEmpty() {
        // T1: str es nulo.
        Assertions.assertThat(StringUtils.substringsBetween(null, "a", "b")).isEqualTo(null);
        // T2: str está vacío.
        Assertions.assertThat(StringUtils.substringsBetween("", "a", "b")).isEqualTo(new String[]{});
    }

    @Test
    void openIsNullOrEmpty() {
        // T3: open es nulo.
        //Assertions.assertThat(StringUtils.substringsBetween("abc", null, "b")).isEqualTo(null);
        // T4: open está vacío.
        // Assertions.assertThat(StringUtils.substringsBetween("abc", "", "c")).isEqualTo(null);
    }

    @Test
    void closeIsNullOrEmpty() {
        // T5: close es nulo.
        //Assertions.assertThat(StringUtils.substringsBetween("abc", "a", null)).isEqualTo(null);
        // T6: close está vacío.
        // Assertions.assertThat(StringUtils.substringsBetween("abc", "a", "")).isEqualTo(null);
    }

    // Cadena de longitud 1
    @Test
    void strOfLength1() {
        // T7: el carácter único en str coincide con la etiqueta open.
        Assertions.assertThat(StringUtils.substringsBetween("a", "a", "b")).isEqualTo(null);
        // T8: El carácter único en str coincide con la etiqueta de close.
        Assertions.assertThat(StringUtils.substringsBetween("a", "b", "a")).isEqualTo(null);
        // T9: El carácter único en str no coincide ni con la etiqueta de open ni con la de close.
        Assertions.assertThat(StringUtils.substringsBetween("a", "b", "b")).isEqualTo(null);
        // T10: el carácter único en str coincide con las etiquetas de open y close.
        Assertions.assertThat(StringUtils.substringsBetween("a", "a", "a")).isEqualTo(null);
    }

    // cadena de longitud > 1, longitud de open = 1, longitud de close = 1:
    @Test
    void openAndCloseOfLength1() {
        // T11: str no contiene ni la etiqueta de open ni la de close.
        Assertions.assertThat(StringUtils.substringsBetween("abc", "x", "y")).isEqualTo(null);
        // T12: str contiene la etiqueta open pero no contiene la etiqueta close.
        Assertions.assertThat(StringUtils.substringsBetween("abc", "a", "y")).isEqualTo(null);
        // T13: str contiene la etiqueta de close pero no contiene la etiqueta de open.
        Assertions.assertThat(StringUtils.substringsBetween("abc", "x", "c")).isEqualTo(null);
        // T14: str contiene las etiquetas de open y close.
        Assertions.assertThat(StringUtils.substringsBetween("abc", "a", "c")).isEqualTo(new String[]{"b"});
        // T15: str contiene las etiquetas de open y close varias veces. <- falta implementar
        Assertions.assertThat(StringUtils.substringsBetween("abcabcabc", "a", "c")).isEqualTo(new String[]{"b", "b", "b"});
        // ... previas aseveraciones aquí
        Assertions.assertThat(StringUtils.substringsBetween("abcabyt byrc", "a", "c")).isEqualTo(new String[]{"b", "bytbyr"});
    }

    // cadena de longitud > 1, longitud de open > 1, longitud de close > 1:
    @Test
    void openAndCloseTagsOfDifferentSizes() {
        // T16: str no contiene ni la etiqueta de open ni la de close.
        Assertions.assertThat(StringUtils.substringsBetween("aabcc", "xx", "yy")).isEqualTo(null);
        // T17: str contiene la etiqueta open pero no contiene la etiqueta close.
        Assertions.assertThat(StringUtils.substringsBetween("aabcc", "aa", "yy")).isEqualTo(null);
        // T18: str contiene la etiqueta de close pero no contiene la etiqueta de open.
        Assertions.assertThat(StringUtils.substringsBetween("aabcc", "xx", "cc")).isEqualTo(null);
        // T19: str contiene las etiquetas de open y close.
        Assertions.assertThat(StringUtils.substringsBetween("aabbcc", "aa", "cc")).isEqualTo(new String[]{"bb"});
        // T20: str contiene las etiquetas de open y close varias veces.
        Assertions.assertThat(StringUtils.substringsBetween("aabbccaaeecc", "aa", "cc")).isEqualTo(new String[]{"bb", "ee"});
        // ... previas aseveraciones aquí
        Assertions.assertThat(StringUtils.substringsBetween("aabb ddccca abbccc", "aa", "ccc")).isEqualTo(new String[]{"bbdd", "bb"});
    }

    //  str contiene las etiquetas de open y close sin caracteres entre ellas.
    @Test
    void notSubstringBetweenOpenAndCloseTags() {
        // T21: str contiene las etiquetas de open y close sin caracteres entre ellas.
        Assertions.assertThat(StringUtils.substringsBetween("aabb", "aa", "bb")).isEqualTo(new String[]{""});
    }

    // open o close es una cadena vacía.
    @Test
    void throwsExceptionWhenOpenOrCloseIsEmpty() {
        // T25: open es una cadena vacía.-> Retorna InvalidDelimiterException
        assertThrows(InvalidDelimiterException.class, () -> {
            StringUtils.substringsBetween("abc", "", "c");
        });
        // T26: close es una cadena vacía.-> Retorna InvalidDelimiterException
        assertThrows(InvalidDelimiterException.class, () -> {
            StringUtils.substringsBetween("abc", "a", "");
        });
    }

    @Test
    void regexSimpleCase() {
        Assertions.assertThat(StringUtils.regexSubstringsBetween("abcd", "a", "d")).isEqualTo(new String[] { "bc" });
    }

    @Test
    void regexManyStrings() {
        Assertions.assertThat(StringUtils.regexSubstringsBetween("abcdabcdab", "a", "d")).isEqualTo(new String[]
                { "bc", "bc" });
    }

    @Test
    void regexOpenAndCloseTagsThatAreLongerThan1Char() {
        Assertions.assertThat(StringUtils.regexSubstringsBetween("aabcddaabfddaab", "aa", "dd")).isEqualTo(new
                String[] { "bc", "bf" });
    }

}
