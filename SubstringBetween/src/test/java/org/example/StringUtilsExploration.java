package org.example;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class StringUtilsExploration {

    @Test
    void testSubstringsBetween() {
        Assertions.assertThat(StringUtils.substringsBetween("axcaycazc", "a", "c")).isEqualTo(new String[] { "x", "y", "z" });
    }

}