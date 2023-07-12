package com.jorgepatrick;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class RomanCalculatorIntegrationTest {
    private RomanCalculator romanCalculator;
    private NumberConverter converter;
    private RomanNumber romanNumber;
    private RomanNumberValidator romanNumberValidator;

    @BeforeEach
    public void setup(){
        romanNumberValidator = new RomanNumberValidator();
        romanNumber = new RomanNumber(romanNumberValidator);
        converter = new NumberConverter(romanNumber);
        romanCalculator = new RomanCalculator(converter);
    }

    @ParameterizedTest
    @MethodSource("provideRomanNumbersForCalculation")
    public void sum(String firstAddend, String secondAddend, String result) {
        assertEquals(result, romanCalculator.sumRomanNumbers(firstAddend, secondAddend));
    }
    private static Stream<Arguments> provideRomanNumbersForCalculation() {
        return Stream.of(
                Arguments.of("I", "I", "II"),
                Arguments.of("II", "I", "III"),
                Arguments.of("I", "II", "III"),
                Arguments.of("I", "III", "IV"),
                Arguments.of("III", "III", "VI"),
                Arguments.of("III", "II", "V"),
                Arguments.of("I", "IV", "V"),
                Arguments.of("V", "II", "VII"),
                Arguments.of("V", "V", "X"),
                Arguments.of("III", "VI", "IX"),
                Arguments.of("VII", "V", "XII"),
                Arguments.of("VI", "VIII", "XIV"),
                Arguments.of("VIII", "VIII", "XVI"),
                Arguments.of("X", "VII", "XVII"),
                Arguments.of("IX", "IX", "XVIII"),
                Arguments.of("X", "IX", "XIX"),
                Arguments.of("X", "X", "XX"),
                Arguments.of("XIV", "VII", "XXI"),
                Arguments.of("XIII", "IX", "XXII"),
                Arguments.of("XIII", "X", "XXIII"),
                Arguments.of("XII", "XII", "XXIV"),
                Arguments.of("XX", "V", "XXV"),
                Arguments.of("XXX", "VI", "XXXVI"),
                Arguments.of("XX", "XVII", "XXXVII"),
                Arguments.of("X", "XXVIII", "XXXVIII"),
                Arguments.of("XX", "XIX", "XXXIX"),
                Arguments.of("XXXIX", "I", "XL"),
                Arguments.of("XX", "XX", "XL"),
                Arguments.of("XX", "XXI", "XLI"),
                Arguments.of("XXII", "XX", "XLII"),
                Arguments.of("XX", "XXIII", "XLIII"),
                Arguments.of("XXIV", "XX", "XLIV"),
                Arguments.of("XXX", "XV", "XLV"),
                Arguments.of("XXVIII", "XXVIII", "LVI"),
                Arguments.of("XXVIII", "XXIX", "LVII"),
                Arguments.of("XXIX", "XXIX", "LVIII"),
                Arguments.of("XXX", "XXIX", "LIX"),
                Arguments.of("XXX", "XXX", "LX")
//                Arguments.of("XCIX", "I", "C")
        );
    }
    @ParameterizedTest
    @MethodSource("provideNonRomanNumbersForValidation")
    public void romanNumberValidation(String firstAddend, String secondAddend, String exceptionMessage) {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            romanCalculator.sumRomanNumbers(firstAddend, secondAddend);
        });
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contentEquals(exceptionMessage));
    }
    private static Stream<Arguments> provideNonRomanNumbersForValidation() {
        return Stream.of(
                Arguments.of(null, null, "Roman Number Cannot be Null"),
                Arguments.of("A", "A", "Invalid Roman Digit (A)"),
                Arguments.of("XVZI", "XVZI", "Invalid Roman Digit (Z)"),
                Arguments.of("IIII", "IIII", "Invalid Roman Number - Invalid Roman Symbol after an III"),
                Arguments.of("IXCI", "IXCI", "Invalid Roman Number - Unexpected char after IX"),
                Arguments.of("IL", "IL", "Invalid Roman Number - Invalid Roman Symbol after an I"),
                Arguments.of("IC", "IC", "Invalid Roman Number - Invalid Roman Symbol after an I"),
                Arguments.of("ID", "ID", "Invalid Roman Number - Invalid Roman Symbol after an I"),
                Arguments.of("IM", "IM", "Invalid Roman Number - Invalid Roman Symbol after an I"),
                Arguments.of("IVI", "IVI", "Invalid Roman Number - Unexpected char after IV"),
                Arguments.of("IXC", "IXC", "Invalid Roman Number - Unexpected char after IX"),
                Arguments.of("VIIII", "VIIII", "Invalid Roman Number - Invalid Roman Symbol after an III")
        );
    }

}


