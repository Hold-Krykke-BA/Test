public class StringUtility {
    /**
     * Reverses a string
     * aBc -> cBa
     */
    public String reverseString(String str){
        if (str == null) throw new NullPointerException("String cannot be null");
        if (str.isEmpty()) return str;

        StringBuilder reversedStr = new StringBuilder();
        for (int i = str.length() - 1; i >= 0; i--) {
            reversedStr.append(str.charAt(i));
        }
        return reversedStr.toString();
    }


    /**
     * Uppercase a String
     * aBc -> ABC
     */
    public String uppercaseString(String str){
        if (str == null) throw new NullPointerException("String cannot be null");
        if (str.isEmpty()) return str;

        StringBuilder uppercasedStr = new StringBuilder();
        char[] chars = str.toCharArray();
        for (char _char : chars) {
            if (_char >= 'a' && _char <= 'z')
                uppercasedStr.append((char) (_char - ('a' - 'A')));
            else
                uppercasedStr.append(_char);
        }
        return uppercasedStr.toString();
    }

    /**
     * Lowercase a String
     * AbC -> abc
     */
    public String lowercaseString(String str){
        if (str == null) throw new NullPointerException("String cannot be null");
        if (str.isEmpty()) return str;
        StringBuilder lowercasedStr = new StringBuilder();
        char[] chars = str.toCharArray();
        for (char _char : chars) {
            if (_char >= 'A' && _char <= 'Z')
                lowercasedStr.append((char) (_char + ('a' - 'A')));
            else
                lowercasedStr.append(_char);
        }
        return lowercasedStr.toString();
    }
}