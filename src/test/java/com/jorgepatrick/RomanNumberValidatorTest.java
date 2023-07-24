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
//            romanNumber.isEmpty();
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
                Arguments.of("A", "Invalid Roman Digit (A)"),
                Arguments.of("XVBI", "Invalid Roman Digit (B)"),
                Arguments.of("IIII", "Invalid Roman Number - Invalid Roman Symbol after III"),
                Arguments.of("XIIII", "Invalid Roman Number - Invalid Roman Symbol after III"),
                Arguments.of("XXIIII", "Invalid Roman Number - Invalid Roman Symbol after III"),
                Arguments.of("IIIX", "Invalid Roman Number - Invalid Roman Symbol after III"),
                Arguments.of("IXCI", "Invalid Roman Number - Unexpected char after IX"),
                Arguments.of("IL", "Invalid Roman Number - Invalid Roman Symbol after I"),
                Arguments.of("IC", "Invalid Roman Number - Invalid Roman Symbol after I"),
                Arguments.of("ID", "Invalid Roman Number - Invalid Roman Symbol after I"),
                Arguments.of("IM", "Invalid Roman Number - Invalid Roman Symbol after I"),
                Arguments.of("IVI", "Invalid Roman Number - Unexpected char after IV"),
                Arguments.of("IXC", "Invalid Roman Number - Unexpected char after IX"),
                Arguments.of("IXI", "Invalid Roman Number - Unexpected char after IX"),
                Arguments.of("IIV", "Invalid Roman Number - After two I there can only be another I or a lesser symbol"),
                Arguments.of("XVVIIII", "Invalid Roman Number - After a V there can only be lesser symbols"),
                Arguments.of("VV", "Invalid Roman Number - After a V there can only be lesser symbols"),
                Arguments.of("VIV", "Invalid Roman Number - After a V there can only be lesser symbols"),
                Arguments.of("VX", "Invalid Roman Number - After a V there can only be lesser symbols"),
                Arguments.of("VL", "Invalid Roman Number - After a V there can only be lesser symbols"),
                Arguments.of("VC", "Invalid Roman Number - After a V there can only be lesser symbols"),
                Arguments.of("VD", "Invalid Roman Number - After a V there can only be lesser symbols"),
                Arguments.of("VM", "Invalid Roman Number - After a V there can only be lesser symbols"),
                Arguments.of("XVX", "Invalid Roman Number - After a V there can only be lesser symbols"),
                Arguments.of("XXXX", "Invalid Roman Number - Invalid Roman Symbol after XXX"),
                Arguments.of("XXL", "Invalid Roman Number - After two X there can only be another X or a lesser symbol"),
                Arguments.of("XD", "Invalid Roman Number - Invalid Roman Symbol after X"),
                Arguments.of("XM", "Invalid Roman Number - Invalid Roman Symbol after X"),
                Arguments.of("XLX", "Invalid Roman Number - Unexpected char after XL"),
                Arguments.of("XCD", "Invalid Roman Number - Unexpected char after XC"),
                Arguments.of("LL", "Invalid Roman Number - After a L there can only be lesser symbols"),
                Arguments.of("LXL", "Invalid Roman Number - After a L there can only be lesser symbols"),
                Arguments.of("LXVIIL", "Invalid Roman Number - After a L there can only be lesser symbols"),
                Arguments.of("LC", "Invalid Roman Number - After a L there can only be lesser symbols"),
                Arguments.of("LD", "Invalid Roman Number - After a L there can only be lesser symbols"),
                Arguments.of("LM", "Invalid Roman Number - After a L there can only be lesser symbols"),
                Arguments.of("CCCC", "Invalid Roman Number - Invalid Roman Symbol after CCC"),
                Arguments.of("CCD", "Invalid Roman Number - After two C there can only be another C or a lesser symbol"),
                Arguments.of("CDC", "Invalid Roman Number - Unexpected char after CD"),
                Arguments.of("CMM", "Invalid Roman Number - Unexpected char after CM"),
                Arguments.of("DD", "Invalid Roman Number - After a D there can only be lesser symbols"),
                Arguments.of("DCD", "Invalid Roman Number - After a D there can only be lesser symbols"),
                Arguments.of("DXVIID", "Invalid Roman Number - After a D there can only be lesser symbols"),
                Arguments.of("DM", "Invalid Roman Number - After a D there can only be lesser symbols"),
                Arguments.of("MMMM", "Invalid Roman Number - Invalid Roman Symbol after MMM")
        );
    }

}
