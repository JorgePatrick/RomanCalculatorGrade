package com.jorgepatrick;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RomanNumberValidatorTest {
    RomanNumberValidator romanNumberValidator;
//    @Mock
    private RomanNumber romanNumber;

    @BeforeEach
    public void setup(){
        romanNumberValidator = new RomanNumberValidator();
        romanNumber = new RomanNumber(romanNumberValidator);
    }

    @ParameterizedTest
    @MethodSource("provideNonRomanNumbersForValidation")
    public void romanNumberValidation(String romanNumberStr, String exceptionMessage) {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            romanNumber.setRomanNumberStr(romanNumberStr);
//            Methods to mock
//            romanNumber.isNull();
//            romanNumber.length();
//            romanNumber.digitAt(int: currentDigit);
//            romanNumber.isThereCharInPosition(int: nextDigit);

//            when(romanNumber.isNull()).thenReturn(true);
            romanNumberValidator.validateRomanNumber(romanNumber);
        });
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contentEquals(exceptionMessage));
    }
    private static Stream<Arguments> provideNonRomanNumbersForValidation() {
        return Stream.of(
                Arguments.of(null, "Roman Number Cannot be Null"),
                Arguments.of("A", "Invalid Roman Digit"),
                Arguments.of("XVAI", "Invalid Roman Digit"),
                Arguments.of("IIII", "Invalid Roman Number - Invalid Roman Symbol after an III"),
                Arguments.of("IIIX", "Invalid Roman Number - Invalid Roman Symbol after an III"),
                Arguments.of("IXCI", "Invalid Roman Number - Unexpected char after IX"),
                Arguments.of("IL", "Invalid Roman Number - Invalid Roman Symbol after an I"),
                Arguments.of("IC", "Invalid Roman Number - Invalid Roman Symbol after an I"),
                Arguments.of("ID", "Invalid Roman Number - Invalid Roman Symbol after an I"),
                Arguments.of("IM", "Invalid Roman Number - Invalid Roman Symbol after an I"),
                Arguments.of("IVI", "Invalid Roman Number - Unexpected char after IV"),
                Arguments.of("IXC", "Invalid Roman Number - Unexpected char after IX"),
                Arguments.of("IXI", "Invalid Roman Number - Unexpected char after IX"),
                Arguments.of("IIV", "Invalid Roman Number - After two I there can only be another I"),
                Arguments.of("VV", "Invalid Roman Number - No char different I allowed after one V"),
                Arguments.of("VIV", "Invalid Roman Number - No char different I allowed after one V"),
                Arguments.of("VX", "Invalid Roman Number - No char different I allowed after one V"),
                Arguments.of("VL", "Invalid Roman Number - No char different I allowed after one V"),
                Arguments.of("VC", "Invalid Roman Number - No char different I allowed after one V"),
                Arguments.of("VD", "Invalid Roman Number - No char different I allowed after one V"),
                Arguments.of("VM", "Invalid Roman Number - No char different I allowed after one V"),

/*
                Arguments.of("XVX", "Invalid Roman Number"),
                Arguments.of("XXXX", "Invalid Roman Number"),
                Arguments.of("XXIX", "Valid Roman Number"),
                Arguments.of("XCIX", "Valid Roman Number"),
                Arguments.of("XXXIX", "Valid Roman Number"),
                Arguments.of("XD", "Invalid Roman Number - Invalid Roman Symbol after an X"),
                Arguments.of("XM", "Invalid Roman Number - Invalid Roman Symbol after an X"),
                Arguments.of("XLX", "Invalid Roman Number - Unexpected char after XL"),
                Arguments.of("XCD", "Invalid Roman Number - Unexpected char after XC"),
*/
                Arguments.of("VIIII", "Invalid Roman Number - Invalid Roman Symbol after an III")
        );
    }

}
