package com.jorgepatrick;
public class TestGenerator {
    private RomanNumberValidator romanNumberValidator = new RomanNumberValidator();
    private RomanNumber romanNumber = new RomanNumber(romanNumberValidator);
    private NumberConverter numberConverter = new NumberConverter(romanNumber);
    public void numberConverterTest() {
        int convertToRomanQtde = 178;
        int convertToArabicQtde = 89;
        String testLine;

        System.out.println("convertToRoman");

        for (int i = 1; i <= convertToRomanQtde; i++) {
            testLine = "Arguments.of(" + i + ", \"" + numberConverter.parseArabicToRoman(i) +  "\"),";
            System.out.println(testLine);
        }

        System.out.println("convertToArabic");

        for (int i = 1; i <= convertToArabicQtde; i++) {
            testLine = "Arguments.of(\"" + numberConverter.parseArabicToRoman(i) +  "\", " + i + "),";
            System.out.println(testLine);
        }
    }

    public void romanCalculatorIntegrationTest() {
        int sumResultQtde = 178;
        int maxValueAddend = 89;
        String testLine;
        int firstAdend;
        int secondAdend;

        System.out.println("sumRomanNumbers");

        for (int sumResult = 2; sumResult <= sumResultQtde; sumResult++) {
            do {
                firstAdend = (int)Math.floor(Math.random() * (maxValueAddend - 1 + 1) + 1);
                secondAdend = sumResult - firstAdend;
            } while (secondAdend > maxValueAddend || secondAdend < 1);

            testLine = "Arguments.of(\"" +
                        numberConverter.parseArabicToRoman(firstAdend) +
                        "\", \"" +
                        numberConverter.parseArabicToRoman(secondAdend) +
                        "\", \"" +
                        numberConverter.parseArabicToRoman(sumResult) +
                        "\"),";
            System.out.println(testLine);
        }
    }
}
