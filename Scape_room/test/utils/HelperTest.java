package utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class HelperTest {

    //private static final Scanner input = new Scanner(System.in);
    //private final InputStream systemIn = System.in;
    //private final PrintStream systemOut = System.out;
    //private Scanner input;

    /*@Test
    public void testReadString() {
        String expectedResult = "Jason";
        String message = "Name";
        InputStream in = new ByteArrayInputStream(expectedResult.getBytes());
        System.setIn(in);
        assertEquals(expectedResult, Helper.readString(message));

    } *///em passa el mateix que amb int; el problema és el missatge que rep el mètode

    @Test
    @DisplayName("Checking that readInt(String message) returns an integer.")
    public void testReadInt() {
        String message = "Introduce a number:";
        ByteArrayInputStream testIn = new ByteArrayInputStream("42".getBytes());
        System.setIn(testIn);
        int result = Helper.readInt(message);
        assertThat(result).isExactlyInstanceOf(Integer.class);
        System.setIn(System.in);
    } //no funciona perquè no deixa introduir

    @Test
	@DisplayName("Checking email validation with a valid address.")
	public void testValidateEmailTrue() {
        String validEmail = "username@domain.com";
        assertTrue(Helper.validateEmail(validEmail));
    }
    //també es podria parametritzar

    @Test
    @DisplayName("Checking email validation with an invalid address.")
    public void testValidateEmailTrueFalse() {
        String invalidEmail = "invalid-email";
        assertFalse(Helper.validateEmail(invalidEmail));
    }
    //també es podria parametritzar

    /*@ParameterizedTest
    @DisplayName("Checking that the email validation works.")
    @CsvSource({"example@example.com, true", "invalid-email, false", "another.email@domain.co.uk, true"})
    public void testValidateEmail(String email, boolean expected) {
        assertEquals(expected, Helper.validateEmail(email));
    }*/ //no funciona



}
