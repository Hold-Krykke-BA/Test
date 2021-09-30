package katas.one;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

//Test unicode?
//Test emojis?
//Test surrogate pairs (https://stackoverflow.com/questions/5903008/what-is-a-surrogate-pair-in-java)

/**
 * TDD
 * Reverse string
 * Capitalize string
 * Lowercase string
 */
@Tag("katas.one.StringUtil")
class StringUtilTest {

    @Tag("Reverse")
    @Nested
    class TestReverse {

        @Test
        @DisplayName("\"aBc\" -> \"cBa\"")
        void testReverseString() {
            //Arrange
            String testParameter = "aBc";
            String expectedResult = "cBa";

            //Act
            String result = StringUtil.reverse(testParameter);


            //Assert
            assertEquals(expectedResult, result);
        }

        @Test
        @DisplayName("long string")
        void testReverseLongString() {
            //Arrange
            String testParameter = "This is a very long string that the ½§1325#&@ function should still pass.-//";
            String expectedResult = "//-.ssap llits dluohs noitcnuf @&#5231§½ eht taht gnirts gnol yrev a si sihT";

            //Act
            String result = StringUtil.reverse(testParameter);

            //Assert
            assertEquals(expectedResult, result);
        }

        @Test
        @DisplayName("null -> throws IAE")
        void testReverseNullStringShouldThrow() {
            //Arrange
            String testParameter = null;
            String expectedResult = "String \"input\" must not be null";

            //Act

            Exception exception = assertThrows(IllegalArgumentException.class, () ->
                    StringUtil.reverse(testParameter));

            //Assert
            assertEquals(expectedResult, exception.getMessage());
        }

        @Test
        @DisplayName("empty \"\" -> \"\"")
        void testReverseEmptyStringShouldPass() {
            //Arrange
            String testParameter = "";
            String expectedResult = "";

            //Act
            String result = StringUtil.reverse(testParameter);

            //Assert
            assertEquals(expectedResult, result);
        }
    }

    @Nested
    @Tag("Capitalize")
    class TestCapitalize {

        @Test
        @DisplayName("\"aBc\" -> \"ABC\"")
        void testCapitalizeString() {
            //Arrange
            String testParameter = "aBc";
            String expectedResult = "ABC";

            //Act
            String result = StringUtil.capitalize(testParameter);

            //Assert
            assertEquals(expectedResult, result);
        }

        @Test
        @DisplayName("long string")
        void testCapitalizeLongString() {
            //Arrange
            String testParameter = "Long sEnTenCe wITH MULTIPLE casings";
            String expectedResult = "LONG SENTENCE WITH MULTIPLE CASINGS";

            //Act
            String result = StringUtil.capitalize(testParameter);

            //Assert
            assertEquals(expectedResult, result);
        }

        @Test
        @DisplayName("null -> throws IAE")
        void testCapitalizeNullStringShouldThrow() {
            //Arrange
            String testParameter = null;
            String expectedResult = "String \"input\" must not be null";

            //Act

            Exception exception = assertThrows(IllegalArgumentException.class, () ->
                    StringUtil.capitalize(testParameter));

            //Assert
            assertEquals(expectedResult, exception.getMessage());
        }

        @Test
        @DisplayName("empty \"\" -> \"\"")
        void testCapitalizeEmptyStringShouldPass() {
            //Arrange
            String testParameter = "";
            String expectedResult = "";

            //Act
            String result = StringUtil.capitalize(testParameter);

            //Assert
            assertEquals(expectedResult, result);
        }
    }

    @Nested
    @Tag("Lowercase")
    class TestLowercase {

        @Test
        @DisplayName("aBc -> abc")
        void testLowercaseString() {
            //Arrange
            String testParameter = "aBc";
            String expectedResult = "abc";

            //Act
            String result = StringUtil.lowercase(testParameter);

            //Assert
            assertEquals(expectedResult, result);
        }

        @Test
        @DisplayName("long string")
        void testLowercaseLongString() {
            //Arrange
            String testParameter = "Long sEnTenCe wITH MULTIPLE casings";
            String expectedResult = "long sentence with multiple casings";

            //Act
            String result = StringUtil.lowercase(testParameter);

            //Assert
            assertEquals(expectedResult, result);
        }

        @Test
        @DisplayName("null -> throws IAE")
        void testLowercaseeNullStringShouldThrow() {
            //Arrange
            String testParameter = null;
            String expectedResult = "String \"input\" must not be null";

            //Act

            Exception exception = assertThrows(IllegalArgumentException.class, () ->
                    StringUtil.lowercase(testParameter));

            //Assert
            assertEquals(expectedResult, exception.getMessage());
        }

        @Test
        @DisplayName("empty \"\" -> \"\"")
        void testLowercaseEmptyStringShouldPass() {
            //Arrange
            String testParameter = "";
            String expectedResult = "";

            //Act
            String result = StringUtil.lowercase(testParameter);

            //Assert
            assertEquals(expectedResult, result);
        }
    }
}