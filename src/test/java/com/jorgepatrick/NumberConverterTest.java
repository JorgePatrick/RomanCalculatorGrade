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
public class NumberConverterTest {
    private NumberConverter numberConverter;
    @Mock
    private RomanNumber romanNumber;

    @BeforeEach
    public void setup(){
        numberConverter = new NumberConverter(romanNumber);
    }

    @ParameterizedTest
    @MethodSource("provideRomanNumbersForConversion")
    public void convertToRoman(int arabicNumber, String romanNumberStr) {
        assertEquals(romanNumberStr, numberConverter.parseArabicToRoman(arabicNumber));
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
                Arguments.of(20, "XX"),
                Arguments.of(30, "XXX"),
                Arguments.of(40, "XL"),
                Arguments.of(50, "L"),
                Arguments.of(60, "LX"),
                Arguments.of(70, "LXX"),
                Arguments.of(80, "LXXX"),
                Arguments.of(90, "XC"),
                Arguments.of(100, "C"),
                Arguments.of(200, "CC"),
                Arguments.of(300, "CCC"),
                Arguments.of(400, "CD"),
                Arguments.of(500, "D"),
                Arguments.of(600, "DC"),
                Arguments.of(700, "DCC"),
                Arguments.of(800, "DCCC"),
                Arguments.of(900, "CM"),
                Arguments.of(1000, "M"),
                Arguments.of(2000, "MM"),
                Arguments.of(3000, "MMM"),
                Arguments.of(3998, "MMMCMXCVIII")
        );
    }

    @ParameterizedTest
    @MethodSource("provideArabicNumbersForConversion")
    public void convertToArabic(String romanNumberStr, int arabicNumber) {
        when(romanNumber.arabicNumber()).thenReturn(arabicNumber);
        assertEquals(arabicNumber, numberConverter.parseRomanToArabic(romanNumberStr));
    }
    private static Stream<Arguments> provideArabicNumbersForConversion() {
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
                Arguments.of("XX", 20),
                Arguments.of("XXX", 30),
                Arguments.of("XL", 40),
                Arguments.of("L", 50),
                Arguments.of("LX", 60),
                Arguments.of("LXX", 70),
                Arguments.of("LXXX", 80),
                Arguments.of("XC", 90),
                Arguments.of("C", 100),
                Arguments.of("CC", 200),
                Arguments.of("CCC", 300),
                Arguments.of("CD", 400),
                Arguments.of("D", 500),
                Arguments.of("DC", 600),
                Arguments.of("DCC", 700),
                Arguments.of("DCCC", 800),
                Arguments.of("CM", 900),
                Arguments.of("M", 1000),
                Arguments.of("MCMXCIX", 1999)
        );
    }
}
