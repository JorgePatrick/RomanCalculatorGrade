package com.jorgepatrick;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RomanCalculatorTest {
    @Mock
    private NumberConverter converter;
    RomanCalculator romanCalculator;

    @BeforeEach
    public void setup(){
        romanCalculator = new RomanCalculator(converter);
    }

    @ParameterizedTest
    @MethodSource("provideRomanNumbersForCalculation")
    public void sum(String firstAddendRoman, int firstAddendArabic, String secondAddendRoman, int secondAddendArabic, String romanNumberSum, int arabicNumberSum) {
        when(converter.parseRomanToArabic(firstAddendRoman)).thenReturn(firstAddendArabic);
        when(converter.parseRomanToArabic(secondAddendRoman)).thenReturn(secondAddendArabic);
        when(converter.parseArabicToRoman(arabicNumberSum)).thenReturn(romanNumberSum);
        assertEquals(romanNumberSum, romanCalculator.sumRomanNumbers(firstAddendRoman, secondAddendRoman));
    }
    private static Stream<Arguments> provideRomanNumbersForCalculation() {
        return Stream.of(
                Arguments.of("I", 1, "I", 1, "II", 2),
                Arguments.of("II", 2, "I", 1, "III", 3),
                Arguments.of("VIII", 8, "VIII", 8, "XVI", 16)
        );
    }
}


