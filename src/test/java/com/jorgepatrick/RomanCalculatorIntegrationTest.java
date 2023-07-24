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
                Arguments.of("II", "II", "IV"),
                Arguments.of("IV", "I", "V"),
                Arguments.of("I", "V", "VI"),
                Arguments.of("I", "VI", "VII"),
                Arguments.of("VII", "I", "VIII"),
                Arguments.of("VIII", "I", "IX"),
                Arguments.of("III", "VII", "X"),
                Arguments.of("XVI", "IV", "XX"),
                Arguments.of("II", "XXVIII", "XXX"),
                Arguments.of("IX", "XXXI", "XL"),
                Arguments.of("XXXIX", "XI", "L"),
                Arguments.of("XXV", "XXXV", "LX"),
                Arguments.of("XX", "L", "LXX"),
                Arguments.of("XXVI", "LIV", "LXXX"),
                Arguments.of("LVIII", "XXXII", "XC"),
                Arguments.of("XXXII", "LXVIII", "C"),
                Arguments.of("CLXXXIX", "XI", "CC"),
                Arguments.of("XVI", "CCLXXXIV", "CCC"),
                Arguments.of("CCCXXXV", "LXV", "CD"),
                Arguments.of("XLV", "CDLV", "D"),
                Arguments.of("CCLV", "CCCXLV", "DC"),
                Arguments.of("DLXXIII", "CXXVII", "DCC"),
                Arguments.of("DCCXIX", "LXXXI", "DCCC"),
                Arguments.of("DXLI", "CCCLIX", "CM"),
                Arguments.of("DCCX", "CCXC", "M"),
                Arguments.of("MDXLIV", "CDLVI", "MM"),
                Arguments.of("MCDLXXIV", "MDXXVI", "MMM"),
                Arguments.of("MCMXCIX", "MCMXCIX", "MMMCMXCVIII")
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
                Arguments.of("IIII", "IIII", "Invalid Roman Number - Invalid Roman Symbol after III"),
                Arguments.of("IXCI", "IXCI", "Invalid Roman Number - Unexpected char after IX"),
                Arguments.of("IL", "IL", "Invalid Roman Number - Invalid Roman Symbol after I"),
                Arguments.of("IC", "IC", "Invalid Roman Number - Invalid Roman Symbol after I"),
                Arguments.of("ID", "ID", "Invalid Roman Number - Invalid Roman Symbol after I"),
                Arguments.of("IM", "IM", "Invalid Roman Number - Invalid Roman Symbol after I"),
                Arguments.of("IVI", "IVI", "Invalid Roman Number - Unexpected char after IV"),
                Arguments.of("IXC", "IXC", "Invalid Roman Number - Unexpected char after IX"),
                Arguments.of("VIIII", "VIIII", "Invalid Roman Number - Invalid Roman Symbol after III"),
                Arguments.of("I", "XVVIIII", "Invalid Roman Number - After a V there can only be lesser symbols"),
                Arguments.of("I", "VV", "Invalid Roman Number - After a V there can only be lesser symbols"),
                Arguments.of("I", "VIV", "Invalid Roman Number - After a V there can only be lesser symbols"),
                Arguments.of("I", "VM", "Invalid Roman Number - After a V there can only be lesser symbols"),
                Arguments.of("I", "XVX", "Invalid Roman Number - After a V there can only be lesser symbols"),
                Arguments.of("I", "XXXX", "Invalid Roman Number - Invalid Roman Symbol after XXX"),
                Arguments.of("I", "XXL", "Invalid Roman Number - After two X there can only be another X or a lesser symbol"),
                Arguments.of("I", "XM", "Invalid Roman Number - Invalid Roman Symbol after X"),
                Arguments.of("I", "XLX", "Invalid Roman Number - Unexpected char after XL"),
                Arguments.of("I", "XCD", "Invalid Roman Number - Unexpected char after XC"),
                Arguments.of("I", "LXL", "Invalid Roman Number - After a L there can only be lesser symbols"),
                Arguments.of("I", "LXVIIL", "Invalid Roman Number - After a L there can only be lesser symbols"),
                Arguments.of("I", "LM", "Invalid Roman Number - After a L there can only be lesser symbols"),
                Arguments.of("I", "CCCC", "Invalid Roman Number - Invalid Roman Symbol after CCC"),
                Arguments.of("I", "CCD", "Invalid Roman Number - After two C there can only be another C or a lesser symbol"),
                Arguments.of("I", "CDC", "Invalid Roman Number - Unexpected char after CD"),
                Arguments.of("I", "CMM", "Invalid Roman Number - Unexpected char after CM"),
                Arguments.of("I", "DD", "Invalid Roman Number - After a D there can only be lesser symbols"),
                Arguments.of("I", "DCD", "Invalid Roman Number - After a D there can only be lesser symbols"),
                Arguments.of("I", "DXVIID", "Invalid Roman Number - After a D there can only be lesser symbols"),
                Arguments.of("I", "DM", "Invalid Roman Number - After a D there can only be lesser symbols"),
                Arguments.of("I", "MMMM", "Invalid Roman Number - Invalid Roman Symbol after MMM")
        );
    }

}


