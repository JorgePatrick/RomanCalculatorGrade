package com.jorgepatrick;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RomanNumberTest {
    RomanNumber romanNumber;
    @Mock
    private RomanNumberValidator romanNumberValidator;

    @BeforeEach
    public void setup(){
        romanNumber = new RomanNumber(romanNumberValidator);
    }

    @ParameterizedTest
    @MethodSource("provideRomanNumbersToVerifyDigit")
    public void verifyRomanDigitIsOne(String romanNumberStr, boolean digitIsOne) {
        romanNumber.setRomanNumberStr(romanNumberStr);
        assertEquals(digitIsOne, romanNumber.isDigitOnes(0));
    }
    private static Stream<Arguments> provideRomanNumbersToVerifyDigit() {
        return Stream.of(
                Arguments.of("I", true),
                Arguments.of("V", false),
                Arguments.of("X", true),
                Arguments.of("L", false),
                Arguments.of("C", true),
                Arguments.of("D", false),
                Arguments.of("M", true)
        );
    }

    @ParameterizedTest
    @MethodSource("provideRomanNumbersForConversion")
    public void getArabicNumber(String romanNumberStr, int arabicNumber) {
        romanNumber.setRomanNumberStr(romanNumberStr);
        assertEquals(arabicNumber, romanNumber.arabicNumber());
    }
    private static Stream<Arguments> provideRomanNumbersForConversion() {
        return Stream.of(
                Arguments.of("I", 1),
                Arguments.of("II", 2),
                Arguments.of("III", 3),
                Arguments.of("IV", 4),
                Arguments.of("V", 5),
                Arguments.of("VI", 6),
                Arguments.of("VII", 7),
                Arguments.of("VIII", 8),
                Arguments.of("IX", 9),
                Arguments.of("X", 10),
                Arguments.of("XI", 11),
                Arguments.of("XII", 12),
                Arguments.of("XIII", 13),
                Arguments.of("XIV", 14),
                Arguments.of("XV", 15),
                Arguments.of("XVI", 16),
                Arguments.of("XVII", 17),
                Arguments.of("XVIII", 18),
                Arguments.of("XIX", 19),
                Arguments.of("XX", 20),
                Arguments.of("XXI", 21),
                Arguments.of("XXII", 22),
                Arguments.of("XXIII", 23),
                Arguments.of("XXIV", 24),
                Arguments.of("XXV", 25),
                Arguments.of("XXVI", 26),
                Arguments.of("XXVII", 27),
                Arguments.of("XXVIII", 28),
                Arguments.of("XXIX", 29),
                Arguments.of("XXX", 30),
                Arguments.of("XXXI", 31),
                Arguments.of("XXXII", 32),
                Arguments.of("XXXIII", 33),
                Arguments.of("XXXIV", 34),
                Arguments.of("XXXV", 35),
                Arguments.of("XXXVI", 36),
                Arguments.of("XXXVII", 37),
                Arguments.of("XXXVIII", 38),
                Arguments.of("XXXIX", 39),
                Arguments.of("XL", 40),
                Arguments.of("XLI", 41),
                Arguments.of("XLII", 42),
                Arguments.of("XLIII", 43),
                Arguments.of("XLIV", 44),
                Arguments.of("XLV", 45),
                Arguments.of("XLVI", 46),
                Arguments.of("XLVII", 47),
                Arguments.of("XLVIII", 48),
                Arguments.of("XLIX", 49),
                Arguments.of("L", 50),
                Arguments.of("LI", 51),
                Arguments.of("LII", 52),
                Arguments.of("LIII", 53),
                Arguments.of("LIV", 54),
                Arguments.of("LV", 55),
                Arguments.of("LVI", 56),
                Arguments.of("LVII", 57),
                Arguments.of("LVIII", 58),
                Arguments.of("LIX", 59),
                Arguments.of("LX", 60),
                Arguments.of("LXI", 61),
                Arguments.of("LXII", 62),
                Arguments.of("LXIII", 63),
                Arguments.of("LXIV", 64),
                Arguments.of("LXV", 65),
                Arguments.of("LXVI", 66),
                Arguments.of("LXVII", 67),
                Arguments.of("LXVIII", 68),
                Arguments.of("LXIX", 69),
                Arguments.of("LXX", 70),
                Arguments.of("LXXI", 71),
                Arguments.of("LXXII", 72),
                Arguments.of("LXXIII", 73),
                Arguments.of("LXXIV", 74),
                Arguments.of("LXXV", 75),
                Arguments.of("LXXVI", 76),
                Arguments.of("LXXVII", 77),
                Arguments.of("LXXVIII", 78),
                Arguments.of("LXXIX", 79),
                Arguments.of("LXXX", 80),
                Arguments.of("LXXXI", 81),
                Arguments.of("LXXXII", 82),
                Arguments.of("LXXXIII", 83),
                Arguments.of("LXXXIV", 84),
                Arguments.of("LXXXV", 85),
                Arguments.of("LXXXVI", 86),
                Arguments.of("LXXXVII", 87),
                Arguments.of("LXXXVIII", 88),
                Arguments.of("LXXXIX", 89)
        );
    }

    @ParameterizedTest
    @MethodSource("provideRomanNumbersForValueOfOnes")
    public void getValueOfOnes(String romanNumberStr, int arabicValueOfOne) {
        romanNumber.setRomanNumberStr(romanNumberStr);
        assertEquals(arabicValueOfOne, romanNumber.getValueOnes(0));
    }
    private static Stream<Arguments> provideRomanNumbersForValueOfOnes() {
        return Stream.of(
                Arguments.of("I", 1),
                Arguments.of("II", 1),
                Arguments.of("III", 1),
                Arguments.of("IV", -1),
                Arguments.of("IX", -1),
                Arguments.of("X", 10),
                Arguments.of("XX", 10),
                Arguments.of("XXX", 10)
        );
    }

    /*
    private int getArabicValue (String romanDigit) {

    @ParameterizedTest
    @MethodSource("provideRomanNumbersToVerifyNextDigitEqualOrLess")
    public void verifyNextDigitEqualOrLess(String romanNumberStr, boolean digitIsEqualOrLess) {
        romanNumber.setRomanNumberStr(romanNumberStr);
        assertEquals(digitIsEqualOrLess, romanNumber.isNextDigitEqualOrLess(0));
    }
    private static Stream<Arguments> provideRomanNumbersToVerifyNextDigitEqualOrLess() {
        return Stream.of(
                Arguments.of("I", true),
                Arguments.of("II", true),
                Arguments.of("IV", false),
                Arguments.of("IX", false),
                Arguments.of("X", true),
                Arguments.of("XI", true),
                Arguments.of("XV", true),
                Arguments.of("XX", true),
                Arguments.of("XL", false),
                Arguments.of("XC", false),
                Arguments.of("C", true),
                Arguments.of("CI", true),
                Arguments.of("CV", true),
                Arguments.of("CX", true),
                Arguments.of("CL", true),
                Arguments.of("CC", true),
                Arguments.of("CD", false),
                Arguments.of("CM", false),
                Arguments.of("M", true)
        );
    }
    */

    @ParameterizedTest
    @MethodSource("provideRomanNumbersForDigitAt")
    public void getDigitAt(String romanNumberStr, int digitAt, String romanDigitAt) {
        romanNumber.setRomanNumberStr(romanNumberStr);
        assertEquals(romanDigitAt, romanNumber.digitAt(digitAt));
    }
    private static Stream<Arguments> provideRomanNumbersForDigitAt() {
        return Stream.of(
                Arguments.of("MDCLXVI", 0, "M"),
                Arguments.of("MDCLXVI", 1, "D"),
                Arguments.of("MDCLXVI", 2, "C"),
                Arguments.of("MDCLXVI", 3, "L"),
                Arguments.of("MDCLXVI", 4, "X"),
                Arguments.of("MDCLXVI", 5, "V"),
                Arguments.of("MDCLXVI", 6, "I")
        );
    }

    @ParameterizedTest
    @MethodSource("provideRomanNumbersForLength")
    public void getLength(String romanNumberStr, int length) {
        romanNumber.setRomanNumberStr(romanNumberStr);
        assertEquals(length, romanNumber.length());
    }
    private static Stream<Arguments> provideRomanNumbersForLength() {
        return Stream.of(
                Arguments.of("I", 1),
                Arguments.of("II", 2),
                Arguments.of("III", 3),
                Arguments.of("XIII", 4),
                Arguments.of("XXIII", 5),
                Arguments.of("XXXIII", 6),
                Arguments.of("CXXXIII", 7),
                Arguments.of("CCXXXIII", 8),
                Arguments.of("CCCXXXIII", 9),
                Arguments.of("MCCCXXXIII", 10)
        );
    }

    @ParameterizedTest
    @MethodSource("provideRomanNumbersToVerifyNull")
    public void verifyRomanNumbersIsNull(String romanNumberStr, boolean romanNumberIsNull) {
        romanNumber.setRomanNumberStr(romanNumberStr);
        assertEquals(romanNumberIsNull, romanNumber.isNull());
    }
    private static Stream<Arguments> provideRomanNumbersToVerifyNull() {
        return Stream.of(
                Arguments.of(null, true),
                Arguments.of("V", false)
        );
    }

    @ParameterizedTest
    @MethodSource("provideRomanNumbersToCharInPosition")
    public void verifyThereIsCharInPosition(String romanNumberStr, int position, boolean thereIsCharInPosition) {
        romanNumber.setRomanNumberStr(romanNumberStr);
        assertEquals(thereIsCharInPosition, romanNumber.isThereCharInPosition(position));
    }
    private static Stream<Arguments> provideRomanNumbersToCharInPosition() {
        return Stream.of(
                Arguments.of("I", 0, true),
                Arguments.of("I", 1, false),
                Arguments.of("II", 0, true),
                Arguments.of("II", 1, true),
                Arguments.of("II", 2, false),
                Arguments.of("III", 0, true),
                Arguments.of("III", 1, true),
                Arguments.of("III", 2, true),
                Arguments.of("III", 3, false)
        );
    }

    @ParameterizedTest
    @MethodSource("provideRomanNumbersToVerifyEmpty")
    public void verifyRomanNumbersIsEmpty(String romanNumberStr, boolean romanNumberIsEmpty) {
        romanNumber.setRomanNumberStr(romanNumberStr);
        assertEquals(romanNumberIsEmpty, romanNumber.isEmpty());
    }
    private static Stream<Arguments> provideRomanNumbersToVerifyEmpty() {
        return Stream.of(
                Arguments.of("", true),
                Arguments.of("V", false)
        );
    }
}