package com.jorgepatrick;

public class RomanCalculator {
    private final NumberConverter converter;

    public RomanCalculator(NumberConverter converter) {
        this.converter = converter;
    }

    public String sumRomanNumbers(final String firstAddend, final String secondAddend) {

        final int firstArabicNumber = converter.parseRomanToArabic(firstAddend);
        final int secondArabicNumber = converter.parseRomanToArabic(secondAddend);

        int arabicNumberSum = firstArabicNumber + secondArabicNumber;

        return converter.parseArabicToRoman(arabicNumberSum);
    }

}

