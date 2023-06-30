package com.jorgepatrick;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RomanNumberValidatorTest {

    @ParameterizedTest
    @MethodSource("provideNonRomanNumbersForValidation")
    public void romanNumberValidation(String romanNumber, String exceptionMessage) {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            RomanNumberValidator romanNumberValidator = new RomanNumberValidator();
            romanNumberValidator.validateRomanNumber(romanNumber);
        });
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(exceptionMessage));
    }
    private static Stream<Arguments> provideNonRomanNumbersForValidation() {
        return Stream.of(
                Arguments.of(null, "Roman Number Cannot be Null"),
                Arguments.of("A", "Invalid Roman Digit"),
                Arguments.of("IIII", "Invalid Roman Number - More than two chars after an I"),
                Arguments.of("IXCI", "Invalid Roman Number - More than two chars after an I"),
                Arguments.of("IL", "Invalid Roman Number - Invalid Roman Symbol after an I"),
                Arguments.of("IC", "Invalid Roman Number - Invalid Roman Symbol after an I"),
                Arguments.of("ID", "Invalid Roman Number - Invalid Roman Symbol after an I"),
                Arguments.of("IM", "Invalid Roman Number - Invalid Roman Symbol after an I"),
                Arguments.of("IVI", "Invalid Roman Number - Unexpected char after IV"),
                Arguments.of("IXC", "Invalid Roman Number - Unexpected char after IX"),
                Arguments.of("VIIII", "Invalid Roman Number"),
                Arguments.of("VIIII", "Invalid Roman Number"),
                Arguments.of("VIIII", "Invalid Roman Number"),
                Arguments.of("VIIII", "Invalid Roman Number")
        );
    }

}
