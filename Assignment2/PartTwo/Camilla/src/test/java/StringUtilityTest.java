import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class StringUtilityTest {

    @Test
    void testReverseString_expectedUse() {
        StringUtility strUtil = new StringUtility();
        String exp = "fedcba";
        String res = strUtil.reverseString("abcdef");
        assertEquals(exp, res);
    }

    @Test
    void testReverseString_Null() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> {
            StringUtility strUtil = new StringUtility();
            strUtil.reverseString(null);
        });
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains("String cannot be null"));
    }

    @Test
    void testReverseString_edgeCases() {
        StringUtility strUtil = new StringUtility();
        String exp1 = "f";
        String res1 = strUtil.reverseString("f");

        String exp2 = "123";
        String res2 = strUtil.reverseString("321");

        String exp3 = "";
        String res3 = strUtil.reverseString("");

        assertEquals(exp1, res1);
        assertEquals(exp2, res2);
        assertEquals(exp3, res3);
    }

    @Test
    void testUppercaseString_expectedUse() {
        StringUtility strUtil = new StringUtility();
        String exp = "ABCDEF";
        String res = strUtil.uppercaseString("abcdef");
        assertEquals(exp, res);
    }

    @Test
    void testUppercaseString_Null() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> {
            StringUtility strUtil = new StringUtility();
            strUtil.uppercaseString(null);
        });
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains("String cannot be null"));
    }

    @Test
    void testUppercaseString_edgeCases() {
        StringUtility strUtil = new StringUtility();
        String exp1 = "F";
        String res1 = strUtil.uppercaseString("f");

        String exp2 = "123";
        String res2 = strUtil.uppercaseString("123");

        String exp3 = "";
        String res3 = strUtil.uppercaseString("");

        assertEquals(exp1, res1);
        assertEquals(exp2, res2);
        assertEquals(exp3, res3);
    }


    @Test
    void testLowercaseString_expectedUse() {
        StringUtility strUtil = new StringUtility();
        String exp = "abcdef";
        String res = strUtil.lowercaseString("ABCDEF");
        assertEquals(exp, res);
    }

    @Test
    void testLowercaseString_Null() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> {
            StringUtility strUtil = new StringUtility();
            strUtil.lowercaseString(null);
        });
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains("String cannot be null"));
    }

    @Test
    void testLowercaseString_edgeCases() {
        StringUtility strUtil = new StringUtility();
        String exp1 = "f";
        String res1 = strUtil.lowercaseString("F");

        String exp2 = "123";
        String res2 = strUtil.lowercaseString("123");

        String exp3 = "";
        String res3 = strUtil.lowercaseString("");

        assertEquals(exp1, res1);
        assertEquals(exp2, res2);
        assertEquals(exp3, res3);
    }

}