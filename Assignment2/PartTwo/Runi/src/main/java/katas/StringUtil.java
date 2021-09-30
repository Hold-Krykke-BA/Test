package katas;

import java.util.Locale;
import java.util.stream.Collectors;

public class StringUtil {

    /**
     * Reverses a string while keeping its structure of lowercase/uppercase.
     * <p>
     * aBc -> cBa
     *
     * @param input the string to reverse
     * @return the reversed string
     */
    public static String reverse(String input) {
        if (input == null) throw new IllegalArgumentException("String \"input\" must not be null");
        if (input == "") return input;

        char[] inputArr = input.toCharArray();
        String reverse = "";

        for (int i = inputArr.length - 1; i >= 0; i--) {
            reverse += inputArr[i];
        }
        return reverse;
    }

    /**
     * Capitalizes (not really, uppercases) a string input
     * <p>
     * aBc -> ABC
     * <p>
     * allowed values: a-zA-Z
     * <p>
     * and spaces
     *
     * @param input the string to capitalize
     * @return the capitalized string
     */
    public static String capitalize(String input) {
        if (input == null) throw new IllegalArgumentException("String \"input\" must not be null");
        if (input == "") return input;
        int diff = 'a' - 'A'; // 32 (difference in ASCII values)

        StringBuilder sb = new StringBuilder();
        for (char ch : input.toCharArray()) {
            if (ch >= 'a' && ch <= 'z')
                sb.append((char) (ch - diff));
            else
                sb.append(ch);
        }
        return sb.toString();

//        String result = "";
//        for (char ch : input.toCharArray()) {
//            if (ch >= 'A' && ch <= 'Z')
//                ch += 'a' - 'A';
//            else if (ch <= 'A' && ch >= 'Z') ch += 'a' + 'A';
//
//            result += ch;
//        }
//        return result;

//        return input.chars().
//                filter(ch -> ch >= 'a' && ch <= 'z' || ch == ' '). //only valid values
//                mapToObj(ch -> String.valueOf((char) (ch - (diff)))). //turn lowercase to uppercase and typecasts to char
//                collect(Collectors.joining()); //turns stream to String by encounter order ("joining").
    }

    /**
     *
     * Converts the given input string to lowercase if within bounds a-zA-Z
     *
     * @param input
     * @return
     */
    public static String lowercase(String input) {
        if (input == null) throw new IllegalArgumentException("String \"input\" must not be null");
        if (input == "") return input;

        int diff = 'a' - 'A'; // 32 (difference in ASCII values)

        StringBuilder sb = new StringBuilder();
        for (char ch : input.toCharArray()) {
            if (ch >= 'A' && ch <= 'Z')
                sb.append((char) (ch + diff));
            else
                sb.append(ch);
        }
        return sb.toString();
    }
}
