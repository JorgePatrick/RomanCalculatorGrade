package com.jorgepatrick;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static com.jorgepatrick.RomanNumbers.*;

public class RomanCalculatorTest {
    @ParameterizedTest
    @MethodSource("provideRomanNumbersForCalculation")
    public void sum(String firstAddend, String secondAddend, String result) {
        RomanCalculator romanCalculator = new RomanCalculator();
        assertEquals(result, romanCalculator.sumRomanNumbers(firstAddend, secondAddend));
    }
    private static Stream<Arguments> provideRomanNumbersForCalculation() {
        return Stream.of(
                Arguments.of(I, I, II),
                Arguments.of(II, I, III),
                Arguments.of(I, II, III),
                Arguments.of(I, III, IV),
                Arguments.of(III, III, VI),
                Arguments.of(III, II, V)
        );
    }
}


