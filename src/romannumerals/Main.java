package romannumerals;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

/**
 *
 * @author Nadiia Trofymova
 */
public class Main {

    private static final int YES = 1;
    private static final int NO = 2;
    private static final int ERROR = -1;
    private static final int MAX = 3999;

    /**
     * A method that checks to see if there are four similar digits in a string
     *
     * @param roman_numeral string with number
     * @return true if there are
     */
    private static boolean check_numeral(String roman_numeral) {
        char[] arr = roman_numeral.toCharArray();
        int count = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i + 1] == arr[i]) {
                count++;
            } else {
                count = 0;
            }
            if (count >= 3) {
                System.out.println("You can`t repeat the four digits in a row.");
                return true;
            }
        }
        return false;
    }

    /**
     * Method that will help to make the correct Roman numeral.
     *
     * @param numeral roman numeral
     */
    private static String find_num(String numeral) {
        for (int i = 0; i < numeral.length(); i++) {
            char ch = numeral.charAt(i);
            if (ch != 'I' && ch != 'V' && ch != 'X' && ch != 'L'
                    && ch != 'C' && ch != 'D' && ch != 'M') {
                numeral = numeral.replace(ch, ' ');
            }
        }
        boolean was_replaces = numeral.contains(" ");
        if (was_replaces && !checkEnter(numeral)) {
            numeral = numeral.replaceAll(" ", "");
            System.out.println("Maybe you want to convert " + numeral);
        }
        return numeral;
    }

    private static void show_result_or_find_errors(int result, String numeral) {
        if (result > MAX) {
            System.out.println("Roman numeral must have value 3999 or less. Try again.");
        }
        if (numeral.equals(RomanNumeral.ordinary_num_to_roman_num(result))) {
            System.out.println("Your result: " + result);
        } else {
            System.out.println("Incorrect data. Try again.");
        }
    }

    private static String getString() {
        String S = "";
        try {
            LineNumberReader L = new LineNumberReader(new InputStreamReader(System.in, "CP1251"));
            S = L.readLine();
        } catch (IOException ioe) {
            S = "";
        }
        return S;
    }

    private static int getInt() {
        try {
            int num = Integer.parseInt(getString());
            if (num <= 0) {
                System.out.println("The value is negative or zero");
                return ERROR;
            }
            return num;
        } catch (NumberFormatException ie) {
            System.out.println("Incorrect data");
            return ERROR;
        }
    }

    private static boolean checkEnter(String numeral) {
        return !(numeral.contains("I") || numeral.contains("V") || numeral.contains("L")
                || numeral.contains("X") || numeral.contains("C") || numeral.contains("D")
                || numeral.contains("M"));
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int choice = 0;
        int repeat;
        do {

            do {
                System.out.println("Press");
                System.out.println("1 - Convert ordinary numeral to Roman numeral");
                System.out.println("2 - Convert Roman numeral to ordinary numeral");
                choice = getInt();
            } while (choice != YES && choice != NO);

            switch (choice) {
                case 1: {
                    int numeral;
                    do {
                        System.out.println("Enter your ordinary numeral");
                        numeral = getInt();
                    } while (numeral == ERROR || numeral > MAX);
                    String result = RomanNumeral.ordinary_num_to_roman_num(numeral);
                    System.out.println("Your result: " + result);
                    break;
                }
                case 2: {
                    String numeral;
                    do {
                        System.out.println("Enter your Roman numeral");
                        numeral = getString();
                        numeral = numeral.toUpperCase();
                        numeral = find_num(numeral);
                    } while (checkEnter(numeral) || check_numeral(numeral));

                    int result = RomanNumeral.roman_num_to_ordinary_num(numeral);
                    show_result_or_find_errors(result, numeral);
                    break;
                }
            }
            System.out.println("Press 1 to start again");
            repeat = getInt();
        } while (repeat == YES);
    }
}
