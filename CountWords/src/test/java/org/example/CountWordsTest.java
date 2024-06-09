package org.example;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class CountWordsTest {

    @Test
    void testCountWordsEndingWithS() {
        int words = new CountWords().count("dogs cats");
        Assertions.assertThat(words).isEqualTo(2);
    }

    @Test
    void testCountWordsEndingWithR() {
        int words = new CountWords().count("car bar");
        Assertions.assertThat(words).isEqualTo(2);
    }

    @Test
    void testCountNoWordsEndingWithSOrR() {
        int words = new CountWords().count("dog cat");
        Assertions.assertThat(words).isEqualTo(0);
    }

    @Test
    void testCountWordsWithSpecialCharacters() {
        int words = new CountWords().count("dog's car!");
        Assertions.assertThat(words).isEqualTo(2);
    }

    @Test
    void testEmptyString() {
        int words = new CountWords().count("");
        Assertions.assertThat(words).isEqualTo(0);
    }

    @Test
    void testCountSingleCharacterS() {
        int words = new CountWords().count("s");
        Assertions.assertThat(words).isEqualTo(1);
    }

    @Test
    void testCountSingleCharacterR() {
        int words = new CountWords().count("r");
        Assertions.assertThat(words).isEqualTo(1);
    }

    @Test
    void testCountSingleCharacterNonLetter() {
        int words = new CountWords().count("1");
        Assertions.assertThat(words).isEqualTo(0);
    }

    @Test
    void testCountWordsEndingWithRAndS() {
        int words = new CountWords().count("runners dogs cars");
        Assertions.assertThat(words).isEqualTo(3);
    }

    @Test
    void testCountWordsEndingWithNeither() {
        int words = new CountWords().count("hello world");
        Assertions.assertThat(words).isEqualTo(0);
    }

    @Test
    void testCountWordsEndingWithSAndSpecialChar() {
        int words = new CountWords().count("dogs cats@");
        Assertions.assertThat(words).isEqualTo(2);
    }

    @Test
    void testCountWordsStartingWithNonLetter() {
        int words = new CountWords().count("1dogs 2cats");
        Assertions.assertThat(words).isEqualTo(2);
    }

    @Test
    void testCountWordsWithSpaces() {
        int words = new CountWords().count("dogs  cats");
        Assertions.assertThat(words).isEqualTo(2);
    }
}