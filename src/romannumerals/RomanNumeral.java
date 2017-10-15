package romannumerals;

/**
 *
 * @author Nadiia Trofymova 
 * Class converter
 */
public class RomanNumeral {

    private static final int[] mNumbers = {1000, 900, 500, 400, 100, 90,
        50, 40, 10, 9, 5, 4, 1};

    private static final String[] mLetters = {"M", "CM", "D", "CD", "C", "XC",
        "L", "XL", "X", "IX", "V", "IV", "I"};

    /**
     * Method for converting ordinary numeral to Roman numeral.
     *
     * @param ordinary_numeral numeral that less than 4000
     * @return result string
     */
    public static String ordinary_num_to_roman_num(int ordinary_numeral) {
        String roman_numeral = "";
        for (int i = 0; i < mNumbers.length; i++) {
            while (ordinary_numeral >= mNumbers[i]) {
                roman_numeral += mLetters[i];
                ordinary_numeral -= mNumbers[i];
            }
        }
        return roman_numeral;

    }

    /**
     * Method for converting Roman numeral to ordinary numeral. 
     *
     * @param roman_numeral numeral.
     * @return int result
     */
    public static int roman_num_to_ordinary_num(String roman_numeral) {

        int result_ordinary_numeral = 0;
        int step = 0;

        while (step < roman_numeral.length()) {
            char letter = roman_numeral.charAt(step);
            int ordinary_numeral = rules_roman_to_ord(letter);

            step++;
            if (step == roman_numeral.length()) {
                result_ordinary_numeral += ordinary_numeral;
            } else {
                int nextNum = rules_roman_to_ord(roman_numeral.charAt(step));
                if (nextNum > ordinary_numeral) {
                    result_ordinary_numeral += (nextNum - ordinary_numeral);
                    step++;
                } else {
                    result_ordinary_numeral += ordinary_numeral;
                }
            }
        }
        return result_ordinary_numeral;
    }

    private static int rules_roman_to_ord(char roman_numeral) {
        int ordinary_numeral = 0;
        switch (roman_numeral) {
            case 'I': {
                ordinary_numeral = 1;
                break;
            }
            case 'V': {
                ordinary_numeral = 5;
                break;
            }
            case 'X': {
                ordinary_numeral = 10;
                break;
            }
            case 'L': {
                ordinary_numeral = 50;
                break;
            }
            case 'C': {
                ordinary_numeral = 100;
                break;
            }
            case 'D': {
                ordinary_numeral = 500;
                break;
            }
            case 'M': {
                ordinary_numeral = 1000;
                break;
            }
        }
        return ordinary_numeral;
    }
}
