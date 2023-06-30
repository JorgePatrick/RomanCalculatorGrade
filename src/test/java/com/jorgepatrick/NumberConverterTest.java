package com.jorgepatrick;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.stubbing.OngoingStubbing;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class NumberConverterTest {

    private NumberConverter numberConverter;
//    @Mock
    private RomanNumberValidator romanNumberValidator;

    @BeforeEach
    public void setup(){
        romanNumberValidator = new RomanNumberValidator();
        numberConverter = new NumberConverter(romanNumberValidator);
    }

    @ParameterizedTest
    @MethodSource("provideRomanNumbersForConversion")
    public void convertToRoman(int arabicNumber, String romanNumber) {
        assertEquals(romanNumber, numberConverter.parseArabicToRoman(arabicNumber));
    }
    private static Stream<Arguments> provideRomanNumbersForConversion() {
        return Stream.of(
                Arguments.of(1, "I"),
                Arguments.of(2, "II"),
                Arguments.of(3, "III"),
                Arguments.of(4, "IV"),
                Arguments.of(5, "V"),
                Arguments.of(6, "VI"),
                Arguments.of(7, "VII"),
                Arguments.of(8, "VIII"),
                Arguments.of(9, "IX"),
                Arguments.of(10, "X"),
                Arguments.of(11, "XI"),
                Arguments.of(12, "XII"),
                Arguments.of(13, "XIII"),
                Arguments.of(14, "XIV"),
                Arguments.of(15, "XV"),
                Arguments.of(16, "XVI"),
                Arguments.of(17, "XVII"),
                Arguments.of(18, "XVIII"),
                Arguments.of(19, "XIX"),
                Arguments.of(20, "XX")
        );
    }
    @ParameterizedTest
    @MethodSource("provideArabicNumbersForConversion")
    public void convertToArabic(String romanNumber, int arabicNumber) {
//        when(romanNumberValidator.validateRomanNumber(romanNumber));
        assertEquals(arabicNumber, numberConverter.parseRomanToArabic(romanNumber));
    }
    private static Stream<Arguments> provideArabicNumbersForConversion() {
        return Stream.of(
                Arguments.of("I",1),
                Arguments.of("II", 2),
                Arguments.of("III", 3),
                Arguments.of("IV", 4),
                Arguments.of("V", 5),
                Arguments.of("VI", 6),
                Arguments.of("VII", 7),
                Arguments.of("VIII", 8)
/**                Arguments.of("IX", 9),
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
                Arguments.of("XX", 20) */
        );
    }
}
