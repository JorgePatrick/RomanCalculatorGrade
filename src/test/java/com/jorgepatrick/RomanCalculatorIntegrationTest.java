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
                Arguments.of("XXX", "XXX", "LX"),
                Arguments.of("I", "I", "II"),
                Arguments.of("II", "I", "III"),
                Arguments.of("III", "I", "IV"),
                Arguments.of("II", "III", "V"),
                Arguments.of("II", "IV", "VI"),
                Arguments.of("VI", "I", "VII"),
                Arguments.of("V", "III", "VIII"),
                Arguments.of("II", "VII", "IX"),
                Arguments.of("VI", "IV", "X"),
                Arguments.of("V", "VI", "XI"),
                Arguments.of("VII", "V", "XII"),
                Arguments.of("I", "XII", "XIII"),
                Arguments.of("IX", "V", "XIV"),
                Arguments.of("I", "XIV", "XV"),
                Arguments.of("III", "XIII", "XVI"),
                Arguments.of("X", "VII", "XVII"),
                Arguments.of("V", "XIII", "XVIII"),
                Arguments.of("X", "IX", "XIX"),
                Arguments.of("XVI", "IV", "XX"),
                Arguments.of("IX", "XII", "XXI"),
                Arguments.of("IV", "XVIII", "XXII"),
                Arguments.of("XVI", "VII", "XXIII"),
                Arguments.of("XIX", "V", "XXIV"),
                Arguments.of("XVI", "IX", "XXV"),
                Arguments.of("VI", "XX", "XXVI"),
                Arguments.of("XIII", "XIV", "XXVII"),
                Arguments.of("XVIII", "X", "XXVIII"),
                Arguments.of("XXVI", "III", "XXIX"),
                Arguments.of("II", "XXVIII", "XXX"),
                Arguments.of("V", "XXVI", "XXXI"),
                Arguments.of("XXVI", "VI", "XXXII"),
                Arguments.of("XIII", "XX", "XXXIII"),
                Arguments.of("XV", "XIX", "XXXIV"),
                Arguments.of("XXVII", "VIII", "XXXV"),
                Arguments.of("XVII", "XIX", "XXXVI"),
                Arguments.of("XXIV", "XIII", "XXXVII"),
                Arguments.of("XIV", "XXIV", "XXXVIII"),
                Arguments.of("XIII", "XXVI", "XXXIX"),
                Arguments.of("V", "XXXV", "XL"),
                Arguments.of("XVIII", "XXIII", "XLI"),
                Arguments.of("I", "XLI", "XLII"),
                Arguments.of("XXVIII", "XV", "XLIII"),
                Arguments.of("X", "XXXIV", "XLIV"),
                Arguments.of("XLIV", "I", "XLV"),
                Arguments.of("XV", "XXXI", "XLVI"),
                Arguments.of("VIII", "XXXIX", "XLVII"),
                Arguments.of("I", "XLVII", "XLVIII"),
                Arguments.of("XXXIX", "X", "XLIX"),
                Arguments.of("VIII", "XLII", "L"),
                Arguments.of("XLII", "IX", "LI"),
                Arguments.of("XLV", "VII", "LII"),
                Arguments.of("XVII", "XXXVI", "LIII"),
                Arguments.of("LIII", "I", "LIV"),
                Arguments.of("XXIV", "XXXI", "LV"),
                Arguments.of("XII", "XLIV", "LVI"),
                Arguments.of("I", "LVI", "LVII"),
                Arguments.of("XLI", "XVII", "LVIII"),
                Arguments.of("LIV", "V", "LIX"),
                Arguments.of("LV", "V", "LX"),
                Arguments.of("XXVI", "XXXV", "LXI"),
                Arguments.of("LVII", "V", "LXII"),
                Arguments.of("XLIII", "XX", "LXIII"),
                Arguments.of("I", "LXIII", "LXIV"),
                Arguments.of("LIV", "XI", "LXV"),
                Arguments.of("LX", "VI", "LXVI"),
                Arguments.of("XXX", "XXXVII", "LXVII"),
                Arguments.of("II", "LXVI", "LXVIII"),
                Arguments.of("XX", "XLIX", "LXIX"),
                Arguments.of("XXVIII", "XLII", "LXX"),
                Arguments.of("XXI", "L", "LXXI"),
                Arguments.of("LXXI", "I", "LXXII"),
                Arguments.of("VII", "LXVI", "LXXIII"),
                Arguments.of("XXVI", "XLVIII", "LXXIV"),
                Arguments.of("XL", "XXXV", "LXXV"),
                Arguments.of("II", "LXXIV", "LXXVI"),
                Arguments.of("LXXIII", "IV", "LXXVII"),
                Arguments.of("LXXVI", "II", "LXXVIII"),
                Arguments.of("LXXVIII", "I", "LXXIX"),
                Arguments.of("XV", "LXV", "LXXX"),
                Arguments.of("LXXI", "X", "LXXXI"),
                Arguments.of("XLII", "XL", "LXXXII"),
                Arguments.of("XXXI", "LII", "LXXXIII"),
                Arguments.of("VII", "LXXVII", "LXXXIV"),
                Arguments.of("LXVI", "XIX", "LXXXV"),
                Arguments.of("XXIX", "LVII", "LXXXVI"),
                Arguments.of("LXXXIII", "IV", "LXXXVII"),
                Arguments.of("LXXVII", "XI", "LXXXVIII"),
                Arguments.of("XXVI", "LXIII", "LXXXIX"),
                Arguments.of("XXXVI", "LIV", "XC"),
                Arguments.of("XLI", "L", "XCI"),
                Arguments.of("LVIII", "XXXIV", "XCII"),
                Arguments.of("XVII", "LXXVI", "XCIII"),
                Arguments.of("LXXXIII", "XI", "XCIV"),
                Arguments.of("XXX", "LXV", "XCV"),
                Arguments.of("LXXI", "XXV", "XCVI"),
                Arguments.of("LII", "XLV", "XCVII"),
                Arguments.of("XII", "LXXXVI", "XCVIII"),
                Arguments.of("LXXIII", "XXVI", "XCIX"),
                Arguments.of("XXXVII", "LXIII", "C"),
                Arguments.of("XXXVI", "LXV", "CI"),
                Arguments.of("LXVI", "XXXVI", "CII"),
                Arguments.of("XXXVI", "LXVII", "CIII"),
                Arguments.of("XLVIII", "LVI", "CIV"),
                Arguments.of("XLII", "LXIII", "CV"),
                Arguments.of("XLVIII", "LVIII", "CVI"),
                Arguments.of("XXV", "LXXXII", "CVII"),
                Arguments.of("LI", "LVII", "CVIII"),
                Arguments.of("XLIV", "LXV", "CIX"),
                Arguments.of("LXXV", "XXXV", "CX"),
                Arguments.of("LXXIII", "XXXVIII", "CXI"),
                Arguments.of("LXXIII", "XXXIX", "CXII"),
                Arguments.of("LXXXIX", "XXIV", "CXIII"),
                Arguments.of("LXXX", "XXXIV", "CXIV"),
                Arguments.of("XXXII", "LXXXIII", "CXV"),
                Arguments.of("LVIII", "LVIII", "CXVI"),
                Arguments.of("LI", "LXVI", "CXVII"),
                Arguments.of("XXIX", "LXXXIX", "CXVIII"),
                Arguments.of("XLI", "LXXVIII", "CXIX"),
                Arguments.of("XLIV", "LXXVI", "CXX"),
                Arguments.of("LXXXIV", "XXXVII", "CXXI"),
                Arguments.of("LXXV", "XLVII", "CXXII"),
                Arguments.of("LXXXVII", "XXXVI", "CXXIII"),
                Arguments.of("XLV", "LXXIX", "CXXIV"),
                Arguments.of("LVIII", "LXVII", "CXXV"),
                Arguments.of("LXV", "LXI", "CXXVI"),
                Arguments.of("LXIII", "LXIV", "CXXVII"),
                Arguments.of("LXXXV", "XLIII", "CXXVIII"),
                Arguments.of("LXIX", "LX", "CXXIX"),
                Arguments.of("LVII", "LXXIII", "CXXX"),
                Arguments.of("LXXXI", "L", "CXXXI"),
                Arguments.of("LIV", "LXXVIII", "CXXXII"),
                Arguments.of("XLVI", "LXXXVII", "CXXXIII"),
                Arguments.of("LXII", "LXXII", "CXXXIV"),
                Arguments.of("LXXXIX", "XLVI", "CXXXV"),
                Arguments.of("LXXX", "LVI", "CXXXVI"),
                Arguments.of("LXXXIV", "LIII", "CXXXVII"),
                Arguments.of("LXIII", "LXXV", "CXXXVIII"),
                Arguments.of("LVIII", "LXXXI", "CXXXIX"),
                Arguments.of("LXVII", "LXXIII", "CXL"),
                Arguments.of("LVIII", "LXXXIII", "CXLI"),
                Arguments.of("LXIX", "LXXIII", "CXLII"),
                Arguments.of("LXXXVII", "LVI", "CXLIII"),
                Arguments.of("LXXXVIII", "LVI", "CXLIV"),
                Arguments.of("LXXXVII", "LVIII", "CXLV"),
                Arguments.of("LXVIII", "LXXVIII", "CXLVI"),
                Arguments.of("LXV", "LXXXII", "CXLVII"),
                Arguments.of("LX", "LXXXVIII", "CXLVIII"),
                Arguments.of("LXXVIII", "LXXI", "CXLIX"),
                Arguments.of("LXXIV", "LXXVI", "CL"),
                Arguments.of("LXXII", "LXXIX", "CLI"),
                Arguments.of("LXXXIII", "LXIX", "CLII"),
                Arguments.of("LXXIX", "LXXIV", "CLIII"),
                Arguments.of("LXXV", "LXXIX", "CLIV"),
                Arguments.of("LXVIII", "LXXXVII", "CLV"),
                Arguments.of("LXXIX", "LXXVII", "CLVI"),
                Arguments.of("LXXVIII", "LXXIX", "CLVII"),
                Arguments.of("LXXXIII", "LXXV", "CLVIII"),
                Arguments.of("LXXI", "LXXXVIII", "CLIX"),
                Arguments.of("LXXXI", "LXXIX", "CLX"),
                Arguments.of("LXXIV", "LXXXVII", "CLXI"),
                Arguments.of("LXXXII", "LXXX", "CLXII"),
                Arguments.of("LXXIX", "LXXXIV", "CLXIII"),
                Arguments.of("LXXXIX", "LXXV", "CLXIV"),
                Arguments.of("LXXXIV", "LXXXI", "CLXV"),
                Arguments.of("LXXXVII", "LXXIX", "CLXVI"),
                Arguments.of("LXXX", "LXXXVII", "CLXVII"),
                Arguments.of("LXXXIX", "LXXIX", "CLXVIII"),
                Arguments.of("LXXXV", "LXXXIV", "CLXIX"),
                Arguments.of("LXXXIX", "LXXXI", "CLXX"),
                Arguments.of("LXXXVII", "LXXXIV", "CLXXI"),
                Arguments.of("LXXXVII", "LXXXV", "CLXXII"),
                Arguments.of("LXXXIX", "LXXXIV", "CLXXIII"),
                Arguments.of("LXXXVIII", "LXXXVI", "CLXXIV"),
                Arguments.of("LXXXVIII", "LXXXVII", "CLXXV"),
                Arguments.of("LXXXVII", "LXXXIX", "CLXXVI"),
                Arguments.of("LXXXVIII", "LXXXIX", "CLXXVII"),
                Arguments.of("LXXXIX", "LXXXIX", "CLXXVIII")
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
                Arguments.of("I", "LM", "Invalid Roman Number - After a L there can only be lesser symbols")
        );
    }

}


